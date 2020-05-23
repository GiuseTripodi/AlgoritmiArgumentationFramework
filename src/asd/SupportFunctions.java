package asd;

import asd.AF.AbstractAF;
import asd.AF.AbstractArgumentationFramework;
import asd.AF.ArgumentationFramework;
import asd.AF.IncompleteAbstractArgumentationFramework;
import asd.Argument.AbstractArgument;
import asd.Argument.Argument;
import asd.Argument.IncompleteArgument;
import asd.Argument.Relation;

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
    public static boolean acceptable(AbstractArgument a, List<AbstractArgument> S, ArgumentationFramework AF){
        List<AbstractArgument> A = AF.getArguments();
        List<Relation> R = AF.getRelations();
        for(AbstractArgument b : A){
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
    private static boolean Sattacksb(List<AbstractArgument> S, AbstractArgument b, List<Relation> R){
        for(AbstractArgument s : S){
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
    public static boolean conflictFree(ArgumentationFramework AF, List<AbstractArgument> S){
        List<Relation> R = AF.getRelations();
        for(AbstractArgument a : S){
            for(AbstractArgument b : S){
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
    public static boolean admissible(ArgumentationFramework AF, List<AbstractArgument> S){
        if(conflictFree(AF,S)){
            for(AbstractArgument a : S){
                if(! acceptable(a,S,AF))
                    return false;
            }
            return true;

        }
        return false;
    }


    /**
     * L'argoritmo verifica se S è una complete extension w.r.t AF, ovvero se è ammissibile  e tutti contiene tutti
     * gli argomenti accettabili in A
     * @param AF
     * @param S
     * @return true se S è una complete extension
     */
    public static boolean completeExtension(ArgumentationFramework AF, List<AbstractArgument> S){
        if(admissible(AF, S)){
            List<AbstractArgument> A = AF.getArguments();
            for(AbstractArgument a : A){
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
     * Il metodo verifica se l'argomento a è un core argument seguendo una versione rilassata dell'algoritmo che
     * non valuta le c-expansion
     * @param a
     * @param IF è di tipo (A,A?, D, D?)
     * @param F, è di tipo (A*, D*)
     * @param S
     * @return true se l'argomento a è un core argument
     */
    public static boolean coreArgument(AbstractArgument a, IncompleteAbstractArgumentationFramework IF, AbstractArgumentationFramework F, List<AbstractArgument> S){
        LinkedList<AbstractArgument> cEx = new LinkedList<>();
        List<AbstractArgument> visitati = new LinkedList<>();
        return coreArgument(a,cEx, visitati, IF, F,S);
    }

    private static boolean coreArgument(AbstractArgument a , LinkedList<AbstractArgument> cEx, List<AbstractArgument> visitati,  IncompleteAbstractArgumentationFramework IF, AbstractArgumentationFramework F, List<AbstractArgument> S){
        if(! (F.getArguments().contains(a) && ! S.contains(a)))return false; // ! a appartiene ad A*/S
        cEx.add(a);
        visitati.add(a);
        //controllo 2
        for(AbstractArgument b : S){
            Relation r1 = new Relation(a,b);Relation r2 = new Relation(b,a);
            if((F.getRelations().contains(r1) && IF.getCertainRelations().contains(r1) ||
                    (F.getRelations().contains(r2) && IF.getCertainRelations().contains(r2))))
                    return false;

        }//for
        if(cEx.size() > 0){
            for(int  i = 1; i <cEx.size(); i++){
                Relation r = new Relation(cEx.get(i), cEx.get(i -1));
                if(! F.getRelations().contains(r))
                    return false;
            }//for
        }//if

        for(AbstractArgument j : cEx){
            Relation r = new Relation(j, cEx.getLast());
            if(F.getRelations().contains(r))
                return true;
        }//for
        List<AbstractArgument> adiacenti = adiacenti(a,IF);
        for(AbstractArgument ad : adiacenti){
            if(! visitati.contains(ad))
                return coreArgument(ad, cEx, visitati,IF,F,S);
        }
        return false; //return fittizia
    }//coreArgument

    private static List<AbstractArgument> adiacenti(AbstractArgument a, IncompleteAbstractArgumentationFramework IF){
        List<AbstractArgument> ret = new LinkedList<>();
        for(AbstractArgument b : IF.getArguments()){
            Relation r = new Relation(a,b);
            if(IF.getRelations().contains(r))//
                ret.add(b);
        }
        return  ret;
    }

    public static List<AbstractArgument> CORE(IncompleteAbstractArgumentationFramework IF, AbstractArgumentationFramework F, List<AbstractArgument> S){
        List<AbstractArgument> ret = new LinkedList<>();
        for(AbstractArgument a : F.getArguments()) {
            if (coreArgument(a, IF, F, S))
                ret.add(a);
        }
        return ret;
    }






}//SupportFunctions
