import java.util.HashMap;
import java.util.Map;
/**
 * A class to test expressions.
 */
public class ExpressionsTest {
    /**
     * main method to execute the tests.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) throws Exception {
        Expression x = new Var("x");
        Expression y = new Var("y");
        Expression z = new Var("z");
        Expression ex = new Xnor(new Nand(x, new Val(false)),
                new Not(new And(new Or(x, y),
                        new Xor(new Val(true), z))));
        System.out.println(ex);

        Map<String, Boolean> assignment = new HashMap<>();
        assignment.put("x", true);
        assignment.put("y", false);
        assignment.put("z", true);
        System.out.println(ex.evaluate(assignment));

        System.out.println(ex.nandify());

        System.out.println(ex.norify());

        System.out.println(ex.simplify());
    }
}
