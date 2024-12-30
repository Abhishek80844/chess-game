<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
	margin-left:auto;
	margin-right:auto;
	width:40%;
	background-image: url('./images/background.jpg');
	background-repeat: no-repeat;
  	background-attachment: fixed;
  	background-size: cover;
}
form {border: 3px solid #f1f1f1;}

input[type=text], input[type=password] {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
}

button {
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
}

button:hover {
  opacity: 0.8;
}

.cancelbtn {
  width: auto;
  padding: 10px 18px;
  background-color: #f44336;
}

.imgcontainer {
  text-align: center;
  margin: 24px 0 12px 0;
}

img.avatar {
  width: 20%;
  height: 20%;
  border-radius: 50%;
}

.container {
  padding: 16px;
}

span.psw {
  float: right;
  padding-top: 16px;
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
  span.psw {
     display: block;
     float: none;
  }
  .cancelbtn {
     width: 100%;
  }
}
</style>
</head>
<body>

<h2>Vulnerable Chess Game</h2>
<h5>You can try SQL Injection in this page ;-)</h5>
<h6>According to the OWASP TOP TEN: it is risk #1, more info <a href="https://owasp.org/www-project-top-ten/OWASP_Top_Ten_2017/Top_10-2017_A1-Injection">here</a></h6>
<h6>And you can find a specific explanation for this SQL Injection in my blog <a href="https://securingsoftware.blog/2020/10/06/sql-injection-in-chess-application/">here</a></h6>

<form action="./Login" method="post">
  <div class="imgcontainer">
    <img src="./images/profileimg.jpg" alt="Avatar" class="avatar">
  </div>

  <div class="container">
    <label for="username"><b>Username</b></label>
    <input type="text" placeholder="Enter Username" name="username" required>

    <label for="password"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="password" required>
        
    <button type="submit">Login</button>
  </div>

</form>
<script>
	<% if (request.getSession().getAttribute("error") != null){ %>
		alert("<%=request.getSession().getAttribute("error")%>")
		
	<% request.getSession().removeAttribute("error");}%>
</script>
<p>
<p>
<p>
<p>
<h8>HINT 1. username: user' or '1'='1</h8>
<h8>Password: whatever</h8>
<p>
<p>
<h8>HINT 2: username: whatever</h8>
<h8>Password: ' UNION select * from user where '1'='1</h8>
</body>
