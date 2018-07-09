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

import java.io.Console;
// [START simple_includes]
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
// [END simple_includes]
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 

@SuppressWarnings("serial")
public class MailServlet extends HttpServlet {
	
	private static final Logger logger = Logger.getLogger(MailServlet.class.getName());
	
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String type = req.getParameter("type");

    resp.getWriter().print("Sending simple email.");
    sendSimpleMail();
  }
  
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
	logger.log(Level.INFO, "Hits Mail serverlet");
    request.getRequestDispatcher("/mail.jsp").forward(request, response);
}

  private void sendSimpleMail() {
    Properties props = new Properties();
    Session session = Session.getDefaultInstance(props, null);

    try {
      Message msg = new MimeMessage(session);
      msg.setFrom(new InternetAddress("lriffle@codeustudents.com"));
      msg.addRecipient(Message.RecipientType.TO,
                       new InternetAddress("4259858290@vtext.com"));
      msg.setSubject("TEST");
      msg.setText("This is a test");
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