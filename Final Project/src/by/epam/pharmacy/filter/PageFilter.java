package by.epam.pharmacy.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Implements the following methods from the interface {@link Filter}: init, destroy, doFilter
 */
@WebFilter(urlPatterns = {"/*"})
public class PageFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String command = httpServletRequest.getParameter("command");
        if (command != null && !command.equals("Back")) {
            String curPage = (String) httpServletRequest.getSession().getAttribute("page");
            httpServletRequest.getSession().setAttribute("lastPage", curPage);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}