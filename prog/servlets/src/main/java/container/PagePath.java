package container;

public interface PagePath {
    String PAGE_ADMIN_MENU = "page/admin/admin-menu";
    String PAGE_ADMIN_FEEDBACK = "page/admin/admin-feedback";
    String PAGE_ADMIN_SCHEDULE = "page/admin/adminPageSchedule";

    String PAGE_CLIENT_APPOINTMENT_CONFIRM = "page/client/make-appointment-confirm";
    String PAGE_CLIENT_MAKE_APPOINTMENT = "/page/client/make-appointment";
    String PAGE_CLIENT_NOTIFICATION = "page/client/client-notification";
    String PAGE_CLIENT_REVIEW = "page/client/client-review";

    String PAGE_MASTER_SCHEDULE = "page/master/masterSchedule";

    String PAGE_AUTHORIZATION = "page/authorization";
    String PAGE_REGISTRATION = "page/registration";

    String REDIRECT_TO_CLIENT_NOTIFICATION_PAGE = "redirect:/client/notification";
    String REDIRECT_TO_LOGIN_PAGE = "redirect:/login";
}
