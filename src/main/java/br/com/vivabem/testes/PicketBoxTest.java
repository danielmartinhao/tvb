//package br.com.vivabem.testes;
//
//import javax.faces.context.FacesContext;
//import javax.inject.Inject;
//import javax.inject.Named;
//import javax.persistence.EntityManager;
//import javax.servlet.ServletContext;
//import javax.transaction.UserTransaction;
//
//import org.picketbox.core.PicketBoxManager;
//import org.picketbox.core.exceptions.AuthenticationException;
//import org.picketbox.core.identity.jpa.EntityManagerPropagationContext;
//import org.picketbox.http.PicketBoxHTTPManager;
//import org.picketbox.http.config.HTTPConfigurationBuilder;
//import org.picketbox.http.config.PicketBoxHTTPConfiguration;
//import org.picketlink.idm.IdentityManager;
//import org.picketlink.idm.credential.internal.Password;
//import org.picketlink.idm.model.Group;
//import org.picketlink.idm.model.Role;
//import org.picketlink.idm.model.SimpleGroup;
//import org.picketlink.idm.model.SimpleRole;
//import org.picketlink.idm.model.SimpleUser;
//
//import br.com.vivabem.sec.CustomConfigurationProvider;
//
//@Named
//public class PicketBoxTest {
//	@Inject
//    private EntityManager em;
//	
//	@Inject
//	private UserTransaction ut;
//	
//	@Inject
//	private FacesContext context;
//	
//	public void testarPicketBox() throws AuthenticationException {
//		EntityManagerPropagationContext.set(em);
//		
//		ServletContext servletContext = (ServletContext) context.getExternalContext().getContext(); 
////		ConfigurationBuilder builder = new ConfigurationBuilder();
//		HTTPConfigurationBuilder builder = (new CustomConfigurationProvider()).getBuilder(servletContext);
//		 
////		builder.identityManager().jpaStore();
//		
//		PicketBoxHTTPConfiguration configuration = (PicketBoxHTTPConfiguration) builder.build();
//		
////		PicketBoxConfiguration configuration = builder.build();
//		
//		 
//		// instantiates a PicketBoxManager with the default configurations
////		PicketBoxManager picketBoxManager = new DefaultPicketBoxManager(configuration);
//		PicketBoxManager picketBoxManager = new PicketBoxHTTPManager(configuration);
//		 
//		picketBoxManager.start();
//		 
////		// create a empty context and set the credentials
////		UserContext authenticatingContext = new UserContext();
////		 
////		authenticatingContext.setCredential(new UsernamePasswordCredential("admin", "admin"));
////		 
////		// authenticate the usert using the provided credentials
////		UserContext authenticatedContext = picketBoxManager.authenticate(authenticatingContext);
////		 
////		// user is authenticated
////		assertNotNull(authenticatedContext);
////		assertTrue(authenticatedContext.isAuthenticated());
//		
//		
//		IdentityManager identityManager = picketBoxManager.getIdentityManager();
//		 
//	    SimpleUser adminUser = new SimpleUser("admin");
//	 
//	    // sets some properties
//	    adminUser.setEmail("admin@picketbox.com");
//	    adminUser.setFirstName("The");
//	    adminUser.setLastName("Admin");
//	    
//	    // creates some roles
//	    Role developerRole = new SimpleRole("developer");
//	    Role adminRole = new SimpleRole("admin");
//
//	    // creates a group
//	    Group picketBoxGroup = new SimpleGroup("The PicketBox Group");
//	    
//	    try {
//	    	ut.begin();
//
//	    	// creates the user
//	    	identityManager.add(adminUser);
//	    	
//	    	// updates the user credential. In this case a password credential.
//	    	identityManager.updateCredential(adminUser, new Password("admin".toCharArray()));
//
//	    	identityManager.add(developerRole);
//	    	identityManager.add(adminRole);
//	    	identityManager.add(picketBoxGroup);
//	    	// grant the roles to the user and make him member of the group
//	    	identityManager.grantRole(adminUser, developerRole);
//	    	identityManager.grantRole(adminUser, adminRole);
//	    	
//	    	identityManager.addToGroup(adminUser, picketBoxGroup);
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
//	}
//}
