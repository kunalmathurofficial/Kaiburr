package com.km.servers;

import com.km.servers.models.Server;
import com.km.servers.repositories.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


}
