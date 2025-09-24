public final class LivroFisico extends Livro {
    private int quantidadeDisponivel;
    private String tamanho;

    public LivroFisico(String titulo, String autor, int anoPublicacao, int numeroPaginas,
                       int quantidadeDisponivel, String tamanho) {
        super(titulo, autor, anoPublicacao, numeroPaginas);
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.tamanho = tamanho;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    @Override
    public String getFormato() {
        return "Físico";
    }

    @Override
    public String toString() {
        return String.format("Livro Físico: %s, Autor: %s, Ano: %d, Páginas: %d, Quantidade: %d, Tamanho: %s",
                getTitulo(), getAutor(), getAnoPublicacao(), getNumeroPaginas(), quantidadeDisponivel, tamanho);
    }
}
