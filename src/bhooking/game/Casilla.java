package bhooking.game;

public class Casilla {
	public final static int Ghostbusters=0;

	public final static int Ghost_type_1=1;
	public final static int Ghost_type_2=2;
	public final static int Ghost_type_3=3;
	public final static int Ghost_type_4=4;
	public final static int Ghost_type_5=5;
	
	public final static int Ghost_king=6;
	
	public final static int null_area=-1;
	
	private boolean isActive;
	private int type;
	
	Casilla(int type) {
		setType(type);
		activate();
	}

	public boolean isActive() {
		return isActive;
	}

	private void activate() {
		this.isActive = true;
	}
	
	public int getType() {
		return type;
	}

	private void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return ""+getType();
	}
	
	
	
	
	

}
