package com.print.size;

import com.codetest.print.PrintJobParameters;
import com.print.exception.PrintException;

/**
 * Class containing the cost calculation logic for A5 Size
 * 
 * @author NaveenG
 *
 */
public class AFiveSize implements PrintSize {

    /**
     * Method to calculate cost of printing an A5 size
     * A5 size costs are 70% of A4 size costs
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
                    - Integer.valueOf(printPaper.getNumberOfColorPages().trim());
            
            if (printPaper.isDoubleSided()) {
                // A5 double-sided: 70% of A4 double-sided costs
                printCost = Math.round((numberOfNonColorPrints * 0.07 + Integer
                        .valueOf(printPaper.getNumberOfColorPages().trim()) * 0.14) * 100d) / 100d;
            } else if (!printPaper.isDoubleSided()) {
                // A5 single-sided: 70% of A4 single-sided costs
                printCost = Math.round((numberOfNonColorPrints * 0.105 + Integer
                        .valueOf(printPaper.getNumberOfColorPages().trim()) * 0.175) * 100d) / 100d;
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