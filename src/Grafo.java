import java.util.ArrayList;
import java.util.List;

public class Grafo {
    List<Vertice> vertices;

    public Grafo(){
        vertices = new ArrayList<>();
    }
    public void addVertice(Vertice v){
        vertices.add(v);
    }
    public List<Vertice> getVertices() {
        return vertices;
    }
}
