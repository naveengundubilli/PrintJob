package com.codetest.print;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.print.exception.PrintException;
import com.print.input.reader.CsvFileReader;

public class CsvFileReaderTest {

	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();

	private File validCsvFile;
	private File invalidCsvFile;

	@Before
	public void setUp() throws IOException {
		// Create a valid CSV file
		validCsvFile = tempFolder.newFile("valid.csv");
		try (FileWriter writer = new FileWriter(validCsvFile)) {
			writer.write("10,5,true\n");
			writer.write("20,10,false\n");
			writer.write("15,0,true\n");
		}

		// Create an invalid CSV file
		invalidCsvFile = tempFolder.newFile("invalid.csv");
		try (FileWriter writer = new FileWriter(invalidCsvFile)) {
			writer.write("10,15,true\n"); // Invalid: color pages > total pages
			writer.write("0,0,true\n");   // Invalid: zero pages
			writer.write("-5,2,true\n");  // Invalid: negative pages
		}
	}

	@Test
	public void testCsvFileReaderWithValidFile() throws IOException, PrintException {
		// Test with valid file
		CsvFileReader.createOutput(validCsvFile.getAbsolutePath(), "A4");
		// Note: This test mainly verifies that no exceptions are thrown
		// In a real application, we might want to capture and verify the output
	}

	@Test
	public void testCsvFileReaderWithNonExistentFile() {
		assertThrows(FileNotFoundException.class, () -> {
			CsvFileReader.createOutput("nonexistent.csv", "A4");
		});
	}

	@Test
	public void testCsvFileReaderWithInvalidFile() {
		PrintException exception = assertThrows(PrintException.class, () -> {
			CsvFileReader.createOutput(invalidCsvFile.getAbsolutePath(), "A4");
		});
		assertTrue(exception.getMessage().contains("Number of color pages cannot be greater than total pages"));
	}

	@Test
	public void testCsvFileReaderWithInvalidSize() {
		PrintException exception = assertThrows(PrintException.class, () -> {
			CsvFileReader.createOutput(validCsvFile.getAbsolutePath(), "INVALID");
		});
		assertTrue(exception.getMessage().contains("Unsupported print size"));
	}

	@Test
	public void testCsvFileReaderWithEmptyFile() throws IOException {
		File emptyFile = tempFolder.newFile("empty.csv");
		assertThrows(PrintException.class, () -> {
			CsvFileReader.createOutput(emptyFile.getAbsolutePath(), "A4");
		});
	}
}
