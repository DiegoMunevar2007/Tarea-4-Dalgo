import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CentroGrafo {

    public static List<Vertice> centroGrafo (Grafo grafo){
        int[][] dpResultado = floydWarshall(grafo);
        int excentricidadMinima = Integer.MAX_VALUE;
        List<Vertice> centroGrafo = new ArrayList<>();
        for (int i=0;i<dpResultado.length;i++) {
            int excentricidadVertice = maximo(dpResultado[i], grafo.getVertices().size());
            //Si tiene conexion con todos los vertices se considera
            if (excentricidadVertice != Integer.MAX_VALUE) {
                if (excentricidadVertice < excentricidadMinima) {
                    excentricidadMinima = excentricidadVertice;
                    centroGrafo = new ArrayList<>();
                    centroGrafo.add(grafo.getVertice(i));
                } else if (excentricidadVertice == excentricidadMinima) {
                    centroGrafo.add(grafo.getVertice(i));
                }
            }
        }
        return centroGrafo;
    }

    public static int maximo(int[] arreglo,int cantidadVertices){
        int maximo = Integer.MIN_VALUE;
        int cantidadVerticesRecorridos=0;
        for (int i : arreglo) {
            if (i != Integer.MAX_VALUE) {
                maximo = Math.max(maximo, i);
                cantidadVerticesRecorridos++;
            }
        }
        if (cantidadVertices==cantidadVerticesRecorridos){
            return maximo;
        }
        return Integer.MAX_VALUE;
    }

    public static int[][] floydWarshall(Grafo grafo) {
        int[][] dp = new int[grafo.getVertices().size()][grafo.getVertices().size()];
        // Caso base: Iniciar la matriz dp sin haber visitado ningun otro vertice.
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            //El costo de llegar a mi mismo es 0
            dp[i][i] = 0;
        }
        for (Vertice verticeOrigen : grafo.getVerticesLista()) {
            //Inicializar todo en infinito
            int verticeOrigenNombre = verticeOrigen.getNombre();
            //Obtener los adyacentes del vertice que se esta viendo actualmente
            List<Arista> adyacentes = verticeOrigen.getAdyacentes();
            for (Arista aristaAdyacente: adyacentes) {
                int verticeDestinoNombre = aristaAdyacente.getDestino().getNombre();
                if (!grafo.isDirigido()) {
                    dp[verticeDestinoNombre][verticeOrigenNombre] = aristaAdyacente.getPeso();
                }
                dp[verticeOrigenNombre][verticeDestinoNombre] = aristaAdyacente.getPeso();
            }
        }
        // Caso recursivo: Se usan los distintos vertices como "puentes"
        for (int verticePuente = 0; verticePuente <dp.length; verticePuente++){
            for (int verticeOrigen=0;verticeOrigen< dp.length;verticeOrigen++){
                for (int verticeDestino=0; verticeDestino<dp.length;verticeDestino++){
                    if (dp[verticeOrigen][verticePuente]!=Integer.MAX_VALUE && dp[verticePuente][verticeDestino]!=Integer.MAX_VALUE) {
                        dp[verticeOrigen][verticeDestino] = Math.min(dp[verticeOrigen][verticeDestino], dp[verticeOrigen][verticePuente] + dp[verticePuente][verticeDestino]);
                    }
                }
            }
        }
        return dp;
    }


    public static void main(String[] args) {
        Grafo grafo = Lector.lectorConPesos(true);
        List<Vertice> centro = centroGrafo(grafo);
        for (Vertice vertice:centro){
            System.out.println(vertice);
        }
    }
}
