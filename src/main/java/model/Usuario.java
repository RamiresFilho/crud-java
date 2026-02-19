package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario extends Pessoa {

    private String email;

    public Usuario() {
        super();
    }

    public Usuario(Long id, String nome, String email) {
        super(id, nome);
        this.email = email;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public void exibirDados() {
        System.out.println("ID: " + getId() + " | Nome: " + getNome() + " | Email: " + email);
    }

    @Override
    public String toString() {
        return "ID: " + getId() + " | Nome: " + getNome() + " | Email: " + email;
    }
}