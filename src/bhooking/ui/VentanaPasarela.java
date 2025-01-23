package bhooking.ui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bhooking.auxiliar.Detalles;
import bhooking.auxiliar.Registro;
import bhooking.model.Descuento;
import bhooking.service.Bhooking;
import bhooking.service.Pedido;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

public class VentanaPasarela extends JDialog {

	private static final long serialVersionUID = 1L;
	private VentanaPrincipal vp;

	private final JPanel contentPanel = new JPanel();
	private Bhooking bhooking=new Bhooking();
	private JButton btnPagar;
	private JButton btnModificar;
	private JButton btnCerrar;
	private JLabel iconoOk;
	private JLabel lblDescuento;
	private JLabel lblAmount;
	private JLabel lblTotalAPagar;

	private ResourceBundle txts;
	private String actualLocale;


	/**
	 * Create the dialog.
	 */
	public VentanaPasarela(Bhooking bhooking,VentanaPrincipal vp) {
		setTitle("Bhoo!king: Resumen pago");
		this.bhooking=bhooking;
		this.vp=vp;
		this.txts=vp.getResourceBundle();
		this.actualLocale=vp.getActualLocale();

		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPasarela.class.getResource("/img/Logotipo Bhoo_king_mini.png")));
		setModal(true);
		setBounds(100, 100, 570, 670);
		setMinimumSize(new Dimension(570, 670));
		setMaximumSize(new Dimension(1325, 801));
		getContentPane().setLayout(new BorderLayout(0, 0));
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getIconoOk());
		{
			JLabel lblLogo = new JLabel("");
			lblLogo.setIcon(new ImageIcon(VentanaPasarela.class.getResource("/img/Logotipo Bhoo_king_mini.png")));
			lblLogo.setBounds(10, 11, 71, 71);
			contentPanel.add(lblLogo);
		}

		JLabel thumb = new JLabel("");
		thumb.setIcon(new ImageIcon(VentanaPasarela.class.getResource("/img/tmb_"+bhooking.showPedido().getCastillo().getCodigo()+".png")));
		thumb.setBounds(402, 106, 114, 114);
		contentPanel.add(thumb);

		JLabel lblTitulo = new JLabel(bhooking.showPedido().getCastillo().getDenominacion());
		lblTitulo.setFont(new Font("Century Gothic", Font.BOLD, 25));
		lblTitulo.setBounds(34, 106, 331, 32);
		contentPanel.add(lblTitulo);
		{
			int index=Registro.getPosibilities().indexOf(bhooking.showPedido().getCastillo().getCodigo());
			JLabel lblDescripcion = new JLabel(new Detalles().getDirecciones(index)+" , "+bhooking.showPedido().getCastillo().getPais());
			lblDescripcion.setFont(new Font("Century Gothic", Font.PLAIN, 12));
			lblDescripcion.setBounds(34, 145, 342, 32);
			contentPanel.add(lblDescripcion);
		}
		{
			Date fechaInicio = bhooking.showPedido().getFecha_inicio();
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd MMMM yyyy", new Locale(actualLocale));
			JLabel lblFechaInicio = new JLabel(txts.getString("lb.fechainicio")+formatoFecha.format(fechaInicio));
			lblFechaInicio.setFont(new Font("Century Gothic", Font.BOLD, 13));
			lblFechaInicio.setBounds(34, 188, 413, 32);
			contentPanel.add(lblFechaInicio);
		}
		{
			Date fechaFinal = bhooking.showPedido().getFecha_salida();
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd MMMM yyyy", new Locale(actualLocale));
			JLabel lblFechaFinal = new JLabel(txts.getString("lb.fecharegreso")+formatoFecha.format(fechaFinal));
			lblFechaFinal.setFont(new Font("Century Gothic", Font.BOLD, 13));
			lblFechaFinal.setBounds(34, 216, 413, 32);
			contentPanel.add(lblFechaFinal);
		}
		{
			JLabel lblAdultos = new JLabel(txts.getString("lb.numadultos")+bhooking.showPedido().getNumAdultos());
			lblAdultos.setFont(new Font("Century Gothic", Font.BOLD, 13));
			lblAdultos.setBounds(34, 245, 413, 32);
			contentPanel.add(lblAdultos);
		}
		{
			JLabel lblHabitaciones = new JLabel(txts.getString("lb.numhabs")+bhooking.showPedido().getNumHabitaciones());
			lblHabitaciones.setFont(new Font("Century Gothic", Font.BOLD, 13));
			lblHabitaciones.setBounds(34, 275, 413, 32);
			contentPanel.add(lblHabitaciones);
		}

		JButton btnModificar = new JButton(txts.getString("bt.configurar"));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});

		JLabel lblImagenPasarela = new JLabel("");
		lblImagenPasarela.setIcon(new ImageIcon(VentanaPasarela.class.getResource("/img/M\u00E9todos de pago.png")));
		lblImagenPasarela.setBounds(34, 496, 221, 56);
		contentPanel.add(lblImagenPasarela);

		contentPanel.add(getBtnPagar());
		contentPanel.add(getBtnModificar());
		contentPanel.add(getBtnCerrar());
		contentPanel.add(getLblDescuento());
		contentPanel.add(getLblAmount());
		contentPanel.add(getLblTotalAPagar());

		inicializar();
	}

	private void ifDescuento() {
		if(bhooking.hasDiscount()) {
			getLblDescuento().setText(txts.getString("lb.descuento")+bhooking.showPedido().getDiscount().toString()+" "+
					txts.getString("lbl.ahorros")+
					Pedido.conversion(actualLocale, (bhooking.showPedido().getAmountWithoutDiscount()-bhooking.showPedido().getAmount())));
			getLblDescuento().setForeground(Color.RED);
			getLblAmount().setForeground(Color.RED);
			getLblTotalAPagar().setForeground(Color.RED);
		}
	}

	private JLabel getLblDescuento() {
		if (lblDescuento == null) {
			lblDescuento = new JLabel("");
			lblDescuento.setFont(new Font("Century Gothic", Font.BOLD, 13));
			lblDescuento.setBounds(34, 349, 342, 32);
		}
		return lblDescuento;
	}

	private JLabel getLblAmount() {
		if(lblAmount==null) {
			lblAmount = new JLabel(Pedido.conversion(actualLocale,(int)bhooking.getAmount()));
			lblAmount.setHorizontalAlignment(SwingConstants.RIGHT);
			lblAmount.setFont(new Font("Century Gothic", Font.BOLD, 25));
			lblAmount.setBounds(389, 452, 130, 32);
		}
		return lblAmount;
	}

	private JLabel getLblTotalAPagar() {
		if(lblTotalAPagar==null) {
			lblTotalAPagar = new JLabel(txts.getString("lb.totalPagar"));
			lblTotalAPagar.setHorizontalAlignment(SwingConstants.LEFT);
			lblTotalAPagar.setFont(new Font("Century Gothic", Font.BOLD, 20));
			lblTotalAPagar.setBounds(34, 452, 274, 32);
		}
		return lblTotalAPagar;
	}

	private void inicializar() {
		getIconoOk().setEnabled(false);
		getIconoOk().setVisible(false);
		btnCerrar.setEnabled(false);
		btnCerrar.setVisible(false);
		ifDescuento();
	}

	private void close() {
		this.dispose();
	}

	private JButton getBtnPagar() {
		if (btnPagar == null) {
			btnPagar = new JButton(txts.getString("bt.pagar"));
			btnPagar.setMnemonic('P');
			btnPagar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pagar();
					new Descuento();
					Descuento.removeDiscounts(bhooking.showPedido().getUsuario().getDNI());
					vp.restablecer();
					vp.showInicialWindow();
				}
			});
			btnPagar.setFont(new Font("Century Gothic", Font.BOLD, 20));
			btnPagar.setBounds(405, 588, 114, 35);
		}
		return btnPagar;
	}

	private JButton getBtnModificar() {
		if (btnModificar == null) {
			btnModificar = new JButton(txts.getString("bt.configurar"));
			btnModificar.setMnemonic('V');
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					close();
				}
			});
			btnModificar.setFont(new Font("Century Gothic", Font.BOLD, 10));
			btnModificar.setBounds(34, 317, 208, 21);
			btnModificar.setBackground(new Color(255, 186, 0));
		}
		return  btnModificar;
	}

	private void pagar() {
		showPagoConfirmado();
		bhooking.finalizarPedido();
		getBtnPagar().setVisible(false);
		getBtnPagar().setEnabled(false);
		getBtnModificar().setVisible(false);
		getBtnModificar().setEnabled(false);
		getBtnCerrar().setVisible(true);
		getBtnCerrar().setEnabled(true);
	}

	private void showPagoConfirmado() {
		getIconoOk().setEnabled(true);
		getIconoOk().setVisible(true);

	}
	private JButton getBtnCerrar() {
		if (btnCerrar == null) {
			btnCerrar = new JButton(txts.getString("bt.cerrar"));
			btnCerrar.setMnemonic('C');
			btnCerrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					close();
				}
			});
			btnCerrar.setFont(new Font("Century Gothic", Font.BOLD, 15));
			btnCerrar.setBounds(30, 588, 114, 35);
		}
		return btnCerrar;
	}
	private JLabel getIconoOk() {
		if (iconoOk == null) {
			iconoOk = new JLabel("");
			iconoOk.setIcon(new ImageIcon(VentanaPasarela.class.getResource("/img/okPago.png")));
			iconoOk.setBounds(175, 275, 200, 200);
		}
		return iconoOk;
	}
}
