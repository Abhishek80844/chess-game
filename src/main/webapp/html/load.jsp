<!DOCTYPE html>
<html>
<style>
body, html {
  height: 100%;
  margin: 0;
}

.bgimg {
  background-image: url('./images/xml.jpg');
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
<h2>Vulnerable Chess Game</h2>
<h5>You can try Path Traversal in this page ;-)</h5>
<h6>According to OWASP TOP TEN: it is a serious risk, more info <a href="https://owasp.org/www-community/attacks/Path_Traversal">here</a></h6>
<div class="bgimg">
  <div class="topleft">
  </div>
  <div class="middle">
    <h3>Please choose your txt file<span id="myText"></span></h3>
    <p>All the matches are saved in the folder "matches", so you can write for example /matches/match1.txt</p>
    <form action="./LoadGame" method="post">
		<input id="file" name="file" value="/matches/match1.txt"> 
		<button id="button" type="submit">Load Match</button>
	</form>
	<script>
	<% if (request.getSession().getAttribute("error") != null){ %>
		alert("<%=request.getSession().getAttribute("error")%>")
		
	<% request.getSession().removeAttribute("error");}%>
</script>
	<br>
    <hr>
    <p id="demo" style="font-size:30px"></p>
  </div>
  <div class="bottomleft">
  </div>
</div>
<p>
<p>
<p>HINT. Try "/matches/../etc/passwd"</p>
<p>
<p>
</body>
</html>
