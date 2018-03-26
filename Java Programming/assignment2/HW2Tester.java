// Sample set of tests for HW 2
// To test the IntegerSet and the Fraction classes.


public class HW2Tester
{
    public static void main(String[] args)
    {
        IntegerSet set1 = new IntegerSet();
        IntegerSet set2 = new IntegerSet();

        set1.insertElement(0);
        set1.insertElement(2);
        set1.insertElement(8);
        System.out.println("After set1.insertElement(10), set1 = " 
				+ set1.insertElement(10));
        set1.insertElement(4);
        set1.insertElement(6);
        set1.insertElement(10);
        set1.insertElement(100);
        set1.insertElement(12);
        set1.insertElement(95);

        set2.insertElement(0);
        set2.insertElement(3);
        set2.insertElement(6);
        set2.insertElement(9);
        set2.insertElement(12);

        System.out.println("default IntegerSet is = " + (new IntegerSet()));
        System.out.println("set1 = " + set1);
        System.out.println("set2 = " + set2);
        System.out.println("set1.union(set2) = " + set1.union(set2));
        System.out.println("set1.intersection(set2) = " + set1.intersection(set2));
        System.out.println("set1.deleteElement(2) = " + set1.deleteElement(2));
        System.out.println("set1.isEqualTo(set1) = " + set1.isEqualTo(set1));
        System.out.println("set1.isEqualTo(set2) = " + set1.isEqualTo(set2));

	System.out.println("\n\nFraction tests:\n");

	Fraction f1 = new Fraction(4,6);
	Fraction f2 = new Fraction(75,175);
	Fraction f3 = new Fraction(-6,17);

	System.out.println(f1 + " simplified = " + f1.simplify());
	System.out.println(f2 + " simplified = " + f2.simplify());
	System.out.println(f3 + " simplified = " + f3.simplify());

	// show that f1, f2, f3 haven't changed
	System.out.println("f1 = " + f1);
	System.out.println("f2 = " + f2);
	System.out.println("f3 = " + f3);

	// arithmetic
	System.out.println(f1 + " + " + f2 + " = " + f1.add(f2));
	System.out.println(f1 + " - " + f2 + " = " + f1.subtract(f2));
	System.out.println(f1 + " * " + f2 + " = " + f1.multiply(f2));
	System.out.println(f1 + " / " + f2 + " = " + f1.divide(f2));
	System.out.println();

	System.out.println(f2 + " + " + f3 + " = " + f2.add(f3));
	System.out.println(f2 + " - " + f3 + " = " + f2.subtract(f3));
	System.out.println(f2 + " * " + f3 + " = " + f2.multiply(f3));
	System.out.println(f2 + " / " + f3 + " = " + f2.divide(f3));
	System.out.println();

	// test 'division by zero' handling
	Fraction zero = new Fraction();
	System.out.println(f2 + " / " + zero + " = " + f2.divide(zero));	
    }
}
