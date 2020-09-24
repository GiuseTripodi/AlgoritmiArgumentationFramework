package asd.AF;

import asd.Argument.Argument;
import asd.Argument.Relation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Rappresenta un AF di tipo incomplete, con tutte le proprietà
 */

public class IncompleteAbstractArgumentationFramework extends AbstractAF{
    public enum type {CERTAIN, UNCERTAIN};//Ci sono due possibili tipi

    private List<Argument> Ac; //argument certain
    private List<Argument> Au; //argument uncertain
    private List<Relation> Rc; //relation certain,
    private List<Relation> Ru; //relation, uncertain,

    public IncompleteAbstractArgumentationFramework(){
        this.Ac = new LinkedList<>();
        this.Au = new LinkedList<>();
        this.Rc = new LinkedList<>();
        this.Ru = new LinkedList<>();
    }
    public IncompleteAbstractArgumentationFramework(List<Argument> Ac, List<Argument> Au, List<Relation> Rc,  List<Relation> Ru){
        this.Ac = Ac;
        this.Au = Au;
        this.Rc = Rc;
        this.Ru = Ru;
    }

    /**
     * Il metodo aggiunge una relazione, l'insieme di aggiunta della relazione
     * è determinata tipo riportato.
     * @param relation è la relazione da aggiungere
     * @return true se l'aggiunta va a buon fine, false altrimenti
     */
    public boolean addTypeInteraction(Relation relation, type tipo){
        if(Rc.contains(relation) || Ru.contains(relation))
            return false;
        if(tipo == type.UNCERTAIN)
            Ru.add(relation);
        if(tipo == type.CERTAIN)
            Rc.add(relation);
        return true;
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
    public boolean addTypeArgument(Argument a, type tipo){
        if(Ac.contains(a) || Au.contains(a))
            return false;
        if(tipo == type.UNCERTAIN)
            Au.add(a);
        if(tipo == type.CERTAIN)
            Ac.add(a);
        return true;
    }


    public boolean removeArgument(Argument a){
        if(Au.contains(a)){
            Au.remove(a);
            return  true;
        }
        else if(Ac.contains(a)){
            Ac.remove(a);
            return  true;
        }
        return false;

    }

    @Override
    public List<Argument> getArguments(){
        LinkedList<Argument> ret = new LinkedList<>();
        ret.addAll(Ac);
        ret.addAll(Au);
        return ret;
    }

    @Override
    public List<Relation> getRelations(){
        LinkedList<Relation> ret = new LinkedList<>();
        ret.addAll(Rc);
        ret.addAll(Ru);
        return ret;
    }

    public List<Relation> getCertainRelations(){
        return Rc;
    }

    public List<Relation> getUncertainRelations(){
        return Ru;
    }

    public List<Argument> getCertainArgument(){
        return Ac;
    }

    public List<Argument> getUncertainArgument(){
        return Au;
    }




    public ListIterator<Argument> listIteratorArgument(){
        LinkedList<Argument> ret = new LinkedList<>();
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
