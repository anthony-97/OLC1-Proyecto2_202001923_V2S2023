package Utils;

import Structures.Arbol;
import Structures.Instructions.Instruccion;
import Structures.Instructions.Metodo;
import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedList;
import Structures.NodoSim;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Analizador {
    
    private LinkedList<Instruccion> AST_arbolSintaxisAbstracta;
    private Arbol arbol;
    public static LinkedList<NodoSim> tablaSimbolos = new LinkedList<NodoSim>(); //LinkedList que sera la tabla de simbolos
    public static String consolaSalida = ""; //Texto que se seteara a la consola de salida
    
    public Analizador() {
    }
    
    public String interpretar(String text) {
        File file = new File("parse.txt");  
        (new Files()).crearArchivo(file, text);
        Analizadores.Sintactico pars;
        try {
            pars=new Analizadores.Sintactico(new Analizadores.Lexico(new FileInputStream(file)));
            pars.parse();        
            AST_arbolSintaxisAbstracta=pars.getAST();
            arbol = pars.getArbol();
        } catch (Exception ex) {
            System.out.println("Error fatal en compilación de entrada.");
            System.out.println("Causa: "+ex);
        }
        String titulo = ejecutarAST(AST_arbolSintaxisAbstracta);
        arbol.graficar(); //Se grafica el ast
        return titulo;
    }
    
    public Arbol getArbol(){
        return this.arbol;
    }
    
    public String ejecutarAST(LinkedList<Instruccion> ast) {
        if(ast==null){
            System.out.println("No es posible ejecutar las instrucciones porque\r\n"
                    + "el árbol no fue cargado de forma adecuada por la existencia\r\n"
                    + "de errores léxicos o sintácticos.");
        }
        
        for(Instruccion ins:ast) {
            //Carga de metodos
            if(ins instanceof Metodo) { //Si es una instruccion Metodo, entonces se carga a la tabla de simbolos
                ins.interpretar();
            }
        }
        //Se ejecuta cada instruccion en el ast, es decir, cada instruccion de 
        //la lista principal de instrucciones.
        for(Instruccion ins:ast){
            //Si existe un error léxico o sintáctico en cierta instrucción esta
            //será inválida y se cargará como null, por lo tanto no deberá ejecutarse
            //es por esto que se hace esta validación.
            if(ins!=null && !(ins instanceof Metodo)) { //Si no son metodos, entonces son llamadas o cualquier otra cosa, se ejecutan
            	ins.interpretar();
            }
        }
        imprimirTablaSimbolos();
        return "\t         -> ...... Java en español - Consola ...... <-       \n";
    }
    
    public static Object obtenerValor(String id) { //Obtiene el valor de la tabla de simbolos segun el id pasado 
        for(NodoSim simbolo: tablaSimbolos) {
            if(simbolo.id.equals(id)) {
                return simbolo.valor;
            }
        }
        return null; //Retorna nulo si no encuentra el simbolo con el id pasado
    }
    
    public static void anadirSimbolo(NodoSim sim) {
        boolean encontrado = false;
        for(NodoSim simbolo: tablaSimbolos) {
            if(simbolo.id.equals(sim.id)) { //Ya existe un simbolo con el id del simbolo que se quiere ingresar
                encontrado = true;
                break;
            }
        }
        if(!encontrado) { //El simbolo no existe, por lo que se puede agregar sin problema
            tablaSimbolos.add(sim);
        } else {
            System.out.println("El simbolo con id " + sim.id + " ya existe en la tabla de simbolos");
        }
    }
    
    public static void asignarValorSimbolo(String id, Object valor) {
        boolean encontrado = false;
        for(NodoSim simbolo: tablaSimbolos) {
            if(simbolo.id.equals(id)) { //Se encuentra al simbolo, se le asignara el valor
                simbolo.valor = valor; //Se le asigna el valor
                encontrado = true;
                break;
            }
        }
        if(!encontrado) {
            System.out.println("El simbolo con id " + id + " no existe en la tabla de simbolos");
        }
    }
    
    public void imprimirTablaSimbolos() {
        for(NodoSim simbolo: tablaSimbolos) {
            System.out.println("id-> "+ simbolo.id + ", tipo -> " + simbolo.tipo + ", valor -> " + simbolo.valor.toString() + "\n");
        }
    }
    
    public static void generarReporteTablaSimbolos() throws IOException {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            String path = "TablaSimbolos.html";
            fichero = new FileWriter(path);
            pw = new PrintWriter(fichero);
            
            //Comenzamos a escribir el html
            pw.println("<html>");
            pw.println("<head><title>Tabla de Simbolos</title></head>");
            pw.println("<body>");
            pw.println("<div align=\"center\">");
            pw.println("<h1>Simbolos</h1>");
            pw.println("<br></br>");
            pw.println("<table border=1>");
            pw.println("<tr>");
            pw.println("<td>ID</td>");
            pw.println("<td>ROL</td>");
            pw.println("<td>TIPO</td>");
            pw.println("<td>VALOR</td>");
            pw.println("</tr>");

            for (NodoSim simbolo : tablaSimbolos) {
                pw.println("<tr>");
                pw.println("<td>" + simbolo.id + "</td>");
                pw.println("<td>" + simbolo.rol + "</td>");
                pw.println("<td>" + simbolo.tipo + "</td>");
                pw.println("<td>" + simbolo.valor.toString() + "</td>");
                pw.println("</tr>");
            }

            pw.println("</table>");
            pw.println("</div");
            pw.println("</body>");
            pw.println("</html>");            
            
        } catch (Exception e) {
        } finally {
            if (fichero != null) {
                fichero.close();
            }
        }
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}