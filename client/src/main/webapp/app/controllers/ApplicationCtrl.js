'use strict';

/* ApplicationCtrl */

angular.module('app')

.controller('ApplicationCtrl', function($scope, $location) {
    $scope.isActive = function(path) {
        if ($location.path().substr(0, path.length) === path) {
            if (path === "/" && $location.path() === "/") {
                return true;
            } else if (path === "/") {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    };
})