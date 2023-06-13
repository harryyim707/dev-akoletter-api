package akoletter.devakoletterapi.util.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.IOException;
public class CORSfilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;

	response.setHeader("Access-Control-Allow-Origin", "*");
response.setHeader("Access-Control-Allow-Method","POST,GET,OPTIONS,DELETE, PUT");
	response.setHeader("Access-Control-Max-Age","3600");
	response.setHeader("Access-Control-Allow-Headers","Content-Type,x-requested-with,Authorization,Axxess-Control-Allow-Origin");

        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {}
}
