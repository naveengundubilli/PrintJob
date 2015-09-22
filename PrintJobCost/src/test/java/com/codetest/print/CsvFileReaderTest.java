package com.codetest.print;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import com.print.exception.PrintException;
import com.print.input.reader.CsvFileReader;

public class CsvFileReaderTest {

	@Test
	public void testCsvFileReader() {

		try {
			CsvFileReader.createOutput("C:\\Code Test\\printjobs.csv", "A4");
		} catch (IOException e) {

		}

	}

	@Test
	public void testCsvFileReader2() {
		try {
			CsvFileReader.createOutput("C:\\Code Test\\printjob.csv", "A4");
		} catch (IOException e) {
			assertTrue(e instanceof FileNotFoundException);
		}
	}

}
