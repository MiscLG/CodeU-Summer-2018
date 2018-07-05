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
<%@ page import="codeu.model.data.Message" %>
<%@ page import="codeu.model.store.basic.UserStore" %>
<%
Conversation conversation = (Conversation) request.getAttribute("conversation");
List<Message> messages = (List<Message>) request.getAttribute("messages");
%>

<!DOCTYPE html>
<html>
  <head>
    <title><%= conversation.getTitle() %></title>
    <link rel="stylesheet" href="/css/mobile_first.css" type="text/css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
    #chat {
      background-color: white;
      height: 500px;
      overflow-y: scroll
    }
    </style>
    <script src="/javascript/chat.js"></script>
    <!--<script>
    // scroll the chat div to the bottom
    function scrollChat() {
      var chatDiv = document.getElementById('chat');
      chatDiv.scrollTop = chatDiv.scrollHeight;
    };
    </script>
    /!-->
  </head>
  <body onload="callFunctions()">

    <nav>
        <a id="navTitle" href="/">CodeU Chat App</a>
        <a href="/about.jsp">About</a>
        <% if(request.getSession().getAttribute("user") != null){ %>
            <a href="/profiles">Profile</a>
            <a href="/conversations">Chats</a>
            <a href="/">Logout</a>
            <% if(request.getSession().getAttribute("admin") != null) %>
                <a href="/admin">Admin</a>
        <% } else{ %>
            <a href="/login">Login</a>
            <a href="/register">Register</a>
        <% } %>
    </nav>


    <div id="container">

      <h1 id="title"><%= conversation.getTitle() %>
      <a id="reload" href="" style="float: right">&#8635;</a></h1>

      <hr/>

      <div id="chat">
        <ul>
          <%
          for (Message message : messages) {
            String author = UserStore.getInstance()
            .getUser(message.getAuthorId()).getName();
            %>
            <li><strong><%= author %>:</strong> <%= message.getContent() %></li>
            <%
          }
          %>
        </ul>
      </div>
    <hr/>

    <hr/>

    <% if (request.getSession().getAttribute("user") != null) { %>
    <form id="newMessage" action="/chat/<%= conversation.getTitle() %>" method="POST">
      <h3 class="preview">
        Preview:
      </h3>
        <p class="preview" id="preview"></p>
        <nav id="bar">
        </nav>
        <input type="text" id="message" name="message"  onkeyup="document.getElementById('preview').innerHTML = this.value">
        <br/>
        <button type="submit" id="submit">Send</button>

    </form>
    <% } else { %>

      <p><a href="/login">Login</a> to send a message.</p>
      <% } %>

      <hr/>

    </div>

  </body>
</html>
