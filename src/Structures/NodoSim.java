package Structures;

/**
 * @author polares
 */
public class NodoSim {
    public String id;
    public String rol; //Metodo o variable
    public String tipo;
    public String entorno;
    public Object valor;
    
    public NodoSim(String id, String rol, String tipo, String entorno, Object valor) {
        this.id = id;
        this.rol = rol;
        this.tipo = tipo;
        this.entorno = entorno;
        this.valor = valor;
    }
}
