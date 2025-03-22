import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Or extends BinaryExpression {
    /**
     * Constructs an Or expression.
     *
     * @param left  left operand.
     * @param right right operand.
     */
    public Or(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * Evaluates the Or expression.
     *
     * @param assignment variable assignment for evaluation.
     * @return result
     * @throws Exception error handler.
     */
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        try {
            boolean exp = getLeft().evaluate(assignment) || getRight().evaluate(assignment);
            return exp;
        } catch (Exception e) {
            throw new Exception("Error evaluating expression.", e);
        }

    }

    /**
     * Evaluates Or expression without assignment.
     *
     * @return result.
     * @throws Exception error handling.
     */
    public Boolean evaluate() throws Exception {
        try {
            boolean exp = getLeft().evaluate() || getRight().evaluate();
            return exp;
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
        List<String> vars = new ArrayList<String>();
        vars.addAll(getLeft().getVariables());
        vars.addAll(getRight().getVariables());
        return vars;
    }

    /**
     * Returns a string representation.
     *
     * @return The string representation.
     */
    public String toString() {
        return "(" + getLeft().toString() + " | " + getRight().toString() + ")";
    }

    /**
     * Assigns a new expression.
     *
     * @param var        variable to assign.
     * @param expression expression to assign.
     * @return new Or expression.
     */
    public Expression assign(String var, Expression expression) {
        return new Or(getLeft().assign(var, expression), getRight().assign(var, expression));
    }

    /**
     * Returns new expression represents NAND operation.
     *
     * @return The NAND expression.
     */
    public Expression nandify() {
        Expression q = new Nand(getLeft().nandify(), getLeft().nandify());
        Expression r = new Nand(getRight().nandify(), getRight().nandify());
        return new Nand(q, r);
    }

    /**
     * Returns new expression represents NOR operation.
     *
     * @return The NOR expression.
     */
    public Expression norify() {
        Expression q = new Nor(getLeft().norify(), getRight().norify());
        return new Nor(q, q);
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
        Or q = new Or(sL, sR);

        if (cmp(sL.toString(), "T")) {
            return v;
        }
        if (cmp(sL.toString(), "F")) {
            return sR;
        }
        if (cmp(sR.toString(), "T")) {
            return v;
        }
        if (cmp(sR.toString(), "F")) {
            return sL;
        }
        if (cmp(sL.toString(), sR.toString())) {
            return sL;
        }
        return new Or(sL, sR);
    }
}
