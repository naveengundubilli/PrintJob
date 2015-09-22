package com.print.input.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import com.codetest.print.PrintJobParameters;
import com.codetest.print.TableBuilder;
import com.print.size.PrintSize;
import com.print.size.factory.SizeFactory;

public class CsvFileReader {

	private static final String SEPARATOR = ",";

	public static void main(String[] args) {
		if (args.length > 0 && !(args[0].isEmpty() && args[1].isEmpty())) {
			createOutput(args[0], args[1]);
		}

	}

	/**
	 * 
	 * @param inputFile
	 * @param printSize
	 */
	private static void createOutput(String inputFile, String printSize) {
		File file = new File(inputFile);
		try {

			BufferedReader br = new BufferedReader(new FileReader(file));
			String strLine = "";
			StringTokenizer st = null;
			int lineNumber = 0;
			double totalPrintCost = 0;
			TableBuilder tableBuilder = new TableBuilder();
			tableBuilder.addRow("TotalPages", "ColorPages", "DoubleSide",
					"Cost");
			// read comma separated file line by line
			while ((strLine = br.readLine()) != null) {
				lineNumber++;
				if (strLine.length() > 0) {
					PrintJobParameters printJobParameters = createPrintParameter(strLine);

					SizeFactory sizeFactory = new SizeFactory();
					PrintSize size = sizeFactory.getSize(printSize);
					double perJobCost=size.calculateCostOfPrint(printJobParameters);
					totalPrintCost = totalPrintCost
							+ perJobCost;
					tableBuilder.addRow(printJobParameters.getNumberOfPages(),
							" "+printJobParameters.getNumberOfColorPages(),
							" "+String.valueOf(printJobParameters.isDoubleSided()),String.valueOf(perJobCost));
				}
			}

			tableBuilder.addRow("Total Cost", String.valueOf(totalPrintCost));
			System.out.println(tableBuilder.toString());

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static PrintJobParameters createPrintParameter(String strLine) {
		PrintJobParameters jobParameters = new PrintJobParameters();

		jobParameters.setNumberOfPages(strLine.split(SEPARATOR)[0]);
		jobParameters.setNumberOfColorPages(strLine.split(SEPARATOR)[1]);
		jobParameters
				.setDoubleSided(Boolean.valueOf(strLine.split(SEPARATOR)[2]));
		return jobParameters;
	}

}
