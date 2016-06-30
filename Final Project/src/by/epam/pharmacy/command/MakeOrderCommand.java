package by.epam.pharmacy.command;

import by.epam.pharmacy.entity.Drug;
import by.epam.pharmacy.entity.DrugOrdered;
import by.epam.pharmacy.logic.CheckRecipeLogic;
import by.epam.pharmacy.logic.MakeOrderLogic;
import by.epam.pharmacy.resource.JspParamName;
import by.epam.pharmacy.resource.JspPathName;
import by.epam.pharmacy.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;

/**
 * Command to create a new order
 */
public class MakeOrderCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String[] locale = ((String) request.getSession().getAttribute(JspParamName.PARAM_LOCALE)).split("_");
        request.setAttribute(JspParamName.PARAM_LOCALE, request.getParameter(JspParamName.PARAM_LOCALE));
        String role = (String) request.getSession().getAttribute("role");
        String login = (String) request.getSession().getAttribute("login");
        HashSet<String> basket = (HashSet<String>) request.getSession().getAttribute("basket");
        ArrayList<Drug> drugsList = (ArrayList<Drug>) request.getSession().getAttribute("drugs");
        ArrayList<DrugOrdered> drugs = new ArrayList<>();
        CheckRecipeLogic checkRecipeLogic = new CheckRecipeLogic();
        for (String name : basket) {
            String strRecipeId = request.getParameter(name + "RecipeId");
            String strAmount = request.getParameter(name + "Amount");
            Integer recipeId = 0;
            Integer amount = 0;
            if (strRecipeId != null) {
                recipeId = Integer.parseInt(strRecipeId);
            }
            if (strAmount != null) {
                amount = Integer.parseInt(strAmount);
            }
            DrugOrdered drugOrdered = new DrugOrdered(name, recipeId, amount);
            drugs.add(drugOrdered);
        }
        if (!role.equals("GUEST") && checkInStock(drugsList, drugs) && checkRecipeLogic.check(login, drugs)) {
            MakeOrderLogic makeOrderLogic = new MakeOrderLogic();
            makeOrderLogic.addOrder(drugs, login);
            request.getSession().setAttribute("basket", null);
        } else {
            MessageManager messageManager = new MessageManager(new Locale(locale[0], locale[1]));
            request.setAttribute(JspParamName.PARAM_ERROR_REGISTRATION,
                    messageManager.getProperty("message.error"));
        }
        page = JspPathName.PATH_TO_BASKET_JSP;
        request.getSession().setAttribute(JspParamName.PARAM_PAGE, page);
        return page;
    }

    private boolean checkInStock(ArrayList<Drug> drugs, ArrayList<DrugOrdered> drugOrdereds) {
        boolean result = true;
        for (int i = 0; i < drugs.size(); i++) {
            if (drugs.get(i).getInStock() < drugOrdereds.get(i).getAmount()) {
                result = false;
            }
        }
        return result;
    }
}
