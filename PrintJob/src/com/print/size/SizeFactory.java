package com.print.size;

public class SizeFactory {
	
	public PrintSize getSize(String printSize) {
		if (printSize == null) {
			return null;
		}
		if (printSize.equalsIgnoreCase("A4")) {
			return new AFourSize();
		} else if (printSize.equalsIgnoreCase("A5")) {
			System.out.println("Need implementation");
		}

		return null;
	}
}
