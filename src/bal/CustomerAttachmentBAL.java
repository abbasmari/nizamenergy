package bal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.log4j.Logger;

import connection.Connect;

public class CustomerAttachmentBAL {

	final static Logger logger = Logger.getLogger(CustomerAttachmentBAL.class);
		
		public int insertAttachment(HashMap<String, String> map){
			
			int id = -1;
			try (Connection connection = Connect.getConnection();){
				CallableStatement prepareCall = connection.prepareCall("{call insert_customer_attachments(?,?)}");
				prepareCall.setString(1, map.get(""));
				prepareCall.setString(2, map.get("customerCnic"));
		          
				ResultSet resultSet = prepareCall.executeQuery();
				if (resultSet.next()) {
					id = resultSet.getInt("customer_id");
				}
				
			} catch (SQLException e) {
				logger.error("", e);
				e.printStackTrace();
			}	
			return id;
		}

}
