package primes.threads.balanced;

import primes.PrimesComputation;

public class PrimesBalanced extends PrimesComputation {

	@Override
	public Boolean[] computePrimes(int upto) throws InterruptedException {
		int noProcessors = Runtime.getRuntime().availableProcessors();
		PrimesComputationThread[] threads = new PrimesComputationThread[noProcessors];

		Boolean[] results = new Boolean[upto];
		for (int i = 0; i < noProcessors; i++) {
			threads[i] = new PrimesComputationThread(i,  noProcessors, results);
			threads[i].start();
		}

		for (int i = 0; i < noProcessors; i++) {
			threads[i].join();
		}
		return results;
	}

	static class PrimesComputationThread extends Thread {
		private final int startIndex;
		private final int step;
		private final Boolean[] a;

		public PrimesComputationThread(int startIndex, int step, Boolean[] a) {
			this.startIndex = startIndex;
			this.step = step;
			this.a = a;
		}

		@Override
		public void run() {
			for (int x = startIndex; x < a.length; x+=step)
				a[x] = isPrime(x);
		}
	}

}
