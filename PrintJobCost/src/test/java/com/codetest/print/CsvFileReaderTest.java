package com.codetest.print;

import org.junit.Test;

import com.print.input.reader.CsvFileReader;

public class CsvFileReaderTest {

	@Test
	public void testCsvFileReader() {
		CsvFileReader csvFileReader = new CsvFileReader();
		String[] inputParam={"C:\\Code Test\\printjobs.csv" ,"A4"};
		csvFileReader.main(inputParam);
	}

}
