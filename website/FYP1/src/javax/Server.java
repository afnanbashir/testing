package javax;

import java.io.File;
import java.io.IOException;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class Server
 */
@WebServlet("/Server")
@MultipartConfig(fileSizeThreshold = 6291456, // 6 MB
maxFileSize = 10485760L, // 10 MB
maxRequestSize = 20971520L) // 20 MB
public class Server extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Server() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username=(String)request.getSession().getAttribute("username");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String filuser=username+".jpg";
		String applicationPath = request.getServletContext().getRealPath("");
		String UPLOAD_DIR="upload";
		String realpath=request.getServletContext().getRealPath("");
		String uploadFilePath = realpath + File.separator + UPLOAD_DIR;
		File uploadFolder = new File(uploadFilePath);
		if (!uploadFolder.exists()) {
			uploadFolder.mkdirs();
		}
		for (Part part : request.getParts()) {
			if (part != null && part.getSize() > 0) {
				String fileName = part.getSubmittedFileName();
				System.out.println(fileName+applicationPath);
				String contentType = part.getContentType();
				
				// allows only JPEG files to be uploaded
				if (!contentType.equalsIgnoreCase("image/jpeg")) {
					continue;
				}
				
				part.write(uploadFilePath + File.separator + filuser);
				break;
			}
		}
		response.sendRedirect("Main.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
