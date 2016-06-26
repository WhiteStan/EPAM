package by.epam.pharmacy.listener;

import by.epam.pharmacy.resource.JspPathName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by Lenovo on 22.04.2016.
 */
@WebListener
public class SessionListenerImpl implements HttpSessionListener {
    private final String DEFAULT_LOCALE = "ru_RU";
    private final String DEFAUILT_PAGE = JspPathName.PATH_TO_MAIN_JSP;
    private final String DEFAULT_ROLE = "GUEST";
    private final static Logger LOG = LogManager.getLogger(SessionListenerImpl.class);

    public void sessionCreated(HttpSessionEvent sessionEvent) {
        HttpSession session = sessionEvent.getSession();
        session.setAttribute("lang", DEFAULT_LOCALE);
        session.setAttribute("page", DEFAUILT_PAGE);
        session.setAttribute("lastPage", DEFAUILT_PAGE);
        session.setAttribute("role", DEFAULT_ROLE);
    }

    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        LOG.error("Session Destroyed:: ID="+sessionEvent.getSession().getId());
    }
}
