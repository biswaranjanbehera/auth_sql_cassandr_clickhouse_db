package com.example.spring_sql_cassandra_clickhouse.cassandra.model;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("users")
@Data
@Getter
@Setter
public class UserCassandra {
    @PrimaryKey
    @NotNull
    private int id;

    private String email;
    private String firstName;
    private String lastName;

    public UserCassandra(int id, String email, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserCassandra() {
    }
}
