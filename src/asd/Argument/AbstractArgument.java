package asd.Argument;

import java.util.Objects;

/**
 * Questa classe è quella astratta che definisce la struttura base di un Argomento.
 */


public abstract class AbstractArgument {
    private final String value; // Una volta che è stato definito non può essere più modificato

    public AbstractArgument(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    @Override
    public String toString(){
        return String.format("{%s}", value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o instanceof AbstractArgument){
            AbstractArgument a = (AbstractArgument)o;
            if(a.getValue().equals(this.getValue())){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
