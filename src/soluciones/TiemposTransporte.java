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

    /**
     * Metodo que implementa el algoritmo de Dijkstra para obtener los tiempos de transporte desde un vertice inicial
     * @param grafo
     * @param verticeInicial
     * @return HashMap con los tiempos de transporte a cada vertice a partir del inicial
     */
    public static HashMap<Integer,Integer> tiemposTransporte(Grafo grafo, int verticeInicial){
        HashMap<Integer,Integer> pesos = new HashMap<>(); // Los pesos de llegar a cada vertice a partir del inicial
        List<Vertice> verticesVistos = new ArrayList<>(); // Los vertices que se han visto
        List<Arista> pesosLlegar = new ArrayList<>(); // Lista para obtener los pendientes de ver

        for (Vertice vertice : grafo.getVerticesLista()){
            //Inicializar en infinito la distancia a todos los vertices
            pesos.put(vertice.getNombre(),Integer.MAX_VALUE);
        }
        // Costo de llegar al inicial -> 0
        Vertice verticeActualInicial = grafo.getVertice(verticeInicial);
        pesos.put(verticeActualInicial.getNombre(),0);

        // Los costos de los vertices adyacentes al inicial
        for (Arista arista : verticeActualInicial.getAdyacentes()){
            Arista aristaPesoOrigenDestino = new Arista(verticeActualInicial,arista.getDestino(),arista.getPeso());
            pesosLlegar.add(aristaPesoOrigenDestino);
            pesos.put(arista.getDestino().getNombre(),arista.getPeso());
        }

        while (!pesosLlegar.isEmpty()){
            // Seleccionar la arista con menor peso
            Arista aristaMinima = minimo(pesosLlegar);
            pesosLlegar.remove(aristaMinima);
            Vertice destinoActual = aristaMinima.getDestino();

            if (verticesVistos.contains(destinoActual)){
                continue;
            }
            verticesVistos.add(destinoActual); // Se añade a la lista de vertices vistos para no volver a verlo

            if (aristaMinima.getPeso()<pesos.get(aristaMinima.getDestino().getNombre())){ // Caso para poder quitar el infinito -> Hay un camino entre el inicial y el destino
                pesos.put(aristaMinima.getDestino().getNombre(),aristaMinima.getPeso());
            }

            for (Arista adyacenteDestino : destinoActual.getAdyacentes()){
                if (adyacenteDestino.getPeso()+pesos.get(destinoActual.getNombre()) < pesos.get(adyacenteDestino.getDestino().getNombre())){
                    pesos.put(adyacenteDestino.getDestino().getNombre(),adyacenteDestino.getPeso()+pesos.get(destinoActual.getNombre()));
                    Arista aristaPesoOrigenDestino = new Arista(destinoActual,adyacenteDestino.getDestino(),adyacenteDestino.getPeso()+pesos.get(destinoActual.getNombre()));
                    pesosLlegar.add(aristaPesoOrigenDestino);
                }
            }
        }
        return pesos;
    }

    /**
     * Metodo que obtiene la arista con menor peso a partir de una lista de aristas pendientes por ver
     * @param pesos
     * @return Arista con menor peso
     */
    public static Arista minimo(List<Arista> pesos ){
        Arista aristaMinima = null;
        for (Arista arista :pesos){
            if (aristaMinima==null || arista.getPeso()<aristaMinima.getPeso()){
                aristaMinima=arista;
            }
        }
        return aristaMinima;
    }

    public static void main(String[] args) {
        Grafo grafo = Lector.lectorConPesos(true);
        System.out.println("Ingrese el vertice inicial");
        Scanner scanner = new Scanner(System.in);
        int verticeInicial = scanner.nextInt();
        HashMap<Integer,Integer> dijkstra = tiemposTransporte(grafo,verticeInicial);
        for (Integer vertice : dijkstra.keySet()) {
            System.out.println(verticeInicial + "->" + vertice + "= " + dijkstra.get(vertice));
        }
    }
}
