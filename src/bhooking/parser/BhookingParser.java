package bhooking.parser;

import java.util.LinkedList;
import java.util.List;

import bhooking.auxiliar.Registro;
import bhooking.exceptions.ParseException;
import bhooking.model.Castle;
import bhooking.model.Encantamiento;
import bhooking.util.ArgumentChecks;

public class BhookingParser {
	/**
	 * 
	 * @param lines
	 * @return
	 * @throws IllegalArgumentException if lines is null
	 */
	public List<Castle> parse(List<String> lines) {
		ArgumentChecks.isTrue(lines != null, "Illegal null list");
		List<Castle> castillos = new LinkedList<>();
		for(String line: lines) {
			castillos.add(parseLine(line));
		}
		return castillos;
	}

	private Castle parseLine(String line) {
		String parts[] = line.split(";");
		try {
	    Registro codigo= parserCodigo(parts[0]);
	    String denominacion=parserText(parts[1]);
	    String descripcion=parserText(parts[2]);
	    String pais=parserText(parts[3]);
	    float precio=parserPrice(parts[4]);
	    Encantamiento encantamiento = parserEncantamiento(parts[5]);
	    
	    return new Castle(codigo,denominacion, descripcion, pais, precio, encantamiento);
		}catch(Exception e) {
			return null;
		}
	}
	
	private Registro parserCodigo(String codigo) throws ParseException {
		List<Registro> registro = Registro.getPosibilities();
		for(int i=0; i<registro.size(); i++) {
			if(registro.get(i).toString().equals(codigo)) {
				return registro.get(i);
			}
		}
		throw new ParseException("Especific code invalid");
	}
	
	private String parserText(String txt) throws ParseException {
		if(txt!="" && txt!=null) {return txt;}
		throw new ParseException("Please, check: "+txt); 
	}
	
	private float parserPrice(String price) throws ParseException {
		float p= Float.parseFloat(price);
		if(p>=0) {return p;}
		throw new ParseException("Price incorrect"); 
	}
	
	private Encantamiento parserEncantamiento(String txt) throws ParseException {
		if(txt!="" && txt!=null) {return new Encantamiento(txt);}
		throw new ParseException("Magic incantation incorrect"); 
	}

}
