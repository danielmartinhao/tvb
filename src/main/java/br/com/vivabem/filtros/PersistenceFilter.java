package br.com.vivabem.filtros;

import java.io.IOException;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.transaction.UserTransaction;

import org.picketbox.core.identity.jpa.EntityManagerPropagationContext;

@WebFilter(urlPatterns = "/*")
public class PersistenceFilter implements Filter {
	
	@Inject
	EntityManager em;
	
	@Resource
	private UserTransaction ut;

    @Override	
	public void init(FilterConfig fConfig) throws ServletException {
	}

    @Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	// uses the EntityManagerContext to make the EntityManager available to the JPA Identity Store.
    	EntityManagerPropagationContext.set(this.em);
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
		} finally {
            // clear the context.
            EntityManagerPropagationContext.clear();
        }
	}
    @Override
	public void destroy() {
	}

}
