package soluciones;

import utils.Arista;
import utils.Grafo;
import utils.Lector;
import utils.Vertice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class TiemposTransporte {

    public static HashMap<Integer,Integer> tiemposTransporte(Grafo grafo, int verticeInicial){
        HashMap<Integer,Integer> pesos = new HashMap<>();
        List<Arista> aristasVistas = new ArrayList<>();
        for (Vertice vertice : grafo.getVerticesLista()){
            //Inicializar en infinito la distancia a todos los vertices
            pesos.put(vertice.getNombre(),Integer.MAX_VALUE);
        }
        // Costo de llegar al inicial -> 0
        Vertice verticeActual = grafo.getVertice(verticeInicial);
        pesos.put(verticeActual.getNombre(),0);
        List<Arista> pesosLlegar = new ArrayList<>();
        // Los costos de los vertices adyacentes al inicial
        for (Arista arista : verticeActual.getAdyacentes()){
            Arista aristaPesoOrigenDestino = new Arista(verticeActual,arista.getDestino(),arista.getPeso());
            pesosLlegar.add(aristaPesoOrigenDestino);
            aristasVistas.add(aristaPesoOrigenDestino);
            pesos.put(arista.getDestino().getNombre(),arista.getPeso());
        }

        while (!pesosLlegar.isEmpty()){
            Arista aristaMinima = minimo(pesosLlegar);
            for (Arista adyacenteDestino : aristaMinima.getDestino().getAdyacentes()){
                pesosLlegar.add(new Arista(aristaMinima.getDestino(), adyacenteDestino.getDestino(), adyacenteDestino.getPeso()+pesos.get(aristaMinima.getDestino().getNombre())));
            }
            if (aristaMinima.getPeso()<pesos.get(aristaMinima.getDestino().getNombre())){
                pesos.put(aristaMinima.getDestino().getNombre(),aristaMinima.getPeso());
            }
            if (!existe(pesosLlegar,aristaMinima.getOrigen().getNombre(),aristaMinima.getDestino().getNombre())){
                aristasVistas.add(aristaMinima);
                continue;
            }
            aristasVistas.remove(aristaMinima);
        }
        return pesos;
    }
    public static Arista minimo(List<Arista> pesos ){
        Arista aristaMinima = null;
        for (Arista arista :pesos){
            if (aristaMinima==null || arista.getPeso()<aristaMinima.getPeso()){
                aristaMinima=arista;
            }
        }
        return aristaMinima;
    }
    public static boolean existe(List<Arista> aristas, int verticeOrigen, int verticeDestino){
        for (Arista arista: aristas){
            if (arista.getOrigen().getNombre()==verticeOrigen && arista.getDestino().getNombre()==verticeDestino){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Grafo grafo = Lector.lectorConPesos(true);
        int verticeInicial = 0;
        HashMap<Integer,Integer> dijkstra = tiemposTransporte(grafo,verticeInicial);
        for (Integer vertice : dijkstra.keySet()) {
            System.out.println(verticeInicial + "->" + vertice + "= " + dijkstra.get(vertice));
        }
    }
}
