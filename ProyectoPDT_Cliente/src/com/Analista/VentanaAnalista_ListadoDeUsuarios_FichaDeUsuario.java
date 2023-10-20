package com.Analista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.entitiesProyecto.Area;
import com.entitiesProyecto.Departamento;
import com.entitiesProyecto.EstadoItr;
import com.entitiesProyecto.Estudiante;
import com.entitiesProyecto.Generacion;
import com.entitiesProyecto.Genero;
import com.entitiesProyecto.Itr;
import com.entitiesProyecto.Localidad;
import com.entitiesProyecto.RolTutor;
import com.entitiesProyecto.Semestre;
import com.entitiesProyecto.TipoUsuario;
import com.entitiesProyecto.Tutor;
import com.entitiesProyecto.Usuario;
import com.entitiesProyecto.UsuarioEstado;
import com.exceptionProyecto.ServiciosException;
import com.serviciosProyecto.DepartamentoBeanRemote;
import com.serviciosProyecto.EstudianteBeanRemote;
import com.serviciosProyecto.ITRBeanRemote;
import com.serviciosProyecto.LocalidadesBeanRemote;
import com.serviciosProyecto.TutoresBeanRemote;
import com.serviciosProyecto.UsuarioEstadoBeanRemote;
import com.serviciosProyecto.UsuariosBeanRemote;

import Atxy2k.CustomTextField.RestrictedTextField;
import net.sourceforge.jdatepicker.impl.DateComponentFormatter;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class VentanaAnalista_ListadoDeUsuarios_FichaDeUsuario extends JPanel {

	private UsuariosBeanRemote usuariosBean;
	private Long documentoAuxiliar;
	
	
	private JLabel lblGeneracion;
	private JLabel lblAreaTutorPertenece;
	private JLabel lblRol;
	private JLabel lblSemestre;
	
	private String generacionAux;
	private String semestreAux;
	private String areaAux;
	private String rolAux;

	private JTextField textFieldNombres;
	private JTextField textFieldApellidos;
	private JTextField textFieldDocumento;
	private JTextField textFieldCorreoP;
	private JTextField textFieldTelefono;
	private JTextField textFieldCorreoI;
	private JComboBox<Genero> comboBoxGenero;
	private JComboBox<Generacion> comboBoxGeneracion;
	private JComboBox<Semestre> comboBoxSemestre;
	private JComboBox<Area> comboBoxArea;
	private JComboBox<RolTutor> comboBoxRol;
	
	public void cambiarContenido(JPanel panelDestino, JPanel panelFuente) {
		panelDestino.removeAll();
		panelDestino.add(panelFuente, BorderLayout.CENTER);
		panelDestino.revalidate();
		panelDestino.repaint();
	}

	public VentanaAnalista_ListadoDeUsuarios_FichaDeUsuario(UsuariosBeanRemote usuariosBean, Usuario p1) {
		this.usuariosBean = usuariosBean;
		setLayout(null);
		
		JPanel panelMain = new JPanel();
		panelMain.setBackground(SystemColor.controlHighlight);
		panelMain.setBounds(10, 0, 779, 451);
		add(panelMain);
		panelMain.setLayout(null);

		JPanel panelForm = new JPanel();
		panelForm.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		panelForm.setBackground(SystemColor.controlHighlight);
		panelForm.setBounds(10, 0, 748, 474);
		panelMain.add(panelForm);
		panelForm.setLayout(null);

		
		// ----------------------DATOS NO MODIFICABLES---------------------------------/
				
		//----------------------NOMBRES--------------------------------//

				JLabel lblNombres = new JLabel("Nombres");
				lblNombres.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblNombres.setBounds(29, 25, 76, 14);
				panelForm.add(lblNombres);

		//----------------------APELLIDOS--------------------------------//

				JLabel lblApellido1 = new JLabel("Apellidos");
				lblApellido1.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblApellido1.setBounds(29, 81, 76, 19);
				panelForm.add(lblApellido1);

		//---------------------FECHA DE NACIMIENTO------------------------------//
				
				JLabel lblFechaDeNacimiento1 = new JLabel("Fecha de Nacimiento");
				lblFechaDeNacimiento1.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblFechaDeNacimiento1.setBounds(29, 354, 143, 13);
				panelForm.add(lblFechaDeNacimiento1);
				
		//----------------------CORREO PERSONAL-------------------------------//

				JLabel lblCorreoElectrnicoPersonal = new JLabel("Correo electrónico personal");
				lblCorreoElectrnicoPersonal.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblCorreoElectrnicoPersonal.setBounds(29, 206, 189, 13);
				panelForm.add(lblCorreoElectrnicoPersonal);

		//--------------------CORREO INSTITUCIONAL------------------------//

				JLabel lblCorreoElectrnicoInstitucional = new JLabel("Correo electrónico institucional");
				lblCorreoElectrnicoInstitucional.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblCorreoElectrnicoInstitucional.setBounds(29, 273, 225, 13);
				panelForm.add(lblCorreoElectrnicoInstitucional);
				
		//----------------------DOCUMENTO--------------------------------//

				JLabel lblDocumento = new JLabel("Documento");
				lblDocumento.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblDocumento.setBounds(29, 141, 76, 14);
				panelForm.add(lblDocumento);
	     
		//----------------------TELEFONO--------------------------------//

				JLabel lblTelfono = new JLabel("Teléfono");
				lblTelfono.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblTelfono.setBounds(282, 25, 76, 14);
				panelForm.add(lblTelfono);				
				
		//----------------------GÉNERO-------------------------------//

				JLabel lblGenero = new JLabel("Género");
				lblGenero.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblGenero.setBounds(282, 86, 76, 14);
				panelForm.add(lblGenero);				

		//----------------------DEPARTAMENTO--------------------------//

				JLabel lblDepartamento = new JLabel("Departamento");
				lblDepartamento.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblDepartamento.setBounds(282, 168, 115, 14);
				panelForm.add(lblDepartamento);

	   //----------------------LOCALIDAD----------------------------//
		
				JLabel lblLocalidad = new JLabel("Localidad");
				lblLocalidad.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblLocalidad.setBounds(282, 246, 115, 14);
				panelForm.add(lblLocalidad);
			
		//----------------------ITR--------------------------------//
				JLabel lblItr = new JLabel("ITR");
				lblItr.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblItr.setBounds(282, 317, 115, 14);
				panelForm.add(lblItr);


		//-----------------------------------TRAER DATOS A LA TABLA----------------------------------------------//
				
				textFieldNombres = new JTextField();
				textFieldNombres.setBounds(29, 45, 179, 25);
				textFieldNombres.setText(p1.getNombres());				
				panelForm.add(textFieldNombres);
							
				textFieldApellidos = new JTextField();
				textFieldApellidos.setBounds(29, 101, 179, 25);
				textFieldApellidos.setText(p1.getApellidos());
				panelForm.add(textFieldApellidos);
							
				textFieldDocumento = new JTextField();
				textFieldDocumento.setBounds(29, 164, 179, 25);
				textFieldDocumento.setText(String.valueOf(p1.getDocumento()));
				documentoAuxiliar = p1.getDocumento();
				panelForm.add(textFieldDocumento);
				
				RestrictedTextField r1;
				r1 = new RestrictedTextField(textFieldDocumento);
		        r1.setLimit(8);
		        
				textFieldCorreoP = new JTextField();
				textFieldCorreoP.setBounds(29, 230, 179, 25);
				textFieldCorreoP.setText(p1.getMail());
				panelForm.add(textFieldCorreoP);
				
				textFieldTelefono = new JTextField();
				textFieldTelefono.setBounds(282, 45, 179, 25);
				textFieldTelefono.setText(p1.getTelefono());
				panelForm.add(textFieldTelefono);
				
				JComboBox comboBoxDepartamento = new JComboBox();
				DepartamentoBeanRemote departamentoBean;
				try {
					departamentoBean = (DepartamentoBeanRemote) InitialContext.doLookup(
							"ejb:/ProyectoPDT_Servidor/DepartamentoBean!com.serviciosProyecto.DepartamentoBeanRemote");
					List<Departamento> dep = departamentoBean.obtenerTodosDepartamento();

					for (Departamento departamento : dep) {
						comboBoxDepartamento.addItem(departamento.getNombre());
					}
				} catch (NamingException e1) {
					e1.printStackTrace();
				}
				
				comboBoxDepartamento.setBounds(282, 194, 179, 24);
				
				panelForm.add(comboBoxDepartamento);
				String departamento = p1.getDepartamento().getNombre();
				comboBoxDepartamento.setSelectedItem(departamento);
				
				
				JComboBox comboBoxLocalidad = new JComboBox();
				LocalidadesBeanRemote localidadBean;
				try {
					localidadBean = (LocalidadesBeanRemote) InitialContext
							.doLookup("ejb:/ProyectoPDT_Servidor/LocalidadesBean!com.serviciosProyecto.LocalidadesBeanRemote");
					List<Localidad> dep = localidadBean.obtenerTodasLocalidades();

					for (Localidad localidad : dep) {
						// con el comando addItem metemos los departamentos en el combobox
						comboBoxLocalidad.addItem(localidad.getNombre());

					}
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				comboBoxLocalidad.setBounds(282, 270, 179, 24);
				panelForm.add(comboBoxLocalidad);
				String localidad = p1.getLocalidade().getNombre();
				comboBoxLocalidad.setSelectedItem(localidad);
				
			
				JComboBox comboBoxItr = new JComboBox();
				ITRBeanRemote itrBean;
				try {
					 itrBean = (ITRBeanRemote) InitialContext
							.doLookup("ejb:/ProyectoPDT_Servidor/ITRBean!com.serviciosProyecto.ITRBeanRemote");

					List<Itr> itr = itrBean.obtenerItrTodos();

					for (Itr itrListado : itr) {
						if (itrListado.getEstado().equals(EstadoItr.ACTIVO)) { // Verifica el estado del TIR
							comboBoxItr.addItem(itrListado.getNombre());
						}
					}

				} catch (NamingException e1) {
					e1.printStackTrace();
				}
				
				comboBoxItr.setBounds(282, 342, 179, 25);
				panelForm.add(comboBoxItr);
				String itr = p1.getItr().getNombre();
				comboBoxItr.setSelectedItem(itr);
				
				textFieldCorreoI = new JTextField();
				textFieldCorreoI.setBounds(29, 297, 225, 25);
				textFieldCorreoI.setText(p1.getMaiInstitucional());
				panelForm.add(textFieldCorreoI);
				
				
				comboBoxGenero = new JComboBox(Genero.values());//
				comboBoxGenero.setSelectedItem(p1.getGenero());
				comboBoxGenero.setBounds(282, 114, 179, 25);
				panelForm.add(comboBoxGenero);

				
				UtilDateModel model = new UtilDateModel();
				JDatePanelImpl datePanel = new JDatePanelImpl(model);
				JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
			
				datePicker.setBounds(29, 373, 179, 25);
				panelForm.add(datePicker);
				UtilDateModel dateModel = (UtilDateModel) datePicker.getModel();
				dateModel.setValue(p1.getFecNacimiento());
				
				
				
				if (p1.getTipo() == TipoUsuario.ESTUDIANTE) {
					
					try {
						EstudianteBeanRemote estudianteBean = (EstudianteBeanRemote) InitialContext
							.doLookup("ejb:/ProyectoPDT_Servidor/EstudianteBean!com.serviciosProyecto.EstudianteBeanRemote");
						generacionAux = estudianteBean.obtenerEstudiantePorIdUsuario(p1.getIdUsuario()).getGeneracion().toString();
						semestreAux = estudianteBean.obtenerEstudiantePorIdUsuario(p1.getIdUsuario()).getSemestre().toString();	
						
						//----------------------GENERACION-------------------------------//
						
						lblGeneracion = new JLabel("Generación");
						lblGeneracion.setFont(new Font("Tahoma", Font.BOLD, 13));
						lblGeneracion.setBounds(523, 100, 182, 13);
						panelForm.add(lblGeneracion);
						
						comboBoxGeneracion = new JComboBox<>(Generacion.values());// 
						comboBoxGeneracion.setBounds(523, 120, 179, 25);
						Generacion generacion = Generacion.valueOf(generacionAux);
						comboBoxGeneracion.setSelectedItem(generacion);

						panelForm.add(comboBoxGeneracion);
						

						
						//----------------------SEMESTRE-------------------------------//


						lblSemestre = new JLabel("Semestre");
						lblSemestre.setFont(new Font("Tahoma", Font.BOLD, 13));
						lblSemestre.setBounds(523, 170, 158, 13);
						panelForm.add(lblSemestre);
						
						comboBoxSemestre = new JComboBox<>(Semestre.values());// 
						comboBoxSemestre.setBounds(523, 190, 179, 25);
						Semestre semestre = Semestre.valueOf(semestreAux);
						comboBoxSemestre.setSelectedItem(semestre);
						
						panelForm.add(comboBoxSemestre);
						


					} catch (NamingException ex) {
					ex.printStackTrace();
					}
				
				} 
				
				if (p1.getTipo() == TipoUsuario.TUTOR) {
					
					try {
						TutoresBeanRemote tutoresBean = (TutoresBeanRemote) InitialContext
							.doLookup("ejb:/ProyectoPDT_Servidor/TutoresBean!com.serviciosProyecto.TutoresBeanRemote");
						areaAux = tutoresBean.obtenerTutorPorIdUsuario(p1.getIdUsuario()).getArea().toString();
						rolAux = tutoresBean.obtenerTutorPorIdUsuario(p1.getIdUsuario()).getTipo().toString();			
					
					} catch (NamingException ex1) {
						ex1.printStackTrace();
					}
				
					//----------------------AREA--------------------------------//

					
					lblAreaTutorPertenece = new JLabel("Área");
					lblAreaTutorPertenece.setFont(new Font("Tahoma", Font.BOLD, 13));
					lblAreaTutorPertenece.setBounds(523, 100, 182, 13);
					panelForm.add(lblAreaTutorPertenece);
					
					
					comboBoxArea = new JComboBox<>(Area.values());// 
					comboBoxArea.setBounds(523, 120, 179, 25);
					Area area = Area.valueOf(areaAux);
					comboBoxArea.setSelectedItem(area);
					
					panelForm.add(comboBoxArea);
	
					
					//----------------------ROL-------------------------------//

					
					lblRol = new JLabel("Rol");
					lblRol.setFont(new Font("Tahoma", Font.BOLD, 13));
					lblRol.setHorizontalAlignment(SwingConstants.LEFT);
					lblRol.setBounds(523, 170, 158, 13);
					panelForm.add(lblRol);
					
					
					comboBoxRol = new JComboBox<>(RolTutor.values());// 
					comboBoxRol.setBounds(523, 190, 179, 25);
					RolTutor rol = RolTutor.valueOf(rolAux);
					comboBoxRol.setSelectedItem(rol);
					panelForm.add(comboBoxRol);

				}
				
		//----------------------------------------DATOS MODIFICABLES-------------------------------------------//

				// ----------------TIPO DE USUARIO----------------------//
				JLabel lblTipoUsuario = new JLabel("Tipo de Usuario");
				lblTipoUsuario.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblTipoUsuario.setBounds(523, 21, 115, 14);
				panelForm.add(lblTipoUsuario);

				JComboBox comboBoxTipo = new JComboBox(TipoUsuario.values());
				comboBoxTipo.setBounds(523, 46, 138, 25);
				comboBoxTipo.setSelectedItem(p1.getTipo());
				comboBoxTipo.setEnabled(false);
				panelForm.add(comboBoxTipo);
				

				// ----------------ESTADO DEL USUARIO----------------------//

				
				JLabel lblEstado = new JLabel("Estado");
				lblEstado.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblEstado.setBounds(523, 275, 115, 14);
				panelForm.add(lblEstado);

				JComboBox comboBoxEstado = new JComboBox();
				comboBoxEstado.setBounds(523, 297, 138, 25);
				panelForm.add(comboBoxEstado);

				try {
					UsuarioEstadoBeanRemote usuarioEstadoBean = (UsuarioEstadoBeanRemote) InitialContext.doLookup(
							"ejb:/ProyectoPDT_Servidor/UsuarioEstadoBean!com.serviciosProyecto.UsuarioEstadoBeanRemote");
					List<UsuarioEstado> usE = usuarioEstadoBean.obtenerUEstadoTodos();

					for (UsuarioEstado estado : usE) {
						comboBoxEstado.addItem(estado.getNombre());
					}
				} catch (NamingException e1) {
					e1.printStackTrace();
				}
				String estado = p1.getUsuaEstado().getNombre();
				comboBoxEstado.setSelectedItem(estado);

		//-----------------------------------MODIFICAR--------------------------------------------//

				JButton btnModificar = new JButton("Modificar");
				btnModificar.setBounds(599, 381, 119, 23);
				panelForm.add(btnModificar);
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Usuario modificado = p1;
						TipoUsuario tipomodifi = (TipoUsuario) comboBoxTipo.getSelectedItem();
						modificado.setTipo(tipomodifi);
						UsuarioEstado oEstadoElegido;
						UsuarioEstadoBeanRemote usuaEstado;
						try {
							usuaEstado = (UsuarioEstadoBeanRemote) InitialContext.doLookup(
									"ejb:/ProyectoPDT_Servidor/UsuarioEstadoBean!com.serviciosProyecto.UsuarioEstadoBeanRemote");
							oEstadoElegido = usuaEstado.obtenerEstadoPorNombre(comboBoxEstado.getSelectedItem().toString());
						} catch (Exception e2) {
							oEstadoElegido = null;
						}

						boolean valido = true;
						if (valido) {
							Object[] opciones = { "Sí", "No" };
							int respuesta = JOptionPane.showOptionDialog(null, "¿Deseas modificar?", "Confirmar modificar",
									JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

							if (respuesta == JOptionPane.YES_OPTION) {
								
								//Departamento
								Departamento oDepartamentoElegido;
								DepartamentoBeanRemote departamentoBean1;
								try {
									departamentoBean1 = (DepartamentoBeanRemote) InitialContext.doLookup(
											"ejb:/ProyectoPDT_Servidor/DepartamentoBean!com.serviciosProyecto.DepartamentoBeanRemote");
									oDepartamentoElegido = departamentoBean1
											.obtenerDepartamentoPorNombre(comboBoxDepartamento.getSelectedItem().toString());
								} catch (NamingException e1) {
									oDepartamentoElegido = null;
								}
								
								// localidad
								Localidad oLocalidadElegido = null;
								LocalidadesBeanRemote localidadBean1;
								try {
									localidadBean1 = (LocalidadesBeanRemote) InitialContext.doLookup(
											"ejb:/ProyectoPDT_Servidor/LocalidadesBean!com.serviciosProyecto.LocalidadesBeanRemote");
									oLocalidadElegido = localidadBean1
											.obtenerLocalidadPorNombre(comboBoxLocalidad.getSelectedItem().toString());
								} catch (NamingException e1) {
									oLocalidadElegido = null;
								}
								
								//itr
								Itr oItrElegido = null;
								ITRBeanRemote itrBean;
								try {
									itrBean = (ITRBeanRemote) InitialContext
											.doLookup("ejb:/ProyectoPDT_Servidor/ITRBean!com.serviciosProyecto.ITRBeanRemote");
									String nombreItr = comboBoxItr.getSelectedItem().toString();
									oItrElegido = itrBean.obtenerItrPorNombre(nombreItr);
								} catch (NamingException e1) {
									oItrElegido = null;
								}
								
								try {
									UsuariosBeanRemote usuariosBean = (UsuariosBeanRemote) InitialContext.doLookup(
											"ejb:/ProyectoPDT_Servidor/UsuariosBean!com.serviciosProyecto.UsuariosBeanRemote");
									modificado.setNombres(textFieldNombres.getText());
									modificado.setApellidos(textFieldApellidos.getText());
									modificado.setDocumento(Long.parseLong(textFieldDocumento.getText()));
									modificado.setMail(textFieldCorreoP.getText());
									modificado.setTelefono(textFieldTelefono.getText());
									modificado.setUsuaEstado(oEstadoElegido);
									modificado.setDepartamento(oDepartamentoElegido);
									modificado.setLocalidade(oLocalidadElegido);
									modificado.setItr(oItrElegido);
									modificado.setMaiInstitucional(textFieldCorreoI.getText());
									modificado.setGenero((Genero) comboBoxGenero.getSelectedItem());
									Date fecha = (Date) datePicker.getModel().getValue();
									modificado.setFecNacimiento(fecha);
									
									if (modificado.getTipo() == TipoUsuario.ESTUDIANTE) {
										
										
										EstudianteBeanRemote estudianteBean = (EstudianteBeanRemote) InitialContext
												.doLookup(
														"ejb:/ProyectoPDT_Servidor/EstudianteBean!com.serviciosProyecto.EstudianteBeanRemote");
										Estudiante estudiante = estudianteBean.obtenerEstudiantePorIdUsuario(modificado.getIdUsuario());

										Generacion nuevaGeneracion = (Generacion) comboBoxGeneracion.getSelectedItem();
										Semestre nuevoSemestre = (Semestre) comboBoxSemestre.getSelectedItem();
										estudiante.setGeneracion(nuevaGeneracion);
										estudiante.setSemestre(nuevoSemestre);
										
										estudianteBean.actualizarEstudiante(estudiante);
										
									} 
									if (modificado.getTipo() == TipoUsuario.TUTOR) {

										TutoresBeanRemote tutoresBean = (TutoresBeanRemote) InitialContext
												.doLookup("ejb:/ProyectoPDT_Servidor/TutoresBean!com.serviciosProyecto.TutoresBeanRemote");
										Tutor tutor = tutoresBean.obtenerTutorPorIdUsuario(modificado.getIdUsuario());

										Area nuevaArea = (Area) comboBoxArea.getSelectedItem();
										RolTutor nuevoRol = (RolTutor) comboBoxRol.getSelectedItem();
										tutor.setArea(nuevaArea);
										tutor.setTipo(nuevoRol);
										
										tutoresBean.actualizarTutor(tutor);
										
									}
									
									
									
									if (textFieldNombres.getText().isEmpty()) {
										JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío");
										return;
									} else if (!textFieldNombres.getText().matches("[a-zA-Z]+( [a-zA-Z]+)?")) {
										JOptionPane.showMessageDialog(null, "El nombre solo debe contener letras");
										textFieldNombres.setText("");
										return;
									} else if (textFieldNombres.getText().length() < 3 || textFieldNombres.getText().length() > 20) {
										JOptionPane.showMessageDialog(null, "El nombre debe tener entre 3 y 20 caracteres");
										textFieldNombres.setText("");
										return;
									} else if (textFieldApellidos.getText().isEmpty()) {
										JOptionPane.showMessageDialog(null, "El apellido no puede estar vacío");
										return;
									} else if (!textFieldApellidos.getText().matches("[a-zA-Z]+( [a-zA-Z]+)?")) {
										JOptionPane.showMessageDialog(null, "El apellido solo debe contener letras");
										textFieldApellidos.setText("");
										return;
									} else if (textFieldApellidos.getText().length() < 3
											|| textFieldApellidos.getText().length() > 20) {
										JOptionPane.showMessageDialog(null, "El apellido debe tener entre 3 y 20 caracteres");
										textFieldApellidos.setText("");
										return;
									}else if (textFieldDocumento.getText().isEmpty()) {
										JOptionPane.showMessageDialog(null, "El documento no puede estar vacío");
										return;
									} else if (!validarCedula(textFieldDocumento.getText())) {
									    JOptionPane.showMessageDialog(null, "El documento ingresado " + textFieldDocumento.getText() + " no es válido. Por favor, intenta nuevamente.");
									    textFieldDocumento.setText("");
									    return; 	
									}else if (usuariosBean.existeDocumento(documentoAuxiliar)) {
										if(documentoAuxiliar != p1.getDocumento()) {
											JOptionPane.showMessageDialog(null, "El documento " + textFieldDocumento.getText() + " ya está en uso. Por favor, intenta nuevamente.");
											textFieldDocumento.setText("");
										return;
										}
									} else if (textFieldCorreoP.getText().isEmpty()) {
										JOptionPane.showMessageDialog(null, "El correo electrónico personal no puede estar vacío");
										return;
									} else if (!textFieldCorreoP.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
										JOptionPane.showMessageDialog(null,
												"El correo electrónico personal no tiene un formato válido");
										return;
									} else if (textFieldCorreoP.getText().length() > 100) {
										JOptionPane.showMessageDialog(null,
												"El correo electrónico personal debe contener menos de 100 caracteres");
										return;
									} else if (usuariosBean.existeCorreoP(textFieldCorreoP.getText())) {
										JOptionPane.showMessageDialog(null,
												"El correo electrónico personal " + textFieldCorreoP.getText() + " ya está en uso. Por favor, intenta nuevamente.");
										return;
									}  else if (textFieldTelefono.getText().isEmpty()) {
										JOptionPane.showMessageDialog(null, "El teléfono de contacto no puede estar vacío");
										return;
									} else if (!textFieldTelefono.getText().matches("\\d+")) {
										JOptionPane.showMessageDialog(null, "El teléfono de contacto debe contener números únicamente");
										return;
									} else if (textFieldTelefono.getText().length() < 8 || textFieldTelefono.getText().length() > 10) {
										JOptionPane.showMessageDialog(null, "El teléfono de contacto debe tener  9 números");
										return;
									} else if (textFieldCorreoI.getText().isEmpty()) {
									    JOptionPane.showMessageDialog(null, "El correo electrónico institucional no puede estar vacío");
									    return;
									} else if (!textFieldCorreoI.getText().matches("^\\w+\\.\\w+@(?:utec\\.edu\\.uy|estudiantes\\.utec\\.edu\\.uy)$")) {
									    JOptionPane.showMessageDialog(null, "El correo electrónico institucional no tiene un formato válido");
									    return;
									} else if (textFieldCorreoI.getText().length() > 100) {
									    JOptionPane.showMessageDialog(null, "El correo electrónico institucional debe contener menos de 100 caracteres");
									    return;
									}
									
									
									usuariosBean.actualizarUsuario(modificado);

								} catch (NamingException e1) {
									e1.printStackTrace();
								} catch (ServiciosException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								JOptionPane.showMessageDialog(null, "Modificación realizada con éxito");
							}
						}
					}
				});

				// -----------------------------------VOLVER--------------------------------------------//

				JButton btnVolver = new JButton("Volver");
				btnVolver.setBounds(599, 415, 119, 23);
				panelForm.add(btnVolver);

				btnVolver.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						VentanaAnalista_ListadoDeUsuarios volver = new VentanaAnalista_ListadoDeUsuarios(usuariosBean,
								VentanaAnalista.getUsuario());

						volver.setBounds(-40, 0, 769, 498);

						cambiarContenido(panelForm, volver);

						volver.setVisible(true);

					}
				});

			}
	
	public static boolean validarCedula(String ced) {
        int correcto = 0;
        int[] cedula;
        int[] factor = {8, 1, 2, 3, 4, 7, 6, 0};
        cedula = new int[8];
        int suma = 0;

        for (int i = 0; i < ced.length(); i++) {
            if (Character.isDigit(ced.charAt(i))) {
                correcto++;
                cedula[i] = Integer.parseInt("" + ced.charAt(i));
                suma = suma + (cedula[i] * factor[i]);
            }
        }

        if (correcto != 8) {
            System.out.println("Debe ingresar solo números o le faltaron dígitos");
            return false;
        } else {
            int resto = suma % 10;
            if (resto == cedula[7]) {
                System.out.println("Correcto");
                return true;
            } else {
                System.out.println("No coincide el dígito verificador : " + resto + " --> Dígito ingresado :" + cedula[7]);
                return false;
            }
        }
    }
}
