package primes.live;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import primes.LiveResults;
import primes.PrimesComputation;

public class PrimesLive extends PrimesComputation implements LiveResults<Integer[]> {
	
	Integer[] livePrimes;
	Integer noLivePrimes;
	
	@Override
	public Boolean[] computePrimes(int upto) throws InterruptedException {
		int processorCount = Runtime.getRuntime().availableProcessors();
		ExecutorService threadPool = Executors.newFixedThreadPool(processorCount);
		
		livePrimes = new Integer[upto];
		noLivePrimes = 0;

		int noTasks = 100;
		Boolean[] results = new Boolean[upto];
		for (int i = 0; i < noTasks; i++) {
			threadPool.execute(new PrimesComputationTask(i * results.length
					/ noTasks, (i + 1) * results.length / noTasks, results));
		}
		threadPool.shutdown();
		threadPool.awaitTermination(10, TimeUnit.SECONDS);
		return results;
	}

	class PrimesComputationTask implements Runnable {
		private final int from;
		private final int to;
		private final Boolean[] a;

		public PrimesComputationTask(int from, int to, Boolean[] a) {
			this.from = from;
			this.to = to;
			this.a = a;
		}

		@Override
		public void run() {
			for (int x = from; x < to; x++) {
				a[x] = isPrime(x);
				if(a[x])
					livePrimes[noLivePrimes++] = x;
			}
		}
	}

	public String getNoResults() {
		return noLivePrimes.toString();
	}

	@Override
	public Integer[] getResults() {
		return livePrimes;
	}

	@Override
	public int resultsCount() {
		return noLivePrimes;
	}

}
