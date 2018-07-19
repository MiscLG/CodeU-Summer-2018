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
    <title>CodeU Chat App</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/mobile_first.css">
    </head>
    <body>

      <nav>
        <a id="navTitle" href="/">CodeU Chat App</a>
        <a href="/about.jsp">About</a>
        <% if(request.getSession().getAttribute("user") != null){ %>
            <% if(request.getSession().getAttribute("admin") != null){ %>
                <a href="/admin">Admin</a>
            <% } %>
            <a href="/profiles">Profile</a>
            <a href="/conversations">Chats</a>
            <a href="/login?logout=true" >Logout</a>
        <% } else{ %>
            <a href="/login">Login</a>
            <a href="/register">Register</a>
        <% } %>
    </nav>

      <div id="container">
        <div
          style="width:75%; margin-left:auto; margin-right:auto; margin-top: 50px;">

          <h1>CodeU Chat App</h1>
          <h2>Welcome!</h2>

          <ul>
            <li><a href="/login">Login</a> to get started.</li>
            <li>Go to the <a href="/conversations">conversations</a> page to

            create or join a conversation.</li>
            <li>View the <a href="/about.jsp">about</a> page to learn more about the
            project.</li>
          </ul>
        </div>
      </div>
    </body>
  </html>
