package com.km.servers.repositories;

import com.km.servers.models.Server;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ServerRepository extends MongoRepository<Server, String> {
    List<Server> findByNameContainingIgnoreCase(String name);
}

