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


</style>

<body onload="initalize()">
	<!-- Call the initalize method when the page loads -->
	<!-- HEAD -->
<!-- <h1>Top Trumps STAR CITIZEN</h1> -->

<div class="flex-container">
	<div class="container">

		<!-- Add your HTML Here -->
		<div class="card-deck">
		<div class="play">
			<div class="card border-light mb-3" style="max-width: 20rem; height: 25rem;">
				<div href="/document" style="height:100%;">
					<img class="card-img-top" src="http://dcs.gla.ac.uk/~richardm/TopTrumps/350r.jpg" alt=“350r”>
					<div class="card-block">
						<br />
						<center>
							<h4><b>Player Card</b></h4>
							<br />
							<p class="card-text">Description: 350R <br /> Size: 1 <br /> Speed: 9 <br /> Range: 2 <br /> Firepower: 3 <br /> Cargo: 0 <br /> Current Cards: 5 <br /></p>
							<br />

					</div>
				</div>
			</div>
		</div>

		<div class=“ai1”>
			<div class="card border-light mb-3" style="max-width: 20rem; height: 25rem;">
				<div href="/document" style="height:100%;">
					<img class="card-img-top" src="http://dcs.gla.ac.uk/~richardm/TopTrumps/Avenger.jpg" alt=“Avenger”>
					<div class="card-block">
						<br />
						<center>
							<h4><b>Ai1</b></h4>
							<br />
							<p class="card-text">Description: Avenger <br /> Size: 2 <br /> Speed: 5 <br /> Range: 4 <br /> Firepower: 3 <br /> Cargo: 2 <br /> Current Cards: 5 <br /></p>
							<br />

					</div>
				</div>
			</div>
		</div>

			<div class=“ai2”>
			<div class="card border-light mb-3" style="max-width: 20rem; height: 25rem;">
				<div href="/document" style="height:100%;">
					<img class="card-img-top" src="http://dcs.gla.ac.uk/~richardm/TopTrumps/Carrack.jpg" alt=“350r”>
					<div class="card-block">
						<br />
						<center>
							<h4><b>Ai2</b></h4>
							<br />
							<p class="card-text">Description: Carrack <br /> Size: 6 <br /> Speed: 2 <br /> Range: 10 <br /> Firepower: 4 <br /> Cargo: 6 <br /> Current Cards: 5 <br /></p>
							<br />

					</div>
				</div>
			</div>
		</div>

			<div class=“ai3”>
			<div class="card border-light mb-3" style="max-width: 20rem; height: 25rem;">
				<div href="/document" style="height:100%;">
					<img class="card-img-top" src="http://dcs.gla.ac.uk/~richardm/TopTrumps/Constellation.jpg" alt=“350r”>
					<div class="card-block">
						<br />
						<center>
							<h4><b>Ai3</b></h4>
							<br />
							<p class="card-text">Description: Constellation <br /> Size: 4 <br /> Speed: 5 <br /> Range: 7 <br /> Firepower: 3 <br /> Cargo: 4 <br /> Current Cards: 5 <br /></p>
							<br />

					</div>
				</div>
			</div>
		</div>
			<div class=“ai4”>
			<div class="card border-light mb-3" style="max-width: 20rem; height: 25rem;">
				<div href="/document" style="height:100%;">
					<img class="card-img-top" src="http://dcs.gla.ac.uk/~richardm/TopTrumps/Hawk.jpg" alt=“350r”>
					<div class="card-block">
						<br />
						<center>
							<h4><b>Ai4</b></h4>
							<br />
							<p class="card-text">Description: Hawk <br /> Size: 1 <br /> Speed: 3 <br /> Range: 2 <br /> Firepower: 4 <br /> Cargo: 0 <br /> Current Cards: 5<br /</p>
							<br />

					</div>
				</div>
			</div>
		</div>
		<div clas="selection">
		<div class="card border-light mb-3" style="max-width: 20rem; height: 10rem;">
		<div class="card-block">
						<br />
						<center>
							<h4><b>Category</b></h4>
							<br />
							<p class="card-text">Current Category: Speed <br /> AI1 chose this category<br /></p>
							<br />

					</div>
				</div>
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
			// For example, lets call our sample methods

			var gameID;
			startGame(x);
			communalPile();
			playerList();
			activePlayers();
		}
		// -----------------------------------------
		// Add your other Javascript methods Here
		// -----------------------------------------

			function start()
			{
				firstChoice();
				runGame();
			}

			function runGame()
			{
				while(isValid()){
					newRound();
					activePlayers();
					currentCard();
					var category;
					if(firstChoice().playerid != 1)
					{
						category = categorySelection();
					}
					findWinner();
					isWinner();
				}

			}

			function isValid()
			{
				while(activePlayers)
			}

        	function myFunction(){
            	var x;
           		var person=prompt("Please enter your name","");
            	if (person!=null)
            	{
              		x="Hello " + person + "! How are you today?";
              		document.getElementById("demo").innerHTML=x;
              	}
            }


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

		function startGame(username){
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/startGame?username=" + username); // Request type and URL

			if (!xhr) {
				alert("CORS not supported");
			}

			xhr.onload = function(e) {
				var responseText = xhr.response; // the text of the response
				gameID = responseText;
				alert(responseText); // lets produce an alert
			};

			xhr.send();
		}

		function playerList(){
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/playerList"); // Request type and URL

			if (!xhr) {
				alert("CORS not supported");
			}

			xhr.onload = function(e) {
				var responseText = xhr.response; // the text of the response
				var playerList = JSON.parse(responseText);
				alert(playerList); // lets produce an alert
			};

			xhr.send();
		}

		function activePlayers(){
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/activePlayers"); // Request type and URL

			if (!xhr) {
				alert("CORS not supported");
			}

			xhr.onload = function(e) {
				var responseText = xhr.response; // the text of the response
				var activePlayers = JSON.parse(responseText);
				alert(activePlayers); // lets produce an alert
			};

			xhr.send();
		}

		function communalPile(){
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/communalPile"); // Request type and URL

			if (!xhr) {
				alert("CORS not supported");
			}

			xhr.onload = function(e) {
				var responseText = xhr.response; // the text of the response
				var communalPile = JSON.parse(responseText);
				alert(communalPile); // lets produce an alert
			};

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

</body>

</html>
