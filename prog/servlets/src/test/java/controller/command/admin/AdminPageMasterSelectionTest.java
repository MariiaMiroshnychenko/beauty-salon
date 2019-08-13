package controller.command.admin;

import controller.command.impl.admin.AdminPageMasterSelection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AdminPageMasterSelectionTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Spy
    private AdminPageMasterSelection adminPageMasterSelection = new AdminPageMasterSelection();

    @Test
    public void executeTest() {
        when(request.getParameter("master")).thenReturn("2");
        when(request.getParameter("language")).thenReturn("en");
        when(request.getSession()).thenReturn(session);

        assertEquals("/WEB-INF/view/admin/admin-feedback.jsp", adminPageMasterSelection.execute(request));
    }

    @Test(expected = NumberFormatException.class)
    public void executeTestNullCheck() {
        when(request.getParameter("master")).thenReturn(null);
        when(request.getParameter("language")).thenReturn("en");
        when(request.getSession()).thenReturn(session);

        adminPageMasterSelection.execute(request);
    }
}
