package test;

import asd.AF.AbstractArgumentationFramework;
import asd.AF.ArgumentationFramework;
import asd.Argument.Argument;
import asd.Argument.Relation;
import asd.SupportFunctions;

import java.util.LinkedList;
import java.util.List;

public class test3 {
    public static void main(String ... args){
        Argument a = new Argument("a");
        Argument b = new Argument("b");
        Argument c = new Argument("c");
        Argument d = new Argument("d");

        Relation ab = new Relation(a, b);
        Relation dc = new Relation(d, c);
        Relation ca = new Relation(c, a);

        AbstractArgumentationFramework AF = new AbstractArgumentationFramework();
        AF.addArgument(a);AF.addArgument(b);AF.addArgument(c);AF.addArgument(d);

        AF.addInteraction(ab);AF.addInteraction(dc);AF.addInteraction(ca);

        System.out.println("AF = " + AF);

        SupportFunctions s = new SupportFunctions();

        List<Argument> S = new LinkedList<>(); S.add(d);
        S.add(d);
        S.add(c);
        System.out.println("S = " + S);

        System.out.println(s.acceptable(a, S, AF));

        //S.add(c);
        System.out.println(s.conflictFree(AF,S));

        System.out.println(s.admissible(AF,S));


    }
}
