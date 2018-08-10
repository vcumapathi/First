var app = angular.module('user');
app.controller('LeavesCtrl',['$scope','$state','$mdToast','employeeService',
	function($scope,$state,$mdToast,employeeService){
	
	 $scope.leavesManager = {};
	 
	 $scope.saveLeavesMaster = function(leavesManager){
		 employeeService.saveLeavesDetails(leavesManager).then(function(response){
			console.log(response); 
			$scope.leaves = {};
			//$scope.showActionToast();
		 },function(error){
			 console.log(error);
		 });
	 };
	 
	 $scope.leaves = {};
	 $scope.leaves.reportingOne;
	 $scope.getEmployeeLeaveList = function(){
		employeeService.getLeaveOfEmp().then(function(response){
			$scope.leaves = response;
			$scope.leaves.reportingOne=response.manager.email;
		},function(err){
			console.log(err);
		}) 
	 }
	 
	 $scope.getEmployeeLeaveList();
	 
}])