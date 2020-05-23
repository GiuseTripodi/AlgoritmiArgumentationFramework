package asd.AF;

import asd.Argument.AbstractArgument;
import asd.Argument.Argument;
import asd.Argument.Relation;

import java.util.List;
import java.util.ListIterator;

/**
 * Classe astratta che definisce alcuni metodi, quali equals e toString.
 */

public abstract class AbstractAF implements ArgumentationFramework {

    public String toString(){
        ListIterator<AbstractArgument> ita = this.listIteratorArgument();

        ListIterator<Relation> itr = this.listIteratorRelation();

        String ret = "";
        ret += "{";
        ret += "(";
        while(ita.hasNext()){
            ret += ita.next().getValue();
            if(ita.hasNext()) ret += ", ";

        }
        ret += ") , (";
        while(itr.hasNext()){
            ret += itr.next().toString();
            if(itr.hasNext()) ret += ", ";

        }
        ret += ")}";
        return ret;
    }

    public boolean equals(Object o){
        if(o == this)return true;
        if(o instanceof AbstractAF ){
            AbstractAF a = (AbstractAF)o;
            if(a.sizeArguments() != this.sizeArguments() || a.sizeRelations() != this.sizeRelations())
                return false;
            ListIterator<Argument> ita = this.listIteratorArgument();
            ListIterator<Argument> ita2 = a.listIteratorArgument();
            while (ita.hasNext()){
                if(! ita.next().equals(ita2.next()))
                    return false;
            }
            ListIterator<Relation> itr = this.listIteratorRelation();
            ListIterator<Relation> itr2 = a.listIteratorRelation();
            while (ita.hasNext()){
                if(! ita.next().equals(ita2.next()))
                    return false;
            }

            return true;
        }
        return false;
    }

}
