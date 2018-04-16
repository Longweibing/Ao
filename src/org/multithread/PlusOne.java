package org.multithread;

public class PlusOne implements Runnable {

	@Override
	public void run() {
		while (true) {
			System.out.println("加一");
		}
	}

}
