package controller.servlet;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ServletTest {
    private static final String PATH = "/WEB-INF/view/authorization.jsp";
    private static final String URI = "/authorization";

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Spy
    private static MainServlet mainServlet = new MainServlet();

    @BeforeClass
    public static void init() throws ServletException {
        mainServlet.init();
    }

    @Test
    public void doGetTest() throws ServletException, IOException {
        when(request.getRequestDispatcher(PATH)).thenReturn(requestDispatcher);
        when(request.getRequestURI()).thenReturn(URI);
        when(request.getSession()).thenReturn(session);

        mainServlet.doGet(request, response);

        verify(request, times(1)).getRequestDispatcher(PATH);
        verify(requestDispatcher).forward(request, response);
    }
}
