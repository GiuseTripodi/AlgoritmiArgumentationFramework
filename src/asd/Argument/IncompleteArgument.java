package asd.Argument;

/**
 * Questa classe implememta il concetto di argument per un Argumentation framework ti tipo incompelte, deve gli argomenti
 * oltre ad avere un un valore sono identificati da un attributo "certain" o "uncertain"
 */

public class IncompleteArgument extends AbstractArgument {
    public enum type {CERTAIN, UNCERTAIN};//Ci sono due possibili tipi
    private type tipo;


    public IncompleteArgument(String value, type tipo){
        super(value);
        this.tipo = tipo;
    }

    public type getType(){
        return tipo;
    }

    public boolean equals(Object o){
        if(o instanceof IncompleteArgument){
            IncompleteArgument i = (IncompleteArgument)o;
            boolean a = i.getType().equals(this.getType());
            return super.equals(o) && a;
        }
        return false;
    }

    public String toString(){
        String ret = super.toString();
        return ret;
    }


    public String CompletetoString(){
        String ret = super.toString();
        return ret + " Il tipo è:" + tipo;
    }

}
