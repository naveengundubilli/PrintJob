package com.print.size;

public class AFourSize implements PrintSize {

	/**
	 * Method to calculate cost of printing an A4 size
	 * 
	 * @param printPaper
	 * @return printCost
	 */
	@Override
	public double calculateCostOfPrint(PrintJobParameters printPaper) {
		double printCost = 0;

		if (printPaper.isDoubleSided()) {
			if (!(printPaper.getNumberOfPages().isEmpty() || printPaper
					.getNumberOfColorPages().isEmpty())) {
				printCost = Integer.valueOf(printPaper.getNumberOfPages())
						* .10
						+ Integer.valueOf(printPaper.getNumberOfColorPages())
						* .20;
			}

		} else if (!printPaper.isDoubleSided()) {
			if (!(printPaper.getNumberOfPages().isEmpty() || printPaper
					.getNumberOfColorPages().isEmpty())) {
				printCost = Integer.valueOf(printPaper.getNumberOfPages())
						* .15
						+ Integer.valueOf(printPaper.getNumberOfColorPages())
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
