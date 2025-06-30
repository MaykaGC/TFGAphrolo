package Modelo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {

    private static final String NOMBRE_ARCHIVO = "C:\\Users\\chica\\OneDrive\\Desktop\\TFGAPHROLO\\Aphrolo\\src\\Modelo\\Log_texto";
    private static final DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void logger(String mensaje) {

        try (PrintWriter out = new PrintWriter(new FileWriter(NOMBRE_ARCHIVO, true))) {
            String fecha = LocalDateTime.now().format(formatoFecha);
            out.println(fecha + " - " + mensaje);
        } catch (IOException e) {
            System.err.println("ERROR al escribir en el log: " + e.getMessage());
        }
    }
}
