package Structures.Instructions;

public class Operacion implements Instruccion{
    public static enum Tipo_operacion{
        SUMA,//
        RESTA,//
        MULTIPLICACION,//
        DIVISION,//
        NEGATIVO,//
        PARENTESIS,//
        NUMERO,//
        CARACTER,//
        IDENTIFICADOR,//
        V_F,//
        CADENA,//
        VAR,
        MAYOR_QUE,//
        MENOR_QUE,//
        MAYORIGUAL_QUE,//
        MENORIGUAL_QUE,//
        IGUAL,//
        NOIGUAL,//
        OR,//
        AND,//
        NOT,//
        CONCATENACION,
        //Operaciones para el for
        MAY,
        MEN,
        MAYOIG, 
        MENOIG,
        INCR,
        DECR
    }
    
    private final Tipo_operacion tipo;
    private Operacion operadorIzq;
    private Operacion operadorDer;
    
    private Object valor;
    /**
     * Constructor de la clase para operaciones binarias (con dos operadores), estas
     * operaciones son:
     * SUMA, RESTA, MULTIPLICACION, DIVISION, CONCATENACION, MAYOR_QUE, MENOR_QUE
     * @param operadorIzq Operador izquierdo de la operación
     * @param operadorDer Opeardor derecho de la operación
     * @param tipo Tipo de la operación
     */
    public Operacion(Operacion operadorIzq, Operacion operadorDer, Tipo_operacion tipo) {
        this.tipo = tipo;
        this.operadorIzq = operadorIzq;
        this.operadorDer = operadorDer;
    }
    /**
     * Constructor para operaciones unarias (un operador), estas operaciones son:
     * NEGATIVO, Asociacion, NOT
     * @param operadorIzq Único operador de la operación
     * @param tipo Tipo de operación
     */
    public Operacion(Operacion operadorIzq, Tipo_operacion tipo) {
        this.tipo = tipo;
        this.operadorIzq = operadorIzq;
    }
    /**
     * Constructor para operaciones unarias (un operador), cuyo operador es 
     * específicamente una cadena, estas operaciones son:
     * IDENTIFICADOR, CADENA , CARACTER
     * @param a Cadena que representa la operación a realizar
     * @param tipo Tipo de operación
     */
    public Operacion(String a, Tipo_operacion tipo) {
        this.valor=a;
        this.tipo = tipo;
    }

    @Override
    public String traducir() {
    	String traduccionPython = "";
        /* ======== OPERACIONES ARITMETICAS ======== */
        if(tipo== Tipo_operacion.DIVISION) {
        	traduccionPython = operadorIzq.traducir() + "/" + operadorDer.traducir();
            return traduccionPython;
        } else if(tipo== Tipo_operacion.MULTIPLICACION) {
        	traduccionPython = operadorIzq.traducir() + "*" + operadorDer.traducir();
            return traduccionPython;
        } else if(tipo== Tipo_operacion.RESTA) {
        	traduccionPython = operadorIzq.traducir() + "-" + operadorDer.traducir();
        	return traduccionPython;
        } else if(tipo== Tipo_operacion.SUMA) {
        	traduccionPython = operadorIzq.traducir() + "+" + operadorDer.traducir();
        	return traduccionPython;
        } else if(tipo== Tipo_operacion.NEGATIVO) {
        	traduccionPython = "-" + operadorIzq.traducir();
            return traduccionPython;
        } else if(tipo== Tipo_operacion.PARENTESIS) {
        	traduccionPython = "(" + operadorIzq.traducir() + ")";
        	return traduccionPython;
        }
        /* ======== OPERACIONES UNARIOS ======== */
        else if(tipo == Tipo_operacion.NUMERO) {
        	traduccionPython = valor.toString();
        	return traduccionPython;
        } else if(tipo == Tipo_operacion.IDENTIFICADOR) {
        	traduccionPython = valor.toString();
            return traduccionPython;
        } else if(tipo == Tipo_operacion.CADENA) {
        	traduccionPython = valor.toString();
            return traduccionPython;
        } else if(tipo == Tipo_operacion.CARACTER) {
        	traduccionPython = valor.toString();
            return traduccionPython;
        }  else if(tipo == Tipo_operacion.V_F) {
        	if(valor.toString().contains("true")) {
                    traduccionPython = "True";
        	} else {
                    traduccionPython = "False";
        	}
            return traduccionPython;
        }
        /* ======== Tipos de datos ======== */
        else if(tipo == Tipo_operacion.VAR) {//Validar si es float
        	traduccionPython = "var";
            return traduccionPython;
        }
        /* ======== OPERACIONES RELACIONALES ======== */
        else if(tipo== Tipo_operacion.MAYOR_QUE) {
        	traduccionPython = operadorIzq.traducir()+ " > " + operadorDer.traducir();
            return traduccionPython;
        } else if(tipo== Tipo_operacion.MENOR_QUE) {
        	traduccionPython = operadorIzq.traducir()+ " < " + operadorDer.traducir();
            return traduccionPython;
        } else if(tipo== Tipo_operacion.MENORIGUAL_QUE) {
        	traduccionPython = operadorIzq.traducir() + " <= " + operadorDer.traducir();
            return traduccionPython;
        } else if(tipo== Tipo_operacion.MAYORIGUAL_QUE) {
        	traduccionPython = operadorIzq.traducir()+ " >= " + operadorDer.traducir();
            return traduccionPython;
        } else if(tipo== Tipo_operacion.IGUAL) {
        	traduccionPython = operadorIzq.traducir() + " == " + operadorDer.traducir();
            return traduccionPython;
        } else if(tipo== Tipo_operacion.NOIGUAL) {
        	traduccionPython = operadorIzq.traducir() + " != " + operadorDer.traducir();
            return traduccionPython;
        } 
        /* ======== Operaciones comp de un for ==== */
        else if(tipo== Tipo_operacion.MAY) {
            traduccionPython = ">" ;
            return traduccionPython;
        } else if(tipo== Tipo_operacion.MEN) {
            traduccionPython = "<";
            return traduccionPython;
        } else if(tipo== Tipo_operacion.MAYOIG) {
            traduccionPython = ">=";
            return traduccionPython;
        } else if(tipo== Tipo_operacion.MENOIG) {
            traduccionPython = "<=";
            return traduccionPython;
        } else if(tipo== Tipo_operacion.INCR) {
            traduccionPython = "++";
            return traduccionPython;
        } else if(tipo== Tipo_operacion.DECR) {
            traduccionPython = "--";
            return traduccionPython;
        }
        /* ======== OPERACIONES LOGICAS ======== */
        else if(tipo== Tipo_operacion.OR) {
        	traduccionPython = operadorIzq.traducir() + " or " + operadorDer.traducir();
            return traduccionPython;
        } else if(tipo== Tipo_operacion.AND) {
        	traduccionPython = operadorIzq.traducir() + " and " + operadorDer.traducir();
            return traduccionPython;
        }else if(tipo== Tipo_operacion.NOT) {
        	traduccionPython = " not  "+ operadorIzq.traducir();
            return traduccionPython;
        } else {
            return traduccionPython;
        }
    }
}