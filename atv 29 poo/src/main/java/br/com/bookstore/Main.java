package br.com.bookstore;

import br.com.bookstore.dao.LivroDAO;
import br.com.bookstore.dao.LivroDAOSqlite;
import br.com.bookstore.factory.ConexaoFactory;
import br.com.bookstore.model.Livro;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        limparTabela();

        System.out.println("======================================================");
        System.out.println("       LIVRARIA BOOKSTORE ENTERPRISE - TESTE CANÔNICO");
        System.out.println("======================================================");

        // 1. Inversão de Dependência (DIP)
        LivroDAO dao = new LivroDAOSqlite();

        System.out.println("\n--- 1. Cadastrando livros no SQLite ---");

        Livro l1 = new Livro("Clean Code", "Robert C. Martin", 89.90);
        Livro l2 = new Livro("Design Patterns", "Erich Gamma et al.", 125.50);
        Livro l3 = new Livro("Refactoring", "Martin Fowler", 98.00);

        dao.salvar(l1);
        dao.salvar(l2);
        dao.salvar(l3);

        System.out.println("Livro salvo com sucesso: " + l1);
        System.out.println("Livro salvo com sucesso: " + l2);
        System.out.println("Livro salvo com sucesso: " + l3);

        // 2. Consulta pontual por ID
        System.out.println("\n--- 2. Consulta pontual por ID (ID: 2) ---");
        dao.buscarPorId(2L).ifPresentOrElse(
                livro -> System.out.println("Encontrado: " + livro),
                () -> System.out.println("Livro não localizado!")
        );

        // 3. Consulta por ID inexistente com Optional seguro
        System.out.println("\n--- 3. Consulta por ID Inexistente (ID: 999) ---");
        dao.buscarPorId(999L).ifPresentOrElse(
                livro -> System.out.println("Encontrado: " + livro),
                () -> System.out.println("Livro com ID 999 não encontrado (Optional.empty tratado corretamente).")
        );

        // 4. Listagem completa formatada
        System.out.println("\n--- 4. Listagem Geral do Acervo ---");
        List<Livro> catalogo = dao.listarTodos();
        System.out.printf("%-5s | %-25s | %-22s | %s%n", "ID", "TÍTULO", "AUTOR", "PREÇO");
        System.out.println("--------------------------------------------------------------------------");

        for (Livro livro : catalogo) {
            System.out.printf("%-5d | %-25s | %-22s | R$ %.2f%n",
                    livro.getId(), livro.getTitulo(), livro.getAutor(), livro.getPreco());
        }

        System.out.println("======================================================");
    }

    private static void limparTabela() {
        String sql = "DELETE FROM livros";
        try (Connection conn = ConexaoFactory.obterConexao();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            // Na primeira execução, a tabela ainda pode não existir.
            // O construtor do DAO cria a tabela logo depois.
        }
    }
}
