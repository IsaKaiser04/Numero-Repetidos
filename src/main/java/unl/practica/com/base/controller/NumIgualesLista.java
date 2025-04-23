
package unl.practica.com.base.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import unl.practica.com.base.controller.DataStruct.list.LinkedList;

public class NumIgualesLista {
    private LinkedList<Integer> numeros = new LinkedList<>();
    private LinkedList<Integer> numIguales = new LinkedList<>();

    public void cargarNumerosDesdeArchivo(String rutaArchivo) {
        try {
            long inicio = System.nanoTime();
            BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
            String linea;
    
            System.out.println("Leyendo archivo...");
            while ((linea = br.readLine()) != null) {
                try {
                    int n = Integer.parseInt(linea.trim());
                    numeros.add(n);
                } catch (NumberFormatException e) {
                    System.out.println("Línea inválida (no es número): " + linea);
                }
            }
    
            br.close();
            System.out.println("Total de números leídos: " + numeros.getLenght());
            
            this.identificarNumerosIguales();
    
            long fin = System.nanoTime();
            mostrarTiempoDeEjecucion(inicio, fin);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error general: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void identificarNumerosIguales() {
        System.out.println("Buscando números repetidos...");
        
        // Usamos un mapa para contar ocurrencias
        LinkedList<Integer> elementosUnicos = new LinkedList<>();
        LinkedList<Integer> repetidos = new LinkedList<>();
        
        for (int i = 0; i < numeros.getLenght(); i++) {
            try {
                Integer num = numeros.get(i);
                if (!elementosUnicos.contains(num)) {
                    elementosUnicos.add(num);
                } else if (!repetidos.contains(num)) {
                    repetidos.add(num);
                }
            } catch (Exception e) {
                System.out.println("Error al acceder a la lista: " + e.getMessage());
            }
        }
    
        System.out.println("\nNúmeros repetidos encontrados (" + repetidos.getLenght() + "):");
        for (int i = 0; i < repetidos.getLenght(); i++) {
            try {
                System.out.println(repetidos.get(i));
            } catch (Exception e) {
                System.out.println("Error al acceder a la lista: " + e.getMessage());
            }
        }
        
        this.numIguales = repetidos;
    }

    private void mostrarTiempoDeEjecucion(long inicio, long fin) {
        long tiempoTotal = fin - inicio;
        System.out.println("\nTiempo de ejecución del programa:");
        System.out.println(tiempoTotal + " nanosegundos");
        System.out.println((tiempoTotal / 1_000_000) + " milisegundos");
    }

    public static void main(String[] args) {
        NumIgualesLista lista = new NumIgualesLista();
        lista.cargarNumerosDesdeArchivo("src/main/java/unl/practica/com/base/data.txt");
    }
}