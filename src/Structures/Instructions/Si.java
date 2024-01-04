package Structures.Instructions;

import java.util.LinkedList;
import Utils.Analizador;

public class Si implements Instruccion {
    
    private final Operacion condicion; //condicion del si-entonces
    private final LinkedList<Instruccion> listaInstrucciones; //instrucciones si la condicion se cumple
    private LinkedList<Instruccion> listaSinoSis; //Lista de Sis que vienen en forma de sino si (else if)
    private LinkedList<Instruccion> listaInstruccionesSino; //Lista de instrucciones si no se cumple la condicion (else)
    
    //Constructor cuando es solo un si y una lista de instrucciones
    //Si(condicion:a, listaInstrucciones:b)
    public Si(Operacion a, LinkedList<Instruccion> b) {
        this.condicion = a;
        this.listaInstrucciones = b;
    }
    
    //Si(condicion:a, listaInstrucciones:b, listaInstruccionesSino:c)
    //Constructor cuando viene sino
    public Si(Operacion a, LinkedList<Instruccion> b, LinkedList<Instruccion> c) {
        this.condicion = a;
        this.listaInstrucciones = b;
        this.listaInstruccionesSino= c;
    }
    
    //Si(condicion:a, listaInstrucciones:b, listaSinoSis:l, listaInstruccionesSino:c) 
    //Constructor cuando viene si, sino si y sino
    public Si(Operacion a, LinkedList<Instruccion> b, LinkedList<Instruccion> l, LinkedList<Instruccion> c) {
        this.condicion = a;
        this.listaInstrucciones = b;
        this.listaSinoSis = l;
        this.listaInstruccionesSino = c;
    }
    
    @Override
    public String interpretar() {
        if(listaInstrucciones != null) {
            if((boolean) this.condicion.interpretar()) { //Se revisa si la condicion retorna true
                for(Instruccion ins: listaInstrucciones){
                    ins.interpretar(); //Ejecuta las instrucciones
                }
            }
        }
        
        if(listaSinoSis != null) { //Vienen sino si
            for(Instruccion s: listaSinoSis) { //Recorre la lista de sis
                if((boolean) ((Si) s).condicion.interpretar()) { //Si la condicion se cumple, se ejecutan las instrucciones del si
                    for(Instruccion ins:((Si) s).listaInstrucciones) {
                        ins.interpretar();
                    }
                }            
            }
        }
        
        if(listaInstruccionesSino != null) { //Viene el sino (else)
            if(!(boolean) this.condicion.interpretar()) {
                for(Instruccion ins: listaInstruccionesSino) {
                    ins.interpretar();
                }
            }           
        }
        return "Se interpreto un si";
    }

}