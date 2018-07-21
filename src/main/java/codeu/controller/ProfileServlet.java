// Copyright 2017 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package codeu.controller;

import codeu.model.data.Conversation;
import codeu.model.data.User;
import codeu.model.store.basic.ConversationStore;
import codeu.model.store.basic.UserStore;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet class responsible for the conversations page. */
public class ProfileServlet extends HttpServlet {

  /** Store class that gives access to Users. */
  private UserStore userStore;

  /** Store class that gives access to Conversations. */
  private ConversationStore conversationStore;

  /**
   * Set up state for handling conversation-related requests. This method is only called when
   * running in a server, not when running in a test.
   */
  @Override
  public void init() throws ServletException {
    super.init();
    setUserStore(UserStore.getInstance());
    setConversationStore(ConversationStore.getInstance());
  }

  /**
   * Sets the UserStore used by this servlet. This function provides a common setup method for use
   * by the test framework or the servlet's init() function.
   */
  void setUserStore(UserStore userStore) {
    this.userStore = userStore;
  }

  /**
   * Sets the ConversationStore used by this servlet. This function provides a common setup method
   * for use by the test framework or the servlet's init() function.
   */
  void setConversationStore(ConversationStore conversationStore) {
    this.conversationStore = conversationStore;
  }

  /**
   * This function fires when a user navigates to the conversations page. It gets all of the
   * conversations from the model and forwards to profiles.jsp for rendering the list.
   */
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    List<Conversation> conversations = conversationStore.getAllConversations();
    request.setAttribute("conversations", conversations);
    request.getRequestDispatcher("/WEB-INF/view/profiles.jsp").forward(request, response);
    }
  /**
   * This function sets the user phone number
   */
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
	  
	  String phoneNumber = null;
	  phoneNumber = RegisterServlet.createNumber(request.getParameter("phone"), request.getParameter("carriers"));
	  
	  String username = (String) request.getSession().getAttribute("user");
	  if (username == null) {
	        // user is not logged in, go back to profile
	        response.sendRedirect("/profiles");
	        return;
	      }

	  User user = UserStore.getInstance().getUser(username);
	  if (user == null) {
	         // user is not logged in, go back to profile
	        System.out.println("User not found line 112: " + username);
	        response.sendRedirect("/profiles");
	        return;
	      }
	  user.setPhoneNumber(phoneNumber);
	  userStore.addUser(user);
	  
	  if(phoneNumber != null) {
	    	request.getSession().setAttribute("phoneNumber", phoneNumber);
	    }

      //add status to database
      String status = (String) request.getParameter("status_name");
      if (status == null) {
        System.out.println("User STATUS: " + status);
      }

      user.setStatus(status);
      response.sendRedirect("/profiles");
    }
  }
  
