
package modelo;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.entidades.Equipo;
import modelo.dao.EquipoJpaController;
import modelo.dao.exceptions.IllegalOrphanException;
import modelo.dao.exceptions.NonexistentEntityException;

public class FacadeEquipos extends FacadeViewReporte{
	
	private static final EquipoJpaController daoEquipo = new EquipoJpaController(emf);
	
	//SELECCIONAR UNA LISTA DE EQUIPOS
	public static List<Equipo> getListEquipo() {
		return daoEquipo.findEquipoEntities();
	}

	//SELECCIONAR UN EQUIPO POR EL ID
	public static Equipo getEquipo(int id) {
		return daoEquipo.findEquipo(id);
	}

	//ELIMINAR UN EQUIPO
	public static void deleteEquipo(int id) {
		try {
			daoEquipo.destroy(id);
		} catch (IllegalOrphanException | NonexistentEntityException ex) {
				JOptionPane.showMessageDialog(null, "Error:\nNo es posible eliminar este equipo ya que está siendo utilizado en otra tabla, asegúrese de eliminar todas las referencias e intente de nuevo.", "FATAL ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	//EDITAR LOS VALORES DE UN EQUIPO
	public static void updateEquipo(int ID, String descripcion, String marca, String modelo,
		String color, int cantidad, String propiedad) {
		Equipo actual = daoEquipo.findEquipo(ID);
		actual.setDescripcion(descripcion);
		actual.setMarca(marca);
		actual.setModelo(modelo);
		actual.setColor(color);
		actual.setCantidad(cantidad);
		actual.setPropiedad(propiedad);
		try {
			daoEquipo.edit(actual);
		} catch (NonexistentEntityException ex) {
			Logger.getLogger(FacadeEquipos.class.getName()).log(Level.SEVERE, null, ex);
		} catch (Exception ex) {
			Logger.getLogger(FacadeEquipos.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	//GUARDAR UN NUEVO USUARIO
	public static void saveEquipo(String descripcion, String marca, String modelo,
		String color, int cantidad, String propiedad) {
		Equipo nuevo = new Equipo();
		nuevo.setDescripcion(descripcion);
		nuevo.setMarca(marca);
		nuevo.setModelo(modelo);
		nuevo.setColor(color);
		nuevo.setCantidad(cantidad);
		nuevo.setPropiedad(propiedad);
		daoEquipo.create(nuevo);
	}
}
