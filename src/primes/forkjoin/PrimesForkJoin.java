package primes.forkjoin;

import jsr166y.ForkJoinPool;
import jsr166y.RecursiveAction;
import primes.PrimesComputation;

public class PrimesForkJoin extends PrimesComputation {
	@Override
	public Boolean[] computePrimes(int upto) throws Exception {
		ForkJoinPool pool = new ForkJoinPool();
		Boolean[] results = new Boolean[upto];
		pool.invoke(new PrimesTask(0, results.length, results));
		return results;
	}

	static class PrimesTask extends RecursiveAction {
		private static final long serialVersionUID = 5514355747533962701L;
		private final int from;
		private final int to;
		private final Boolean[] results;

		public PrimesTask(int from, int to, Boolean[] results) {
			this.from = from;
			this.to = to;
			this.results = results;
		}

		@Override
		public void compute() {
			if (to - from < 1000)
				for (int x = from; x < to; x++)
					results[x] = isPrime(x);
			else {
				PrimesTask left = new PrimesTask(from, (to + from) / 2, results);
				PrimesTask right = new PrimesTask((to + from) / 2, to, results);
				left.fork();
				right.compute();
				left.join();
//				right.join();
			}
		}
	}

}
