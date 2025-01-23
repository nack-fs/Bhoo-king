package bhooking.ui;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import bhooking.auxiliar.Detalles;
import bhooking.auxiliar.Registro;
import bhooking.model.Castle;
import bhooking.model.Encantamiento.Types;
import bhooking.service.Bhooking;
import bhooking.service.Pedido;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.util.Date;
import java.util.Calendar;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JTextArea;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URL;

public class VentanaPrincipal extends JFrame {

	private Bhooking bhooking = new Bhooking();
	private Castle[] carrusuel;
	private int indexCarrusel;

	private boolean modoPreview=true;

	private static final long serialVersionUID = 1L;
	private JPanel main_panel;
	private JPanel pnInicioSup;
	private JLabel lbIcono;
	private JLabel carrusel_central;
	private JLabel carrusel_der;
	private JLabel carrusel_izq;
	private JLabel bt_carrusel_izq;
	private JButton btnDisponible;
	private JLabel imgGelatina;
	private JLabel imgCupon10;
	private JLabel lbOverlayIzq;
	private JLabel lblTitleCastle;	
	private JLabel lblDescripcion;
	private JLabel bt_carrusel_der;
	private JPanel panel_inicio;
	private JPanel panel_reserva;
	private JPanel pnSuperior_reserva;
	private JLabel btPlayGame;
	private JLabel lbPlay;
	private JPanel pnReserva;
	private JPanel pnFechaInicio;
	private JLabel lblblCheckoutDate;
	private JLabel lbInstruc;
	private JPanel pnFechaRegreso;
	private JLabel lblCheckoutDate;
	private JLabel lbInstruc_1;
	private JButton btnReservar;
	private JLabel val_calabaza;
	private JLabel btSalir;
	private JLabel imgCastillo;
	private JLabel imgInterior;
	private JLabel imgExterior;
	private JLabel lbTitle;
	private JTextArea txrDescripcionReserva;
	private JLabel lbDireccion;

	private Detalles details=new Detalles();
	private JLabel lblNumHab;
	private JLabel lbPrecio;
	private JLabel lblNoche;
	private JSpinner spHabitaciones;
	private JSpinner spAdultos;
	private JLabel lblNAdultos;
	private JLabel lblCupon25;
	private JLabel lbCupon10;
	private JLabel iconoIdioma;
	private JLabel iconoLogOut;
	private JComboBox<Integer> cbDayIn;
	private JComboBox<String> cbMonthIn;
	private JComboBox<Integer> cbYearIn;
	private JComboBox<Integer> cbDayOut;
	private JComboBox<String> cbMonthOut;
	private JComboBox<Integer> cbYearOut;
	private JPanel pnIdiomas;
	private JLabel iconoEngland;
	private JLabel iconoNoruega;
	private JLabel iconoIrlanda;
	private JLabel iconoEspaña;
	private JLabel iconoSuecia;
	private JPanel pnEncantamientos;

	private String actualLocale;
	private ResourceBundle txts;
	private ProcesaPais pp = new ProcesaPais();
	private JLabel lbOverlayDer;
	private ProcesaReservar pr = new ProcesaReservar();
	private boolean exclusive;
	private JButton btnAyuda;
	private boolean gameActive;

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		Locale defaultLocale = Locale.getDefault();
		localizar(defaultLocale);
		actualLocale=defaultLocale.getLanguage();
		setMaximumSize(new Dimension(1920, 1080));
		setMinimumSize(new Dimension(1325, 801));
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				if(isResizable()) {
					asociaImagenBotones();
				}
			}
		});
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int anw=JOptionPane.showConfirmDialog(null, txts.getString("msg.salir"), txts.getString("msg.salirconf"), JOptionPane.YES_NO_OPTION);
				if(anw==JOptionPane.YES_OPTION) {
					System.exit(-1);
				}
			}
		});
		this.modoPreview=false;
		setTitle("Bhoo!king");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/Logotipo Bhoo!king.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1325, 801);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(getMain_panel(), BorderLayout.CENTER);
		inicializar();
		cargaAyuda();
	}

	private void localizar(Locale localizacion) {	
		this.txts=ResourceBundle.getBundle("rcs/Textos",localizacion);
		String imgPais = "/img/"+ txts.getString("logo.pais");
		getIconoIdioma().setIcon(new ImageIcon(VentanaPrincipal.class.getResource(imgPais)));
	}

	protected ResourceBundle getResourceBundle() {
		return txts;
	}
	protected String getActualLocale() {
		return actualLocale;
	}

	public JPanel getMain_panel() {
		if (main_panel == null) {
			main_panel = new JPanel();
			main_panel.setBackground(Color.WHITE);
			main_panel.setLayout(new CardLayout());
			main_panel.add(getPanel_inicio(), "panel_inicio");
			main_panel.add(getPanel_reserva(), "panel_reserva");
		}
		return main_panel;
	}

	private void inicializar() {
		Locale defaultLocale = Locale.getDefault();
		localizar(defaultLocale);
		actualLocale=defaultLocale.getLanguage();
		setLocationRelativeTo(null);
		inicializarCarrusel();
		hideIdiomas();
	}

	private JPanel getPnInicioSup() {
		if (pnInicioSup == null) {
			pnInicioSup = new JPanel();
			pnInicioSup.setBounds(0, 0, 1337, 179);
			pnInicioSup.setBackground(new Color(0, 46, 128));
			pnInicioSup.setLayout(null);
			pnInicioSup.add(getPnIdiomas());
			pnInicioSup.add(getLbIcono());
			pnInicioSup.add(getIconoIdioma());
			pnInicioSup.add(getBtnAyuda());
		}
		return pnInicioSup;
	}
	private JLabel getLbIcono() {
		if (lbIcono == null) {
			lbIcono = new JLabel("");
			lbIcono.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/Logotipo_Bhoo_king.png")));
			lbIcono.setBounds(23, 22, 104, 104);
		}
		return lbIcono;
	}
	private JLabel getCarrusel_central() {
		if (carrusel_central == null) {
			carrusel_central = new JLabel("");
			carrusel_central.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					checkExclusive();
					showBookingWindow();
				}
			});
			carrusel_central.setBounds(206, 190, 388, 388);
		}
		return carrusel_central;
	}
	private JLabel getCarrusel_der() {
		if (carrusel_der == null) {
			carrusel_der = new JLabel("");
			carrusel_der.setBounds(411, 214, 317, 338);
		}
		return carrusel_der;
	}

	private JLabel getCarrusel_izq() {
		if (carrusel_izq == null) {
			carrusel_izq = new JLabel("");
			carrusel_izq.setBounds(77, 214, 317, 338);
		}
		return carrusel_izq;
	}
	private JLabel getBt_carrusel_izq() {
		if (bt_carrusel_izq == null) {
			bt_carrusel_izq = new JLabel("");
			bt_carrusel_izq.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					setCarrusel(indexCarrusel-1);
				}
			});
			bt_carrusel_izq.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/carrusel_izq.png")));
			bt_carrusel_izq.setBounds(246, 658, 36, 41);
		}
		return bt_carrusel_izq;
	}

	private JButton getBtnDisponible() {
		if (btnDisponible == null) {
			btnDisponible = new JButton(txts.getString("bt.disponible"));
			btnDisponible.setMnemonic('D');
			btnDisponible.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					checkExclusive();
					showBookingWindow();
				}
			});
			btnDisponible.setFont(new Font("Century Gothic", Font.BOLD, 14));
			btnDisponible.setBounds(314, 662, 161, 32);
			btnDisponible.setBackground(new Color(156, 156, 156));
		}
		return btnDisponible;
	}

	private void showBookingWindow() {
		((CardLayout) getMain_panel().getLayout()).show(getMain_panel(), "panel_reserva");
		showSelectionExtended();
		setResizable();
	}

	private void setResizable() {
		this.setResizable(true);
	}

	private void setNotResizable() {
		this.setResizable(false);
		this.setBounds(getLocation().x, getLocation().y, 1325, 801);
	}

	private void setImagenAdaptada(JLabel label, String rutaImagen){
		Image imgOriginal = new ImageIcon(getClass().getResource(rutaImagen)).getImage(); 
		Image imgEscalada = imgOriginal.getScaledInstance(label.getWidth(),label.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(imgEscalada);
		label.setIcon(icon);
	}

	private void asociaImagenBotones() {
		setImagenAdaptada(getImgCastillo(),"/img/ESC00"+indexCarrusel+".png");
		setImagenAdaptada(getImgInterior(),"/img/interior_ESC00"+indexCarrusel+".png");
		setImagenAdaptada(getImgExterior(),"/img/exterior_ESC00"+indexCarrusel+".png");
	}

	private JButton getBtnAyuda() {
		if (btnAyuda == null) {
			btnAyuda = new JButton("");
			btnAyuda.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/help_bt.png")));
			btnAyuda.setBackground(new Color(0, 46, 128));
			btnAyuda.setForeground(getForeground()); 
			btnAyuda.setBorderPainted(false); 
			btnAyuda.setFocusPainted(false); 
			btnAyuda.setContentAreaFilled(false); 
			btnAyuda.setBounds(1166, 18, 73, 49);
		}
		return btnAyuda;
	}

	private JLabel getImgGelatina() {
		if (imgGelatina == null) {
			imgGelatina = new JLabel("");
			imgGelatina.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/gelatina.png")));
			imgGelatina.setBounds(905, 123, 406, 660);
		}
		return imgGelatina;
	}
	private JLabel getImgCupon10() {
		if (imgCupon10 == null) {
			imgCupon10 = new JLabel("");
			imgCupon10.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/fantasmas_gif.gif")));
			imgCupon10.setBounds(1026, 173, 480, 480);
		}
		return imgCupon10;
	}
	private JLabel getLbOverlayIzq() {
		if (lbOverlayIzq == null) {
			lbOverlayIzq = new JLabel("");
			lbOverlayIzq.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					setCarrusel(indexCarrusel-1);
				}
			});
			lbOverlayIzq.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/Overlay_carrusel.png")));
			lbOverlayIzq.setBounds(56, 214, 335, 338);
		}
		return lbOverlayIzq;
	}
	private JLabel getLblTitleCastle() {
		if (lblTitleCastle == null) {
			lblTitleCastle = new JLabel("");
			lblTitleCastle.setFont(new Font("Century Gothic", Font.BOLD, 18));
			lblTitleCastle.setBounds(246, 592, 298, 22);
		}
		return lblTitleCastle;
	}

	private void inicializarCarrusel() {
		List<Castle> catalogo = bhooking.showCatalog().getCatalogo();
		this.carrusuel=catalogo.toArray(new Castle[catalogo.size()]);
		this.indexCarrusel = carrusuel.length/2;
		setCarrusel(indexCarrusel);
	}

	private void setImagesCarrusel(int pos) {
		int size=carrusuel.length - 1;
		int indexIzq=(pos-1<0)? -1 : (pos-1>=size)? size-2 : pos-1;
		int indexCentral=(pos<0)? 0 : (pos>=size)? size-1 : pos;
		int indexDer=(pos+1<0)? 0 : (pos+1>=size)? -1 : pos+1;

		if (indexIzq==-1) {
			getCarrusel_izq().setIcon(null);
		} else {
			getCarrusel_izq().setIcon(new ImageIcon(getClass().getResource("/img/ESC00" + indexIzq + ".png")));
		}
		getCarrusel_central().setIcon(new ImageIcon(getClass().getResource("/img/ESC00" + indexCentral + ".png")));
		
		if (indexDer==-1) {
			getCarrusel_der().setIcon(null);
		} else {
			getCarrusel_der().setIcon(new ImageIcon(getClass().getResource("/img/ESC00" + indexDer + ".png")));
		}
	}

	private void setCarrusel(int pos) {
		int size=carrusuel.length-1;
		indexCarrusel=(pos>=size)? size-1 : (pos<0)? 0 : pos;
		setImagesCarrusel(indexCarrusel);
		setInfoCarrusel(carrusuel[indexCarrusel]);
	}

	private void setInfoCarrusel(Castle castle) {
		if (castle!=null) {
			getLblTitleCastle().setText(castle.getDenominacion());
			getLblDescripcion().setText(details.getDirecciones(indexCarrusel)+" , "+castle.getPais());
		} 
	}

	private JLabel getLblDescripcion() {
		if (lblDescripcion == null) {
			lblDescripcion = new JLabel("");
			lblDescripcion.setFont(new Font("Century Gothic", Font.PLAIN, 13));
			lblDescripcion.setBounds(246, 616, 307, 17);
		}
		return lblDescripcion;
	}
	private JLabel getBt_carrusel_der() {
		if (bt_carrusel_der == null) {
			bt_carrusel_der = new JLabel("");
			bt_carrusel_der.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					setCarrusel(indexCarrusel+1);
				}
			});
			bt_carrusel_der.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/carrusel_der.png")));
			bt_carrusel_der.setBounds(508, 658, 36, 41);
		}
		return bt_carrusel_der;
	}


	private JPanel getPanel_inicio() {
		if (panel_inicio == null) {
			panel_inicio = new JPanel();
			panel_inicio.setLayout(null);
			panel_inicio.setBackground(Color.WHITE);
			panel_inicio.setBorder(new EmptyBorder(0, 0, 0, 0));
			panel_inicio.setBounds(100, 100, 1325, 801);
			panel_inicio.add(getLblCupon25());
			panel_inicio.add(getLbPlay());
			panel_inicio.add(getBtPlayGame());

			panel_inicio.add(getCarrusel_central());
			panel_inicio.add(getLbOverlayDer());
			panel_inicio.add(getLbOverlayIzq());
			panel_inicio.add(getImgGelatina());
			panel_inicio.add(getImgCupon10());
			panel_inicio.add(getPnInicioSup());
			panel_inicio.add(getCarrusel_der());
			panel_inicio.add(getCarrusel_izq());
			panel_inicio.add(getBt_carrusel_izq());
			panel_inicio.add(getBtnDisponible());
			panel_inicio.add(getLblTitleCastle());
			panel_inicio.add(getLblDescripcion());
			panel_inicio.add(getBt_carrusel_der());
			panel_inicio.add(getLbCupon10());
			panel_inicio.add(getIconoLogOut());
		}
		return panel_inicio;
	}
	private JPanel getPanel_reserva() {
		if (panel_reserva == null) {
			panel_reserva = new JPanel();
			panel_reserva.setBackground(Color.WHITE);
			panel_reserva.setLayout(null);
			panel_reserva.add(getImgInterior());
			panel_reserva.add(getImgCastillo());
			panel_reserva.add(getPnSuperior_reserva());
			panel_reserva.add(getPnReserva());
			panel_reserva.add(getVal_calabaza());
			panel_reserva.add(getBtSalir());
			panel_reserva.add(getImgExterior());
			panel_reserva.add(getLbTitle());
			panel_reserva.add(getTxrDescripcionReserva());
			panel_reserva.add(getLbDireccion());
		}
		return panel_reserva;
	}
	private JPanel getPnSuperior_reserva() {
		if (pnSuperior_reserva == null) {
			pnSuperior_reserva = new JPanel();
			pnSuperior_reserva.setBounds(0, 0, 1920, 130);
			pnSuperior_reserva.setBackground(new Color(0, 46, 128));
			pnSuperior_reserva.setLayout(null);
			pnSuperior_reserva.add(getPnEncantamientos());
		}
		return pnSuperior_reserva;
	}
	private JLabel getBtPlayGame() {
		if (btPlayGame == null) {
			btPlayGame = new JLabel("");
			btPlayGame.setToolTipText(txts.getString("tooltip.jugar"));
			btPlayGame.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(!gameActive) {
						showGameWindow();
					}
				}
			});
			btPlayGame.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/bt_playGame.png")));
			btPlayGame.setBounds(979, 592, 174, 191);
		}
		return btPlayGame;
	}

	private void showGameWindow() {
		gameActive=true;
		VentanaGame vg = new VentanaGame(this);
		vg.setLocationRelativeTo(this);
		vg.setVisible(true);
	}

	protected void inactiveGame() {
		this.gameActive=false;
	}

	private JLabel getLbPlay() {
		if (lbPlay == null) {
			lbPlay = new JLabel(txts.getString("bt.jugar"));
			lbPlay.setLabelFor(getBtPlayGame());
			lbPlay.setDisplayedMnemonic('J');
			lbPlay.setForeground(new Color(56, 56, 56));
			lbPlay.setHorizontalAlignment(SwingConstants.CENTER);
			lbPlay.setFont(new Font("Century Gothic", Font.BOLD, 20));
			lbPlay.setBounds(979, 608, 174, 25);
		}
		return lbPlay;
	}
	private JPanel getPnReserva() {
		if (pnReserva == null) {
			pnReserva = new JPanel();
			pnReserva.setBounds(154, 157, 272, 395);
			pnReserva.setLayout(null);
			pnReserva.setBackground(new Color(255, 187, 2));
			pnReserva.add(getPnFechaInicio());
			pnReserva.add(getLblblCheckoutDate());
			pnReserva.add(getLbInstruc());
			pnReserva.add(getPnFechaRegreso());
			pnReserva.add(getLblCheckoutDate());
			pnReserva.add(getLbInstruc_1());
			pnReserva.add(getBtnReservar());
			pnReserva.add(getLblNumHab());
			pnReserva.add(getLbPrecio());
			pnReserva.add(getLblNoche());
			pnReserva.add(getSpHabitaciones());
			pnReserva.add(getSpAdultos());
			pnReserva.add(getLblNAdultos());
		}
		return pnReserva;
	}
	private JPanel getPnFechaInicio() {
		if (pnFechaInicio == null) {
			pnFechaInicio = new JPanel();
			pnFechaInicio.setBackground(Color.WHITE);
			pnFechaInicio.setBounds(32, 59, 222, 31);
			pnFechaInicio.setLayout(null);
			pnFechaInicio.add(getCbDayIn());
			pnFechaInicio.add(getCbMonthIn());
			pnFechaInicio.add(getCbYearIn());
		}
		return pnFechaInicio;
	}

	private JLabel getLblblCheckoutDate() {
		if (lblblCheckoutDate == null) {
			lblblCheckoutDate = new JLabel(txts.getString("lb.checkin"));
			lblblCheckoutDate.setFont(new Font("Century Gothic", Font.BOLD, 12));
			lblblCheckoutDate.setBounds(32, 23, 212, 17);
		}
		return lblblCheckoutDate;
	}
	private JLabel getLbInstruc() {
		if (lbInstruc == null) {
			lbInstruc = new JLabel(txts.getString("lb.instruccion"));
			lbInstruc.setForeground(Color.DARK_GRAY);
			lbInstruc.setFont(new Font("Century Gothic", Font.PLAIN, 9));
			lbInstruc.setBounds(32, 43, 222, 17);
		}
		return lbInstruc;
	}
	private JPanel getPnFechaRegreso() {
		if (pnFechaRegreso == null) {
			pnFechaRegreso = new JPanel();
			pnFechaRegreso.setBackground(Color.WHITE);
			pnFechaRegreso.setBounds(32, 150, 222, 31);
			pnFechaRegreso.setLayout(null);
			pnFechaRegreso.add(getCbDayOut());
			pnFechaRegreso.add(getCbMonthOut());
			pnFechaRegreso.add(getCbYearOut());
		}
		return pnFechaRegreso;
	}

	private Date getOutDate() {
		int day =(int) getCbDayOut().getSelectedItem();
		int month =getCbMonthOut().getSelectedIndex();
		int year =(int) getCbYearOut().getSelectedItem();


		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR,year);
		calendar.set(Calendar.MONTH,month); 
		calendar.set(Calendar.DAY_OF_MONTH,day);

		Date date = calendar.getTime();
		return date;
	}

	private Date getInDate() {
		int day =(int) getCbDayIn().getSelectedItem();
		int month =getCbMonthIn().getSelectedIndex();
		int year =(int) getCbYearIn().getSelectedItem();


		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR,year);
		calendar.set(Calendar.MONTH,month); 
		calendar.set(Calendar.DAY_OF_MONTH,day);

		Date date = calendar.getTime();
		return date;
	}

	private void book() {
		bhooking.book(getInDate(), getOutDate(), (int)getSpHabitaciones().getValue(),(int) getSpAdultos().getValue(), carrusuel[indexCarrusel]);
		if(isBookCorrect()) {
			if(!bhooking.isRegistred()) {
				showRegisterWindow();
			}else {
				showPasarelaWindow();
			}
		}
	}

	private void showRegisterWindow() {
		VentanaRegistro vr = new VentanaRegistro(this.bhooking,this);
		vr.setLocationRelativeTo(this);
		vr.setVisible(true);
	}

	private void showPasarelaWindow() {
		VentanaPasarela vp = new VentanaPasarela(this.bhooking,this);
		vp.setLocationRelativeTo(this);
		vp.setVisible(true);
	}

	private JLabel getLblCheckoutDate() {
		if (lblCheckoutDate == null) {
			lblCheckoutDate = new JLabel(txts.getString("lb.checkout"));
			lblCheckoutDate.setFont(new Font("Century Gothic", Font.BOLD, 12));
			lblCheckoutDate.setBounds(32, 114, 212, 17);
		}
		return lblCheckoutDate;
	}
	private JLabel getLbInstruc_1() {
		if (lbInstruc_1 == null) {
			lbInstruc_1 = new JLabel(txts.getString("lb.instruccion"));
			lbInstruc_1.setForeground(Color.DARK_GRAY);
			lbInstruc_1.setFont(new Font("Century Gothic", Font.PLAIN, 9));
			lbInstruc_1.setBounds(32, 134, 222, 17);
		}
		return lbInstruc_1;
	}
	private JButton getBtnReservar() {
		if (btnReservar == null) {
			btnReservar = new JButton(txts.getString("bt.reservar"));
			btnReservar.setMnemonic('R');
			btnReservar.addActionListener(pr);
			btnReservar.setFont(new Font("Century Gothic", Font.BOLD, 18));
			btnReservar.setBounds(42, 321, 177, 51);
		}
		return btnReservar;
	}

	class ProcesaReservar implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(exclusive) {
				JOptionPane.showMessageDialog(null,txts.getString("msg.exclusivo"));
			}else {
				book();
			}
		}
	}

	private JLabel getVal_calabaza() {
		if (val_calabaza == null) {
			val_calabaza = new JLabel("");
			val_calabaza.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/val_calabazas_0.5.png")));
			val_calabaza.setBounds(820, 510, 283, 48);
		}
		return val_calabaza;
	}
	private JLabel getBtSalir() {
		if (btSalir == null) {
			btSalir = new JLabel("");
			btSalir.setToolTipText(txts.getString("tooltip.btsalir"));
			btSalir.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					showInicialWindow();
				}
			});
			btSalir.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/lateral, atr\u00E1s.png")));
			btSalir.setBounds(-15, 261, 80, 229);
		}
		return btSalir;
	}

	protected void showInicialWindow() {
		((CardLayout) getMain_panel().getLayout()).show(getMain_panel(), "panel_inicio");
		restablecerReserva();
		setNotResizable();
	}

	protected void restablecer() {
		this.bhooking=new Bhooking();
		Locale defaultLocale = Locale.getDefault();
		setCountry(defaultLocale.getLanguage());
		setGameAsInactive();
		restablecerReserva();
	}
	
	protected void setGameAsInactive() {
		this.gameActive=false;
	}

	private void restablecerReserva() {
		getCbDayIn().setSelectedIndex(0);
		getCbDayOut().setSelectedIndex(0);
		getCbMonthIn().setSelectedIndex(0);
		getCbMonthOut().setSelectedIndex(0);
		getSpHabitaciones().setValue(1);
		getSpAdultos().setValue(1);
		getCbYearIn().setSelectedIndex(0);
		getCbYearOut().setSelectedIndex(0);
		clearEncantamientos();
	}

	private JLabel getImgCastillo() {
		if (imgCastillo == null) {
			imgCastillo = new JLabel("");
			imgCastillo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/ESC000.png")));
			imgCastillo.setBounds(820, 112, 388, 388);
		}
		return imgCastillo;
	}
	private JLabel getImgInterior() {
		if (imgInterior == null) {
			imgInterior = new JLabel("");
			imgInterior.setBounds(479, 112, 315, 210);
		}
		return imgInterior;
	}
	private JLabel getImgExterior() {
		if (imgExterior == null) {
			imgExterior = new JLabel("");
			imgExterior.setBounds(479, 333, 315, 210);
		}
		return imgExterior;
	}

	private void showSelectionExtended() {
		asociaImagenBotones();
		getLbTitle().setText(carrusuel[indexCarrusel].getDenominacion());
		getTxrDescripcionReserva().setText(carrusuel[indexCarrusel].getDescripcion());
		getLbDireccion().setText(details.getDirecciones(indexCarrusel)+" , "+carrusuel[indexCarrusel].getPais());
		showPuntuation();
		showPrice();
		pintaEncantamientos();
	}

	private void showPuntuation() {
		getVal_calabaza().setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/val_calabazas_"+details.getPuntuaciones(indexCarrusel)+".png")));
	}

	private JLabel getLbTitle() {
		if (lbTitle == null) {
			lbTitle = new JLabel("");
			lbTitle.setFont(new Font("Century Gothic", Font.BOLD, 20));
			lbTitle.setBounds(820, 569, 377, 26);
		}
		return lbTitle;
	}
	private JTextArea getTxrDescripcionReserva() {
		if (txrDescripcionReserva == null) {
			txrDescripcionReserva = new JTextArea();
			txrDescripcionReserva.setEditable(false);
			txrDescripcionReserva.setFont(new Font("Century Gothic", Font.PLAIN, 14));
			txrDescripcionReserva.setLineWrap(true);
			txrDescripcionReserva.setWrapStyleWord(true);
			txrDescripcionReserva.setText("");
			txrDescripcionReserva.setBackground(Color.WHITE);
			txrDescripcionReserva.setBounds(154, 604, 491, 130);
		}
		return txrDescripcionReserva;
	}
	private JLabel getLbDireccion() {
		if (lbDireccion == null) {
			lbDireccion = new JLabel("");
			lbDireccion.setFont(new Font("Century Gothic", Font.PLAIN, 14));
			lbDireccion.setBounds(820, 599, 377, 26);
		}
		return lbDireccion;
	}
	private JLabel getLblNumHab() {
		if (lblNumHab == null) {
			lblNumHab = new JLabel(txts.getString("lb.numhab"));
			lblNumHab.setFont(new Font("Century Gothic", Font.BOLD, 12));
			lblNumHab.setBounds(32, 202, 107, 17);
		}
		return lblNumHab;
	}
	private JLabel getLbPrecio() {
		if (lbPrecio == null) {
			lbPrecio = new JLabel("");
			lbPrecio.setHorizontalAlignment(SwingConstants.RIGHT);
			lbPrecio.setFont(new Font("Tahoma", Font.PLAIN, 22));
			lbPrecio.setBounds(32, 283, 129, 27);
		}
		return lbPrecio;
	}

	private void showPrice() {
		getLbPrecio().setText(Pedido.conversion(actualLocale, (int)carrusuel[indexCarrusel].getPrecio()));
	}

	private JLabel getLblNoche() {
		if (lblNoche == null) {
			lblNoche = new JLabel(txts.getString("lb.noche"));
			lblNoche.setHorizontalAlignment(SwingConstants.LEFT);
			lblNoche.setFont(new Font("Century Gothic", Font.BOLD, 13));
			lblNoche.setBounds(171, 289, 71, 15);
		}
		return lblNoche;
	}
	private JSpinner getSpHabitaciones() {
		if (spHabitaciones == null) {
			spHabitaciones = new JSpinner();
			spHabitaciones.setModel(new SpinnerNumberModel(1, 1, 5, 1));
			spHabitaciones.setBounds(32, 229, 67, 31);
		}
		return spHabitaciones;
	}

	private boolean isBookCorrect() {
		if(!bhooking.areDatesCorrect()) {
			JOptionPane.showMessageDialog(null,txts.getString("joption.correct1"));
			return false;
		}if(!bhooking.isCorrectAdultsRooms()) {
			JOptionPane.showMessageDialog(null,txts.getString("joption.correct2"));
			return false;
		}if(!bhooking.areDatesInRange(getOutDate(), getInDate())) {
			JOptionPane.showMessageDialog(null,txts.getString("joption.correct3"));
			return false;
		}
		return true;
	}

	private JSpinner getSpAdultos() {
		if (spAdultos == null) {
			spAdultos = new JSpinner();
			spAdultos.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
			spAdultos.setBounds(147, 229, 67, 31);
		}
		return spAdultos;
	}
	private JLabel getLblNAdultos() {
		if (lblNAdultos == null) {
			lblNAdultos = new JLabel(txts.getString("lb.adultos"));
			lblNAdultos.setFont(new Font("Century Gothic", Font.BOLD, 12));
			lblNAdultos.setBounds(147, 202, 107, 17);
		}
		return lblNAdultos;
	}
	private JLabel getLblCupon25() {
		if (lblCupon25 == null) {
			lblCupon25 = new JLabel("");
			lblCupon25.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/Cupon.png")));
			lblCupon25.setBounds(1144, 232, 193, 122);
		}
		return lblCupon25;
	}
	private JLabel getLbCupon10() {
		if (lbCupon10 == null) {
			lbCupon10 = new JLabel("");
			lbCupon10.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/Cupon10.png")));
			lbCupon10.setBounds(1098, 389, 203, 135);
		}
		return lbCupon10;
	}
	private JLabel getIconoIdioma() {
		if (iconoIdioma == null) {
			iconoIdioma = new JLabel("");
			iconoIdioma.setToolTipText(txts.getString("tooltip.pais"));
			iconoIdioma.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					showIdiomas();
				}
			});
			iconoIdioma.setBounds(1080, 23, 40, 39);
			iconoIdioma.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/bt_idioma_en.png")));
		}
		return iconoIdioma;
	}
	private JLabel getIconoLogOut() {
		if (iconoLogOut == null) {
			iconoLogOut = new JLabel("");
			iconoLogOut.setToolTipText(txts.getString("tooltip.cerrar"));
			iconoLogOut.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int anw=JOptionPane.showConfirmDialog(null, txts.getString("joption.cerrar"), txts.getString("msg.salirconf"), JOptionPane.YES_NO_OPTION);
					if(anw==JOptionPane.YES_OPTION) {
						restablecer();
					}
				}
			});
			iconoLogOut.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/bt_logOut.png")));
			iconoLogOut.setBounds(10, 702, 58, 51);
		}
		return iconoLogOut;
	}

	void reInicializar() {
		inicializarCarrusel();
		restablecerReserva();
	}

	private void checkExclusive() {
		if(carrusuel[indexCarrusel].getCodigo().equals(Registro.ESC008) || 
				carrusuel[indexCarrusel].getCodigo().equals(Registro.ESC009)) {
			exclusive=true;
			getBtnReservar().setText("");
			getBtnReservar().setIcon((new ImageIcon(VentanaPrincipal.class.getResource("/img/locked.png"))));
		}else {
			exclusive=false;
			getBtnReservar().setIcon(null);
			getBtnReservar().setText(txts.getString("bt.reservar"));
		}
	}

	private static JComboBox<Integer> creadorComboBoxFechas(int primero, int ultimo) {
		JComboBox<Integer> comboBox = new JComboBox<>();
		for (int i=primero; i<=ultimo; i++) {
			comboBox.addItem(i);
		}
		return comboBox;
	}

	private JComboBox<String> createMonthComboBox() {
		String[] meses = {
				txts.getString("meses.en"), txts.getString("meses.feb"), txts.getString("meses.mar"), txts.getString("meses.abr"), txts.getString("meses.may")
				,txts.getString("meses.jun"),txts.getString("meses.jul"), txts.getString("meses.ago"), txts.getString("meses.sep"), txts.getString("meses.oct")
				, txts.getString("meses.nov"), txts.getString("meses.dic")
		};
		JComboBox<String> comboBox = new JComboBox<>(meses);
		return comboBox;
	}

	private void updateDays(JComboBox<String> monthComboBox, JComboBox<Integer> yearComboBox,
			JComboBox<Integer> dayComboBox) {
		int selectedMonth = monthComboBox.getSelectedIndex()+1;
		int selectedYear = (int)yearComboBox.getSelectedItem();
		int daysInMonth = getDaysInMonth(selectedMonth, selectedYear);

		int saved= (int)dayComboBox.getSelectedItem();
		saved=getSavedDay(saved, daysInMonth);
		dayComboBox.removeAllItems();
		for (int i=1;i<=daysInMonth; i++) {
			dayComboBox.addItem(i);
		}
		dayComboBox.setSelectedItem(saved);
	}

	private int getSavedDay(int day,int dayInMonth) {
		if(day<=dayInMonth) {
			return day;
		}else if(dayInMonth==29) {
			return 29;
		}return 28;
	} 

	private int getDaysInMonth(int month, int year) {
		if (month==2) {
			return (year%4==0 && (year%100!=0||year%400==0))? 29 : 28;
		} else if (month==4 || month==6 || month==9 || month==11) {
			return 30;
		}return 31;
	}


	private JComboBox<Integer> getCbDayIn() {
		if (cbDayIn == null) {
			if(modoPreview) {
				cbDayIn=new JComboBox<Integer>();;
			}else {
				cbDayIn = creadorComboBoxFechas(1,31);
			}
			cbDayIn.setBounds(0, 0, 49, 31);
		}
		return cbDayIn;
	}
	private JComboBox<String> getCbMonthIn() {
		if (cbMonthIn == null) {
			if(modoPreview) {
				cbMonthIn=new JComboBox<String>();
			}else {
				cbMonthIn = createMonthComboBox();
			}
			cbMonthIn.setBounds(48, 0, 96, 31);
			cbMonthIn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					updateDays(cbMonthIn, cbYearIn, cbDayIn);
				}
			});
		}
		return cbMonthIn;
	}
	private JComboBox<Integer> getCbYearIn() {
		if (cbYearIn == null) {
			if(modoPreview) {
				cbYearIn=new JComboBox<Integer>();;
			}else {
				cbYearIn = creadorComboBoxFechas(2024,2026);
			}
			cbYearIn.setBounds(144, 0, 78, 31);
			cbYearIn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					updateDays(cbMonthIn, cbYearIn, cbDayIn);
				}
			});
		}
		return cbYearIn;
	}
	private JComboBox<Integer> getCbDayOut() {
		if (cbDayOut == null) {
			if(modoPreview) {
				cbDayOut=new JComboBox<Integer>();;
			}else {
				cbDayOut = creadorComboBoxFechas(1,31);
			}
			cbDayOut.setBounds(0, 0, 49, 31);
		}
		return cbDayOut;
	}
	private JComboBox<String> getCbMonthOut() {
		if (cbMonthOut == null) {
			if(modoPreview) {
				cbMonthOut=new JComboBox<String>();
			}else {
				cbMonthOut = createMonthComboBox();
			}
			cbMonthOut.setBounds(48, 0, 96, 31);
			cbMonthOut.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					updateDays(getCbMonthOut(), getCbYearOut(), getCbDayOut());
				}
			});
		}
		return cbMonthOut;
	}
	private JComboBox<Integer> getCbYearOut() {
		if (cbYearOut == null) {
			if(modoPreview) {
				cbYearOut=new JComboBox<Integer>();;
			}else {
				cbYearOut = creadorComboBoxFechas(2024,2026);
			}
			cbYearOut.setBounds(144, 0, 78, 31);
			cbYearOut.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					updateDays(cbMonthOut, cbYearOut, cbDayOut);
				}
			});
		}
		return cbYearOut;
	} 
	private JPanel getPnIdiomas() {
		if (pnIdiomas == null) {
			pnIdiomas = new JPanel();
			pnIdiomas.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
			pnIdiomas.setBounds(972, 10, 167, 159);
			pnIdiomas.setBackground(new Color(0, 46, 128));
			pnIdiomas.setLayout(new GridLayout(3, 2, 0, 0));
			pnIdiomas.add(getIconoEngland());
			pnIdiomas.add(getIconoEspaña());
			pnIdiomas.add(getIconoIrlanda());
			pnIdiomas.add(getIconoNoruega());
			pnIdiomas.add(getIconoSuecia());
		}
		return pnIdiomas;
	}

	private void showIdiomas() {
		getPnIdiomas().setEnabled(true);
		getPnIdiomas().setVisible(true);
	}

	private void hideIdiomas() {
		getPnIdiomas().setEnabled(false);
		getPnIdiomas().setVisible(false);
	}

	private JLabel getIconoEngland() {
		if (iconoEngland == null) {
			iconoEngland = new JLabel("");
			iconoEngland.setName("en");
			iconoEngland.addMouseListener(pp);
			iconoEngland.setHorizontalAlignment(SwingConstants.CENTER);
			iconoEngland.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/bt_idioma_en.png")));
		}
		return iconoEngland;
	}

	private JLabel getIconoNoruega() {
		if (iconoNoruega == null) {
			iconoNoruega = new JLabel("");
			iconoNoruega.setName("nor");
			iconoNoruega.addMouseListener(pp);
			iconoNoruega.setHorizontalAlignment(SwingConstants.CENTER);
			iconoNoruega.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/bt_idioma_nor.png")));
		}
		return iconoNoruega;
	}
	private JLabel getIconoIrlanda() {
		if (iconoIrlanda == null) {
			iconoIrlanda = new JLabel("");
			iconoIrlanda.setName("ir");
			iconoIrlanda.addMouseListener(pp);
			iconoIrlanda.setHorizontalAlignment(SwingConstants.CENTER);
			iconoIrlanda.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/bt_idioma_ir.png")));
		}
		return iconoIrlanda;
	}
	private JLabel getIconoEspaña() {
		if (iconoEspaña == null) {
			iconoEspaña = new JLabel("");
			iconoEspaña.setName("es");
			iconoEspaña.addMouseListener(pp);
			iconoEspaña.setHorizontalAlignment(SwingConstants.CENTER);
			iconoEspaña.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/bt_idioma_es.png")));
		}
		return iconoEspaña;
	}
	private JLabel getIconoSuecia() {
		if (iconoSuecia == null) {
			iconoSuecia = new JLabel("");
			iconoSuecia.setName("su");
			iconoSuecia.addMouseListener(pp);
			iconoSuecia.setHorizontalAlignment(SwingConstants.CENTER);
			iconoSuecia.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/bt_idioma_su.png")));
		}
		return iconoSuecia;
	}

	class ProcesaPais extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			setCountry(((JLabel)e.getSource()).getName());
		}
	}

	private void setCountry(String code) {
		hideIdiomas();
		actualLocale=code;
		localizar(new Locale(actualLocale));
		bhooking.changeCastlesLanguage(actualLocale);
		inicializarCarrusel();
		recalcularIdioma();
	} 

	private void recalcularIdioma() {
		getBtnDisponible().setText(txts.getString("bt.disponible"));
		getBtPlayGame().setToolTipText(txts.getString("tooltip.jugar"));
		getLbPlay().setText(txts.getString("bt.jugar"));
		getLblblCheckoutDate().setText(txts.getString("lb.checkin"));
		getLbInstruc().setText(txts.getString("lb.instruccion"));
		getLblCheckoutDate().setText(txts.getString("lb.checkout"));
		getLbInstruc_1().setText(txts.getString("lb.instruccion"));
		getBtnReservar().setText(txts.getString("bt.reservar"));
		getBtSalir().setToolTipText(txts.getString("tooltip.btsalir"));
		getLblNumHab().setText(txts.getString("lb.numhab"));
		getLbPrecio().setText(txts.getString("sim.moneda"));
		getLblNoche().setText(txts.getString("lb.noche"));
		getLblNAdultos().setText(txts.getString("lb.adultos"));
		getIconoIdioma().setToolTipText(txts.getString("tooltip.pais"));
		getBtnAyuda().setToolTipText(txts.getString("tooltip.ayuda"));
		getIconoLogOut().setToolTipText(txts.getString("tooltip.cerrar"));

		getPnFechaInicio().remove(getCbMonthIn());
		getPnFechaRegreso().remove(getCbMonthOut());
		cbMonthIn=null; cbMonthOut=null;
		getPnFechaInicio().add(getCbMonthIn());
		getPnFechaRegreso().add(getCbMonthOut());

	}

	private JPanel getPnEncantamientos() {
		if (pnEncantamientos == null) {
			pnEncantamientos = new JPanel();
			pnEncantamientos.setBounds(901, 59, 308, 48);
			pnEncantamientos.setBackground(new Color(0, 46, 128));
			pnEncantamientos.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));
		}
		return pnEncantamientos;
	}

	private void pintaEncantamientos() {
		if(indexCarrusel>0 && indexCarrusel<carrusuel.length) {
			Castle selected = carrusuel[indexCarrusel];
			for(int i=0; i<selected.getEncantamiento().getEncantamientos().size(); i++) {
				Types enc=selected.getEncantamiento().getEncantamientos().get(i);
				JLabel def = new JLabel("");
				def.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/Icono_encantamiento_"+enc.toString()+".png")));
				pnEncantamientos.add(def);
			}
			validate();
		}
	}

	private void clearEncantamientos() {
		pnEncantamientos.removeAll();
		validate();
	}
	private JLabel getLbOverlayDer() {
		if (lbOverlayDer == null) {
			lbOverlayDer = new JLabel("");
			lbOverlayDer.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/Overlay_carrusel.png")));
			lbOverlayDer.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					setCarrusel(indexCarrusel+1);
				}
			});
			lbOverlayDer.setBounds(393, 214, 335, 338);
		}
		return lbOverlayDer;
	}

	private void cargaAyuda(){
		URL hsURL;
		HelpSet hs;
		try {
			File fichero = new File("help/ayuda.hs");
			hsURL = fichero.toURI().toURL();
			hs = new HelpSet(null, hsURL);
		}

		catch (Exception e){
			System.out.println("Ayuda no encontrada");
			return;
		}

		HelpBroker hb = hs.createHelpBroker();
		hb.initPresentation();
		
		hb.enableHelpKey(getRootPane(),"introduccion", hs);

		hb.enableHelpOnButton(getBtnAyuda(), "introduccion", hs);
		
		hb.enableHelp(getPnIdiomas(), "introduccion", hs);
		hb.enableHelp(getPnReserva(), "reserva", hs);
		hb.enableHelp(getBtPlayGame(), "juego", hs);
		hb.enableHelp(getImgCastillo(), "reserva", hs);
	}
}
