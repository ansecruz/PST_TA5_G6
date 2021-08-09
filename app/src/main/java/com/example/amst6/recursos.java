package com.example.amst6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class recursos {

    public recursos() {
    }

    public ArrayList<String> leerArchivo() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        ArrayList<String> libros = new ArrayList<String>();
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File("./libros.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while ((linea = br.readLine()) != null) {
                libros.add(linea);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return libros;
    }

    public String imagen(ArrayList<String> libros, String nombre) {
        String link = "";
        for (String libro : libros) {
            String[] a = libro.split(";");
            if (nombre.equals(a[0])) {
                link = a[-1];
            }
        }
        return link;
    }

    public String genero(ArrayList<String> libros, String nombre) {
        String genero = "";
        for (String libro : libros) {
            String[] a = libro.split(";");
            if (nombre.equals(a[0])) {
                genero = a[2];
            }
        }
        return genero;
    }

    public String descripcion(ArrayList<String> libros, String nombre) {
        String descripcion = "";
        for (String libro : libros) {
            String[] a = libro.split(";");
            if (nombre.equals(a[0])) {
                descripcion = a[1];
            }
        }
        return descripcion;
    }
}
