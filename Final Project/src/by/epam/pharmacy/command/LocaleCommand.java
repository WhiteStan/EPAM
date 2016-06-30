package by.epam.pharmacy.command;

import by.epam.pharmacy.resource.LangParams;
import by.epam.pharmacy.resource.JspParamName;

import javax.servlet.http.HttpServletRequest;

/**
 * Command to change localization
 */
public class LocaleCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String locale = null;
        String language = (String) request.getSession().getAttribute(JspParamName.PARAM_LOCALE);
        if (language.equals(LangParams.englishLanguage)) {
            locale = LangParams.russianLanguage;
        } else if (language.equals(LangParams.russianLanguage)) {
            locale = LangParams.englishLanguage;
        }
        request.getSession().setAttribute(JspParamName.PARAM_LOCALE, locale);
        page = (String) request.getSession().getAttribute(JspParamName.PARAM_PAGE);
        return page;
    }
}
