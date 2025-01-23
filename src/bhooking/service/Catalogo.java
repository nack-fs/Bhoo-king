package bhooking.service;

import java.util.List;

import bhooking.loader.BhookingLoader;
import bhooking.model.Castle;

public class Catalogo {
	private List<Castle> catalogo;
	
	public Catalogo() {
		loadAllCastles();
	}
	
	public Catalogo(String actualLocale) {
		loadAllCastles(actualLocale);
	}
	
	private void loadAllCastles() {
		this.catalogo=BhookingLoader.load();
	}
	
	private void loadAllCastles(String actualLocale) {
		this.catalogo=BhookingLoader.load(actualLocale);
	}
	
	public List<Castle> getCatalogo() {
		return catalogo;
	}
	
	

}
