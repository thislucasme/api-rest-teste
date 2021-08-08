package com.thislucasme.connectsly.app.domain.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thislucasme.connectsly.app.domain.model.model.Usuario;
import com.thislucasme.connectsly.app.domain.model.repository.UsuarioRepository;

@Service
public class CadastroUsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario adcionar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public List<Usuario> randomUsers(int quantidadeUsuario){
		
		List<Usuario> usuariosSorteados = new ArrayList<Usuario>();
		
		Long totalUsuarios = usuarioRepository.count();
		
		for(int i = 1; i <= quantidadeUsuario; i++) {
			
			Long  idSorteado = Sortear.randomLong(1L, totalUsuarios+1);
			Optional<Usuario> usuarioAtual = usuarioRepository.findById(idSorteado);
			
			if(!usuarioAtual.isEmpty()) {
				usuariosSorteados.add(usuarioAtual.get());
			}
		}
		
		return usuariosSorteados;
	}

}
