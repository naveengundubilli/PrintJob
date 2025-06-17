package com.print.size.factory;

import com.print.size.AFiveSize;
import com.print.size.AFourSize;
import com.print.size.PrintSize;

/**
 * Factory Class where object instantiation is based on the size. New sizes
 * support would only need changes in this class
 * 
 * @author NaveenG
 *
 */
public class SizeFactory {

	public PrintSize getSize(String printSize) {
		if (printSize == null) {
			return null;
		}
		
		switch (printSize.toUpperCase()) {
			case "A4":
				return new AFourSize();
			case "A5":
				return new AFiveSize();
			default:
				return null;
		}
	}
}
