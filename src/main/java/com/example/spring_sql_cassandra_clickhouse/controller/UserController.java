package com.example.spring_sql_cassandra_clickhouse.controller;

import com.example.spring_sql_cassandra_clickhouse.cassandra.model.UserCassandra;
import com.example.spring_sql_cassandra_clickhouse.service.UserService;
import com.example.spring_sql_cassandra_clickhouse.sql_clickhouse.model.UserSql;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class UserController {
    private UserService userService;

    @PostMapping("/users_sql")
    public ResponseEntity<UserSql> saveUserSql(@RequestBody UserSql userMySql) {
        return ResponseEntity.ok(userService.saveUserSql(userMySql));
    }

    @PostMapping("/users_cassandra")
    public ResponseEntity<UserCassandra> saveUserCassandra(@RequestBody UserCassandra user) {
        return ResponseEntity.ok(userService.saveUserCassandra(user));
    }

    @PostMapping("/users_clickhouse")
    public ResponseEntity<UserSql> saveUserClickhouse(@RequestBody UserSql user) {
        return ResponseEntity.ok(userService.insertClickhouse(user));
    }

    @GetMapping("/users_sql/{id}")
    public ResponseEntity<UserSql> findBySqlId(@PathVariable("id") Integer id){
        return ResponseEntity.ok().body(userService.findSql(id));
    }

    @GetMapping("/users_cassandra/{id}")
    public ResponseEntity<UserCassandra> findByCassandraId(@PathVariable("id") Integer id){
        return ResponseEntity.ok().body(userService.findCassandra(id));
    }

    @GetMapping("/users_clickhouse/{id}")
    public ResponseEntity<UserSql> findByClickhouseId(@PathVariable("id") Integer id){
        return ResponseEntity.ok().body(userService.singleUserClickhouse(id));
    }

    @GetMapping("/check/{id}")
    public String check(@PathVariable("id") Integer id){
        int checking = userService.check(id);
        if(checking==1)
            return "These are equal";
        return "These are not equal";
    }


}
