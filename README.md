
# ExpressionEvaluator

A Java-based logic expression evaluator and transformer, built using the **Decorator design pattern**. This project allows the construction, evaluation, transformation, and simplification of boolean logic expressions with support for the following logical operations:

- `And`
- `Nand`
- `Nor`
- `Not`
- `Or`
- `Xnor`
- `Xor`

##  Architecture

The system is built around a hierarchy of `Expression` interfaces and abstract base classes (`UnaryExpression`, `BinaryExpression`), with each operation implemented as a concrete decorator class. This makes the logic fully extensible and modular.

##  Features

- **Expression Evaluation** – Evaluate complex expressions with or without variable assignments.
- **Simplification** – Automatically reduce expressions using logical identities.
- **NAND/NOR Transformation** – Convert expressions into equivalent forms using only NAND or NOR gates.
- **String Representation** – Clear and readable format for all expressions.
- **Variable Assignment** – Support for dynamic substitution and re-evaluation.

##  Example Usage

Expression x = new Var("x");
Expression y = new Var("y");
Expression z = new Var("z");

Expression ex = new Xnor(
    new Nand(x, new Val(false)),
    new Not(new And(new Or(x, y), new Xor(new Val(true), z)))
);

System.out.println(ex);                     // Prints the full expression
System.out.println(ex.evaluate(...));       // Evaluates the expression
System.out.println(ex.nandify());           // Transforms using NANDs
System.out.println(ex.norify());            // Transforms using NORs
System.out.println(ex.simplify());          // Simplifies the expression
