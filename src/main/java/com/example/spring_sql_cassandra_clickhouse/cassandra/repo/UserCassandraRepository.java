package com.example.spring_sql_cassandra_clickhouse.cassandra.repo;

import com.example.spring_sql_cassandra_clickhouse.cassandra.model.UserCassandra;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@EnableCassandraRepositories
public interface UserCassandraRepository extends CassandraRepository<UserCassandra,Integer> {
}
