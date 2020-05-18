package asd;

import asd.AF.AbstractAF;
import asd.Argument.AbstractArgument;
import asd.Argument.Argument;
import asd.Argument.Relation;

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
    public boolean acceptable(AbstractArgument a, List<AbstractArgument> S, AbstractAF AF){
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

    private boolean Sattacksb(List<AbstractArgument> S, AbstractArgument b, List<Relation> R){
        for(AbstractArgument s : S){
            Relation r = new Relation(s, b);
            if(R.contains(r))
                return true;
        }
        return false;
    }


}
