<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" type="text/css" href="overall.css" />
		<link rel="stylesheet" type="text/css" href="searchResult.css" />
		<title>Search Result</title>
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
		<%
		ArrayList<String[]> searchResult = (ArrayList<String[]>)request.getAttribute("searchResult");
		if (searchResult.size() == 0){
			%>
			No User Found.
			<%
		}
		%>
			<div id="searchResultBox">
				<%
				for(int i=0; i < searchResult.size(); i++){
					String fname = searchResult.get(i)[1];
					String lname = searchResult.get(i)[2];
					String image = searchResult.get(i)[3];
					
					String name = fname + " " + lname;
					String email = searchResult.get(i)[0];
					%>
					<div id="searchResultProfile">
<%-- 						<a href="profileOtherUser.jsp"><img id="searchResultImage" src=<%= image %> ></a> --%>
						<form action="LinkToOthersProfile" method="POST">
							<input type="hidden" name="email" value="<%=email %>">
							<input type="hidden" id ="hidden2" name="currentUserEmail">
							<input type="submit" value="" style="background-image: url('<%=image %>'); background-repeat:no-repeat; background-size:100% 100%; width: 200px; height: 200px; border-radius: 100px;" >
						</form>
						<div style="text-align: center;"><%= name %></div>
					</div>	
					<%
				}		
				
			    %>
			</div>
			
			
			<%-- <%
			for (int i = 0; i < searchResult.size(); i+=2){ %>
				<div id="searchResultBox">
				<%
				for(int j=0; j<2 && i*2+j < searchResult.size(); j++){
					String fname = searchResult.get(i*2+j)[1];
					String lname = searchResult.get(i*2+j)[2];
					String image = searchResult.get(i*2+j)[3];
					
					String name = fname + " " + lname;
					%>
					<div id="searchResultProfile">
						<img id="searchResultImage" src=<%= image %>>
						<div><%= name %></div>
					</div>	
					<%
				}		
				
			} %>
				</div>
			 --%>
			
		</div>
		
		<script type="text/javascript">
      // Client ID and API key from the Developer Console
      var CLIENT_ID = '635120887250-lcl57h1nsh0i8b5srj75qi0472765ng8.apps.googleusercontent.com';
      var API_KEY = 'AIzaSyA61CFhVxigqJr8PXA2oxCdad9oYN6BCp8';

      // Array of API discovery doc URLs for APIs used by the quickstart
      var DISCOVERY_DOCS = ["https://www.googleapis.com/discovery/v1/apis/calendar/v3/rest"];

      // Authorization scopes required by the API; multiple scopes can be
      // included, separated by spaces.
      var SCOPES = "https://www.googleapis.com/auth/calendar.readonly";

      var authorizeButton = document.getElementById('authorize_button');
      var signoutButton = document.getElementById('signout_button'); 
      var email;
      
      /**
       *  On load, called to load the auth2 library and API client library.
       */
      function handleClientLoad() {
        gapi.load('client:auth2', initClient);
      }

      /**
       *  Initializes the API client library and sets up sign-in state
       *  listeners.
       */
      function initClient() {
        gapi.client.init({
          apiKey: API_KEY,
          clientId: CLIENT_ID,
          discoveryDocs: DISCOVERY_DOCS,
          scope: SCOPES
        }).then(function () {
          // Listen for sign-in state changes.
          gapi.auth2.getAuthInstance().isSignedIn.listen(updateSigninStatus);

          // Handle the initial sign-in state.
          updateSigninStatus(gapi.auth2.getAuthInstance().isSignedIn.get());
          authorizeButton.onclick = handleAuthClick;
          signoutButton.onclick = handleSignoutClick;
         

        });
        
        function updateSigninStatus(isSignedIn) {
            if (isSignedIn) {
              //authorizeButton.style.display = 'none';
              //signoutButton.style.display = 'block';
              //getEmail();
            	email = gapi.auth2.getAuthInstance().currentUser.get().getBasicProfile().getEmail();
                document.getElementById("hidden2").value = email;
                console.log("Getting the Current User's Email from searchResult.jsp.................." + email); 	 
            } else {
              //authorizeButton.style.display = 'block';
              //signoutButton.style.display = 'none';
            }
          }

        /**
         *  Sign in the user upon button click.
         */
        function handleAuthClick(event) {
          gapi.auth2.getAuthInstance().signIn();
        }

        /**
         *  Sign out the user upon button click.
         */
        function handleSignoutClick(event) {
          gapi.auth2.getAuthInstance().signOut();
        }
        
        /*function getEmail(){
        	gapi.client.calendar.events.list({
                'calendarId': 'primary',
                'timeMin': (new Date()).toISOString(),
                'showDeleted': false,
                'singleEvents': true,
                'maxResults': 15,
                'orderBy': 'startTime'
              }).then(function(response) {
            	  email = gapi.auth2.getAuthInstance().currentUser.get().getBasicProfile().getEmail();
                  document.getElementById("hidden2").value = email;
                  console.log("Getting the Current User's Email from searchResult.jsp.................." + email); 	 
              });
        }*/
        
        
      }
      </script>
		
		<div id = "bottom">
		
		</div>
	</body>
</html>