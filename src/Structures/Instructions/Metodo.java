package Structures.Instructions;

import Structures.NodoSim;
import java.util.LinkedList;
import Utils.Analizador;

public class Metodo implements Instruccion {
    private final String id; //Identificador del metodo
    public final LinkedList<Instruccion> listaInstrucciones; //instrucciones del metodo
    
    //Metodo(id:a, listaInstrucciones:b)
    public Metodo(String a, LinkedList<Instruccion> l) {
        this.id = a;
        this.listaInstrucciones = l;
    }

    @Override
    public String interpretar() {  
        NodoSim nM = new NodoSim(this.id, "Metodo", "Void", "global", this);
        Analizador.anadirSimbolo(nM); //Agrega el metodo a la tabla de simbolos
        return "Metodo ejecutado";
    }
}