

import java.util.ArrayList;
import java.util.List;

/**
 * Represents binary expression - left and right operand.
 */
public abstract class BinaryExpression extends BaseExpression {
    private Expression left;
    private Expression right;

    /**
     * Constructs a binary expression.
     *
     * @param left  left operand.
     * @param right right operand.
     */
    public BinaryExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Gets the left operand.
     *
     * @return left operand.
     */
    public Expression getLeft() {
        return left;
    }

    /**
     * Gets right operand.
     *
     * @return right operand.
     */
    public Expression getRight() {
        return right;
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

}
