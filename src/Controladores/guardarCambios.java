package Controladores;

import app.PrestamosITCA;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import modelo.FacadeEquipos;
import modelo.FacadePrestamos;
import modelo.FacadeUsuarios;

public class guardarCambios extends PrestamosITCA {

	public void guardarUsuario() {
		//GUARDAR UN NUEVO USUARIO
		FacadeUsuarios.saveUsuario(txtCedula.getText(), txtNombres.getText(), txtApellidos.getText(),
			txtTelefono.getText(), txtEmail.getText(), txtDireccion.getText());

		//MOSTRAR MENSAJE PARA CONFIRMAR QUE SE HA CREADO UN USARUIO NEUVO
		JOptionPane.showMessageDialog(this, "Se ha creado un nuevo usuario exitosamente!", "Nuevo Usario",
			JOptionPane.INFORMATION_MESSAGE);
	}

	public void actualizarUsuario() {
		//PREGUNTAR SI ESTA SEGURO DE ACTUALIZAR EL USUARIO
		int res = JOptionPane.showConfirmDialog(this, "¿Está seguro de actualizar este usuarios?", "Actualizar usuario",
			JOptionPane.YES_NO_OPTION);
		if (res == 0) {
			//ACTUALIZAR UN NUEVO USUARIO
			FacadeUsuarios.updateUsuario(Integer.parseInt(txtIdUsuario.getText()), txtCedula.getText(),
				txtNombres.getText(), txtApellidos.getText(),
				txtTelefono.getText(), txtEmail.getText(), txtDireccion.getText());
			//MOSTRAR MENSAJE PARA CONFIRMAR QUE SE HA CREADO UN USARUIO NEUVO
			JOptionPane.showMessageDialog(this, "Se ha modificado el usuario"
				+ txtNombres.getText() + txtApellidos.getText() + "exitosamente!", "Actualizar usuario",
				JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void guardarEquipo() {
		//GUARDAR UN NUEVO EQUIPO
		FacadeEquipos.saveEquipo(txtDescripcion.getText(), txtMarca.getText(), txtModelo.getText(),
			txtColor.getText(), Integer.parseInt(txtCantidad.getText()), txtDepartamento.getText());
		//MOSTRAR MENSAJE PARA CONFIRMAR QUE SE HA CREADO UN EQUIPO NEUVO
		JOptionPane.showMessageDialog(this, "Se ha creado un nuevo equipo exitosamente!", "Nuevo Equipo",
			JOptionPane.INFORMATION_MESSAGE);
	}

	public void actualizarEquipo() {
		//PREGUNTAR SI ESTA SEGURO DE ACTUALIZAR EL EQUIPO
		int res = JOptionPane.showConfirmDialog(this, "¿Está seguro de actualizar este equipo?", "Actualizar Equipo",
			JOptionPane.YES_NO_OPTION);
		if (res == 0) {
			//ACTUALIZAR UN NUEVO EQUIPO
			FacadeEquipos.updateEquipo(Integer.parseInt(txtIdEquipo.getText()), txtDescripcion.getText(),
				txtMarca.getText(), txtModelo.getText(),
				txtColor.getText(), Integer.parseInt(txtCantidad.getText()), txtDepartamento.getText());
			//MOSTRAR MENSAJE PARA CONFIRMAR QUE SE HA CREADO UN EQUIPO NUEVO
			JOptionPane.showMessageDialog(this, "Se ha modificado el equipo"
				+ txtDescripcion.getText() + "exitosamente!", "Actualizar Equipo",
				JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void guardarPrestamo() throws ParseException {
		//GUARDAR UN NUEVO PRESTAMO
		int cantidad = Integer.parseInt(txtCantidadEquipos.getText());
		int totalEquipos = equipoPrestamo.getCantidad();
		if (cantidad <= totalEquipos) {
			FacadePrestamos.savePrestamo(equipoPrestamo, usuarioPrestamo,
				Integer.parseInt(txtCantidadEquipos.getText()),
				new SimpleDateFormat("yyyy/MM/dd").parse(txtFechaSalida.getText()),
				new SimpleDateFormat("yyyy/MM/dd").parse(txtFechaEntrega.getText()));

			//ACTUALIZAR CANTIDAD DE EQUIPOS DISPONIBLES
			int id_equipo = equipoPrestamo.getIdEquipo();
			String _descripcion = equipoPrestamo.getDescripcion();
			String _marca = equipoPrestamo.getMarca();
			String _modelo = equipoPrestamo.getModelo();
			String _color = equipoPrestamo.getColor();
			int _cantidad = totalEquipos - cantidad;
			String _departamento = equipoPrestamo.getPropiedad();
			FacadeEquipos.updateEquipo(id_equipo, _descripcion, _marca, _modelo,
				_color, _cantidad, _departamento);

			//MOSTRAR MENSAJE PARA CONFIRMAR QUE SE HA CREADO UN PRESTAMO NEUVO
			JOptionPane.showMessageDialog(this, "Se ha creado un nuevo PRESTAMO exitosamente!",
				"Nuevo Prestamo",
				JOptionPane.INFORMATION_MESSAGE);
		}

	}

	public void actualizarPrestamo() throws ParseException {
		//PREGUNTAR SI ESTA SEGURO DE ACTUALIZAR EL PRESTAMO
		int res = JOptionPane.showConfirmDialog(this, "¿Está seguro de actualizar este préstamo?", 
			"Actualizar Prestamo",
			JOptionPane.YES_NO_OPTION);
		if (res == 0) {
			//ACTUALIZAR PRESTAMO
			FacadePrestamos.updatePrestamo(Integer.parseInt(String.valueOf(txtIdPrestamo.getText())), 
				equipoPrestamo, usuarioPrestamo, Integer.parseInt(txtCantidadEquipos.getText()), 
				new SimpleDateFormat("yyyy/MM/dd").parse(txtFechaSalida.getText()),
				new SimpleDateFormat("yyyy/MM/dd").parse(txtFechaEntrega.getText()));

			//MOSTRAR MENSAJE PARA CONFIRMAR QUE SE HA CREADO UN PRESTAMO NUEVO
			JOptionPane.showMessageDialog(this, "Se ha modificado el equipo préstamo exitosamente!", 
				"Actualizar Equipo",
				JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
