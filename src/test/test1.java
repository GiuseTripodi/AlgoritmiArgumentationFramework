package test;

import asd.AF.AbstractArgumentationFramework;

import asd.Argument.Argument;
import asd.Argument.Relation;

import java.util.LinkedList;
import java.util.List;

public class test1 {
    public static void main(String ... args){
        Argument a1 = new Argument("a");
        Argument c1 = new Argument("c");
        Argument a2 = new Argument("b");
        Argument b1  = new Argument("d");

        Relation r1 = new Relation(a1, a2);
        Relation r2 = new Relation(a1, c1);
        Relation r3 = new Relation(a2, b1);
        Relation r4 = new Relation(c1, a2);

       AbstractArgumentationFramework AAF = new AbstractArgumentationFramework();

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
        System.out.println(AAF.getArguments());

        System.out.println(AAF.getArguments().contains(c1));






    }

}
