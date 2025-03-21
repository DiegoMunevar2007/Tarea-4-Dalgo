public class Arista {

    private Vertice destino;
    private int peso;
    private boolean dirigido;

    public Arista(Vertice origen, Vertice destino, int peso, boolean dirigido){
        this.destino= destino;
        this.peso = peso;
        this.dirigido = dirigido;
    }

    public Vertice getDestino() {
        return destino;
    }
    public void setDestino(Vertice destino) {
        this.destino = destino;
    }
    public int getPeso() {
        return peso;
    }
    public void setPeso(int peso) {
        this.peso = peso;
    }
    public boolean isDirigido() {
        return dirigido;
    }
    public void setDirigido(boolean dirigido) {
        this.dirigido = dirigido;
    }
}
