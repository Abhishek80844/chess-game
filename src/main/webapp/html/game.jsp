<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<!--%@page import="java.sql.Connection"%-->
<%@ page import="java.util.List"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<title>Chess</title>
	<script src="./js/ajax.js" language="JavaScript"></script>
	<link rel="stylesheet" type="text/css" href="./css/squares.css" media="screen" />
</head>
<body>
	<h2>Vulnerable Chess Game</h2>

	<div id="a8">
		<img src="./images/towerb.png" />
	</div>
	<div id="b8">
		<img src="./images/knightb.png" />
	</div>
	<div id="c8">
		<img src="./images/bishopb.png" />
	</div>
	<div id="d8">
		<img src="./images/queenb.png" />
	</div>
	<div id="e8">
		<img src="./images/kingb.png" />
	</div>
	<div id="f8">
		<img src="./images/bishopb.png" />
	</div>
	<div id="g8">
		<img src="./images/knightb.png" />
	</div>
	<div id="h8">
		<img src="./images/towerb.png" />
	</div>
	<div id="a7">
		<img src="./images/pawnb.png" />
	</div>
	<div id="b7">
		<img src="./images/pawnb.png" />
	</div>
	<div id="c7">
		<img src="./images/pawnb.png" />
	</div>
	<div id="d7">
		<img src="./images/pawnb.png" />
	</div>
	<div id="e7">
		<img src="./images/pawnb.png" />
	</div>
	<div id="f7">
		<img src="./images/pawnb.png" />
	</div>
	<div id="g7">
		<img src="./images/pawnb.png" />
	</div>
	<div id="h7">
		<img src="./images/pawnb.png" />
	</div>
	<div id="a6"></div>
	<div id="b6"></div>
	<div id="c6"></div>
	<div id="d6"></div>
	<div id="e6"></div>
	<div id="f6"></div>
	<div id="g6"></div>
	<div id="h6"></div>
	<div id="a5"></div>
	<div id="b5"></div>
	<div id="c5"></div>
	<div id="d5"></div>
	<div id="e5"></div>
	<div id="f5"></div>
	<div id="g5"></div>
	<div id="h5"></div>
	<div id="a4"></div>
	<div id="b4"></div>
	<div id="c4"></div>
	<div id="d4"></div>
	<div id="e4"></div>
	<div id="f4"></div>
	<div id="g4"></div>
	<div id="h4"></div>
	<div id="a3"></div>
	<div id="b3"></div>
	<div id="c3"></div>
	<div id="d3"></div>
	<div id="e3"></div>
	<div id="f3"></div>
	<div id="g3"></div>
	<div id="h3"></div>
	<div id="a2">
		<img src="./images/pawnw.png" />
	</div>
	<div id="b2">
		<img src="./images/pawnw.png" />
	</div>
	<div id="c2">
		<img src="./images/pawnw.png" />
	</div>
	<div id="d2">
		<img src="./images/pawnw.png" />
	</div>
	<div id="e2">
		<img src="./images/pawnw.png" />
	</div>
	<div id="f2">
		<img src="./images/pawnw.png" />
	</div>
	<div id="g2">
		<img src="./images/pawnw.png" />
	</div>
	<div id="h2">
		<img src="./images/pawnw.png" />
	</div>
	<div id="a1">
		<img src="./images/towerw.png" />
	</div>
	<div id="b1">
		<img src="./images/knightw.png" />
	</div>
	<div id="c1">
		<img src="./images/bishopw.png" />
	</div>
	<div id="d1">
		<img src="./images/queenw.png" />
	</div>
	<div id="e1">
		<img src="./images/kingw.png" />
	</div>
	<div id="f1">
		<img src="./images/bishopw.png" />
	</div>
	<div id="g1">
		<img src="./images/knightw.png" />
	</div>
	<div id="h1">
		<img src="./images/towerw.png" />
	</div>
	<div id="match">
		<%
			out.println("Match:");
		    String history = (String) request.getSession().getAttribute("history");
		    if (history != null) {
		      out.println(history);
		    }
		%>
	</div>
	<div id="timer1">
		<%
			out.println("Time:");
		    String time = (String) request.getSession().getAttribute("timer");
		    if (time != null) {
		      out.println(time);
		    }
		%>
	</div>
	<div id="timer2">
		<%
			out.println("Time:");
		    String time2 = (String) request.getSession().getAttribute("timer");
		    if (time2 != null) {
		      out.println(time2);
		    }
		%>
	</div>
	<div id="player1">
		<%
			out.println("Player 1:");
		    String player1 = (String) request.getSession().getAttribute("player1");
		    if (player1 != null) {
		      out.println(player1);
		    }
		%>
	</div>
	<div id="player2">
		<%
			out.println("Player 2:");
		    String player2 = (String) request.getSession().getAttribute("player2");
		    if (player2 != null) {
		      out.println(player2);
		    }
		%>
	</div>
	<div id="play">
		<a href="./Play">Play</a>
	</div>
	<div id="new">
		<a href="./Game">New</a>
	</div>
	<div id="load">
		<a href="./Load">Load</a>
	</div>
	<div id="save">
		<a href="./Save">Save</a>
	</div>
	<div id="message">
		Click Play button or insert your move as e2e4
	</div>
	<div id="move">
		<form action="/Play">
		  <input type="text" name="move"><br>
		  <input id='button' type='submit' value='Play'>	
		</form>
	</div>
	<table id="table">
		<tr>
			<th>Username</th>
			<!-- th>Password</th-->
		</tr>
		<%
			List<String> user = (List<String>) request.getSession().getAttribute("user");
			for (int i = 0; i < user.size(); i = i + 2) {
		%>
		<tr>
			<td><%=user.get(i)%></td>
			<!-- td><%=user.get(i + 1)%></td-->
		</tr>
		<%
			}
		%>
	</table>


</body>
</html>
