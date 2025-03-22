
import java.util.Map;

public class Nor extends BinaryExpression {
    /**
     * Constructs a Nor expression.
     *
     * @param left  left operand.
     * @param right right operand.
     */
    public Nor(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * Evaluates the Nor expression.
     *
     * @param assignment variable assignment for evaluation.
     * @return result
     * @throws Exception error handler.
     */
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        try {
            boolean exp = getLeft().evaluate(assignment) || getRight().evaluate(assignment);
            return !exp;
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
            boolean exp = getLeft().evaluate() || getRight().evaluate();
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
        return "(" + getLeft().toString() + " V " + getRight().toString() + ")";
    }

    /**
     * Assigns a new expression.
     *
     * @param var        variable to assign.
     * @param expression expression to assign.
     * @return new Nor expression.
     */
    public Expression assign(String var, Expression expression) {
        return new Nor(getLeft().assign(var, expression), getRight().assign(var, expression));
    }

    /**
     * Returns new expression represents NAND operation.
     *
     * @return The NAND expression.
     */
    public Expression nandify() {
        Expression q = new Nand(getLeft().nandify(), getLeft().nandify());
        Expression r = new Nand(getRight().nandify(), getRight().nandify());
        Expression exp = new Nand(q, r);
        return new Nand(exp, exp);
    }

    /**
     * Returns new expression represents NOR operation.
     *
     * @return The NOR expression.
     */
    public Expression norify() {
        return new Nor(getLeft().norify(), new Nor(getLeft().norify(), getRight().norify()));
    }

    /**
     * Simplifies expression if it can be.
     *
     * @return simplified expression.
     */
    @Override
    public Expression simplify() {
        Val v = new Val(false);
        Expression sR = getRight().simplify();
        Expression sL = getLeft().simplify();
        Nor q = new Nor(sL, sR);

        if (cmp(sL.toString(), "T")) {
            return v;
        }
        if (cmp(sL.toString(), "F")) {
            return new Not(sR);
        }
        if (cmp(sR.toString(), "T")) {
            return v;
        }
        if (cmp(sR.toString(), "F")) {
            return new Not(sL);
        }
        if (cmp(sL.toString(), sR.toString())) {
            return new Not(sL);
        }
        return new Nor(sL, sR);
    }
}
