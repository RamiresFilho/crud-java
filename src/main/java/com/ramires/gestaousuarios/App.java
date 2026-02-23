package com.ramires.gestaousuarios;

import com.ramires.gestaousuarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.Scanner;

@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired
    private UsuarioService service;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        boolean rodando = true;

        System.out.println("\n============================================");
        System.out.println("========     GESTÃO DE USUÁRIOS     ========");
        System.out.println("============================================");

        while (rodando) {
            System.out.println("\n--------------------------------------------");
            System.out.println("  1. Cadastrar novo usuário");
            System.out.println("  2. Listar todos os usuários");
            System.out.println("  3. Buscar usuário por ID");
            System.out.println("  4. Atualizar usuário");
            System.out.println("  5. Remover usuário");
            System.out.println("  0. Sair");
            System.out.println("--------------------------------------------");
            System.out.print("  Escolha uma opção: ");

            String opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1" -> {
                    System.out.println("\n--- CADASTRAR NOVO USUÁRIO ---");
                    System.out.print("Nome completo: ");
                    String nome = scanner.nextLine().trim();
                    if (nome.isBlank()) {
                        System.out.println("O nome não pode ser vazio.");
                        break;
                    }
                    String email;
                    while (true) {
                        System.out.print("E-mail: ");
                        email = scanner.nextLine().trim();
                        if (email.isBlank()) {
                            System.out.println("O e-mail não pode ser vazio. Tente novamente.");
                        } else if (!service.emailValido(email)) {
                            System.out.println("E-mail inválido. Use o formato: exemplo@dominio.com. Tente novamente.");
                        } else {
                            break;
                        }
                    }
                    System.out.print("\nConfirmar cadastro de \"" + nome + "\"? (s/n): ");
                    if (scanner.nextLine().trim().equalsIgnoreCase("s")) {
                        service.criarUsuario(nome, email);
                    } else {
                        System.out.println("Cadastro cancelado.");
                    }
                }
                case "2" -> {
                    System.out.println("\n--- LISTA DE USUÁRIOS ---");
                    service.listarUsuarios();
                }
                case "3" -> {
                    System.out.println("\n--- BUSCAR USUÁRIO ---");
                    System.out.print("Digite o ID do usuário: ");
                    service.buscarUsuario(Long.parseLong(scanner.nextLine().trim()));
                }
                case "4" -> {
                    System.out.println("\n--- ATUALIZAR USUÁRIO ---");
                    System.out.print("Digite o ID do usuário a atualizar: ");
                    Long idAtualizar = Long.parseLong(scanner.nextLine().trim());
                    service.buscarUsuario(idAtualizar);
                    System.out.print("Novo nome completo: ");
                    String novoNome = scanner.nextLine().trim();
                    if (novoNome.isBlank()) {
                        System.out.println("O nome não pode ser vazio.");
                        break;
                    }
                    String novoEmail;
                    while (true) {
                        System.out.print("Novo e-mail: ");
                        novoEmail = scanner.nextLine().trim();
                        if (novoEmail.isBlank()) {
                            System.out.println("O e-mail não pode ser vazio. Tente novamente.");
                        } else if (!service.emailValido(novoEmail)) {
                            System.out.println("E-mail inválido. Use o formato: exemplo@dominio.com. Tente novamente.");
                        } else {
                            break;
                        }
                    }
                    System.out.print("\nConfirmar atualização do usuário ID " + idAtualizar + "? (s/n): ");
                    if (scanner.nextLine().trim().equalsIgnoreCase("s")) {
                        service.atualizarUsuario(idAtualizar, novoNome, novoEmail);
                    } else {
                        System.out.println("Atualização cancelada.");
                    }
                }
                case "5" -> {
                    System.out.println("\n--- REMOVER USUÁRIO ---");
                    System.out.print("Digite o ID do usuário a remover: ");
                    Long idRemover = Long.parseLong(scanner.nextLine().trim());
                    service.buscarUsuario(idRemover);
                    System.out.print("\nTem certeza que deseja remover este usuário? (s/n): ");
                    if (scanner.nextLine().trim().equalsIgnoreCase("s")) {
                        service.removerUsuario(idRemover);
                    } else {
                        System.out.println("Remoção cancelada.");
                    }
                }
                case "0" -> {
                    System.out.println("\nSaindo do sistema... Até logo!");
                    rodando = false;
                }
                default -> System.out.println("\nOpção inválida. Tente novamente.");
            }
        }
        scanner.close();
    }
}