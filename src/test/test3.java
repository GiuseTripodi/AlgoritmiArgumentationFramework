package test;

import asd.AF.AbstractArgumentationFramework;
import asd.Argument.AbstractArgument;
import asd.Argument.Argument;
import asd.Argument.Relation;
import asd.SupportFunctions;

import java.util.LinkedList;
import java.util.List;

public class test3 {
    public static void main(String ... args){
        AbstractArgument a = new Argument("a");
        AbstractArgument b = new Argument("b");
        AbstractArgument c = new Argument("c");
        AbstractArgument d = new Argument("d");

        Relation ab = new Relation(a, b);
        Relation dc = new Relation(d, c);
        Relation ca = new Relation(c, a);

        AbstractArgumentationFramework AF = new AbstractArgumentationFramework();
        AF.addArgument(a);AF.addArgument(b);AF.addArgument(c);AF.addArgument(d);

        AF.addInteraction(ab);AF.addInteraction(dc);AF.addInteraction(ca);

        System.out.println(AF);

        SupportFunctions s = new SupportFunctions();

        List<AbstractArgument> S = new LinkedList<>(); S.add((Argument)d);

        System.out.println(s.acceptable(a, S, AF));
    }
}
