package container;

public interface PagePath {
    String PAGE_ADMIN_MENU = "/WEB-INF/view/admin/admin-menu.jsp";
    String PAGE_ADMIN_FEEDBACK = "/WEB-INF/view/admin/admin-feedback.jsp";
    String PAGE_ADMIN_SCHEDULE = "/WEB-INF/view/admin/adminPageMasters.jsp";

    String PAGE_CLIENT_APPOINTMENT_CONFIRM = "/WEB-INF/view/client/make-appointment-confirm.jsp";
    String PAGE_CLIENT_MAKE_APPOINTMENT = "/WEB-INF/view/client/make-appointment.jsp";
    String PAGE_CLIENT_PAST_RECORD = "/WEB-INF/view/client/client-past-records.jsp";
    String PAGE_CLIENT_FUTURE_RECORD = "/WEB-INF/view/client/client-future-records.jsp";
    String PAGE_CLIENT_FEEDBACK = "/WEB-INF/view/client/feedback.jsp";

    String PAGE_MASTER_SCHEDULE = "/WEB-INF/view/master/masterSchedule.jsp";

    String PAGE_AUTHORIZATION = "/WEB-INF/view/authorization.jsp";
    String PAGE_REGISTRATION = "/WEB-INF/view/registration.jsp";
}
