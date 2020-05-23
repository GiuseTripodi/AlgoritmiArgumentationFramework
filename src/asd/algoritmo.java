package asd;

import asd.AF.AbstractArgumentationFramework;
import asd.AF.ArgumentationFramework;
import asd.AF.IncompleteAbstractArgumentationFramework;
import asd.Argument.AbstractArgument;
import asd.Argument.Argument;
import asd.Argument.Relation;

import java.util.LinkedList;
import java.util.List;

public class algoritmo {

    /**
     * Il metodo implementa la costruzione della completion F = (A*, D*)
     * @param IF
     * @param S
     * @return
     */
    public static AbstractArgumentationFramework buildF(ArgumentationFramework IF, List<AbstractArgument> S){
        List<Argument> As;
        List<Relation> Ds = new LinkedList<>();

        //costruisco As
        As = IF.getArguments();
        IncompleteAbstractArgumentationFramework IF1 = (IncompleteAbstractArgumentationFramework)IF;
        for(AbstractArgument a : IF1.getUncertainArgument()){
            for(AbstractArgument b : S){
                Relation r1 = new Relation(a,b);
                if((IF1.getCertainRelations().contains(r1))){
                    for (AbstractArgument c : S){
                        Relation r2 = new Relation(c,a);
                        if(! (IF1.getRelations().contains(r2)))
                            As.remove(a);
                    }//for
                }//if
            }//for
        }//for

        //Costruisco Ds
        List<Relation> Dtest = IF.getRelations();
        for(AbstractArgument b : S){
            for(AbstractArgument a : IF1.getArguments()){
                Relation r = new Relation(a,b);
                if(IF1.getUncertainRelations().contains(r))
                    Dtest.remove(r);
            }//for
        }//for

        //costruisco A* x A*
        for(AbstractArgument a : As){
            for(AbstractArgument b : As){
                Relation r = new Relation(a,b);
                if(Dtest.contains(r))
                    Ds.add(r);

            }//for
        }//for

        AbstractArgumentationFramework ret = new AbstractArgumentationFramework(As,Ds);
        return ret;
    }//buildF

    /**
     * L'algoritmo risolve la verifica se S è una possibile i*-extensione sotto la complete semantics
     * @param IF
     * @param S
     * @return
     */
    public static boolean Algoritmo1(IncompleteAbstractArgumentationFramework IF, List<AbstractArgument> S){
        AbstractArgumentationFramework F = buildF(IF,S);
        List<AbstractArgument> CS = new LinkedList<>();
        AbstractArgument a = find(F,S, CS);
        while(a != null){
            CS.add(a);
            for(AbstractArgument b : SupportFunctions.CORE(IF,F,S)){
                Relation r = new Relation(b,a);
                if(F.getRelations().contains(r)){
                    for(AbstractArgument c : S){
                        Relation r1 = new Relation(c,b);
                        if(F.getRelations().contains(r1) && IF.getUncertainRelations().contains(r1))
                            F.removeInteraction(r1);
                    }//for
                }//if

            }//for
            if(SupportFunctions.acceptable(a,S,F) && IF.getUncertainArgument().contains(a)){
                F.removeArgument(a); //rimuovendo l'argomento, elimina automaticamente anche le relazioni
            }//if
            a = find(F,S, CS);
        }//while
        System.out.println(F);
        return SupportFunctions.completeExtension(F,S);

    }//Algoritmo 1

    private static AbstractArgument find(AbstractArgumentationFramework F , List<AbstractArgument> S, List<AbstractArgument> CS){
        for(AbstractArgument a : F.getArguments()){
            if(! S.contains(a) && SupportFunctions.acceptable(a,S,F) && ! CS.contains(a))
                return a;
        }
        return null;
    }


}
