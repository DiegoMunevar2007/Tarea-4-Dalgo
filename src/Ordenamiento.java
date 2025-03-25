import java.util.ArrayList;
import java.util.List;

public class Ordenamiento {
    private static List<Vertice> verticesCiclo = new ArrayList<>();
    /**
     * Metodo que implementa DFS para detectar si existen ciclos en el grafo
     * @param vertice El grafo a analizar
     * @return booleano que indica si existe un ciclo en el grafo
     */
    public static boolean detectarCiclos(Vertice vertice, List<Vertice> verticesVistos, List<Vertice> verticesPorVisitar) {
        verticesPorVisitar.add(vertice); // Se añade a la fila de vertices que se estan procesando
        for (Arista arista : vertice.getAdyacentes()) {
            Vertice verticeDestino = arista.getDestino();
            if (verticesPorVisitar.contains(verticeDestino)) { // Si todavia se esta procesando el vertice y se llega a el de nuevo, existe un ciclo
                verticesCiclo.add(verticeDestino);
                return true;
            }
            if (!verticesVistos.contains(verticeDestino)) { // Si el vertice no se ha visto, inicia el DFS recursivo
                if (detectarCiclos(verticeDestino, verticesVistos, verticesPorVisitar)) { //Si se detectó un ciclo dentro de la recursion, se sale de él.
                    verticesCiclo.add(verticeDestino);
                    return true;
                }
            }
        }
        verticesPorVisitar.remove(vertice); // Se añade a la lista de vertices vistos y se quita de la fila de en proceso.
        verticesVistos.add(vertice);
        return false; // No existe un ciclo
    }
    /**
     * Metodo de entrada para el DFS,
     * donde se busca si el grafo tiene ciclos. Si no los tiene, hace el DFO.
     * @param grafo
     * @return
     */
    public static List<Vertice> ordenTopologico(Grafo grafo) throws Exception{
        List<Vertice> ordenTopologico = new ArrayList<>();
        List<Vertice> verticesVistos = new ArrayList<>();
        //Detectar los ciclos del grafo.
        if (detectarCiclos(grafo.getVerticesLista().get(0),new ArrayList<>(),new ArrayList<>())) {
            throw new Exception("El grafo tiene ciclos");
        }

        for (Vertice vertice : grafo.getVerticesLista()) { //Se itera por todos los vertices del grafo
            if (!verticesVistos.contains(vertice)) { // Si todavia no se ha visto se hace DFS sobre el
                dfo(vertice, verticesVistos, ordenTopologico);
            }
        }
        return ordenTopologico;
    }

    /**
     * Metodo que implementa DFO para obtener el orden topologico del grafo
     * @param vertice
     * @param verticesVistos
     * @param ordenTopologico
     */
    public static void dfo(Vertice vertice, List<Vertice> verticesVistos, List<Vertice> ordenTopologico) {
        verticesVistos.add(vertice);
        for (Arista arista : vertice.getAdyacentes()) {
            Vertice verticeDestino = arista.getDestino();
            if (!verticesVistos.contains(verticeDestino)) {
                dfo(verticeDestino, verticesVistos, ordenTopologico);
            }
        }
        // Agregar el vertice al inicio de la lista cuando ya no tenga mas adyacentes (Una pila casera)
        ordenTopologico.add(0, vertice);
    }

    public static void main(String[] args) {
        Grafo grafo = Lector.lectorSinPesos(true);
        try {
            List<Vertice> ordenTopologico = ordenTopologico(grafo);
            for (Vertice vertice : ordenTopologico) {
                System.out.print(vertice + "->");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Los vertices que pertenecen al ciclo son:");
            for (Vertice verticeCiclo : verticesCiclo){
                System.out.println(verticeCiclo);
            }
        }
    }
}
