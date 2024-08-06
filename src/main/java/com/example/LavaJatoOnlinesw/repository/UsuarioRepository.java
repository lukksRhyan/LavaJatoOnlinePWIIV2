package com.example.LavaJatoOnlinesw.repository;

import com.example.LavaJatoOnlinesw.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Usuario findByUsername(String Username);
}

