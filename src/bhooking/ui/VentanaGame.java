package bhooking.ui;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bhooking.game.Board;
import bhooking.game.Casilla;
import bhooking.game.Game;
import bhooking.model.Descuento.CodigosDescuento;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaGame extends JFrame {

	private static final long serialVersionUID = 1L;
	private VentanaPrincipal vp;
	private JPanel contentPane;
	private Clip clip;
	private Game game;

	private JPanel pnFila5;
	private JPanel pnFila4;
	private JPanel pnFila3;
	private JPanel pnFila2;
	private JPanel pnFila1;
	private JLabel imgBackground;
	private JLabel btnSalir;
	private JLabel imgDado;
	private JLabel imgBarraProgreso;
	private JButton btTirar;
	private JLabel txtNumAttempts;

	private ProcesaGhostbusters pgb = new ProcesaGhostbusters();
	private ProcesaGhosts pg = new ProcesaGhosts();
	private ProcesaClickDado pcd = new ProcesaClickDado();

	private boolean isMovementActive;

	private Coordinate fuente;
	private JLabel fuenteLabel;
	private Coordinate destino;
	private JLabel destinoLabel;
	
	private ResourceBundle txts;

	public VentanaGame(VentanaPrincipal vp) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				close();
			}
		});
		setTitle("Bhoo!king: Juego ");
		this.game=new Game();
		this.vp=vp;
		this.txts=vp.getResourceBundle();

		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaGame.class.getResource("/img/Logotipo Bhoo_king_mini.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1325, 801);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getTxtNumAttempts());
		contentPane.add(getPnFila5());
		contentPane.add(getPnFila4());
		contentPane.add(getPnFila3());
		contentPane.add(getPnFila2());
		contentPane.add(getPnFila1());
		contentPane.add(getBtnSalir());
		contentPane.add(getImgDado());
		contentPane.add(getImgBarraProgreso());
		contentPane.add(getBtTirar());
		contentPane.add(getImgBackground());

		inicializar();
	}

	private void inicializar() {
		reproducirSoundTrack();
		inicalizarTablero();
	}

	public void reproducirSoundTrack() {
		try {
			File archivoCancion = new File("others/soundtrack.wav");
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(archivoCancion);
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);

			clip.loop(Clip.LOOP_CONTINUOUSLY);

		} catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
			e.printStackTrace();
		}
	}

	private void showPrincipal() {
		vp.inactiveGame();
		clip.close();
		this.dispose();
	}

	private JPanel getPnFila5() {
		if (pnFila5 == null) {
			pnFila5 = new JPanel();
			pnFila5.setOpaque(false);
			pnFila5.setBounds(313, 568, 961, 129);
		}
		return pnFila5;
	}
	private JPanel getPnFila4() {
		if (pnFila4 == null) {
			pnFila4 = new JPanel();
			pnFila4.setOpaque(false);
			pnFila4.setBounds(313, 439, 961, 129);
		}
		return pnFila4;
	}
	private JPanel getPnFila3() {
		if (pnFila3 == null) {
			pnFila3 = new JPanel();
			pnFila3.setOpaque(false);
			pnFila3.setBounds(384, 313, 822, 135);
		}
		return pnFila3;
	}
	private JPanel getPnFila2() {
		if (pnFila2 == null) {
			pnFila2 = new JPanel();
			pnFila2.setOpaque(false);
			pnFila2.setBounds(384, 184, 822, 129);
		}
		return pnFila2;
	}
	private JPanel getPnFila1() {
		if (pnFila1 == null) {
			pnFila1 = new JPanel();
			pnFila1.setBounds(384, 58, 822, 129);
			pnFila1.setOpaque(false);
		}
		return pnFila1;
	}
	private JLabel getImgBackground() {
		if (imgBackground == null) {
			imgBackground = new JLabel("");
			imgBackground.setIcon(new ImageIcon(VentanaGame.class.getResource("/img/BackgroundGame.png")));
			imgBackground.setBounds(0, 0, 1330, 820);
		}
		return imgBackground;
	}
	private JLabel getBtnSalir() {
		if (btnSalir == null) {
			btnSalir = new JLabel("");
			btnSalir.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					close();
				}
			});
			btnSalir.setIcon(new ImageIcon(VentanaGame.class.getResource("/img/lateral, atr\u00E1s.png")));
			btnSalir.setBounds(-22, 383, 80, 229);
		}
		return btnSalir;
	}
	
	private void close() {
		int anw=JOptionPane.showConfirmDialog(null, txts.getString("msg.salirjuego"), txts.getString("msg.salirconf"), JOptionPane.YES_NO_OPTION);
		if(anw==JOptionPane.YES_OPTION) {
			showPrincipal();
		}
	}
	
	private JLabel getImgDado() {
		if (imgDado == null) {
			imgDado = new JLabel("");
			imgDado.addMouseListener(pcd);
			imgDado.setHorizontalAlignment(SwingConstants.CENTER);
			imgDado.setIcon(new ImageIcon(VentanaGame.class.getResource("/img/Icono_lanzarDado.png")));
			imgDado.setBounds(89, 538, 179, 190);
		}
		return imgDado;
	}
	
	class ProcesaClickDado extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			tirarDado();
		}
	}
	
	private JLabel getImgBarraProgreso() {
		if (imgBarraProgreso == null) {
			imgBarraProgreso = new JLabel("");
			imgBarraProgreso.setIcon(new ImageIcon(VentanaGame.class.getResource("/img/Barra_dado_left.png")));
			imgBarraProgreso.setBounds(25, 14, 193, 51);
		}
		return imgBarraProgreso;
	}
	private JButton getBtTirar() {
		if (btTirar == null) {
			btTirar = new JButton(txts.getString("bt.tirar"));
			btTirar.setMnemonic('T');
			btTirar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tirarDado();
				}
			});
			btTirar.setFont(new Font("Century Gothic", Font.BOLD, 20));
			btTirar.setBounds(94, 476, 179, 41);
		}
		return btTirar;
	}

	private void tirarDado() {
		game.throwDice();
		showActualMovement();
		getTxtNumAttempts().setText(String.valueOf(game.getLeftAttempts()));
		this.isMovementActive = true;
		getBtTirar().setEnabled(false);
		getBtTirar().setVisible(false);
	}

	private void showActualMovement() {
		getImgDado().removeMouseListener(pcd);
		getImgDado().setIcon(new ImageIcon(VentanaGame.class.getResource("/img/Dado_"+game.showActualMovement()+".png")));
	}

	private JLabel getTxtNumAttempts() {
		if (txtNumAttempts == null) {
			txtNumAttempts = new JLabel(""+game.getLeftAttempts());
			txtNumAttempts.setFont(new Font("Century Gothic", Font.BOLD, 25));
			txtNumAttempts.setBounds(94, 23, 38, 32);
		}
		return txtNumAttempts;
	}

	private void inicalizarTablero() {
		crearTablero();
		updateCasillas();
	}

	private void crearTablero() {
		int count=0;
		for (int i=0;i<Board.ROW_SIZE; i++) {
			for(int j=0;j<Board.COL_SIZE;j++) {
				if(game.getCopyOfBoard()[i][j]!=null) {
					Coordinate c = new Coordinate(getMargenes()[count]);
					distribuidorFilas(c.getX(), c.getY());
					count++;
				}
			}
		}
	}

	private void distribuidorFilas(int row,int col) {
		if(row==0) {
			getPnFila1().add(crearEtiqueta(row,col));
		}if(row==1) {
			getPnFila2().add(crearEtiqueta(row,col));
		}if(row==2) {
			getPnFila3().add(crearEtiqueta(row,col));
		}if(row==3) {
			getPnFila4().add(crearEtiqueta(row,col));
		}if (row==4) {getPnFila5().add(crearEtiqueta(row,col)); 
		}validate();
	}


	private JLabel crearEtiqueta(int row, int col) {
		JLabel label = new JLabel("");
		label.setName("(" + row + "," + col + ")");
		int type = game.getType(row, col);
		if (type!=Casilla.null_area) {
			label.setIcon(new ImageIcon(VentanaGame.class.getResource("/img/Casilla_"+type+".png")));
			if(type==Casilla.Ghostbusters) {
				label.addMouseListener(pgb);
			}else {
				label.addMouseListener(pg);
			}
		} else {
			label.setIcon(new ImageIcon(VentanaGame.class.getResource("/img/Casilla_null.png")));
		}
		return label;
	}

	private String[] getMargenes() {
		return new String[] {  "(0,3)", "(1,2)","(1,3)","(1,4)","(2,1)", "(2,2)", "(2,3)", "(2,4)", "(2,5)", 
				"(3,0)", "(3,1)", "(3,2)", "(3,3)", "(3,4)", "(3,5)", "(3,6)","(4,0)", "(4,1)", "(4,2)",
				"(4,3)", "(4,4)", "(4,5)", "(4,6)"};
	}

	private void updateCasillas() {
		String[] margenes =getMargenes();
		for(int i=0;i<margenes.length; i++) {
			Coordinate c = new Coordinate(getMargenes()[i]);
			updateDistrubuidor(c.getX(),c.getY());
		}
	}

	private void updateDistrubuidor(int row, int col) {
		if(row==0) {
			updateFila1(col); 
		}if(row==1) {
			updateFila2(col);
		}if(row==2) {
			updateFila3(col);
		}if(row==3) {
			updateFila4(col);
		}if (row==4) {updateFila5(col);
		}
		validate();
	}

	private void updateFila1(int col) {
		int row=0;
		for(int i=0;i<pnFila1.getComponentCount(); i++) {
			if(((JLabel)(pnFila1.getComponent(i))).getName().equals("("+row+","+col+")")) {
				if(game.getCopyOfBoard()[row][col]!=null) {
					int type=game.getType(row, col);
					updateAction((JLabel)(pnFila1.getComponent(i)), type);
					((JLabel)(pnFila1.getComponent(i))).setIcon(new ImageIcon(VentanaGame.class.getResource("/img/Casilla_"+type+".png")));
				}else {
					((JLabel)(pnFila1.getComponent(i))).setIcon(new ImageIcon(VentanaGame.class.getResource("/img/Casilla_null.png")));
					updateAction((JLabel)(pnFila1.getComponent(i)), Casilla.null_area);
				}
			}
		}
		validate();
	}

	private void updateAction(JLabel label,int type) {
		label.removeMouseListener(pg);
		label.removeMouseListener(pgb);
		if(type==Casilla.Ghostbusters) {
			label.addMouseListener(pgb);
		}else if(type!=Casilla.null_area) {
			label.addMouseListener(pg);
		}
	}

	private void updateFila2(int col) {
		int row=1;
		for(int i=0;i<pnFila2.getComponentCount(); i++) {
			if(((JLabel)(pnFila2.getComponent(i))).getName().equals("("+row+","+col+")")) {
				if(game.getCopyOfBoard()[row][col]!=null) {
					int type=game.getType(row, col);
					updateAction((JLabel)(pnFila2.getComponent(i)), type);
					((JLabel)(pnFila2.getComponent(i))).setIcon(new ImageIcon(VentanaGame.class.getResource("/img/Casilla_"+type+".png")));
				}else {
					((JLabel)(pnFila2.getComponent(i))).setIcon(new ImageIcon(VentanaGame.class.getResource("/img/Casilla_null.png")));
					updateAction((JLabel)(pnFila2.getComponent(i)), Casilla.null_area);
				}
			}
		}
		validate();
	}

	private void updateFila3(int col) {
		int row=2;
		for(int i=0;i<pnFila3.getComponentCount(); i++) {
			if(((JLabel)(pnFila3.getComponent(i))).getName().equals("("+row+","+col+")")) {
				if(game.getCopyOfBoard()[row][col]!=null) {
					int type=game.getType(row, col);
					updateAction((JLabel)(pnFila3.getComponent(i)), type);
					((JLabel)(pnFila3.getComponent(i))).setIcon(new ImageIcon(VentanaGame.class.getResource("/img/Casilla_"+type+".png")));
				}else {
					((JLabel)(pnFila3.getComponent(i))).setIcon(new ImageIcon(VentanaGame.class.getResource("/img/Casilla_null.png")));
					updateAction((JLabel)(pnFila3.getComponent(i)), Casilla.null_area);
				}
			}
		}
		validate();
	}

	private void updateFila4(int col) {
		int row=3;
		for(int i=0;i<pnFila4.getComponentCount(); i++) {
			if(((JLabel)(pnFila4.getComponent(i))).getName().equals("("+row+","+col+")")) {
				if(game.getCopyOfBoard()[row][col]!=null) {
					int type=game.getType(row, col);
					updateAction((JLabel)(pnFila4.getComponent(i)), type);
					((JLabel)(pnFila4.getComponent(i))).setIcon(new ImageIcon(VentanaGame.class.getResource("/img/Casilla_"+type+".png")));
				}else {
					((JLabel)(pnFila4.getComponent(i))).setIcon(new ImageIcon(VentanaGame.class.getResource("/img/Casilla_null.png")));
					updateAction((JLabel)(pnFila4.getComponent(i)), Casilla.null_area);
				}
			}
		}
		validate();
	}

	private void updateFila5(int col) {
		int row=4;
		for(int i=0;i<pnFila5.getComponentCount(); i++) {
			if(((JLabel)(pnFila5.getComponent(i))).getName().equals("("+row+","+col+")")) {
				if(game.getCopyOfBoard()[row][col]!=null) {
					int type=game.getType(row, col);
					updateAction((JLabel)(pnFila5.getComponent(i)), type);
					((JLabel)(pnFila5.getComponent(i))).setIcon(new ImageIcon(VentanaGame.class.getResource("/img/Casilla_"+type+".png")));
				}else {
					((JLabel)(pnFila5.getComponent(i))).setIcon(new ImageIcon(VentanaGame.class.getResource("/img/Casilla_null.png")));
					updateAction((JLabel)(pnFila5.getComponent(i)), Casilla.null_area);
				}
			}
		}
		validate();
	}

	class ProcesaGhostbusters extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(isMovementActive) {
				if (fuenteLabel!=null) {
					Coordinate c =  new Coordinate(fuenteLabel.getName());
					if(game.getCopyOfBoard()[c.getX()][c.getY()]==null) {
						fuenteLabel.setIcon(new ImageIcon(VentanaGame.class.getResource("/img/Casilla_null.png")));
					}else {
						fuenteLabel.setIcon(new ImageIcon(VentanaGame.class.getResource("/img/Casilla_0.png")));
					}
				}

				JLabel label = (JLabel)e.getSource();
				fuenteLabel = label;
				selectFuente(label);
				fuenteLabel.setIcon(new ImageIcon(VentanaGame.class.getResource("/img/Casilla_0_active.png")));
			}
		}
	}

	private void selectFuente(JLabel label) {
		this.fuente=new Coordinate(label.getName());
	}

	private void selectDestino(JLabel label) {
		this.destino=new Coordinate(label.getName());
	}

	private void realizarMovimiento() {
		game.moveCasilla(fuente.getX(), fuente.getY(), destino.getX(), destino.getY());
		recalcularCasillas();
		restablecerDado();
		if(game.getLeftAttempts()==0) {
			finishGame();
		}
	}

	private void restablecerDado() {
		isMovementActive=false;
		getBtTirar().setEnabled(true);
		getBtTirar().setVisible(true);
		getImgDado().addMouseListener(pcd);
		getImgDado().setIcon(new ImageIcon(VentanaGame.class.getResource("/img/Icono_lanzarDado.png")));
	}

	private void finishGame() {
		CodigosDescuento cd = game.getResult();
		VentanaCupon vc = new VentanaCupon(vp,cd);
		vc.setVisible(true);
		vc.setLocationRelativeTo(null);
		vp.setGameAsInactive();
		clip.close();
		this.dispose();
	}

	private void recalcularCasillas() {
		updateCasillas();
		showActualAttemps();
	}

	private void showActualAttemps() {
		getTxtNumAttempts().setText(""+game.getLeftAttempts());
	}

	class ProcesaGhosts extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(isMovementActive) {
				if (destinoLabel!=null) {
					Coordinate c = new Coordinate(destinoLabel.getName());
					int type= game.getCopyOfBoard()[c.getX()][c.getY()].getType();
					destinoLabel.setIcon(new ImageIcon(VentanaGame.class.getResource("/img/Casilla_"+type+".png")));
				}

				JLabel label = (JLabel)e.getSource();

				if (isValidSelection(fuente, new Coordinate(label.getName()))) {
					destinoLabel = label;
					selectDestino(label);
					Coordinate c = new Coordinate(label.getName());
					int type= game.getCopyOfBoard()[c.getX()][c.getY()].getType();
					label.setIcon(new ImageIcon(VentanaGame.class.getResource("/img/Casilla_"+type+"_active.png")));
					realizarMovimiento();
				}
			}
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			if (isMovementActive) {
				JLabel label = (JLabel) e.getSource();
				if (isValidSelection(fuente, new Coordinate(label.getName()))) {
					Coordinate c = new Coordinate(label.getName());
					int type= game.getCopyOfBoard()[c.getX()][c.getY()].getType();
					label.setIcon(new ImageIcon(VentanaGame.class.getResource("/img/Casilla_"+type+"_active.png")));
				}
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			if (isMovementActive) {
				JLabel label = (JLabel) e.getSource();
				Coordinate c = new Coordinate(label.getName());
				int type= game.getCopyOfBoard()[c.getX()][c.getY()].getType();
				label.setIcon(new ImageIcon(VentanaGame.class.getResource("/img/Casilla_"+type+".png")));
			}
		}
	}

	private boolean isValidSelection(Coordinate fuente, Coordinate destino) {
		if(fuente!=null && destino!=null) {
			return fuente.getY()==destino.getY() && fuente.getX()-destino.getX()==game.showActualMovement();
		}
		return false;
	}

	protected class Coordinate {
		private int x;
		private int y;

		Coordinate(String coordenada) {
			String s = coordenada.substring(1, coordenada.length() - 1);
			String[] parts = s.split(",");
			x = Integer.parseInt(parts[0]);
			y = Integer.parseInt(parts[1]);
		}

		protected int getX() {
			return x;
		}

		protected int getY() {
			return y;
		}
	}


}
