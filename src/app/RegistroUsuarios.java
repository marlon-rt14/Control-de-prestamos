/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import Controladores.AceptarDatos;
import Controladores.IniciarDatos;
import javax.swing.table.DefaultTableModel;
import modelo.FacadeUsuarios;
import modelo.entidades.Usuario;

/**
 *
 * @author mjavi
 */
public class RegistroUsuarios extends javax.swing.JDialog {

	/**
	 * Creates new form NewJDialog
	 */
	public RegistroUsuarios(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
		iniciarDatos();
	}
	
	PrestamosITCA parentFrame;
	public RegistroUsuarios(PrestamosITCA parentFrame){
		this.parentFrame = parentFrame;
		initComponents();
		iniciarDatos();
	}
	
	Usuario usuario;
	
	public void iniciarDatos(){
		//LLENAR LA TABLA CON LOS DATOS DE LO USUARIOS
		IniciarDatos iniciar = new IniciarDatos();
		iniciar.IniciarDatosUsuarios((DefaultTableModel)tblUsuarios.getModel());
	}

	/**
	 * This method is called from within the constructor to initialize the
	 * form. WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jLabel1 = new javax.swing.JLabel();
                jScrollPane2 = new javax.swing.JScrollPane();
                tblUsuarios = new javax.swing.JTable();
                btnAceptarUsuario = new javax.swing.JButton();

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

                jLabel1.setText("SELECCIONAR USUARIO");

                tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {
                                "ID", "Cédula", "Nombres", "Apellidos", "Teléfono", "E-mail", "Dirección"
                        }
                ) {
                        Class[] types = new Class [] {
                                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
                        };
                        boolean[] canEdit = new boolean [] {
                                false, false, false, false, false, false, false
                        };

                        public Class getColumnClass(int columnIndex) {
                                return types [columnIndex];
                        }

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                        }
                });
                tblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                tblUsuariosMouseClicked(evt);
                        }
                });
                jScrollPane2.setViewportView(tblUsuarios);

                btnAceptarUsuario.setText("Aceptar");
                btnAceptarUsuario.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnAceptarUsuarioActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 863, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(btnAceptarUsuario)))
                                .addContainerGap())
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAceptarUsuario)
                                .addContainerGap(14, Short.MAX_VALUE))
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void tblUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuariosMouseClicked
                // TODO add your handling code here:
                //OBTENER EL VALOR DE LA CELDA "ID" DE LA FILA SELECCIONADA DE LA TABLA USUARIOS
                int id = Integer.parseInt(String.valueOf(tblUsuarios.getValueAt(tblUsuarios.getSelectedRow(), 0)));
                //OBTENER EL UN USUARIO EN BASE A NUESTRO ID
                usuario = FacadeUsuarios.getUsuario(id);
        }//GEN-LAST:event_tblUsuariosMouseClicked

        private void btnAceptarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarUsuarioActionPerformed
                // TODO add your handling code here:
                //ENVIAR EL USUARIO A LA CLASE ACEPTAR DATOS PARA QUE SEA USADO POR LA CLASE PRESTAMOS ITCA
                //HACER REFERENCIA A LA CLASE ACEPTAR DATOS
                AceptarDatos aceptar = new AceptarDatos(usuario);
                aceptar.registrarUsuario();
		parentFrame.txtUsuario.setText(usuario.getCedula());
                //CERRAR ESTA VENTANA
                this.dispose();
        }//GEN-LAST:event_btnAceptarUsuarioActionPerformed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(RegistroUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(RegistroUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(RegistroUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(RegistroUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>
		//</editor-fold>

		/* Create and display the dialog */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				RegistroUsuarios dialog = new RegistroUsuarios(new javax.swing.JFrame(), true);
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					@Override
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btnAceptarUsuario;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JScrollPane jScrollPane2;
        private javax.swing.JTable tblUsuarios;
        // End of variables declaration//GEN-END:variables
}