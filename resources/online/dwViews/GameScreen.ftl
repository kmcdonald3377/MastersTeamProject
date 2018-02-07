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

<style>
html {
      height: 100%;
}
body {
    margin: 0;
    background: linear-gradient(Azure, Lavender);
    background-repeat: no-repeat;
    background-attachment: fixed;
}
.selectdrop {
    background-color: #F8F8FF;
    color: black;
    padding: 16px;
    font-size: 16px;
    border: none;
    cursor: pointer;
}
/* Dropdown Content (Hidden by Default) */
.selectplay-content {
    display: none;
    position: absolute;
    background-color: 	#f9f9f9;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    z-index: 1;
}
/* Links inside the dropdown */
.selectplay-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
}
/* Change color of dropdown links on hover */
.selectplay-content a:hover {background-color: Gainsboro}
/* Show the dropdown menu on hover */
.selectplay:hover .selectplay-content {
    display: block;
}
/* Change the background color of the dropdown button when the dropdown content is shown */
.selectplay:hover .selectdrop {
    background-color: grey;
}
.categorydrop {
      background-color: #F8F8FF;
    color: black;
    padding: 16px;
    font-size: 16px;
    border: none;
    cursor: pointer;
}
/* Dropdown Content (Hidden by Default) */
.category-content {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    z-index: 1;
}
/* Links inside the dropdown */
.category-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
}
/* Change color of dropdown links on hover */
.category-content a:hover {background-color: Gainsboro}
/* Show the dropdown menu on hover */
.category:hover .category-content {
    display: block;
}
/* Change the background color of the dropdown button when the dropdown content is shown */
.category:hover .categorydrop {
    background-color: grey;
}
input[type=text] {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    box-sizing: border-box;
    border: none;
    background-color: #F8F8FF;
    color: black;
}
h1 {
    color:black;
    font-size:300%;
    text-align:center;
}
</style>
</div>
</style>
<body onload="initalize()">
<!-- Call the initalize method when the page loads -->
<!-- HEAD -->
<!-- <h1>Top Trumps STAR CITIZEN</h1> -->
<div class="container">
<h1>Top Trumps : Round 1</h1>
</div>
<div class="selectplay">
  <button class="selectdrop">Players</button>
  <div class="selectplay-content">
    <a href="#">1</a>
    <a href="#">2</a>
    <a href="#">3</a>
    <a href="#">4</a>
  </div>
</div>
<form>
<input type="text" id="fname" name="fname" value=“FirstName”>
</br>
</form>
</div>
<div class="card-deck">
<div class="play">
<div class="card card-inverse" style="background-color: #F8F8FF; border-color: #333; max-width: 20rem; height: 27rem;">
<div href="/document" style="height:100%;">
<h4><b>Current Card : 350R</b></h4>
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
<div class="card card-inverse" style="background-color: #F8F8FF; border-color: #333; max-width: 20rem; height: 27rem;">
<div href="/document" style="height:100%;">
<img class="card-img-top" src="https://i.imgur.com/D7MJOZF.jpg" alt=“Avenger”> 
<div class="card-block">
<br />
<center>
<h4><b>Ai1</b></h4>
<br />
<p class="card-text"><br /></p>
<br />
</div>
</div>
</div>
</div>
<div class=“ai2”>
<div class="card card-inverse" style="background-color: #F8F8FF; border-color: #333; max-width: 20rem; height: 27rem;">
<div href="/document" style="height:100%;">
<img class="card-img-top" src="https://i.imgur.com/D7MJOZF.jpg" alt=“350r”>
<div class="card-block">
<br />
<center>
<h4><b>Ai2</b></h4>
<br />
<p class="card-text"><br /></p>
<br />
</div>
</div>
</div>
</div>
<div class=“ai3”>
<div class="card card-inverse" style="background-color: #F8F8FF; border-color: #333; max-width: 20rem; height: 27rem;">
<div href="/document" style="height:100%;">
<img class="card-img-top" src="https://i.imgur.com/D7MJOZF.jpg" alt=“350r”>
<div class="card-block">
<br />
<center>
<h4><b>Ai3</b></h4>
<br />
<p class="card-text"><br /></p>
<br />
</div>
</div>
</div>
</div> 
	<div class=“ai4”>
			<div class="card card-inverse" style="background-color: #F8F8FF; border-color: #333; max-width: 20rem; height: 27rem;">
				<div href="/document" style="height:100%;">
					<img class="card-img-top" src="https://i.imgur.com/D7MJOZF.jpg" alt=“350r”>
					<div class="card-block">
						<br />
						<center>
							<h4><b>Ai4</b></h4>
							<br />
							<p class="card-text"><br /</p>
							<br />

					</div>
				</div>
			</div>
		</div>
		<div clas="selection">
		<div class="card card-inverse" style="background-color: #F8F8FF; border-color: #333; max-width: 20rem; height: 27rem;">
		<div class="card-block">
						<br />
						<center>
							<h4><b>Category</b></h4>
							<br />
							<p class="card-text">Current Category: <br /> Player Selects<br />Communal Pile: 0</p>
							<div class="category">
  							<button class="categorydrop">Category</button>
 							<div class="category-content">
   								 <a href="#">Size</a>
  								 <a href="#">Speed</a>
 								 <a href="#">Range</a>
 								 <a href="#">Firepower</a>
								 <a href="#">Cargo</a>

 								 </div>	</div>

					</div>
				</div>
			</div>
		</div>
							
 				
	</div>
</div>
</div>
</div>
</div>
<a href="http://localhost:7777/toptrumps" class="w3-button w3-hover-grey">Main Menu</a>
</div>
<script type="text/javascript">
// Method that is called on page load
function initalize() {
// --------------------------------------------------------------------------
// You can call other methods you want to run when the page first loads here
// --------------------------------------------------------------------------
// For example, lets call our sample methods
helloJSONList();
helloWord("Student");
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
