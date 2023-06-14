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
	<title>StoryTime Dashboard</title>
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
	</div>
	<div class="display container">
	<div class="d-flex justify-content-around">
		<h2 class="display-2">Welcome, ${user.fName}!</h2>
		<div class="my-auto">
			<button class="btn btn-secondary text-white add-story"><a href="/story/add" class="text-decoration-none text-white">Add Story</a></button>
		</div>
	</div>
	<c:if test="${id != 1 }">
	<p>Welcome to StoryTime, a dedicated platform designed to unleash your creativity and share your original writings with the world. Here, we believe that everyone has a unique story to tell, a voice that deserves to be heard. Whether you're an aspiring author, a seasoned poet, or simply someone with a passion for writing, this is the perfect space to showcase your talent. Share your thoughts, ideas, and imagination through captivating stories, thought-provoking essays, heartfelt poems, or any form of written expression that resonates with you. Connect with a community of like-minded individuals, engage in discussions, and receive valuable feedback to refine your craft. Join us on this journey of literary exploration and let your words leave a mark on the hearts and minds of readers worldwide. Embrace the power of storytelling and let your voice soar through the pages of this platform. Welcome to a world of boundless creativity and inspiration!</p>
	</c:if>
		<div class="d-flex justify-content-around">
			<h4 class="display-4 text-center">Most Recent Stories</h4>
			<jsp:useBean id="now" class="java.util.Date" />
    		<fmt:formatDate value="${now}" pattern="h:mm a" var="formattedDate"/>
    		<p class="my-auto">Last Updated: ${formattedDate}</p>
		</div>
		<c:forEach var="story" items="${stories.content}"> 
			<div class="d-flex row storyCard m-1">
				<div class=" col d-flex flex-column my-auto">
					<h3 class="text-center">${story.storyAuthor.userName}</h3>
					<c:if test="${pictureUrl != null}">
						<img class="mx-auto profilePicture p-2"src="/profilepicture/story/${story.storyAuthor.id}" alt="${story.storyAuthor.fName} ${story.storyAuthor.lName}'s profile picture" />
					</c:if>
				</div>
				<div class="w-50 col">
					<a href="/story/view/${story.id}" class="text-decoration-none display" ><h2 class="text-center mx-auto">${story.storyTitle}</h2></a>
					<div class="d-flex justify-content-around">
						<p>${story.likes.size()} Likes</p>
						<c:if test="${story.storyAuthor.id != user.id }">
							<form:form action="/story/${story.id}/newLike" modelAttribute="like" method="post" class="d-flex mb-3">
								<input type="submit" value="Like" class="btn btn-md btn-secondary text-white"/>
							</form:form>
						</c:if>
						<fmt:formatDate value="${story.createdAt}" pattern="MMM dd, yyyy h:mm a" var="formattedDate2"/>
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
		<div  class="d-flex">
			<nav  class="mx-auto">
				<ul class="pagination ">
				     <c:forEach begin="1" end="${totalPages}" var="index">
				        <a href="/dashboard/${index}" class="page-link page text-white">${index}</a>
				    </c:forEach>
			  	</ul>
			</nav>
		</div>
	</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>