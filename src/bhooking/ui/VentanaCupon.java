package bhooking.ui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bhooking.model.Descuento;
import bhooking.model.Descuento.CodigosDescuento;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

public class VentanaCupon extends JDialog {


	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private VentanaPrincipal vp;

	private final JPanel contentPanel = new JPanel();
	private CodigosDescuento cd;
	private JLabel lblMessage;
	private JLabel imgPremio;
	private JTextField txtDNI1;
	private JLabel lblIntroduzcaSuDni;
	private JTextField txtDNI2;
	private JLabel lblIntroduzcaSuDni2;
	private JLabel imgMochila;
	private JButton btGuardar;
	private JButton btCerrar;

	private ResourceBundle txts;

	/**
	 * Create the dialog.
	 */
	public VentanaCupon(VentanaPrincipal vp,CodigosDescuento cd) {
		setTitle("Bhoo!king: Recompensas");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaCupon.class.getResource("/img/Logotipo Bhoo_king_mini.png")));
		this.cd=cd;
		this.txts=vp.getResourceBundle();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int anw=JOptionPane.showConfirmDialog(null, txts.getString("msg.salir"), txts.getString("msg.salirconf"), JOptionPane.YES_NO_OPTION);
				if(anw==JOptionPane.YES_OPTION) {
					close();
				}
			}
		});

		setBounds(100, 100, 906, 619);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getBtGuardar());
		contentPanel.add(getLblMessage());
		contentPanel.add(getImgPremio());
		contentPanel.add(getTxtDNI1());
		contentPanel.add(getLblIntroduzcaSuDni());
		contentPanel.add(getTxtDNI2());
		contentPanel.add(getLblIntroduzcaSuDni2());
		contentPanel.add(getImgMochila());
		contentPanel.add(getBtCerrar());

		tratarCupon();
	}

	private void tratarCupon() {
		if(cd!=null) {
			if(cd.equals(CodigosDescuento.EXTRA25)) {
				getLblMessage().setText(txts.getString("msg.premio25"));
				getImgPremio().setIcon(new ImageIcon(VentanaCupon.class.getResource("/img/Img_Cupon_25.png")));
				showToSave();
				hideClose();
			}else {
				getLblMessage().setText(txts.getString("msg.premio10"));
				getImgPremio().setIcon(new ImageIcon(VentanaCupon.class.getResource("/img/Img_Cupon_10.png")));
				showToSave();
				hideClose();
			}

		}else {
			getLblMessage().setText(txts.getString("msg.nopremio"));
			getImgPremio().setIcon(new ImageIcon(VentanaCupon.class.getResource("/img/Regalo_nada.png")));
			hideToSave();
			showClose();
		}
	}

	private JLabel getLblMessage() {
		if (lblMessage == null) {
			lblMessage = new JLabel(txts.getString("lb.message"));
			lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
			lblMessage.setFont(new Font("Century Gothic", Font.BOLD, 20));
			lblMessage.setBounds(22, 83, 848, 32);
		}
		return lblMessage;
	}
	private JLabel getImgPremio() {
		if (imgPremio == null) {
			imgPremio = new JLabel("");
			imgPremio.setHorizontalAlignment(SwingConstants.CENTER);
			imgPremio.setIcon(new ImageIcon(VentanaCupon.class.getResource("/img/Regalo_nada.png")));
			imgPremio.setBounds(225, 118, 413, 273);
		}
		return imgPremio;
	}
	private JTextField getTxtDNI1() {
		if (txtDNI1 == null) {
			txtDNI1 = new JTextField();
			txtDNI1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			txtDNI1.setBounds(354, 394, 204, 25);
			txtDNI1.setColumns(10);
		}
		return txtDNI1;
	}
	private JLabel getLblIntroduzcaSuDni() {
		if (lblIntroduzcaSuDni == null) {
			lblIntroduzcaSuDni = new JLabel(txts.getString("lb.introduzcaDni"));
			lblIntroduzcaSuDni.setHorizontalAlignment(SwingConstants.CENTER);
			lblIntroduzcaSuDni.setFont(new Font("Century Gothic", Font.BOLD, 13));
			lblIntroduzcaSuDni.setBounds(142, 387, 202, 32);
		}
		return lblIntroduzcaSuDni;
	}
	private JTextField getTxtDNI2() {
		if (txtDNI2 == null) {
			txtDNI2 = new JTextField();
			txtDNI2.setFont(new Font("Tahoma", Font.PLAIN, 12));
			txtDNI2.setColumns(10);
			txtDNI2.setBounds(354, 432, 204, 25);
		}
		return txtDNI2;
	}
	private JLabel getLblIntroduzcaSuDni2() {
		if (lblIntroduzcaSuDni2 == null) {
			lblIntroduzcaSuDni2 = new JLabel(txts.getString("lb.reintroduzcaDni"));
			lblIntroduzcaSuDni2.setHorizontalAlignment(SwingConstants.CENTER);
			lblIntroduzcaSuDni2.setFont(new Font("Century Gothic", Font.BOLD, 13));
			lblIntroduzcaSuDni2.setBounds(142, 425, 202, 32);
		}
		return lblIntroduzcaSuDni2;
	}
	private JLabel getImgMochila() {
		if (imgMochila == null) {
			imgMochila = new JLabel("");
			imgMochila.setIcon(new ImageIcon(VentanaCupon.class.getResource("/img/Img_mochila.png")));
			imgMochila.setBounds(765, 438, 105, 122);
		}
		return imgMochila;
	}

	private void grabDNI() {
		new Descuento();
		Descuento.grabDiscounts(getTxtDNI1().getText(), cd);
	}

	private JButton getBtCerrar() {
		if (btCerrar == null) {
			btCerrar = new JButton(txts.getString("bt.cerrar"));
			btCerrar.setMnemonic('C');
			btCerrar.setFont(new Font("Century Gothic", Font.BOLD, 13));
			btCerrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					close();
				}
			});
			btCerrar.setBounds(10, 528, 132, 32);
		}
		return btCerrar;
	}
	
	private JButton getBtGuardar() {
	    if (btGuardar == null) {
	        btGuardar = new JButton(txts.getString("bt.guardar"));
	        btGuardar.setMnemonic('G');
	        btGuardar.setFont(new Font("Century Gothic", Font.BOLD, 13));
	        btGuardar.setBounds(680, 528, 132, 32);
	        btGuardar.setBackground(new Color(255, 186, 0));
	        btGuardar.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                if (checkDNI()) {
	                    grabDNI();
	                    hideToSave();
	                    showClose();
	                }
	            }
	        });
	        return btGuardar;
	    }
	    return btGuardar;
	}


	private void close() {
		this.dispose();
	}


	private boolean checkDNI() {
		if(getTxtDNI2().getText().equals(getTxtDNI1().getText())) {
			if(getTxtDNI1().getText().length()<9 || getTxtDNI1().getText().length()>10) {
				JOptionPane.showMessageDialog(null,txts.getString("msg.dniinvalido")); 
				return false;
			}else {
				boolean hasDiscount=(new Descuento().hasDiscount(getTxtDNI1().getText()));
				if(hasDiscount) {
					JOptionPane.showMessageDialog(null,txts.getString("msg.yatiene"));
					hideToSave();
					showClose();
				}else {
					return true;
				}
			}
		}else {
			JOptionPane.showMessageDialog(null,txts.getString("msg.dniincorr"));
		}
		return false;
	}

	private void showToSave() {
		getTxtDNI1().setEnabled(true);
		getTxtDNI1().setVisible(true);
		getTxtDNI2().setEnabled(true);
		getTxtDNI2().setVisible(true);
		getLblIntroduzcaSuDni().setEnabled(true);
		getLblIntroduzcaSuDni().setVisible(true);
		getLblIntroduzcaSuDni2().setEnabled(true);
		getLblIntroduzcaSuDni2().setVisible(true);
		getBtGuardar().setEnabled(true);
		getBtGuardar().setVisible(true);
	}

	private void hideToSave() {
		getTxtDNI1().setEnabled(false);
		getTxtDNI1().setVisible(false);
		getTxtDNI2().setEnabled(false);
		getTxtDNI2().setVisible(false);
		getLblIntroduzcaSuDni().setEnabled(false);
		getLblIntroduzcaSuDni().setVisible(false);
		getLblIntroduzcaSuDni2().setEnabled(false);
		getLblIntroduzcaSuDni2().setVisible(false);
		getBtGuardar().setEnabled(false);
		getBtGuardar().setVisible(false);
	}

	private void showClose() {
		getBtCerrar().setEnabled(true);
		getBtCerrar().setVisible(true);
	}

	private void hideClose() {
		getBtCerrar().setEnabled(false);
		getBtCerrar().setVisible(false);
	}

}
