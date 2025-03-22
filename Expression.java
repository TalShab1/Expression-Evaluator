import java.util.List;
import java.util.Map;

public interface Expression {
    /**
     * Evaluates the expression with variable assignment.
     *
     * @param assignment A map contains variables.
     * @return result of evaluating.
     * @throws Exception error handler.
     */
    Boolean evaluate(Map<String, Boolean> assignment) throws Exception;

    /**
     * Evaluates expression.
     *
     * @return result of evaluating.
     * @throws Exception error handler.
     */
    Boolean evaluate() throws Exception;

    /**
     * Returns list of the variables in the expression.
     *
     * @return list of variables in the expression.
     */
    List<String> getVariables();

    /**
     * Returns a string representation.
     *
     * @return The string representation.
     */
    String toString();

    /**
     * Assigns a new expression.
     *
     * @param var        variable to assign.
     * @param expression expression to assign.
     * @return new expression.
     */
    Expression assign(String var, Expression expression);

    /**
     * Returns new expression represents NAND operation.
     *
     * @return The NAND expression.
     */
    Expression nandify();

    /**
     * Returns new expression represents NOR operation.
     *
     * @return The NOR expression.
     */
    Expression norify();

    /**
     * Simplifies expression if it can be.
     *
     * @return simplified expression.
     */
    Expression simplify();

}
