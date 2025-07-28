package Utility;

import java.io.IOException;
import java.util.Scanner;

public class ToolBox {
    
    static Scanner scanner = new Scanner(System.in);

    /**
     * Muestra un mensaje en la consola y espera a que el usuario presione Enter.
     * @param mensaje El mensaje a mostrar.
     */
    public static String getConsolaString(String mensaje) {
        scanner = new Scanner(System.in);
        System.out.println(mensaje);
        String input = scanner.nextLine();
        if (input.isEmpty()) {
            System.out.println("Entrada vacía. Por favor, ingrese un valor.");
            return getConsolaString(mensaje);
        }
        return input;
    }
    
    /**
     * Muestra un mensaje en la consola y espera a que el usuario ingrese un número entero positivo dentro de un rango.
     * @param mensaje El mensaje a mostrar.
     * @param valorMinimo El valor mínimo permitido (inclusive).
     * @param valorMaximo El valor máximo permitido (inclusive).
     * @return Un número entero positivo dentro del rango especificado.
     */
    public static int getConsolaEnteroPositivo(String mensaje, Integer valorMinimo, Integer valorMaximo) {
        int numero;
        do {
            System.out.print(mensaje);
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, ingrese un número entero válido.");
                scanner.next(); // Limpiar el buffer
                System.out.print(mensaje);
            }
            numero = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea pendiente
            
            if (numero < valorMinimo || numero > valorMaximo) {
                System.out.println("El número debe estar entre " + valorMinimo + " y " + valorMaximo + ".");
            }
        } while (numero < valorMinimo || numero > valorMaximo);
         
        return numero;
    }

    /**
     * Muestra un mensaje en la consola y espera a que el usuario ingrese un número entero dentro de un rango específico.
     * @param mensaje El mensaje a mostrar.
     * @param minimo El valor mínimo permitido (inclusive).
     * @param maximo El valor máximo permitido (inclusive).
     * @return Un número entero dentro del rango especificado.
     */
    public static int getNumero(String mensaje, int minimo, int maximo) {
        int valor;
        do {
            System.out.print(mensaje);
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, ingrese un número entero válido.");
                scanner.next(); // Limpiar el buffer
                System.out.print(mensaje);
            }
            valor = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea pendiente
            
            if (valor >= minimo && valor <= maximo) {
                return valor;
            }
            System.out.print(":( ");
        } while (true);
    }

    /**
     * Muestra una barra de carga animada en la consola.
     * @param tiempo El tiempo en milisegundos entre cada actualización de la barra (Normalmente   100 ms).
     */
    public static void loading(int tiempo) {
        int total = 20;

        for (int i = 1; i <= total; i++) {
            System.out.print("\r");

            StringBuilder barra = new StringBuilder("[");
            for (int j = 1; j < i; j++) {
                barra.append("=");
            }

        if (i < total) {
            barra.append((i % 2 == 0) ? ">" : "-");
            for (int j = i + 1; j <= total; j++) {
                barra.append(" ");
            }
        } else {
            barra.append("=");
        }

        barra.append("] ").append(i * 5).append("%");

        System.out.print(barra);

        try {
            Thread.sleep(tiempo); // pausa de 200 ms
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // restablecer el estado de interrupción
            System.err.println("La animación fue interrumpida.");
            break; // salir del bucle si fue interrumpido
        }
    }

    System.out.println(); // salto de línea final
}

    /**
     * Limpia la consola dependiendo del sistema operativo.
     */
    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException ex) {
            // Manejar excepciones si es necesario
            System.err.println("Error al limpiar la consola: " + ex.getMessage());
        }
    }
}
