<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="shortcut icon" href="assets/icons/favicon.png" />
        <title>Nizam Energy</title>

        <!-- ================== BEGIN BASE CSS STYLE ================== -->
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet">
        <link href="assets/plugins/jquery-ui/themes/base/minified/jquery-ui.min.css" rel="stylesheet" />
        <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
        <link href="assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
        <link href="assets/css/animate.min.css" rel="stylesheet" />
        <link href="assets/css/style.min.css" rel="stylesheet" />
        <link href="assets/css/style-responsive.min.css" rel="stylesheet" />
        <link href="assets/css/theme/default.css" rel="stylesheet" id="theme" />
        <!-- ================== END BASE CSS STYLE ================== -->

		<link href="assets/plugins/password-indicator/css/password-indicator.css" rel="stylesheet" />
 		<link href="assets/plugins/select2/dist/css/select2.min.css" rel="stylesheet" />
        <!-- ================== BEGIN BASE JS ================== -->
         
        <!-- ================== END BASE JS ================== -->

        
       

    </head>
    <body class="pace-top">
        <!-- begin #page-loader -->
        <div id="page-loader" class="fade in"><span class="spinner"></span></div>
        <!-- end #page-loader -->

        <div class="login-cover">
            <div class="login-cover-image"><img src="assets/img/login-bg/BG4.jpg" data-id="login-cover-image" alt="" /></div>
            <div class="login-cover-bg"></div>
        </div>
        <!-- begin #page-container -->
        <div id="page-container" class="fade">
            <!-- begin login -->
            <div class="login login-v2" data-pageload-addclass="animated fadeIn">
                <!-- begin brand -->
                <div class="login-header" style="padding-left: 1px;">
                    <div class="brand">
                        <img id="helpico" src="assets/icons/logo.png">

                    </div>

                </div>
                <!-- end brand -->
                <div class="login-content">
                
                
                        
                
<!--                     <form action="LoginServlet" onsubmit="return checkFormValidation();" method="POST" class="margin-bottom-0"> -->
                        
                        
                        
                        	<div class="form-group">
									
									<div class="form-group m-b-20">
										<input type="password" name="password" placeholder="Current password" class="form-control m-b-5">
										
									</div>
									
									<div class="form-group m-b-20">
										<input type="password" name="password" placeholder="New password" id="password-indicator-default" class="form-control m-b-5">
										<div id="passwordStrengthDiv" class="is0 m-t-5"></div>
									</div>
									
									
									<div class="form-group m-b-20">
										<input type="password" name="password" placeholder="Repeat password" id="password-indicator-default-repeat" class="form-control m-b-6">
										<div id="passwordStrengthDivRepeat" class="is0 m-t-6"></div>
									</div>
									
								</div>
                        
                        
                        
                        
                        
                        
                        <div class="login-buttons">
                            <button type="submit" name="click" value="login" class="btn btn-success btn-block btn-lg">Change password</button>
                        </div>
                        
                        
                         
<!--                     </form> -->
                </div>
            </div>
            <!-- end login -->
            
            
            

            


        </div>
        <!-- end page container -->

        <!-- ================== BEGIN BASE JS ================== -->
        <script src="assets/plugins/jquery/jquery-1.9.1.min.js"></script>
        <script src="assets/plugins/jquery/jquery-migrate-1.1.0.min.js"></script>
        <script src="assets/plugins/jquery-ui/ui/minified/jquery-ui.min.js"></script>
        <script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
        <!--[if lt IE 9]>
                <script src="assets/crossbrowserjs/html5shiv.js"></script>
                <script src="assets/crossbrowserjs/respond.min.js"></script>
                <script src="assets/crossbrowserjs/excanvas.min.js"></script>
        <![endif]-->
        <script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
        <script src="assets/plugins/jquery-cookie/jquery.cookie.js"></script>
        <!-- ================== END BASE JS ================== -->










	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
		<script src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
		<script src="assets/plugins/ionRangeSlider/js/ion-rangeSlider/ion.rangeSlider.min.js"></script>
		<script src="assets/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.min.js"></script>
		<script src="assets/plugins/masked-input/masked-input.min.js"></script>
		<script src="assets/plugins/bootstrap-timepicker/js/bootstrap-timepicker.min.js"></script>
		<script src="assets/plugins/password-indicator/js/password-indicator.js"></script>
		<script src="assets/plugins/bootstrap-combobox/js/bootstrap-combobox.js"></script>
		<script src="assets/plugins/bootstrap-select/bootstrap-select.min.js"></script>
		<script src="assets/plugins/bootstrap-tagsinput/bootstrap-tagsinput.min.js"></script>
		<script src="assets/plugins/bootstrap-tagsinput/bootstrap-tagsinput-typeahead.js"></script>
		<script src="assets/plugins/jquery-tag-it/js/tag-it.min.js"></script>
	    <script src="assets/plugins/bootstrap-daterangepicker/moment.js"></script>
	    <script src="assets/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>
	    <script src="assets/plugins/select2/dist/js/select2.min.js"></script>
		<script src="assets/js/form-plugins.demo.min.js"></script>
		<script src="assets/js/apps.min.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->


        <!-- ================== BEGIN PAGE LEVEL JS ================== -->
	        <script src="assets/js/login-v2.demo.min.js"></script>
	        <script src="assets/js/apps.min.js"></script>
        <!-- ================== END PAGE LEVEL JS ================== -->

        <script>
            $(document).ready(function () {
                App.init();
//                 LoginV2.init();
                FormPlugins.init();
            });
        </script>
        
    </body>

    
</html>
