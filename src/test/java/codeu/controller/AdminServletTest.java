package codeu.controller;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import codeu.model.store.basic.ConversationStore;
import codeu.model.store.basic.MessageStore;
import codeu.model.store.basic.UserStore;

public class AdminServletTest {
	
	private AdminServlet adminServlet;
	private HttpServletRequest mockRequest;
	private HttpSession mockSession;
	private HttpServletResponse mockResponse;
	private RequestDispatcher mockRequestDispatcher;
	private ConversationStore mockConversationStore;
	private UserStore mockUserStore;
	private MessageStore mockMessageStore;

	@Before
	public void setUp() throws Exception {
		adminServlet = new AdminServlet();

	    mockRequest = Mockito.mock(HttpServletRequest.class);
	    mockSession = Mockito.mock(HttpSession.class);
	    Mockito.when(mockRequest.getSession()).thenReturn(mockSession);

	    mockResponse = Mockito.mock(HttpServletResponse.class);
	    mockRequestDispatcher = Mockito.mock(RequestDispatcher.class);
	    Mockito.when(mockRequest.getRequestDispatcher("/admin.jsp"))
	        .thenReturn(mockRequestDispatcher);

	    mockConversationStore = Mockito.mock(ConversationStore.class);
	    adminServlet.setConversationStore(mockConversationStore);

	    mockUserStore = Mockito.mock(UserStore.class);
	    adminServlet.setUserStore(mockUserStore);
	    
	    mockMessageStore = Mockito.mock(MessageStore.class);
	    adminServlet.setMessageStore(mockMessageStore);
	}

	@Test
	  public void testDoGet() throws IOException, ServletException {
	    adminServlet.doGet(mockRequest, mockResponse);

	    Mockito.verify(mockRequestDispatcher).forward(mockRequest, mockResponse);
	  }
	
}
