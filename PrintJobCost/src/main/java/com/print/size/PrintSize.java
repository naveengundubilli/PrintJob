package com.print.size;

import com.codetest.print.PrintJobParameters;
import com.print.exception.PrintException;

public interface PrintSize {
	
	public double calculateCostOfPrint(PrintJobParameters printPape) throws PrintException; 

}
 	