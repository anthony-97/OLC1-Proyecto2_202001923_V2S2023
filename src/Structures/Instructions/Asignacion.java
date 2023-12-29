package Structures.Instructions;

import Utils.Analizador;

public class Asignacion implements Instruccion {
    final String id;
    private Operacion expresion;

    public Asignacion(String a, Operacion b) {
        this.id = a;
        this.expresion = b;
    }
    
    @Override
    public String traducir() {
        String traduccionPython = "";
        Object valor = expresion.traducir(); //Nuevo valor a asignar
        Analizador.asignarValorSimbolo(id, valor); //Busca el simbolo con el id y le asigna el valor
        return traduccionPython;
    }
}