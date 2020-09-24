package asd;

import asd.AF.AbstractArgumentationFramework;
import asd.AF.ArgumentationFramework;
import asd.AF.IncompleteAbstractArgumentationFramework;
import asd.Argument.Argument;
import asd.Argument.Relation;

import javax.sound.sampled.Line;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class algoritmo {

    /**
     * Il metodo implementa la costruzione della completion F = (A*, D*)
     *
     * @param IF
     * @param S
     * @return
     */
    public static AbstractArgumentationFramework buildF(IncompleteAbstractArgumentationFramework IF, List<Argument> S) {
        List<Argument> As;
        List<Relation> Ds ;
        //costruisco A*
        As = IF.getArguments(); // As = A U A?
        for (Argument a : IF.getUncertainArgument()) {
            for (Argument b : S) {
                Relation r1 = new Relation(a, b);
                if ((IF.getCertainRelations().contains(r1))) {
                    for (Argument c : S) {
                        Relation r2 = new Relation(c, a);
                        if (!(IF.getRelations().contains(r2)))
                            As.remove(a);
                    }//for
                }//if
            }//for
        }//for
        //Costruisco D*
        Ds = IF.getRelations();
        for(Relation r1 : IF.getUncertainRelations()){
            if(S.contains(r1.getSecond()))
                Ds.remove(r1);
        }//for
        for (Argument a : As) {
            for (Argument b : As) {
                Relation r = new Relation(a, b);
                if (! Ds.contains(r))
                    Ds.remove(r);
            }//for
        }//for
        AbstractArgumentationFramework ret = new AbstractArgumentationFramework(As, Ds);
        return ret;
    }//buildF

    /**
     * L'algoritmo risolve la verifica se S è una possibile i*-extensione sotto la complete semantics
     *
     * @param IF
     * @param S
     * @return
     */
    public static boolean IPosVerCO(IncompleteAbstractArgumentationFramework IF, List<Argument> S) {
        AbstractArgumentationFramework F = buildF(IF, S);
        List<Argument> CS = new LinkedList<>();
        Argument a = find(F, S, CS);
        while (a != null) {
            CS.add(a);
            for (Argument b : Objects.requireNonNull(SupportFunctions.CORE(IF, F, S))) {
                Relation r = new Relation(b, a);
                if (F.getRelations().contains(r)) {
                    for (Argument c : S) {
                        Relation r1 = new Relation(c, b);
                        if (F.getRelations().contains(r1) && IF.getUncertainRelations().contains(r1))
                            F.removeInteraction(r1);
                    }//for
                }//if
            }//for
            if (SupportFunctions.acceptable(a, S, F) && IF.getUncertainArgument().contains(a)) {
                F.removeArgument(a);
            }//if
            a = find(F, S, CS);
        }//while
        return SupportFunctions.completeExtension(F, S);
    }//IPosVerCO

    private static Argument find(AbstractArgumentationFramework F, List<Argument> S, List<Argument> CS) {
        for (Argument a : F.getArguments()) {
            if (!S.contains(a) && SupportFunctions.acceptable(a, S, F) && !CS.contains(a))
                return a;
        }
        return null;
    }


    /**
     * Il metodo implementa l'algoritmo di verifica di controllo che un insieme S è una possible i-extension di un iAAF IF.
     * E' una versione inefficente in quanto non usa il concetto di i*-extension ma bensì quello di i-extension. Questo algorimto
     * rientra quindi nella categoria dei problemi NP-completi.
     *
     * @param IF
     * @param S
     * @return
     */
    public static boolean IPosVerCO_VersioneInefficente(IncompleteAbstractArgumentationFramework IF, List<Argument> S) {
        ArrayList<Argument> AsPartial = new ArrayList<>(IF.getCertainArgument());
        ArrayList<Relation> RsPartial = new ArrayList<>();
        for(Relation r : IF.getCertainRelations()){
            if(AsPartial.contains(r.getFirst()) && AsPartial.contains(r.getSecond()))
                RsPartial.add(r);
        }//for
        boolean[] verificata = new boolean[1];
        for(int lenArg = 0; lenArg <= IF.getUncertainArgument().size();lenArg++){                                                       //creo le combinazioni di dimensione diversa tra gli argomenti incerti
            LinkedList<Argument> resultArgument = new LinkedList<>();
            int startPosition = 0;
            combinationArgument(AsPartial,RsPartial, lenArg, startPosition, resultArgument, verificata, S, IF);
        }
        return verificata[0];
    }

    /**
     * metodo che crea tutte le possibiili combinazioni di argomenti incerti
     * @param AsPartial, insieme di argomenti certi da mettere nell'insieme degli argomenti finele
     * @param RsPartial, insieme di relazioni certe con argomenti certi da inserire nell'insieme di relazioni finale
     * @param lenArg, lunghezza della combinazione di argoemtni incerti
     * @param startPosition, posizione iniziale su cui vare la combinazione
     * @param resultArgument, insieme di argometni risultante corrente
     * @param verificata, booleano che indica se l'algoritmo è temrinato
     * @param S
     * @param IF
     */
    private static void combinationArgument(ArrayList<Argument> AsPartial,ArrayList<Relation> RsPartial, int lenArg, int startPosition,
                                            LinkedList<Argument> resultArgument, boolean[] verificata,List<Argument> S,
                                            IncompleteAbstractArgumentationFramework IF){
        if(!verificata[0]){
            if(lenArg == 0){
                LinkedList<Argument> AstarCorrente = new LinkedList<>();AstarCorrente.addAll(AsPartial);AstarCorrente.addAll(resultArgument);
                for (int lenRel = 0;lenRel <= IF.getUncertainRelations().size(); lenRel++) {
                    LinkedList<Relation> resultRelation = new LinkedList<>();
                    int startPositionRel = 0;
                    combinationRelation(RsPartial, lenRel, startPositionRel, resultRelation,  AstarCorrente , verificata, S, IF);
                }
                return;
            }//lenArg == 0
            for(int i = startPosition; i <= IF.getUncertainArgument().size() - lenArg; i++){
                resultArgument.add(IF.getUncertainArgument().get(i));  //aggiungo argomento incerto
                combinationArgument(AsPartial, RsPartial, lenArg-1, i+1, resultArgument, verificata, S, IF);
                resultArgument.remove(IF.getUncertainArgument().get(i));//rimuovo argomento incerto
            }
        }//not verificata
    }//combination Argument

    /**
     * Metodo privato che crea tutte le possibili combinazioni di relazioni con dimensione k, rispetto all’insieme di argomenti ricevuto.
     * Una volta creato l’insieme, ed ottenuta quindi una completion si occupa di fare effettivamente la verifica di complete extension.
     * @param RsPartial insieme di relazioni certe con argomenti certi da inserire nell'insieme di relazioni finale
     * @param lenRel, lunghezza della combinazione di relazioni incerte
     * @param startPositionRel, posizione iniziale su cui fare la combinazione
     * @param resultRelation, insieme della combinazione risultate corrente
     * @param AstarCorrente, insieme di argoemti della completion su cui calcolare l'isnieme delle relazini
     * @param verificata, boolean che indica la terminazione dell'algo
     * @param S
     * @param IF
     */
    private static void combinationRelation(ArrayList<Relation>RsPartial, int lenRel,int startPositionRel,
                                            LinkedList<Relation> resultRelation, LinkedList<Argument> AstarCorrente ,boolean[] verificata,
                                            List<Argument> S, IncompleteAbstractArgumentationFramework IF){
        if(! verificata[0]){
            if(lenRel == 0){
                LinkedList<Relation>  RstarCorrente = new LinkedList<>();
                for(Relation r: resultRelation){
                    if(AstarCorrente.contains(r.getFirst()) && AstarCorrente.contains(r.getSecond()))
                        RstarCorrente.add(r);
                }//for
                RstarCorrente.addAll(RsPartial);
                AbstractArgumentationFramework AFCompletion = new AbstractArgumentationFramework(AstarCorrente,RstarCorrente);
                //creo S*
                LinkedList<Argument> S_star = new LinkedList<>();
                for(Argument s : S)
                    if(AstarCorrente.contains(s))
                        S_star.add(s);
                if(SupportFunctions.completeExtension(AFCompletion, S_star))
                    verificata[0] = true;
                return;
            }//lenRel == 0
            for(int j = startPositionRel; j<= IF.getUncertainRelations().size() - lenRel; j++ ){
                resultRelation.add(IF.getUncertainRelations().get(j)); //aggiungo una relazione incerta
                combinationRelation(RsPartial, lenRel-1, j +1, resultRelation, AstarCorrente,verificata,S, IF );
                resultRelation.remove(IF.getUncertainRelations().get(j));//rimuovo una relazione incerta
            }

        }//not verificata
    }//combinationRelation

}
