<%--
Copyright 2017 Google Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
--%>

<%@ page import="java.util.List" %>
<%@ page import="codeu.model.data.Conversation" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>


<%
BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>

<!DOCTYPE html>
<html>
  <head lang="en">
    <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
          <link rel="stylesheet" href="/css/main.css">
            <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
            <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
            <title>Register</title>

            <!--JavaScript functions-->
            <script language="JavaScript">
            <!--gets the user input to be dispayed-->
            function showInput() {
              document.getElementById('status').innerHTML =
              document.getElementById("status_name").value;
            }

            function showPhoto(){
              var preview = document.querySelector('img'); //selects the query named img
              var file    = document.querySelector('input[type=file]').files[0]; //sames as here
              var reader  = new FileReader();
              reader.onloadend = function () {
                preview.src = reader.result;
              }
              if (file) {
                reader.readAsDataURL(file); //reads the data as a URL
              } else {
                preview.src = "";
              }
            }
            showPhoto();

            </script>

           <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
           <link rel="stylesheet" href="/css/mobile_first.css">
           <style>
             nav {  width:100%;  }
             nav a:hover {  text-decoration:none;  color:white;  }
      	     ul {padding:0; margin-right:auto; margin-left:auto;
             list-style-type:none; width:50%;}
             li {width:100%; text-align:center;
             background-color:white; margin-top:1%;}
             @media screen and (min-width: 769px){
             textarea {width:75%;}
        }
              </style>
              <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
              <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
              <title>Register</title>
            </head>
            <body>
              <nav>
                <a id="navTitle" href="/">CodeU Chat App</a>
                <a href="/about.jsp">About</a>
                <% if(request.getSession().getAttribute("user") != null){ %>
                <a href="/profiles">Profile</a>
                <a href="/conversations">Chats</a>
                <a href="/login?logout=true" >Logout</a>
                <% if(request.getSession().getAttribute("admin") != null) %>
                <a href="/admin">Admin</a>
                <% } else{ %>
                <a href="/login">Login</a>
                <a href="/register">Register</a>
                <a href="/mail">Mail</a>
                <% } %>
              </nav>

              <div class="container">
                <div class="span3 well">
                  <center>
                    <!---Displays profileImage //https://www.gstatic.com/webp/gallery3/2.png//-->
                    <img src="/serve" name="profileImage" id="photo" width="160" height="160" class="img-circle">
                      <!-- Displays username-->
                      <h3 style="text-transform: uppercase;" ><%= request.getSession().getAttribute("user") %></h3>
                      <!-- Displays status-->
                      <%
                      String status =  (String)request.getAttribute("status_name");
                      if (status == null) {
                        %>
                        <em><p><span id ='status'>No Status</span></p></em>
                        <%
                      } else {
                        %>
                        <em><p><span id='status'><%=status%></span></p></em>
                        <%
                      }
                      %>
                    </center>
                  </div>
                </div>

                <!-- Uploading images-->
                <form action="<%= blobstoreService.createUploadUrl("/upload") %>" method="post" enctype="multipart/form-data">
                <div style="text-align: center;">
                  <div>
                    <h4 style="text-align:center;">Change Profile</h4>
                  </div>
                  <div style="text-align: center;">
                    <input type="file" id="fileUpload" name="myFile" onchange="this.form.submit(); showPhoto()"/>
                  </div>
                </div>
              </form>

              <!--updates status-->
              <h2 style="text-align:center;">Update Status</h2>
              <!--text field goes here-->
              <% if(request.getSession().getAttribute("user") != null){ %>
              <form action="/profiles" method="POST">
                <div class="form-group" align = "center" margin-left:auto; margin-right:auto;>
                  <input type="text" name="status_name" placeholder="Type your status here" cols="70" rows="4" id="status_name">
                  </div>
                  <br>
                    <!--submit status-->
                    <div style="text-align: center;">
                      <button type="submit" value = "Update" onclick = "showInput()">Create</button>
                    </div>
                    <br>
                    </form>
                    <% } %>

                    <h2 style="text-align:center;">Recent Conversations</h2>
                    <!--load conversations from database and displays them-->
           <% if(request.getSession().getAttribute("phoneNumber") != null) { 
                String number = (String) request.getSession().getAttribute("phoneNumber"); %>
                <h3>Phone: <%= number.substring(0,10) %> </h3>
            <%
            }
            else { %> 
                <h3>PhoneNumber: N/A </h3>
            <%
            }
            %>
            <br/>
                
            <form action="/profiles" method="POST">
              <label for="phone" style="text-align:center;" width = 50% >Change Phone Number (No dashes/spaces): </h2>
              <input type="text" name="phone" id="phone"> 
              <br/> 
              <label for="carriersList" style="text-align:center;" width = 50% >Change Carrier: </h2>
              <br/>
              <input list="carriersList" name="carriers" id="carriers" >
                  <datalist id="carriersList">
                    <option value="Verizon">
                    <option value="AT&T">
                    <option value="T-Mobile">
                    <option value="Sprint">
                    <option value="Virgin-Mobile">
                    <option value="Other">
                  </datalist>
              <br/>
              <br/>
              <input type="submit" value = "Save">
            </form>
              <br/>
              <br/>
              <h2 style="text-align:center;">Recent Conversations</h2>
              <!--load conversations from database and displays them-->
              <%
              List<Conversation> conversations =
              (List<Conversation>) request.getAttribute("conversations");
              if(conversations == null || conversations.isEmpty()){
                %>
                <p style="text-align:center;">No recent conversations, why don't you start a new one? :)</p>
                <%
              }else{
                %>
                <ul class="mdl-list" >
                  <%
                  for(Conversation conversation : conversations){
                    %>
                    <li><a href="/chat/<%= conversation.getTitle()%>">
                    <%= conversation.getTitle()%></a></li>
                    <%
                    List<Conversation> conversations =
                    (List<Conversation>) request.getAttribute("conversations");
                    if(conversations == null || conversations.isEmpty()){
                      %>
                      <p style="text-align:center;">No recent conversations, why don't you start a new one? :)</p>
                      <%
                    }else{
                      %>
                      <ul class="mdl-list">
                        <%
                        for(Conversation conversation : conversations){
                          %>
                          <li><a href="/chat/<%= conversation.getTitle()%>">
                          <%= conversation.getTitle()%></a></li>
                          <%
                        }
                        %>
                      </ul>
                      <%
                    }
                    %>
                    <br>
                    </body>
                  </html>
