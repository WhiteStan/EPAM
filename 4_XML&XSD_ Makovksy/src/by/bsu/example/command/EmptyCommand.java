package by.bsu.example.command;
import javax.servlet.http.HttpServletRequest;
import by.bsu.example.resource.ConfigurationManager;
public class EmptyCommand extends ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
/* в случае ошибки или прямого обращения к контроллеру
* переадресация на страницу ввода логина */
        String page = ConfigurationManager.getProperty("path.page.login");
        return page;
    }
}