package asd.Argument;

/**
 * Questa classe rappresenta il concetto di relazione nei AF, dove per relazione si intende una relazione binaria
 * tra due argomenti, dove il primo nell'insieme "attacca" il secondo.
 */


public class Relation {
    private Argument first;
    private Argument second;

    public Relation(Argument f, Argument s){
        this.first = f;
        this.second = s;
    }

    public Argument getFirst(){
        return first;
    }

    public Argument getSecond(){
        return second;
    }

    public String toString(){
        String ret = String.format("{%s,%s}", first.getValue(), second.getValue());
        return ret;
    }

    public String graphicToString(){
        String ret = String.format("{%s ---> %s}", first, second);
        return ret;

    }

    public boolean equals(Object o ){
        if(o == this)return true;
        if(o instanceof Relation){
            Relation r = (Relation)o;
            if(this.getFirst().equals(r.getFirst()) && this.getSecond().equals(r.getSecond()))
                return true;
        }
        return false;
    }
}
