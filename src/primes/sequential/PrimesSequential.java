package primes.sequential;

import primes.PrimesComputation;

public class PrimesSequential extends PrimesComputation {
	@Override
	public Boolean[] computePrimes(int upto) { 
		Boolean[] results= new Boolean[upto];
		for(int x=0;x<results.length;x++)
			results[x] = isPrime(x);
		return results;
	}
}
