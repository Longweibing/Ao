package org.multithread;

public class SubtractOne implements Runnable {
	@Override
	public void run() {
		while (true) {
			System.out.println("减一");
		}
	}
}
