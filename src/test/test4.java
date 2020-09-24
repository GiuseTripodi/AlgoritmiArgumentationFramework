package test;

import asd.AF.AbstractArgumentationFramework;
import asd.AF.IncompleteAbstractArgumentationFramework;
import asd.Argument.Argument;
import asd.Argument.Relation;
import asd.SupportFunctions;

import java.util.LinkedList;
import java.util.List;

public class test4 {
    public static void main(String... args){
        SupportFunctions s = new SupportFunctions();

        //Rappresento l'IF' in Figure 1, pagina 3. Per Verificare in base al example 3
        IncompleteAbstractArgumentationFramework IF = new IncompleteAbstractArgumentationFramework();
        Argument a = new Argument("a");
        Argument b = new Argument("b");
        Argument c = new Argument("c");
        Argument d = new Argument("d");
        Argument e = new Argument("e");
        Argument f = new Argument("f");
        Argument g = new Argument("g");
        IF.addTypeArgument(a, IncompleteAbstractArgumentationFramework.type.UNCERTAIN);
        IF.addTypeArgument(b, IncompleteAbstractArgumentationFramework.type.UNCERTAIN);
        IF.addTypeArgument(c, IncompleteAbstractArgumentationFramework.type.UNCERTAIN);
        IF.addTypeArgument(d, IncompleteAbstractArgumentationFramework.type.UNCERTAIN);
        IF.addTypeArgument(e, IncompleteAbstractArgumentationFramework.type.UNCERTAIN);
        IF.addTypeArgument(f, IncompleteAbstractArgumentationFramework.type.UNCERTAIN);
        IF.addTypeArgument(g, IncompleteAbstractArgumentationFramework.type.UNCERTAIN);

        Relation ab = new Relation(a,b);IF.addTypeInteraction(ab, IncompleteAbstractArgumentationFramework.type.UNCERTAIN);
        Relation ad = new Relation(a,d);IF.addTypeInteraction(ad, IncompleteAbstractArgumentationFramework.type.UNCERTAIN);
        Relation ag = new Relation(a,g);IF.addTypeInteraction(ag, IncompleteAbstractArgumentationFramework.type.UNCERTAIN);
        Relation bc = new Relation(b,c);IF.addTypeInteraction(bc, IncompleteAbstractArgumentationFramework.type.UNCERTAIN);
        Relation cd = new Relation(c,d);IF.addTypeInteraction(cd, IncompleteAbstractArgumentationFramework.type.UNCERTAIN);
        Relation dc = new Relation(d,c);IF.addTypeInteraction(dc, IncompleteAbstractArgumentationFramework.type.UNCERTAIN);
        Relation de = new Relation(d,e);IF.addTypeInteraction(de, IncompleteAbstractArgumentationFramework.type.UNCERTAIN);
        Relation ee = new Relation(e,e);IF.addTypeInteraction(ee, IncompleteAbstractArgumentationFramework.type.UNCERTAIN);
        Relation fa = new Relation(f,a);IF.addTypeInteraction(fa, IncompleteAbstractArgumentationFramework.type.UNCERTAIN);
        Relation gf = new Relation(g,f);IF.addTypeInteraction(gf, IncompleteAbstractArgumentationFramework.type.UNCERTAIN);

        System.out.println("IF = "+ IF);

        List<Argument> arg = new LinkedList<>();
        for(Argument p : IF.getArguments()){
            Argument tes = new Argument(p.getValue());
            arg.add(tes);
        }
        AbstractArgumentationFramework F = new AbstractArgumentationFramework(arg, IF.getRelations());

        System.out.println("F = "+ F );

        List<Argument> S = new LinkedList<>();S.add(a);
        System.out.println("S = " + S);


        //System.out.println(s.coreArgument(c ,IF, F, S));

        System.out.println(s.CORE(IF,F,S));








    }

}
