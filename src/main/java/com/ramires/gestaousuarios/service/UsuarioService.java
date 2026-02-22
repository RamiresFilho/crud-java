package com.ramires.gestaousuarios.service;

import com.ramires.gestaousuarios.model.Usuario;
import com.ramires.gestaousuarios.repository.UsuarioRepository;

import java.util.List;

public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public boolean emailValido(String email) {
        return email.contains("@") && email.contains(".");
    }

    public void criarUsuario(String nome, String email) {
        Usuario usuario = new Usuario(null, nome, email);
        repository.salvar(usuario);
        System.out.println("\nUsuário cadastrado com sucesso!");
        System.out.println(usuario);
    }

    public void listarUsuarios() {
        List<Usuario> lista = repository.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            lista.forEach(Usuario::exibirDados);
        }
    }

    public void buscarUsuario(Long id) {
        Usuario usuario = repository.buscarPorId(id);
        if (usuario != null) {
            usuario.exibirDados();
        } else {
            System.out.println("Usuário com ID " + id + " não encontrado.");
        }
    }

    public void atualizarUsuario(Long id, String novoNome, String novoEmail) {
        Usuario usuario = repository.buscarPorId(id);
        if (usuario == null) {
            System.out.println("Usuário com ID " + id + " não encontrado.");
            return;
        }
        usuario.setNome(novoNome);
        usuario.setEmail(novoEmail);
        repository.atualizar(usuario);
        System.out.println("\nUsuário atualizado com sucesso!");
        System.out.println(usuario);
    }

    public void removerUsuario(Long id) {
        Usuario usuario = repository.buscarPorId(id);
        if (usuario == null) {
            System.out.println("Usuário com ID " + id + " não encontrado.");
            return;
        }
        repository.remover(id);
        System.out.println("\nUsuário ID " + id + " removido com sucesso.");
    }
}