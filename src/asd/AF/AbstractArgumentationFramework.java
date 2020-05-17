package asd.AF;

import asd.Argument.AbstractArgument;
import asd.Argument.Argument;
import asd.Argument.Relation;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * La classe esplicita un Abstract Argumentation Framework, e ne definisce tutti i metodi per relzionarsi ad esso.
 */

public class AbstractArgumentationFramework extends  AbstractAF {
    private List<Argument> A; //set of arguments
    private List<Relation> R; //set of relation, defeats or attacks

    public AbstractArgumentationFramework(){
        this.A = new LinkedList<>();
        this.R = new LinkedList<>();
    }

    /*
    L'aggiunta di una relazione è fattibile solo se gli argomenti coinvolti
    sono già presenti nell' insieme A dell AF
     */
    public boolean addInteraction(Relation rel){
        if(R.contains(rel))return false;
        //Verifico che A contenga gli elementi della relazione
        if(A.contains(rel.getFirst()) && A.contains(rel.getSecond())){
            R.add(rel);
            return  true;
        }
        return false;
    }

    /*
    Rimuove solo una relazione esistente in (A, R) , quini r deve appartenere  R
     */
    public boolean removeInteraction(Relation r){
        if(R.contains(r)){
            R.remove(r);
            return true;
        }
        return  false;
    }

    @Override
    public boolean addArgument(AbstractArgument a) {
        if(A.contains(a)){
            return false;
        }
        A.add((Argument)a);
        return true;

    }

    public boolean addArgsAndRelations(AbstractArgument a , List<Relation> r ) {
        this.addArgument(a);
        for(Relation rel : r ){
            if (rel.getSecond().equals(a) || rel.getFirst().equals(a))//inserisco solo le relazioni corrette
                this.addInteraction(rel);
        }
        return true;
    }

    @Override
    public boolean removeArgument(AbstractArgument a) {
        if(! A.contains(a))
            return false;
        A.remove(a);
        for(Relation r : R){
            if (r.getSecond().equals(a) || r.getFirst().equals(a))
                this.removeInteraction(r);
        }
        return true;
    }


    public List<Relation> getRelations(){
        return R;
    }

    public ListIterator<Argument> listIteratorArgument(){
        return A.listIterator();
    }

    public ListIterator<Relation> listIteratorRelation(){
        return R.listIterator();
    }

}
