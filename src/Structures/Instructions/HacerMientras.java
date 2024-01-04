package Structures.Instructions;

import java.util.LinkedList;

public class HacerMientras implements Instruccion{
    
    private final Operacion condicion;
    private final LinkedList<Instruccion> listaInstrucciones;
    
    public HacerMientras(Operacion a, LinkedList<Instruccion> b) {
        this.condicion = a;
        this.listaInstrucciones = b;
    }
    
    @Override
    public String interpretar() {
        do {
            for(Instruccion ins:listaInstrucciones) {
                ins.interpretar();
            }
        } while((boolean) this.condicion.interpretar());
        return "Se interpreto un hacerMientras";
    }   
}