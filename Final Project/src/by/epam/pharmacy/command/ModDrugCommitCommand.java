package by.epam.pharmacy.command;

import by.epam.pharmacy.entity.Drug;
import by.epam.pharmacy.logic.UpdateDrugLogic;
import by.epam.pharmacy.resource.JspParamName;
import by.epam.pharmacy.resource.JspPathName;
import by.epam.pharmacy.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Command for modifying selected drug
 */
public class ModDrugCommitCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String name = request.getParameter(JspParamName.PARAM_NAME);
        String internName = request.getParameter(JspParamName.PARAM_INTERN_NAME);
        String strPrice = request.getParameter(JspParamName.PARAM_PRICE);
        String measureUnit = request.getParameter(JspParamName.PARAM_MEASURE_UNIT);
        String strInStock = request.getParameter(JspParamName.PARAM_IN_STOCK);
        String strIsRecipeNeed = request.getParameter(JspParamName.PARAM_RECIPE_NEED);
        String description = request.getParameter(JspParamName.PARAM_DESCRIPTION);
        Integer inStock = null;
        Integer price = null;
        Boolean isRecipeNeed = null;
        if (strInStock != null) {
            inStock = Integer.parseInt(strInStock);
        }
        if (strPrice != null) {
            price = Integer.parseInt(strPrice);
        }
        if (strIsRecipeNeed != null) {
            isRecipeNeed = Boolean.parseBoolean(strIsRecipeNeed);
        }
        String[] locale = ((String) request.getSession().getAttribute(JspParamName.PARAM_LOCALE)).split("_");
        Drug drug = new Drug(name, internName, price,  measureUnit,
                inStock, isRecipeNeed, description);
        UpdateDrugLogic updateDrugLogic = new UpdateDrugLogic();
        if(!updateDrugLogic.update(drug)){
            MessageManager messageManager = new MessageManager(new Locale(locale[0], locale[1]));
            request.setAttribute(JspParamName.PARAM_ERROR_DATABASE,
                    messageManager.getProperty("message.errorDataBase"));
        }
        page = JspPathName.PATH_TO_MODDRUG_JSP;
        request.getSession().setAttribute(JspParamName.PARAM_PAGE, page);
        return page;
    }
}
