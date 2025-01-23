package bhooking;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

import bhooking.ui.VentanaPrincipal;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
		    UIManager.setLookAndFeel( new FlatLightLaf() );
		} catch( Exception ex ) {
		    System.err.println( "Failed to initialize LaF" );
		}
		
		// create UI here...
		VentanaPrincipal vp = new VentanaPrincipal();
		vp.setVisible(true);
	}

}
