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
	<title>${story.storyTitle}</title>
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
	<div class="container">
		<fmt:formatDate value="${story.createdAt}" pattern="MMM dd, yyyy HH:mm a" var="StoryDate"/>
		<fmt:formatDate value="${story.updatedAt}" pattern="MMM dd, yyyy HH:mm a" var="StoryUpdatedDate"/>
					<div class="storyCard container mt-3">
					<h2 class="display-2 text-center">${story.storyTitle}</h2>
					<br />
					<p class="display-4 text-center">${story.storyAuthor.userName}</p>
					<br/>
					<p class="text-center display">Posted : ${StoryDate}</p>
					<c:if test="${story.updatedAt != null }">
						<p class="text-center display">Updated: ${StoryUpdatedDate}</p>
					
					</c:if>
					<c:if test = "${user.id == story.storyAuthor.id}">
						<div class="d-flex justify-content-around">
							<a href="/story/edit/${id}"><button class="btn btn-lg btn-outline-secondary">Edit Story</button></a>
							<form:form action="/story/delete/${id}" method = "post">
								<input type="hidden" name="_method" value="delete"/>
								<input type="submit" value="Delete" class="btn btn-lg btn-outline-secondary">
							</form:form>
						</div>
					</c:if>
					${story.storyText}
					</div>
				</div>
				<div>
					<h3 class="display-3 text-center">Add Comment</h3>
					<form:form action="/story/${story.id}/newComment" modelAttribute="comment" method="post" class="d-flex mb-3">
						<form:textarea path="text" class="form-control" rows="2"/>
						<input type="submit" value="Submit"/>
					</form:form>
						<div class="commentCard">
							<table class="table table-striped mt-3">
								<thead>
									<th>User</th>
									<th>Comment</th>
									<th>Date/Time</th>
								</thead>
								<tbody>
									<c:forEach var="comment" items="${currentComments}">
									<jsp:useBean id="now" class="java.util.Date" />
									<fmt:formatDate value="${comment.createdAt}" pattern="MMM dd, yyyy HH:mm a" var="formattedDate"/>
									<tr>
										<td>${comment.commentUser.userName }</td>
										<td>${comment.text}</td>
										<td>${formattedDate}</td>
									</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
				</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>