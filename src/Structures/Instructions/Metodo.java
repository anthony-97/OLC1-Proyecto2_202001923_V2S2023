package Structures.Instructions;

import Structures.NodoSim;
import java.util.LinkedList;
import Utils.Analizador;

public class Metodo implements Instruccion {
    private final String id; //Identificador del metodo
    private final LinkedList<Instruccion> listaInstrucciones; //instrucciones del metodo
    private final boolean esLlamada;
    
    //Metodo(id:a, listaInstrucciones:b, esLlamada:c)
    public Metodo(String a, LinkedList<Instruccion> l, boolean esLlamada) {
        this.id = a;
        this.listaInstrucciones = l;
        this.esLlamada = esLlamada;
    }

    @Override
    public String interpretar() {  
        if(!this.esLlamada) { //Si no es llamada, se debe anadir el metodo a la tabla de simbolos
            NodoSim nM = new NodoSim(this.id, "Metodo", "Void", "global", this);
            Analizador.anadirSimbolo(nM); //Agrega el metodo a la tabla de simbolos
        } else { //Es llamada entonces, ejecutar la lista de instrucciones
            Metodo m = (Metodo) Analizador.obtenerValor(this.id);
            if(m != null) { //Si encuentra el metodo, se ejecutan las instrucciones guardadas en el
                for(Instruccion i: m.listaInstrucciones) {
                    i.interpretar(); //Ejecuta las instrucciones del metodo
                }
            } else {
                System.out.println("Error semantico, el metodo no ha sido declarado aun");
            }
        }
        return "Metodo ejecutado";
    }
}