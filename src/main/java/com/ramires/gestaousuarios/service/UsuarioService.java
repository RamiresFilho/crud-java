package com.ramires.gestaousuarios.service;

import com.ramires.gestaousuarios.model.Usuario;
import com.ramires.gestaousuarios.repository.UsuarioRepository;

import java.util.List;

public class UsuarioService {

    private UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void criarUsuario(String nome, String email) {
        Usuario usuario = new Usuario(null, nome, email);
        repository.salvar(usuario);
        System.out.println("Usuario criado com sucesso! " + usuario);
    }

    public void listarUsuarios() {
        List<Usuario> lista = repository.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum usuario cadastrado.");
        } else {
            lista.forEach(u -> u.exibirDados());
        }
    }

    public void buscarUsuario(Long id) {
        Usuario usuario = repository.buscarPorId(id);
        if (usuario != null) {
            usuario.exibirDados();
        } else {
            System.out.println("Usuario com ID " + id + " nao encontrado.");
        }
    }

    public void atualizarUsuario(Long id, String novoNome, String novoEmail) {
        Usuario usuario = repository.buscarPorId(id);
        if (usuario == null) {
            System.out.println("Usuario com ID " + id + " nao encontrado.");
            return;
        }
        usuario.setNome(novoNome);
        usuario.setEmail(novoEmail);
        repository.atualizar(usuario);
        System.out.println("Usuario atualizado: " + usuario);
    }

    public void removerUsuario(Long id) {
        Usuario usuario = repository.buscarPorId(id);
        if (usuario == null) {
            System.out.println("Usuario com ID " + id + " nao encontrado.");
            return;
        }
        repository.remover(id);
        System.out.println("Usuario ID " + id + " removido com sucesso.");
    }
}