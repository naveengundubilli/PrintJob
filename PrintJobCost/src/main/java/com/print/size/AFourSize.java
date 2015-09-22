package com.print.size;

import com.codetest.print.PrintJobParameters;

public class AFourSize implements PrintSize {

	/**
	 * Method to calculate cost of printing an A4 size
	 * 
	 * @param printPaper
	 * @return printCost
	 */
	public double calculateCostOfPrint(PrintJobParameters printPaper) {
		double printCost = 0;

		if (printPaper.isDoubleSided()) {
			if (!(printPaper.getNumberOfPages().isEmpty() || printPaper
					.getNumberOfColorPages().isEmpty())) {
				printCost = Integer.valueOf(printPaper.getNumberOfPages().trim())
						* .10
						+ Integer.valueOf(printPaper.getNumberOfColorPages().trim())
						* .20;
			}

		} else if (!printPaper.isDoubleSided()) {
			if (!(printPaper.getNumberOfPages().isEmpty() || printPaper
					.getNumberOfColorPages().isEmpty())) {
				printCost = Integer.valueOf(printPaper.getNumberOfPages().trim())
						* .15
						+ Integer.valueOf(printPaper.getNumberOfColorPages().trim())
						* .25;
			}

		} else {
			System.out
					.println("Please provide Double Sided Printing Value for proper Cost Calculation");
		}
		System.out.println();
		return printCost;
	}
}