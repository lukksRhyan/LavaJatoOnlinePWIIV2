package com.example.LavaJatoOnlinesw.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Usuario {
    private String username;
    private String password;
    private String role;
    @Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long Id;

	public Usuario(Long id, String username, String password, String role) {
		super();
        this.username = username;
		this.password = password;
		this.role = role;
	}

	public Usuario() {

	}


	// Getters and Setters
    
}