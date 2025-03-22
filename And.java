
import java.util.Map;

public class And extends BinaryExpression {

    public And(Expression left, Expression right) {
        super(left, right);
    }


    /**
     * Evaluates the AND expression.
     *
     * @param assignment variable assignment for evaluation.
     * @return result
     * @throws Exception error handler.
     */

    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        try {
            boolean exp = getLeft().evaluate(assignment) && getRight().evaluate(assignment);
            return exp;
        } catch (Exception e) {
            throw new Exception("Error evaluating expression.", e);
        }
    }


    /**
     * Evaluates AND expression without assignment.
     *
     * @return result.
     * @throws Exception error handling.
     */
    public Boolean evaluate() throws Exception {
        try {
            boolean exp = getLeft().evaluate() && getRight().evaluate();
            return exp;
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
        return "(" + getLeft().toString() + " & " + getRight().toString() + ")";
    }

    /**
     * Assigns a new expression.
     *
     * @param var        variable to assign.
     * @param expression expression to assign.
     * @return new AND expression.
     */

    public Expression assign(String var, Expression expression) {
        return new And(getLeft().assign(var, expression), getRight().assign(var, expression));
    }

    /**
     * Returns new expression represents NAND operation.
     *
     * @return The NAND expression.
     */
    public Expression nandify() {
        Expression q = new Nand(getLeft().nandify(), getRight().nandify());
        return new Nand(q, q);
    }

    /**
     * Returns new expression represents NOR operation.
     *
     * @return The NOR expression.
     */

    public Expression norify() {
        Expression q = new Nor(getLeft().norify(), getLeft().norify());
        Expression e = new Nor(getRight().norify(), getRight().norify());
        return new Nor(q, e);
    }

    /**
     * Simplifies expression if it can be.
     *
     * @return simplified expression.
     */
    public Expression simplify() {
        Val v = new Val(false);
        Expression sR = getRight().simplify();
        Expression sL = getLeft().simplify();

        if (cmp(sL.toString(), "T")) {
            return sR;
        }
        if (cmp(sL.toString(), "F")) {
            return v;
        }
        if (cmp(sR.toString(), "T")) {
            return sL;
        }
        if (cmp(sR.toString(), "F")) {
            return v;
        }
        if (cmp(sL.toString(), sR.toString())) {
            return sL;
        }
        return new And(sL, sR);
    }
}
