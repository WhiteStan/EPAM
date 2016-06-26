package by.epam.pharmacy.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.pharmacy.resource.JspPathName;

public class EmptyCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
/* в случае ошибки или прямого обращения к контроллеру
* переадресация на страницу ввода логина */
        String page = JspPathName.PATH_TO_MAIN_JSP;
        return page;
    }
}