package Structures.Instructions;
import Utils.Analizador;

public class Imprimir implements Instruccion {
    private final Operacion expresion;
	
    public Imprimir(Operacion a) {
        this.expresion = a;
    }
	
    @Override
    public String interpretar() {
        Analizador.consolaSalida += expresion.interpretar()+"" + "\n";
        return "Se realizo una impresion";
    }
}