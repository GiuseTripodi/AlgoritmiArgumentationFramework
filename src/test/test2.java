package test;

import asd.AF.IncompleteAbstractArgumentationFramework;
import asd.Argument.AbstractArgument;
import asd.Argument.Argument;
import asd.Argument.IncompleteArgument;
import asd.Argument.Relation;

import java.util.LinkedList;
import java.util.List;

public class test2 {
    public static void main(String ... args){
        AbstractArgument a1 = new IncompleteArgument("a", IncompleteArgument.type.CERTAIN);
        AbstractArgument c1 = new IncompleteArgument("b", IncompleteArgument.type.CERTAIN);
        AbstractArgument a2 = new IncompleteArgument("c", IncompleteArgument.type.CERTAIN);
        AbstractArgument b1  = new IncompleteArgument("d", IncompleteArgument.type.CERTAIN);

        Relation r1 = new Relation(a1, a2);
        Relation r2 = new Relation(a1, c1);
        Relation r3 = new Relation(a2, b1);
        Relation r4 = new Relation(c1, a2);

        IncompleteAbstractArgumentationFramework AAF = new IncompleteAbstractArgumentationFramework();

        AAF.addArgument(a1);AAF.addArgument(a2);
        AAF.addArgument(c1);
        AAF.addInteraction(r1);
        AAF.addInteraction(r2);
        System.out.println(AAF);

        AAF.removeInteraction(r1);
        System.out.println(AAF);

        AAF.removeArgument(a2);
        System.out.println(AAF);

        List<Relation> l = new LinkedList<>();l.add(r3);l.add(r4);
        AAF.addArgsAndRelations(a2, l);
        System.out.println(AAF);






    }

}
