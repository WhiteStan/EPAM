package by.bsu.example.command;
import javax.servlet.http.HttpServletRequest;
import by.bsu.example.resource.ConfigurationManager;
public class LogoutCommand extends ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.index");

        request.setAttribute("lang", locale);
// уничтожение сессии
        request.getSession().invalidate();
        return page;
    }
}