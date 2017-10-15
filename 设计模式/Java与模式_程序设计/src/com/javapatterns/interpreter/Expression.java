package com.javapatterns.interpreter;

/**
 * Abstraction of a class representing terminal and non-terminal
 * classes of the following little grammar:<br>
 * <pre>
 * BooleanExp ::=
 *           BooleanExp AND BooleanExp
 *         | BooleanExp OR BooleanExp
 *         | NOT BooleanExp
 *         | Variable
 *         | Constant
 * Variable ::= ... // a string of printable, non-white space characters
 * Contant ::= "true" | "false"
 * </pre>
 */
public abstract class Expression {
    /**
     * Given a BooleanExp object denoting a term,
     * this method interprets this term relative to a Context
     * object.
     */
    public abstract boolean interpret(Context ctx);

    /**
     * Given a BooleanExp object denoting a term,
     * this method test whether the given argument
     * denoting another term is structurally the same.
     */
    public abstract boolean equals(Object o);

    /**
     * Returns a hash code of this term.
     */
    public abstract int hashCode();

    /**
     * Converts a term into a string. Can be used as the
     * basis for calculating the hashCode.
     */
    public abstract String toString();
}
