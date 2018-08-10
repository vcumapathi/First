angular.module('App',['user','ui.router','ui.bootstrap']).config(['$stateProvider','$urlRouterProvider','$locationProvider',
	function($stateProvider,$urlRouterProvider,$locationProvider){
	$stateProvider
	 
	 .state('home', {
	 url:'/home',
	 templateUrl:'student/views/home.html'
	  })
	  
	  .state('empRegister',{
		  url:'/empRegister',
		  templateUrl:'student/views/empRegister.html',
		  controller:'EmployeeRegisterCtrl'
	  })
	  
	   .state('managerDashboard',{
		  url:'/managerDashboard',
		  templateUrl:'student/views/managerDashboard.html',
		  controller:'EmployeeRegisterCtrl'
	  })
	  
	  .state('empLogin',{
		  url:'/empLogin',
		  templateUrl:'student/views/empLogin.html',
		  controller:'EmployeeRegisterCtrl'
	  })
	  
	  .state('category',{
		  url:'/category',
		  templateUrl:'student/views/category.html',
		  controller:'EmployeeRegisterCtrl'
	  })
	  
	    .state('doctorList',{
		  url:'/doctorList',
		  templateUrl:'student/views/doctorList.html',
		  controller:'EmployeeRegisterCtrl'
	  })
	  
	  .state('managerHome',{
		  url:'/managerHome',
		  templateUrl:'student/views/managerHome.html',
		  controller:'LeavesCtrl'
	  })
	  
	  .state('login', {
	 url:'/login',
	 templateUrl:'student/views/login.html'
	  });

	$urlRouterProvider.otherwise('/home');
}])