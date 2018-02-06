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
		/* the background image should stretch to all screen sizes */
		background-repeat: no-repeat;
		background-size: cover;
		background-image: url("https://images2.alphacoders.com/552/thumb-1920-552988.jpg")
	}
	.play {
		 display: block;
    		 margin-left: auto;
   		 margin-right: auto;
    		 width: 40%;
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
			<div class="card border-light mb-3" style="max-width: 30rem; height: 40rem;">
				<div href="/document" style="height:100%;">
					<img class="card-img-top" src="https://i.imgur.com/Z6X85jK.jpg" alt=“Stats”>
					<div class="card-block">
						<center>
							<h4><b>Game Statstics</b></h4>
							<br />							
							<p class="card-text">Overall number of games: <span id="totalGames"></span> 
								<br /><br /> 
								Number of computer wins: <span id="computerWins"></span> 
								<br /><br /> 
								Number of human wins: <span id="humanWins"></span> 
								<br /><br /> 
								Average number of draws: <span id="averageDraws"></span> 
								<br /><br /> 
								Largest number of rounds per game: <span id="highestRoundsPerGame"></span> 
								<br /> 
							</p>
							<br />
							<a href="http://localhost:7777/toptrumps/play-game"</a></center>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
	
		//var totalGamesResponse = {
		//	totalGames: 5
		//};
		
		//var computerWinsResponse = {
		//	computerWins: 6
		//};
	
		// Method that is called on page load
		function initalize() {
			// --------------------------------------------------------------------------
			// You can call other methods you want to run when the page first loads here
			// --------------------------------------------------------------------------
			// For example, lets call our sample methods
			
			//getMatchesPlayedMock();
			//getComputerWinsMock();
			
			getMatchesPlayed();
			getComputerWins();
			getHumanWins();
			getAverageDraws();
			getLargestRoundsPerGame();
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

	<!-- Here are examples of how to call REST API Methods -->
	<script type="text/javascript">
	
	//below used to test methods working to output to page 
		//function getMatchesPlayedMock(){
			// call getMatches API endpoint
			//var response = totalGamesResponse;
			//
			//var computerWinsSpan = document.getElementById('totalGames');
			//
			//computerWinsSpan.innerHTML = response.totalGames;
		//}
		//
		//function getComputerWinsMock(){
			//var response = computerWinsResponse;
			//
			//var totalGamesSpan = document.getElementById('computerWins');
			//
			//totalGamesSpan.innerHTML = response.computerWins;
		//}
	
		function getMatchesPlayed() {
			// First create a CORS request, this is the message we are going to send (a get request in this case)
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/matchesPlayed"); // Request type and URL
			// Message is not sent yet, but we can check that the browser supports CORS
			if (!xhr) {
				alert("CORS not supported");
			}
			// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
			// to do when the response arrives
			xhr.onload = function(e) {
				var responseText = xhr.response; // the text of the response
				
				var totalGamesSpan = document.getElementById('totalGames');
		
				totalGamesSpan.innerHTML = responseText;
			};
			// We have done everything we need to prepare the CORS request, so send it
			xhr.send();
		}
		
		function getComputerWins() {
			// First create a CORS request, this is the message we are going to send (a get request in this case)
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/computerWonMatches"); // Request type and URL
			// Message is not sent yet, but we can check that the browser supports CORS
			if (!xhr) {
				alert("CORS not supported");
			}
			// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
			// to do when the response arrives
			xhr.onload = function(e) {
				var responseText = xhr.response; // the text of the response
				
				var totalGamesSpan = document.getElementById('computerWins');
		
				totalGamesSpan.innerHTML = responseText;
			};
			// We have done everything we need to prepare the CORS request, so send it
			xhr.send();
		}
		
		function getHumanWins() {
			// First create a CORS request, this is the message we are going to send (a get request in this case)
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/humanWonMatches"); // Request type and URL
			// Message is not sent yet, but we can check that the browser supports CORS
			if (!xhr) {
				alert("CORS not supported");
			}
			// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
			// to do when the response arrives
			xhr.onload = function(e) {
				var responseText = xhr.response; // the text of the response
				
				var totalGamesSpan = document.getElementById('humanWins');
		
				totalGamesSpan.innerHTML = responseText;
			};
			// We have done everything we need to prepare the CORS request, so send it
			xhr.send();
		}
		
		function getAverageDraws() {
			// First create a CORS request, this is the message we are going to send (a get request in this case)
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/averageNumberOfDraws"); // Request type and URL
			// Message is not sent yet, but we can check that the browser supports CORS
			if (!xhr) {
				alert("CORS not supported");
			}
			// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
			// to do when the response arrives
			xhr.onload = function(e) {
				var responseText = xhr.response; // the text of the response
				
				var totalGamesSpan = document.getElementById('averageDraws');
		
				totalGamesSpan.innerHTML = responseText;
			};
			// We have done everything we need to prepare the CORS request, so send it
			xhr.send();
		}
		
		function getLargestRoundsPerGame() {
			// First create a CORS request, this is the message we are going to send (a get request in this case)
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/highestNumberOfRounds"); // Request type and URL
			// Message is not sent yet, but we can check that the browser supports CORS
			if (!xhr) {
				alert("CORS not supported");
			}
			// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
			// to do when the response arrives
			xhr.onload = function(e) {
				var responseText = xhr.response; // the text of the response
				
				var totalGamesSpan = document.getElementById('highestRoundsPerGame');
		
				totalGamesSpan.innerHTML = responseText;
			};
			// We have done everything we need to prepare the CORS request, so send it
			xhr.send();
		}
	
		// This calls the helloJSONList REST method from TopTrumpsRESTAPI
		function helloJSONList() {
			// First create a CORS request, this is the message we are going to send (a get request in this case)
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/CardList"); // Request type and URL
			// Message is not sent yet, but we can check that the browser supports CORS
			if (!xhr) {
				alert("CORS not supported");
			}
			// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
			// to do when the response arrives
			xhr.onload = function(e) {
				var responseText = xhr.response; // the text of the response
				alert(responseText); // lets produce an alert
			};
			// We have done everything we need to prepare the CORS request, so send it
			xhr.send();
		}
		// This calls the helloJSONList REST method from TopTrumpsRESTAPI
		function helloWord(word) {
			// First create a CORS request, this is the message we are going to send (a get request in this case)
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/hellocards?ListofCards=" + ListofCards); // Request type and URL+parameters
			// Message is not sent yet, but we can check that the browser supports CORS
			if (!xhr) {
				alert("CORS not supported");
			}
			// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
			// to do when the response arrives
			xhr.onload = function(e) {
				var responseText = xhr.response; // the text of the response
				alert(responseText); // lets produce an alert
			};
			// We have done everything we need to prepare the CORS request, so send it
			xhr.send();
		}
	</script>
