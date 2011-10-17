package primes;

public abstract class PrimesComputation {
	/**
	 * Finds all prime numbers smaller than n
	 * @param upto TODO
	 * @param uPTO
	 * @return TODO
	 * @return
	 */
	public abstract Boolean[] computePrimes(int upto)  throws Exception;
	
	public static boolean isPrime(int x) {
		if(x == 2)
			return true;
		if(x % 2 == 0 || x < 2)
			return false;
		for(long i=3;i<=Math.sqrt(x);i+=2)
			if(x % i == 0)
				return false;
		return true;
	}
}