package Structures.Instructions;

import Structures.NodoSim;
import java.util.LinkedList;
import Utils.Analizador;

public class Llamada implements Instruccion {
    private final String id; //Identificador del metodo
    
    //Llamada(id:a)
    public Llamada(String a) {
        this.id = a;
    }

    @Override
    public String interpretar() {  
        Metodo m = (Metodo) Analizador.obtenerValor(this.id);
        if(m != null) { //Si encuentra el metodo, se ejecutan las instrucciones guardadas en el
            for(Instruccion i: m.listaInstrucciones) {
                i.interpretar(); //Ejecuta las instrucciones del metodo
            }
        } else {
            System.out.println("Error semantico, el metodo no ha sido declarado aun");
        }
        return "Metodo ejecutado";
    }
}