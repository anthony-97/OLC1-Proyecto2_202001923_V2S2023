package Structures.Instructions;

import java.util.LinkedList;

public class Mientras implements Instruccion{
    
    private final Operacion condicion;
    private final LinkedList<Instruccion> listaInstrucciones;
    
    public Mientras(Operacion a, LinkedList<Instruccion> b) {
        this.condicion = a;
        this.listaInstrucciones = b;
    }
    
    @Override
    public String interpretar() {
        while((boolean) this.condicion.interpretar()) {
            for(Instruccion ins:listaInstrucciones) {
                ins.interpretar();
            }
        }
        return "Se interpreto un mientras";
    }   
}