package app;

import service.UsuarioService;
import model.Usuario;
import java.util.Scanner;
import java.util.List;

public class Application {

    private static UsuarioService service = new UsuarioService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao = 0;

        do {
            System.out.println("+-----------------------------+");
            System.out.println("|      GESTÃO DE USUÁRIOS     |");
            System.out.println("+-----------------------------+");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Deletar");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
                continue;
            }

            if (opcao == 1) {
                cadastrar();
            } else if (opcao == 2) {
                listar();
            } else if (opcao == 3) {
                atualizar();
            } else if (opcao == 4) {
                deletar();
            } else if (opcao == 0) {
                System.out.println("Saindo...");
            } else {
                System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private static void cadastrar() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        service.criarUsuario(nome, email);
    }

    private static void listar() {
        List<Usuario> lista = service.listarUsuarios();
        for (Usuario u : lista) {
            System.out.println(u);
        }
    }

    private static void atualizar() {
        listar();
        System.out.print("Digite o ID (Long) para editar: ");
        Long id = Long.parseLong(scanner.nextLine());

        System.out.print("Novo Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Novo Email: ");
        String email = scanner.nextLine();

        service.atualizarUsuario(id, nome, email);
    }

    private static void deletar() {
        listar();
        System.out.print("Digite o ID (Long) para deletar: ");
        Long id = Long.parseLong(scanner.nextLine());

        service.deletarUsuario(id);
    }
}