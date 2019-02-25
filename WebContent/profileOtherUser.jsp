<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" type="text/css" href="overall.css" />
		<title>Profile</title>
		
		<script>
			function changeFollow(currentUserEmail, otherEmail){
				var xhttp = new XMLHttpRequest();
				xhttp.open("GET", "changeFollow?currentUserEmail="+currentUserEmail+"&otherEmail="+otherEmail, false);
				xhttp.send();
			}
		</script>
	</head>
	<body>
		<div id = "top">
	    	<div>
	    		<div id = "sycCalendar"><a href="loggedIn.html">Sycamore Calendar</a></div>
	    	</div>
			<div id ="navi">
				<form name = "searchForm" method="POST" action="Search">
	 					<input type="hidden" id = "hiddenParam" name="currentUserEmail">
						<input id="searchBar" type="text" name="search" placeholder="Search Friends">
					<input type="submit" value="" style="margin-left: -80px; background-image: url('https://d1b10bmlvqabco.cloudfront.net/attach/jl4eo81c7fx3ds/j6muzqcg5hv40y/jn80vjqgn71r/search.png'); background-repeat:no-repeat; background-size:100% 100%; width: 20px; height: 20px; ">
				</form>
				<div id = "profile"><a href="profile.html">Profile</a></div>
				<div id = "home"><a href = "home.html">Home</a></div>
			</div>
					
				
		</div>
		
		<div id = "content">
			<h1 style="padding-left: 50px;">Upcoming Event</h1>
			<div id ="bigContentInProfile" style = "padding-left: 50px;">
				<div id ="upcomingEventList" style = "padding-left: 15px;">
					<table id="eventContent" style = "width: 100%;">
						<tr>
				  			<th>Date</th>
				  			<th>Time</th>
				  			<th>Event Summary</th>
				  		</tr>
				  		
				  		<%
				  		boolean followStatus = (boolean)request.getAttribute("followStatus");
				  		if (followStatus){
				  		
					  		ArrayList<String[]> eventOfThisUser = (ArrayList<String[]>)request.getAttribute("eventOfThisUser");
					  		
							if (eventOfThisUser.size() > 0){
								for (int i = 0; i < eventOfThisUser.size(); i++){
									String[] eventRow = eventOfThisUser.get(i);
									%><tr>
									<td><%=eventRow[1] %></td>
									<td><%=eventRow[3] %></td>
									<td><%=eventRow[0] %></td>
									</tr>
									
									<script>
									console.log(eventRow[1] + " " + eventRow[2] + " " + eventRow[3]);
									</script>
									<%
									
								}
							}
						
				  		}
				  		else{
				  			%>
				  			<tr >
				  			<td style="color: red;" colspan="3">Follow this user to show upcoming events.</td>
				  			</tr>
				  			
				  			<%
				  		}
				  		%>			
				
					</table>
				    
			    </div>
			    
			    <%
			    ArrayList<String[]> profileOfThisUser = (ArrayList<String[]>)request.getAttribute("profileOfThisUser");
			    String image = profileOfThisUser.get(0)[0];
			    String fname = profileOfThisUser.get(0)[1];
			    String lname = profileOfThisUser.get(0)[2];
			    String email = profileOfThisUser.get(0)[3];
			    String name = fname + " " + lname;
			    
			    String currentUserEmail = (String)request.getAttribute("currentUserEmail");
			    %>
			    <script>console.log("Current User Email: ---------------" + currentUserEmail);
			    </script>
			    
			    <%
			    if (followStatus){
			    	%>
			    	<form action="changeFollow" method="POST">
			    		<input type="hidden" name="otherEmail" value="<%=email %>">
			    		<input type="hidden" name="currentUserEmail" value="<%=currentUserEmail %>">
			    		<input type="submit" value="UNFOLLOW">
			    	</form>
			    	<!-- <input type="button" value="UNFOLLOW" onclick="changeFollow()"> -->
			    	<%
			    }else{
			    	%> 
			    	<form action="changeFollow" method="POST">
			    		<input type="hidden" name="otherEmail" value="<%=email %>">
			    		<input type="hidden" name="currentUserEmail" value="<%=currentUserEmail %>">
			    		<input type="submit" value="FOLLOW">
			    	</form>
			    	<!-- <input type="button" value="FOLLOW" onclick="changeFollow()"> -->
			    	<%
			    }
			    
			    %>
			    
			    <div id = "imageInProfile">
			    	<img id = "profilePicture" src=<%= image%>>
			    	<div style="text-align: center;"><%=name%></div>
			    	
			    	<input type="hidden" name="currentUserEmail" value="<%=email %>">
			    </div>
			</div>		    
		</div> 
	
		
		<div id = "bottom">
		
		</div>	
	</body>
</html>