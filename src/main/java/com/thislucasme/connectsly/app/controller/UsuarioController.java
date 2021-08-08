package com.thislucasme.connectsly.app.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thislucasme.connectsly.app.domain.model.model.Usuario;
import com.thislucasme.connectsly.app.domain.model.repository.UsuarioRepository;
import com.thislucasme.connectsly.app.domain.model.service.CadastroUsuarioService;


@RestController
@RequestMapping(value = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private CadastroUsuarioService usuarioService;
	
	@PostMapping
	public ResponseEntity<?> salvarUser(@RequestBody Usuario usuario) {
		
		try {
			usuarioService.adcionar(usuario);
			return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping
	public List<Usuario> listar(){
		return usuarioRepository.findAll();
	}
	
	@GetMapping("/random-users")
	public ResponseEntity<List<Usuario>> randomUser(@RequestParam int quantidade){
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.randomUsers(quantidade));
	}
	@GetMapping("/contar")
	public long quantidade() {
		return  usuarioRepository.count();
	}
	
	@GetMapping("/mostrar-numeros")
	public List<Integer> listarNumeros(){
		List<Integer> numeros = new ArrayList<>();
		
		for(int i=0; i< 30; i++) {
			numeros.add(i);
		}
		return numeros;
	}
	
}
