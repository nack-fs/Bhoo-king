package bhooking.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Encantamiento {
	private List<Types> encantamientos;
	public final static int numOfEncantamientos=6;

	public enum Types {
		  Ap,De,En,Ob,Ol,Ru;
		
		public static List<Types> getPosibilities() {
			 return new ArrayList<>(Arrays.asList(values()));
		}
	}
	
  public Encantamiento(String encantamientos) {
	  this.encantamientos=new ArrayList<Types>();
	  load(encantamientos);
  }
  
  private void load(String encantamientos) {
	  String parts[] = encantamientos.split("-");
	  for(int i=0;i<parts.length; i++) {
		  addEncantamiento(getType(parts[i]));
	  }
  }
  
  private Types getType(String type) {
	  List<Types> pos = Types.getPosibilities();
	  for(int i=0;i<numOfEncantamientos; i++) {
		  if(pos.get(i).toString().equals(type)) {
			  return pos.get(i);
		  }
	  }
	  return null;
  }
  
  
  private boolean addEncantamiento(Types en) {
	  if(!isAlready(en)) {
		  encantamientos.add(en);
		  return true;
	  }
	  return false;
  } 
  
  private boolean isAlready(Types en) {
	  for(int i=0; i<encantamientos.size(); i++) {
		  if(encantamientos.get(i)!=null && encantamientos.get(i).equals(en)) {
			  return true;
		  }
	  }
	  return false;
  }
  
  public List<Types> getEncantamientos() {
	  return encantamientos;
  }
  
  @Override
  public String toString() {
	  StringBuilder sb = new StringBuilder();
	  sb.append((isAlready(Types.Ap))? Types.Ap.toString()+"-":"");
	  sb.append((isAlready(Types.De))? Types.De.toString()+"-":"");
	  sb.append((isAlready(Types.En))? Types.En.toString()+"-":"");
	  sb.append((isAlready(Types.Ob))? Types.Ob.toString()+"-":"");
	  sb.append((isAlready(Types.Ol))? Types.Ol.toString()+"-":"");
	  sb.append((isAlready(Types.Ru))? Types.Ru.toString()+"-":"");
	  String secuence =sb.toString();
	  if(secuence.toCharArray()[secuence.length()-1]=='-') {
		  secuence=secuence.substring(0,secuence.length()-1);
	  }
	  return secuence;
  }
  
  
}
