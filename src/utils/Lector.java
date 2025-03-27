package utils;

import java.util.Scanner;

public class Lector {
    public static Grafo lectorConPesos(boolean dirigido){
        Scanner sc = new Scanner(System.in);
        Grafo grafo = new Grafo(dirigido);
        int numeroVertices = sc.nextInt();
        int numeroAristas = sc.nextInt();
        for (int i=0; i<numeroAristas;i++){
            int verticeOrigenNombre = sc.nextInt();
            Vertice verticeOrigen = grafo.getVertice(verticeOrigenNombre);
            if (verticeOrigen==null){
                verticeOrigen=grafo.addVertice(verticeOrigenNombre);
            }
            int verticeDestinoNombre = sc.nextInt();
            Vertice verticeDestino = grafo.getVertice(verticeDestinoNombre);
            if (verticeDestino==null){
                verticeDestino=grafo.addVertice(verticeDestinoNombre);
            }
            int peso = sc.nextInt();
            grafo.addArista(verticeOrigen.getNombre(),verticeDestino.getNombre(),peso);
        }
        return grafo;
    }

    public static Grafo lectorSinPesos(boolean dirigido){
        Scanner sc = new Scanner(System.in);
        Grafo grafo = new Grafo(dirigido);
        int numeroVertices = sc.nextInt();
        int numeroAristas = sc.nextInt();
        for (int i=0; i<numeroAristas;i++){
            int verticeOrigenNombre = sc.nextInt();
            Vertice verticeOrigen = grafo.getVertice(verticeOrigenNombre);
            if (verticeOrigen==null){
                verticeOrigen=grafo.addVertice(verticeOrigenNombre);
            }
            int verticeDestinoNombre = sc.nextInt();
            Vertice verticeDestino = grafo.getVertice(verticeDestinoNombre);
            if (verticeDestino==null){
                verticeDestino=grafo.addVertice(verticeDestinoNombre);
            }
            grafo.addArista(verticeOrigen.getNombre(),verticeDestino.getNombre(),0);
        }
        return grafo;
    }
}
