package servlets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WriteData
 */
public class WriteData extends HttpServlet{
		private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WriteData() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String attribute = (String) request.getParameter("data");
		String message = "";
		try {
			int data = Integer.parseInt(attribute);
			if (data >= 0 && data <= 100) {
				ServletContext servletContext = request.getServletContext();
				@SuppressWarnings("unchecked")
				List<Integer> mainData = (List<Integer>) servletContext
						.getAttribute("Data");
				synchronized (mainData) {
					mainData.add(data);
					String csv = Utility.getCSV(mainData);
					writeToFile(csv);
					message = data + " added !";
				}
				// servletContext.setAttribute("Data", mainData);
			}
			else {
				message = attribute + " is not a number between 1 and 100";
			}
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
			message = attribute + " is not a number between 1 and 100";
		} catch (Exception ex) {
			ex.printStackTrace();
			message = ex.getMessage();
		}
		request.getSession().setAttribute("message", message);
		response.sendRedirect("input.jsp");

	}

	private void writeToFile(String mainData) {
		File file = new File(Utility.FILE_NAME);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
		}
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(file);
			System.out.println(file.getAbsolutePath());
			fileOutputStream.write(mainData.getBytes());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
