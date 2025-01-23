package bhooking.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bhooking.util.FileUtil;

public class Descuento {
	private List<String> DNIs;
	private List<CodigosDescuento> codigos;
	
	private final static String root="data/descuentos.dat";
	
	public enum CodigosDescuento{
		EXTRA10,EXTRA25;
	}
	
	
	public Descuento() {
		this.DNIs=new ArrayList<String>();
		this.codigos=new ArrayList<CodigosDescuento>();
		
		loadDiscounts();
	}
	
	private void loadDiscounts() {
		try {
			List<String> lines= new FileUtil().readLines(root);
			for(String line:lines) {
				parseLine(line);
			}
		} catch (FileNotFoundException e) {}
	}
	
	public static void grabDiscounts(String DNI,CodigosDescuento code) {
		try {
			List<String> l = new ArrayList<String>();
			l.add(DNI+";"+code.toString());
			new FileUtil().writeLines(root, l);
		} catch (IOException e) {}
	}
	
	public static void removeDiscounts(String DNI) {
		try {
			new FileUtil().removeLines(root, DNI);
		} catch (IOException e) {}
	}
	
	public boolean hasDiscount(String dni) {
		for(int i=0; i<DNIs.size(); i++) {
			if(DNIs.get(i).equals(dni)) {return true;}
		}
		return false;
	}
	
	public CodigosDescuento getDiscount(String dni) {
		for(int i=0; i<DNIs.size(); i++) {
			if(DNIs.get(i).equals(dni)) {
                return codigos.get(i);
			}
		}
		return null;
	}
	
	public int getDiscountPercentage(String dni) {
		if(getDiscount(dni)!=null) {
			if(getDiscount(dni).equals(CodigosDescuento.EXTRA10)) {
				return 10;
			}else {
				return 25;
			}
		}
		return -1;
	}

	private void parseLine(String line) {
		String parts[] = line.split(";");
		DNIs.add(parts[0]);
		codigos.add(parseDescuento(parts[1])); 
	}
	
	private CodigosDescuento parseDescuento(String part) {
		if(part.equals(CodigosDescuento.EXTRA10.toString())) {
			return CodigosDescuento.EXTRA10;
		}
		return CodigosDescuento.EXTRA25;
	}

}
