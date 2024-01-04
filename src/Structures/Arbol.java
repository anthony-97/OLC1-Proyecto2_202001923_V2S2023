package Structures;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;

public class Arbol {
    private Node raiz;

    public Arbol() {
    }

    public Arbol(Node raiz) {
        this.raiz = raiz;
    }

    public Node getRaiz() {
        return raiz;
    }

    public void setRaiz(Node raiz) {
        this.raiz = raiz;
    }    
    
    public void graficar(){
        StringBuilder s = new StringBuilder();
        s.append("digraph G{\n");
        s.append("\"node").append(raiz.hashCode()).append("\" [ label = \"").append(raiz.getValor()).append("\",];\n");
        graficar(raiz, s);
        s.append("}");
        graficarArbol(s.toString()); //Lo pasa a dot
        System.out.println(s.toString());
    }
    
    public void graficar(Node raiz, StringBuilder s){        
        LinkedList<Node> hijos = raiz.getHijos();
        if(hijos != null){
            for(Node hijo: hijos){
                s.append("\"node").append(hijo.hashCode()).append("\" [ label = \"").append(hijo.getValor()).append("\",];\n");
                graficar(hijo, s);
                s.append("\"node").append(raiz.hashCode()).append("\" -> \"node").append(hijo.hashCode()).append("\" [ label = \"\",];\n");
            }
        }
    }
    
    public void graficarArbol(String cuerpo) {
        FileWriter fichero = null;
        PrintWriter escritor = null;
        try {
            fichero = new FileWriter("ast.dot");
            escritor = new PrintWriter(fichero);
            escritor.print(cuerpo);
        } catch (Exception e) {
            System.err.println("Error al escribir el archivo ast" );
        } finally {
            try {
                if (fichero != null)
                    fichero.close();
            } catch (Exception e2) {
                System.err.println("Error al cerrar el archivo ast");
            }
        }
        try {
            String dotCommand = "dot ast.dot -Tpng -o ast.png";
            Process process = Runtime.getRuntime().exec(dotCommand);
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Imagen generada correctamente: ast.jpg");
            } else {
                System.err.println("Error al generar la imagen para el archivo ast.dot");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Error al ejecutar el comando dot en Debian.");
        }
    }
}