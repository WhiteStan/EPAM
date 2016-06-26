
@WebListener
public class SessionListenerImpl implements HttpSessionListener {
    private final String DEFAULT_LOCALE = "ru_RU";
    private final static Logger LOG = LogManager.getLogger(SessionListenerImpl.class);

    public void sessionCreated(HttpSessionEvent sessionEvent) {
        HttpSession session = sessionEvent.getSession();
        session.setAttribute("locale", DEFAULT_LOCALE);
    }

    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        LOG.error("Session Destroyed:: ID="+sessionEvent.getSession().getId());
    }
}