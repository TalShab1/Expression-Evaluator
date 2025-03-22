
import java.util.Map;

public class Nand extends BinaryExpression {
    /**
     * Constructs a Nand expression.
     *
     * @param left  left operand.
     * @param right right operand.
     */
    public Nand(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * Evaluates the Nand expression.
     *
     * @param assignment variable assignment for evaluation.
     * @return result
     * @throws Exception error handler.
     */

    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        try {
            boolean exp = getLeft().evaluate(assignment) && getRight().evaluate(assignment);
            return !exp;
        } catch (Exception e) {
            throw new Exception("Error evaluating expression.", e);
        }
    }

    /**
     * Evaluates Nand expression without assignment.
     *
     * @return result.
     * @throws Exception error handling.
     */

    public Boolean evaluate() throws Exception {
        try {
            boolean exp = getLeft().evaluate() && getRight().evaluate();
            return !exp;
        } catch (Exception e) {
            throw new Exception("Error evaluating expression.", e);
        }
    }

    /**
     * Returns list of the variables in the expression.
     *
     * @return list of variables in the expression.
     */

    public String toString() {
        return "(" + getLeft().toString() + " A " + getRight().toString() + ")";
    }

    /**
     * Assigns a new expression.
     *
     * @param var        variable to assign.
     * @param expression expression to assign.
     * @return new Nand expression.
     */
    public Expression assign(String var, Expression expression) {
        return new Nand(getLeft().assign(var, expression), getRight().assign(var, expression));
    }

    /**
     * Returns new expression represents NAND operation.
     *
     * @return The NAND expression.
     */
    public Expression nandify() {
        return new Nand(getLeft().nandify(), getRight().nandify());
    }

    /**
     * Returns new expression represents NOR operation.
     *
     * @return The NOR expression.
     */
    public Expression norify() {
        Expression q = new Nor(getLeft().norify(), getLeft().norify());
        Expression r = new Nor(getRight().norify(), getRight().norify());
        Expression exp = new Nor(q, r);
        return new Nor(exp, exp);
    }

    /**
     * Simplifies expression if it can be.
     *
     * @return simplified expression.
     */
    @Override
    public Expression simplify() {
        Val v = new Val(true);
        Expression sR = getRight().simplify();
        Expression sL = getLeft().simplify();
        Nand q = new Nand(sL, sR);

        if (cmp(sL.toString(), "T")) {
            return new Not(sR);
        }
        if (cmp(sL.toString(), "F")) {
            return v;
        }
        if (cmp(sR.toString(), "T")) {
            return new Not(sL);
        }
        if (cmp(sR.toString(), "F")) {
            return v;
        }
        if (cmp(sL.toString(), sR.toString())) {
            return new Not(sL);
        }
        return new Nand(sL, sR);
    }
}
