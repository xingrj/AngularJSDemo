<!DOCTYPE html>
<html>
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script>
	var app = angular.module('volcker', []);
	app.controller('volckCtrl', function($scope, $http) {
		$scope.retrieve = function() {
		$http.get("getPnL").success(function(response) {
			$scope.desks = response.pnls;
		});
	   }
	});
</script>
</head>
<body>
<h1>AngularJS AJax call to retrieved data from DB.</h1>
<p>Click the button to show data from DB.</p>

	<div ng-app="volcker" ng-controller="volckCtrl">
	   <button ng-click="retrieve()">Show Data</button>
		<table border="1">
			<tr ng-repeat="x in desks">
				<td>{{x.deskName}}</td>
				<td>{{x.sourceName}}</td>
			</tr>
		</table>
	</div>
</body>
</html>
