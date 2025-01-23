package bhooking.model;

public class User {
	private boolean isRegistred;
	private String DNI;
	private String name;
	private String surname;
	private String email;
	private boolean isAdult;
	
	public User(String name,String surname,String email,String DNI,boolean isAdult) {
		setName(name);
		setSurname(surname);
		setEmail(email);
		setDNI(DNI);
		setAdult(isAdult);
	}
	
	
	public boolean isRegistred() {
		return isRegistred;
	}
	public void registrar() {
		this.isRegistred = true;
	}
	public String getDNI() {
		return DNI;
	}
	private void setDNI(String dNI) {
		DNI = dNI;
	}
	public String getName() {
		return name;
	}
	private void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	private void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	private void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdult() {
		return isAdult;
	}

	private void setAdult(boolean isAdult) {
		this.isAdult = isAdult;
	}
	

}
