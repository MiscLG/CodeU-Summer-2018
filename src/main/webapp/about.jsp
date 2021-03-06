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
        <div id="paragraph">
          <h1>About the CodeU Chat App</h1>
          <p>
            This is an example chat application designed to be a starting point
            for your CodeU project team work. Here's some stuff to think about:
          </p>


          <ul>
            <li><h4>Algorithms and data structures:</h4> We've made the app
            and the code as simple as possible. You will have to extend the
            existing data structures to support your enhancements to the app,
            and also make changes for performance and scalability as your app
            increases in complexity.</li>
            <li><h4>Look and feel:</h4> The focus of CodeU is on the Java
            side of things, but if you're particularly interested you might use
            HTML, CSS, and JavaScript to make the chat app prettier.</li>
            <li><h4>Customization:</h4> Think about a group you care about.
            What needs do they have? How could you help? Think about technical
            requirements, privacy concerns, and accessibility and
            internationalization.</li>
          </ul>

          <p>
            This is your code now. Get familiar with it and get comfortable
            working with your team to plan and make changes. Start by updating the
            homepage and this about page to tell your users more about your team.
            This page should also be used to describe the features and improvements
            you've added.
          </p>
        </div>
      </div>
    </body>
  </html>
