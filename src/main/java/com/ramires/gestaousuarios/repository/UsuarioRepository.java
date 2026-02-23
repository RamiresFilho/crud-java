package com.ramires.gestaousuarios.repository;

import com.ramires.gestaousuarios.model.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void salvar(Usuario usuario) {
        entityManager.persist(usuario);
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
        entityManager.merge(usuario);
    }

    public void remover(Long id) {
        Usuario usuario = buscarPorId(id);
        if (usuario != null) {
            entityManager.remove(usuario);
        }
    }
}