'use strict';

angular.module('app')
	.controller('loginController',[ '$rootScope', '$scope', '$http', '$state', function($rootScope, $scope, $http, $state) {
        $scope.user = {};
		$state.go('main.sys.user.list');
		/*$scope.login = function () {
			$.ajax({
				url : '/login',
				data: $scope.user
			}).then(function(result) {
				var aa = 200;
				if (aa == 200) {
					$state.go('main.sys.user.list');
				} else {
					$scope.msg = result.msg;
					$rootScope.$apply();
				}
			});
		}*/
} ]);