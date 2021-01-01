package modelo;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.entidades.Prestamo;
import modelo.dao.PrestamoJpaController;
import modelo.dao.exceptions.IllegalOrphanException;
import modelo.dao.exceptions.NonexistentEntityException;
import modelo.entidades.Equipo;
import modelo.entidades.Usuario;

public class FacadePrestamos extends FacadeViewReporte {

	private static final PrestamoJpaController daoPrestamo = new PrestamoJpaController(emf);
	
	//SELECCIONAR UNA LISTA DE PRESTAMOS
	public static List<Prestamo> getListPrestamo() {
		return daoPrestamo.findPrestamoEntities();
	}

	//SELECCIONAR UN PRESTAMO POR EL ID
	public static Prestamo getPrestamo(int id) {
		return daoPrestamo.findPrestamo(id);
	}

	//ELIMINAR UN PRESTAMO
	public static void deletePrestamo(int id) {
		try {
			daoPrestamo.destroy(id);
		} catch (NonexistentEntityException ex) {
			Logger.getLogger(FacadePrestamos.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	//EDITAR LOS VALORES DE UN EQUIPO
	public static void updatePrestamo(int ID, Equipo equipo, Usuario usuario, int cantidadEquipos, Date fechaSalida, 
		Date fechaEntrega) {
		Prestamo actual = daoPrestamo.findPrestamo(ID);
		actual.setIdEquipo(equipo);
		actual.setIdUsuario(usuario);
		actual.setCantidadEquipos(cantidadEquipos);
		actual.setFechaSalida(fechaSalida);
		actual.setFechaEntrega(fechaEntrega);
		try {
			daoPrestamo.edit(actual);
		} catch (NonexistentEntityException ex) {
			Logger.getLogger(FacadePrestamos.class.getName()).log(Level.SEVERE, null, ex);
		} catch (Exception ex) {
			Logger.getLogger(FacadePrestamos.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	//GUARDAR UN NUEVO USUARIO
	public static void savePrestamo(Equipo equipo, Usuario usuario, int cantidadEquipos, Date fechaSalida, Date fechaEntrega) {
		Prestamo nuevo = new Prestamo();
		nuevo.setIdEquipo(equipo);
		nuevo.setIdUsuario(usuario);
		nuevo.setCantidadEquipos(cantidadEquipos);
		nuevo.setFechaSalida(fechaSalida);
		nuevo.setFechaEntrega(fechaEntrega);
		daoPrestamo.create(nuevo);
	}
}
