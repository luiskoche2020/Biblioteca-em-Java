import java.util.List;
import java.util.Scanner;

public class Main {

    private static Biblioteca biblioteca = new Biblioteca();
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        String menu = """
        === Sistema Biblioteca ===
        Escolha uma das opções abaixo:
        1 - Adicionar Livro
        2 - Listar Acervo
        3 - Pesquisar Livro
        4 - Remover Livro
        5 - Atualizar Livro
        6 - Pesquisar por Intervalo de Ano
        7 - Mostrar Primeiro e Último Livro Adicionado
        8 - Mostrar Livro Mais Antigo e Mais Novo
        0 - Sair
        """;

        int opcao;
        do {
            System.out.println(menu);
            opcao = Input.scanInt("Opção: ", scan);

            switch (opcao) {
                case 1 -> { adicionarLivro(); pausa(); }
                case 2 -> { listarAcervo(); pausa(); }
                case 3 -> { pesquisarLivro(); pausa(); }
                case 4 -> { removerLivro(); pausa(); }
                case 5 -> { atualizarLivro(); pausa(); }
                case 6 -> { pesquisarPorIntervaloAno(); pausa(); }
                case 7 -> { mostrarPrimeiroEUltimoLivro(); pausa(); }
                case 8 -> { mostrarMaisAntigoENovo(); pausa(); }
                case 0 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void adicionarLivro() {
        String titulo = Input.scanString("Título: ", scan);
        String autor = Input.scanString("Autor: ", scan);
        int ano = Input.scanInt("Ano de publicação: ", scan);
        int paginas = Input.scanInt("Número de páginas: ", scan);

        String formato = Input.scanString("Tipo do livro [F - Físico | D - Digital]: ", scan);

            try {
                Livro livro;

                if (formato.equalsIgnoreCase("F")) {
                    int quantidade = Input.scanInt("Quantidade disponível: ", scan);
                    String tamanho = Input.scanString("Tamanho/Dimensões (ex.: 14x21cm): ", scan);

                    livro = new LivroFisico(titulo, autor, ano, paginas, quantidade, tamanho);
                } else if (formato.equalsIgnoreCase("D")) {
                    String extensao = Input.scanString("Extensão do arquivo (ex.: PDF, EPUB): ", scan);
                    double tamanhoMB = Input.scanDouble("Tamanho do arquivo (MB): ", scan);

                    livro = new LivroDigital(titulo, autor, ano, paginas, extensao, tamanhoMB);
                } else {
                    System.out.println("Formato inválido. Use F ou D.");
                    return;
                }

                biblioteca.adicionar(livro);
                System.out.println("Livro adicionado com sucesso!");
            } catch (Exception e) {
                System.out.println("Erro ao adicionar livro: " + e.getMessage());
        }
    }

    private static void listarAcervo() {
        var acervo = biblioteca.pesquisar();
        imprimirLista(acervo);
    }

    private static void pesquisarLivro() {
        String titulo = Input.scanString("Digite o título que procuras: ", scan);
        String pesquisaAutor = Input.scanString("Deseja pesquisar por autor? (S/N): ", scan);

        List<Livro> livros;
        if (pesquisaAutor.equalsIgnoreCase("S")) {
            String autor = Input.scanString("Digite o nome do autor: ", scan);
                livros = biblioteca.pesquisar(autor, true);
            } else {
        livros = biblioteca.pesquisar(titulo, false);
    }
        imprimirLista(livros);
    }

    private static void pesquisarPorIntervaloAno() {
        int anoInicial = Input.scanInt("Digite o ano inicial: ", scan);
        int anoFinal = Input.scanInt("Digite o ano final: ", scan);

        try {
            var resultados = biblioteca.pesquisarPorIntervaloAno(anoInicial, anoFinal);
            imprimirLista(resultados);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void atualizarLivro() {
        var acervo = biblioteca.pesquisar();

        if (acervo.isEmpty()) {
            System.out.println("Acervo vazio. Nada para atualizar.");
            return;
        }

        imprimirLista(acervo);

        int indiceUsuario = Input.scanInt("Digite o índice do livro para atualizar (1 a " + acervo.size() + "): ", scan);
        int indiceInterno = indiceUsuario - 1;

        if (indiceInterno < 0 || indiceInterno >= acervo.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        Livro atual = acervo.get(indiceInterno);

        String novoTitulo = Input.scanString("Novo título: ", scan);
        String novoAutor = Input.scanString("Novo autor: ", scan);
        int novoAno = Input.scanInt("Novo ano de publicação: ", scan);
        int novasPaginas = Input.scanInt("Novo número de páginas: ", scan);

        try {
            Livro novosDados;

            if (atual instanceof LivroFisico lf) {
                int novaQuantidade = Input.scanInt("Nova quantidade disponível: ", scan);
                String novoTamanho = Input.scanString("Novo tamanho/dimensões: ", scan);

                novosDados = new LivroFisico(novoTitulo, novoAutor, novoAno, novasPaginas, novaQuantidade, novoTamanho);
            } else if (atual instanceof LivroDigital ld) {
                String novaExtensao = Input.scanString("Nova extensão do arquivo: ", scan);
                double novoTamanhoMB = Input.scanDouble("Novo tamanho do arquivo (MB): ", scan);

                novosDados = new LivroDigital(novoTitulo, novoAutor, novoAno, novasPaginas, novaExtensao, novoTamanhoMB);
            } else {

            System.out.println("Tipo de livro desconhecido. Atualização cancelada.");
                return;
            }

            biblioteca.atualizar(indiceInterno, novosDados);
            System.out.println("Livro atualizado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar livro: " + e.getMessage());
        }
    }

    private static void removerLivro() {
        var acervo = biblioteca.pesquisar();

        if (acervo.isEmpty()) {
            System.out.println("Acervo vazio. Nada para remover.");
            return;
        }

        imprimirLista(acervo);

        int indiceUsuario = Input.scanInt("Digite o índice do livro para remover (1 a " + acervo.size() + "): ", scan);
        int indiceInterno = indiceUsuario - 1;

        try {
            Livro removido = biblioteca.remover(indiceInterno);
            System.out.println("Livro removido: " + removido);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void imprimirLista(List<Livro> lista) {
        if (lista == null || lista.isEmpty()) {
            System.out.println("Nenhum livro encontrado.");
            return;
        }

        System.out.println("Livros Encontrados:");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println("Livro " + (i + 1) + ": " + lista.get(i));
        }
    }

    private static void pausa() {
        System.out.println("Pressione Enter para continuar");
        scan.nextLine();
    }

    private static void mostrarPrimeiroEUltimoLivro() {
    try {
        Livro primeiro = biblioteca.getPrimeiroLivro();
        Livro ultimo = biblioteca.getUltimoLivro();

        System.out.println("Primeiro livro adicionado:");
        System.out.println(primeiro);

        System.out.println("\nÚltimo livro adicionado:");
        System.out.println(ultimo);
    } catch (Exception e) {
        System.out.println("Erro: " + e.getMessage());
        }
    }
    private static void mostrarMaisAntigoENovo() {
    try {
        Livro maisAntigo = biblioteca.getLivroMaisAntigo();
        Livro maisNovo = biblioteca.getLivroMaisNovo();

        System.out.println("Livro mais antigo:");
        System.out.println(maisAntigo);

        System.out.println(" Livro mais novo:");
        System.out.println(maisNovo);
    } catch (Exception e) {
        System.out.println("Erro: " + e.getMessage());
    }
}
}
