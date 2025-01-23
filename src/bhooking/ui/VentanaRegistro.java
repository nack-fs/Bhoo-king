package bhooking.ui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bhooking.model.User;
import bhooking.service.Bhooking;

import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Toolkit;
import javax.swing.JRadioButton;

public class VentanaRegistro extends JDialog {

	private static final long serialVersionUID = 1L;
	private VentanaPrincipal vp;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JLabel lblNombre;
	private JLabel lblApellidos;
	private JTextField txtApellidos;
	private JLabel lblDni;
	private JTextField txtDNI;
	private JLabel lblEmail;
	private JTextField txtEmail;
	
	private Bhooking bhooking=new Bhooking();
	private JLabel lblComentarios;
	private JScrollPane scrollPane;
	private JTextArea txComentario;
	
    private ResourceBundle txts;
    private JRadioButton rdbtnAdulto;
	
	/**
	 * Create the dialog.
	 */
	public VentanaRegistro(Bhooking bhooking,VentanaPrincipal vp) {
		setTitle("Bhoo!king: Registro");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaRegistro.class.getResource("/img/Logotipo Bhoo_king_mini.png")));
		this.vp=vp;
		this.bhooking=bhooking;
		this.txts=vp.getResourceBundle();
		
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 575, 471);
		getContentPane().setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 391, 561, 43);
			buttonPane.setBackground(new Color(255, 186, 0));
			getContentPane().add(buttonPane);
			buttonPane.setLayout(new GridLayout(0, 2, 300, 0));
			{
				JButton btSalir = new JButton(txts.getString("bt.salir"));
				btSalir.setMnemonic('S');
				btSalir.setFont(new Font("Century Gothic", Font.BOLD, 12));
				btSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						close();
					}
				});
				btSalir.setActionCommand("OK");
				btSalir.setBackground(new Color(255, 136, 125));
				buttonPane.add(btSalir);
				getRootPane().setDefaultButton(btSalir);
			}
			{
				JButton btConfirmar = new JButton(txts.getString("bt.confirmar"));
				btConfirmar.setMnemonic('C');
				btConfirmar.setFont(new Font("Century Gothic", Font.BOLD, 12));
				btConfirmar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						register();
					}
				});
				btConfirmar.setActionCommand("Cancel");
				btConfirmar.setBackground(new Color(24, 201, 111));
				buttonPane.add(btConfirmar);
			}
		}
		contentPanel.setBounds(0, 0, 561, 393);
		contentPanel.setBackground(new Color(255, 186, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JLabel lblIcono = new JLabel("");
			lblIcono.setIcon(new ImageIcon(VentanaRegistro.class.getResource("/img/Logotipo Bhoo_king_mini.png")));
			lblIcono.setBounds(10, 11, 71, 71);
			contentPanel.add(lblIcono);
		}
		contentPanel.add(getTxtNombre());
		contentPanel.add(getLblNombre());
		contentPanel.add(getLblApellidos());
		contentPanel.add(getTxtApellidos());
		contentPanel.add(getLblDni());
		contentPanel.add(getTxtDNI());
		contentPanel.add(getLblEmail());
		contentPanel.add(getTxtEmail());
		contentPanel.add(getLblComentarios());
		contentPanel.add(getScrollPane());
		contentPanel.add(getRdbtnAdulto());
	}
	
	private void register() {
		if(check()) {
			String name= getTxtNombre().getText();
			String surname= getTxtApellidos().getText();
			String email= getTxtEmail().getText();
			String dni= getTxtDNI().getText();
			boolean adult = getRdbtnAdulto().isSelected();
			bhooking.addUserTobook(new User(name,surname,email,dni,adult));
			bhooking.setComentario(getTxComentario().getText());
			bhooking.register();
			bhooking.calculateAmount();
			close();
			showPasarelaWindow();
		}
	}
	
	private void showPasarelaWindow() {
		VentanaPasarela vr = new VentanaPasarela(bhooking,vp);
		vr.setLocationRelativeTo(this);
		vr.setVisible(true);
	}
	
	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setBounds(163, 100, 178, 20);
			txtNombre.setColumns(10);
		}
		return txtNombre;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel(txts.getString("lb.nombre"));
			lblNombre.setDisplayedMnemonic('N');
			lblNombre.setLabelFor(getTxtNombre());
			lblNombre.setFont(new Font("Century Gothic", Font.BOLD, 15));
			lblNombre.setBounds(30, 101, 123, 19);
		}
		return lblNombre;
	}
	private JLabel getLblApellidos() {
		if (lblApellidos == null) {
			lblApellidos = new JLabel(txts.getString("lb.apellidos"));
			lblApellidos.setLabelFor(getTxtApellidos());
			lblApellidos.setDisplayedMnemonic('A');
			lblApellidos.setFont(new Font("Century Gothic", Font.BOLD, 15));
			lblApellidos.setBounds(30, 130, 123, 19);
		}
		return lblApellidos;
	}
	private JTextField getTxtApellidos() {
		if (txtApellidos == null) {
			txtApellidos = new JTextField();
			txtApellidos.setColumns(10);
			txtApellidos.setBounds(163, 129, 312, 20);
		}
		return txtApellidos;
	}
	private JLabel getLblDni() {
		if (lblDni == null) {
			lblDni = new JLabel(txts.getString("lb.dni"));
			lblDni.setDisplayedMnemonic('D');
			lblDni.setLabelFor(getTxtDNI());
			lblDni.setFont(new Font("Century Gothic", Font.BOLD, 15));
			lblDni.setBounds(30, 161, 123, 19);
		}
		return lblDni;
	}
	private JTextField getTxtDNI() {
		if (txtDNI == null) {
			txtDNI = new JTextField();
			txtDNI.setColumns(10);
			txtDNI.setBounds(163, 160, 178, 20);
		}
		return txtDNI;
	}
	private JLabel getLblEmail() {
		if (lblEmail == null) {
			lblEmail = new JLabel(txts.getString("lb.email"));
			lblEmail.setLabelFor(getTxtEmail());
			lblEmail.setDisplayedMnemonic('E');
			lblEmail.setFont(new Font("Century Gothic", Font.BOLD, 15));
			lblEmail.setBounds(30, 192, 123, 19);
		}
		return lblEmail;
	}
	private JTextField getTxtEmail() {
		if (txtEmail == null) {
			txtEmail = new JTextField();
			txtEmail.setColumns(10);
			txtEmail.setBounds(163, 191, 312, 20);
		}
		return txtEmail;
	}
	
	private boolean check() {
		if(getTxtNombre().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null,txts.getString("msg.nombrevacio")); 
			return false;
		}if(getTxtApellidos().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null,txts.getString("msg.apellidosvacio")); 
			return false;
		}if(getTxtDNI().getText().length()<9 || getTxtDNI().getText().length()>10) {
			JOptionPane.showMessageDialog(null,txts.getString("msg.dniinvalido")); 
			return false;
		}if(!getTxtEmail().getText().contains("@")) {
			JOptionPane.showMessageDialog(null,txts.getString("msg.emailinvalido")); 
			return false;
		}if(!getRdbtnAdulto().isSelected()) {
			JOptionPane.showMessageDialog(null,txts.getString("msg.adulto")); 
			return false;
		}
		return true;
	}
	
	private void close() {
		this.dispose();
	}
	
	private JLabel getLblComentarios() {
		if (lblComentarios == null) {
			lblComentarios = new JLabel(txts.getString("lb.comentarios"));
			lblComentarios.setDisplayedMnemonic('o');
			lblComentarios.setLabelFor(getTxComentario());
			lblComentarios.setFont(new Font("Century Gothic", Font.BOLD, 15));
			lblComentarios.setBounds(30, 221, 123, 19);
		}
		return lblComentarios;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(162, 221, 312, 87);
			scrollPane.setViewportView(getTxComentario());
		}
		return scrollPane;
	}
	private JTextArea getTxComentario() {
		if (txComentario == null) {
			txComentario = new JTextArea();
		}
		return txComentario;
	}
	private JRadioButton getRdbtnAdulto() {
		if (rdbtnAdulto == null) {
			rdbtnAdulto = new JRadioButton(txts.getString("lb.adulto"));
			rdbtnAdulto.setBackground(new Color(255, 186, 0));
			rdbtnAdulto.setFont(new Font("Century Gothic", Font.BOLD, 11));
			rdbtnAdulto.setBounds(172, 332, 301, 21);
		}
		return rdbtnAdulto;
	}
}
