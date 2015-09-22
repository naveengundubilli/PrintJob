package com.codetest.print;

import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.print.exception.PrintException;
import com.print.size.PrintSize;
import com.print.size.factory.SizeFactory;

public class AFourSizeTest {

	SizeFactory sizeFactory;

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void testCalculateCostOfPrint() {
		PrintJobParameters printPaper = new PrintJobParameters();
		printPaper.setNumberOfPages("");
		printPaper.setNumberOfColorPages("5");
		printPaper.setDoubleSided(true);
		sizeFactory = new SizeFactory();
		PrintSize size = sizeFactory.getSize("A4");

		try {
			size.calculateCostOfPrint(printPaper);
		} catch (PrintException e) {
			assertTrue(e instanceof PrintException);
		}
	}

	@Test
	public void testCalculateCostOfPrint1() {
		PrintJobParameters printPaper = new PrintJobParameters();
		printPaper.setNumberOfPages("10");
		printPaper.setNumberOfColorPages("");
		printPaper.setDoubleSided(true);
		sizeFactory = new SizeFactory();
		PrintSize size = sizeFactory.getSize("A4");

		try {
			size.calculateCostOfPrint(printPaper);
		} catch (PrintException e) {
			assertTrue(e instanceof PrintException);
		}
	}

	@Test
	public void testCalculateCostOfPrint2() {
		PrintJobParameters printPaper = new PrintJobParameters();
		printPaper.setNumberOfPages("");
		printPaper.setNumberOfColorPages("");
		printPaper.setDoubleSided(true);
		sizeFactory = new SizeFactory();
		PrintSize size = sizeFactory.getSize("A4");

		try {
			size.calculateCostOfPrint(printPaper);
		} catch (PrintException e) {
			assertTrue(e instanceof PrintException);
		}
	}
	
	@Test
	public void testCalculateCostOfPrint3() {
		PrintJobParameters printPaper = new PrintJobParameters();
		printPaper.setNumberOfPages("10");
		printPaper.setNumberOfColorPages("5");
		sizeFactory = new SizeFactory();
		PrintSize size = sizeFactory.getSize("A4");

		try {
			size.calculateCostOfPrint(printPaper);
		} catch (PrintException e) {
			assertTrue(e instanceof PrintException);
		}
	}
}
