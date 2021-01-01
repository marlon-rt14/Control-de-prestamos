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
import modelo.entidades.Equipo;

/**
 *
 * @author mjavi
 */
public class EquipoJpaController implements Serializable {

	public EquipoJpaController(EntityManagerFactory emf) {
		this.emf = emf;
	}
	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(Equipo equipo) {
		if (equipo.getPrestamoList() == null) {
			equipo.setPrestamoList(new ArrayList<Prestamo>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			List<Prestamo> attachedPrestamoList = new ArrayList<Prestamo>();
			for (Prestamo prestamoListPrestamoToAttach : equipo.getPrestamoList()) {
				prestamoListPrestamoToAttach = em.getReference(prestamoListPrestamoToAttach.getClass(), prestamoListPrestamoToAttach.getIdPrestamos());
				attachedPrestamoList.add(prestamoListPrestamoToAttach);
			}
			equipo.setPrestamoList(attachedPrestamoList);
			em.persist(equipo);
			for (Prestamo prestamoListPrestamo : equipo.getPrestamoList()) {
				Equipo oldIdEquipoOfPrestamoListPrestamo = prestamoListPrestamo.getIdEquipo();
				prestamoListPrestamo.setIdEquipo(equipo);
				prestamoListPrestamo = em.merge(prestamoListPrestamo);
				if (oldIdEquipoOfPrestamoListPrestamo != null) {
					oldIdEquipoOfPrestamoListPrestamo.getPrestamoList().remove(prestamoListPrestamo);
					oldIdEquipoOfPrestamoListPrestamo = em.merge(oldIdEquipoOfPrestamoListPrestamo);
				}
			}
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(Equipo equipo) throws IllegalOrphanException, NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Equipo persistentEquipo = em.find(Equipo.class, equipo.getIdEquipo());
			List<Prestamo> prestamoListOld = persistentEquipo.getPrestamoList();
			List<Prestamo> prestamoListNew = equipo.getPrestamoList();
			List<String> illegalOrphanMessages = null;
			for (Prestamo prestamoListOldPrestamo : prestamoListOld) {
				if (!prestamoListNew.contains(prestamoListOldPrestamo)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Prestamo " + prestamoListOldPrestamo + " since its idEquipo field is not nullable.");
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
			equipo.setPrestamoList(prestamoListNew);
			equipo = em.merge(equipo);
			for (Prestamo prestamoListNewPrestamo : prestamoListNew) {
				if (!prestamoListOld.contains(prestamoListNewPrestamo)) {
					Equipo oldIdEquipoOfPrestamoListNewPrestamo = prestamoListNewPrestamo.getIdEquipo();
					prestamoListNewPrestamo.setIdEquipo(equipo);
					prestamoListNewPrestamo = em.merge(prestamoListNewPrestamo);
					if (oldIdEquipoOfPrestamoListNewPrestamo != null && !oldIdEquipoOfPrestamoListNewPrestamo.equals(equipo)) {
						oldIdEquipoOfPrestamoListNewPrestamo.getPrestamoList().remove(prestamoListNewPrestamo);
						oldIdEquipoOfPrestamoListNewPrestamo = em.merge(oldIdEquipoOfPrestamoListNewPrestamo);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Integer id = equipo.getIdEquipo();
				if (findEquipo(id) == null) {
					throw new NonexistentEntityException("The equipo with id " + id + " no longer exists.");
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
			Equipo equipo;
			try {
				equipo = em.getReference(Equipo.class, id);
				equipo.getIdEquipo();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The equipo with id " + id + " no longer exists.", enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Prestamo> prestamoListOrphanCheck = equipo.getPrestamoList();
			for (Prestamo prestamoListOrphanCheckPrestamo : prestamoListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This Equipo (" + equipo + ") cannot be destroyed since the Prestamo " + prestamoListOrphanCheckPrestamo + " in its prestamoList field has a non-nullable idEquipo field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			em.remove(equipo);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<Equipo> findEquipoEntities() {
		return findEquipoEntities(true, -1, -1);
	}

	public List<Equipo> findEquipoEntities(int maxResults, int firstResult) {
		return findEquipoEntities(false, maxResults, firstResult);
	}

	private List<Equipo> findEquipoEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Equipo.class));
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

	public Equipo findEquipo(Integer id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Equipo.class, id);
		} finally {
			em.close();
		}
	}

	public int getEquipoCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Equipo> rt = cq.from(Equipo.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}
	
}
