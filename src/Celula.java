public class Celula {
    private int valor;
    private boolean fixo;

    public Celula(int valor, boolean fixo) {
        this.valor = valor;
        this.fixo = fixo;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public boolean isFixo() {
        return fixo;
    }

    @Override
    public String toString() {
        return valor == 0 ? "." : String.valueOf(valor);
    }
}
