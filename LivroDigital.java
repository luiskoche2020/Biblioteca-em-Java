public final class LivroDigital extends Livro {
    private String extensao;
    private double tamanhoMB;

    public LivroDigital(String titulo, String autor, int anoPublicacao, int numeroPaginas,
                        String extensao, double tamanhoMB) {
        super(titulo, autor, anoPublicacao, numeroPaginas);
        this.extensao = extensao;
        this.tamanhoMB = tamanhoMB;
    }

    public String getExtensao() {
        return extensao;
    }

    public void setExtensao(String extensao) {
        this.extensao = extensao;
    }

    public double getTamanhoMB() {
        return tamanhoMB;
    }

    public void setTamanhoMB(double tamanhoMB) {
        this.tamanhoMB = tamanhoMB;
    }

    @Override
    public String getFormato() {
        return "Digital";
    }

    @Override
    public String toString() {
        return String.format("Livro Digital: %s, Autor: %s, Ano: %d, Páginas: %d, Extensão: %s, Tamanho: %.2f MB",
                getTitulo(), getAutor(), getAnoPublicacao(), getNumeroPaginas(), extensao, tamanhoMB);
    }
}
