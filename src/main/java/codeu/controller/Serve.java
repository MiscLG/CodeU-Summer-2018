// file Serve.java
package codeu.controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import codeu.model.data.User;
import codeu.model.store.basic.UserStore;
import java.util.UUID;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Serve extends HttpServlet {
  private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
  /** Store class that gives access to Users. */
  private UserStore userStore;


  /**
  * Set up state for handling conversation-related requests. This method is only called when
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
  public void doGet(HttpServletRequest req, HttpServletResponse res)
  throws IOException {
    String username = (String) req.getSession().getAttribute("user");
    if (username == null) {
      // user is not logged in, don't let them create a conversation
      res.sendRedirect("/profiles");
      return;
    }

    User user = userStore.getUser(username);
    if (user == null) {
      // user was not found, don't let them create a conversation
      System.out.println("User not found: " + username);
      res.sendRedirect("/profiles");
      return;
    }

    String key = user.getBlobKey();

    if (key == null || key.isEmpty()) {
      res.sendRedirect("/profiles");
      return;
    }

    BlobKey blobKey = new BlobKey(key);
    blobstoreService.serve(blobKey, res);
  }
}
