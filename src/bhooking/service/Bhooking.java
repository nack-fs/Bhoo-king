package bhooking.service;

import java.io.IOException;
import java.util.Date;

import bhooking.model.Castle;
import bhooking.model.User;
import bhooking.util.FileUtil;

public class Bhooking {
	private Catalogo catalogo;
	private Pedido pedido;
	private boolean isRegistred;
	private boolean hasFinished;
	
	public Bhooking() {
		this.catalogo=new Catalogo();
		this.pedido=new Pedido();
	}
	
	public Catalogo showCatalog() {
		return catalogo;
	}
	
	public void changeCastlesLanguage(String actualLocale) {
		this.catalogo=new Catalogo(actualLocale);
	}
	
	public boolean isRegistred() {
		return isRegistred;
	}
	
	public void register() {
		this.isRegistred=true;
	}
	
	public boolean isHasFinished() {
		return hasFinished;
	}

	private void finished() {
		this.hasFinished = true;
	}

	public void book(Date fechaInicio,Date fechaSalida,int numHabit,int numAdultos,Castle castilo) {
		pedido.setFecha_inicio(fechaInicio);
		pedido.setFecha_salida(fechaSalida);
		pedido.setNumHabitaciones(numHabit);
		pedido.setNumAdultos(numAdultos);
		pedido.addBook(castilo);
	}
	
	public void addUserTobook(User usuario) {
		pedido.setUsuario(usuario);
	}
	
	public Pedido showPedido() {
		if(pedido.isComplete()) {return pedido;}
		return null;
	}
	
	public boolean finalizarPedido() {
		if(pedido.isComplete()) {
			finished();
			try {
				new FileUtil().writeLines("OUTPUT/reservas.dat", pedido.serialize());
				return true;
			} catch (IOException e) {}
		}return false;
	}
	
	public double getAmount() {
		if(pedido.isComplete()) {
			return pedido.getAmount();
		}
		return -1;
	}
	
	public void calculateAmount() {
		if(pedido.isComplete()) {
			pedido.calculateAmount();
		}
	}
	
	public double getAmountWithoutDisucont() {
		if(pedido.isComplete()) {
			return pedido.getAmountWithoutDiscount();
		}
		return -1;
	}
	
	public boolean hasDiscount() {
		return pedido.hasDiscount();
	}
	
	public boolean isCorrectAdultsRooms() {
		return pedido.isCorrectAdultsRooms();
	}
	
	public boolean areDatesCorrect() {
		return pedido.areDatesCorrect();
	}
	
	public boolean areDatesInRange(Date regreso,Date inicio) {
		return pedido.daysDifference(regreso, inicio)<45+1;
	}
	
	public void setComentario(String txt) {
		this.pedido.setComentarios(txt);
	}

}
