package by.epam.pharmacy.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * Implements the following methods from the interface {@link Filter}: init, destroy, doFilter
 */
@WebFilter(urlPatterns = {"/*"},
        initParams = {
                @WebInitParam(name = "encoding", value = "UTF-8", description = "Encoding Param")})
public class EncodingFilter implements Filter {
    private String code;

    /**
     * Called by the web container to indicate to a filter that it is being placed into service. The servlet container
     * calls the init method exactly once after instantiating the filter. The init method must complete successfully
     * before the filter is asked to do any filtering work.
     * Extracts encoding value from {@link FilterConfig} parameters.
     *
     * @param fConfig a filter configuration object used by a servlet container to pass information to a filter
     *                     during initialization.
     * @throws ServletException if an exception occurs that interrupts the filter's normal operation
     */
    public void init(FilterConfig fConfig) throws ServletException {
        code = fConfig.getInitParameter("encoding");
    }

    /**
     * The doFilter method of the Filter is called by the container each time a request/response pair is passed through
     * the chain due to a client request for a resource at the end of the chain. The FilterChain passed in to this method
     * allows the Filter to pass on the request and response to the next entity in the chain.
     * Sets {@link ServletRequest} encoding to UTF-8.
     *
     * @param request an object to provide client request information to a servlet
     * @param response an object to assist a servlet in sending a response to the client
     * @param chain an object provided by the servlet container to the developer giving a view into the invocation
     *                    chain of a filtered request for a resource
     * @throws IOException if an input/output exception occurs that interrupts the filter's normal operation
     * @throws ServletException if an exception occurs that interrupts the filter's normal operation
     */
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        String codeRequest = request.getCharacterEncoding();
        if (code != null && !code.equalsIgnoreCase(codeRequest)) {
            request.setCharacterEncoding(code);
            response.setCharacterEncoding(code);
        }
        chain.doFilter(request, response);
    }

    /**
     * Called by the web container to indicate to a filter that it is being taken out of service. This method is only
     * called once all threads within the filter's doFilter method have exited or after a timeout period has passed.
     * After the web container calls this method, it will not call the doFilter method again on this instance of the
     * filter.
     * Sets encoding object to null.
     */
    public void destroy() {
        code = null;
    }
}