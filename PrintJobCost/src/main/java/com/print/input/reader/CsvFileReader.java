package com.print.input.reader;

import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import com.codetest.print.PrintJobParameters;
import com.codetest.print.TableBuilder;
import com.print.exception.PrintException;
import com.print.size.PrintSize;
import com.print.size.factory.SizeFactory;

public class CsvFileReader {

	private static final String SEPARATOR = ",";

	public static void main(String[] args) throws FileNotFoundException,
			IOException {

		if (args.length > 0 && !(args[0].isEmpty() && args[1].isEmpty())) {
			createOutput(args[0], args[1]);
		}

	}

	/**
	 * Method to read the file and each line in it to do the calculation
	 * 
	 * @param inputFile
	 * @param printSize
	 * @throws IOException
	 *             ,FileNotFoundException
	 */
	public static void createOutput(String inputFile, String printSize)
			throws IOException, FileNotFoundException {
		File file = new File(inputFile);

		BufferedReader br = new BufferedReader(new FileReader(file));
		String strLine = "";
		StringTokenizer st = null;
		int lineNumber = 0;
		double totalPrintCost = 0;
		TableBuilder tableBuilder = new TableBuilder();
		tableBuilder.addRow("TotalPages", "ColorPages", "DoubleSide", "Cost");
		// read comma separated file line by line
		while ((strLine = br.readLine()) != null) {
			lineNumber++;
			if (strLine.length() > 0) {
				PrintJobParameters printJobParameters = createPrintParameter(strLine);

				SizeFactory sizeFactory = new SizeFactory();
				PrintSize size = sizeFactory.getSize(printSize);
				double perJobCost = 0;
				try {
					perJobCost = size.calculateCostOfPrint(printJobParameters);
				} catch (PrintException e) {
					e.printStackTrace();
				}
				totalPrintCost = totalPrintCost + perJobCost;
				tableBuilder.addRow(printJobParameters.getNumberOfPages(), " "
						+ printJobParameters.getNumberOfColorPages(), " "
						+ String.valueOf(printJobParameters.isDoubleSided()),
						String.valueOf(perJobCost));
			}
		}

		tableBuilder.addRow("Total Cost",
				String.valueOf(Math.round(totalPrintCost * 100d)/ 100d));
		System.out.println(tableBuilder.toString());

	}

	/**
	 * Creates PrintJobParameters object for each line for calculations
	 * 
	 * @param strLine
	 * @return
	 */
	private static PrintJobParameters createPrintParameter(String strLine) {
		PrintJobParameters jobParameters = new PrintJobParameters();

		jobParameters.setNumberOfPages(strLine.split(SEPARATOR)[0]);
		jobParameters.setNumberOfColorPages(strLine.split(SEPARATOR)[1]);
		jobParameters.setDoubleSided(Boolean.parseBoolean(strLine
				.split(SEPARATOR)[2].trim()));
		return jobParameters;
	}

}
