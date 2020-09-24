package asd.AF;

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
    public AbstractArgumentationFramework(List<Argument> A, List<Relation> R){
        this.A = A;
        this.R = R;
    }

    /**
     * il metodo aggiunge una relazione all'Argumentation Fraework
     *
     * L'aggiunta di una relazione è fattibile solo se gli argomenti coinvolti
     * sono già presenti nell' insieme A dell AF
     * @param rel
     * @return boolean
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


    public boolean removeInteraction(Relation relation){
        if(R.contains(relation)){
            R.remove(relation);
            return true;
        }
        return  false;
    }

    /**
     * Il metodo aggiunge un argomento che non è coinvolto in alcun tipo di relazione, all'Argumentation Framework,
     * @param a
     * @return boolean
     */
    public boolean addArgument(Argument a) {
        if(A.contains(a)){
            return false;
        }
        A.add((Argument)a);
        return true;

    }

    @Override
    public boolean removeArgument(Argument a) {
        if(! A.contains(a))
            return false;
        A.remove(a);
        List<Relation> rel = new LinkedList<>(this.getRelations());
        for(Relation r : rel){
            if (r.getSecond().equals(a) || r.getFirst().equals(a))
                this.removeInteraction(r);
        }
        return true;
    }

    /**
     * Il metodo aggiunge all' AF sia un argomento ed un insieme di relazionoi che lo convolgono
     * @param a , argument da inserire
     * @param r, insieme di che coinvolgono l'argomento
     * @return true, se l'operazione va a buon fine
     */
    public boolean addArgsAndRelations(Argument a , List<Relation> r ) {
        this.addArgument(a);
        for(Relation rel : r ){
            if (rel.getSecond().equals(a) || rel.getFirst().equals(a))//inserisco solo le relazioni corrette
                this.addInteraction(rel);
        }
        return true;
    }




    public List<Argument> getArguments(){
        return A;
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
