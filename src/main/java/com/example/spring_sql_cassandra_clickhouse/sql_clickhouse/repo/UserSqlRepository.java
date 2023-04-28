package com.example.spring_sql_cassandra_clickhouse.sql_clickhouse.repo;

import com.example.spring_sql_cassandra_clickhouse.sql_clickhouse.model.UserSql;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface UserSqlRepository extends JpaRepository<UserSql,Integer> {
}
