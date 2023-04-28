package com.example.spring_sql_cassandra_clickhouse.constant;

public class Constants {

    public static final String domain = "dummy";
    public static final String clientId = "dummy";

    public static final String clientSecret = "dummy";
    public static final String redirectUri  = "http://localhost:8080/callback";
    public static final String swaggerRedirect  = "http://localhost:8080/swagger-ui.html";
    public static final String clickhouseUrl  = "jdbc:clickhouse://localhost:8123/mydatabase";
    public static final String clickhouseInsert  = "INSERT INTO db_integration (id, email,firstName,lastName) VALUES (?, ?,?,?)";
    public static final String clickhouseRead  = "SELECT * FROM db_integration WHERE id = ?";


}
