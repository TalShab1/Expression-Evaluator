import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Val implements Expression {
    private boolean b;

    /**
     * Constructs a new Val object.
     *
     * @param b boolean value.
     */
    public Val(boolean b) {
        this.b = b;
    }

    /**
     * Gets the boolean value.
     *
     * @return boolean value.
     */
    public Boolean getB() {
        return b;
    }

    /**
     * Evaluates the Val.
     *
     * @param assignment variable assignment for evaluation.
     * @return result
     * @throws Exception error handler.
     */
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        try {
            return b;
        } catch (Exception e) {
            throw new Exception("Error evaluating expression.", e);
        }
    }

    /**
     * Evaluates Val without assignment.
     *
     * @return result.
     * @throws Exception error handling.
     */
    public Boolean evaluate() throws Exception {
        try {
            return b; // Assuming `b` is a boolean expression or variable
        } catch (Exception e) {
            throw new Exception("Error evaluating expression.", e);
        }
    }

    /**
     * Returns list of the variables in the expression.
     *
     * @return list of variables in the expression.
     */
    public List<String> getVariables() {
        List<String> l = new ArrayList<>();
        return l;
    }

    /**
     * Returns a string representation.
     *
     * @return The string representation.
     */
    public String toString() {
        if (this.b) {
            return "T";
        }
        return "F";
    }

    /**
     * Assigns a new expression.
     *
     * @param var        variable to assign.
     * @param expression expression to assign.
     * @return new Var expression.
     */
    public Expression assign(String var, Expression expression) {
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

}
