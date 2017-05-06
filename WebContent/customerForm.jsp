<html>
<head>
<title>Angular JS Includes</title>

<style>
table, th, td {
	border: 1px solid grey;
	border-collapse: collapse;
	padding: 5px;
}

table tr:nth-child(odd) {
	background-color: #f2f2f2;
}

table tr:nth-child(even) {
	background-color: #ffffff;
}
</style>

</head>
<body>
	<h2>AngularJS Sample Application</h2>
	<div data-ng-app="myApp" ng-controller="studentController">
		<table>
			<tr>
				<th>Name</th>
				<th>Roll No</th>
				<th>Percentage</th>
			</tr>

			<tr ng-repeat="student in students">
				<td>{{ student.name }}</td>
				<td>{{ student.rollNo }}</td>
				<td>{{ student.percentage }}</td>
			</tr>
		</table>
	</div>

	<script>
		var app = angular.module('myApp', []);
		function studentController($scope, $http) {
			$http({
				method : 'GET',
				url : 'ApplianceController'
			}).success(function(data, status, headers, config) {
				console.log("jfjjfj" + data)
				$scope.students = data;
			}).error(function(data, status, headers, config) {
				
			});
		}
	</script>

	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.15/angular.min.js"></script>

</body>
</html>