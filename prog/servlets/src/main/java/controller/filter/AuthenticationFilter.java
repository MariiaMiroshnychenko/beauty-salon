package controller.filter;

import container.ConstantWorkHour;
import container.PagePath;
import controller.command.Command;
import controller.command.impl.admin.AdminMenu;
import controller.command.impl.client.ClientFutureRecordPage;
import controller.command.impl.master.MasterPage;
import model.dao.UserDao;
import model.entity.User;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class AuthenticationFilter implements Filter {
    final static Logger LOGGER = Logger.getLogger(AuthenticationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        final String username = request.getParameter("username");
        final String password = request.getParameter("password");


        @SuppressWarnings("unchecked") final AtomicReference<UserDao> userDao =
                (AtomicReference<UserDao>) request.getServletContext()
                        .getAttribute("userDao");

        final HttpSession session = request.getSession();

        if (Objects.nonNull(session) &&
                Objects.nonNull(session.getAttribute("user"))&&
                Objects.nonNull(session.getAttribute("role"))) {

            filterChain.doFilter(request, response);
        } else if (Objects.nonNull(userDao.get().findUserByUsername(username))) {
            final User user = userDao.get()
                    .findUserByUsername(username);

            if (BCrypt.checkpw(password, user.getPassword())) {
                request.getSession().setAttribute("user", user);
                request.getSession().setAttribute("role", user.getRole());

                LocalDate availableDate = LocalTime.now()
                        .compareTo(LocalTime.of(ConstantWorkHour.END_HOUR, ConstantWorkHour.MINUTE)) >= 0 ? LocalDate.now().plusDays(1) : LocalDate.now();
                request.getSession().setAttribute("availableDate", availableDate);

                LOGGER.debug("User logged in and redirected to menu page");
                redirectToAccount(request, response, user.getRole());
            } else {
                LOGGER.error("Invalid user password");
                LOGGER.debug("User redirected to login page");
                request.getRequestDispatcher(PagePath.PAGE_AUTHORIZATION).forward(request, response);
            }
        } else {
            request.getRequestDispatcher(PagePath.PAGE_AUTHORIZATION).forward(request, response);
        }
    }

    private void redirectToAccount(final HttpServletRequest request, final HttpServletResponse response,
                                   final String role) throws ServletException, IOException {
        Command menuPage;

        switch (role) {
            case ("admin"):
                menuPage = new AdminMenu();
                request.getRequestDispatcher(menuPage.execute(request)).forward(request, response);
            case ("master"):
                menuPage = new MasterPage();
                request.getRequestDispatcher(menuPage.execute(request)).forward(request, response);
            case ("client"):
                menuPage = new ClientFutureRecordPage();
                request.getRequestDispatcher(menuPage.execute(request)).forward(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}