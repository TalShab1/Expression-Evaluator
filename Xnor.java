
import java.util.Map;

public class Xnor extends BinaryExpression {
    /**
     * Constructs a Xnor expression.
     *
     * @param left  left operand.
     * @param right right operand.
     */
    public Xnor(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * Evaluates the Xnor expression.
     *
     * @param assignment variable assignment for evaluation.
     * @return result
     * @throws Exception error handler.
     */
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        try {
            boolean exp = (getLeft().evaluate(assignment) && !getRight().evaluate(assignment))
                    || (!getLeft().evaluate(assignment) && getRight().evaluate(assignment));
            return !exp;
        } catch (Exception e) {
            throw new Exception("Error evaluating expression.", e);
        }

    }

    /**
     * Evaluates Xnor expression without assignment.
     *
     * @return result.
     * @throws Exception error handling.
     */
    public Boolean evaluate() throws Exception {
        try {
            boolean exp = (getLeft().evaluate() && !getRight().evaluate())
                    || (!getLeft().evaluate() && getRight().evaluate());
            return !exp;
        } catch (Exception e) {
            throw new Exception("Error evaluating expression.", e);
        }
    }

    /**
     * Returns a string representation.
     *
     * @return The string representation.
     */
    public String toString() {
        return "(" + getLeft().toString() + " # " + getRight().toString() + ")";
    }

    /**
     * Assigns a new expression.
     *
     * @param var        variable to assign.
     * @param expression expression to assign.
     * @return new Xnor expression.
     */
    public Expression assign(String var, Expression expression) {
        return new Xnor(getLeft().assign(var, expression), getRight().assign(var, expression));
    }

    /**
     * Returns new expression represents NAND operation.
     *
     * @return The NAND expression.
     */
    public Expression nandify() {
        Expression g = new Nand(getRight().nandify(), getRight().nandify());
        Expression f = new Nand(getLeft().nandify(), getLeft().nandify());
        return new Nand(new Nand(f, g), new Nand(getLeft().nandify(), getRight().nandify()));
    }

    /**
     * Returns new expression represents NOR operation.
     *
     * @return The NOR expression.
     */
    public Expression norify() {
        Expression q = new Nor(getLeft().norify(), getRight().norify());
        return new Nor(new Nor(getLeft().norify(), q), new Nor(getRight().norify(), q));
    }

    /**
     * Simplifies expression if it can be.
     *
     * @return simplified expression.
     */
    @Override
    public Expression simplify() {
        Expression sL = getLeft().simplify();
        Expression sR = getRight().simplify();

        if (cmp(sL.toString(), sR.toString())) {
            return new Val(true);
        }
        return new Xnor(sL, sR);
    }
}
