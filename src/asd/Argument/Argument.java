package asd.Argument;

import java.util.Objects;

/**
 * Questa classe implementa il concetto di argomento per per un Argumentation Framework di tipo abstract,
 * dove gli argomenti non implementano nulla di  nuovo rispetto alla super classe,
 */



public class Argument {
    private final String value; // Una volta che è stato definito non può essere più modificato

    public Argument(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Argument argument = (Argument) o;
        return Objects.equals(value, argument.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Argument{" +
                "value='" + value + '\'' +
                '}';
    }
}
