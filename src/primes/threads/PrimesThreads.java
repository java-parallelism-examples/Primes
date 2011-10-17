package primes.threads;

import primes.PrimesComputation;

public class PrimesThreads extends PrimesComputation {

	@Override
	public Boolean[] computePrimes(int upto) throws InterruptedException {
		int processorCount = Runtime.getRuntime().availableProcessors();
		PrimesComputationThread[] threads = new PrimesComputationThread[processorCount];

		Boolean[] results = new Boolean[upto];
		for (int i = 0; i < processorCount; i++) {
			threads[i] = new PrimesComputationThread(i * upto / processorCount,
					(i + 1) * upto / processorCount, results);
			threads[i].start();
		}

		for (int i = 0; i < processorCount; i++) {
			threads[i].join();
		}
		return results;
	}

	static class PrimesComputationThread extends Thread {
		private final int from;
		private final int to;
		private final Boolean[] results;

		public PrimesComputationThread(int from, int to, Boolean[] a) {
			this.from = from;
			this.to = to;
			this.results = a;
		}

		@Override
		public void run() {
			for (int x = from; x < to; x++)
				results[x] = isPrime(x);
		}
	}

}
