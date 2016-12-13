<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<link rel="stylesheet" type="text/css" href="css/shareholder.css" />
<link rel="css/bootstrap.css" type="text/css" href="css/shareholder.css" />
<head>
<img id="mtdew" src="img/mtndew.jpg">
<br>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Theme Template for Bootstrap</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap theme -->
<link href="css/bootstrap-theme.min.css" rel="stylesheet">
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="css/ie10-viewport-bug-workaround.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="theme.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="../../assets/js/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Trading Platform</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="login.html">Logout</a></li>
					<li class="active"><a href="login.html">Quit</a></li>
					</li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>
	<div id="body" class="container theme-showcase">
		<div class="jumbotron">
			<input type="button" value="View Profile" onclick="show('profile')"
				class="btn btn-lg btn-default">
			<div id="profile">
				<div class="row">
					<div class="col-sm-10">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h3 class="panel-title">Profile</h3>
							</div>
							<div class="panel-body">${profile}</div>
						</div>
					</div>
				</div>
			</div>
			<br> <input type="button" value="show all trade hist"
				onclick="show('tradehist')" class="btn btn-lg btn-default">
			<div id="tradehist">
				<div class="row">
					<div class="col-sm-10">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h3 class="panel-title">Trade History</h3>
							</div>
							<div class="panel-body">
								<c:forEach items="${tradeHist}" var="entry">
									${entry}<br>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
			<br> <input type="button" value="show all req hist"
				onclick="show('reqhist')" class="btn btn-lg btn-default">
			<div id="reqhist">
				<div class="row">
					<div class="col-sm-10">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h3 class="panel-title">Request History</h3>
							</div>
							<div class="panel-body">
								<c:forEach items="${reqHist}" var="entry">
									${entry}<br>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
			<br> <input type="button" value="portfolio"
				onclick="show('portfolio')" class="btn btn-lg btn-default">
			<div id="portfolio">
				<div class="row">
					<div class="col-sm-10">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h3 class="panel-title">Your Portfolio</h3>
							</div>
							<div class="panel-body">Portfolio</div>
						</div>
					</div>
				</div>
			</div>
			<br> <input type="button" value="new request"
				onclick="show('request')" class="btn btn-lg btn-default">
			<div id="request">
				<div class="row">
					<div class="col-sm-10">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h3 class="panel-title">New Request</h3>
							</div>
							<div class="panel-body">
								<form id="newReq" action="newRequest" method="post">
									<fieldset>
										company: <input type="text" list="companies" name="company">
										<datalist id="companies">
											<c:forEach items="${allComps}" var="displayCompany">
												<option>${displayCompany.getName()}</option>
											</c:forEach>
										</datalist>
										<br> how many? <input type="number" name="howmany"><br> buy
										<input type="radio" name="bs" value="BUY"> sell <input
											type="radio" name="bs" value="SELL"> <br> <input
											type="submit" value="confirm trade"
											class="btn btn-lg btn-default">
									</fieldset>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
			<br>
		</div>
	</div>
</body>
<script>
	function show(thing) {
		document.getElementById("profile").style.display = "none";
		document.getElementById("request").style.display = "none";
		document.getElementById("portfolio").style.display = "none";
		document.getElementById("tradehist").style.display = "none";
		document.getElementById("reqhist").style.display = "none";
		document.getElementById(thing).style.display = "block";
	}
</script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
	window.jQuery
			|| document
					.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')
</script>
<script src="../../dist/js/bootstrap.min.js"></script>
<script src="../../assets/js/docs.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>

</html>