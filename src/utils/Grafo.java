package utils;

import java.util.HashMap;
import java.util.List;

public class Grafo {
    private HashMap<Integer, Vertice> vertices;
    private boolean dirigido;

    public Grafo(boolean dirigido){
        vertices = new HashMap<>();
        this.dirigido=dirigido;
    }
    public Vertice addVertice(int nombre) {
        Vertice v = new Vertice(nombre);
        vertices.put(nombre, v);
        return v;
    }
    public void addArista(int origen, int destino, int peso){
        Vertice verticeOrigen =vertices.get(origen);
        Vertice verticeDestino = vertices.get(destino);
        Arista arista = new Arista(verticeDestino,peso);
        verticeOrigen.getAdyacentes().add(arista);
        if (!dirigido){
            Arista aristaInversa = new Arista(verticeOrigen,peso);
            verticeDestino.getAdyacentes().add(aristaInversa);
        }
    }

    public List<Vertice> getVerticesLista(){
        return vertices.values().stream().toList();
    }

    public HashMap<Integer, Vertice> getVertices() {
        return vertices;
    }
    public Vertice getVertice(int nombre){
        return vertices.get(nombre);
    }

    public void setVertices(HashMap<Integer, Vertice> vertices) {
        this.vertices = vertices;
    }

    public boolean isDirigido() {
        return dirigido;
    }

    public void setDirigido(boolean dirigido) {
        this.dirigido = dirigido;
    }
}
