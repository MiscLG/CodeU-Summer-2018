// file Upload.java
package codeu.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import codeu.model.data.User;
import codeu.model.store.basic.UserStore;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;



public class Upload extends HttpServlet {
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
  public void doPost(HttpServletRequest req, HttpServletResponse res)
  throws ServletException, IOException {

    Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
    List<BlobKey> blobKeys = blobs.get("myFile");

    if (blobKeys == null || blobKeys.isEmpty()) {
      res.sendRedirect("/profiles");
    } else {
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
      //get BlobKey instance and save it.
      user.setBlobKey(blobKeys.get(0).getKeyString());
      userStore.updateUser(user);
      res.sendRedirect("/profiles");
    }
  }
}
