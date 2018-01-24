<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<script src="<c:url value="/resources/js/dist/entry.js" />"></script>

	<link rel="icon" href="<c:url value="/resources/images/global/favicon.ico" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/entrypage/entry.css" />">

	<script type="text/javascript">
		const basicUrl = "${requestScope.basicUrl}";
	</script>
</head>
<body class="d-flex flex-column">
	<!-- START header -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top header">
	  <a class="navbar-brand ml-5 scrolltop" href=""><img src="<c:url value="/resources/images/global/donat-home.png" />" class="rounded-circle" style="width:30px;height:30px;">Home</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	  <div class="collapse navbar-collapse" id="navbarSupportedContent">
		<form class="form-inline my-2 my-lg-0 mr-5 ml-auto">
		  <input class="form-control mr-sm-2 rounded-element form-control-sm" type="text" placeholder="Search" aria-label="Search">
		  <button class="btn btn-info btn-sm my-2 my-sm-0" type="submit">Search</button>
		</form>
		<button type="button" class="btn btn-success btn-sm mr-2 btn-login-open" value="login" data-toggle="modal" data-target="#loginRegistModal">
		 Login
		</button>
		<button type="button" class="btn btn-primary btn-sm btn-register-open" value="register" data-toggle="modal" data-target="#loginRegistModal">
		 Register
		</button>
	  </div>
	</nav>
	
	
	<div class="modal fade" id="loginRegistModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel"></h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        <form class="modal-form">
			  <div class="form-group">
			    <div class="input-group">
					<span class="input-group-addon">@</span>
					<input type="email" class="form-control" name="email" placeholder="Enter email" aria-describedby="emailHelp">
				</div>
			    <small class="form-text text-muted">Pay attention to the email words case.</small>
			  </div>
			  <div class="form-group">
			  	<div class="input-group">
			    	<span class="input-group-addon">&nbsp;*&nbsp;</span>
			    	<input type="password" class="form-control" name="pass" placeholder="Password">
			    </div>
			    <small class="form-text text-muted">A password must be in the range of 5-32 symbols.</small>
			  </div>
			  <div class="form-group appendedRegister">
				  <div class="input-group">
				  	<span class="input-group-addon">&nbsp;*&nbsp;</span>
				  	<input type="password" class="form-control" placeholder="Repeat password" name="passconfirm">
				  </div>
				  <small class="form-text text-muted">This password must be idential to the given above.</small>
			  </div>
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary btn-submit-action" value="" data-dismiss="modal" data-toggle="modal" data-target="#resultModal"></button>
	      </div>
	    </div>
	  </div>
	</div>	



	<div class="container-fluid content">
		<div class="row align-items-center">
		    <div class="col-lg-3 text-center">
		      <img src="<c:url value="/resources/images/global/virtucon.jpg" />" class="rounded-circle" style="width:200px;height:200px;">
		      <img src="<c:url value="/resources/images/global/gla.png" />" class="rounded-circle" style="width:200px;height:200px;">
		    </div>
		    <div class="col-lg-9 spec-font-rules py-3">
		      <h2 class="color-nat-black text-capitalize">UniversalSite for all your needs</h2><br>
		      <p>
		      	<strong>Our values:</strong> We believe in free expression and think every voice has the power to impact the world.
		      </p><br>
		      <p>
		      	<strong>Our principles:</strong> On our site, you should feel safe expressing your unique point of view with every post. 
		      	We want everyone to discover a wide variety of voices and perspectives, and for that reason we allow strong opinions and controversial 
		      	views. So it’s our job to make your experience as safe as we can. But if you do experience abuse or harassment on Twitter, 
		      	that can jeopardize your freedom of expression. We won’t let Twitter be a place where you are intimidated, harassed, or silenced.
		      </p>
		      <p>
		      	<strong>Our approach:</strong> Free expression is a human right. Everyone has a voice, and the right to use it. On UniversalSite, 
		      	you should feel safe expressing your unique point of view with every post – and it’s our job to make that happen. 
		      	But sometimes posts can cross a line and are abusive or threatening. To keep you safe, we build tools 
		      	so you can control what you see and who you interact with; work with a community of online safety experts to fight abuse everywhere; 
		      	and develop and enforce policies to prohibit abusive behavior.
		      </p>
		      <p>
		      	<strong>About GLA:</strong> The GLA has bases scattered across the globe. Over a period of several years, the GLA steadily built up 
		      	their strength and support in the Third World, mainly within the Middle East and Central Asia. While the group possessed a 
		      	number of 'main' bases, their destruction did little to halt the GLA's spread of influence. For this reason, it 
		      	is debatable whether or not the organization possessed a 'nerve center' at all. In the First GLA War, the GLA's main 
		      	base of operations is in Akmola of Kazakhstan and was destroyed by a Sino-American force during Last Call.
		      </p>
		      <p>
		      	<strong>GLA Resources:</strong> The GLA began with a financial injection from a number of shadowy, wealthy backers (many of whom, 
		      	such as Prince Kassad who would go on to become GLA generals), and gained much of its funding hence from raiding resources, 
		      	such as the United Nations' foreign aid supplies. They also had ties with the Black Market which brought them a steady cash 
		      	flow as well as access to relatively advanced weapons.
		      </p>
		      <p>
		      	The GLA was highly resourceful, raiding old Soviet weapon dumps and employing ex-Soviet scientists and even scavenging spare parts
		      	 from the battlefield. While GLA vehicles could not match their more modern counterparts on the battlefield, GLA commanders were 
		      	 highly devious and unscrupulous, but many American and Chinese generals underestimated them at their peril.
		      </p>
		    </div>
	  	</div>
	  	<div class="row ft-video flex-column p-3 spec-font-rules text-center">
	  		<div class="text-capitalize color-nat-black pb-2">In order to clarify everything watch this video</div>
	  		<div class="embed-responsive embed-responsive-16by9 div-video align-self-center">
			  <iframe class="embed-responsive-item" src="https://www.youtube.com/embed/WrdnerqWtX4?rel=0"></iframe>
			</div>		
  		</div>
	  	<div class="row ft-contact spec-font-rules">
	  		<div class="col-lg-6 p-3">
	  			<h3 class="text-capitalize color-nat-black text-center">Contact Us</h3>
	  			<p>Contact me and i will get back to you within 24 hours.</p>
	  			<p>* Minsk, Belarus</p>
	  			<p>* 8-029-202-03-27</p>
	  			<p>* vvopaa@mail.com</p>
	  		</div>
	  		<div class="col-lg-6 p-3 padding-map">
	  			<div id="googleMap"></div>
	  		</div>
		</div>
	</div>


	<div class="d-flex flex-column footer">
		<div class="ft-1 text-center pt-1">
	  		<p class="font-weight-bold">UniversalSite© Copyright 2017 Privacy Policy</p>
		</div>
	</div>


</body>
</html>