import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Biblioteca {
    private List<Livro> acervo = new ArrayList<>();

    public static final int ANO_PUBLICACAO_MINIMO = 1676;

    public Livro adicionar(Livro livro) throws Exception {
        if (livro == null)
            throw new Exception("Livro não pode ser nulo");

        if (livro.getTitulo() == null)
            throw new Exception("Título não pode ser em branco");
        livro.setTitulo(livro.getTitulo().trim());
        if (livro.getTitulo().isEmpty())
            throw new Exception("Título não pode ser em branco");

        if (livro.getAutor() == null)
            throw new Exception("Autor não pode ser em branco");
        livro.setAutor(livro.getAutor().trim());
        if (livro.getAutor().isEmpty())
            throw new Exception("Autor não pode ser em branco");

        int anoAtual = LocalDate.now().getYear();
        if (livro.getAnoPublicacao() < 1676 || livro.getAnoPublicacao() > anoAtual)
            throw new Exception("Ano de publicação deve estar entre 1676 e o ano atual");

        if (livro.getNumeroPaginas() <= 0)
            throw new Exception("Número de páginas deve ser maior que zero");

        for (Livro existente : acervo) {
            if (existente.getTitulo().equalsIgnoreCase(livro.getTitulo())
                    && existente.getAutor().equalsIgnoreCase(livro.getAutor())
                    && existente.getAnoPublicacao() == livro.getAnoPublicacao()) {
                throw new Exception("Já existe um livro com este título, autor e ano de publicação.");
            }
        }

        acervo.add(livro);
        return livro;
    }

    public List<Livro> pesquisar() {
        return new ArrayList<>(acervo);
    }

    public List<Livro> pesquisar(String termo, boolean porAutor) {
        List<Livro> encontrados = new ArrayList<>();
        for (Livro livro : acervo) {
            if (porAutor) {
                if (livro.getAutor().toLowerCase().contains(termo.toLowerCase())) {
                    encontrados.add(livro);
                }
            } else {
                if (livro.getTitulo().toLowerCase().contains(termo.toLowerCase())) {
                    encontrados.add(livro);
                }
            }
        }
        return encontrados;
    }

    public Livro getPrimeiroLivro() throws Exception {
    if (acervo.isEmpty()) {
        throw new Exception("Acervo vazio. Nenhum livro cadastrado.");
        }
        return acervo.get(0);
    }

    public Livro getUltimoLivro() throws Exception {
    if (acervo.isEmpty()) {
        throw new Exception("Acervo vazio. Nenhum livro cadastrado.");
        }
        return acervo.get(acervo.size() - 1);
    }
    public Livro remover(int indice) throws Exception {
        if (indice < 0 || indice >= acervo.size()) {
            throw new Exception("Índice inválido. Informe um número entre 1 e " + acervo.size());
        }
        return acervo.remove(indice);
    }

    public Livro atualizar(int indice, Livro novosDados) throws Exception {
        if (indice < 0 || indice >= acervo.size()) {
            throw new Exception("Índice inválido. Informe um número entre 1 e " + acervo.size());
        }
        if (novosDados == null) {
            throw new Exception("Dados do livro não podem ser nulos");
        }

        if (novosDados.getTitulo() == null || novosDados.getTitulo().trim().isEmpty())
            throw new Exception("Título não pode ser em branco");
        if (novosDados.getAutor() == null || novosDados.getAutor().trim().isEmpty())
            throw new Exception("Autor não pode ser em branco");

        int anoAtual = LocalDate.now().getYear();
        if (novosDados.getAnoPublicacao() < 1900 || novosDados.getAnoPublicacao() > anoAtual)
            throw new Exception("Ano de publicação inválido");
        if (novosDados.getNumeroPaginas() <= 0)
            throw new Exception("Número de páginas deve ser maior que zero");

        acervo.set(indice, novosDados);
        return novosDados;
    }

    public List<Livro> pesquisarPorIntervaloAno(int anoInicial, int anoFinal) throws Exception {
        if (anoInicial > anoFinal) {
            throw new Exception("Ano inicial não pode ser maior que o ano final.");
        }

        List<Livro> livrosEncontrados = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getAnoPublicacao() >= anoInicial && livro.getAnoPublicacao() <= anoFinal) {
                livrosEncontrados.add(livro);
            }
        }
        return livrosEncontrados;
    }
    
    public Livro getLivroMaisAntigo() throws Exception {
        if (acervo.isEmpty()) {
            throw new Exception("Acervo vazio. Nenhum livro cadastrado.");
        }
        return acervo.stream()
                 .min(Comparator.comparingInt(Livro::getAnoPublicacao))
                 .orElse(null);
    }

    public Livro getLivroMaisNovo() throws Exception {
        if (acervo.isEmpty()) {
            throw new Exception("Acervo vazio. Nenhum livro cadastrado.");
        }
            return acervo.stream()
                 .max(Comparator.comparingInt(Livro::getAnoPublicacao))
                 .orElse(null);
    }
    
}