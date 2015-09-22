package com.print.size;

import com.codetest.print.PrintJobParameters;
import com.print.exception.PrintException;

/**
 * PrintSize
 * 
 * @author NaveenG
 *
 */
public interface PrintSize {

	public double calculateCostOfPrint(PrintJobParameters printPape)
			throws PrintException;

}
