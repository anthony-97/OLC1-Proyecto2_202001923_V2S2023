package Utils;

import Structures.Arbol;
import Structures.Instructions.Instruccion;
import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedList;
import Structures.NodoSim;

public class Analizador {
    
    private LinkedList<Instruccion> AST_arbolSintaxisAbstracta;
    private Arbol arbol;
    public static LinkedList<NodoSim> tablaSimbolos = new LinkedList<NodoSim>(); //LinkedList que sera la tabla de simbolos
    
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
        String traduccion = ejecutarAST(AST_arbolSintaxisAbstracta);
        return traduccion;
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
        //Se ejecuta cada instruccion en el ast, es decir, cada instruccion de 
        //la lista principal de instrucciones.
        
        String traduccionPython = "def main():\n";
        for(Instruccion ins:ast){
            //Si existe un error léxico o sintáctico en cierta instrucción esta
            //será inválida y se cargará como null, por lo tanto no deberá ejecutarse
            //es por esto que se hace esta validación.
            if(ins!=null) {
            	traduccionPython += ins.traducir();
            }
        }
        traduccionPython += "\nif __name__ == \"__main__\":\n    main()";
        return traduccionPython;
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
}