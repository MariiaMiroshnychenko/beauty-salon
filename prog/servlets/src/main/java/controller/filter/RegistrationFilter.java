package controller.filter;

import controller.command.impl.Authorization;
import controller.command.impl.Registration;
import model.entity.User;
import model.service.general.UserService;
import model.service.general.impl.UserServiceImpl;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationFilter implements Filter {
    final static Logger LOGGER = Logger.getLogger(RegistrationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse,
                         final FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        final String name = request.getParameter("name");
        final String surname = request.getParameter("surname");
        final String email = request.getParameter("email");
        final String username = request.getParameter("username_reg");
        final String repeatedPassword = request.getParameter("repeatedPassword");

        final String encodingPassword = BCrypt.hashpw(repeatedPassword, BCrypt.gensalt());

        UserService userService = new UserServiceImpl();
        User user = userService.findUserByUsername(username);

        if (user != null) {
            LOGGER.error("User exists!");

            request.setAttribute("usernameError", "Try to enter new username!");
        } else {
            user = new User(name, surname, email, "client", username, encodingPassword);

            userService.create(user);

            LOGGER.info("New user registered");
            request.getRequestDispatcher(new Authorization().execute(request)).forward(request, response);
        }
        request.getRequestDispatcher(new Registration().execute(request)).forward(request, response);
    }

    @Override
    public void destroy() {
    }
}
