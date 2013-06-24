package br.com.vivabem.testes;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.weld.context.http.Http;
import org.picketbox.core.PicketBoxManager;
import org.picketbox.core.UserContext;
import org.picketbox.core.UserCredential;
import org.picketbox.core.authentication.credential.UsernamePasswordCredential;
import org.picketbox.core.ctx.PicketBoxSecurityContext;
import org.picketbox.core.ctx.SecurityContext;
import org.picketbox.core.ctx.SecurityContextPropagation;
import org.picketbox.core.exceptions.AuthenticationException;
import org.picketbox.core.exceptions.ProcessingException;
import org.picketbox.core.identity.jpa.EntityManagerLookupStrategy;
import org.picketbox.http.HTTPUserContext;
import org.picketbox.http.PicketBoxHTTPManager;
import org.picketbox.http.config.HTTPConfigurationBuilder;
import org.picketbox.http.config.PicketBoxHTTPConfiguration;
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.credential.internal.Password;
import org.picketlink.idm.model.Group;
import org.picketlink.idm.model.Role;
import org.picketlink.idm.model.SimpleGroup;
import org.picketlink.idm.model.SimpleRole;
import org.picketlink.idm.model.SimpleUser;

import br.com.vivabem.sec.CustomConfigurationProvider;

@Named
public class PicketBoxTest {
//	@Inject
//    private EntityManager em;
	
//	@Inject
//	private UserTransaction ut;
//	
	@Inject
	private FacesContext context;
	
	public void testarPicketBox() throws AuthenticationException {
//		EntityManagerPropagationContext.set(em);
		
		ServletContext servletContext = (ServletContext) context.getExternalContext().getContext(); 
//		ConfigurationBuilder builder = new ConfigurationBuilder();
		HTTPConfigurationBuilder builder = (new CustomConfigurationProvider()).getBuilder(servletContext);
//		HTTPConfigurationBuilder builder = new HTTPConfigurationBuilder();
		 
//		builder.identityManager().jpaStore();
		
		PicketBoxHTTPConfiguration configuration = (PicketBoxHTTPConfiguration) builder.build();

//		PicketBoxConfiguration configuration = builder.build();
		
		// instantiates a PicketBoxManager with the default configurations
//		PicketBoxManager picketBoxManager = new DefaultPicketBoxManager(configuration);
		PicketBoxManager picketBoxManager = new PicketBoxHTTPManager(configuration);
		
		
		picketBoxManager.start();
		
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

		UserCredential credential = new UsernamePasswordCredential("admin", "admin");
		
		HTTPUserContext authenticatingContext = new HTTPUserContext(request, response, credential);
//		authenticatingContext.setCredential(new UsernamePasswordCredential("admin", "admin"));
		
		UserContext authenticatedContext = picketBoxManager.authenticate(authenticatingContext);
		
		if (authenticatedContext.isAuthenticated()) {
			System.out.println(authenticatedContext.getPrincipal().getName() + " logado!!!!");
			SecurityContext securityContext = new PicketBoxSecurityContext(authenticatedContext);
			try {
				SecurityContextPropagation.setContext(securityContext);
			} catch (ProcessingException e) {
				e.printStackTrace();
			}
		}
		
		
//		// create a empty context and set the credentials
//		UserContext authenticatingContext = new UserContext();
//		 
//		authenticatingContext.setCredential(new UsernamePasswordCredential("admin", "admin"));
//		 
//		// authenticate the usert using the provided credentials
//		UserContext authenticatedContext = picketBoxManager.authenticate(authenticatingContext);
//		 
//		// user is authenticated
//		assertNotNull(authenticatedContext);
//		assertTrue(authenticatedContext.isAuthenticated());
		
//		IdentityManager identityManager = picketBoxManager.getIdentityManager();
		
//	    SimpleUser adminUser = new SimpleUser("admin2");
	 
	    // sets some properties
//	    adminUser.setEmail("admin@picketbox.com");
//	    adminUser.setFirstName("The2");
//	    adminUser.setLastName("Admin2");
//	    
	    // creates some roles
//	    Role developerRole = new SimpleRole("developer");
//	    Role adminRole = new SimpleRole("admin");
//
//	    // creates a group
//	    Group picketBoxGroup = new SimpleGroup("The PicketBox Group");
//	    
//	    // creates the user
//	    identityManager.add(adminUser);
//	    
//	    // updates the user credential. In this case a password credential.
//	    identityManager.updateCredential(adminUser, new Password("admin".toCharArray()));
//	    
//	    //identityManager.add(developerRole);
//	    //identityManager.add(adminRole);
//	    //identityManager.add(picketBoxGroup);
//	    // grant the roles to the user and make him member of the group
//	    identityManager.grantRole(adminUser, developerRole);
//	    identityManager.grantRole(adminUser, adminRole);
//	    
//	    identityManager.addToGroup(adminUser, picketBoxGroup);
	    
//	    try {
////	    	ut.begin();
//
//	    } catch (Exception e){
//	    	try {
//	    		ut.rollback();
//	    	} catch (Exception re) {
//	    		System.out.println(re.getMessage());
//	    		re.printStackTrace();
//	    	}
//	    	System.out.println(e.getMessage());
//	    	e.printStackTrace();
//	    } finally {
//	    	try {
//				ut.commit();
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//				e.printStackTrace();
//			}
//	    }
	}
}
