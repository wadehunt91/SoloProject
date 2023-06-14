<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">
<head>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="/css/style.css">
	<script type="text/javascript" src="/js/app.js"></script>
	<meta charset="UTF-8" />
	<title>Edit Profile</title>
</head>
<body class="container display">
	<div class="container">
		<nav class="navbar navbar-expand-lg navbar-light">
	  		<a class="navbar-brand" href="/dashboard/1">StoryTime</a>
	  		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	    		<span class="navbar-toggler-icon"></span>
	  		</button>
	  		<div class="collapse navbar-collapse" id="navbarSupportedContent">
	    		<ul class="navbar-nav ml-auto">
	      			<li class="nav-item active">
	        			<a class="nav-link" href="/dashboard/1">Home <span class="sr-only">(current)</span></a>
	      			</li>
	      			<li class="nav-item">
	        			<a class="nav-link" href="/view/author/${user.id}">My Stories</a>
	      			</li>
	      			<li class="nav-item">
	        			<a class="nav-link" href="/search">Search Stories</a>
	      			</li>
	      			<li class="nav-item">
	  					<a class="nav-link" href="/profile/${user.id}">Profile</a>
	  				</li>
	      			<li class="nav-item">
	        			<a class="nav-link" href="/logout" tabindex="-1" aria-disabled="true">Logout</a>
	      			</li>
	    		</ul>
	  		</div>
		</nav>
	</div>
	<div  class="d-flex row">
					<div class="w-50 col">
						<h3 class="display-3 text-center">Profile Picture</h3>
						<c:if test="${ picture != null }">
							<div class="picSpace mx-auto">
								<img class="profilePicturePage ml-auto" src="/profilepicture/show/${userId}" alt="${user.fName} ${user.lName} profile picture"/>
							</div>
						</c:if>
						<c:if test="${ picture == null }">
							<div class="picSpace mx-auto">
								<img class="profilePicturePage ml-auto" src="/notfound.jpeg" alt="${user.fName} ${user.lName} profile picture"/>
							</div>
						</c:if>
						<form action="/profilepicture/${user.id}" enctype="multipart/form-data" method="post">
							<div class="d-flex flex-column">	
							<input type="file" name="file" id="profilePicture" class="mx-auto"/>
							<input type="hidden" name="name" value= "${user.fName}ProfilePicture"/>
							<input type="hidden" name="user_id" value= "${user.id}"/>
							<button type="submit" class="btn btn-secondary mx-auto">Submit</button>
							</div>	
						</form>
					</div>
					<div class="login col" class="form-group">
            			<form:form action="/profile/${user.id}" modelAttribute="user" method="put" >
                			<h3 class="display-3 text-center">Update Profile</h3>
                			<div>
			                    <form:label path="fName">First Name</form:label>
			                    <form:errors path="fName" class="text-danger"/>
			                    <form:input path="fName" type="text" class="form-control form-control-lg" value="${user.fName}"/>
			                </div>
                			<div>
			                    <form:label path="lName">Last Name</form:label>
			                    <form:errors path="lName" class="text-danger"/>
			                    <form:input path="lName" type="text" class="form-control form-control-lg" value="${user.lName}"/>
			                </div>
			                <div>
			                    <form:label path="userName">Username</form:label>
			                    <form:errors path="userName" class="text-danger"/>
			                    <form:input path="userName" type="text" class="form-control form-control-lg"/>
			                </div>
			                <div>
			                    <form:label path="email">Email</form:label>
			                    <form:errors path="email" class="text-danger"/>
			                    <form:input path="email" type="email" class="form-control form-control-lg" value="${user.email}"/>
			                </div>
			                <div>
			                    <form:label path="password">Password</form:label>
			                    <form:errors path="password" class="text-danger"/>
			                    <form:input path="password" type="password" class="form-control form-control-lg" value=""/>
			                </div>
			                <div>
			                    <form:label path="confirmPassword">Confirm Password</form:label>
			                    <form:errors path="confirmPassword" class="text-danger"/>
			                    <form:input path="confirmPassword" type="password" class="form-control form-control-lg" value=""/>
			                </div>
			                <div class="mt-2 d-flex ">
			                    <button  class="btn btn-secondary mx-auto ">Register User</button>
			                </div>
            			</form:form>
        			</div>
				</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>