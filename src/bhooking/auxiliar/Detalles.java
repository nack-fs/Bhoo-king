package bhooking.auxiliar;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import bhooking.util.FileUtil;

public class Detalles {
	
		private List<String> direcciones;
		private List<String> puntuation;
		
		private final static String root="data/details.dat";
		

		
		public Detalles() {
			this.direcciones=new ArrayList<String>();
			this.puntuation=new ArrayList<String>();
			loadDetails();
		}
		
		private void loadDetails() {
			try {
				List<String> lines= new FileUtil().readLines(root);
				for(String line:lines) {
					parseLine(line);
				}
			} catch (FileNotFoundException e) {}
		}
		
		
		public String getDirecciones(int i) {
			return direcciones.get(i);
		}
		
		public String getPuntuaciones(int i) {
			return puntuation.get(i);
		}
	/**
	 * 
	 * @param lines
	 * @return
	 * @throws IllegalArgumentException if lines is null
	 */
	private void parseLine(String line) {
		String parts[] = line.split(";");
		direcciones.add(parts[1]);
		puntuation.add(parts[2]);
	}

}
