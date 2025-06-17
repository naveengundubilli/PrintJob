package com.codetest.print;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Before;
import org.junit.Test;

import com.print.exception.PrintException;
import com.print.size.PrintSize;
import com.print.size.factory.SizeFactory;

public class AFourSizeTest {

	private SizeFactory sizeFactory;
	private PrintSize size;

	@Before
	public void setUp() {
		sizeFactory = new SizeFactory();
		size = sizeFactory.getSize("A4");
	}

	@Test
	public void testCalculateCostOfPrintWithEmptyTotalPages() {
		PrintJobParameters printPaper = new PrintJobParameters();
		printPaper.setNumberOfPages("");
		printPaper.setNumberOfColorPages("5");
		printPaper.setDoubleSided(true);

		PrintException exception = assertThrows(PrintException.class, () -> {
			size.calculateCostOfPrint(printPaper);
		});
		assertEquals("Total number of pages is required", exception.getMessage());
	}

	@Test
	public void testCalculateCostOfPrintWithEmptyColorPages() {
		PrintJobParameters printPaper = new PrintJobParameters();
		printPaper.setNumberOfPages("10");
		printPaper.setNumberOfColorPages("");
		printPaper.setDoubleSided(true);

		PrintException exception = assertThrows(PrintException.class, () -> {
			size.calculateCostOfPrint(printPaper);
		});
		assertEquals("Number of color pages is required", exception.getMessage());
	}

	@Test
	public void testCalculateCostOfPrintWithEmptyInputs() {
		PrintJobParameters printPaper = new PrintJobParameters();
		printPaper.setNumberOfPages("");
		printPaper.setNumberOfColorPages("");
		printPaper.setDoubleSided(true);

		PrintException exception = assertThrows(PrintException.class, () -> {
			size.calculateCostOfPrint(printPaper);
		});
		assertEquals("Total number of pages is required", exception.getMessage());
	}

	@Test
	public void testCalculateCostOfPrintWithInvalidColorPages() {
		PrintJobParameters printPaper = new PrintJobParameters();
		printPaper.setNumberOfPages("10");
		printPaper.setNumberOfColorPages("15");
		printPaper.setDoubleSided(true);

		PrintException exception = assertThrows(PrintException.class, () -> {
			size.calculateCostOfPrint(printPaper);
		});
		assertEquals("Number of color pages cannot be greater than total pages", exception.getMessage());
	}

	@Test
	public void testCalculateCostOfPrintWithNegativeColorPages() {
		PrintJobParameters printPaper = new PrintJobParameters();
		printPaper.setNumberOfPages("10");
		printPaper.setNumberOfColorPages("-5");
		printPaper.setDoubleSided(true);

		PrintException exception = assertThrows(PrintException.class, () -> {
			size.calculateCostOfPrint(printPaper);
		});
		assertEquals("Number of color pages cannot be negative", exception.getMessage());
	}

	@Test
	public void testCalculateCostOfPrintWithZeroTotalPages() {
		PrintJobParameters printPaper = new PrintJobParameters();
		printPaper.setNumberOfPages("0");
		printPaper.setNumberOfColorPages("0");
		printPaper.setDoubleSided(true);

		PrintException exception = assertThrows(PrintException.class, () -> {
			size.calculateCostOfPrint(printPaper);
		});
		assertEquals("Total number of pages must be greater than 0", exception.getMessage());
	}

	@Test
	public void testCalculateCostOfPrintDoubleSided() throws PrintException {
		PrintJobParameters printPaper = new PrintJobParameters();
		printPaper.setNumberOfPages("10");
		printPaper.setNumberOfColorPages("5");
		printPaper.setDoubleSided(true);

		double cost = size.calculateCostOfPrint(printPaper);
		// 5 BW pages * 0.10 + 5 color pages * 0.20 = 0.50 + 1.00 = 1.50
		assertEquals(1.50, cost, 0.001);
	}

	@Test
	public void testCalculateCostOfPrintSingleSided() throws PrintException {
		PrintJobParameters printPaper = new PrintJobParameters();
		printPaper.setNumberOfPages("10");
		printPaper.setNumberOfColorPages("5");
		printPaper.setDoubleSided(false);

		double cost = size.calculateCostOfPrint(printPaper);
		// 5 BW pages * 0.15 + 5 color pages * 0.25 = 0.75 + 1.25 = 2.00
		assertEquals(2.00, cost, 0.001);
	}

	@Test
	public void testCalculateCostOfPrintAllBW() throws PrintException {
		PrintJobParameters printPaper = new PrintJobParameters();
		printPaper.setNumberOfPages("10");
		printPaper.setNumberOfColorPages("0");
		printPaper.setDoubleSided(true);

		double cost = size.calculateCostOfPrint(printPaper);
		// 10 BW pages * 0.10 = 1.00
		assertEquals(1.00, cost, 0.001);
	}

	@Test
	public void testCalculateCostOfPrintAllColor() throws PrintException {
		PrintJobParameters printPaper = new PrintJobParameters();
		printPaper.setNumberOfPages("10");
		printPaper.setNumberOfColorPages("10");
		printPaper.setDoubleSided(true);

		double cost = size.calculateCostOfPrint(printPaper);
		// 10 color pages * 0.20 = 2.00
		assertEquals(2.00, cost, 0.001);
	}
}
