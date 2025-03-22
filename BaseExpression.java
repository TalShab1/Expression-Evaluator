

import java.util.ArrayList;
import java.util.List;

/**
 * Base abstract class for expressions, should be extended by expression classes.
 */
public abstract class BaseExpression implements Expression {

    /**
     * Returns list of the variables in the expression.
     *
     * @return list of variables in the expression.
     */
    public List<String> getVariables() {
        List<String> vars = new ArrayList<String>();
        return vars;
    }

    /**
     * Compares two strings=.
     *
     * @param s1 the first string to be compared
     * @param s2 the second string to be compared
     * @return true equal false otherwise
     */
    protected boolean cmp(String s1, String s2) {
        int l1 = s1.length();
        int l2 = s2.length();
        if (l1 == l2) {
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
