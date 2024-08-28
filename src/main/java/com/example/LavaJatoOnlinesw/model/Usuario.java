package com.example.LavaJatoOnlinesw.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table (name = "usuario")
public class Usuario {
	@Id
    private String username;

	private String password;

	public Usuario(String username, String password) {
		super();
        this.username = username;
		this.password = password;

	}
	public Usuario() {

	}
}