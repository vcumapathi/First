var app = angular.module('user');
app.factory('employeeService',['$http','$q',function($http,$q){
	var service ={};
	
	service.saveEmployeeDetails = function(employee){
		var deferred = $q.defer();
	     var url = '/FstPayRool/api/employee';
	        $http({
	        	url:url,
	        	data:employee,
	        	method :"POST",
	        	headers: { 
	                'Accept': 'application/json',
	                'Content-Type': 'application/json' 
	            }
	        }).then(function success(response) {
	            deferred.resolve(response.data);
	        }, function fail() {
	            deferred.reject();
	        });
	        return deferred.promise;
	};
	service.saveDetails = function(user){
		var deferred = $q.defer();
	     var url = '/FstPayRool/recipe';
	        $http({
	        	url:url,
	        	data:user,
	        	method :"POST",
	        	headers: { 
	                'Accept': 'application/json',
	                'Content-Type': 'application/json' 
	            }
	        }).then(function success(response) {
	            deferred.resolve(response.data);
	        }, function fail() {
	            deferred.reject();
	        });
	        return deferred.promise;
};

service.getRecipeList = function() {
    var deferred = $q.defer();
    var url = '/FstPayRool/recipes/' ;
    $http.get(url).then(function success(response) {
        deferred.resolve(response.data);
    }, function fail(reject) {
        deferred.reject(reject);
    });
    return deferred.promise;
}

service.getRecipe=function(recipeId) {
    var deferred = $q.defer();
    var url = '/FstPayRool/recipe/' + deviceId;
    $http.get(url).then(function success(response) {
        deferred.resolve(response.data);
    }, function fail(response) {
        deferred.reject(response.data);
    });
    return deferred.promise;
}
	
	service.getDoctors=function(deviceId) {
        var deferred = $q.defer();
        var url = '/FstPayRool/api/doctors/' + deviceId;
        $http.get(url).then(function success(response) {
            deferred.resolve(response.data);
        }, function fail(response) {
            deferred.reject(response.data);
        });
        return deferred.promise;
    }
	
	service.saveCat = function(employee){
		var deferred = $q.defer();
	     var url = '/FstPayRool/api/category';
	        $http({
	        	url:url,
	        	data:employee,
	        	method :"POST",
	        	headers: { 
	                'Accept': 'application/json',
	                'Content-Type': 'application/json' 
	            }
	        }).then(function success(response) {
	            deferred.resolve(response.data);
	        }, function fail() {
	            deferred.reject();
	        });
	        return deferred.promise;
	};
	
	service.saveLeavesDetails = function(leaveMaster){
		var deferred = $q.defer();
		var url ='/FstPayRool/api/saveLeaveMaster';
		$http({
			url:url,
			data: leaveMaster,
			method: 'POST',
			headers:{
				'Accept' : 'application/json',
				'Content-Type' : 'application/json'
			}
		}).then(function success(response){
			deferred.resolve(response.data)
		},function fail(){
			deferred.reject();
		});
		return deferred.promise;
	}
	
	service.employeeLogin = function(login) {
		var deferred = $q.defer();
		var url ='/FstPayRool/api/login';
		$http({
			url:url,
			data:login,
			method:'POST',
			headers:{
				'Accept' :'application/json',
				'Content-Type':'application/json'
			}
		}).then(function(response) {
			deferred.resolve(response.data);
		},function(err){
			deferred.reject(err);
		});
		return deferred.promise;
	}
	
	
	
	
	
	service.getEmployees = function() {
	        var deferred = $q.defer();
	        var url = '/FstPayRool/api/employees/' ;
	        $http.get(url).then(function success(response) {
	            deferred.resolve(response.data);
	        }, function fail(reject) {
	            deferred.reject(reject);
	        });
	        return deferred.promise;
	    }
	
	service.getCategories = function() {
        var deferred = $q.defer();
        var url = '/FstPayRool/api/categories/' ;
        $http.get(url).then(function success(response) {
            deferred.resolve(response.data);
        }, function fail(reject) {
            deferred.reject(reject);
        });
        return deferred.promise;
    }

	/*service.assignLeavesToEmployee = function(leaves) {
		 var deferred = $q.defer();
	        var url = '/FstPayRool/api/assignLeaves/' ;
	    	$http({
				url:url,
				data:leaves,
				method:'POST',
				headers:{
					'Accept' :'application/json',
					'Content-Type':'application/json'
				}
			}).then(function success(response) {
	            deferred.resolve(response.data);
	        }, function fail(reject) {
	            deferred.reject(reject);
	        });
	        return deferred.promise;
	}*/
	
	service.assignLeavesToEmployee = function(employee){
		var deferred = $q.defer();
	     var url = '/FstPayRool/api/assignLeaves';
	        $http({
	        	url:url,
	        	data:employee,
	        	method :"POST",
	        	headers: { 
	                'Accept': 'application/json',
	                'Content-Type': 'application/json' 
	            }
	        }).then(function success(response) {
	            deferred.resolve(response.data);
	        }, function fail() {
	            deferred.reject();
	        });
	        return deferred.promise;
	};
	
	service.getLeaveOfEmp = function() {
        var deferred = $q.defer();
        var url = '/FstPayRool/api/employeeLeaves/' ;
        $http.get(url).then(function success(response) {
            deferred.resolve(response.data);
        }, function fail(reject) {
            deferred.reject(reject);
        });
        return deferred.promise;
    }
	
	
	
	return service;
}])