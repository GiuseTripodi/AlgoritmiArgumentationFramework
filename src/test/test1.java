package test;

import asd.Argument.AbstractArgument;
import asd.Argument.Argument;
import asd.Argument.IncompleteArgument;
import asd.Argument.Relation;

public class test1 {
    public static void main(String ... args){
        AbstractArgument a1 = new Argument("a");
        Argument c1 = new Argument("c");
        IncompleteArgument a2 = new IncompleteArgument("b", IncompleteArgument.type.CERTAIN);

        System.out.println(a1);
        System.out.println(a2);

        //Prova equals

        Argument b1  = new Argument("bbb");

        System.out.println(b1.equals(a1));

        Relation r = new Relation(a1, a2);
        Relation r2 = new Relation(a1, a2);
        Relation r3 = new Relation(a1, b1);



        System.out.println(r.equals(r3));


    }

}
