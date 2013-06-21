package br.com.vivabem.sec;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.picketlink.Identity;

@WebFilter(urlPatterns = "/faces/sistema/*")
public class SecurityFilter implements Filter {

	@Inject
	private Identity identity;
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		if (!this.identity.isLoggedIn()) {
			HttpServletRequest request = (HttpServletRequest) req;
			
			if (request.getRequestURI().startsWith(request.getContextPath() + "/faces/sistema")) {
				req.getServletContext().getRequestDispatcher("/faces/login.xhtml").forward(req, response);
			} else {
				chain.doFilter(req, response);
			}
		} else {
			chain.doFilter(req, response);
		}
	}

	@Override
	public void destroy() {
		
	}

}
