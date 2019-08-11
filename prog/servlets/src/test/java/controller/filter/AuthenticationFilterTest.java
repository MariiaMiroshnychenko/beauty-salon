package controller.filter;

import model.dao.FactoryDao;
import model.dao.UserDao;
import model.dao.impl.UserJdbcDao;
import model.entity.User;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationFilterTest {

    @Mock
    private ServletRequest servletRequest;

    @Mock
    private ServletResponse servletResponse;

    @Mock
    private HttpServletRequest httpServletRequest;

    @Mock
    private HttpServletResponse httpServletResponse;

    @Mock
    private FilterChain filterChain;

    @Mock
    private HttpSession session;

    @Mock
    private User user;

    @Spy
    private UserDao userDao = FactoryDao.getInstance().userDao();

    @Spy
    private AuthenticationFilter authenticationFilter;

    @Test
    public void doFilterTest() throws IOException, ServletException {
        Mockito.when((HttpServletRequest) servletRequest).thenReturn(httpServletRequest);
        Mockito.when((HttpServletResponse) servletResponse).thenReturn(httpServletResponse);
        Mockito.when(servletRequest.getParameter("username")).thenReturn("salon_admin");
        Mockito.when(servletRequest.getParameter("password")).thenReturn("salon_admin");
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(session.getAttribute("role")).thenReturn("admin");
        authenticationFilter.doFilter(servletRequest, servletResponse, filterChain);
    }
}
