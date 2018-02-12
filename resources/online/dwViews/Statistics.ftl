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
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

</head>

<style>
	body {
	background: linear-gradient(to right, LightSkyBlue, DodgerBlue);
	}
	.play {
		 display: block;
    	margin-left: auto;
   		 margin-right: auto;
    		 width: 60%;
		 margin-top: 10%;
	}

</style>

<body onload="initalize()">
	<!-- Call the initalize method when the page loads -->
	<!-- HEAD -->
<!-- <h1>Top Trumps STAR CITIZEN</h1> -->

	<div class="container">
		<!-- Add your HTML Here -->
			<div class="play">
			<center>
				<div class="w3-container">
					<div class = "w3-container w3-cell">
					<div class="w3-card-4 w3-pale-blue" id="player">
						<div class="card border-light mb-3" style="width: 40rem; height: 55rem;">
							<div href="/document" style="height:100%;">
								<center>
									<h4 id="desc"><b>Game Statistics</b></h4>
								</center>
								<img class="card-img-top" src="https://i.imgur.com/PjPKwx9.png" alt="PLAY GAME">
								<div class="card-block">
								<br />
								<br /><br />
								<b>Total number of games played:</b> <span id="totalGames"></span>			
								<br /><br /> 
								<b>Number of computer wins:</b> <span id="computerWins"></span> 
								<br /><br /> 
								<b>Number of human wins:</b> <span id="humanWins"></span> 
								<br /><br /> 
								<b>Average number of draws:</b> <span id="averageDraws"></span> 
								<br /><br /> 
								<b>Largest number of rounds per game:</b> <span id="highestRoundsPerGame"></span> 
								<br /> 
							</p>
							<br />
							<center><a <center><button><a href="http://localhost:7777/toptrumps" class="button"><b><font color="black">Main Menu</font></b></a></button></center>
					</div>
				</div>
			</div>
			</center>
		</div>
	</div>
	<script type="text/javascript">

	var games;
	var comWins;
	var playerWins;
	var avgDraws;
	var maxRounds;

		// Method that is called on page load
		function initalize() {
			getMatchesPlayed();
			getComputerWins();
			getHumanWins();
			getAverageDraws();
			getLargestRoundsPerGame();
			setValues();
		}

		function setValues(){
			document.getElementById('totalGames').innerHTML = games;
			document.getElementById('computerWins').innerHTML = comWins;
			document.getElementById('humanWins').innerHTML = playerWins;
			document.getElementById('averageDraws').innerHTML = avgDraws;
			document.getElementById('highestRoundsPerGame').innerHTML = maxRounds;
		}

		// This is a reusable method for creating a CORS request. Do not edit this.
		function createCORSRequest(method, url, async) {
			var xhr = new XMLHttpRequest();
			if ("withCredentials" in xhr) {
				// Check if the XMLHttpRequest object has a "withCredentials" property.
				// "withCredentials" only exists on XMLHTTPRequest2 objects.
				xhr.open(method, url, async);
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

		function getMatchesPlayed() {
			// First create a CORS request, this is the message we are going to send (a get request in this case)
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/matchesPlayed", false); // Request type and URL
			// Message is not sent yet, but we can check that the browser supports CORS
			if (!xhr) {
				alert("CORS not supported");
			}
			// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
			// to do when the response arrives
			xhr.onload = function(e) {
				games = xhr.response; // the text of the response

			};
			// We have done everything we need to prepare the CORS request, so send it
			xhr.send();
		}

		function getComputerWins() {
			// First create a CORS request, this is the message we are going to send (a get request in this case)
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/computerWonMatches", false); // Request type and URL
			// Message is not sent yet, but we can check that the browser supports CORS
			if (!xhr) {
				alert("CORS not supported");
			}
			// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
			// to do when the response arrives
			xhr.onload = function(e) {
				comWins = xhr.response; // the text of the response

			};
			// We have done everything we need to prepare the CORS request, so send it
			xhr.send();
		}

		function getHumanWins() {
			// First create a CORS request, this is the message we are going to send (a get request in this case)
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/humanWonMatches", false); // Request type and URL
			// Message is not sent yet, but we can check that the browser supports CORS
			if (!xhr) {
				alert("CORS not supported");
			}
			// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
			// to do when the response arrives
			xhr.onload = function(e) {
				playerWins = xhr.response; // the text of the response

			};
			// We have done everything we need to prepare the CORS request, so send it
			xhr.send();
		}

		function getAverageDraws() {
			// First create a CORS request, this is the message we are going to send (a get request in this case)
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/averageNumberOfDraws", false); // Request type and URL
			// Message is not sent yet, but we can check that the browser supports CORS
			if (!xhr) {
				alert("CORS not supported");
			}
			// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
			// to do when the response arrives
			xhr.onload = function(e) {
				avgDraws = xhr.response; // the text of the response

			};
			// We have done everything we need to prepare the CORS request, so send it
			xhr.send();
		}

		function getLargestRoundsPerGame() {
			// First create a CORS request, this is the message we are going to send (a get request in this case)
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/highestNumberOfRounds", false); // Request type and URL
			// Message is not sent yet, but we can check that the browser supports CORS
			if (!xhr) {
				alert("CORS not supported");
			}
			// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
			// to do when the response arrives
			xhr.onload = function(e) {
				maxRounds = xhr.response; // the text of the response

			};
			// We have done everything we need to prepare the CORS request, so send it
			xhr.send();
		}
	</script>

