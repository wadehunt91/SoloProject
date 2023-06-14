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
	<script src="https://cdn.tiny.cloud/1/808g1os6dwr35g6ssp5e8ne97b3uxr4g4di8v6grg4ywwap5/tinymce/6/tinymce.min.js" referrerpolicy="origin"></script>
	<script type="text/javascript" src="/js/app.js"></script>
	<meta charset="UTF-8" />
	<title>Story Search</title>
</head>
<body class="container">
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
		<div>
			<form action="/search/" method="get"class="d-flex" >
				<select name="q" class="form-control">
			        <option value="Action And Adventure">Action and Adventure</option>
					<option value="Alternate History">Alternate History</option>
					<option value="Anthology">Anthology</option>
					<option value="Chick Lit">Chick Lit</option>
					<option value="Children">Children</option>
					<option value="Christian">Christian</option>
					<option value="Classics">Classics</option>
				    <option value="Cookbooks">Cook books</option>
				    <option value="Crime">Crime</option>
				    <option value="Fantasy">Fantasy</option>
				    <option value="Fan-fiction">Fan-fiction</option>
				    <option value="Historical Fiction">Historical Fiction</option>
				    <option value="History">History</option>
				    <option value="Horror">Horror</option>
				    <option value="Humor and Comedy">Humor and Comedy</option>								    
				    <option value="Memoir and Autobiography">Memoir and Autobiography</option>
					<option value="Mystery">Mystery</option>
				    <option value="Paranormal">Paranormal</option>
				    <option value="Philosophy">Philosophy</option>
				    <option value="Poetry">Poetry</option>
				    <option value="Psychology">Psychology</option>
				    <option value="Religion and Spirituality">Religion and Spirituality</option>
				    <option value="Romance">Romance</option>
				    <option value="Science Fiction">Science Fiction</option>
				    <option value="Self-Help Books">Self-Help Books</option>								    
				    <option value="Suspense and Thrillers">Suspense and Thrillers</option>
				    <option value="Travel">Travel</option>
	           </select>
			   <button  class="btn btn-primary mx-auto ">Search</button>
			</form>
		</div>
		<c:if test="${stories == null }">
			<h3 class="display-3 display text-center">No stories added</h3>
		</c:if>
		<c:forEach var="story" items="${stories}">
			<div class="d-flex row storyCard m-1 display">
				<div class=" col d-flex flex-column my-auto">
					<h3 class="display-4 text-center">${story.storyAuthor.userName}</h3>
					<c:if test="${pictureUrl != null}">
						<img class="mx-auto profilePicture" height="300px" width="300px"src="/profilepicture/story/${story.storyAuthor.id}" alt="${story.storyAuthor.fName} ${story.storyAuthor.lName}'s profile picture" />
					</c:if>
				</div>
				<div class="w-50 col">
					<a href="/story/view/${story.id}" class="text-decoration-none display"><h2 class="text-center mx-auto">${story.storyTitle}</h2></a>
					<div class="d-flex justify-content-around">
						<p>${story.likes.size()} Likes</p>
						<c:if test="${story.storyAuthor.id != user.id }">
							<form:form action="/story/${story.id}/newLike" modelAttribute="like" method="post" class="d-flex mb-3">
								<input type="submit" value="Like" class="btn btn-md btn-secondary text-white"/>
							</form:form>
						</c:if>
						<fmt:formatDate value="${story.createdAt}" pattern=" MM/dd/YYYY HH:mm" var="formattedDate2"/>
						<p>Submitted ${formattedDate2}</p>
					</div>
					<div class="my-auto">
						 <c:if test="${story.storyDescription != null }">
						 	<p class="text-center">${story.storyDescription}</p>
						 </c:if>
						 <c:if test="${story.storyDescription == null }">
						 	<p>No description provided. . .</p>
						 </c:if>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>