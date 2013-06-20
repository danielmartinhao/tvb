//package br.com.vivabem.login;
//
//
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
//
//import javax.annotation.Resource;
//import javax.inject.Inject;
//import javax.persistence.EntityManager;
//import javax.transaction.UserTransaction;
//
//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.arquillian.junit.Arquillian;
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.asset.EmptyAsset;
//import org.jboss.shrinkwrap.api.spec.JavaArchive;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.picketbox.core.DefaultPicketBoxManager;
//import org.picketbox.core.PicketBoxManager;
//import org.picketbox.core.UserContext;
//import org.picketbox.core.authentication.credential.UsernamePasswordCredential;
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
//import br.com.vivabem.Resources;
//import br.com.vivabem.dao.UsuarioDAO;
//import br.com.vivabem.tests.Calculadora;
//
//@RunWith(Arquillian.class)
//public class UserTest {
//	
//	@Inject
//	private EntityManager em;
//	
//	@Resource
//	private UserTransaction ut;
//	
//	@Deployment
//	public static JavaArchive createDeployment(){
//		return ShrinkWrap.create(JavaArchive.class)
//			.addClass(EntityManager.class)
//			.addClass(UserTransaction.class)	
//			.addClass(Resources.class)
//			.addClass(UsuarioDAO.class)
//			.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
//	}
//	
//	@Test
//	public void testUserNamePasswordCredential() throws AuthenticationException {
//		ConfigurationBuilder builder = new ConfigurationBuilder();
//
//		// configure the JPA identity store
//		builder.identityManager().jpaStore();
//		
//		PicketBoxConfiguration configuration = builder.build(); 
//
////		PicketBoxManager picketBoxManager =  createManager(builder);
//		PicketBoxManager picketBoxManager =  new DefaultPicketBoxManager(configuration);
//		
//		picketBoxManager.start();
//		
//		IdentityManager identityManager = picketBoxManager.getIdentityManager();
//		
//		SimpleUser danielUser = new SimpleUser("daniel");
//		identityManager.add(danielUser);
//		identityManager.updateCredential(danielUser, new Password("daniel".toCharArray()));
//		
//		Role gerenciar = new SimpleRole("gerenciar");
//		identityManager.add(gerenciar);
//		
//		Group administracao = new SimpleGroup("administracao");
//		identityManager.add(administracao);
//		identityManager.grantRole(danielUser, gerenciar);
//		identityManager.addToGroup(danielUser, administracao);
//
//		UserContext authenticatingUser = new UserContext();
//
//		authenticatingUser.setCredential(new UsernamePasswordCredential(
//				"daniel", "daniel"));
//
//		// let's authenticate the user
//		UserContext authenticatedUser = picketBoxManager
//				.authenticate(authenticatingUser);
//
//		assertNotNull(authenticatedUser);
//		assertTrue(authenticatedUser.isAuthenticated());
////		assertRoles(authenticatedUser);
////		assertGroups(authenticatedUser);
//	}	
//
//	@Before
//	public void onSetup() throws Exception {
//		
//		try {
//			ut.begin();
//			// sets the current JPA EntityManager instance
//			EntityManagerPropagationContext.set(em);
//		} catch (Exception e) {
//			try {
//				ut.rollback();
//			} catch (Exception e1) {
//				throw new Exception("Falha on rollback transaction!!!", e);
//			}
//		}
//
//	}
//
////	@After
////	public void onFinish() throws Exception {
////		EntityManager entityManager = EntityManagerPropagationContext.get();
////
////		entityManager.flush();
////		entityManager.getTransaction().commit();
////		entityManager.close();
////
////		// clear the context. Do not forget to do that.
////		EntityManagerPropagationContext.clear();
////		this.entityManagerFactory.close();
////	}
//
//}
