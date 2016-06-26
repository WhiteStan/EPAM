package by.epam.pharmacy.tag;

import by.epam.pharmacy.entity.Recipe;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Lenovo on 20.06.2016.
 */
public class RecipesRequestTag extends TagSupport {
    private static final String REQUEST_PARAMETER_NAME_RECIPES = "recipes";
    private ArrayList<Recipe> recipes;
    public void setRecipes(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public int doStartTag() throws JspException {
        Locale locale = (Locale)pageContext.getSession().getAttribute("locale");
        ResourceBundle bundle;
        if (locale != null) {
            bundle = ResourceBundle.getBundle("pagecontent", locale);
        } else {
            bundle = ResourceBundle.getBundle("pagecontent");
        }
        if (!recipes.isEmpty()) {
            try {
                pageContext.getOut().write("<table border=\"2\" border-style=\"solid\">\n" +
                        "                <thead>\n" +
                        "                    <th>" + bundle.getString("label.patientName") + "</th>\n" +
                        "                    <th>" + bundle.getString("label.drugName") + "</th>\n" +
                        "                    <th>" + bundle.getString("label.amount")+ "</th>\n" +
                        "                </thead>\n" +
                        "                <tbody>");
                for (Recipe recipe : recipes) {
                    pageContext.getOut().write("<tr>\n" +
                            "                        <td>\n" +
                            "<input type=\"checkbox\" name=\"\n" +
                            recipe.getId() +
                            "                       value=\"true\">\n" +
                            "                </td>\n" +
                            "                        <td>\n" +
                            recipe.getLogin() +
                            "                        </td>\n" +
                            "                        <td>\n" +
                            recipe.getDrugName() +
                            "                        </td>\n" +
                            "                        <td>\n" +
                            recipe.getAmount() +
                            "                        </td>\n" +
                            "                    </tr>");
                }
                pageContext.getOut().write("</tbody>\n" +
                        "            </table>");
            } catch (IOException e) {
                throw new JspTagException(e);
            }
        } else {
            try {
                pageContext.getOut().write(bundle.getString("RegistrationBook.empty"));
            } catch (IOException e) {
                throw new JspTagException(e);
            }
        }
        return SKIP_BODY;
    }

}