package asd.AF;

import asd.Argument.Argument;
import asd.Argument.Relation;
import test.LibreriaIAAF;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


/**
 * L'interfaccia descrive tutti i metodi base nell'interazione con
 * un Argumentation Framework, che sia di tipo AAF oppure iAAF.
 * L'AF è rappresentato come una coppia (A, R), dove gli argomenti
 * in se al momento sono rappresentati come oggetti Argument, mentre le relazioni
 * come oggetti Relation.
 *
 * I metodi restituiscono un boolean che indica se l'operaione è andata a buon fine
 *
 */
public interface ArgumentationFramework {

    /**
     * Il metodo rimuove un relazione dall AF,    Rimuove solo una relazione esistente in (A, R) , quini r deve appartenere  R
     * @param relation
     * @return boolean
     */
    public boolean removeInteraction(Relation relation);




    /**
     * Il metodo rimuove un argomento dall AF
     * @param argument
     * @return boolean
     */
    public boolean removeArgument(Argument argument);

    /**
     *
     * @return Tutte le relazioni presenti nell'AF
     */
    public List<Relation> getRelations();

    public List<Argument> getArguments();

    /**
     *
     * @return la lista di tutti gli argomenti presenti nell'AF
     */
    //public abstract <T> List<T> getArguments();

    /**
     *
     * @return un List iteratori sugli argomenti, è di tipo parametrico
     */
    <T> ListIterator<T> listIteratorArgument();

    /**
     *
     * @return un list iterator sulle relazioni
     */
    ListIterator<Relation> listIteratorRelation();



    /**
     * Il metodo restituisce una lista di tutti gli argomenti
     * @param <T>
     * @return
     */
    /*
    <T> List<T> getArguments();

    <T> List<T> getRelations();

    */



    /**
     *
     * @return il numero di argomenti presenti nell' AF.
     */
    default int sizeArguments(){
        int ret = 0;
        ListIterator<Argument> it = listIteratorArgument();
        while(it.hasNext()){
            ret++;
        }
        return ret;
    }

    /**
     *
     * @return il numero di relazioni presenti nell' AF
     */
    default int sizeRelations(){
        int ret = 0;
        ListIterator<Relation> it = listIteratorRelation();
        while(it.hasNext()){
            ret++;
        }
        return ret;
    }





}
