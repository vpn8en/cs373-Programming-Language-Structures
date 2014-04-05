package a8;

import java.util.concurrent.locks.ReentrantLock;

public class Deadlock {

	/** Runs the philosophers in separate threads, often resulting in a deadlock. */
	public static void main(String[] args) {		
		ReentrantLock[] forks = new ReentrantLock[5];
		for (int i = 0; i < forks.length; i++) {
			forks[i] = new ReentrantLock();
		}
		for (int i = 0; i < forks.length; i++) {
			new Thread(new ConcurrentPhilosopher(i, forks)).start();
		}
	}

}
