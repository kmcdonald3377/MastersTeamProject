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

	<body onload="initalize()">

		<h1>Top Trumps</h1>
		<div id="mainSection">
			<p>
				<h2>Round <span id="roundDisplay"></span></h2>
				<br>
				<h3>Rounds Drawn: <span id="roundDrawnDisplay"></span></h3>
				<br>
				<span id="gameProgression"></span>
				<br>
				<span id="gameProgression2"></span>
			</p>
			<input placeholder="player name" id="playerN" type="text"/>
			<br>
			<span id="input">Number of AI Players:</span>
			<br>
			<select id="noOfPlayers" size="4" value="1">
				<option>1</option>
				<option>2</option>
				<option>3</option>
				<option>4</option>
			</select>
			<button id="newGameBtn" onclick="startGame()">Start Game</button>
			<button id="startRound" onclick="startRound()" style="display:none">Start Round</button>
			<button id="revealCards" onclick="revealCards()" style="display:none">Reveal Cards</button>
			<button id="continue" onclick="choice()" style="display:none">Continue</button>
			<button id="mainMenu" onclick="choice()" style="display:none" ><a href="http://localhost:7777/toptrumps">Main Menu</a></button>

			<div class="container">

				<div class="player" id="player" style="display:none">
						<h4><b><span id="playerNamePlacement"></span></b></h4>
					<div class="card border-light mb-3" style="max-width: 16rem; height: 25rem;">
						<div href="/document" style="height:100%;">
							<center>
								<h4 id="cardName"><b>Name of card here</b></h4>
							</center>
							<img class="card-img-top" src="https://i.imgur.com/PjPKwx9.png" alt="PLAY GAME">
							<div class="card-block">
								<br />
								<left>
									<button id="attribute1" disabled="true" onclick="att1()">Attribute 1:</button><span id="value1"></span>
									<br>
									<button id="attribute2" disabled="true" onclick="att2()">Attribute 2:</button><span id="value2"></span>
									<br>
									<button id="attribute3" disabled="true" onclick="att3()">Attribute 3:</button><span id="value3"></span>
									<br>
									<button id="attribute4" disabled="true" onclick="att4()">Attribute 4:</button><span id="value4"></span>
									<br>
									<button id="attribute5" disabled="true" onclick="att5()">Attribute 5:</button><span id="value5"></span>
									<br>
								</left>
							</div>
						</div>
					</div>
					<h5><b>Cards Remaining: <span id="playerCards"></span></b></h5>
				</div>

				<div class="ai1" id="ai1" style="display:none">
						<h4><b>AI Player 1</b></h4>
					<div class="card border-light mb-3" style="max-width: 16rem; height: 25rem;">
						<div href="/document" style="height:100%;">
							<center>
								<h4 id="ai1CardName"><b>Name of card here</b></h4>
							</center>
							<img class="card-img-top" src="https://i.imgur.com/PjPKwx9.png" alt="PLAY GAME">
							<div class="card-block">
								<br />
								<left>
									<span id="ai1attribute1">Attribute 1: </span><span id="ai1value1"></span>
									<br>
									<span id="ai1attribute2">Attribute 2: </span><span id="ai1value2"></span>
									<br>
									<span id="ai1attribute3">Attribute 3: </span><span id="ai1value3"></span>
									<br>
									<span id="ai1attribute4">Attribute 4: </span><span id="ai1value4"></span>
									<br>
									<span id="ai1attribute5">Attribute 5: </span><span id="ai1value5"></span>
									<br>
								</left>
							</div>
						</div>
					</div>
					<h5><b>Cards Remaining: <span id="ai1Cards"></span></b></h5>
				</div>

				<div class="ai2" id="ai2" style="display:none">
						<h4><b>AI Player 2</b></h4>
					<div class="card border-light mb-3" style="max-width: 16rem; height: 25rem;">
						<div href="/document" style="height:100%;">
							<center>
								<h4 id="ai2CardName"><b>Name of card here</b></h4>
							</center>
							<img class="card-img-top" src="https://i.imgur.com/PjPKwx9.png" alt="PLAY GAME">
							<div class="card-block">
								<br />
								<left>
									<span id="ai2attribute1">Attribute 1: </span><span id="ai2value1"></span>
									<br>
									<span id="ai2attribute2">Attribute 2: </span><span id="ai2value2"></span>
									<br>
									<span id="ai2attribute3">Attribute 3: </span><span id="ai2value3"></span>
									<br>
									<span id="ai2attribute4">Attribute 4: </span><span id="ai2value4"></span>
									<br>
									<span id="ai2attribute5">Attribute 5: </span><span id="ai2value5"></span>
									<br>
								</left>
							</div>
						</div>
					</div>
					<h5><b>Cards Remaining: <span id="ai2Cards"></span></b></h5>
				</div>

				<div class="ai3" id="ai3" style="display:none">
						<h4><b>AI Player 3</b></h4>
					<div class="card border-light mb-3" style="max-width: 16rem; height: 25rem;">
						<div href="/document" style="height:100%;">
							<center>
								<h4 id="ai3CardName"><b>Name of card here</b></h4>
							</center>
							<img class="card-img-top" src="https://i.imgur.com/PjPKwx9.png" alt="PLAY GAME">
							<div class="card-block">
								<br />
								<left>
									<span id="ai3attribute1">Attribute 1: </span><span id="ai3value1"></span>
									<br>
									<span id="ai3attribute2">Attribute 2: </span><span id="ai3value2"></span>
									<br>
									<span id="ai3attribute3">Attribute 3: </span><span id="ai3value3"></span>
									<br>
									<span id="ai3attribute4">Attribute 4: </span><span id="ai3value4"></span>
									<br>
									<span id="ai3attribute5">Attribute 5: </span><span id="ai3value5"></span>
									<br>
								</left>
							</div>
						</div>
					</div>
					<h5><b>Cards Remaining: <span id="ai3Cards"></span></b></h5>
				</div>

				<div class="ai4" id="ai4" style="display:none">
					<h4><b>AI Player 4</b></h4>
					<div class="card border-light mb-3" style="max-width: 16rem; height: 25rem;">
						<div href="/document" style="height:100%;">
							<center>
								<h4 id="ai4CardName"><b>Name of card here</b></h4>
							</center>
							<img class="card-img-top" src="https://i.imgur.com/PjPKwx9.png" alt="PLAY GAME">
							<div class="card-block">
								<br />
								<left>
									<span id="ai4attribute1">Attribute 1: </span><span id="ai4value1"></span>
									<br>
									<span id="ai4attribute2">Attribute 2: </span><span id="ai4value2"></span>
									<br>
									<span id="ai4attribute3">Attribute 3: </span><span id="ai4value3"></span>
									<br>
									<span id="ai4attribute4">Attribute 4: </span><span id="ai4value4"></span>
									<br>
									<span id="ai4attribute5">Attribute 5: </span><span id="ai4value5"></span>
									<br>
								</left>
							</div>
						</div>
					</div>
					<h5><b>Cards Remaining: <span id="ai4Cards"></span></b></h5>
				</div>

				<div class="communal" id="communal" style="display:none">
						<h4><b>Communal Pile</b></h4>
						<div class="card border-light mb-3" style="max-width: 16rem; height: 25rem;">
							<div href="/document" style="height:100%;">
								<img class="card-img-top" src="https://i.imgur.com/PjPKwx9.png" alt="PLAY GAME">
							</div>
						</div>
						<h5><b>Cards in Communal Pile: <span id="communalPileCards"></span></b></h5>
					</div>

			</div>

		</div>

		








		<script type="text/javascript">

			var matchID = null;
			var playerName = null;
			var aiPlayerCount = null;
			var players = {};
			var activePlayers = {};
			var handSizes = [];
			var comPile = {};
			var playersTurn = null;
			var currentRound = null;
			var category = null;
			var maxScore = null;
			var isWinner = false;
			var winners = {};
			var rounds = null;
			var roundsDrawn = null;
			var removed = {};

			// Method that is called on page load
			function initalize() {
			
			}
			
			function startGame(){
				playerName = document.getElementById('playerN').value;
				aiPlayerCount = document.getElementById("noOfPlayers").value;
				document.getElementById('playerNamePlacement').innerHTML = playerName;
				document.getElementById('playerN').style.display = 'none';
				document.getElementById('newGameBtn').style.display = 'none';
				document.getElementById('input').style.display = 'none';
				document.getElementById('noOfPlayers').style.display = 'none';
				document.getElementById('startRound').style.display = 'block';

				newGame();
				getPlayers();
				activeP();
				communalPile();
				firstChoice();
			}

			function startRound(){
				endOfRound();
				document.getElementById('startRound').style.display = 'none';
				round();
				increaseRoundsPlayed();
				totalRounds();
				totalDraws();
				document.getElementById('roundDisplay').innerHTML = rounds;
				document.getElementById('roundDrawnDisplay').innerHTML = roundsDrawn;
				displayPlayerCard();
				playerHandSizes();
				
				if(playersTurn == 1){
					document.getElementById('gameProgression').innerHTML = playerName + " it is your choice to select a category.";
					document.getElementById('attribute1').disabled = false;
					document.getElementById('attribute2').disabled = false;
					document.getElementById('attribute3').disabled = false;
					document.getElementById('attribute4').disabled = false;
					document.getElementById('attribute5').disabled = false;
				}
				else if(playersTurn == 2){
					document.getElementById('gameProgression').innerHTML = "It is AI Player 1's choice to select a category.";
					document.getElementById('continue').style.display = 'block';
				}
				else if(playersTurn == 3){
					document.getElementById('gameProgression').innerHTML = "It is AI Player 2's choice to select a category.";
					document.getElementById('continue').style.display = 'block';
				}
				else if(playersTurn == 4){
					document.getElementById('gameProgression').innerHTML = "It is AI Player 3's choice to select a category.";
					document.getElementById('continue').style.display = 'block';
				}
				else{
					document.getElementById('gameProgression').innerHTML = "It is AI Player 4's choice to select a category";
					document.getElementById('continue').style.display = 'block';
				}
			}

			function choice(){
				document.getElementById('continue').style.display = 'none';
				document.getElementById('attribute1').disabled = true;
				document.getElementById('attribute2').disabled = true;
				document.getElementById('attribute3').disabled = true;
				document.getElementById('attribute4').disabled = true;
				document.getElementById('attribute5').disabled = true;
				if(playersTurn != 1){
					aiCatSel();
				}
				document.getElementById('gameProgression').innerHTML = "The category " + category + " has been selected!";
				document.getElementById('revealCards').style.display = 'block';
			}
			
			function revealCards(){
				showCards();
				findMax();
				isAWinner();
				findWinners();

				if(isWinner == 1){
					var winner = "";
					if(winners[0].playerID == 1){winner = playerName;}
					else if(winners[0].playerID == 2){winner = "AI Player 1";}
					else if(winners[0].playerID == 3){winner = "AI Player 2";}
					else if(winners[0].playerID == 4){winner = "AI Player 3";}
					else if(winners[0].playerID == 5){winner = "AI Player 4";}
					document.getElementById('gameProgression').innerHTML = winner + " wins the round with " + maxScore + " in the category " + category + "!";
					roundWin();
					getPlayers();
					activeP();
					playersTurn = winners[0].playerID;
				}
				else{
					var winningPlayers = "";
					for(i=0; i < winners.length; i++){
						var winner = "";
						if(winners[i].playerID == 1){winner = playerName;}
						else if(winners[i].playerID == 2){winner = "AI Player 1";}
						else if(winners[i].playerID == 3){winner = "AI Player 2";}
						else if(winners[i].playerID == 4){winner = "AI Player 3";}
						else if(winners[i].playerID == 5){winner = "AI Player 4";}

						winningPlayers += winner + ", "
					}
					document.getElementById('gameProgression').innerHTML = winningPlayers + "has drawn the round with " + maxScore + " in the category " + category + 
					"! All players cards have been forfeited to the communal pile!";
					roundDraw();
					getPlayers();
					activeP();
					communalPile();
					increaseDraws();
				}
					
				playersToBeRemoved();
				removedFromActivePlayers();
				var removedPlayers = "";
				if(removed.length != 0){
					for(i=0; i < removed.length; i++){
						var rmvPlayers = "";
						if(removed[i].playerID == 1){rmvPlayers = playerName;}
						else if(removed[i].playerID == 2){rmvPlayers = "AI Player 1";}
						else if(removed[i].playerID == 3){rmvPlayers = "AI Player 2";}
						else if(removed[i].playerID == 4){rmvPlayers = "AI Player 3";}
						else{rmvPlayers = "AI Player 4";}
					removedPlayers += rmvPlayers + ", "
				}
				document.getElementById('gameProgression2').innerHTML = removedPlayers + "have run out of cards! They have been removed from the game! " + activePlayers.length + 
				" players remain!";
				}

				if(!isValid())
				{
					endGame();
				}
				else{
					document.getElementById('startRound').style.display = 'block';
				}
			}

			function showCards(){
				document.getElementById('revealCards').style.display = 'none';
				displayOpponentsCard();
				document.getElementById('ai1').style.display = 'block';
				document.getElementById('ai1Cards').innerHTML = players[1].playerHand.numberOfCards;


				if(aiPlayerCount >= 2){
					document.getElementById('ai2').style.display = 'block';
					document.getElementById('ai2Cards').innerHTML = players[2].playerHand.numberOfCards;

					if(aiPlayerCount >= 3){
						document.getElementById('ai3').style.display = 'block';
						document.getElementById('ai3Cards').innerHTML = players[3].playerHand.numberOfCards;

						if(aiPlayerCount == 4){
							document.getElementById('ai4').style.display = 'block';
							document.getElementById('ai4Cards').innerHTML = players[4].playerHand.numberOfCards;
						}
					}
				}
			}

			function isValid(){
				if(activePlayers.length > 1)
				{
					return true;
				}
				else
				{
					return false;
				}
			}

			function endGame(){
				if(activePlayers[0].playerID == 1){
					document.getElementById('gameProgression').innerHTML = "Congratulations you have won!";
				}
				else{
					document.getElementById('gameProgression').innerHTML = "AI Player " + (activePlayers[0].playerID + 1) + " has won!";
				}
				document.getElementById('gameProgression2').innerHTML = "";
				document.getElementById('mainMenu').style.display = "block";
			}

			function endOfRound(){
				document.getElementById('ai1').style.display = 'none';
				document.getElementById('ai2').style.display = 'none';
				document.getElementById('ai3').style.display = 'none';
				document.getElementById('ai4').style.display = 'none';
				document.getElementById('communal').style.display = 'none';
			}

			function displayPlayerCard(){
				if(activePlayers[0].playerHand.size != 0){
					document.getElementById('player').style.display = 'block';
					document.getElementById("cardName").innerHTML = activePlayers[0].playerHand.currentCard.name;
					document.getElementById("attribute1").innerHTML = activePlayers[0].playerHand.currentCard.attribute1;
					document.getElementById("attribute2").innerHTML = activePlayers[0].playerHand.currentCard.attribute2;
					document.getElementById("attribute3").innerHTML = activePlayers[0].playerHand.currentCard.attribute3;
					document.getElementById("attribute4").innerHTML = activePlayers[0].playerHand.currentCard.attribute4;
					document.getElementById("attribute5").innerHTML = activePlayers[0].playerHand.currentCard.attribute5;

					document.getElementById("value1").innerHTML = activePlayers[0].playerHand.currentCard.value1;
					document.getElementById("value2").innerHTML = activePlayers[0].playerHand.currentCard.value2;
					document.getElementById("value3").innerHTML = activePlayers[0].playerHand.currentCard.value3;
					document.getElementById("value4").innerHTML = activePlayers[0].playerHand.currentCard.value4;
					document.getElementById("value5").innerHTML = activePlayers[0].playerHand.currentCard.value5;

					document.getElementById('playerCards').innerHTML = players[0].playerHand.numberOfCards;	
				}

				document.getElementById('communal').style.display = 'block';
				document.getElementById('communalPileCards').innerHTML = comPile.numberOfCards;
			}

			function displayOpponentsCard(){
				for(i = 0; i< activePlayers.length; i++){
					if(activePlayers[i].playerID == 2){			
						document.getElementById("ai1CardName").innerHTML = activePlayers[i].playerHand.currentCard.name;
						document.getElementById("ai1attribute1").innerHTML = activePlayers[i].playerHand.currentCard.attribute1;
						document.getElementById("ai1attribute2").innerHTML = activePlayers[i].playerHand.currentCard.attribute2;
						document.getElementById("ai1attribute3").innerHTML = activePlayers[i].playerHand.currentCard.attribute3;
						document.getElementById("ai1attribute4").innerHTML = activePlayers[i].playerHand.currentCard.attribute4;
						document.getElementById("ai1attribute5").innerHTML = activePlayers[i].playerHand.currentCard.attribute5;

						document.getElementById("ai1value1").innerHTML = activePlayers[i].playerHand.currentCard.value1;
						document.getElementById("ai1value2").innerHTML = activePlayers[i].playerHand.currentCard.value2;
						document.getElementById("ai1value3").innerHTML = activePlayers[i].playerHand.currentCard.value3;
						document.getElementById("ai1value4").innerHTML = activePlayers[i].playerHand.currentCard.value4;
						document.getElementById("ai1value5").innerHTML = activePlayers[i].playerHand.currentCard.value5;
					}

					if(activePlayers[i].playerID == 3){	
						document.getElementById("ai2CardName").innerHTML = activePlayers[i].playerHand.currentCard.name;
						document.getElementById("ai2attribute1").innerHTML = activePlayers[i].playerHand.currentCard.attribute1;
						document.getElementById("ai2attribute2").innerHTML = activePlayers[i].playerHand.currentCard.attribute2;
						document.getElementById("ai2attribute3").innerHTML = activePlayers[i].playerHand.currentCard.attribute3;
						document.getElementById("ai2attribute4").innerHTML = activePlayers[i].playerHand.currentCard.attribute4;
						document.getElementById("ai2attribute5").innerHTML = activePlayers[i].playerHand.currentCard.attribute5;

						document.getElementById("ai2value1").innerHTML = activePlayers[i].playerHand.currentCard.value1;
						document.getElementById("ai2value2").innerHTML = activePlayers[i].playerHand.currentCard.value2;
						document.getElementById("ai2value3").innerHTML = activePlayers[i].playerHand.currentCard.value3;
						document.getElementById("ai2value4").innerHTML = activePlayers[i].playerHand.currentCard.value4;
						document.getElementById("ai2value5").innerHTML = activePlayers[i].playerHand.currentCard.value5;
					}
					
					if(activePlayers[i].playerID == 4){	
						document.getElementById("ai3CardName").innerHTML = activePlayers[i].playerHand.currentCard.name;
						document.getElementById("ai3attribute1").innerHTML = activePlayers[i].playerHand.currentCard.attribute1;
						document.getElementById("ai3attribute2").innerHTML = activePlayers[i].playerHand.currentCard.attribute2;
						document.getElementById("ai3attribute3").innerHTML = activePlayers[i].playerHand.currentCard.attribute3;
						document.getElementById("ai3attribute4").innerHTML = activePlayers[i].playerHand.currentCard.attribute4;
						document.getElementById("ai3attribute5").innerHTML = activePlayers[i].playerHand.currentCard.attribute5;

						document.getElementById("ai3value1").innerHTML = activePlayers[i].playerHand.currentCard.value1;
						document.getElementById("ai3value2").innerHTML = activePlayers[i].playerHand.currentCard.value2;
						document.getElementById("ai3value3").innerHTML = activePlayers[i].playerHand.currentCard.value3;
						document.getElementById("ai3value4").innerHTML = activePlayers[i].playerHand.currentCard.value4;
						document.getElementById("ai3value5").innerHTML = activePlayers[i].playerHand.currentCard.value5;
					}
					
					if(activePlayers[i].playerID == 5){	
						document.getElementById("ai4CardName").innerHTML = activePlayers[i].playerHand.currentCard.name;
						document.getElementById("ai4attribute1").innerHTML = activePlayers[i].playerHand.currentCard.attribute1;
						document.getElementById("ai4attribute2").innerHTML = activePlayers[i].playerHand.currentCard.attribute2;
						document.getElementById("ai4attribute3").innerHTML = activePlayers[i].playerHand.currentCard.attribute3;
						document.getElementById("ai4attribute4").innerHTML = activePlayers[i].playerHand.currentCard.attribute4;
						document.getElementById("ai4attribute5").innerHTML = activePlayers[i].playerHand.currentCard.attribute5;

						document.getElementById("ai4value1").innerHTML = activePlayers[i].playerHand.currentCard.value1;
						document.getElementById("ai4value2").innerHTML = activePlayers[i].playerHand.currentCard.value2;
						document.getElementById("ai4value3").innerHTML = activePlayers[i].playerHand.currentCard.value3;
						document.getElementById("ai4value4").innerHTML = activePlayers[i].playerHand.currentCard.value4;
						document.getElementById("ai4value5").innerHTML = activePlayers[i].playerHand.currentCard.value5;
					}
				}
			}

			function att1(){
				category = activePlayers["0"].playerHand.currentCard.attribute1;
				choice();
			}

			function att2(){
				category = activePlayers["0"].playerHand.currentCard.attribute2;
				choice();
			}

			function att3(){
				category = activePlayers["0"].playerHand.currentCard.attribute3;
				choice();
			}

			function att4(){
				category = activePlayers["0"].playerHand.currentCard.attribute4;
				choice();
			}

			function att5(){
				category = activePlayers["0"].playerHand.currentCard.attribute5;
				choice();
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

			function newGame(){
                var xhr = createCORSRequest("POST", "http://localhost:7777/toptrumps/startGame?username=" + playerName + "&numberOfPlayers=" + aiPlayerCount, false)

                xhr.onload = function (e) {
                    matchID = xhr.response;
                };

                xhr.send();
			}

			function getPlayers(){
				var xhr = createCORSRequest("GET", "http://localhost:7777/toptrumps/playerList?matchID=" + matchID, false);
				if (!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function (e) {
					players = JSON.parse(xhr.response);
				};
				xhr.send();
			}

			function activeP(){
				var xhr = createCORSRequest("GET", "http://localhost:7777/toptrumps/activePlayers?matchID=" + matchID, false);
				if (!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function (e) {
					activePlayers = JSON.parse(xhr.response);
				};
				xhr.send();
			}

			function communalPile(){
				var xhr = createCORSRequest("GET", "http://localhost:7777/toptrumps/communalPile?matchID=" + matchID, false);
				if (!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function (e) {
					comPile = JSON.parse(xhr.response);
				};
				xhr.send();
			}

			function playerHandSizes(){
				var xhr = createCORSRequest("GET", "http://localhost:7777/toptrumps/playerHandSizes?matchID=" + matchID, false);
				if (!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function (e) {
					handSizes = JSON.parse(xhr.response);
				};
				xhr.send();
			}

			function firstChoice(){
				var xhr = createCORSRequest("GET", "http://localhost:7777/toptrumps/firstPlayer?matchID=" + matchID, false);
				if (!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function (e) {
					playersTurn = xhr.response;
				};
				xhr.send();
			}

			function round(){
				var xhr = createCORSRequest("POST", "http://localhost:7777/toptrumps/newRound?matchID=" + matchID, false);
				if (!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function (e) {
					currentRound = xhr.response;
				};
				xhr.send();
			}

			function aiCatSel(){
				var xhr = createCORSRequest("GET", "http://localhost:7777/toptrumps/categorySelection?matchID=" + matchID, false);
				if (!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function (e) {
					category = xhr.response;
				};
				xhr.send();
			}

			function findMax(){
				console.log(category);
				var xhr = createCORSRequest("GET", "http://localhost:7777/toptrumps/maxScore?matchID=" + matchID + "&Category=" + category, false);
				if (!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function (e) {
					maxScore = xhr.response;
				};
				xhr.send();
			}

			function isAWinner(){
				var xhr = createCORSRequest("GET", "http://localhost:7777/toptrumps/isWinner?matchID=" + matchID + "&Category=" + category, false);
				if (!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function (e) {
					isWinner = parseInt(xhr.response);
				};
				xhr.send();
			}

			function findWinners(){
				var xhr = createCORSRequest("GET", "http://localhost:7777/toptrumps/findWinner?matchID=" + matchID + "&Category=" + category, false);
				if (!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function (e) {
					winners = JSON.parse(xhr.response);
				};
				xhr.send();
			}

			function totalRounds(){
				var xhr = createCORSRequest("GET", "http://localhost:7777/toptrumps/totalRounds?matchID=" + matchID, false);
				if (!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function (e) {
					rounds = xhr.response;
				};
				xhr.send();
			}

			function increaseRoundsPlayed(){
				var xhr = createCORSRequest("POST", "http://localhost:7777/toptrumps/increaseRounds?matchID=" + matchID, false);
				if (!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function (e) {
					var test = xhr.response;
				};
				xhr.send();
			}

			function totalDraws(){
				var xhr = createCORSRequest("GET", "http://localhost:7777/toptrumps/totalDraws?matchID=" + matchID, false);
				if (!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function (e) {
					roundsDrawn = xhr.response;
				};
				xhr.send();
			}

			function increaseDraws(){
				var xhr = createCORSRequest("POST", "http://localhost:7777/toptrumps/increaseDraws?matchID=" + matchID, false);
				if (!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function (e) {
					xhr.response;
				};
				xhr.send();
			}

			function playersToBeRemoved(){
				var xhr = createCORSRequest("GET", "http://localhost:7777/toptrumps/playersToBeRemoved?matchID=" + matchID, false);
				if (!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function (e) {
					removed = JSON.parse(xhr.response);
				};
				xhr.send();
			}

			function removedFromActivePlayers(){
				var xhr = createCORSRequest("GET", "http://localhost:7777/toptrumps/removedFromActivePlayers?matchID=" + matchID, false);
				if (!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function (e) {
					activePlayers = JSON.parse(xhr.response);
				};
				xhr.send();
			}

			function roundWin(){
				var xhr = createCORSRequest("POST", "http://localhost:7777/toptrumps/computeRoundWin?matchID=" + matchID + "&Category=" + category, false);
				if (!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function (e) {
					xhr.response;
				};
				xhr.send();
			}

			function roundDraw(){
				var xhr = createCORSRequest("POST", "http://localhost:7777/toptrumps/computeRoundDraw?matchID=" + matchID + "&Category=" + category, false);
				if (!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function (e) {
					xhr.response;
				};
				xhr.send();
			}

		</script>
	</body>
</html>
