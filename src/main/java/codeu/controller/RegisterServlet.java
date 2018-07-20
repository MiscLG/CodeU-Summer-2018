package codeu.controller;

import java.io.IOException;
import java.time.Instant;
import java.util.UUID;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import codeu.model.data.User;
import codeu.model.store.basic.UserStore;

public class RegisterServlet extends HttpServlet {

  /** Store class that gives access to Users. */
  private UserStore userStore;

  /**
   * Set up state for handling registration-related requests. This method is only called when
   * running in a server, not when running in a test.
   */
  @Override
  public void init() throws ServletException {
    super.init();
    setUserStore(UserStore.getInstance());
  }

  /**
   * Sets the UserStore used by this servlet. This function provides a common setup method for use
   * by the test framework or the servlet's init() function.
   */
  void setUserStore(UserStore userStore) {
    this.userStore = userStore;
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    request.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(request, response);
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

    String username = request.getParameter("username");

    if (!username.matches("[\\w*\\s*]*")) {
      request.setAttribute("error", "Please enter only letters, numbers, and spaces.");
      request.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(request, response);
      return;
    }

    if (userStore.isUserRegistered(username)) {
      request.setAttribute("error", "That username is already taken.");
      request.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(request, response);
      return;
    }

    String password = request.getParameter("password");
    String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
    
    String phoneNumber = null;
    
    if(true/*VALIDATE*/) {
    	phoneNumber = createNumber(request.getParameter("phone"), request.getParameter("carriers"));
    }
    
    User user = new User(UUID.randomUUID(), username, hashedPassword, Instant.now());
    user.setPhoneNumber(phoneNumber);
    userStore.addUser(user);

    response.sendRedirect("/login");
  }
  
  private String createNumber(String phone, String carrier) {
	  String phoneNumber = phone;
	  if(carrier.equals("Verizon")) phoneNumber += "@vtext.com";
	  else if(carrier.equals("AT&T")) phoneNumber += "@txt.att.net";
	  else if(carrier.equals("T-Mobile")) phoneNumber += "@tmomail.net";
	  else if(carrier.equals("Sprint")) phoneNumber += "@messaging.sprintpcs.com";
	  else if(carrier.equals("Virgin-Mobile")) phoneNumber += "@vmobl.com";
	  else phoneNumber = null;
	  return phoneNumber;
  }
}
