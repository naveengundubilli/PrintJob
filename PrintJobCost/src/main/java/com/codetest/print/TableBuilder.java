package com.codetest.print;

import java.util.LinkedList;
import java.util.List;

public class TableBuilder {

	List<String[]> rows = new LinkedList<String[]>();
	private static final int PAD_LIMIT = 8192;

	public void addRow(String... cols) {
		rows.add(cols);
	}

	private int[] colWidths() {
		int cols = -1;

		for (String[] row : rows)
			cols = Math.max(cols, row.length);

		int[] widths = new int[cols];

		for (String[] row : rows) {
			for (int colNum = 0; colNum < row.length; colNum++) {
				widths[colNum] = Math.max(widths[colNum], length(row[colNum]));
			}
		}

		return widths;
	}

	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();

		int[] colWidths = colWidths();

		for (String[] row : rows) {
			for (int colNum = 0; colNum < row.length; colNum++) {
				buf.append(rightPad(defaultString(row[colNum]),
						colWidths[colNum]));
				buf.append(' ');
			}

			buf.append('\n');
		}

		return buf.toString();
	}

	/**
	 * Following methods copied from StringUtils class of Apache Library
	 * 
	 * @param cs
	 * @return
	 */
	public static int length(final CharSequence cs) {
		return cs == null ? 0 : cs.length();
	}

	public static String defaultString(final String str) {
		return str == null ? "" : str;
	}

	public static String rightPad(final String str, final int size) {
		return rightPad(str, size, ' ');
	}

	public static String rightPad(final String str, final int size,
			String padStr) {
		if (str == null) {
			return null;
		}
		if (isEmpty(padStr)) {
			padStr = " ";
		}
		final int padLen = padStr.length();
		final int strLen = str.length();
		final int pads = size - strLen;
		if (pads <= 0) {
			return str; // returns original String when possible
		}
		if (padLen == 1 && pads <= PAD_LIMIT) {
			return rightPad(str, size, padStr.charAt(0));
		}

		if (pads == padLen) {
			return str.concat(padStr);
		} else if (pads < padLen) {
			return str.concat(padStr.substring(0, pads));
		} else {
			final char[] padding = new char[pads];
			final char[] padChars = padStr.toCharArray();
			for (int i = 0; i < pads; i++) {
				padding[i] = padChars[i % padLen];
			}
			return str.concat(new String(padding));
		}
	}

	public static String rightPad(final String str, final int size,
			final char padChar) {
		if (str == null) {
			return null;
		}
		final int pads = size - str.length();
		if (pads <= 0) {
			return str; // returns original String when possible
		}
		if (pads > PAD_LIMIT) {
			return rightPad(str, size, String.valueOf(padChar));
		}
		return str.concat(repeat(padChar, pads));
	}

	public static String repeat(final char ch, final int repeat) {
		if (repeat <= 0) {
			return "";
		}
		final char[] buf = new char[repeat];
		for (int i = repeat - 1; i >= 0; i--) {
			buf[i] = ch;
		}
		return new String(buf);
	}

	public static boolean isEmpty(final CharSequence cs) {
		return cs == null || cs.length() == 0;
	}
}
