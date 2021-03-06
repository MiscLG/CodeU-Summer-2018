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
<!DOCTYPE html>
<html>
  <head>
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/mobile_first.css">
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
        <% } %>
    </nav>

      <div id="container">
        <h1 style="text-align:center;">Login</h1>
        <% if(request.getAttribute("error") != null){ %>
        <h2 style="color:red"><%= request.getAttribute("error") %></h2>
        <% } %>

        <form action="/login" method="POST">
          <label for="username">Username: </label>
          <br/>
          <input type="text" name="username" id="username">
            <br/>
            <label for="password">Password: </label>
            <br/>
            <input type="password" name="password" id="password">
              <br/><br/>
              <button type="submit">Login</button>
            </form>

            <p>New users can register <a href="/register">here</a>.</p>
          </div>
        </body>
      </html>
