/**
 * Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package codeu.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import codeu.model.data.Conversation;
import codeu.model.data.User;
import codeu.model.store.basic.ConversationStore;
import codeu.model.store.basic.MessageStore;
import codeu.model.store.basic.UserStore; 

@SuppressWarnings("serial")
public class MailServlet extends HttpServlet {

  private static final Logger logger = Logger.getLogger(MailServlet.class.getName());
 
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
	 String requestUrl = request.getRequestURI();
	  
	String conversationTitle = requestUrl.substring("/mail/".length());
	System.out.print(conversationTitle);
	response.sendRedirect("/chat/" + conversationTitle);
	  
	String messageContent = request.getParameter("message"); 
	
	String username = (String) request.getSession().getAttribute("user");
    
	if (username == null) {
      // user is not logged in, don't let them add send a text
      response.sendRedirect("/login");
      return;
    };
    
    User user = UserStore.getInstance().getUser(username);
    if (user == null) {
      // user was not found, don't let them add a message
      response.sendRedirect("/login");
      return;
    }
    
    Conversation conversation = ConversationStore.getInstance().
    		getConversationWithTitle(conversationTitle);
    
    ArrayList<User> participants = conversation.getChatParticipants();
    if(participants.size() > 0) {
    	sendSimpleMail(messageContent, username, conversationTitle, participants);
    }

  }

  private void sendSimpleMail(String messageContent, String username, 
		  String conversationTitle, ArrayList<User> participants) {
    Properties props = new Properties();
    Session session = Session.getDefaultInstance(props, null);

    try {
      Message msg = new MimeMessage(session);
      msg.setFrom(new InternetAddress("lriffle@codeustudents.com"));
      
      boolean send = false;
      
      for(User participant: participants) {
    	  if(participant.getPhoneNumber() != null && participant.wantsNotifications()) {
    		  msg.addRecipient(Message.RecipientType.TO,
                      new InternetAddress(participant.getPhoneNumber()));
    		  send = true;
    	  }
      }
      msg.setSubject(conversationTitle + ": "+ username + " ");
      msg.setText(Jsoup.clean(messageContent, Whitelist.none()));
      if(send) {
    	  Transport.send(msg);
      }
    } catch (AddressException e) {
      // ...
    } catch (MessagingException e) {
      // ...
    }
  }
}