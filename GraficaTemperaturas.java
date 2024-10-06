import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GraficaTemperaturas {
    public static void main(String[] args) {
        crearArchivoSiNoExiste();
        llenarArchivoConDatosDePrueba();
        int[] temperaturas = new int[24];

        try (Scanner scanner = new Scanner(new File("data.dat"))) {
            for (int i = 0; i < 24; i++) {
                if (scanner.hasNextInt()) {
                    temperaturas[i] = scanner.nextInt();
                } else {
                    System.out.println("Error: El archivo no tiene suficientes datos.");
                    return;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: No se pudo encontrar el archivo data.dat.");
            return;
        }

        System.out.println("Temperaturas para 24 horas:");
        System.out.println("  -30   0  30  60  90  120");

        for (int i = 0; i < 24; i++) {
            int temperatura = temperaturas[i];
            int numAsteriscos = Math.round(temperatura / 3.0f);

            System.out.printf("%4d |", temperatura);

            for (int j = 0; j < numAsteriscos; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    private static void crearArchivoSiNoExiste() {
        File archivo = new File("data.dat");
        if (!archivo.exists()) {
            try (FileWriter writer = new FileWriter(archivo)) {
                // Crear el archivo vacío
                writer.write("");
                System.out.println("Archivo data.dat creado.");
            } catch (IOException e) {
                System.out.println("Error: No se pudo crear el archivo data.dat.");
            }
        }
    }

    private static void llenarArchivoConDatosDePrueba() {
        File archivo = new File("data.dat");
        try (FileWriter writer = new FileWriter(archivo)) {
            int[] datosPrueba = { -20, 0, 1, 2, 3, 4, 5, 10, 50, 100, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85 };
            for (int temperatura : datosPrueba) {
                writer.write(temperatura + " ");
            }
            System.out.println("Archivo data.dat llenado con datos de prueba.");
        } catch (IOException e) {
            System.out.println("Error: No se pudo llenar el archivo data.dat con datos de prueba.");
        }
    }
}
