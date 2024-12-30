<!DOCTYPE html>
<%@ page import="java.util.List"%>
<html>
<style>
body, html {
  height: 100%;
  margin: 0;
}

.bgimg {
  background-image: url('./images/start.jpg');
  height: 100%;
  background-position: center;
  background-size: cover;
  position: relative;
  color: white;
  font-family: "Courier New", Courier, monospace;
  font-size: 25px;
}

.topleft {
  position: absolute;
  top: 0;
  left: 16px;
}

.bottomleft {
  position: absolute;
  bottom: 0;
  left: 16px;
}

.middle {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
}

hr {
  margin: auto;
  width: 40%;
}
</style>
<body>  

<div class="bgimg">
  <div class="topleft">
  </div>
  <h2>Vulnerable Chess Game</h2>
  <h5>You can try Cross Site Scripting (XSS) in this page ;-)</h5>
  <h6>According to OWASP TOP TEN: it is risk #7, more info <a href="https://owasp.org/www-project-top-ten/OWASP_Top_Ten_2017/Top_10-2017_A7-Cross-Site_Scripting_(XSS)">here</a></h6>

  <div class="middle">
    <%
	List<String> user = (List<String>) request.getSession().getAttribute("user");
	for (int i = 0; i < user.size(); i = i + 2) {
	}
	%>
	<h2>Welcome <%=user.get(1)%>!</h2>
	<p>
	<p>
    <h5>Set players names and the time for the match</h5>
    <form action="Timer" method="GET">
		<input id="player1" name="player1" value="kasparov">
		<input id="player2" name="player2" value="revuelta"> 
		<input id="timer" name="timer" value="10"> 
		<input id="button" type="submit" value="Create Match">
	</form>
	<br>
    <hr>
    <p id="demo" style="font-size:20px"></p>
  </div>
  <div class="bottomleft">
  </div>
</div>

<script>
function getParams(url) {
	var params = {};
	var parser = document.createElement('a');
	parser.href = url;
	var query = parser.search.substring(1);
	var vars = query.split('&');
	for (var i = 0; i < vars.length; i++) {
		var pair = vars[i].split('=');
		params[pair[0]] = decodeURIComponent(pair[1]);
	}
	return params;
};
// THIS 3 LINES BELOW ARE NOT DETECTED BY KIUWAN AS VULNERABILITY
// const queryString = window.location.search;
// const urlParams = new URLSearchParams(queryString);
// const time = urlParams.get('timer');

var url = document.location.href;
params = getParams(url);
var from = url.indexOf("timer=")+6;

var timer = params["timer"];
var player1 = params["player1"];
var player2 = params["player2"];

if (from != 5) { //-1+6
	var time = decodeURI(url.substring(from));

 	if(!isNaN(parseInt(time))){
		
 		// Set the date we're counting down to
 		var now = new Date(Date.now());
 		now.setSeconds(now.getSeconds() + parseInt(3));
 		var countDownDate = now.getTime();
		
 		// Update the count down every 1 second
 		var countdownfunction = setInterval(function() {
		
 			// Get todays date and time
 			var now = new Date().getTime();
		
 			// Find the distance between now an the count down date
 			var distance = countDownDate - now;
		
 			// Time calculations for days, hours, minutes and seconds
 			var days = Math.floor(distance / (1000 * 60 * 60 * 24));
 			var hours = Math.floor((distance % (1000 * 60 * 60 * 24))/ (1000 * 60 * 60));
 			var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
 			var seconds = Math.floor((distance % (1000 * 60)) / 1000);
		
 			// Output the result in an element with id="demo"
 			document.getElementById("demo").innerHTML = "Preparing match  ..." + days + "d " + hours + "h "
 					+ minutes + "m " + seconds + "s ";
		
 			// If the count down is over, write some text 
 			if (distance < 0) {
 				clearInterval(countdownfunction);
 				document.getElementById("demo").innerHTML = "START!";
 				document.cookie = "timer="+timer;
 				document.cookie = "player1="+player1;
 				document.cookie = "player2="+player2;
 				window.location.href = "./Game"
 			}
 		}, 1000);
 	}
} 
</script>
</body>
</html>
