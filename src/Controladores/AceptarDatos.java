
package Controladores;

import app.PrestamosITCA;
import app.RegistroUsuarios;
import modelo.entidades.Equipo;
import modelo.entidades.Usuario;

//EXTENDER DE LA CLASE PRSTAMO ITCA PARA TENER ACCESO A SUS OBJETOS Y PROPIEDADES
public class AceptarDatos extends PrestamosITCA{
	
	Usuario usuario;
	Equipo equipo;

	//SI EL CONSTRUCTOR RECIBE COMO PARAMETRO UN USUARIO INICIA USUARIO CON USUARIO
	public AceptarDatos(Usuario usu) {
		this.usuario = usu;
	}
	
	//SI EL CONSTRUCTOR RECIBE COMO PARAMETRO UN EQUIPO INICIA EQUIPO CON EQUIPO
	public AceptarDatos(Equipo equi){
		this.equipo = equi;
	}
	
	public void registrarUsuario(){
		setUsuarioPrestamo(usuario);
	}
	
	public void registrarEquipo(){
		setEquipoPrestamo(equipo);
	}
	
}
