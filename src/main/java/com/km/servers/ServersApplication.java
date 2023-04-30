package com.km.servers;

import com.km.servers.models.Server;
import com.km.servers.repositories.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
public class ServersApplication {

    @Autowired
    private ServerRepository serverRepository;

    public static void main(String[] args) {
        SpringApplication.run(ServersApplication.class, args);
    }


    @GetMapping("/servers")
    public ResponseEntity<?> getAllServers(@RequestParam(required = false) String id) {
        if (id == null) {
            List<Server> servers = serverRepository.findAll();
            return ResponseEntity.ok(servers);
        } else {
            Optional<Server> server = serverRepository.findById(id);
            return server.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        }
    }

    @PutMapping("/servers")
    public ResponseEntity<?> addServer(@RequestBody Server server) {
        if (server.getName() == null) {
            return ResponseEntity.badRequest().body("Server name and ID are required");
        }
        Server savedServer = serverRepository.save(server);
        return ResponseEntity.ok(savedServer);
    }

    @DeleteMapping("/servers/{id}")
    public ResponseEntity<Void> deleteServer(@PathVariable String id) {
        Optional<Server> server = serverRepository.findById(id);
        if (server.isPresent()) {
            serverRepository.delete(server.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/servers/search")
    public ResponseEntity<?> getServersByName(@RequestParam String name) {
        List<Server> servers = serverRepository.findByNameContainingIgnoreCase(name);
        if (servers.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(servers);
        }
    }

    //USED FOR TESTING
    @PostMapping("/servers/populate")
    public ResponseEntity<Void> populateTestServers() {
        List<Server> testServers = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Server server = new Server(
                    "Test Server " + i,
                    "Java",
                    "Spring Boot"
            );
            testServers.add(server);
        }
        serverRepository.saveAll(testServers);
        return ResponseEntity.ok().build();
    }

}
