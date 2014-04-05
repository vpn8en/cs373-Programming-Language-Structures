package a8;

import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentPhilosopher extends Philosopher implements Runnable {

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

	private int n;
	public ConcurrentPhilosopher(int n, ReentrantLock[] forks) {
		super(n,forks);
		this.n = n;
	}


	/**
	 * Attempts to pick up two forks, eat, and relinquish them.
	 * 
	 * @return true if eating succeeded.
	 */
	public boolean eat() {
		if (n % 2 == 0) {//if even pickup left first

			this.leftFork().lock();
			System.out.println(this + " Picking Up Left Fork");
			if (this.leftFork().isHeldByCurrentThread()) {
				System.out.println(this + " Obtained Left Fork");
			} else {
				System.out.println(this + " Couldn't Get Left Fork");
				return false;
			}

			this.rightFork().lock();
			System.out.println(this + " Picking Up Right Fork");
			if (this.rightFork().isHeldByCurrentThread()) {
				System.out.println(this + " Obtained Right Fork");
				System.out.println(this + " Eating");
				System.out.println(this + " Putting Down Left Fork");
				this.leftFork().unlock();
				System.out.println(this + " Putting Down Right Fork");
				this.rightFork().unlock();
			} else {
				System.out.println(this + " Couldn't Get Right Fork");
				return false;
			}
			return true;
		} else { //if odd pickup right first
			this.rightFork().lock();
			System.out.println(this + " Picking Up Right Fork");
			if (this.rightFork().isHeldByCurrentThread()) {
				System.out.println(this + " Obtained Right Fork");
			} else {
				System.out.println(this + " Couldn't Get Right Fork");
				return false;
			}

			this.leftFork().lock();
			System.out.println(this + " Picking Up Left Fork");
			if (this.leftFork().isHeldByCurrentThread()) {
				System.out.println(this + " Obtained Left Fork");
				System.out.println(this + " Eating");
				System.out.println(this + " Putting Down Right Fork");
				this.rightFork().unlock();
				System.out.println(this + " Putting Down Left Fork");
				this.leftFork().unlock();
			} else {
				System.out.println(this + " Couldn't Get Left Fork");
				return false;
			}
			return true;
		}
	}
}
