package solarpaygo.app.wsdl;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;

import javax.imageio.ImageIO;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import sun.misc.BASE64Decoder;

import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

@WebService(targetNamespace = "http://wsdl.app.solarpaygo")
@SOAPBinding(style = Style.RPC)
public class Attachments {

	@Resource
	private WebServiceContext context;

	@WebMethod(action = "uploadImage")
	public String uploadImage(
			@WebParam(name = "customerId", mode = WebParam.Mode.IN) int customerId,
			@WebParam(name = "imageId", mode = WebParam.Mode.IN) int imageId,
			@WebParam(name = "tableName", mode = WebParam.Mode.IN) String tableName,
			@WebParam(name = "imageType", mode = WebParam.Mode.IN) int imageType,
			@WebParam(name = "imageData", mode = WebParam.Mode.IN) String imageData,
			@WebParam(name = "imageLabel", mode = WebParam.Mode.IN) String imageLabel,
			@WebParam(name = "assetId", mode = WebParam.Mode.IN) int assetId) {

		String status = "";

		if (imageId != 0 && customerId != 0 && imageType != 0
				&& imageData != null && tableName != null) {

			System.err.println("imageId " + imageId + " customerId "
					+ customerId + " imageType " + imageType + " tableName "
					+ tableName);

			System.err.println("servletContext ok b");

			// String realPath = "E:/Images"
			// + File.separator + customerId;

			ServletContext servletContext = (ServletContext) context
					.getMessageContext().get(MessageContext.SERVLET_CONTEXT);

			String realPath = servletContext.getRealPath("/Images")
					+ File.separator + customerId;

			realPath += File.separator + imageType;
			System.out.println(realPath);
			File file = new File(realPath);
			if (!file.exists()) {
				System.out.println(realPath + " Directory Created");
				file.mkdirs();
			}

			if (imageType == 102 || imageType == 103) {

				try {
					convertAndSaveImage(imageData, realPath + File.separator
							+ imageLabel + ".jpg");
					status = "ok:" + imageId + ":" + tableName;
				} catch (Exception e) {
					status = "error:" + e.toString();
					e.printStackTrace();
				}

				System.out.println("All work done");
			} else if (imageType == 101) {
				if (assetId != 0) {

					realPath += File.separator + assetId;
					System.out.println(realPath);
					file = new File(realPath);
					if (!file.exists()) {
						System.out.println(realPath + " Directory Created");
						file.mkdirs();
					}

					try {
						convertAndSaveImage(imageData, realPath
								+ File.separator + imageId + ".jpg");
						status = "ok:" + imageId + ":" + tableName;
					} catch (Exception e) {
						status = "error:" + e.toString();
						e.printStackTrace();
					}
					System.out.println("All work done");
				} else {
					status = "error:assetId can't be null or 0";
				}

			} else {
				status = "error:Image Type not found";
			}

		} else {
			status = "error:Parameters not found";
		}
		return status;

	}

	public void convertAndSaveImage(String base64String, String fileName)
			throws Exception {

		BASE64Decoder decoder = new BASE64Decoder();
		byte[] decodedBytes = decoder.decodeBuffer(base64String);
		BufferedImage image = ImageIO.read(new ByteArrayInputStream(
				decodedBytes));
		if (image == null) {
			System.err.println("Image Null");
		}
		File f = new File(fileName);
		// write the image
		ImageIO.write(image, "jpg", f);
	}

}
