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
					$("#connect-hidden").addClass("hidden");
					$("#disconnect-hidden").removeClass("hidden");
				}).fail(function(e){
					$("#result").text("Failed to connect: "+e);			
				});
			});
			$("#disocnnect-click").click(function(e){
				e.stopPropagation();
				$.post("/ssh/disconnect",function(){
					$("#result").text("Disconnected!");
					$("#connect-hidden").removeClass("hidden");
					$("#disconnect-hidden").addClass("hidden");
				}).fail(function(e){
					$("#result").text("Failed to connect: "+e);			
				});
			});
		});
	
	</script>
	<style type="text/css">
		.hidden
		{
			display: none;
		}
	</style>
</head>
<body>
<h1>
	Reverse SSH Demo
</h1>

<P>  Click Here to simulate reverse ssh </P>
<div id="connect-hidden">
<button id="ssh-click">initialize</button>
</div>
<div id="disconnect-hidden" class="hidden">
<button id="disocnnect-click">Disconnect</button>
</div>
<p id="result">Not Started</p>
</body>
</html>
