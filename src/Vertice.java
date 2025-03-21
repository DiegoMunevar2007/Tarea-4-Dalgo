import java.util.ArrayList;
import java.util.List;

public class Vertice {

    List<Arista> adyacentes;
    String nombre;

    public Vertice(String nombre){
        this.nombre = nombre;
        adyacentes = new ArrayList<>();
    }
    public void addArista(Arista a){
        adyacentes.add(a);
    }
    public List<Arista> getAdyacentes() {
        return adyacentes;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
