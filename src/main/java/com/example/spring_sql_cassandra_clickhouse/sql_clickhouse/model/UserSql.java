package com.example.spring_sql_cassandra_clickhouse.sql_clickhouse.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class UserSql {
    @Id
    @NotNull
    private int id;

    private String email;
    private String firstName;
    private String lastName;

    public UserSql(int id, String email, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserSql() {

    }
}
