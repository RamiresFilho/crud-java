package application;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import repository.UsuarioRepository;
import service.UsuarioService;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("banco-h2");
        EntityManager em = emf.createEntityManager();

        UsuarioRepository repository = new UsuarioRepository(em);
        UsuarioService service = new UsuarioService(repository);

        Scanner scanner = new Scanner(System.in);
        boolean rodando = true;

        System.out.println("\n============================================");
        System.out.println("========     GESTÃO DE USUÁRIOS     ========");
        System.out.println("============================================");

        while (rodando) {
            System.out.println("\n1. Criar usuario");
            System.out.println("2. Listar todos");
            System.out.println("3. Buscar por ID");
            System.out.println("4. Atualizar");
            System.out.println("5. Remover");
            System.out.println("0. Sair");
            System.out.print("Opcao: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    service.criarUsuario(nome, email);
                    break;
                case "2":
                    service.listarUsuarios();
                    break;
                case "3":
                    System.out.print("ID: ");
                    service.buscarUsuario(Long.parseLong(scanner.nextLine()));
                    break;
                case "4":
                    System.out.print("ID: ");
                    Long idAtualizar = Long.parseLong(scanner.nextLine());
                    System.out.print("Novo nome: ");
                    String novoNome = scanner.nextLine();
                    System.out.print("Novo email: ");
                    String novoEmail = scanner.nextLine();
                    service.atualizarUsuario(idAtualizar, novoNome, novoEmail);
                    break;
                case "5":
                    System.out.print("ID: ");
                    service.removerUsuario(Long.parseLong(scanner.nextLine()));
                    break;
                case "0":
                    rodando = false;
                    System.out.println("Saindo... Ate logo!");
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        }

        scanner.close();
        em.close();
        emf.close();
    }
}