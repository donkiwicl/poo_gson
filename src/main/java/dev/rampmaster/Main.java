package dev.rampmaster;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        // Se define ruta del archivo JSON donde se guardarán los objetos Todo
        Path dataFile = Path.of("data", "todo.json");
        // Se limpia pruebas anteriores
        Files.deleteIfExists(dataFile);

        // Se crea una lista de objetos Todo para guardar en el archivo JSON
        ArrayList<Todo> todos = new ArrayList<>();
        todos.add(new Todo("Learn Java", "Complete the Java course", false));
        todos.add(new Todo("Build a project", "Create a small Java project", false));

        // Se crear carpeta donde se guardará el archivo JSON (si no existe)
        Path carpeta = dataFile.toAbsolutePath().getParent();
        if (carpeta != null) {
            Files.createDirectories(carpeta);   // no falla si ya existe
        }
        // Se crea un escritor para escribir en el archivo JSON
        Writer escritor = Files.newBufferedWriter(dataFile, StandardCharsets.UTF_8);

        // Se crea un objeto Gson con formato de salida bonito (pretty printing)
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(todos, escritor);
        // Se cierra el escritor
        escritor.close();

    }
}