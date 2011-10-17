package primes.parallel;

import primes.PrimesComputation;
import extra166y.Ops.IntToObject;
import extra166y.ParallelArray;

public class PrimesParallel extends PrimesComputation {
	@Override
	public Boolean[] computePrimes(int upto) {
		ParallelArray<Boolean> p = ParallelArray.create(upto, Boolean.class, ParallelArray.defaultExecutor());
		p.replaceWithMappedIndex(new IntToObject<Boolean>() {
			@Override
			public Boolean op(int x) {
				return isPrime(x);
			}
		});			
		return p.getArray();
	}
}
