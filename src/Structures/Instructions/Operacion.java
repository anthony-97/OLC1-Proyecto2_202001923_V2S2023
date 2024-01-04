package Structures.Instructions;

import Utils.Analizador;

public class Operacion implements Instruccion{
    public static enum Tipo_operacion{
        SUMA,//
        RESTA,//
        MULTIPLICACION,//
        DIVISION,//
        NEGATIVO,//
        POTENCIA,//
        MODULO,//
        PARENTESIS,//
        INCREMENTO,//
        DECREMENTO,//
        NUMERO,//
        DECIMAL,//
        CARACTER,//
        IDENTIFICADOR,//
        BOOLEANO,//
        CADENA,//
        MAYOR_QUE,//
        MENOR_QUE,//
        MAYORIGUAL_QUE,//
        MENORIGUAL_QUE,//
        IGUAL,//
        NOIGUAL,//
        OR,//
        AND,//
        NOT,//
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
     * SUMA, RESTA, MULTIPLICACION, DIVISION, POTENCIA, MODULO, MAYOR_QUE, MENOR_QUE, IGUAL, NOIGUAL, OR, AND, NOT
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
     * NEGATIVO, NOT
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
     * IDENTIFICADOR, CADENA , CARACTER, BOOLEANO
     * @param a Cadena que representa la operación a realizar
     * @param tipo Tipo de operación
     */
    public Operacion(String a, Tipo_operacion tipo) {
        this.valor=a;
        this.tipo = tipo;
    }

    @Override
    public Object interpretar() {
        /* ======== OPERACIONES ARITMETICAS ======== */
        if(tipo== Tipo_operacion.DIVISION) {
            Object primero = operadorIzq.interpretar();
            Object segundo = operadorDer.interpretar();
            if(primero instanceof Integer && segundo instanceof Integer) { //En los dos vendra un entero
                if((int) primero % (int) segundo == 0) {
                    return (int) primero / (int) segundo;
                }
               return (double) ((int) primero) / (int) segundo; //Castea a double y retorna la division
            } else if(primero instanceof Integer && segundo instanceof Double) { //entero double
               return (double) ((int) primero) / (double) segundo; //Castea y retorna la division
            } else if(primero instanceof Double && segundo instanceof Integer) { //double entero
               return (double) ((double) primero) / (int) segundo; //Castea a double y retorna la division
            } else if(primero instanceof Double && segundo instanceof Double) { //En los dos vendra un double
                return  ((double) primero / (double)segundo); //Castea a double y retorna la division
            } else {
                Analizador.consolaSalida += "Error semantico, no se puede hacer la operacion division por restriccion de tipos\n";
                return "errorSemantico";
            }
        } else if(tipo== Tipo_operacion.MULTIPLICACION) {
            Object primero = operadorIzq.interpretar();
            Object segundo = operadorDer.interpretar();
            if(primero instanceof Integer && segundo instanceof Integer) { //En los dos vendra un entero
               return (int) primero * (int)segundo; //Castea a int y retorna la multiplicacion
            } else if(primero instanceof Double && segundo instanceof Double) { //En los dos vendra un double
                return (double) primero * (double)segundo; //Castea a double y retorna la multiplicacion
            } else if(primero instanceof Integer && segundo instanceof Double) { //entero y double
                return (int) primero * (double)segundo; //Castea a double y retorna la multiplicacion
            }  else if(primero instanceof Double && segundo instanceof Integer) { //Double y entero
                return (double) primero * (int)segundo; //Castea a double y retorna la multiplicacion
            } else {
                Analizador.consolaSalida += "Error semantico, no se puede hacer la operacion multiplicacion por restriccion de tipos\n";
                return "errorSemantico";
            }
        } else if(tipo== Tipo_operacion.RESTA) {
            Object primero = operadorIzq.interpretar();
            Object segundo = operadorDer.interpretar();
            if(primero instanceof Integer && segundo instanceof Integer) { //En los dos vendra un entero
               return (int) primero - (int)segundo; //Castea a int y retorna la resta
            } else if(primero instanceof Double && segundo instanceof Integer) { //En los dos vendra un double
                return (double) primero - (double)segundo; //Castea a double y retorna la resta
            }  else if(primero instanceof Integer && segundo instanceof Double) { //entero y double
                return (int) primero - (double)segundo; //Castea y retorna la resta
            }  else if(primero instanceof Double && segundo instanceof Integer) { //Double y entero
                return (double) primero - (int)segundo; //Castea y retorna la resta
            }else {
                Analizador.consolaSalida += "Error semantico, no se puede hacer la operacion resta por restriccion de tipos\n";
                return "errorSemantico";
            }
        } else if(tipo== Tipo_operacion.SUMA) {
            Object primero = operadorIzq.interpretar();
            Object segundo = operadorDer.interpretar();
            if(primero instanceof Integer && segundo instanceof Integer) { //En los dos vendra un entero
               return (int) primero + (int)segundo; //Castea a int y retorna la suma
            } else if(primero instanceof Double && segundo instanceof Double) { //En los dos vendra un double
                return (double) primero + (double)segundo; //Castea a double y retorna la suma
            } else if(primero instanceof Integer && segundo instanceof Double) { //entero y double
                return (int) primero + (double)segundo; //Castea y retorna la suma
            } else if(primero instanceof Double && segundo instanceof Integer) { //Double y entero
                return (double) primero + (int)segundo; //Castea y retorna la suma
            } else if(primero instanceof String || segundo instanceof String) { //En los dos vendra una cadena
                return primero+"" + segundo+""; //Castea a String y retorna la concatenacion de los dos
            } else {
                Analizador.consolaSalida += "Error semantico, no se puede hacer la operacion suma por restriccion de tipos\n";
                return "errorSemantico";
            }
        } else if(tipo== Tipo_operacion.POTENCIA) {
            Object primero = operadorIzq.interpretar();
            Object segundo = operadorDer.interpretar();
            if(primero instanceof Integer) { //En los dos vendra un entero
               return Math.pow((int) primero, (int) segundo); //Castea a int y retorna la potencia
            } else if(primero instanceof Double) { //En los dos vendra un double
                return Math.pow((double) primero,(double)segundo); //Castea a double y retorna la potencia
            } else {
                Analizador.consolaSalida += "Error semantico, no se puede hacer la operacion potencia por restriccion de tipos\n";
                return "errorSemantico";
            }
        } else if(tipo== Tipo_operacion.MODULO) {
            Object primero = operadorIzq.interpretar();
            Object segundo = operadorDer.interpretar();
            if(primero instanceof Integer) { //En los dos vendra un entero
               return (int) primero % (int)segundo; //Castea a int y retorna el modulo
            } else if(primero instanceof Double) { //En los dos vendra un double
                return (double) primero % (double)segundo; //Castea a double y retorna el modulo
            } else {
                Analizador.consolaSalida += "Error semantico, no se puede hacer la operacion modulo por restriccion de tipos\n";
                return "errorSemantico";
            }
        } 
        /* ======== OPERACIONES UNARIOS ======== */
        else if(tipo== Tipo_operacion.NEGATIVO) {
            Object primero = operadorIzq.interpretar();
            if(primero instanceof Integer) { //Viene un entero
               return (int) primero * -1; //Castea a int y retorna el negativo
            } else if(primero instanceof Double) { //Viene un double
                return (double) primero * -1 ; //Castea a double y retorna el negativo
            } else {
                Analizador.consolaSalida += "Error semantico, no se puede hacer la operacion negativo por restriccion de tipos\n";
                return "errorSemantico";
            }
        } else if(tipo== Tipo_operacion.PARENTESIS) {
        	return operadorIzq.interpretar();
        } else if(tipo == Tipo_operacion.INCREMENTO) {
            int obtenido = (int) Analizador.obtenerValor(valor.toString()); //Se obtiene el valor de la variable
            Analizador.asignarValorSimbolo(valor.toString(), obtenido+1); //Se le suma + 1 al valor de la variable
            return (int) Analizador.obtenerValor(valor.toString()); //Se obtiene el valor que corresponde al id en la tabla de simbolos
        } else if(tipo == Tipo_operacion.DECREMENTO) {
            int obtenido = (int) Analizador.obtenerValor(valor.toString()); //Se obtiene el valor de la variable
            Analizador.asignarValorSimbolo(valor.toString(), obtenido-1); //Se le suma + 1 al valor de la variable
            return (int) Analizador.obtenerValor(valor.toString()); //Se obtiene el valor que corresponde al id en la tabla de simbolos
        } else if(tipo == Tipo_operacion.NUMERO) {
            String numeroCad = valor.toString();
            int numero = Integer.parseInt(numeroCad); //La cadena del numero se convierte a int
            return numero;
        } else if(tipo == Tipo_operacion.DECIMAL) {
            String numeroCad = valor.toString();
            double decimal = Double.parseDouble(numeroCad); //La cadena del numero se convierte a double
            return decimal;
        } else if(tipo == Tipo_operacion.IDENTIFICADOR) {
            return Analizador.obtenerValor(valor.toString()); //Se obtiene el valor que corresponde al id en la tabla de simbolos
        } else if(tipo == Tipo_operacion.CADENA) {
            return valor.toString().replace("\"", "");
        } else if(tipo == Tipo_operacion.CARACTER) {
            return valor.toString().charAt(1); //Convierte el string a caracter
        }  else if(tipo == Tipo_operacion.BOOLEANO) {
            if(valor.toString().toLowerCase().contains("true")) {
                return true;
            }
            return false;
        }
        /* ======== OPERACIONES RELACIONALES ======== */
        else if(tipo== Tipo_operacion.MAYOR_QUE) {
            Object primero = operadorIzq.interpretar();
            Object segundo = operadorDer.interpretar();
            if(primero instanceof Integer) { //En los dos vendra un entero
               return (int) primero > (int)segundo; //Castea a int y retorna si es mayor que o no
            } else if(primero instanceof Double) { //En los dos vendra un double
                return (double) primero > (double)segundo; //Castea a double y retorna si es mayor que o no
            } else {
                Analizador.consolaSalida += "Error semantico, no se puede hacer la operacion > por restriccion de tipos\n";
                return "errorSemantico";
            }
        } else if(tipo== Tipo_operacion.MENOR_QUE) {
            Object primero = operadorIzq.interpretar();
            Object segundo = operadorDer.interpretar();
            if(primero instanceof Integer) { //En los dos vendra un entero
               return (int) primero < (int)segundo; //Castea a int y retorna si es menor que o no
            } else if(primero instanceof Double) { //En los dos vendra un double
                return (double) primero < (double)segundo; //Castea a double y retorna si es menor que o no
            } else {
                Analizador.consolaSalida += "Error semantico, no se puede hacer la operacion < por restriccion de tipos\n";
                return "errorSemantico";
            }
        } else if(tipo== Tipo_operacion.MENORIGUAL_QUE) {
            Object primero = operadorIzq.interpretar();
            Object segundo = operadorDer.interpretar();
            if(primero instanceof Integer) { //En los dos vendra un entero
               return (int) primero <= (int)segundo; //Castea a int y retorna si es menor o igual que o no
            } else if(primero instanceof Double) { //En los dos vendra un double
                return (double) primero <= (double)segundo; //Castea a double y retorna si es menor o igual que o no
            } else {
                Analizador.consolaSalida += "Error semantico, no se puede hacer la operacion <= por restriccion de tipos\n";
                return "errorSemantico";
            }
        } else if(tipo== Tipo_operacion.MAYORIGUAL_QUE) {
            Object primero = operadorIzq.interpretar();
            Object segundo = operadorDer.interpretar();
            if(primero instanceof Integer) { //En los dos vendra un entero
               return (int) primero >= (int)segundo; //Castea a int y retorna si es mayor o igual que o no
            } else if(primero instanceof Double || segundo instanceof Double) { //En los dos vendra un double
                return (double) primero >= (double)segundo; //Castea a double y retorna si es mayor o igual que o no
            }  else {
                Analizador.consolaSalida += "Error semantico, no se puede hacer la operacion >= por restriccion de tipos\n";
                return "errorSemantico";
            }
        } else if(tipo== Tipo_operacion.IGUAL) { //Agregar comparacion entre booleanos
            Object primero = operadorIzq.interpretar();
            Object segundo = operadorDer.interpretar();
            if(primero instanceof Integer) { //En los dos vendra un entero
               return (int) primero == (int)segundo; //Castea a int y retorna si es igual que o no
            } else if(primero instanceof Double) { //En los dos vendra un double
                return (double) primero == (double)segundo; //Castea a double y retorna si es igual que o no
            } else if(primero instanceof String) { //En los dos vendra una cadena
                return primero.equals(segundo);
            } else if(primero.getClass() == Character.class) { //En los dos vendra un caracter
                return primero.equals(segundo);
            } else if(primero instanceof Boolean) { //En los dos vendra un booleano
                return primero.equals(segundo);
            } else {
                Analizador.consolaSalida += "Error semantico, no se puede hacer la operacion == por restriccion de tipos\n";
                return "errorSemantico";
            }
        } else if(tipo== Tipo_operacion.NOIGUAL) { //Agregar comparacion entre booleanos
            Object primero = operadorIzq.interpretar();
            Object segundo = operadorDer.interpretar();
            if(primero instanceof Integer) { //En los dos vendra un entero
               return (int) primero != (int)segundo; //Castea a int y retorna si es no igual que o no
            } else if(primero instanceof Double) { //En los dos vendra un double
                return (double) primero != (double)segundo; //Castea a double y retorna si es no igual que o no
            } else if(primero instanceof String) { //En los dos vendra una cadena
                if(primero.equals(segundo)) {
                    return false; //Falso pq si son iguales
                }
                return true;
            } else if(primero.getClass() == Character.class) { //En los dos vendra un caracter
                if(primero.equals(segundo)) {
                    return false; //Falso pq si son iguales
                } 
                return true;
            } else if(primero instanceof Boolean) { //En los dos vendra un booleano
                return !primero.equals(segundo);
            } else {
                Analizador.consolaSalida += "Error semantico, no se puede hacer la operacion != por restriccion de tipos\n";
                return "errorSemantico";
            }
        } 
        /* ======== Operaciones comp de un for ==== */
        /*else if(tipo== Tipo_operacion.MAY) {
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
        }*/
        /* ======== OPERACIONES LOGICAS ======== */
        else if(tipo== Tipo_operacion.OR) { 
            Object primero = operadorIzq.interpretar();
            Object segundo = operadorDer.interpretar();
            if(primero instanceof Boolean) {
                return (boolean) primero || (boolean) segundo;
            } else {
                Analizador.consolaSalida += "Error semantico, no se puede hacer la operacion or por restriccion de tipos\n";
                return "errorSemantico";
            }
        } else if(tipo== Tipo_operacion.AND) {
            Object primero = operadorIzq.interpretar();
            Object segundo = operadorDer.interpretar();
            if(primero instanceof Boolean) {
                return (boolean) primero && (boolean) segundo;
            } else {
                Analizador.consolaSalida += "Error semantico, no se puede hacer la operacion and por restriccion de tipos\n";
                return "errorSemantico";
            }
        } else if(tipo== Tipo_operacion.NOT) {
            Object valor = operadorIzq.interpretar();
            if(valor instanceof Boolean) {
                return ! (boolean) valor;
            } else {
                Analizador.consolaSalida += "Error semantico, no se puede hacer la operacion not por restriccion de tipos\n";
                return "errorSemantico";
            }
        } else {
            return "Operacion no encontrada";
        }
    }
}