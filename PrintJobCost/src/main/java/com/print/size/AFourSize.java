package com.print.size;

import com.codetest.print.PrintJobParameters;
import com.print.exception.PrintException;

/**
 * Class containing the cost calculation logic for A4 Size
 * 
 * @author NaveenG
 *
 */
public class AFourSize implements PrintSize {

	private static final double SINGLE_SIDED_BW_COST = 0.15;
	private static final double SINGLE_SIDED_COLOR_COST = 0.25;
	private static final double DOUBLE_SIDED_BW_COST = 0.10;
	private static final double DOUBLE_SIDED_COLOR_COST = 0.20;

	/**
	 * Method to calculate cost of printing an A4 size
	 * 
	 * @param printPaper
	 * @return printCost
	 * @throws PrintException if input parameters are invalid
	 */
	public double calculateCostOfPrint(PrintJobParameters printPaper)
			throws PrintException {
		validateInput(printPaper);

		int totalPages = Integer.parseInt(printPaper.getNumberOfPages().trim());
		int colorPages = Integer.parseInt(printPaper.getNumberOfColorPages().trim());
		int bwPages = totalPages - colorPages;

		if (colorPages > totalPages) {
			throw new PrintException("Number of color pages cannot be greater than total pages");
		}

		double printCost;
		if (printPaper.isDoubleSided()) {
			printCost = (bwPages * DOUBLE_SIDED_BW_COST) + (colorPages * DOUBLE_SIDED_COLOR_COST);
		} else {
			printCost = (bwPages * SINGLE_SIDED_BW_COST) + (colorPages * SINGLE_SIDED_COLOR_COST);
		}

		return Math.round(printCost * 100d) / 100d;
	}

	/**
	 * Validates the input parameters
	 * 
	 * @param printPaper
	 * @throws PrintException if validation fails
	 */
	private void validateInput(PrintJobParameters printPaper) throws PrintException {
		if (printPaper == null) {
			throw new PrintException("Print job parameters cannot be null");
		}

		if (printPaper.getNumberOfPages() == null || printPaper.getNumberOfPages().trim().isEmpty()) {
			throw new PrintException("Total number of pages is required");
		}

		if (printPaper.getNumberOfColorPages() == null || printPaper.getNumberOfColorPages().trim().isEmpty()) {
			throw new PrintException("Number of color pages is required");
		}

		try {
			int totalPages = Integer.parseInt(printPaper.getNumberOfPages().trim());
			int colorPages = Integer.parseInt(printPaper.getNumberOfColorPages().trim());

			if (totalPages <= 0) {
				throw new PrintException("Total number of pages must be greater than 0");
			}

			if (colorPages < 0) {
				throw new PrintException("Number of color pages cannot be negative");
			}
		} catch (NumberFormatException e) {
			throw new PrintException("Invalid number format in input parameters");
		}
	}
}
