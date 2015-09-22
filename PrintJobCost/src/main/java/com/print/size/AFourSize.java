package com.print.size;

import com.codetest.print.PrintJobParameters;
import com.print.exception.PrintException;

public class AFourSize implements PrintSize {

	/**
	 * Method to calculate cost of printing an A4 size
	 * 
	 * @param printPaper
	 * @return printCost
	 */
	public double calculateCostOfPrint(PrintJobParameters printPaper)
			throws PrintException {
		double printCost = 0;

		if (!(printPaper.getNumberOfPages().isEmpty() || printPaper
				.getNumberOfColorPages().isEmpty())) {
			int numberOfNonColorPrints = Integer.valueOf(printPaper
					.getNumberOfPages().trim())
					- Integer
							.valueOf(printPaper.getNumberOfColorPages().trim());
			if (printPaper.isDoubleSided()) {

				printCost = Math
						.round((numberOfNonColorPrints * .10 + Integer
								.valueOf(printPaper.getNumberOfColorPages()
										.trim()) * .20) * 100d) / 100d;
			} else if (!printPaper.isDoubleSided()) {

				printCost = Math
						.round((numberOfNonColorPrints * .15 + Integer
								.valueOf(printPaper.getNumberOfColorPages()
										.trim()) * .25) * 100d) / 100d;
			} else {
				throw new PrintException(
						"Please provide Double Sided Printing Value for proper Cost Calculation");
			}

		} else {
			throw new PrintException("Please check input parameters");
		}
		return printCost;
	}
}
