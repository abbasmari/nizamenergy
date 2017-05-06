/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bal;


import bean.DistrictBean;
import bean.DistrictSalesmanBean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;




import com.mysql.jdbc.Connection;

import connection.Connect;

import java.util.HashMap;

import org.apache.log4j.Logger;




public class DistrictBAL {

	final static Logger logger = Logger.getLogger(DistrictBAL.class);
	    public static ArrayList<DistrictBean> getDistrict_names() {
    	
        ResultSet rs = null;
        DistrictBean bean = null;
        ArrayList<DistrictBean> list = new ArrayList<>();
        int districtId;
        String district_name;
        try(Connection	con=Connect.getConnection()) {
        
            
            String query = "SELECT district_id, district_name FROM district Order by district_name;";
            PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                districtId = rs.getInt(1);
                district_name = rs.getString(2);
                bean = new DistrictBean();
                bean.setDistrict_id(districtId);
                bean.setDistrict_name(district_name);
                list.add(bean);
            }
           
        } catch (SQLException e) {
        	logger.error("",e);    		
        	e.printStackTrace();
        }
        return list;
    } // end of getting all custome
    
    public static ArrayList<DistrictBean> getDistrictNames() {
    	
        ResultSet rs = null;
        DistrictBean bean = null;
        ArrayList<DistrictBean> list = new ArrayList<>();
        int districtId;
        String district_name;
        try(Connection	con=Connect.getConnection()) {
        	
            String query = "SELECT d.district_id,d.district_name FROM district d JOIN user u ON d.district_id = u.user_district Order by district_name;";
            PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                districtId = rs.getInt(1);
                district_name = rs.getString(2);
                bean = new DistrictBean();
                bean.setDistrict_id(districtId);
                bean.setDistrict_name(district_name);
                list.add(bean);
            }
            
            
        } catch (SQLException e) {
        	logger.error("",e);
            e.printStackTrace();
        }
        return list;
    } // end of getting all custome
public static DistrictBean getDistrictLatLong(int id) {
	
    ResultSet rs = null;
        DistrictBean bean = null;
        int districtId;
        String latitude, longitude;
        try(Connection	con=Connect.getConnection()) {       	
        	
            String query = "SELECT district_id,latitude,longitude FROM district where district_id=?";
            PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                districtId = rs.getInt(1);
                latitude = rs.getString(2);
                longitude = rs.getString(3);
                bean = new DistrictBean();
                bean.setDistrict_id(districtId);
                bean.setLatitude(latitude);
                bean.setLongitude(longitude);
            }
            
        } catch (SQLException e) {
        	logger.error("",e);
            e.printStackTrace();
        }
        return bean;
    } // end of getting all custome

public static String getDistrictName(int id) {
	
    ResultSet rs = null;
     
    String district_name = null;
    try(Connection	con=Connect.getConnection()) {
    	
        String query = "SELECT district_name FROM district where district_id=?";
        PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query);
        stmt.setInt(1, id);
        rs = stmt.executeQuery();
        while (rs.next()) {           
        	district_name = rs.getString(1);           
        }
       
    } catch (SQLException e) {
    	logger.error("",e);
        e.printStackTrace();
    }
    return district_name;
}
	public static ArrayList<DistrictBean> getSalesmanDistrict(){        
		ArrayList<DistrictBean> districtList = new ArrayList<>();
		 try(Connection	con=Connect.getConnection()) {
			ResultSet rs = con.prepareStatement("SELECT DISTINCT (s.salesman_district), d.district_name "
					+ "FROM sold_to st "
					+ "INNER JOIN salesman s ON s.salesman_id = st.salesman_id "
					+ "INNER JOIN district d ON d.district_id = s.salesman_district").executeQuery();
			while (rs.next()) {
				DistrictBean district = new DistrictBean();
				district.setDistrict_id(rs.getInt("s.salesman_district"));
				district.setDistrict_name(rs.getString("d.district_name"));
				districtList.add(district);
			}
			
		} catch (SQLException e) {
			logger.error("",e);
			System.out.println("DistrictBAL.getSalesmanDistrict()");
			e.printStackTrace();
		}
		return districtList;
	}

	public static ArrayList<DistrictBean> getCustomerDistricts(){	
       
		ArrayList<DistrictBean> districts = new ArrayList<DistrictBean>();
		 try(Connection	con=Connect.getConnection()) {
			ResultSet rs = con.prepareStatement("SELECT"
					+ " DISTINCT(d.district_id) AS district_id, "
					+ " d.district_name "
					+ " FROM eligibility e "
				  + " INNER JOIN appliance a "
				    + " ON e.appliance_id = a.appliance_id "
				  + " INNER JOIN salesman s "
				    + " ON e.salesman_id = s.salesman_id "
				  + " INNER JOIN customer cs "
				    + " ON e.customer_id = cs.customer_id "
				  + " INNER JOIN city c "
				  + "   ON cs.customer_city = c.city_id "
				  + " INNER JOIN salary sal "
				    + " ON cs.customer_monthly_income = sal.salary_id "
				  + " INNER JOIN city_district cd "
				    + " ON c.city_id = cd.city_id "
				  + " INNER JOIN district d "
				    + " ON cd.district_id = d.district_id").executeQuery();
			while (rs.next()) {
				DistrictBean district = new DistrictBean();
				district.setDistrict_id(rs.getInt("district_id"));
				district.setDistrict_name(rs.getString("d.district_name"));
				districts.add(district);
			}
			
		} catch (SQLException e) {
			logger.error("",e);
			System.out.println("DistrictBAL.getSalesmanDistrict()");
			e.printStackTrace();
		}
		return districts;
	}
	
	
	 public static ArrayList<DistrictSalesmanBean> getDistrictSalesman(int User_district) {
		
	        ResultSet rs = null;
		 DistrictSalesmanBean bean = null;
	        ArrayList<DistrictSalesmanBean> list = new ArrayList<>();
	        int salesman_id,salesman_district;
	        String district_name,salesman_name;
	        
	        try(Connection	con=Connect.getConnection()) {
	            String query = "SELECT s.salesman_id,s.salesman_district, s.salesman_name, d.district_name"
	            		+" FROM salesman s"
	            		+" JOIN district d ON d.district_id = s.salesman_district WHERE s.salesman_district = "+User_district+"  ";
	            PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query);
	            rs = stmt.executeQuery();
	            while (rs.next()) {
	            	salesman_id = rs.getInt(1);
	            	salesman_district = rs.getInt(2);
	                district_name = rs.getString(4);
	                salesman_name = rs.getString(3);
	                
	                bean = new DistrictSalesmanBean();
	                bean.setSalesman_id(salesman_id);
	                bean.setSalesman_district(salesman_district);
	                bean.setDistrict_name(district_name);
	                bean.setSalesman_name(salesman_name);
	                list.add(bean);
	            }
	            
	        } catch (SQLException e) {
	        	logger.error("",e);
	            e.printStackTrace();
	        }
	        return list;
	    } // end of getting all customer

	public ArrayList<HashMap<String, String>> getAllDistrictBySoldTo(){
		
		System.out.println("DistrictBAL.getAllDistrictBySalesmans()");
		ArrayList<HashMap<String, String>> mapList = new ArrayList<>();
		 try(Connection	con=Connect.getConnection()) {
			ResultSet resultSet = con.prepareCall("{CALL get_all_district_by_sold_to()}").executeQuery();
			while(resultSet.next()){
				HashMap<String, String> map = new HashMap<>();
				map.put("districtId", resultSet.getInt("district_id")+"");
				map.put("districtName", resultSet.getString("district_name"));
				mapList.add(map);				
			}
			
		} catch (SQLException e) {
			logger.error("",e);
			e.printStackTrace();
		}
		return mapList;
	}
	 
    
}
