package com.example.spring_sql_cassandra_clickhouse.service;

import com.example.spring_sql_cassandra_clickhouse.cassandra.model.UserCassandra;
import com.example.spring_sql_cassandra_clickhouse.cassandra.repo.UserCassandraRepository;
import com.example.spring_sql_cassandra_clickhouse.sql_clickhouse.model.UserSql;
import com.example.spring_sql_cassandra_clickhouse.sql_clickhouse.repo.UserSqlRepository;
import com.example.spring_sql_cassandra_clickhouse.ResouceNotFoundException;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;

import static com.example.spring_sql_cassandra_clickhouse.constant.Constants.*;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class UserService {
    private UserCassandraRepository cassandraUserRepository;
    private UserSqlRepository mySqlUserRepository;

    public UserSql insertClickhouse(UserSql user) {
        int id = user.getId();
        String firstName = user.getFirstName();
        String email = user.getEmail();
        String lastName = user.getLastName();

        try (Connection conn = DriverManager.getConnection(clickhouseUrl)) {
            PreparedStatement statement = conn.prepareStatement(clickhouseInsert);

            statement.setInt(1, id);
            statement.setString(2, email);
            statement.setString(3, firstName);
            statement.setString(4, lastName);
            statement.executeUpdate();

            mySqlUserRepository.save(user);
            UserCassandra user1 = new UserCassandra(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName());
            cassandraUserRepository.save(user1);

            System.out.println("Data inserted successfully!");
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;

    }

    public UserSql singleUserClickhouse(int id){
        UserSql userClickHouse = new UserSql();
        try (Connection conn = DriverManager.getConnection(clickhouseUrl)) {
            PreparedStatement statement = conn.prepareStatement(clickhouseRead);
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                int retrievedId = result.getInt("id");
                String retrievedEmail = result.getString("email");
                String retrievedFirstname = result.getString("firstName");
                String retrievedLastName = result.getString("lastName");
                userClickHouse.setId(retrievedId);
                userClickHouse.setEmail(retrievedEmail);
                userClickHouse.setFirstName(retrievedFirstname);
                userClickHouse.setLastName(retrievedLastName);
            } else {
                System.out.println("Record with id=" + id + " not found.");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return userClickHouse;
    }

    public UserSql saveUserSql(UserSql user){
        mySqlUserRepository.save(user);

        UserCassandra user1 = new UserCassandra(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName());
        cassandraUserRepository.save(user1);

        UserSql userMySql = new UserSql(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName());
        insertClickhouse(userMySql);

        return user;
    }
    public UserCassandra saveUserCassandra(UserCassandra user) {
        cassandraUserRepository.save(user);

        UserSql userMySql = new UserSql(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName());
        mySqlUserRepository.save(userMySql);

        insertClickhouse(userMySql);

        return user;
    }
    public UserSql findSql(int id){
        UserSql userMySql=mySqlUserRepository.findById(id).orElseThrow(
                () -> new ResouceNotFoundException("User not found" + id));
        return userMySql;
    }

    public UserCassandra findCassandra(int id){
        UserCassandra user = cassandraUserRepository.findById(id).orElseThrow(
                () -> new ResouceNotFoundException("User not found" + id));
        return user ;
    }

    public int check(int id){
        UserSql user1 = findSql(id);
        UserSql user2 = singleUserClickhouse(id);
        if(user1.getId()!=user2.getId()){
            return 0;
        }
        if(!(user1.getEmail().equals(user2.getEmail()))){
            return 0;
        }
        if(!(user1.getFirstName().equals(user2.getFirstName()))){
            return 0;
        }

        if(!(user1.getLastName().equals(user2.getLastName()))){
            return 0;
        }

        return 1;
    }
}
