package test;

import asd.AF.AbstractArgumentationFramework;
import asd.AF.IncompleteAbstractArgumentationFramework;
import asd.Argument.Argument;
import asd.Argument.Relation;
import asd.algoritmo;

import java.util.LinkedList;
import java.util.List;

/**
 * Esempio di test di algoritmo 1 che dovrebbe dare esito positivo
 */
public class testAlgo1 {
    public static void main(String ... args){
        IncompleteAbstractArgumentationFramework IF = new IncompleteAbstractArgumentationFramework();
        Argument a = new Argument("a");
        Argument b = new Argument("b");
        Argument c = new Argument("c");
        Argument d = new Argument("d");
        Argument e = new Argument("e");
        Argument f = new Argument("f");
        Argument test = new Argument("test");
        IF.addTypeArgument(a, IncompleteAbstractArgumentationFramework.type.CERTAIN);
        IF.addTypeArgument(b, IncompleteAbstractArgumentationFramework.type.CERTAIN);
        IF.addTypeArgument(c, IncompleteAbstractArgumentationFramework.type.CERTAIN);
        IF.addTypeArgument(d, IncompleteAbstractArgumentationFramework.type.CERTAIN);
        IF.addTypeArgument(e, IncompleteAbstractArgumentationFramework.type.CERTAIN);
        IF.addTypeArgument(f, IncompleteAbstractArgumentationFramework.type.CERTAIN);
        IF.addTypeArgument(test, IncompleteAbstractArgumentationFramework.type.UNCERTAIN);

        Relation ad = new Relation(a,d);IF.addTypeInteraction(ad, IncompleteAbstractArgumentationFramework.type.CERTAIN);
        Relation db = new Relation(d,b);IF.addTypeInteraction(db, IncompleteAbstractArgumentationFramework.type.CERTAIN);
        Relation be = new Relation(b,e);IF.addTypeInteraction(be, IncompleteAbstractArgumentationFramework.type.CERTAIN);
        Relation ec = new Relation(e,c);IF.addTypeInteraction(ec, IncompleteAbstractArgumentationFramework.type.CERTAIN);
        Relation cf = new Relation(c,f);IF.addTypeInteraction(cf, IncompleteAbstractArgumentationFramework.type.CERTAIN);
        Relation fa = new Relation(f,a);IF.addTypeInteraction(fa, IncompleteAbstractArgumentationFramework.type.CERTAIN);
        Relation testa = new Relation(test,a);IF.addTypeInteraction(testa, IncompleteAbstractArgumentationFramework.type.CERTAIN);









        System.out.println("IF = "+ IF);


        /*
        Esempio di insieme S che è una complete-extension rispetto all iAAF definito prima
         */
        List<Argument> S = new LinkedList<>();
        S.add(a);S.add(b);S.add(c);
        System.out.println("S = "+ S);

        //System.out.println(algoritmo.buildF(IF,S));
        System.out.println(algoritmo.IPosVerCO(IF,S));
        System.out.println(algoritmo.IPosVerCO_VersioneInefficente(IF,S));


    }
}
