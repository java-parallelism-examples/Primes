package primes;

import util.Timer;

public class Driver {
	final static int UP_TO = 2000000;
	static long referenceRuntime = 0;
	
	public static void main(String[] args) throws Exception {
		// Sequential
		PrimesComputation sequential = new primes.sequential.PrimesSequential();
		test("Sequential version", sequential, UP_TO, true);
	
//		// Threads
//		PrimesComputation threads = new primes.threads.PrimesThreads();
//		test("Threads version", threads);
//		
//		// Balanced threads
//		PrimesComputation balandedThreads = new primes.threads.balanced.PrimesBalanced();
//		test("Balanced threads version", balandedThreads);
//		
		// Fixed thread pool version
//		PrimesComputation pool = new primes.threads.pool.PrimesPool();
//		test("Fixed thread pool version", pool);
//		
//		// Cached Thread pool version
//		PrimesComputation poolCached = new primes.threads.pool.PrimesPool();
//		test("Cached thread pool version", poolCached);
//		
//		// Fork-join version
//		PrimesComputation forkjoin = new primes.forkjoin.PrimesForkJoin();
//		test("Fork-join version", forkjoin);
//		
//		// Parallel array version
//		PrimesComputation parallel = new primes.parallel.PrimesParallel();
//		test("ParallelArray version", parallel);
		
		// Live version
//		primes.live.PrimesLive live = new primes.live.PrimesLive();
//		test("Live version", live);
////		
//		// Live version with sync
//		primes.live.sync.PrimesLiveSync liveSync = new primes.live.sync.PrimesLiveSync();
//		test("Live version with sync", liveSync);
//		
//		// Live version with atomic
//		primes.live.atomic.PrimesLiveAtomic liveAtomic = new primes.live.atomic.PrimesLiveAtomic();
//		test("Live version with atomic", liveAtomic);
	}

	private static void test(String version, PrimesComputation p) throws Exception {
		test(version, p, UP_TO, false);
	}
	
	private static void test(String version, PrimesComputation p, int upto, boolean resetReference) throws Exception {
		// warm-up
		p.computePrimes(upto);
		p.computePrimes(upto);
		
		Timer.start();
		Boolean[] results = p.computePrimes(upto);
		Timer.stop();
		System.out.println(version);
		System.out.println("-----------------------------");
		long numberOfPrimes = 0;
		for(boolean isP:results)
			if(isP)
				numberOfPrimes ++;
		System.out.println("#      primes: "+numberOfPrimes);
		if(p instanceof LiveResults)
			System.out.println("# live primes: "+((LiveResults<?>) p).resultsCount());
		Timer.log("Time: ");
		if(resetReference || referenceRuntime == 0)
			referenceRuntime = Timer.getRuntime();
		else
			System.out.printf("Speed-up: %.2f\n",referenceRuntime/1.0/Timer.getRuntime());
		System.out.println();
	}
}