package org.multithread;

public class HelloWorld {
	public static void main(String[] args) {
		PlusOne plusOne = new PlusOne();
		SubtractOne subtractOne = new SubtractOne();
		
		Thread thread = new Thread(plusOne);
		Thread thread2 = new Thread(subtractOne);
		thread.start();
		thread2.start();

	}
}
