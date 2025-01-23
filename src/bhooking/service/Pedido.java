package bhooking.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import bhooking.model.Castle;
import bhooking.model.Descuento;
import bhooking.model.User;
import bhooking.model.Descuento.CodigosDescuento;
import bhooking.util.ArgumentChecks;

public class Pedido {
	private Castle castillo;
	private Date fecha_inicio = new Date();
	private Date fecha_salida= new Date();
	private int numHabitaciones;
	private int numAdultos;
	private double amount;
	private User usuario;
	private String comentarios;

	public Pedido() {
	}

	public Date getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(Date fecha_inicio) {
		ArgumentChecks.isTrue(fecha_inicio!=null);
		this.fecha_inicio = fecha_inicio;
	}

	public Date getFecha_salida() {
		return fecha_salida;
	}

	public void setFecha_salida(Date fecha_salida) {
		ArgumentChecks.isTrue(fecha_salida!=null);
		this.fecha_salida = fecha_salida;
	}

	public boolean hasDiscount() {
		boolean hasDiscount=new Descuento().hasDiscount(usuario.getDNI());
		return hasDiscount;
	}

	public CodigosDescuento getDiscount() {
		if(hasDiscount()) {
			return new Descuento().getDiscount(usuario.getDNI());
		}
		return null;
	}

	public int getAmountWithoutDiscount() {
		return (int) (castillo.getPrecio()*numHabitaciones*daysDifference(fecha_salida,fecha_inicio));
	}


	public void calculateAmount() {
		double baseAmount=castillo.getPrecio()*numHabitaciones*daysDifference(fecha_salida, fecha_inicio);

		if (!hasDiscount()) {
			this.amount=baseAmount;
		} else {
			CodigosDescuento cd=new Descuento().getDiscount(usuario.getDNI());
			double discountFactor=(cd==CodigosDescuento.EXTRA25) ? 0.75 : 0.9;
			this.amount = baseAmount * discountFactor;
		}
	}

	public double getAmount() {
		return amount;
	}

	public static String conversion(String locale,double quantity) {
		switch(locale) {
		case "en": return ((int)(quantity*0.87))+" £";
		case "nor": return ((int)(quantity*11.21))+" NOK";
		case "su": return ((int)(quantity*11.14))+" SEK";
		default: return (int)quantity+" €";
		}
	}

	public int getNumAdultos() {
		return numAdultos;
	}

	public void setNumAdultos(int numAdultos) {
		this.numAdultos = numAdultos;
	}

	public int getNumHabitaciones() {
		return numHabitaciones;
	}

	public boolean isCorrectAdultsRooms() {
		return getNumAdultos()<=getNumHabitaciones()*2;
	}

	public boolean areDatesCorrect() {
		return fecha_salida.after(fecha_inicio) && daysDifference(fecha_salida, fecha_inicio)!=0;
	}

	public void setNumHabitaciones(int numHabitaciones) {
		this.numHabitaciones = numHabitaciones;
	}

	public Castle getCastillo() {
		return castillo;
	}


	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		if(comentarios!="") {
			this.comentarios = comentarios;
		}
	}

	public boolean addBook(Castle castillo) {
		ArgumentChecks.isTrue(castillo!=null);
		this.castillo=castillo;
		return true;
	}

	public boolean isComplete() {
		return getCastillo()!=null && getFecha_inicio()!=null 
				&& getFecha_salida()!=null && getNumHabitaciones()>0 && getUsuario()!=null
				&& getUsuario().isAdult();
	}

	public List<String> serialize() {
		if(isComplete()) {
			List<String> ls = new ArrayList<String>();
			StringBuilder sb = new StringBuilder();
			sb.append(usuario.getDNI()+";");
			sb.append(usuario.getName()+" "+usuario.getSurname()+";");
			sb.append(usuario.getEmail()+";");
			sb.append(castillo.getCodigo()+";");
			sb.append(fecha_inicio+";");
			sb.append(daysDifference(fecha_salida, fecha_inicio)+";");
			sb.append(numHabitaciones+";");
			sb.append(amount+";");
			sb.append(comentarios);
			ls.add(sb.toString());
			return ls;
		}
		return null;
	}

	protected int daysDifference(Date d1, Date d2) {
		long milis = d1.getTime() - d2.getTime();
		long diasDiferencia = TimeUnit.DAYS.convert(milis, TimeUnit.MILLISECONDS);
		return (int)diasDiferencia;
	}

}
