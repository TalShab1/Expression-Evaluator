import java.util.List;
import java.util.Map;


public class Not extends UnaryExpression {
    /**
     * Constructs a NOT expression.
     *
     * @param ex expression.
     */
    public Not(Expression ex) {
        super(ex);
    }

    /**
     * Evaluates the NOT expression.
     *
     * @param assignment variable assignment for evaluation.
     * @return result
     * @throws Exception error handler.
     */
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        try {
            return !getExpression().evaluate(assignment);
        } catch (Exception e) {
            throw new Exception("Error evaluating expression.", e);
        }
    }

    /**
     * Evaluates Nor expression without assignment.
     *
     * @return result.
     * @throws Exception error handling.
     */
    public Boolean evaluate() throws Exception {
        try {
            return !getExpression().evaluate();
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
        return getExpression().getVariables();
    }

    /**
     * Returns a string representation.
     *
     * @return The string representation.
     */
    public String toString() {
        return "~(" + getExpression().toString() + ")";
    }

    /**
     * Assigns a new expression.
     *
     * @param var        variable to assign.
     * @param expression expression to assign.
     * @return new Not expression.
     */
    public Expression assign(String var, Expression expression) {
        return new Not(this.getExpression().assign(var, expression));
    }

    /**
     * Returns new expression represents NAND operation.
     *
     * @return The NAND expression.
     */
    @Override
    public Expression nandify() {
        return new Nand(getExpression().nandify(), getExpression().nandify());
    }

    /**
     * Returns new expression represents NOR operation.
     *
     * @return The NOR expression.
     */
    @Override
    public Expression norify() {
        return new Nor(getExpression().norify(), getExpression().norify());
    }

    /**
     * Simplifies expression if it can be.
     *
     * @return simplified expression.
     */
    @Override
    public Expression simplify() {
        return new Not(getExpression().simplify());

    }

}
