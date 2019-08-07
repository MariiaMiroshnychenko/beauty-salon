package controller.filter;

import controller.command.impl.Registration;
import controller.command.impl.Authorization;
import model.entity.User;
import model.service.UserService;
import model.service.impl.UserServiceImpl;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationFilter implements Filter {
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
            request.setAttribute("usernameError", "Try to enter new username!");
        } else {
            user = new User(name, surname, email, "client", username, encodingPassword);

            userService.create(user);
            request.getRequestDispatcher(new Authorization().execute(request)).forward(request, response);
        }
        request.getRequestDispatcher(new Registration().execute(request)).forward(request, response);
    }

    @Override
    public void destroy() {
    }
}
