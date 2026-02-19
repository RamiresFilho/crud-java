package repository;

import jakarta.persistence.EntityManager;
import model.Usuario;

import java.util.List;

public class UsuarioRepository {

    private EntityManager entityManager;

    public UsuarioRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void salvar(Usuario usuario) {
        entityManager.getTransaction().begin();
        entityManager.persist(usuario);
        entityManager.getTransaction().commit();
    }

    public List<Usuario> listarTodos() {
        return entityManager
                .createQuery("SELECT u FROM Usuario u", Usuario.class)
                .getResultList();
    }

    public Usuario buscarPorId(Long id) {
        return entityManager.find(Usuario.class, id);
    }

    public void atualizar(Usuario usuario) {
        entityManager.getTransaction().begin();
        entityManager.merge(usuario);
        entityManager.getTransaction().commit();
    }

    public void remover(Long id) {
        Usuario usuario = buscarPorId(id);
        if (usuario != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(usuario);
            entityManager.getTransaction().commit();
        }
    }
}