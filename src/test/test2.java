package test;

import asd.AF.IncompleteAbstractArgumentationFramework;

import asd.Argument.Argument;
import asd.Argument.Relation;

import java.util.LinkedList;
import java.util.List;

public class test2 {
    public static void main(String ... args){
        Argument a1 = new Argument("a");
        Argument c1 = new Argument("b");
        Argument a2 = new Argument("c");
        Argument b1  = new Argument("d");

        Relation r1 = new Relation(a1, a2);
        Relation r2 = new Relation(a1, c1);
        Relation r3 = new Relation(a2, b1);
        Relation r4 = new Relation(c1, a2);

        IncompleteAbstractArgumentationFramework AAF = new IncompleteAbstractArgumentationFramework();

        AAF.addTypeArgument(a1, IncompleteAbstractArgumentationFramework.type.UNCERTAIN);
        AAF.addTypeArgument(a2, IncompleteAbstractArgumentationFramework.type.UNCERTAIN);
        AAF.addTypeArgument(c1, IncompleteAbstractArgumentationFramework.type.UNCERTAIN);
        AAF.addTypeInteraction(r1, IncompleteAbstractArgumentationFramework.type.UNCERTAIN);
        AAF.addTypeInteraction(r2, IncompleteAbstractArgumentationFramework.type.UNCERTAIN);
        System.out.println(AAF);

        AAF.removeInteraction(r1);
        System.out.println(AAF);

        AAF.removeArgument(a2);
        System.out.println(AAF);








    }

}
