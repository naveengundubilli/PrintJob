package com.print.test.input;

import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.print.input.reader.CsvFileReader;

public class CsvReaderTest {

	  public void readsRecords() {
    	CsvFileReader csvReader = createCsvReader();
        
    }
	  
    private CsvFileReader createCsvReader() {
        try {
            Path path = Paths.get("src/test/resources","printjobs.csv");
            Reader reader = Files.newBufferedReader(
                path, Charset.forName("UTF-8"));
            return new CsvFileReader(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
    


}
