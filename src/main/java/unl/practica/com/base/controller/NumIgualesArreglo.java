package unl.practica.com.base.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class NumIgualesArreglo {
    private int[] numeros;
    private int[] numIguales;

    public void cargarNumerosDesdeArchivo(String rutaArchivo) {
      try {
         long inicio = System.nanoTime();
         BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
         this.numeros = new int[10];
         int count = 0;

         String linea;
         int n;
         while((linea = br.readLine()) != null) {
            try {
               n = Integer.parseInt(linea.trim());
               if (count == this.numeros.length) {
                  this.numeros = this.expandirArreglo(this.numeros);
               }

               this.numeros[count++] = n;
            } catch (NumberFormatException var11) {
               System.out.println("Línea inválida (no es número): " + linea);
            }
         }

         br.close();
         this.numeros = this.redimensionarArreglo(this.numeros, count);
         System.out.println("Números cargados en el arreglo:");
         int[] var10;
         int var9 = (var10 = this.numeros).length;

         for(int var8 = 0; var8 < var9; ++var8) {
            n = var10[var8];
            System.out.println(n);
         }

         this.IdentificarNumerosIguales();
         long fin = System.nanoTime();
         this.mostrarTiempoDeEjecucion(inicio, fin);
      } catch (IOException var12) {
         System.out.println("Error al leer el archivo: " + var12.getMessage());
      }

   }
    public void IdentificarNumerosIguales() {
        int contador = 0;
        this.numIguales = new int[this.numeros.length];
        for (int i = 0; i < this.numeros.length; i++) {
            for (int j = i + 1; j < this.numeros.length; j++) {
                if (this.numeros[i] == this.numeros[j]) {
                    this.numIguales[contador++] = this.numeros[i];
                    break;
                }
            }
        }
        this.numIguales = redimensionarArreglo(this.numIguales, contador);
        System.out.println("Números iguales encontrados:");
         for (int i = 0; i < this.numIguales.length; i++) {
               System.out.println(this.numIguales[i]);
         }
         System.out.println("Cantidad de números iguales encontrados: " + this.numIguales.length);
         
      }

   private int[] expandirArreglo(int[] arreglo) {
    int nuevoTamaño = arreglo.length * 2;
    int[] nuevoArreglo = new int[nuevoTamaño];
    System.arraycopy(arreglo, 0, nuevoArreglo, 0, arreglo.length);
    return nuevoArreglo;
    }

    private int[] redimensionarArreglo(int[] arreglo, int nuevoTamaño) {
        int[] nuevoArreglo = new int[nuevoTamaño];
        System.arraycopy(arreglo, 0, nuevoArreglo, 0, nuevoTamaño);
        return nuevoArreglo;
    }

    private void mostrarTiempoDeEjecucion(long inicio, long fin) {
        long tiempoTotal = fin - inicio;
        System.out.println("\nTiempo de ejecución del programa:");
        System.out.println("" + tiempoTotal + " nanosegundos");
        System.out.println(tiempoTotal / 1000000L + " milisegundos");
     }

   /*  public static void main(String[] args) {
        NumIgualesArreglo numIgualesArreglo = new NumIgualesArreglo();
        numIgualesArreglo.cargarNumerosDesdeArchivo("src/main/java/unl/practica/com/base/data.txt");
    }
   */ 
}
