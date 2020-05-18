package asd.AF;

import asd.Argument.AbstractArgument;
import asd.Argument.Argument;
import asd.Argument.IncompleteArgument;
import asd.Argument.Relation;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Rappresenta un AF di tipo incomplete, con tutte le proprietà
 */

public class IncompleteAbstractArgumentationFramework extends AbstractAF{
    private List<IncompleteArgument> Ac; //argument certain
    private List<IncompleteArgument> Au; //argument uncertain
    private List<Relation> Rc; //relation certain, both the argument exist
    private List<Relation> Ru; //relation, uncertain, both the argument are not sure

    public IncompleteAbstractArgumentationFramework(){
        this.Ac = new LinkedList<>();
        this.Au = new LinkedList<>();
        this.Rc = new LinkedList<>();
        this.Ru = new LinkedList<>();
    }

    /**
     * Il metodo aggiunge una relazione, l'insieme di aggiunta della relazione
     * è determinata dal tipo degli argomenti coinvolti nella relazione
     * @param relation è la relazione da aggiungere
     * @return true se l'aggiunta va a buon fine, false altrimenti
     */
    public boolean addInteraction(Relation relation){
        if(Ru.contains(relation) || Rc.contains(relation))
            return false;
        IncompleteArgument first = (IncompleteArgument)relation.getFirst();
        IncompleteArgument second = (IncompleteArgument)relation.getSecond();


        if(verificaCertain(first) && verificaCertain(second)){
            Rc.add(relation);
            return true;
        }
        if(verificaUncertain(first) && verificaUncertain(second)){
            Ru.add(relation);
            return true;
        }
        return false;
    }//addInteraction


    /**
     * Il metodo verifica l'appartenenza di un argomento ad un particolare insieme,
     * in questo caso verifica se l'argomento è di tipo certain ed appartiene
     * all'insieme degli argomenti certain;
     * @param args
     * @return boolean se è certain ed appartiene all'insieme certain
     */
    private boolean verificaCertain(IncompleteArgument args){
        return args.getType() ==  IncompleteArgument.type.CERTAIN && Ac.contains(args);
    }

    private boolean verificaUncertain(IncompleteArgument args){
        return args.getType() ==  IncompleteArgument.type.UNCERTAIN && Au.contains(args);
    }

    /**
     * Il metodo rimuove un interaction in bae all insieme in cui appartiene
     * @param r, relazione da rimuovere
     * @return true se è stata rimossa
     */
    public boolean removeInteraction(Relation r){
        if(Ru.contains(r)){
            Ru.remove(r);
            return  true;
        }
        else if(Rc.contains(r)){
            Rc.remove(r);
            return  true;
        }
        return false;

    }

    /**
     * Aggiunge un argomento
     * @param a, argomento da aggiungere
     * @return true se l'aggiunta è andata a buon fine
     */
    public boolean addArgument(AbstractArgument a){
        IncompleteArgument ia = (IncompleteArgument)a;
        if( verificaCertain(ia) || verificaUncertain(ia))
            return false;
        if(ia.getType() == IncompleteArgument.type.CERTAIN){
            Ac.add(ia);
            return true;
        }
        if(ia.getType() == IncompleteArgument.type.UNCERTAIN){
            Au.add(ia);
            return true;
        }
        return false;
    }

    /**
     *
     * @param a , argument da inserire
     * @param r, insieme di che coinvolgono l'argomento
     * @return
     */
    public boolean addArgsAndRelations(AbstractArgument a , List<Relation> r ){
        this.addArgument(a);
        for(Relation rel : r ){
            if (rel.getSecond().equals(a) || rel.getFirst().equals(a))//inserisco solo le relazioni corrette
                this.addInteraction(rel);
        }
        return true;
    }

    public boolean removeArgument(AbstractArgument a){
        IncompleteArgument ia = (IncompleteArgument)a;
        if( ! verificaCertain(ia) || ! verificaUncertain(ia))
            return false;
        if(ia.getType() == IncompleteArgument.type.CERTAIN){
            Ac.remove(ia);
            for(Relation r : Rc){
                if (r.getSecond().equals(a) || r.getFirst().equals(a))
                    this.removeInteraction(r);
            }
        }
        if(ia.getType() == IncompleteArgument.type.UNCERTAIN){
            Au.remove(ia);
            for(Relation r : Ru){
                if (r.getSecond().equals(a) || r.getFirst().equals(a))
                    this.removeInteraction(r);
            }
        }
        return true;

    }

    public List<IncompleteArgument> getArguments(){
        LinkedList<IncompleteArgument> ret = new LinkedList<>();
        ret.addAll(Ac);
        ret.addAll(Au);
        return ret;
    }

    public List<Relation> getRelations(){
        LinkedList<Relation> ret = new LinkedList<>();
        ret.addAll(Rc);
        ret.addAll(Ru);
        return ret;
    }


    public ListIterator<IncompleteArgument> listIteratorArgument(){
        LinkedList<IncompleteArgument> ret = new LinkedList<>();
        ret.addAll(Ac);
        ret.addAll(Au);
        return ret.listIterator();
    }

    public ListIterator<Relation> listIteratorRelation(){
        LinkedList<Relation> ret = new LinkedList<>();
        ret.addAll(Rc);
        ret.addAll(Ru);
        return ret.listIterator();
    }
















}
