
import java.util.Map;

public class Xor extends BinaryExpression {
    /**
     * Constructs a Xor expression.
     *
     * @param left  left operand.
     * @param right right operand.
     */
    public Xor(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * Evaluates the Xor expression.
     *
     * @param assignment variable assignment for evaluation.
     * @return result
     * @throws Exception error handler.
     */
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        try {
            boolean exp = (getLeft().evaluate(assignment) && !getRight().evaluate(assignment))
                    || (!getLeft().evaluate(assignment) && getRight().evaluate(assignment));
            return exp;
        } catch (Exception e) {
            throw new Exception("Error evaluating expression.", e);
        }

    }

    /**
     * Evaluates Xor expression without assignment.
     *
     * @return result.
     * @throws Exception error handling.
     */
    public Boolean evaluate() throws Exception {
        try {
            boolean exp = (getLeft().evaluate() && !getRight().evaluate())
                    || (!getLeft().evaluate() && getRight().evaluate());
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
        return "(" + getLeft().toString() + " ^ " + getRight().toString() + ")";
    }

    /**
     * Assigns a new expression.
     *
     * @param var        variable to assign.
     * @param expression expression to assign.
     * @return new Xor expression.
     */
    public Expression assign(String var, Expression expression) {
        return new Xor(getLeft().assign(var, expression), getRight().assign(var, expression));
    }

    /**
     * Returns new expression represents NAND operation.
     *
     * @return The NAND expression.
     */
    public Expression nandify() {
        Expression r = new Nand(getLeft().nandify(), getRight().nandify());
        return new Nand(new Nand(getLeft().nandify(), r), new Nand(getRight().nandify(), r));
    }

    /**
     * Returns new expression represents NOR operation.
     *
     * @return The NOR expression.
     */
    public Expression norify() {
        Expression q = new Nor(new Nor(getLeft().norify(), getLeft().norify()),
                new Nor(getRight().norify(), getRight().norify()));
        return new Nor(q, new Nor(getLeft().norify(), getRight().norify()));
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

        if (cmp(sL.toString(), "T")) {
            return new Not(sR);
        }
        if (cmp(sL.toString(), "F")) {
            return sR;
        }
        if (cmp(sR.toString(), "T")) {
            return new Not(sL);
        }
        if (cmp(sR.toString(), "F")) {
            return sL;
        }
        if (cmp(sL.toString(), sR.toString())) {
            return v;
        }
        return new Xor(sL, sR);
    }
}
