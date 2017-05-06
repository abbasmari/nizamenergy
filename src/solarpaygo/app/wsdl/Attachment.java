package solarpaygo.app.wsdl;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface Attachment {

	@WebMethod(action = "uploadImage")
	public String uploadImage(
			@WebParam(name = "customerId", mode = WebParam.Mode.IN) int customerId,
			@WebParam(name = "imageId", mode = WebParam.Mode.IN) int imageId,
			@WebParam(name = "tableName", mode = WebParam.Mode.IN) String tableName,
			@WebParam(name = "imageType", mode = WebParam.Mode.IN) int imageType,
			@WebParam(name = "imageData", mode = WebParam.Mode.IN) String imageData,
			@WebParam(name = "imageLabel", mode = WebParam.Mode.IN) String imageLabel,
			@WebParam(name = "assetId", mode = WebParam.Mode.IN) int assetId);

}
