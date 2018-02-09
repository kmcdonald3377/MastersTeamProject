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
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

	</head>

	<body onload="initalize()" style="background-colour:black;">

		<h1>Top Trumps</h1>
		<div id="mainSection">
			<input placeholder="player name" id="playername" type="text"/>
			<button id="startGameBtn" onclick="startGame()">Start Game</button>
			<br>
			<span id="input">Number of AI Players:</span>
			<br>
			<select id="aiCount" size="4" value="1">
				<option>1</option>
				<option>2</option>
				<option>3</option>
				<option>4</option>
			</select>

			<div class="container">

				<div class="player">
					<div class="card border-light mb-3" style="max-width: 16rem; height: 25rem;">
						<div href="/document" style="height:100%;">
							<center>
								<h4><b>Name of card here</b></h4>
							</center>
							<img class="card-img-top" src="https://i.imgur.com/PjPKwx9.png" alt="PLAY GAME">
							<div class="card-block">
								<br />
								<left>
									<button>Attribute 1:</button>
									<br>
									<button>Attribute 2:</button>
									<br>
									<button>Attribute 3:</button>
									<br>
									<button>Attribute 4:</button>
									<br>
									<button>Attribute 5:</button>
									<br>
								</left>
							</div>
						</div>
					</div>
				</div>

				<div class="ai1">
					<div class="card border-light mb-3" style="max-width: 16rem; height: 25rem;">
						<div href="/document" style="height:100%;">
							<center>
								<h4><b>Name of card here</b></h4>
							</center>
							<img class="card-img-top" src="https://i.imgur.com/PjPKwx9.png" alt="PLAY GAME">
							<div class="card-block">
								<br />
								<left>
									<button>Attribute 1:</button>
									<br>
									<button>Attribute 2:</button>
									<br>
									<button>Attribute 3:</button>
									<br>
									<button>Attribute 4:</button>
									<br>
									<button>Attribute 5:</button>
									<br>
								</left>
							</div>
						</div>
					</div>
				</div>

				<div class="ai2">
					<div class="card border-light mb-3" style="max-width: 16rem; height: 25rem;">
						<div href="/document" style="height:100%;">
							<center>
								<h4><b>Name of card here</b></h4>
							</center>
							<img class="card-img-top" src="https://i.imgur.com/PjPKwx9.png" alt="PLAY GAME">
							<div class="card-block">
								<br />
								<left>
									<button>Attribute 1:</button>
									<br>
									<button>Attribute 2:</button>
									<br>
									<button>Attribute 3:</button>
									<br>
									<button>Attribute 4:</button>
									<br>
									<button>Attribute 5:</button>
									<br>
								</left>
							</div>
						</div>
					</div>
				</div>

				<div class="ai3">
					<div class="card border-light mb-3" style="max-width: 16rem; height: 25rem;">
						<div href="/document" style="height:100%;">
							<center>
								<h4><b>Name of card here</b></h4>
							</center>
							<img class="card-img-top" src="https://i.imgur.com/PjPKwx9.png" alt="PLAY GAME">
							<div class="card-block">
								<br />
								<left>
									<button>Attribute 1:</button>
									<br>
									<button>Attribute 2:</button>
									<br>
									<button>Attribute 3:</button>
									<br>
									<button>Attribute 4:</button>
									<br>
									<button>Attribute 5:</button>
									<br>
								</left>
							</div>
						</div>
					</div>
				</div>

				<div class="ai4">
					<div class="card border-light mb-3" style="max-width: 16rem; height: 25rem;">
						<div href="/document" style="height:100%;">
							<center>
								<h4><b>Name of card here</b></h4>
							</center>
							<img class="card-img-top" src="https://i.imgur.com/PjPKwx9.png" alt="PLAY GAME">
							<div class="card-block">
								<br />
								<left>
									<button>Attribute 1:</button>
									<br>
									<button>Attribute 2:</button>
									<br>
									<button>Attribute 3:</button>
									<br>
									<button>Attribute 4:</button>
									<br>
									<button>Attribute 5:</button>
									<br>
								</left>
							</div>
						</div>
					</div>
				</div>

					








			</div>




		</div>

		








		<script type="text/javascript">

			var matchID, playerName, aiPlayerCount;
			var players = {};
			var activePlayers = {};

			// Method that is called on page load
			function initalize() {
								
			}


			
			function displayGameStart()
			{
				startGame();
				myFunction();
				playerList();
				activePlayers();
			}

			function playGame(){

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

			
			function myFunction(){
				document.getElementById('playername').style.display = 'none';
				document.getElementById('startGameBtn').style.display = 'none';
				document.getElementById('input').style.display = 'none';
				document.getElementById('aiCount').style.display = 'none';
			}


			function startGame(){
				playerName = document.getElementById("playername").value;
				aiPlayerCount = document.getElementById("aiCount").value;

				var xhr = createCORSRequest("GET", "http://localhost:7777/toptrumps/startGame?username=" + playerName + "?numberOfPlayers=" + aiCount);
				if (!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function (e) {
					matchID = xhr.response;
				};
				xhr.send();
			}

			function playerList(){
				var xhr = createCORSRequest("GET", "http://localhost:7777/toptrumps/playerList?matchID=" + matchID);
				if (!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function (e) {
					players = JSON.parse(xhr.response);
				};
				xhr.send();
			}

			function activePlayers(){
				var xhr = createCORSRequest("GET", "http://localhost:7777/toptrumps/activePlayers?matchID=" + matchID);
				if (!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function (e) {
					activePlayers = JSON.parse(xhr.response);
				};
				xhr.send();
			}

			function displayPlayerCard(){

			}

		</script>
	</body>
</html>
