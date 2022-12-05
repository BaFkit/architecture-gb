angular.module('l7', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8080/api/v1/students/';

    $scope.loadStudents = function () {
        $http.get(contextPath)
            .then(function (response) {
                $scope.StudentsList = response.data;
            });
    };

    $scope.addNewStudent = function () {
        $http.post('http://localhost:8080/api/v1/students', $scope.newStudent)
            .then(function successCallback(response) {
                alert('Student Added ' + response.data.name);
                $scope.newStudent = null;
                $scope.loadStudents();
            }, function errorCallback(response) {
                alert('FAILED');
            });
    }

    $scope.updateStudent = function (studentId) {
        console.log(studentId)
        $scope.updateStudent = $scope.newStudent
        $scope.updateStudent.id = studentId;
        $http.put('http://localhost:8080/api/v1/students', $scope.updateStudent)
            .then(function (response) {
                $scope.newStudent = null;
                $scope.updateStudent = null;
                $scope.loadStudents();
            });
    }

    $scope.deleteStudent = function (studentId) {
        $http.delete(contextPath + studentId)
            .then(function (response) {
                $scope.loadStudents();
            });
    }

    $scope.loadStudents();
});