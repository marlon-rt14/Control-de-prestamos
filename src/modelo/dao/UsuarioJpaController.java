/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.entidades.Prestamo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.dao.exceptions.IllegalOrphanException;
import modelo.dao.exceptions.NonexistentEntityException;
import modelo.entidades.Usuario;

/**
 *
 * @author mjavi
 */
public class UsuarioJpaController implements Serializable {

	public UsuarioJpaController(EntityManagerFactory emf) {
		this.emf = emf;
	}
	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(Usuario usuario) {
		if (usuario.getPrestamoList() == null) {
			usuario.setPrestamoList(new ArrayList<Prestamo>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			List<Prestamo> attachedPrestamoList = new ArrayList<Prestamo>();
			for (Prestamo prestamoListPrestamoToAttach : usuario.getPrestamoList()) {
				prestamoListPrestamoToAttach = em.getReference(prestamoListPrestamoToAttach.getClass(), prestamoListPrestamoToAttach.getIdPrestamos());
				attachedPrestamoList.add(prestamoListPrestamoToAttach);
			}
			usuario.setPrestamoList(attachedPrestamoList);
			em.persist(usuario);
			for (Prestamo prestamoListPrestamo : usuario.getPrestamoList()) {
				Usuario oldIdUsuarioOfPrestamoListPrestamo = prestamoListPrestamo.getIdUsuario();
				prestamoListPrestamo.setIdUsuario(usuario);
				prestamoListPrestamo = em.merge(prestamoListPrestamo);
				if (oldIdUsuarioOfPrestamoListPrestamo != null) {
					oldIdUsuarioOfPrestamoListPrestamo.getPrestamoList().remove(prestamoListPrestamo);
					oldIdUsuarioOfPrestamoListPrestamo = em.merge(oldIdUsuarioOfPrestamoListPrestamo);
				}
			}
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Usuario persistentUsuario = em.find(Usuario.class, usuario.getIdUsuario());
			List<Prestamo> prestamoListOld = persistentUsuario.getPrestamoList();
			List<Prestamo> prestamoListNew = usuario.getPrestamoList();
			List<String> illegalOrphanMessages = null;
			for (Prestamo prestamoListOldPrestamo : prestamoListOld) {
				if (!prestamoListNew.contains(prestamoListOldPrestamo)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Prestamo " + prestamoListOldPrestamo + " since its idUsuario field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			List<Prestamo> attachedPrestamoListNew = new ArrayList<Prestamo>();
			for (Prestamo prestamoListNewPrestamoToAttach : prestamoListNew) {
				prestamoListNewPrestamoToAttach = em.getReference(prestamoListNewPrestamoToAttach.getClass(), prestamoListNewPrestamoToAttach.getIdPrestamos());
				attachedPrestamoListNew.add(prestamoListNewPrestamoToAttach);
			}
			prestamoListNew = attachedPrestamoListNew;
			usuario.setPrestamoList(prestamoListNew);
			usuario = em.merge(usuario);
			for (Prestamo prestamoListNewPrestamo : prestamoListNew) {
				if (!prestamoListOld.contains(prestamoListNewPrestamo)) {
					Usuario oldIdUsuarioOfPrestamoListNewPrestamo = prestamoListNewPrestamo.getIdUsuario();
					prestamoListNewPrestamo.setIdUsuario(usuario);
					prestamoListNewPrestamo = em.merge(prestamoListNewPrestamo);
					if (oldIdUsuarioOfPrestamoListNewPrestamo != null && !oldIdUsuarioOfPrestamoListNewPrestamo.equals(usuario)) {
						oldIdUsuarioOfPrestamoListNewPrestamo.getPrestamoList().remove(prestamoListNewPrestamo);
						oldIdUsuarioOfPrestamoListNewPrestamo = em.merge(oldIdUsuarioOfPrestamoListNewPrestamo);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Integer id = usuario.getIdUsuario();
				if (findUsuario(id) == null) {
					throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
				}
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Usuario usuario;
			try {
				usuario = em.getReference(Usuario.class, id);
				usuario.getIdUsuario();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Prestamo> prestamoListOrphanCheck = usuario.getPrestamoList();
			for (Prestamo prestamoListOrphanCheckPrestamo : prestamoListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Prestamo " + prestamoListOrphanCheckPrestamo + " in its prestamoList field has a non-nullable idUsuario field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			em.remove(usuario);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<Usuario> findUsuarioEntities() {
		return findUsuarioEntities(true, -1, -1);
	}

	public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
		return findUsuarioEntities(false, maxResults, firstResult);
	}

	private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Usuario.class));
			Query q = em.createQuery(cq);
			if (!all) {
				q.setMaxResults(maxResults);
				q.setFirstResult(firstResult);
			}
			return q.getResultList();
		} finally {
			em.close();
		}
	}

	public Usuario findUsuario(Integer id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Usuario.class, id);
		} finally {
			em.close();
		}
	}

	public int getUsuarioCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Usuario> rt = cq.from(Usuario.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}
	
}
