package com.codetest.print;

/**
 * Pojo class for the print job values provided in each row
 * 
 * @author NaveenG
 *
 */

public class PrintJobParameters {

	private String numberOfPages;
	private String numberOfColorPages;
	private boolean isDoubleSided;

	public String getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(String numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public String getNumberOfColorPages() {
		return numberOfColorPages;
	}

	public void setNumberOfColorPages(String numberOfColorPages) {
		this.numberOfColorPages = numberOfColorPages;
	}

	public boolean isDoubleSided() {
		return isDoubleSided;
	}

	public void setDoubleSided(boolean isDoubleSided) {
		this.isDoubleSided = isDoubleSided;
	}

}
