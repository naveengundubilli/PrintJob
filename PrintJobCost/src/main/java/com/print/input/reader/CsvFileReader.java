package com.print.input.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringTokenizer;

import com.codetest.print.PrintJobParameters;
import com.codetest.print.TableBuilder;
import com.print.exception.PrintException;
import com.print.size.PrintSize;
import com.print.size.factory.SizeFactory;

/**
 * Class to read CSV file provided
 * 
 * @author NaveenG
 *
 */
public class CsvFileReader {

	private static final String SEPARATOR = ",";

	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("Usage: java CsvFileReader <inputFile> <printSize>");
			System.exit(1);
		}

		try {
			createOutput(args[0], args[1]);
		} catch (FileNotFoundException e) {
			System.err.println("Error: Input file not found: " + e.getMessage());
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Error reading file: " + e.getMessage());
			System.exit(1);
		} catch (PrintException e) {
			System.err.println("Error processing print job: " + e.getMessage());
			System.exit(1);
		}
	}

	/**
	 * Method to read the file and each line in it to do the calculation
	 * 
	 * @param inputFile
	 * @param printSize
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws PrintException
	 */
	public static void createOutput(String inputFile, String printSize)
			throws IOException, FileNotFoundException, PrintException {
		Path filePath = Paths.get(inputFile);
		if (!Files.exists(filePath)) {
			throw new FileNotFoundException("Input file not found: " + inputFile);
		}

		try (BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()))) {
			String strLine;
			int lineNumber = 0;
			int validLines = 0;
			double totalPrintCost = 0;
			TableBuilder tableBuilder = new TableBuilder();
			tableBuilder.addRow("TotalPages", "ColorPages", "DoubleSide", "Cost");

			while ((strLine = br.readLine()) != null) {
				lineNumber++;
				if (strLine.trim().isEmpty()) {
					continue;
				}

				try {
					PrintJobParameters printJobParameters = createPrintParameter(strLine);
					SizeFactory sizeFactory = new SizeFactory();
					PrintSize size = sizeFactory.getSize(printSize);
					
					if (size == null) {
						throw new PrintException("Unsupported print size: " + printSize);
					}

					double perJobCost = size.calculateCostOfPrint(printJobParameters);
					totalPrintCost += perJobCost;
					validLines++;
					
					tableBuilder.addRow(
						printJobParameters.getNumberOfPages(),
						printJobParameters.getNumberOfColorPages(),
						String.valueOf(printJobParameters.isDoubleSided()),
						String.format("%.2f", perJobCost)
					);
				} catch (Exception e) {
					throw new PrintException("Error processing line " + lineNumber + ": " + e.getMessage());
				}
			}

			if (validLines == 0) {
				throw new PrintException("No valid print jobs found in the file");
			}

			tableBuilder.addRow("Total Cost", String.format("%.2f", totalPrintCost));
			System.out.println(tableBuilder.toString());
		}
	}

	/**
	 * Creates PrintJobParameters object for each line for calculations
	 * 
	 * @param strLine
	 * @return PrintJobParameters
	 * @throws PrintException if the line format is invalid
	 */
	private static PrintJobParameters createPrintParameter(String strLine) throws PrintException {
		String[] parts = strLine.split(SEPARATOR);
		if (parts.length != 3) {
			throw new PrintException("Invalid line format. Expected: totalPages,colorPages,doubleSided");
		}

		PrintJobParameters jobParameters = new PrintJobParameters();
		jobParameters.setNumberOfPages(parts[0].trim());
		jobParameters.setNumberOfColorPages(parts[1].trim());
		
		try {
			jobParameters.setDoubleSided(Boolean.parseBoolean(parts[2].trim()));
		} catch (Exception e) {
			throw new PrintException("Invalid doubleSided value: " + parts[2]);
		}
		
		return jobParameters;
	}
}
