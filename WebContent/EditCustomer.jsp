<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<%@page import="bal.StatusUpdateBAL"%>
<%@page import="bal.InventoryBAL"%>
<%@page import="bean.InventoryBean"%>
<%@page import="bean.EligibilityBean2"%>
<%@page import="bean.SalesManBean"%>
<%@page import="bean.UserBean"%>
<%@page import="bean.Salesman"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bal.SalesmanBAL"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>AdminLTE 2 | Dashboard</title>
        <!-- Bootstrap 3.3.4 -->
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />    
        <!-- FontAwesome 4.3.0 -->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Ionicons 2.0.0 -->
        <link href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet" type="text/css" />    
        <!-- Theme style -->
        <link href="dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
        <!-- AdminLTE Skins. Choose a skin from the css/skins 
             folder instead of downloading all of them to reduce the load. -->
        <link href="dist/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css" />
        <!-- iCheck -->
        <link href="plugins/iCheck/flat/blue.css" rel="stylesheet" type="text/css" />
        <!-- Morris chart -->
        <link href="plugins/morris/morris.css" rel="stylesheet" type="text/css" />
        <!-- jvectormap -->
        <link href="plugins/jvectormap/jquery-jvectormap-1.2.2.css" rel="stylesheet" type="text/css" />
        <!-- Date Picker -->
        <link href="plugins/datepicker/datepicker3.css" rel="stylesheet" type="text/css" />
        <!-- Daterange picker -->
        <link href="plugins/daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />
        <!-- bootstrap wysihtml5 - text editor -->
        <link href="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css" rel="stylesheet" type="text/css" />

    </head>
    <body>
        <%
            UserBean bean = (UserBean) session.getAttribute("email");
            ArrayList<InventoryBean> inventory = InventoryBAL.getAppliances();
            EligibilityBean2 elig = (EligibilityBean2) request.getAttribute("bean");
            String appName = StatusUpdateBAL.getApplianceName(elig.getApplianceId());

        %>
        <div class="contianer">
            <div><h2>Add Customer Information</h2></div>

            <form class="form-horizontal" role="form" action="Msg" method="POST">
                <div class="form-group">
                    <label class="control-label col-sm-2" >Name:</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="firstname" name="firstname" value="<%= elig.getCustomerName()%>" placeholder="Enter name">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2">CNIC:</label>
                    <div class="col-sm-3">          
                        <input type="text" class="form-control" id="cnic" name="cnic" value="<%= elig.getCnic()%>" placeholder="Enter CNIC">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="pwd">Address:</label>
                    <div class="col-sm-3">          
                        <input type="text" class="form-control" id="phone" name="address" value="<%= elig.getAddress()%>" placeholder="Enter address">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="pwd">Date Of Birth:</label>
                    <div class="col-sm-3">    
                        <input type="date" class="form-control" id="phone" name="dob" value="<%= elig.getDateOfBirth()%>" placeholder="Enter Date of Birth">
                    </div> 
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="pwd">Family Size:</label>
                    <div class="col-sm-3">          
                        <input type="text" class="form-control" id="address" name="family" value="<%= elig.getFamilySize()%>" placeholder="Enter Family Members">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="pwd">Phone Number:</label>
                    <div class="col-sm-3">          
                        <input type="text" class="form-control" id="address" name="phone" value="<%= elig.getCustomer_number()%>"  placeholder="Enter Phone Number">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="pwd">Monthly Income:</label>
                    <div class="col-sm-3">          
                        <input type="text" class="form-control" id="area" name="income" value="<%= elig.getMonthlyIncome()%>" placeholder="Enter income">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="pwd">Family income:</label>
                    <div class="col-sm-3">          
                        <input type="text"  name="fincome" class="form-control" value="<%= elig.getFamilyIncome()%>" placeholder="Enter Family income">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="pwd">Occupation:</label>
                    <div class="col-sm-3">          
                        <input type="text"  name="occupation" class="form-control" value="<%= elig.getOccupation()%>" placeholder="Enter occupation" >
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="pwd">Down Payment:</label>
                    <div class="col-sm-3">          
                        <input type="text"  name="downPayment" class="form-control" value="<%= elig.getDownpayment()%>" placeholder="Enter Down Payement Amount" >
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="pwd">Scheme:</label>
                    <div class="col-sm-3">          
                        <input type="text"  name="scheme" class="form-control" value="<%= elig.getTotalInstallments()%>" placeholder="Enter Scheme" >
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="pwd">Appliance Name:</label>
                    <div class="col-sm-3">     
                        <select class="form-control" id="appname" name="appname">
                            <%                                for (int i = 0; i < inventory.size(); i++) {
                                    String name = inventory.get(i).getName();
                                    if (!name.equals(appName)) {
                            %>
                            <option  value="<%= name%>"> <%= inventory.get(i).getName()%></option>
                            <% } %>
                            <% }%>
                        </select> 
<!--                        <input type="text"  name="appname" class="form-control" value="<%= elig.getApplianceName()%>" placeholder="Enter Appliance Name" >-->
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="pwd">Price:</label>
                    <div class="col-sm-3">          
                        <input type="text"  name="price" class="form-control" value="<%= elig.getAppliancePrice()%>" placeholder="Enter Price" >
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="pwd">Product id:</label>
                    <div class="col-sm-3">          
                        <input type="text"  name="proId" class="form-control" value="<%= elig.getProductId()%>" placeholder="Enter Product_id" >
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="pwd">Appliance GSM</label>
                    <div class="col-sm-3">          
                        <input type="text"  name="gsm" class="form-control" value="<%= elig.getAppliance_number()%>" placeholder="Enter GSM No" >
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="pwd">Salesman Phone</label>
                    <div class="col-sm-3">          
                        <input type="text" class="form-control" name="distri" value="<%= elig.getSalesmanName()%>">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="pwd">Payment Type </label>
                    <div class="col-sm-3">            
                        <input type="text" name="payment_type" class="form-control" placeholder="Payment Type" value="<%= elig.getPaymentMethod()%>" >
                    </div>
                </div>
                <input type="hidden" class="form-control"  name="id" value="<%= elig.getSalesManNumber()%>" >
                <input type="hidden" class="form-control"  name="customerId" value="<%= elig.getCustomerId()%>" >
                <input type="hidden" class="form-control"  name="district" value="<%= elig.getSalesManNumber()%>" >
                <input type="hidden" class="form-control"  name="appId" value="<%= elig.getApplianceId()%>" >
                <input type="hidden" class="form-control"  name="elId" value="<%= elig.getElegibilityId()%>" >
<!--                 <div class="form-group">         -->
<!--                     <div class="col-sm-offset-2 col-sm-10"> -->
<!--                         <input class="col-sm-3 "  type="submit" name="click" value="updateRequest" > -->
<!--                     </div> -->
<!--                 </div> -->
                
                <input class="btn btn-success col-md-5 " type="submit" name="click" value="updateRequest"> <button data-dismiss="modal" class="btn btn-default col-sm-5 col-sm-offset-2" >Cancel</button>
            </form>
        </div>

    </body>
</html>
