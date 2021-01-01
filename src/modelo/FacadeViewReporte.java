
package modelo;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.entidades.ViewReportePrestamos;
import modelo.dao.ViewReportePrestamosJpaController;

public class FacadeViewReporte {
	
	public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestamosITCAPU");
	private static final ViewReportePrestamosJpaController daoViewReporte = new ViewReportePrestamosJpaController(emf);
	
	//SELECT
	public static List<ViewReportePrestamos> getListReporte(){
		return daoViewReporte.findViewReportePrestamosEntities();
	}
}
