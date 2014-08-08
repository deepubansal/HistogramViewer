package listeners;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import servlets.Utility;

public class HistogramContextListener implements ServletContextListener {

	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		File file = new File(Utility.FILE_NAME);
		String str = null;
		if (file.exists()) {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(file);
				str = Utility.readStream(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
			finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		List<Integer> list = new ArrayList<Integer>();
		if (str != null	&& str.trim().length() > 0) {
			list = Utility.csvToList(str);
		}
		List<Integer> synchronizedList = Collections
				.synchronizedList(list);
		arg0.getServletContext().setAttribute("Data", synchronizedList);
	}

}
