package bal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import bean.updategrace;
import connection.Connect;

public class CustomerChildrenBAL {
	
	final static Logger logger = Logger.getLogger(CustomerChildrenBAL.class);
	
	public ArrayList<HashMap<String, String>> getCustomerChild(int customerid){
		System.out.println("CustomerChildrenBAL.getCustomerChild()");
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();
		
		try(Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection.prepareCall("{CALL get_customer_family(?)}");
			prepareCall.setInt(1, customerid);
			ResultSet resultSet = prepareCall.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();

			while(resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				for (int i = 1; i <= metaData.getColumnCount(); i++) {
					// System.out.println(metaData.getColumnType(i)+"
					// "+metaData.getColumnTypeName(i));
					if (metaData.getColumnType(i) == java.sql.Types.INTEGER
							|| metaData.getColumnType(i) == java.sql.Types.BIGINT
							|| metaData.getColumnType(i) == java.sql.Types.TINYINT
							|| metaData.getColumnType(i) == java.sql.Types.BOOLEAN
							|| metaData.getColumnType(i) == java.sql.Types.DOUBLE) {
						map.put(metaData.getColumnLabel(i),
								resultSet.getInt(metaData.getColumnLabel(i)) + "");
					} else {
						map.put(metaData.getColumnLabel(i), resultSet.getString(metaData.getColumnLabel(i)));
					}
				}
				maps.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return maps;
	}
	
	public void insertCustomerChildren(ArrayList<HashMap<String, String>> list){
		System.out.println("CustomerChildrenBAL.insertCustomerChildren()");
		
		try(Connection connection = Connect.getConnection()) {
			Statement statement = connection.createStatement();
			StringBuilder stringBuilder = new StringBuilder("INSERT INTO `nizamdb_clear`.`customerchild`"
            +"(`customer_id`,"
            +" `child_name`, "
             +"`child_cnic`, "
             +"`child_mobile`, "
             +"`child_dob`, "
             +"`child_relation`) VALUES ");
			int customerid = 0;
			for(int i = 0; i < list.size(); i++){
				HashMap<String,String> map = list.get(i);
				if(customerid == 0){
					customerid = Integer.parseInt(map.get("customerId"));
				}
				stringBuilder.append("("+ map.get("customerId") + ", '"
				+ map.get("childName") + "', '"
				+ map.get("childCNIC") + "', '"
				+ map.get("childPhone") + "', '"
				+ map.get("childDob") + "', '"
				+ map.get("childRelation") + "') ");
				if(i != list.size() && i < (list.size()-1) ){
					stringBuilder.append(", ");
				}
			}
			System.out.println(stringBuilder.toString());
			statement.execute(stringBuilder.toString());
			
			String udateFormWizard = "UPDATE "
					+"`nizamdb_clear`.`form_wizard`  "
					+"SET "
					+" `form_wizard_step` = '2' "
					+", `updated_date` = CURRENT_TIMESTAMP()  "
					+"WHERE `form_wizard`.`customer_id` = "+customerid+" ;";
			System.out.println(udateFormWizard);
			statement.execute(udateFormWizard);
			
//			if(resultSet.next()){
//				System.out.println(resultSet.getInt(1));
//			}
			
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
	}
	
	public static void main(String arg[]){
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> mapOne = new HashMap<>();
		mapOne.put("customerId", 220+"");
		mapOne.put("childName", "Child One");
		mapOne.put("childCNIC", "44430-145859854-9");
		mapOne.put("childPhone", "9233368985620");
		mapOne.put("childDob", "2015-09-08");
		mapOne.put("childRelation", "Daughter");

		HashMap<String, String> mapTwo = new HashMap<>();
		mapTwo.put("customerId", 220+"");
		mapTwo.put("childName", "Child Two");
		mapTwo.put("childCNIC", "44430-245859854-9");
		mapTwo.put("childPhone", "9233368985620");
		mapTwo.put("childDob", "2015-09-18");
		mapTwo.put("childRelation", "Daughter");

		list.add(mapOne);
		list.add(mapTwo);
//		new CustomerChildrenBAL().insertCustomerChildren(list);
		System.out.println(new CustomerChildrenBAL().getCustomerChild(228));
	}
}
