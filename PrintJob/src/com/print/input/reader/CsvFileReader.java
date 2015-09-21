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

import com.print.size.PrintJobParameters;
import com.print.size.PrintSize;
import com.print.size.SizeFactory;

public class CsvFileReader {

	private static final String SEPARATOR = ";";

	private final Reader source;

	public CsvFileReader(Reader source) {
		this.source = source;
	}

	public static void main(String[] args) {
		if (args.length > 0) {
			File file = new File(args[0]);
			try {

				BufferedReader br = new BufferedReader(new FileReader(file));
				String strLine = "";
				StringTokenizer st = null;
				int lineNumber = 0;
				double totalPrintCost = 0;

				// read comma separated file line by line
				while ((strLine = br.readLine()) != null) {
					lineNumber++;

					PrintJobParameters printPaper = createPrintParameter(strLine);

					SizeFactory sizeFactory = new SizeFactory();
					PrintSize size = sizeFactory.getSize(args[1]);
					size.calculateCostOfPrint(printPaper);
				}

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private static PrintJobParameters createPrintParameter(String strLine) {
		PrintJobParameters jobParameters = new PrintJobParameters();

		jobParameters.setNumberOfPages(strLine.split(",")[0]);
		jobParameters.setNumberOfColorPages(strLine.split(",")[1]);
		jobParameters.setDoubleSided(Boolean.valueOf(strLine.split(",")[2]));
		return jobParameters;
	}

	public List<String> readHeader() {
		try (BufferedReader reader = new BufferedReader(source)) {
			return reader.lines().findFirst()
					.map(line -> Arrays.asList(line.split(SEPARATOR))).get();
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	public List<List<String>> readRecords() {
		try (BufferedReader reader = new BufferedReader(source)) {
			return reader.lines()
					.map(line -> Arrays.asList(line.split(SEPARATOR)))
					.collect(Collectors.toList());
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}
}
