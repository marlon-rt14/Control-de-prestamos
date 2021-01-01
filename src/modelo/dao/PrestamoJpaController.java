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
import modelo.entidades.Equipo;
import modelo.entidades.Prestamo;
import modelo.entidades.Usuario;

/**
 *
 * @author mjavi
 */
public class PrestamoJpaController implements Serializable {

	public PrestamoJpaController(EntityManagerFactory emf) {
		this.emf = emf;
	}
	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(Prestamo prestamo) {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Equipo idEquipo = prestamo.getIdEquipo();
			if (idEquipo != null) {
				idEquipo = em.getReference(idEquipo.getClass(), idEquipo.getIdEquipo());
				prestamo.setIdEquipo(idEquipo);
			}
			Usuario idUsuario = prestamo.getIdUsuario();
			if (idUsuario != null) {
				idUsuario = em.getReference(idUsuario.getClass(), idUsuario.getIdUsuario());
				prestamo.setIdUsuario(idUsuario);
			}
			em.persist(prestamo);
			if (idEquipo != null) {
				idEquipo.getPrestamoList().add(prestamo);
				idEquipo = em.merge(idEquipo);
			}
			if (idUsuario != null) {
				idUsuario.getPrestamoList().add(prestamo);
				idUsuario = em.merge(idUsuario);
			}
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(Prestamo prestamo) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Prestamo persistentPrestamo = em.find(Prestamo.class, prestamo.getIdPrestamos());
			Equipo idEquipoOld = persistentPrestamo.getIdEquipo();
			Equipo idEquipoNew = prestamo.getIdEquipo();
			Usuario idUsuarioOld = persistentPrestamo.getIdUsuario();
			Usuario idUsuarioNew = prestamo.getIdUsuario();
			if (idEquipoNew != null) {
				idEquipoNew = em.getReference(idEquipoNew.getClass(), idEquipoNew.getIdEquipo());
				prestamo.setIdEquipo(idEquipoNew);
			}
			if (idUsuarioNew != null) {
				idUsuarioNew = em.getReference(idUsuarioNew.getClass(), idUsuarioNew.getIdUsuario());
				prestamo.setIdUsuario(idUsuarioNew);
			}
			prestamo = em.merge(prestamo);
			if (idEquipoOld != null && !idEquipoOld.equals(idEquipoNew)) {
				idEquipoOld.getPrestamoList().remove(prestamo);
				idEquipoOld = em.merge(idEquipoOld);
			}
			if (idEquipoNew != null && !idEquipoNew.equals(idEquipoOld)) {
				idEquipoNew.getPrestamoList().add(prestamo);
				idEquipoNew = em.merge(idEquipoNew);
			}
			if (idUsuarioOld != null && !idUsuarioOld.equals(idUsuarioNew)) {
				idUsuarioOld.getPrestamoList().remove(prestamo);
				idUsuarioOld = em.merge(idUsuarioOld);
			}
			if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
				idUsuarioNew.getPrestamoList().add(prestamo);
				idUsuarioNew = em.merge(idUsuarioNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Integer id = prestamo.getIdPrestamos();
				if (findPrestamo(id) == null) {
					throw new NonexistentEntityException("The prestamo with id " + id + " no longer exists.");
				}
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void destroy(Integer id) throws NonexistentEntityException {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Prestamo prestamo;
			try {
				prestamo = em.getReference(Prestamo.class, id);
				prestamo.getIdPrestamos();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The prestamo with id " + id + " no longer exists.", enfe);
			}
			Equipo idEquipo = prestamo.getIdEquipo();
			if (idEquipo != null) {
				idEquipo.getPrestamoList().remove(prestamo);
				idEquipo = em.merge(idEquipo);
			}
			Usuario idUsuario = prestamo.getIdUsuario();
			if (idUsuario != null) {
				idUsuario.getPrestamoList().remove(prestamo);
				idUsuario = em.merge(idUsuario);
			}
			em.remove(prestamo);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<Prestamo> findPrestamoEntities() {
		return findPrestamoEntities(true, -1, -1);
	}

	public List<Prestamo> findPrestamoEntities(int maxResults, int firstResult) {
		return findPrestamoEntities(false, maxResults, firstResult);
	}

	private List<Prestamo> findPrestamoEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Prestamo.class));
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

	public Prestamo findPrestamo(Integer id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Prestamo.class, id);
		} finally {
			em.close();
		}
	}

	public int getPrestamoCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Prestamo> rt = cq.from(Prestamo.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}
	
}
