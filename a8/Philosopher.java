package a8;

import java.util.concurrent.locks.ReentrantLock;

public class Philosopher implements Runnable {

	/** Runs the philosophers serially. */
	public static void main(String[] args) {
		ReentrantLock[] forks = new ReentrantLock[5];
		for (int i = 0; i < forks.length; i++) {
			forks[i] = new ReentrantLock();
		}
		for (int i = 0; i < forks.length; i++) {
			new Philosopher(i, forks).run();
		}
	}

	/** The set of forks. */
	private ReentrantLock[] forks;

	/** The philosopher's number, useful for determining which fork to pick up. */
	private int n;

	/**
	 * @param n This philosopher's index.
	 * @param forks The set of forks (should be the same for all philosophers).
	 */
	public Philosopher(int n, ReentrantLock[] forks) {
		this.n = n;
		this.forks = forks;
	}

	/**
	 * Attempts to pick up two forks, eat, and relinquish them.
	 * 
	 * @return true if eating succeeded.
	 */
	public boolean eat() {
		synchronized (leftFork()) {
			System.out.println(this + " picking up left fork");
			synchronized (rightFork()) {
				System.out.println(this + " picking up right fork");
				System.out.println(this + " eating");
				System.out.println(this + " putting down right fork");
			}
			System.out.println(this + " putting down left fork");
		}
		return true;
	}

	@Override
	public String toString() {
		return "Philosopher " + n;
	}

	/** Returns this philosopher's left fork. */
	public ReentrantLock leftFork() {
		return forks[n];
	}

	/** Returns this philosopher's right fork. */
	public ReentrantLock rightFork() {
		return forks[(n + 1) % forks.length];
	}

	@Override
	/** Thinks and sleeps five times. */
	public void run() {
		try {
			for (int i = 0; i < 5; i++) {
				think();
				Thread.sleep(100);
				while (!eat()) {
					Thread.sleep(100);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/** Thinks. */
	public void think() {
		System.out.println(this + " thinking");
	}
	
	public int getN(){
		return this.n;
	}

}
