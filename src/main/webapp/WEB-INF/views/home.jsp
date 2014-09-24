<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script>
		$(function(){
			$("#ssh-click").click(function(e){
				e.stopPropagation();
				$.post("/ssh/connect",function(){
					$("#result").text("Connected!");		
				}).fail(function(e){
					$("#result").text("Failed to connect: "+e);			
				});
			});
		});
	
	</script>
</head>
<body>
<h1>
	Reverse SSH Demo
</h1>

<P>  Click Here to simulate reverse ssh </P>
<button id="ssh-click">initialize</button>
<p id="result">Not Started</p>
</body>
</html>
