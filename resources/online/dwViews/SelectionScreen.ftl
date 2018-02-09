<!DOCTYPE html>
<html>

<head>
	<!-- favicon -->
	<link rel="icon" type="image/png" href="https://www.101st.info/media/news_categories/StarCitizen2_1_1.png">

	<!-- Web page title -->
	<title>Top Trumps</title>

	<!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
	<script src="https://code.jquery.com/jquery-2.1.1.js"></script>
	<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">

	<!-- Optional Styling of the Website, for the demo I used Bootstrap (see https://getbootstrap.com/docs/4.0/getting-started/introduction/) -->
	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/TREC_IS/bootstrap.min.css">
	<script src="http://dcs.gla.ac.uk/~richardm/vex.combined.min.js"></script>
	<script>
		vex.defaultOptions.className = 'vex-theme-os';
	</script>
	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex.css" />
	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex-theme-os.css" />
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

</head>

<style>
		body {
	background: black;
	background: linear-gradient(black, grey);
	}
	.play {
		position: absolute;
		left: 0;
		margin-top: 10%;
		margin-left: 25%;
	}
	/* I have only been able to test this on 15.1, 10.1, and 21.1 inch screen sizes, so keep as two separate classes for now */
	.viewStats {
		position: absolute;
		right: 0;
		margin-top: 10%;
		margin-right: 25%;
	}
</style>

<body onload="initalize()">
	<!-- Call the initalize method when the page loads -->
	<!-- HEAD -->
<!-- <h1>Top Trumps STAR CITIZEN</h1> -->

	<div class="container">

		<!-- Add your HTML Here -->
		<div class="play">
			<div class="card card-inverse" style="background-color: Gainsboro; border-color: #333; max-width: 20rem; height: 30rem;">
				<div href="/document" style="height:100%;">
					<img class="card-img-top" src="https://i.imgur.com/PjPKwx9.png" alt="PLAY GAME">
					<div class="card-block">
						<br />
						<center>
							<h4><b>Play a new game</b></h4>
							<br />
							<br />
							<p class="card-text">Trump your Star Citizen opponents <br /> and top the leaderboard!</p>
							<br />
							<a href="http://localhost:7777/toptrumps/game" class="btn btn-outline-primary btn-lg">Play Game</a></center>
					</div>
				</div>
			</div>
		</div>

		<div class="viewStats">
	<div class="card card-inverse" style="background-color: Gainsboro; border-color: #333; max-width: 20rem; height: 30rem;">

				<div href="/document" style="height:100%;">
					<img class="card-img-top" src="https://i.imgur.com/F8xMQ3m.jpg" alt="VIEW STATS">
					<br />
					<div class="card-block">
						<br />
						<center>
							<h4><b>View statistics</b></h4>
							<br />
							<br />
							<p class="card-text">View previous game statistics.</p>
							<br />
							<br />
							<a href="http://localhost:7777/toptrumps/stats" class="btn btn-outline-primary btn-lg">View Statistics</a></center>
					</div>
				</div>
			</div>
		</div>

	</div>

	<script type="text/javascript">
		// Method that is called on page load
		function initalize() {
			// --------------------------------------------------------------------------
			// You can call other methods you want to run when the page first loads here
			// --------------------------------------------------------------------------
			
		}
		// -----------------------------------------
		// Add your other Javascript methods Here
		// -----------------------------------------
		// This is a reusable method for creating a CORS request. Do not edit this.
		function createCORSRequest(method, url) {
			var xhr = new XMLHttpRequest();
			if ("withCredentials" in xhr) {
				// Check if the XMLHttpRequest object has a "withCredentials" property.
				// "withCredentials" only exists on XMLHTTPRequest2 objects.
				xhr.open(method, url, true);
			} else if (typeof XDomainRequest != "undefined") {
				// Otherwise, check if XDomainRequest.
				// XDomainRequest only exists in IE, and is IE's way of making CORS requests.
				xhr = new XDomainRequest();
				xhr.open(method, url);
			} else {
				// Otherwise, CORS is not supported by the browser.
				xhr = null;
			}
			return xhr;
		}
	</script>

</body>

</html>

