package test;

import asd.AF.AbstractArgumentationFramework;
import asd.AF.IncompleteAbstractArgumentationFramework;
import asd.Argument.AbstractArgument;
import asd.Argument.Argument;
import asd.Argument.IncompleteArgument;
import asd.Argument.Relation;
import asd.algoritmo;

import java.util.LinkedList;
import java.util.List;

public class testAlgo1 {
    public static void main(String ... args){
        IncompleteAbstractArgumentationFramework IF = new IncompleteAbstractArgumentationFramework();
        AbstractArgument a = new IncompleteArgument("a", IncompleteArgument.type.CERTAIN); IF.addArgument(a);
        IncompleteArgument b = new IncompleteArgument("b", IncompleteArgument.type.UNCERTAIN); IF.addArgument(b);
        IncompleteArgument c = new IncompleteArgument("c", IncompleteArgument.type.CERTAIN); IF.addArgument(c);
        IncompleteArgument d = new IncompleteArgument("d", IncompleteArgument.type.CERTAIN);IF.addArgument(d);
        IncompleteArgument e = new IncompleteArgument("e", IncompleteArgument.type.CERTAIN);IF.addArgument(e);
        IncompleteArgument f = new IncompleteArgument("f", IncompleteArgument.type.CERTAIN);IF.addArgument(f);
        IncompleteArgument g = new IncompleteArgument("g", IncompleteArgument.type.CERTAIN);IF.addArgument(g);

        Relation ab = new Relation(a,b);IF.addTypeInteraction(ab, IncompleteArgument.type.UNCERTAIN);
        Relation ad = new Relation(a,d);IF.addTypeInteraction(ad, IncompleteArgument.type.UNCERTAIN);
        Relation ag = new Relation(a,g);IF.addTypeInteraction(ag, IncompleteArgument.type.UNCERTAIN);
        Relation bc = new Relation(b,c);IF.addTypeInteraction(bc, IncompleteArgument.type.CERTAIN);
        Relation cd = new Relation(c,d);IF.addTypeInteraction(cd, IncompleteArgument.type.CERTAIN);
        Relation dc = new Relation(d,c);IF.addTypeInteraction(dc, IncompleteArgument.type.CERTAIN);
        Relation de = new Relation(d,e);IF.addTypeInteraction(de, IncompleteArgument.type.CERTAIN);
        Relation ee = new Relation(e,e);IF.addTypeInteraction(ee, IncompleteArgument.type.CERTAIN);
        Relation fa = new Relation(f,a);IF.addTypeInteraction(fa, IncompleteArgument.type.UNCERTAIN);
        Relation gf = new Relation(g,f);IF.addTypeInteraction(gf, IncompleteArgument.type.CERTAIN);

        System.out.println("IF = "+ IF);

        List<Argument> arg = new LinkedList<>();
        for(IncompleteArgument p : IF.getArguments()){
            Argument tes = new Argument(p.getValue());
            arg.add(tes);
        }
        AbstractArgumentationFramework F = new AbstractArgumentationFramework(arg, IF.getRelations());

        System.out.println("F = "+ F );

        List<AbstractArgument> S = new LinkedList<>();S.add(a);
        System.out.println(S);

        System.out.println(algoritmo.buildF(IF,S));
        System.out.println(algoritmo.Algoritmo1(IF,S));

    }
}
