package com.ramires.gestaousuarios.service;

import com.ramires.gestaousuarios.model.Usuario;
import com.ramires.gestaousuarios.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public boolean emailValido(String email) {
        if (email == null || email.isBlank()) return false;

        int arroba = email.indexOf("@");

        if (arroba <= 0) return false;
        if (email.indexOf("@", arroba + 1) != -1) return false;
        String dominio = email.substring(arroba + 1);
        if (!dominio.contains(".")) return false;
        if (dominio.startsWith(".") || dominio.endsWith(".")) return false;
        if (Character.isDigit(email.charAt(0))) return false;

        return true;
    }

    public boolean nomeValido(String nome) {
        for (char c : nome.toCharArray()) {
            if (!Character.isLetter(c) && c != ' ') {
                return false;
            }
        }
        return true;
    }

    @Transactional
    public void criarUsuario(String nome, String email) {
        if (!nomeValido(nome)) {
            throw new IllegalArgumentException("O nome deve conter apenas letras.");
        }
        if (!emailValido(email)) {
            throw new IllegalArgumentException("E-mail inválido.");
        }
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

    @Transactional
    public void atualizarUsuario(Long id, String novoNome, String novoEmail) {
        if (!nomeValido(novoNome)) {
            throw new IllegalArgumentException("O nome deve conter apenas letras.");
        }
        if (!emailValido(novoEmail)) {
            throw new IllegalArgumentException("E-mail inválido.");
        }
        Usuario usuario = repository.buscarPorId(id);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário com ID " + id + " não encontrado.");
        }
        usuario.setNome(novoNome);
        usuario.setEmail(novoEmail);
        repository.atualizar(usuario);
        System.out.println("\nUsuário atualizado com sucesso!");
        System.out.println(usuario);
    }

    @Transactional
    public void removerUsuario(Long id) {
        Usuario usuario = repository.buscarPorId(id);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário com ID " + id + " não encontrado.");
        }
        repository.remover(id);
        System.out.println("\nUsuário ID " + id + " removido com sucesso.");
    }

    public List<Usuario> listarTodos() {
        return repository.listarTodos();
    }

    public Usuario buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }
}