//package br.com.vivabem.login;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.picketbox.core.DefaultPicketBoxManager;
//import org.picketbox.core.PicketBoxManager;
//import org.picketbox.core.config.ConfigurationBuilder;
//import org.picketbox.core.config.PicketBoxConfiguration;
//import org.picketbox.core.exceptions.AuthenticationException;
//import org.picketbox.core.identity.jpa.EntityManagerPropagationContext;
//import org.picketlink.idm.IdentityManager;
//import org.picketlink.idm.credential.internal.Password;
//import org.picketlink.idm.model.Group;
//import org.picketlink.idm.model.Role;
//import org.picketlink.idm.model.SimpleGroup;
//import org.picketlink.idm.model.SimpleRole;
//import org.picketlink.idm.model.SimpleUser;
//
////@RunWith(Arquillian.class)
//public class PicketBoxTest {
//	
//	protected static EntityManagerFactory emf;
//    protected EntityManager em;
//
//    @BeforeClass
//    public static void createEntityManagerFactory() {
//        emf = Persistence.createEntityManagerFactory("PetstorePu");
//    }
//
//    @AfterClass
//    public static void closeEntityManagerFactory() {
//        emf.close();
//    }
//
//    @Before
//    public void beginTransaction() {
//        em = emf.createEntityManager();
//        em.getTransaction().begin();
//    }
//
//    @After
//    public void rollbackTransaction() {
//
//        if (em.getTransaction().isActive()) {
//            em.getTransaction().rollback();
//        }
//
//        if (em.isOpen()) {
//            em.close();
//        }
//    }
//
//	
////	private EntityManagerFactory emf;
//	
////	@Deployment
////	public static JavaArchive createDeployment(){
////		return ShrinkWrap.create(JavaArchive.class)
////			.addClass(Resources.class)
////			.addClass(UsuarioDAO.class)
////			.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
////	}
//	
//	@Test
//	public void test() throws AuthenticationException {
////		this.emf = Persistence.createEntityManagerFactory("teste-picketbox");
////		
////		EntityManager entityManager = this.emf.createEntityManager();
//		
//		EntityManagerPropagationContext.set(em);
//		
//		ConfigurationBuilder builder = new ConfigurationBuilder();
////		HTTPConfigurationBuilder builder = new HTTPConfigurationBuilder();
//		 
//		builder.identityManager().jpaStore();
//		
////		PicketBoxHTTPConfiguration configuration = builder.
//		
//		PicketBoxConfiguration configuration = builder.build();
//		
//		 
//		// instantiates a PicketBoxManager with the default configurations
//		PicketBoxManager picketBoxManager = new DefaultPicketBoxManager(configuration);
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
//	    // creates the user
//	    identityManager.add(adminUser);
//	 
//	    // updates the user credential. In this case a password credential.
//	    identityManager.updateCredential(adminUser, new Password("admin".toCharArray()));
//	 
//	    // creates some roles
//	    Role developerRole = new SimpleRole("developer");
//	 
//	    identityManager.add(developerRole);
//	 
//	    Role adminRole = new SimpleRole("admin");
//	 
//	    identityManager.add(adminRole);
//	 
//	    // creates a group
//	    Group picketBoxGroup = new SimpleGroup("The PicketBox Group");
//	 
//	    identityManager.add(picketBoxGroup);
//	 
//	    // grant the roles to the user and make him member of the group
//	    identityManager.grantRole(adminUser, developerRole);
//	    identityManager.grantRole(adminUser, adminRole);
//	 
//	    identityManager.addToGroup(adminUser, picketBoxGroup);
//	}
//
//}
