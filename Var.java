import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Var implements Expression {
    private String name;

    /**
     * Constructs a new Val object.
     *
     * @param name name of variable.
     */
    public Var(String name) {
        this.name = name;
    }

    /**
     * Evaluates the variable.
     *
     * @param assignment variable assignment for evaluation.
     * @return result
     * @throws Exception error handler.
     */
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        try {
            if (!assignment.containsKey(name) || assignment == null) {
                throw new Exception("not found in assignment.");
            }
            return assignment.get(name);
        } catch (Exception e) {
            throw new Exception("Error evaluating expression.", e);
        }
    }

    /**
     * Evaluates Variable without assignment.
     *
     * @return result.
     * @throws Exception error handling.
     */
    public Boolean evaluate() throws Exception {
        throw new Exception();
    }

    /**
     * Returns list of the variables in the expression.
     *
     * @return list of variables in the expression.
     */
    public List<String> getVariables() {
        List<String> list = new ArrayList<>();
        list.add(this.name);
        return list;
    }

    /**
     * Returns a string representation.
     *
     * @return The string representation.
     */
    public String toString() {
        return name;
    }

    /**
     * Assigns a new expression.
     *
     * @param var        variable to assign.
     * @param expression expression to assign.
     * @return new Val expression.
     */
    public Expression assign(String var, Expression expression) {
        if (cmp(var, this.name)) {
            return expression;
        }
        return this;
    }

    /**
     * Returns new expression represents NAND operation.
     *
     * @return The NAND expression.
     */
    @Override
    public Expression nandify() {
        return this;
    }

    /**
     * Returns new expression represents NOR operation.
     *
     * @return The NOR expression.
     */
    @Override
    public Expression norify() {
        return this;
    }

    /**
     * Simplifies expression if it can be.
     *
     * @return simplified expression.
     */
    @Override
    public Expression simplify() {
        return this;
    }

    /**
     * Compares two strings=.
     *
     * @param s1 the first string to be compared
     * @param s2 the second string to be compared
     * @return true equal false otherwise
     */
    public boolean cmp(String s1, String s2) {
        int l1 = s1.length();
        int l2 = s2.length();
        if (l1 == l2) {
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}
