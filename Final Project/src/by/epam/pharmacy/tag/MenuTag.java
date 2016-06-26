package by.epam.pharmacy.tag;

import by.epam.pharmacy.resource.MessageManager;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Lenovo on 20.06.2016.
 */
public class MenuTag extends TagSupport {
    private String role;

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public int doStartTag() throws JspException {
        String[] locale = ((String)pageContext.getSession().getAttribute("lang")).split("_");
        MessageManager messageManager = new MessageManager(new Locale(locale[0], locale[1]));
        try {
            switch (role) {
                case "USER":
                    pageContext.getOut().write("<ul class=\"nav navbar-nav\"> <li>\n" +
                            "                    <a href=\"controller?command=druglist&page=/jsp/registration.jsp&pageNum=1\" >\n" +
                            messageManager.getProperty("label.menuMedicines") + "</a>\n" +
                            "                </li>\n" +
                            "                <li>\n" +
                            "                    <a href=\"controller?command=openRecipePage&page=/jsp//recipePage.jsp\">\n" +
                            messageManager.getProperty("label.newRecipe") +
                            "                    </a>\n" +
                            "                </li>\n" +
                            "                <li>\n" +
                            "                    <a href=\"controller?command=openBasket&page=/jsp/basket.jsp\" >\n" +
                            messageManager.getProperty("label.basket") + "</a>\n" +
                            "                </li></ul>");
                    break;
                case "DOCTOR":
                    pageContext.getOut().write("<ul class=\"nav navbar-nav\"> <li>\n" +
                            "                    <a href=\"controller?command=openDoctorPage&page=/jsp/modRecipes.jsp\" >\n" +
                            messageManager.getProperty("label.confirmRecipes") + "</a>\n" +
                            "                </li>\n" +
                            "                <li>\n" +
                            "                    <a href=\"controller?command=openRecipeConfirmationPage&page=/jsp/modRecipeRequests.jsp\">\n" +
                            messageManager.getProperty("label.prolongRecipes") +
                            "                    </a>\n" +
                            "                </li>\n" +
                            "                <li>\n" +
                            "                    <a href=\"controller?command=openPage&page=/jsp/addRecipe.jsp\" >\n" +
                            messageManager.getProperty("label.addRecipe") + "</a>\n" +
                            "                </li></ul>");
                    break;
                case "DRUGGIST":
                    pageContext.getOut().write("<ul class=\"nav navbar-nav\"> <li>\n" +
                            "                    <a href=\"controller?command=openPage&page=/jsp/addDrug.jsp\" >\n" +
                            messageManager.getProperty("label.addDrug") + "</a>\n" +
                            "                </li>\n" +
                            "                <li>\n" +
                            "                    <a href=\"controller?command=openPage&page=/jsp/deleteDrug.jsp\">\n" +
                            messageManager.getProperty("label.deleteDrug") +
                            "                    </a>\n" +
                            "                </li>\n" +
                            "                <li>\n" +
                            "                    <a href=\"controller?command=openPage&page=/jsp/modDrug.jsp\" >\n" +
                            messageManager.getProperty("label.modDrug") + "</a>\n" +
                            "                </li>"  +
                            "                <li>\n" +
                            "                    <a href=\"controller?command=manageOrders&page=/jsp/manageOrders.jsp\">\n" +
                            messageManager.getProperty("label.manageOrders") +
                            "                    </a>\n" +
                            "                </li>\n" +
                            "                <li>\n" +
                            "                    <a href=\"controller?command=openPage&page=/jsp/orderSearch.jsp\">\n" +
                            messageManager.getProperty("label.manageOrders") +
                            "                    </a>\n" +
                            "                </li>" +
                            "                <li>\n" +
                            "                    <a href=\"controller?command=openPage&page=/jsp/addProduct.jsp\">\n" +
                            messageManager.getProperty("label.addProduct") +
                            "                    </a>\n" +
                            "                </li>" +
                            "</ul>\n");
                    break;
                default:
                    pageContext.getOut().write("<ul class=\"nav navbar-nav\"> <li>\n" +
                            "                    <a href=\"controller?command=druglist&page=/jsp/registration.jsp&pageNum=1\" >\n" +
                            messageManager.getProperty("label.menuMedicines") + "</a>\n" +
                            "                </li>\n" +
                            "                <li>\n" +
                            "                    <a href=\"controller?command=openRecipePage&page=/jsp//recipePage.jsp\">\n" +
                            messageManager.getProperty("label.newRecipe") +
                            "                    </a>\n" +
                            "                </li>\n" +
                            "                <li>\n" +
                            "                    <a href=\"controller?command=openBasket&page=/jsp/basket.jsp\" >\n" +
                            messageManager.getProperty("label.basket") + "</a>\n" +
                            "                </li></ul>");
                    break;
            }
        } catch (IOException e){
            throw new JspTagException(e);
        }
        return SKIP_BODY;
    }
}
