package hw3;

public class test {
	public static void main (String[] args) {
		Rational r1 = new Rational (5,2);
		Rational r2 = new Rational (-10,-4);
		System.out.println(r1);
		System.out.println(r2); // output should be the same .
		System.out.println(r1.equals(r2)); // output true
	}
}