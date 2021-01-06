package modelo;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.entidades.Usuario;
import modelo.dao.UsuarioJpaController;
import modelo.dao.exceptions.IllegalOrphanException;
import modelo.dao.exceptions.NonexistentEntityException;

public class FacadeUsuarios extends FacadeViewReporte {

	private static final UsuarioJpaController daoUsuario = new UsuarioJpaController(emf);

	//SELECCIONAR UNA LISTA DE USUARIOS
	public static List<Usuario> getListUsuario() {
		return daoUsuario.findUsuarioEntities();
	}

	//SELECCIONAR UN USUARIO POR EL ID
	public static Usuario getUsuario(int id) {
		return daoUsuario.findUsuario(id);
	}

	//ELIMINAR UN USUARIO
	public static void deleteUsuario(int id) {
		try {
			daoUsuario.destroy(id);
		} catch (IllegalOrphanException | NonexistentEntityException ex) {
			JOptionPane.showMessageDialog(null, "Error:\nNo es posible eliminar este usuario ya que está siendo utilizado en otra tabla, asegúrese de eliminar todas las referencias e intente de nuevo.", "FATAL ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	//EDITAR LOS VALORES DE UN USUARIO
	public static void updateUsuario(int ID, String cedula, String nombres, String apellidos,
		String telefono, String email, String direccion) {
		Usuario actual = daoUsuario.findUsuario(ID);
		actual.setCedula(cedula);
		actual.setNombres(nombres);
		actual.setApellidos(apellidos);
		actual.setTelefono(telefono);
		actual.setEmail(email);
		actual.setDireccion(direccion);
		try {
			daoUsuario.edit(actual);
		} catch (NonexistentEntityException ex) {
			Logger.getLogger(FacadeUsuarios.class.getName()).log(Level.SEVERE, null, ex);
		} catch (Exception ex) {
			Logger.getLogger(FacadeUsuarios.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	//GUARDAR UN NUEVO USUARIO
	public static void saveUsuario(String cedula, String nombres, String apellidos,
		String telefono, String email, String direccion) {
		Usuario nuevo = new Usuario();
		nuevo.setCedula(cedula);
		nuevo.setNombres(nombres);
		nuevo.setApellidos(apellidos);
		nuevo.setTelefono(telefono);
		nuevo.setEmail(email);
		nuevo.setDireccion(direccion);
		daoUsuario.create(nuevo);
	}

}
