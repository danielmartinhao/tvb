package br.com.vivabem.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import br.com.vivabem.entities.Usuario;



public class UsuarioDAO implements IUsuarioDAO {
	@Inject
	private EntityManager em;
	
	@Inject
	private UserTransaction ut;

	@Override
	public Usuario getForLogin(String usulogin) {
		try {
			Usuario usuario;
			
			try {
				ut.begin();
				Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.usulogin = :usulogin");
				query.setParameter("usulogin", usulogin);
				usuario = (Usuario) query.getSingleResult();
//				for (Permissao p: usuario.getPermissaos()) {
//					System.out.println(p.getPerdesc());
//				}
			} catch (NoResultException e){
				usuario= null;
			}
			ut.commit();
			return usuario;
		} catch (Exception e) {
			try {
				ut.rollback();
			} catch (SystemException se ){
				throw new RuntimeException(se);
			}
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void addUser(Usuario usuario) {
		try {
			try {
				ut.begin();
				em.persist(usuario);
			} finally {
				ut.commit();
			}
		} catch (Exception e) {
			try {
				ut.rollback();
			} catch (SystemException se) {
				throw new RuntimeException(se);
			}
			throw new RuntimeException(e);
		}
	}
}