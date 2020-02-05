package hw3;
import java.math.BigInteger;
import java.lang.Number;
import java.lang.Object;


public final class Rational extends Number{
	
	private final BigInteger numerator;
	private final BigInteger denominator;
	
	public BigInteger getNumerator() {
		return numerator;
	}
	
	public BigInteger getDenominator() {
		return denominator;
	}
	
    private static BigInteger gcd(BigInteger u, BigInteger v) {
    	
    	if (u == BigInteger.ZERO) {
            return v;
        } else if (v == BigInteger.ZERO) {
            return u;
        } else if (u == BigInteger.ONE || v == BigInteger.ONE) {
            return BigInteger.ONE;
        }
        BigInteger temp;
        while (v != BigInteger.ZERO) {
            temp = u.mod(v); //3 % 7 = R3 
            u = v;
            v = temp;
        }
        return u;
    }
    
    private static Rational reduce(Rational num) {
    	BigInteger gcd = gcd(num.getNumerator(), num.getDenominator());
    	return new Rational(((num.getNumerator()).divide(gcd)), ((num.getDenominator()).divide(gcd))); 
    	//divide both numerator and denominator by GCD
    	
    }
    
    private static Rational neg(Rational num) {
        return new Rational((num.getNumerator()).negate(), num.getDenominator());
    }
	
	public Rational (int numerator, int denominator) { //public parameterized constructor
		//convert primitive int to wrapper class BigInteger, example of autoboxing
		
		if(denominator == 0)
			System.out.println("Denominator cannot be zero");
		
		if(numerator < 0 && denominator < 0) {
			numerator = numerator * -1;
			denominator = denominator * -1;
		}
		
		this.numerator = BigInteger.valueOf(numerator);
		this.denominator = BigInteger.valueOf(denominator);
		
	}
	
	public Rational (BigInteger numerator, BigInteger denominator) { //public parameterized constructor
		//turn the BigInteger to an int
		
		BigInteger zero = BigInteger.ZERO;
		BigInteger one = BigInteger.ONE;
		BigInteger negOne = one.negate();
		
		int numPosNeg = numerator.compareTo(zero);
		int denPosNeg = denominator.compareTo(zero);
		
		if(denominator.equals(zero))
			System.out.println("Denominator cannot be zero");
		
		if(numPosNeg == -1 && denPosNeg == -1) {
			numerator = numerator.multiply(negOne);
			denominator = denominator.multiply(negOne);
		}
		
		this.numerator = numerator;
		this.denominator = denominator;

	}
	
	
	public static Rational add(Rational num1, Rational num2) { //arguments should be type Rational
				
		return reduce(new Rational((((num1.getNumerator()).multiply(num2.getDenominator())).add((num2.getNumerator()).multiply(num1.getDenominator()))),
		((num1.getDenominator()).multiply(num2.getDenominator()))));
	}
	
	public static Rational subtract(Rational num1, Rational num2) { //arguments should be type Rational
		return add(num1, neg(num2));
	}
	
	public static Rational multiply(Rational num1, Rational num2) { //arguments should be type Rational
		
		return reduce(new Rational( num1.getNumerator().multiply(num2.getNumerator()) , ( num1.getDenominator().multiply(num2.getDenominator()) )));
		
	}
	
	public static Rational divide(Rational num1, Rational num2) { //arguments should be type Rational
		Rational num3 =(new Rational(num2.getDenominator(),num2.getNumerator()));
		
		return multiply(num1,num3);
	}
	
	
	@Override 	
	public boolean equals(Object o) {
		if (this.toString().equals(o.toString()))
			return true;
		else 
			return false;
			
	}
	
	@Override
	//Override from java.lang.Object
	public String toString() {
		
		BigInteger newNum = this.numerator;
		BigInteger newDen = this.denominator;
		BigInteger gcd = gcd(newNum,newDen);
		
		
		
		return String.format((newNum.divide(gcd)) + "/" + (newDen.divide(gcd)));
	}
	
	@Override 
	//Override from java.lang.Number
	public int intValue() {

		return (numerator.divide(denominator)).intValue();
		
	}
	
	public double doubleValue() { //divide the two BigInteger numbers using divide class
		return (numerator.divide(denominator)).doubleValue();
	}
	
	public float floatValue() {
		return (numerator.divide(denominator)).floatValue();
	}
	
	public long longValue() {
		return (numerator.divide(denominator)).longValue();
	}
	
	//factory methods
	public static Rational intToRational(int num) { //turn num to Rational, then return new num()

		Rational r = new Rational (num,1);
		return r;
		
	}
	public static Rational BigIntegerToRational(BigInteger num) { 
		
		Rational r = new Rational(num,BigInteger.ONE);
		return r;
		
	}
	
}

