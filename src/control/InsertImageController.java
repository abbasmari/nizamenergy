package control;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.FileUploadException;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import bal.ImageBAL;
import bean.UserBean;

/**
 * Servlet implementation class InsertImageController
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet("/InsertImageController")
public class InsertImageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(InsertImageController.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertImageController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		if (ServletFileUpload.isMultipartContent(request)) {
//
//			HttpSession session = request.getSession();
//
//			UserBean ubean = (UserBean) session.getAttribute("email");
//			logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
//					+ request.getRemoteAddr());
//
//			try {
//				DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
//				File dir = new File("temperory");
//
//				if (!dir.isDirectory())
//					dir.mkdir();
//
//				diskFileItemFactory.setRepository(dir);
//				ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
//				Map<String, List<FileItem>> parseParameterMap = null;
//				for (String string : parseParameterMap.keySet()) {
//					List<FileItem> list = parseParameterMap.get(string);
//					for (FileItem fileItem : list) {
//						if (fileItem.getContentType() != null && !fileItem.getContentType().isEmpty()) {
//							int id = new ImageBAL().insertImage(fileItem.getInputStream(), fileItem.getContentType(),
//									fileItem.getName());
//							if (id != 0) {
//								JSONObject json = new JSONObject();
//								json.put("imageId", id);
//								json.put("contentType", fileItem.getContentType());
//								json.put("filename", fileItem.getName());
//								response.getWriter().println(json);
//								// response.getWriter().println(json);
//
//							}
//						}
//					}
//				}
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}

	}

}