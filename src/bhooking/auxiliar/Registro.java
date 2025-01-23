package bhooking.auxiliar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Registro {
	ESC000, ESC001, ESC002, ESC003, ESC004, ESC005, ESC006, ESC007,
	ESC008, ESC009;
	
	public static List<Registro> getPosibilities() {
		 return new ArrayList<>(Arrays.asList(values()));
	}

}
