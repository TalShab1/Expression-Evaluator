
public abstract class UnaryExpression extends BaseExpression {
    private Expression expression;

    /**
     * Constructs a UnaryExpression.
     *
     * @param expression expression for operation.
     */
    public UnaryExpression(Expression expression) {
        this.expression = expression;
    }

    /**
     * Gets the expression.
     *
     * @return The expression.
     */
    public Expression getExpression() {
        return expression;
    }

    /**
     * Sets the expression.
     *
     * @param expression new expression.
     */
    public void setExpression(Expression expression) {
        this.expression = expression;
    }


}
