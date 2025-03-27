package utils;

import java.util.ArrayList;
import java.util.List;

public class Vertice {

    private List<Arista> adyacentes;
    private int nombre;

    public Vertice(int nombre){
        this.nombre = nombre;
        adyacentes = new ArrayList<>();
    }
    public void addArista(Arista a){
        adyacentes.add(a);
    }
    public List<Arista> getAdyacentes() {
        return adyacentes;
    }
    public int getNombre() {
        return nombre;
    }
    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString(){
        return Integer.toString(nombre);
    }

}
