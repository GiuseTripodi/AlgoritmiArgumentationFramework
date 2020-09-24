package asd;

import asd.AF.AbstractArgumentationFramework;
import asd.AF.ArgumentationFramework;
import asd.AF.IncompleteAbstractArgumentationFramework;
import asd.Argument.Argument;
import asd.Argument.Relation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SupportFunctions {

    /**
     * Il metodo verifica se l'argomento a è accettabile w.r.t ad S ed AF. Un argomento è accetabile ripsetto
     * ad un insieme S, se per ogni argomento b che lo attacca, l'insieme S attacca b.
     * @param a , argomento di cui verificare l'accettabilità
     * @param S
     * @param AF
     * @return true se l'argomenti è accettabile
     */
    public static boolean acceptable(Argument a, List<Argument> S, ArgumentationFramework AF){
        List<Argument> A = AF.getArguments();
        List<Relation> R = AF.getRelations();
        for(Argument b : A){
            Relation r = new Relation(b,a);
            if(R.contains(r))
                if(! Sattacksb(S, b, R))
                    return false;


        }
        return true;

    }

    /**
     * La funzione verifica se ci troviamo nel caso in cui SRa.
     * @param S
     * @param b
     * @param R
     * @return true se S R a
     */
    private static boolean Sattacksb(List<Argument> S, Argument b, List<Relation> R){
        for(Argument s : S){
            Relation r = new Relation(s, b);
            if(R.contains(r))
                return true;
        }
        return false;
    }


    /**
     * Il metodo verifica se l'insieme S è di tipo conflict-free , quindi che tutti
     * i suoi argomenti non si attacchino a vicenda
     * @param AF
     * @param S
     * @return
     */
    public static boolean conflictFree(ArgumentationFramework AF, List<Argument> S){
        List<Relation> R = AF.getRelations();
        for(Argument a : S){
            for(Argument b : S){
                Relation r = new Relation(a,b);
                if(R.contains(r))
                    return false;
            }
        }
        return true;
    }

    /**
     * Verifica se data un AF un insieme S è ammisibile rispetto a quel AF.
     * Ovvero se l'insieme è conflict-free ed ogni suo alemento è accetabile
     * @param AF
     * @param S
     * @return
     */
    public static boolean admissible(ArgumentationFramework AF, List<Argument> S){
        if(conflictFree(AF,S)){
            for(Argument a : S){
                if(! acceptable(a,S,AF))
                    return false;
            }
            return true;

        }
        return false;
    }


    /**
     * L'argoritmo verifica se S è una complete extension w.r.t AF, ovvero se è ammissibile e contiene tutti
     * gli argomenti accettabili in A
     * @param AF
     * @param S
     * @return true se S è una complete extension
     */
    public static boolean completeExtension(ArgumentationFramework AF, List<Argument> S){
        if(admissible(AF, S)){
            List<Argument> A = AF.getArguments();
            for(Argument a : A){
                if(acceptable(a,S,AF)){
                    if(!S.contains(a))
                        return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Il metodo verifica se l'argomento a è un core argument. O meglio dato un possibile core-argument
     * verifica l'esistenza di almeno una c-expansion di quell'argomento
     * se questa ricerca da esito positivo allora restituice true. Cerca la c-expansion più
     * piccola per poter verificare la proprietà.
     * @param a
     * @param IF è di tipo (A,A?, D, D?)
     * @param F, è di tipo (A*, D*)
     * @param S
     * @return true se l'argomento a è un core argument
     */
    public static boolean coreArgument(Argument a, IncompleteAbstractArgumentationFramework IF, AbstractArgumentationFramework F, List<Argument> S){
        ArrayList<Argument> cEx = new ArrayList<>();
        List<Argument> visitati = new LinkedList<>();
        return coreArgument(a,cEx, visitati, IF, F,S);
    }

    /**
     *
     * @param a
     * @param cEx, c-expansion corrente
     * @param visitati
     * @param IF
     * @param F
     * @param S
     * @return
     */
    private static boolean coreArgument(Argument a , ArrayList<Argument> cEx, List<Argument> visitati, IncompleteAbstractArgumentationFramework IF, AbstractArgumentationFramework F, List<Argument> S){
        visitati.add(a);
        cEx.add(a);

        if(! (F.getArguments().contains(a) && ! S.contains(a)))return false; // ! a appartiene ad A*/S
        //controllo 2, lo faccio una volta sola per ogni nuovo elemento aggiunto
        for(Argument b : S){
            Relation r1 = new Relation(a,b);Relation r2 = new Relation(b,a);
            if((F.getRelations().contains(r1) && IF.getCertainRelations().contains(r1) ||
                    (F.getRelations().contains(r2) && IF.getCertainRelations().contains(r2)))) {
                return false;
            }

        }//for
        if(cEx.size() > 1){
            int i = cEx.size()-1;
            Relation r = new Relation(cEx.get(i), cEx.get(i -1));
            if(! F.getRelations().contains(r)) {
                return false;
            }
        }//if


        for(Argument j : cEx){
            Relation r = new Relation(j, cEx.get(cEx.size()-1));//cEx.getLast()
            if(F.getRelations().contains(r))
                return true;
        }//for


        /*
        Se soddifo l'ultima condizione, ciò implica che ho verificato le quattro condizioni e possso fermarmi
         */
        List<Argument> adiacenti = adiacenti(a,IF);
        if(adiacenti.size() > 0) {
            //verifico se ci sono nodi adicenti
            for (Argument ad : adiacenti) {
                if (!visitati.contains(ad)) {
                    if (coreArgument(ad, cEx, visitati, IF, F, S)) {
                        return true;
                    }
                    cEx.remove(cEx.size() -1 );//rimuovo l'ultimo elelemento aggiunto che poi è l'adiacente che sto considerando.
                }
            }//for
        }//non ci sono nodi adiacenti, alimino l'ultimo inserito e vado avanti
        return false; //return fittizia
    }//coreArgument

    private static List<Argument> adiacenti(Argument a, IncompleteAbstractArgumentationFramework IF){
        List<Argument> ret = new LinkedList<>();
        for(Argument b : IF.getArguments()){
            Relation r = new Relation(a,b);Relation r1 = new Relation(b,a);
            if(IF.getRelations().contains(r) || IF.getRelations().contains(r1))//
                ret.add(b);
        }
        return  ret;
    }

    /**
     * Il metodo restituisce l'insieme di core-argument w.r.t IF, F, S.
     * @param IF
     * @param F
     * @param S
     * @return
     */
    public static List<Argument> CORE(IncompleteAbstractArgumentationFramework IF, AbstractArgumentationFramework F, List<Argument> S){
        if(! isCompletionOf(IF,F)) return null;
        List<Argument> ret = new LinkedList<>();
        for(Argument a : F.getArguments()) {
            if (coreArgument(a, IF, F, S) && ! ret.contains(a))
                ret.add(a);
        }
        return ret;
    }

    /**
     * Il metodo verifica se l'abstract argumentation framework F è una completions dell'incomplete abstract argumentation framework iAAF
     * @param iAAF, incomplete abstract arguementation framework
     * @param F, abstract argumentation framework
     * @return, true se F è una completions di iAAF
     */
    private static boolean isCompletionOf(IncompleteAbstractArgumentationFramework iAAF, AbstractArgumentationFramework F){
        if(iAAF.getArguments().containsAll(F.getArguments()) && iAAF.getRelations().containsAll(F.getRelations()))
            return true;
        return false;

    }






}//SupportFunctions
