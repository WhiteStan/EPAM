package by.epam.pharmacy.command;

import by.epam.pharmacy.entity.Recipe;
import by.epam.pharmacy.logic.AddRecipeLogic;
import by.epam.pharmacy.resource.JspParamName;
import by.epam.pharmacy.resource.JspPathName;
import by.epam.pharmacy.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Command to add new recipe for some user by doctor
 */
public class AddRecipeCommand implements ActionCommand {
    private static final String PARAM_TERM = "term";
    private static final String PARAM_AMOUNT = "amountOfDrug";

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String drugName = request.getParameter(JspParamName.PARAM_DRUGNAME);
        String login = request.getParameter(JspParamName.PARAM_LOGIN);
        String term = request.getParameter(PARAM_TERM);
        String strAmount = request.getParameter(PARAM_AMOUNT);
        String measureUnit = request.getParameter(JspParamName.PARAM_MEASURE_UNIT);
        String doctorLogin = (String) request.getSession().getAttribute("login");
        Integer amount = null;
        if (strAmount != null) {
            amount = Integer.parseInt(strAmount);
        }
        String[] locale = ((String) request.getSession().getAttribute(JspParamName.PARAM_LOCALE)).split("_");
        Recipe recipe = new Recipe();
        recipe.setTerm(term);
        recipe.setAmount(amount);
        recipe.setDrugName(drugName);
        recipe.setLogin(login);
        recipe.setDoctorLogin(doctorLogin);
        recipe.setMeasurementUnit(measureUnit);
        AddRecipeLogic addRecipeLogic = new AddRecipeLogic();
        if (!addRecipeLogic.add(recipe)) {
            MessageManager messageManager = new MessageManager(new Locale(locale[0], locale[1]));
            request.setAttribute(JspParamName.PARAM_ERROR_DATABASE,
                    messageManager.getProperty("message.errorDataBase"));
        }
        page = JspPathName.PATH_TO_DRUGADD_JSP;
        request.getSession().setAttribute(JspParamName.PARAM_PAGE, page);
        return page;
    }
}
