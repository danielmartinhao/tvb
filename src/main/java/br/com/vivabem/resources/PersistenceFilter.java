package br.com.vivabem.resources;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.transaction.UserTransaction;

@WebFilter(urlPatterns = "/*")
public class PersistenceFilter implements Filter {
	
	@Resource
	private UserTransaction ut;

    @Override	
	public void init(FilterConfig fConfig) throws ServletException {
	}

    @Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	
    	try {
			this.ut.begin();
			chain.doFilter(request, response);
			this.ut.commit();
		} catch (Exception e) {
			try {
				this.ut.rollback();
			} catch (Exception e1) {
				System.err.println(e1.getMessage());
				e1.printStackTrace();
			}
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
    @Override
	public void destroy() {
	}

}
