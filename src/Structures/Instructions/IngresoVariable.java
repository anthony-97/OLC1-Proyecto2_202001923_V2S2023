package Structures.Instructions;

import java.util.LinkedList;
import Utils.Analizador;
import Structures.NodoSim;

public class IngresoVariable implements Instruccion {
    private String tipoDato;
    final String id;
    private Operacion expresion;
    private LinkedList<Instruccion> lNombres; //Lista de ids si es que vienen varios ids

    //Ingreso Variable(tD:a, id:b, expr:c) 
    public IngresoVariable(String a, String b, Operacion c) {
        this.tipoDato = a;
        this.id = b;
        this.expresion = c;
    }
    
    public IngresoVariable(String a, String b, Operacion c, LinkedList<Instruccion> d) {
        this.tipoDato = a;
        this.id = b;
        this.expresion = c;
        this.lNombres = d;
    }
    
    public void asignarExpresion(Operacion expre) {
        this.expresion = expre;
    }
    
    public void asignarTipoDato(String tDato) {
        this.tipoDato = tDato;
    }
    
    public Object asignarValorDefecto(String tipo) {
        if(tipo.equals("entero")) {
            return 0;
        } else if(tipo.equals("doble")) {
            return 0.0;
        } else if(tipo.equals("binario")) {
            return true;
        } else if(tipo.equals("cadena")) {
            return "";
        } else if(tipo.equals("caracter")) {
            return '\u0000';
        }
        return "Asignacion fallida";
    } 
    
    @Override
    public String traducir() {
        String traduccionPython = "";
        if(lNombres == null) { //Cuando solo viene un id
            Object valor; //Valor del simbolo
            if(expresion == null) { //No viene un valor a asignar, asignar el default;
                valor = asignarValorDefecto(tipoDato.toLowerCase());
            } else {
                valor = expresion.traducir();
            }
            NodoSim simbolo = new NodoSim(id,tipoDato,"global",valor); //Se crea el simbolo
            Analizador.anadirSimbolo(simbolo); //Anade el simbolo a la tabla de simbolos
            System.out.println("IngresoVariable -> " + tipoDato + " " + id + " = " + valor.toString());
        } else { //Creacion de varias variables
            for(Instruccion n: lNombres) {
                //Se deben crear las variables con el valor de la expresion
                ((IngresoVariable) n).asignarExpresion(expresion); //Se obtiene la expresion del ingreso y se le asigna al IngresoVariable que sera traducido
                ((IngresoVariable) n).asignarTipoDato(tipoDato); //Se le asigna el tipo de dato al IngrewsoVariable que sera traducido
                n.traducir();
            }
        }
        return traduccionPython;
    }
}