var app = angular.module('user');
app.controller('EmployeeRegisterCtrl',['$scope','Upload','$state','$mdToast','employeeService',
	function($scope,Upload,$state,$mdToast,employeeService){
	
	 $scope.employee = {};
	 
	 $scope.saveEmployee = function(employee){
		 employeeService.saveEmployeeDetails(employee).then(function(response){
			console.log(response); 
			$scope.employee = {};
			//$scope.showActionToast();
		 },function(error){
			 console.log(error);
		 });
	 };
	 
		$scope.saveDetails = function(recipe){
			employeeService.saveDetails(recipe).then(function(result){
				var message=result.status;
				if(message ==='Saved Successfully')
					{
					console.log('success');
					}
				else{
					console.log('not success');
				}
			},function(err){
				console.log('error while saving...!');
			})
		}
		
		 $scope.getRecipeList = function(){
			 employeeService.getRecipeList().then(function(response){
				 console.log(response); 
				 $scope.recipes = response;
			 },function(error){
				 
			 })
		 }
		 $scope.getRecipeList();
		 
		 $scope.getRecipe = function(cId){
		 employeeService.getRecipe(cId).then(function(response){
			console.log(response); 
		 },function(error){
			 console.log(error);
		 });
	 };
	
//	 $scope.getDoctors(cId);
	 
//	 $scope.getDoctors = function(cId){
//		 employeeService.getDoctors(cId).then(function(response){
//			console.log(response); 
//		 },function(error){
//			 console.log(error);
//		 });
//	 };
//	 var cId =49;
//	 $scope.getDoctors(cId);
	 
	 $scope.saveDoctors = function(doctor,files){
		 $scope.fileObj = files;
		 Upload.upload({
	            url: '/FstPayRool/api/saveDoc',
		 		data:{'doctor': new Blob([JSON.stringify(doctor)],
		 				{type: "application/json"}), 'file': $scope.fileObj},
		 				 arrayKey: ''
	            }).then(function (resp) {
	            console.log('Success ' + resp.config.data.file.name + 'uploaded. Response: ' + resp.data);
	        }, function (resp) {
	            console.log('Error status: ' + resp.status);
	        }, function (evt) {
	            var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
	            console.log('progress: ' + progressPercentage + '% ' + evt.config.data.file.name);
	        });
	 };
	 
	 $scope.saveCat= function(cat){
		 employeeService.saveCat(cat).then(function(response){
				console.log(response); 
			 },function(error){
				 console.log(error);
			 });
	 }
	 
	 $scope.user = {};
	 
	 $scope.loginEmp = function(user) {
		 employeeService.employeeLogin(user).then(function(response){
				console.log(response); 
				$scope.user = {};
				$state.go('managerHome');
				$scope.getEmp();
				//$scope.showActionToast();
			 },function(error){
				 console.log(error);
			 });
	}
	 $scope.getEmp = function(){
		 employeeService.getEmployees().then(function(response){
			 console.log(response); 
			 $scope.employees = response;
		 },function(error){
			 
		 })
	 }
	// $scope.getEmp();
	 $scope.getCategories = function(){
		 employeeService.getCategories().then(function(response){
			 console.log(response); 
			 $scope.categories = response;
		 },function(error){
			 
		 })
	 }
	// $scope.getCategories();
	 $scope.leaves = {};
	
	 $scope.saveLeaves = function(leaves){
		 employeeService.assignLeavesToEmployee(leaves).then(function(response){
			console.log(response); 
			$scope.leaves = {};
			//$scope.showActionToast();
		 },function(error){
			 console.log(error);
		 });
	 };
	 
	  $scope.showActionToast = function() {
		    var pinTo = $scope.getToastPosition();
		    var toast = $mdToast.simple()
		      .textContent('Marked as read')
		      .action('UNDO')
		      .highlightAction(true)
		      .highlightClass('md-accent')// Accent is used by default, this just demonstrates the usage.
		      .position(pinTo);

		    $mdToast.show(toast).then(function(response) {
		      if ( response == 'ok' ) {
		       // alert('You clicked the \'UNDO\' action.');
		      }
		    });
		  };
		  
		  var last = {
			      bottom: false,
			      top: true,
			      left: false,
			      right: true
			    };

			  $scope.toastPosition = angular.extend({},last);

			  $scope.getToastPosition = function() {
			    sanitizePosition();

			    return Object.keys($scope.toastPosition)
			      .filter(function(pos) { return $scope.toastPosition[pos]; })
			      .join(' ');
			  };
			  
			  function sanitizePosition() {
				    var current = $scope.toastPosition;

				    if ( current.bottom && last.top ) current.top = false;
				    if ( current.top && last.bottom ) current.bottom = false;
				    if ( current.right && last.left ) current.left = false;
				    if ( current.left && last.right ) current.right = false;

				    last = angular.extend({},current);
				  }

}])