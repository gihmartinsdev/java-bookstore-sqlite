# BookStore Enterprise — Aula 29

Projeto organizado conforme o gabarito oficial apresentado na atividade.

## Estrutura
- `src/main/java/br/com/bookstore/factory/ConexaoFactory.java`
- `src/main/java/br/com/bookstore/model/Livro.java`
- `src/main/java/br/com/bookstore/dao/LivroDAO.java`
- `src/main/java/br/com/bookstore/dao/LivroDAOSqlite.java`
- `src/main/java/br/com/bookstore/Main.java`
- `src/main/resources/schema.sql`

## Executar
Requisitos: JDK 17+ e Maven.

No terminal, na pasta do projeto:
```bash
mvn clean compile
mvn exec:java
```

O SQLite JDBC é baixado pelo Maven. O banco `libraria.db` é criado na pasta raiz do projeto.

Os três livros do roteiro são cadastrados a cada execução. Para não duplicar os registros, o `Main` limpa a tabela antes de iniciar a demonstração.
