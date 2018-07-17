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
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 

@SuppressWarnings("serial")
public class MailServlet extends HttpServlet {
	
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
	String messageContent = request.getParameter("message");
    sendSimpleMail(messageContent);
    
    String requestUrl = request.getRequestURI();
    String conversationTitle = requestUrl.substring("/mail/".length());
    response.sendRedirect("/chat/" + conversationTitle);
  }

  private void sendSimpleMail(String messageContent) {
    Properties props = new Properties();
    Session session = Session.getDefaultInstance(props, null);

    try {
      Message msg = new MimeMessage(session);
      msg.setFrom(new InternetAddress("lriffle@codeustudents.com"));
      msg.addRecipient(Message.RecipientType.TO,
                       new InternetAddress("4259858290@vtext.com"));
      //msg.setSubject("TEST");
      msg.setText(messageContent);
      Transport.send(msg);
    } catch (AddressException e) {
      // ...
    } catch (MessagingException e) {
      // ...
    } /*catch (UnsupportedEncodingException e) {
      // ...
    }*/
  }
}