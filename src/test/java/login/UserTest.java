package login;

import static org.junit.Assert.*;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.naming.AuthenticationException;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.picketbox.core.PicketBoxManager;
import org.picketbox.core.UserContext;
import org.picketbox.core.authentication.credential.UsernamePasswordCredential;
import org.picketbox.core.identity.jpa.EntityManagerPropagationContext;

public class UserTest {
	
	@Inject
	private EntityManager em;
	
	@Resource
	private UserTransaction ut;

	@Test
	public void testUserNamePasswordCredential() throws AuthenticationException {
		ConfigurationBuilder builder = new ConfigurationBuilder();

		// configure the JPA identity store
		builder.identityManager().jpaStore();

		PicketBoxManager picketBoxManager =  createManager(builder);

		UserContext authenticatingUser = new UserContext();

		authenticatingUser.setCredential(new UsernamePasswordCredential(
				"admin", "admin"));

		// let's authenticate the user
		UserContext authenticatedUser = picketBoxManager
				.authenticate(authenticatingUser);

		assertNotNull(authenticatedUser);
		assertTrue(authenticatedUser.isAuthenticated());
		assertRoles(authenticatedUser);
		assertGroups(authenticatedUser);
	}

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
//				throw new Exception("Falho on rollback transaction!!!", e);
//			}
//		}
//
//	}

//	@After
//	public void onFinish() throws Exception {
//		EntityManager entityManager = EntityManagerPropagationContext.get();
//
//		entityManager.flush();
//		entityManager.getTransaction().commit();
//		entityManager.close();
//
//		// clear the context. Do not forget to do that.
//		EntityManagerPropagationContext.clear();
//		this.entityManagerFactory.close();
//	}

}
