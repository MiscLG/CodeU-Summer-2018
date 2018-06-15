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

    
<!DOCTYPE html>
<html>
<head>
  <title>Admin</title>
  <link rel="stylesheet" href="/css/main.css">
</head>
<body>

    <nav>
        <a id="navTitle" href="/">CodeU Chat App</a>
        <a href="/about.jsp">About</a>
        <% if(request.getSession().getAttribute("user") != null){ %>
            <a href="/profiles">Profile</a>
            <a href="/conversations">Conversations</a>
            <a href="/">Logout</a>
            <% if(request.getSession().getAttribute("admin") != null) %>    
                <a href="/admin">Admin</a>
        <% } else{ %>
            <a href="/login">Login</a>
            <a href="/register">Register</a>
        <% } %>
    </nav>

  <div id="container">
    <div id="paragraph">
        <h1>Admin: Site Statistics</h1>
        
      <ul>
        <li><strong>Users: </strong> <%= request.getAttribute("userCount") %></li>
        <li><strong>Conversations: </strong> <%= request.getAttribute("conversationCount") %> </li>
        <li><strong>Messages: </strong><%= request.getAttribute("messageCount") %></li>
        <li><strong>Most active user: </strong><%= request.getAttribute("mostActiveUser") %></li>  
        <li><strong>Newest user: </strong><%= request.getAttribute("newestUser") %></li>    
      </ul>
    </div>
  </div>
</body>
</html>
