package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Utility {

	public static final String FILE_NAME = "datafile.txt";

	public static String getCSV(List<Integer> list) {
		StringBuffer sb = new StringBuffer();
		for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext();) {
			Integer integer = iterator.next();
			sb.append(integer + ",");
		}
		sb.delete(sb.length() - 1, sb.length());
		return sb.toString();
	}

	public static List<Integer> csvToList(String csv) {
		String[] split = csv.split(",");
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < split.length; i++) {
			String string = split[i];
			int parseInt;
			try {
				parseInt = Integer.parseInt(string);
				list.add(parseInt);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return list;

	}

	public static String readStream(InputStream is) throws IOException {
		StringBuilder sb = new StringBuilder(512);
		Reader r = new InputStreamReader(is, "UTF-8");
		int c = 0;
		while ((c = r.read()) != -1) {
			sb.append((char) c);
		}
		return sb.toString();
	}

	// public static void main(String[] args) {
	// List<Integer> lst = new ArrayList<Integer>();
	// lst.add(3);
	// lst.add(4);
	// lst.add(6);
	// lst.add(8);
	// lst.add(39);
	// lst.add(30);
	// System.out.println(getCSV(lst));
	// System.out.println(csvToList("3,4,6,8,39,30"));
	// }

}
