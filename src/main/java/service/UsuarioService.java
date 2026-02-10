package service;

import model.Usuario;
import repository.UsuarioRepository;
import java.util.List;

public class UsuarioService {

    private UsuarioRepository repository;

    public UsuarioService() {
        this.repository = new UsuarioRepository();
    }

    public void criarUsuario(String nome, String email) {
        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("Nome inválido!");
            return;
        }
        Usuario u = new Usuario(nome, email);
        repository.salvar(u);
        System.out.println("Salvo com sucesso!");
    }

    public List<Usuario> listarUsuarios() {
        return repository.listar();
    }

    public void atualizarUsuario(Long id, String nome, String email) {
        Usuario existe = repository.buscarPorId(id);
        if (existe != null) {
            Usuario u = new Usuario(id, nome, email);
            repository.atualizar(u);
            System.out.println("Usuário atualizado!");
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    public void deletarUsuario(Long id) {
        if (repository.buscarPorId(id) != null) {
            repository.deletar(id);
            System.out.println("Usuário deletado!");
        } else {
            System.out.println("Usuário não encontrado para deletar.");
        }
    }
}