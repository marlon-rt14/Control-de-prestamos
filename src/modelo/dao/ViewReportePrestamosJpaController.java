/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.dao.exceptions.NonexistentEntityException;
import modelo.entidades.ViewReportePrestamos;

/**
 *
 * @author mjavi
 */
public class ViewReportePrestamosJpaController implements Serializable {

	public ViewReportePrestamosJpaController(EntityManagerFactory emf) {
		this.emf = emf;
	}
	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(ViewReportePrestamos viewReportePrestamos) {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(viewReportePrestamos);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(ViewReportePrestamos viewReportePrestamos) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			viewReportePrestamos = em.merge(viewReportePrestamos);
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				String id = viewReportePrestamos.getCedula();
				if (findViewReportePrestamos(id) == null) {
					throw new NonexistentEntityException("The viewReportePrestamos with id " + id + " no longer exists.");
				}
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void destroy(String id) throws NonexistentEntityException {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			ViewReportePrestamos viewReportePrestamos;
			try {
				viewReportePrestamos = em.getReference(ViewReportePrestamos.class, id);
				viewReportePrestamos.getCedula();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The viewReportePrestamos with id " + id + " no longer exists.", enfe);
			}
			em.remove(viewReportePrestamos);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<ViewReportePrestamos> findViewReportePrestamosEntities() {
		return findViewReportePrestamosEntities(true, -1, -1);
	}

	public List<ViewReportePrestamos> findViewReportePrestamosEntities(int maxResults, int firstResult) {
		return findViewReportePrestamosEntities(false, maxResults, firstResult);
	}

	private List<ViewReportePrestamos> findViewReportePrestamosEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(ViewReportePrestamos.class));
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

	public ViewReportePrestamos findViewReportePrestamos(String id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(ViewReportePrestamos.class, id);
		} finally {
			em.close();
		}
	}

	public int getViewReportePrestamosCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<ViewReportePrestamos> rt = cq.from(ViewReportePrestamos.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}
	
}
