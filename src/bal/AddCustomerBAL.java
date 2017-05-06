package bal;

import bean.BusinessDetails;
import bean.CustomerBean;
import bean.CustomerChild;
import bean.CustomerGuardian;
import bean.EmploymentDetails;
import bean.FamilyDetails;
import bean.GuarantorBean;
import bean.HouseHoldAssets;
import bean.MonthlyExpenses;
import bean.OtherLoanDetails;

import com.mysql.jdbc.Connection;

import connection.Connect;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Part;

import org.apache.log4j.Logger;

public class AddCustomerBAL {

	
	
	final static Logger logger = Logger.getLogger(AddCustomerBAL.class);
	public static int addCustomerInfo(CustomerBean bean) {
        int customerId = 0;
       
        try(Connection con=Connect.getConnection()) {
        	
        	
           PreparedStatement statement = con.prepareStatement("INSERT INTO customer("
           		+ "customer_name," //1
           		+ "customer_cnic,"
           		+ "date_of_birth,"
           		+ "customer_address,"
           		+ "customer_city,"
           		+ "customer_phone,"
           		+ "customer_phone2,"
           		+ "customer_monthly_income,"
           		+ "customer_family_income,"
           		+ "status,"
           		+ "customr_image,"
           		
           		+ "father_name,"
           		+ "mother_name,"
           		+ "email,"
           		+ "gender,"
           		+ "age,"
           		+ "relation_status,"
           		+ "is_educated,"
           		+ "education,"
           		+ "source_of_income,"
           		+ "created_on) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP)",Statement.RETURN_GENERATED_KEYS);
           statement.setString(1, bean.getCustomerName());
           statement.setString(2, bean.getCnicNo());
           statement.setString(3, bean.getDateOfBirth());
           statement.setString(4, bean.getAddress());
           statement.setString(5, bean.getDistrict());
           statement.setString(6, bean.getPhoneNo());
           statement.setString(7, bean.getPhoneNo2());
           statement.setDouble(8, bean.getMonthlyIncome());
           statement.setDouble(9, bean.getFamilyIncome());
           statement.setInt(10, bean.getStatus());
           statement.setBlob(11,bean.getCustomer_pic());
           statement.setString(12, bean.getFatherName());
           statement.setString(13, bean.getMotherName());
           statement.setString(14, bean.getEmail());
           statement.setString(15, bean.getGender());
           statement.setInt(16, bean.getAge());
           statement.setString(17, bean.getRelationStatus());
           statement.setBoolean(18, bean.isEducated());
           statement.setString(19, bean.getEducation());
           statement.setString(20, bean.getSourceOfIncome());
           
           statement.executeUpdate();

           try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
               if (generatedKeys.next()) {
                  customerId= generatedKeys.getInt(1);
               }
               else {
                   throw new SQLException("Creating user failed, no ID obtained.");
               }
           }
           
           
        } catch (Exception e) {
        	logger.error("",e);
            e.printStackTrace();
        }
        return customerId;
    }
	
	
	
	public static int addCustomerChild(CustomerChild bean) {
        int customerId = 0;
        
        try(Connection con=Connect.getConnection()) {
        	
           PreparedStatement statement = con.prepareStatement("INSERT INTO customerchild(customer_id,"
           		+ "child_name,"
           		+ "child_relation,"
           		+ "child_mobile,"
           		+ "child_dob,"
           		+ "child_cnic,"
           		+ "child_workdetail,"
           		+ "child_employ_name,"
           		+ "childemploy_adress,"
           		+ "child_business,"
           		+ "childbusiness_adress,"
           		+ "child_monthlyincome) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
           statement.setInt(1, bean.getCustomer_id());
           statement.setString(2, bean.getChild_name());
           statement.setString(3, bean.getChild_relation());
           statement.setString(4, bean.getChild_mobile());
           statement.setString(5, bean.getChild_dob());
           statement.setString(6, bean.getChild_cnic());
           statement.setString(7, bean.getChild_workdetail());
           statement.setString(8, bean.getChild_employ_name());
           statement.setString(9, bean.getChildemploy_adress());
           statement.setString(10,bean.getChild_business());
           statement.setString(11, bean.getChildbusiness_adress());
           statement.setDouble(12, bean.getChild_monthlyincome());
           statement.executeUpdate();

           try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
               if (generatedKeys.next()) {
                  customerId= generatedKeys.getInt(1);
               }
               else {
                   throw new SQLException("Creating user failed, no ID obtained.");
               }
           }
           
            
          
        } catch (Exception e) {
        	logger.error("",e);
            e.printStackTrace();
        }
        return customerId;
    }
	
	
	
	
	public static int addGuardianDetails(CustomerGuardian bean) {
		Statement st = null;
		
        int row = 0;
        try (Connection con=Connect.getConnection()){
            String query = "INSERT INTO customer_guardian(customer_id,guardian_name,phone_no,customer_family_size) VALUES("+ bean.getCustomerId() +",'"+ bean.getGuardianName() +"','"+ bean.getPhone_no()+"','" +bean.getFamilyMember()+"');";
            
            st = con.createStatement();
            row = st.executeUpdate(query);
            if (row > 0) {
                System.out.println("Data is inserted");
            } else {
                System.out.println("Data is not inserted");
            }
            
           
            
        } catch (Exception e) {
        	logger.error("",e);
            e.printStackTrace();
        }
        return row;
    }
	
	public static int addFamilyDetails(FamilyDetails bean) {
		Statement st = null;
		 
        int row = 0;
        try (Connection con=Connect.getConnection()){
            String query = "INSERT INTO family_details(customer_id, member_name, relation, is_professional, occupation) VALUES("+ bean.getCustomerId() +",'"+ bean.getMemberName() +"','"+ bean.getRelation() +"',"+ bean.isProfessional()+",'"+bean.getOccupation()+"');";
           
            st = con.createStatement();
            row = st.executeUpdate(query);
            if (row > 0) {
                System.out.println("Data is inserted");
            } else {
                System.out.println("Data is not inserted");
            }
            
          
            
        } catch (Exception e) {
        	logger.error("",e);
            e.printStackTrace();
        }
        return row;
    }
	
	public static int addHouseHoldAssetDetails(HouseHoldAssets bean) {
		Statement st = null;
		
        int row = 0;
        try (Connection con=Connect.getConnection()){
            String query = "INSERT INTO home_hold_assets(customer_id,"
            		+ "home,"
            		+ "car,"
            		+ "motorcycle,"
            		+ "washing_machine,"
            		+ "tv,"
            		+ "computer,"
            		+ "fridge,"
            		+ "others) VALUES("+ bean.getCustomerId()+","+ bean.isHasHome() +","+ bean.isHasCar() +","+ bean.isHasBike() +","+ bean.isHasWashingMachine() +","+ bean.isHasTv() +","+ bean.isHasComputer() +","+ bean.isHasFridge() +",'"+ bean.getOtherAssets() +"');";
           
            st = con.createStatement();
            row = st.executeUpdate(query);
            if (row > 0) {
                System.out.println("Data is inserted");
            } else {
                System.out.println("Data is not inserted");
            }
            
          
            
        } catch (Exception e) {
        	logger.error("",e);
            e.printStackTrace();
        }
        return row;
    }
	
    
	public static int addBusinessDetails(BusinessDetails bean) {
		Statement st = null;
		 
        int row = 0;
        try (Connection con=Connect.getConnection()){
        	
            String query = "INSERT INTO business_info(customer_id,business_name,businees_type,owned,period,current_place_period,workers,business_place,phone_no) VALUES("+ bean.getCustomerId() +",'"+ bean.getBusinessName() +"','"+ bean.getBusinessType() +"','"+ bean.getOwnedOrPartner() +"','"+ bean.getPeriod() +"','"+ bean.getCurrentPlacePeriod()+"','"+ bean.getWorkers() +"','"+ bean.getBusinessPlace() +"','"+ bean.getOrgPhone() +"');";
          
            st = con.createStatement();
            row = st.executeUpdate(query);
            if (row > 0) {
                System.out.println("Data is inserted");
            } else {
                System.out.println("Data is not inserted");
            }
            
           
            
        } catch (Exception e) {
        	logger.error("",e);
            e.printStackTrace();
        }
        return row;
    }
	
	public static int addEmployementDetails(EmploymentDetails bean) {
		Statement st = null;
		
        int row = 0;
        try (Connection con=Connect.getConnection()){
            String query = "INSERT INTO employment_details(customer_id, organisation_name, job_position,job_period,salary,office_phone_no, address) Value("+ bean.getCustomerId() +",'"+ bean.getOrganisationName() +"','"+ bean.getJobPosition()+"','"+ bean.getJobPeriod() +"',"+ bean.getSalary() +",'"+ bean.getPhoneNo() +"','"+ bean.getOrgAddress() +"');";
         
            st = con.createStatement();
            System.err.println("Error: " + query);
            row = st.executeUpdate(query);
            if (row > 0) {
                System.out.println("Data is inserted");
            } else {
                System.out.println("Data is not inserted");
            }
            
            
            
        } catch (Exception e) {
        	logger.error("",e);
            e.printStackTrace();
        }
        return row;
    }
	
	public static int addHomeExpenseDetails(MonthlyExpenses bean) {
		Statement st = null;
		
        int row = 0;
        try (Connection con=Connect.getConnection()){
            String query = "INSERT INTO monthly_home_expenses(customer_id,"
            		+ "grid_electricity,"
            		+ "generator,"
            		+ "ups,"
            		+ "solar,"
            		+ "electricityusage,"
            		+ "electricityexpense,"
            		+ "majorexpensedescription,"
            		+ "majorexpenseamount,"
            		+ "totalexpense) VALUES("+ bean.getCustomerId() +",'"+ bean.getElectricity() +"','"+ bean.getGenerator() +"','"+ bean.getUps() +"','"+ bean.getSolar() +"','"+ bean.getElectricityusage() +"','"+ bean.getElectricityexpense() +"','"+ bean.getMajorexpensedescription() +"','"+ bean.getMajorexpenseamount() +"','"+ bean.getTotalexpense()+"');";
          
            st = con.createStatement();
            row = st.executeUpdate(query);
            if (row > 0) {
                System.out.println("Data is inserted");
            } else {
                System.out.println("Data is not inserted");
            }
            
            
           
            
        } catch (Exception e) {
        	logger.error("",e);
            e.printStackTrace();
        }
        return row;
    }
	
	public static int addOtherLoanDetails(OtherLoanDetails bean) {
		Statement st = null;
		 
        int row = 0;
        try (Connection con=Connect.getConnection()){
            String query = "INSERT INTO other_loan_details(customer_id,is_loaned,loan_donner,loan_amount,loan_period,monthly_installment,remaining_payment,is_bank_account,bank_name) VALUES("+ bean.getCustomerId() +","+ bean.isOtherLoan() +",'"+ bean.getLoanDonner() +"','"+ bean.getLoanAmount() +"','"+ bean.getLoanPeriod() +"','"+ bean.getMonthlyInstallment() +"','"+ bean.getRemainingPayment() +"',"+ bean.isBankAccount() +",'"+ bean.getBankName() +"');";
          
            st = con.createStatement();
            row = st.executeUpdate(query);
            if (row > 0) {
                System.out.println("Data is inserted");
            } else {
                System.out.println("Data is not inserted");
            }
            
           
           
        } catch (Exception e) {
        	logger.error("",e);
            e.printStackTrace();
        }
        return row;
    }
	
	public static int addGuarantor(GuarantorBean bean) {
		Statement st = null;
		 
        int row = 0;
        
        try (Connection con=Connect.getConnection()){
            String query = "INSERT INTO customer_guarantor(customer_id,gguarantorName,gguarantorFather,gguarantorCnic,gguarantorDob,gRelationToCustomer,gmarritalStatus,gguarantorPhone,gguarantorIncome,gCompanyName,gDesidition,gPhoneNumber,gCompanyAddess,gBusinessName,gBusnessType,gBusnessNumber,gBusinessAddress,guarantertype) VALUES("+ bean.getCustomerId() +",'"+ bean.getGguarantorName() +"','"+ bean.getGguarantorFather() +"','"+ bean.getGguarantorCnic() +"','"+ bean.getGguarantorDob() +"','"+ bean.getgRelationToCustomer() +"','"+ bean.getGmarritalStatus() +"','"+ bean.getGguarantorPhone() +"','"+ bean.getGguarantorIncome() +"','"+ bean.getgCompanyName() +"','"+ bean.getgDesidition() +"','"+ bean.getgPhoneNumber() +"','"+ bean.getgCompanyAddess() +"','"+ bean.getgBusinessName() +"','"+ bean.getgBusnessType() +"','"+ bean.getgBusnessNumber() +"','"+ bean.getgBusinessAddress() +"','"+ bean.getGuarantertype() +"');";
        	
         
            st = con.createStatement();
            row = st.executeUpdate(query);
            if (row > 0) {
                System.out.println("Data is inserted");
            } else {
                System.out.println("Data is not inserted");
            }
           
        } catch (Exception e) {
        	logger.error("",e);
            e.printStackTrace();
        }
        return row;
    }
	
	public static int SaveAppliance2(String gsm_no, String name, double price, int status) throws SQLException {
		Statement st = null;
		 
		
        int appliance_id = 0;
        try (Connection con=Connect.getConnection()){
        	
            st = con.createStatement();
            st.executeUpdate("INSERT INTO appliance(appliance_name,appliance_price,appliance_image,appliance_status,status) VALUES ('" + name + "','" + price + "','" + null + "','" + status + "',"+ 0 +")", st.RETURN_GENERATED_KEYS);
            ResultSet rs = st.getGeneratedKeys();
            if(rs.next())
            {
            	appliance_id = rs.getInt(1);
            }
            
           
            
        } catch(Exception e) {
        	logger.error("",e);
        	e.printStackTrace();
        	System.out.print(e);
        }
        return appliance_id;
    }
	
	public static int saveEligibilty2(int customer_id, int appliance_id, int salesman_id, double downpayment, int scheme, double installment) throws SQLException {
        int rows = 0;

        Statement st = null;
        try (Connection con=Connect.getConnection()){
        st = con.createStatement();
            rows = st.executeUpdate("INSERT INTO eligibility(customer_id,"
            		+ "appliance_id,"
            		+ "salesman_id,"
            		+ "date,"
            		+ "instalment,"																
            		+ "installment_scheme,"
            		+ "down_payment,"
            		+ "status) VALUES (" + customer_id + "," + appliance_id + "," + salesman_id + ", CURRENT_TIMESTAMP ," + installment + "," + scheme + "," + downpayment + "," + 0 +");");
            
        }catch (Exception e) {
        	logger.error("",e);
			e.printStackTrace();
			System.out.println(e);
		}
        
        return rows;
    }
	
	public static String getFileName(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String fileName = cd.substring(cd.indexOf('=') + 1).trim()
						.replace("\"", "");
				return fileName.substring(fileName.lastIndexOf('/') + 1)
						.substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
			}
		}
		return null;
	}
	
	


	public static boolean customerInfo(CustomerBean customerBean,
			/*ArrayList<CustomerChild> cc_list, CustomerGuardian guardianBean,*/
			HouseHoldAssets assetBean, BusinessDetails businessDetails,
			EmploymentDetails employmentDetails, MonthlyExpenses monthlyBean,
			OtherLoanDetails loanDetails, ArrayList<GuarantorBean> glist,
			HashMap<String, String> mapappliance,
			HashMap<String, String> mapelig,List<Part> parts) throws SQLException {
		//Connection con = connection.Connect.getConnection();
		boolean err = true;
		Statement st = null;
		 ResultSet rs = null;
		 try(Connection con=Connect.getConnection()){
			 
		
		 try {
			con.setAutoCommit(false);
			
			//================ Add Customer Open ===========================//
			PreparedStatement statement = con.prepareStatement("INSERT INTO customer("
	           		+ "customer_name," 
	           		+ "customer_cnic,"
	           		+ "date_of_birth,"
	           		+ "customer_address,"
	           		+ "customer_city,"
	           		+ "customer_phone,"
	           		+ "customer_phone2,"
	           		+ "customer_monthly_income,"
	           		+ "customer_family_income,"
	           		+ "status,"
	           		+ "customr_image,"	           		
	           		+ "father_name,"
	           		+ "mother_name,"
	           		+ "email,"
	           		+ "gender,"
	           		+ "age,"
	           		+ "relation_status,"
	           		+ "is_educated,"
	           		+ "education,"
	           		+ "source_of_income,"
	           		+ "created_on) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP)",Statement.RETURN_GENERATED_KEYS);
	           statement.setString(1, customerBean.getCustomerName());
	           statement.setString(2, customerBean.getCnicNo());
	           statement.setString(3, customerBean.getDateOfBirth());
	           statement.setString(4, customerBean.getAddress());
	           statement.setString(5, customerBean.getDistrict());
	           statement.setString(6, customerBean.getPhoneNo());
	           statement.setString(7, customerBean.getPhoneNo2());
	           statement.setDouble(8, customerBean.getMonthlyIncome());
	           statement.setDouble(9, customerBean.getFamilyIncome());
	           statement.setInt(10, customerBean.getStatus());
	           statement.setBlob(11,customerBean.getCustomer_pic());
	           statement.setString(12, customerBean.getFatherName());
	           statement.setString(13, customerBean.getMotherName());
	           statement.setString(14, customerBean.getEmail());
	           statement.setString(15, customerBean.getGender());
	           statement.setInt(16, customerBean.getAge());
	           statement.setString(17, customerBean.getRelationStatus());
	           statement.setBoolean(18, customerBean.isEducated());
	           statement.setString(19, customerBean.getEducation());
	           statement.setString(20, customerBean.getSourceOfIncome());
	           
	           statement.executeUpdate();
	           int customerId = 0;
	           try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
	               if (generatedKeys.next()) {
	                  customerId= generatedKeys.getInt(1);
	               }
	               else {
	                   throw new SQLException("Creating user failed, no ID obtained.");
	               }
	           }
	           
	           
			
			//================ Add Customer End ===========================//
	           if(customerId>0){
	        	 //================ Add Customer Child Open ===========================//
	        	   for (Part part : parts) {
	        		   if(part!=null){
		        		   	InputStream inputStream=part.getInputStream();
		        	    	statement = con.prepareStatement("INSERT INTO customer_attachments(image,customer_id) VALUES (?,?)");
		        			statement.setBlob(1, inputStream);
		        			statement.setInt(2, customerId);
		        			int row = statement.executeUpdate();
		        			System.out.println(row +" Image Added");
	        		   }
	        			
	        	   }
	        	   
	        	   /*for (CustomerChild cc_bean : cc_list) {
	       			cc_bean.setCustomer_id(customerId);
//	       			int cc = AddCustomerBAL.addCustomerChild(cc_bean);
	       			
	       			statement = con.prepareStatement("INSERT INTO customerchild(customer_id,"
	       	           		+ "child_name,"
	       	           		+ "child_relation,"
	       	           		+ "child_mobile,"
	       	           		+ "child_dob,"
	       	           		+ "child_cnic,"
	       	           		+ "child_workdetail,"
	       	           		+ "child_employ_name,"
	       	           		+ "childemploy_adress,"
	       	           		+ "child_business,"
	       	           		+ "childbusiness_adress,"
	       	           		+ "child_monthlyincome) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
	       	           statement.setInt(1, customerId);
	       	           statement.setString(2, cc_bean.getChild_name());
	       	           statement.setString(3, cc_bean.getChild_relation());
	       	           statement.setString(4, cc_bean.getChild_mobile());
	       	           statement.setString(5, cc_bean.getChild_dob());
	       	           statement.setString(6, cc_bean.getChild_cnic());
	       	           statement.setString(7, cc_bean.getChild_workdetail());
	       	           statement.setString(8, cc_bean.getChild_employ_name());
	       	           statement.setString(9, cc_bean.getChildemploy_adress());
	       	           statement.setString(10, cc_bean.getChild_business());
	       	           statement.setString(11, cc_bean.getChildbusiness_adress());
	       	           statement.setDouble(12, cc_bean.getChild_monthlyincome());
	       	           statement.executeUpdate();
	       		}*/
	        	 //================ Add Customer Child End ===========================//
	        	   
	        	
	        	   //================ Add guardianBean Open ==========================//
	        	   
	        	   /*String query = "INSERT INTO customer_guardian(customer_id, "
	        	   		+ "guardian_name,phone_no,customer_family_size) "
	        	   		+ "VALUES("+ customerId +","
	        	   		+ "'"+ guardianBean.getGuardianName() +"',"
	        	   		+ "'"+ guardianBean.getPhone_no()+"',"
	        	   		+ "'" +guardianBean.getFamilyMember()+"');";
	           	
	               st = con.createStatement();
	               st.executeUpdate(query);*/
	              
	        	   //================ Add guardianBean End ===========================//
	               
	               
	               //================ Add home_hold_assets Open ===========================//
	               String query1 = "INSERT INTO home_hold_assets(customer_id,"
	               		+ "home,"
	               		+ "car,"
	               		+ "motorcycle,"
	               		+ "washing_machine,"
	               		+ "tv,"
	               		+ "computer,"
	               		+ "fridge,"
	               		+ "others) VALUES("+ customerId+","
	               				+ ""+ assetBean.isHasHome() +","
	               				+ ""+ assetBean.isHasCar() +","
	               				+ ""+ assetBean.isHasBike() +","
	               				+ ""+ assetBean.isHasWashingMachine() +","
	               				+ ""+ assetBean.isHasTv() +","+ assetBean.isHasComputer() +","
	               				+ ""+ assetBean.isHasFridge() +","
	               				+ "'"+ assetBean.getOtherAssets() +"');";
	               
	               		st = con.createStatement();
	               		st.executeUpdate(query1);

	               //================ Add home_hold_assets End ===========================//
	               		
	               		
	               		//================ Add business_info Open ===========================//
	               		
	               	 String query2 = "INSERT INTO business_info(customer_id,business_name,businees_type, "
	               	 		+ "owned,period,current_place_period,workers,business_place,phone_no) VALUES("
	               	 		+ ""+ customerId +","
	               	 		+ "'"+ businessDetails.getBusinessName() +"',"
	               	 		+ "'"+ businessDetails.getBusinessType() +"',"
	               	 		+ "'"+ businessDetails.getOwnedOrPartner() +"',"
	               	 		+ "'"+ businessDetails.getPeriod() +"',"
	               	 		+ "'"+ businessDetails.getCurrentPlacePeriod()+"',"
	               	 		+ "'"+ businessDetails.getWorkers() +"',"
	               	 		+ "'"+ businessDetails.getBusinessPlace() +"',"
	               	 		+ "'"+ businessDetails.getOrgPhone() +"');";
	               	 
	                 st = con.createStatement();
	                 st.executeUpdate(query2);
	               		
	               		//================ Add business_info End ===========================//
	                 
	                 
	                 //================ Add employmentDetails Open ===========================//
	                 
	                 String query3 = "INSERT INTO employment_details(customer_id, organisation_name, "
	                 		+ "job_position,job_period,salary,office_phone_no, address) Value("
	                 		+ ""+ customerId +",'"+ employmentDetails.getOrganisationName() +"',"
	                 		+ "'"+ employmentDetails.getJobPosition()+"','"+ employmentDetails.getJobPeriod() +"',"
	                 		+ ""+ employmentDetails.getSalary() +",'"+ employmentDetails.getPhoneNo() +"',"
	                 		+ "'"+ employmentDetails.getOrgAddress() +"');";
	             
	                 st = con.createStatement();
	                 st.executeUpdate(query3);
	                 //================ Add employmentDetails End ===========================//
	                 
	                 
	                 //================ Add employmentDetails End ===========================//
	                 
	                 String query4 = "INSERT INTO monthly_home_expenses(customer_id,"
	                 		+ "grid_electricity,"
	                 		+ "generator,"
	                 		+ "ups,"
	                 		+ "solar,"
	                 		+ "electricityusage,"
	                 		+ "electricityexpense,"
	                 		+ "majorexpensedescription,"
	                 		+ "majorexpenseamount,"
	                 		+ "totalexpense) VALUES("+ customerId +","
	                 				+ "'"+ monthlyBean.getElectricity() +"',"
	                 				+ "'"+ monthlyBean.getGenerator() +"',"
	                 				+ "'"+ monthlyBean.getUps() +"',"
	                 				+ "'"+ monthlyBean.getSolar() +"',"
	                 				+ "'"+ monthlyBean.getElectricityusage() +"',"
	                 				+ "'"+ monthlyBean.getElectricityexpense() +"',"
	                 				+ "'"+ monthlyBean.getMajorexpensedescription() +"',"
	                 				+ "'"+ monthlyBean.getMajorexpenseamount() +"',"
	                 				+ "'"+ monthlyBean.getTotalexpense()+"');";
	             	
	                 st = con.createStatement();
	                 st.executeUpdate(query4);
	                 //================ Add employmentDetails End ===========================//
	                 
	                 //================ other_loan_details Open ===========================//
	                 
	                 String query5 = "INSERT INTO other_loan_details(customer_id,is_loaned,loan_donner,loan_amount, "
	                 			+ "loan_period,monthly_installment,remaining_payment,is_bank_account,bank_name) VALUES("
	                 			+ ""+ customerId +","+ loanDetails.isOtherLoan() +","
	                 			+ "'"+ loanDetails.getLoanDonner() +"','"+ loanDetails.getLoanAmount() +"',"
	                 			+ "'"+ loanDetails.getLoanPeriod() +"','"+ loanDetails.getMonthlyInstallment() +"',"
	                 			+ "'"+ loanDetails.getRemainingPayment() +"',"+ loanDetails.isBankAccount() +","
	                 			+ "'"+ loanDetails.getBankName() +"');";
	             	
	                 st = con.createStatement();
	                 st.executeUpdate(query5);

	                 //================ other_loan_details End ===========================//
	                 
	                 
	                 //================ other_loan_details End ===========================//
	                 
	                 for (GuarantorBean guarantorBean : glist) {
//	         			guarantorBean.setCustomerId(customerId);
//	         			int gc = AddCustomerBAL.addGuarantor(guarantorBean);
	                	 
	                	 String query6 = "INSERT INTO customer_guarantor(customer_id,gguarantorName,gguarantorFather, "
	                	 			+ "gguarantorCnic,gguarantorDob,gRelationToCustomer,gmarritalStatus, "
	                	 			+ "gguarantorPhone,gguarantorIncome,gCompanyName,gDesidition,gPhoneNumber, "
	                	 			+ "gCompanyAddess,gBusinessName,gBusnessType,gBusnessNumber,gBusinessAddress "
	                	 			+ ",guarantertype) VALUES("
	                	 			+ ""+ customerId +","
	                	 			+ "'"+ guarantorBean.getGguarantorName() +"',"
	                	 			+ "'"+ guarantorBean.getGguarantorFather() +"',"
	                	 			+ "'"+ guarantorBean.getGguarantorCnic() +"',"
	                	 			+ "'"+ guarantorBean.getGguarantorDob() +"',"
	                	 			+ "'"+ guarantorBean.getgRelationToCustomer() +"',"
	                	 			+ "'"+ guarantorBean.getGmarritalStatus() +"',"
	                	 			+ "'"+ guarantorBean.getGguarantorPhone() +"',"
	                	 			+ "'"+ guarantorBean.getGguarantorIncome() +"',"
	                	 			+ "'"+ guarantorBean.getgCompanyName() +"',"
	                	 			+ "'"+ guarantorBean.getgDesidition() +"',"
	                	 			+ "'"+ guarantorBean.getgPhoneNumber() +"',"
	                	 			+ "'"+ guarantorBean.getgCompanyAddess() +"',"
	                	 			+ "'"+ guarantorBean.getgBusinessName() +"',"
	                	 			+ "'"+ guarantorBean.getgBusnessType() +"',"
	                	 			+ "'"+ guarantorBean.getgBusnessNumber() +"',"
	                	 			+ "'"+ guarantorBean.getgBusinessAddress() +"',"
	                	 			+ "'"+ guarantorBean.getGuarantertype() +"');";
	                 	
	                     st = con.createStatement();
	                     st.executeUpdate(query6);
	                	 
	         		}

	                 //================ other_loan_details End ===========================//
	                 
	                 
	                 //================ appliance Open ===========================//
	                 st = con.createStatement();
	                 st.executeUpdate("INSERT INTO appliance(appliance_GSMno,appliance_name, "
	                 				+ "appliance_status,appliance_price) VALUES ('" + mapappliance.get("gsm_no") + "',"
	                 				+ "'" + mapappliance.get("appname") + "',"
	                 				+ "'" + mapappliance.get("state") + "',"
	                 				+ "'" + mapappliance.get("price") + "')", st.RETURN_GENERATED_KEYS);
	                 ResultSet rs1 = st.getGeneratedKeys();
	                 int appliance_id = 0;
	                 if(rs1.next())
	                 {
	                 	appliance_id = rs1.getInt(1);
	                 }
	                 //================ appliance End ===========================//
	                 
	                 
	                 //================ eligibility Open ===========================//
	                 
	                 statement.executeUpdate("INSERT INTO eligibility(customer_id,"
	                 		+ "appliance_id,"
	                 		+ "salesman_id,"
	                 		+ "date,"
	                 		+ "instalment,"																
	                 		+ "installment_scheme,"
	                 		+ "down_payment,"
	                 		+ "status) VALUES (" + customerId + ","
	                 		+ "" + appliance_id + ","
	                 		+ "" + mapelig.get("salesmanId") + ", "
	                 		+ "CURRENT_TIMESTAMP ,"
	                 		+ "" + mapelig.get("installment") + ","
	                 		+ "" + mapelig.get("scheme") + ","
	                 		+ "" + mapelig.get("downPayment") + "," + 0 +");");

	                 //================ eligibility End ===========================//
	        	   
	           }else{
	        	   con.rollback();
	        	   return false;
	           }
	           
	           con.commit();
	           return true;
	           
		}catch(Exception e){
			
			try {
				con.rollback();
				err = false;
			} catch (SQLException e1) {
//				e1.getStackTrace()"RollBack Catch";
				e1.printStackTrace();
			}
			err = false;
			e.printStackTrace();
			logger.error("",e);
		}
		
		/*try{
		
		int customerId = AddCustomerBAL.addCustomerInfo(customerBean);
		for (CustomerChild cc_bean : cc_list) {
			cc_bean.setCustomer_id(customerId);
			int cc = AddCustomerBAL.addCustomerChild(cc_bean);
		}
		
		guardianBean.setCustomerId(customerId);
		int g = AddCustomerBAL.addGuardianDetails(guardianBean);
			
			assetBean.setCustomerId(customerId);
		int a = AddCustomerBAL.addHouseHoldAssetDetails(assetBean);
			
			businessDetails.setCustomerId(customerId);
		int b = AddCustomerBAL.addBusinessDetails(businessDetails);
			
			employmentDetails.setCustomerId(customerId);
		int emp = AddCustomerBAL.addEmployementDetails(employmentDetails);
			
			monthlyBean.setCustomerId(customerId);
		int exp = AddCustomerBAL.addHomeExpenseDetails(monthlyBean);
			
			loanDetails.setCustomerId(customerId);
		int l = AddCustomerBAL.addOtherLoanDetails(loanDetails);
			
		
		for (GuarantorBean guarantorBean : glist) {
			guarantorBean.setCustomerId(customerId);
			int gc = AddCustomerBAL.addGuarantor(guarantorBean);
		}
		
		
		applianceId = AddCustomerBAL.SaveAppliance2(gsm_no, appname, price, 0);
		
		AddCustomerBAL.saveEligibilty2(customerId, applianceId, salesmanId, downPayment, scheme, installment);
		
		}catch(Exception ex){
			
			ex.getStackTrace();
		}*/
		 }
		return false;
	}



	

}
