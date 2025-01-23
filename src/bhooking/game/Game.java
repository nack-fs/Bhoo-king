package bhooking.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import bhooking.model.Descuento.CodigosDescuento;

public class Game {
	private Board board;
	private int attempts;
	private List<Integer> eliminados=new ArrayList<Integer>();
	private int actualMovement;

	private final static int MAX_NUM_ATTEMPTS=7;


	public Game() {
		this.board=new Board();
		this.attempts=MAX_NUM_ATTEMPTS;
	}

	public int getDiceResult() {
		return actualMovement;
	}


	public void throwDice() {
		actualMovement=new Random().nextInt(1,2+1);
	}

	public boolean moveCasilla(int src_x,int src_y,int dst_x,int dst_y) {
		if(board.isGhostbusters(src_x, src_y)) {
			int eliminate =this.board.move(src_x, src_y, dst_x, dst_y,actualMovement);
			actualMovement=0;
			attempts--;
			if (eliminate!=-1) {eliminados.add(eliminate);}
			return true;
		}
		return false;
	}

	public int getLeftAttempts() {
		return attempts;
	}

	public int showActualMovement() {
		return actualMovement;
	}


	public boolean hasFinished() {
		return attempts==0;
	}

	public CodigosDescuento getResult() {
		if(hasFinished()) {
			if(eliminados.contains(Casilla.Ghost_king)&& eliminados.contains(Casilla.Ghost_type_1)
					&& eliminados.contains(Casilla.Ghost_type_2) && eliminados.contains(Casilla.Ghost_type_3) 
					&& eliminados.contains(Casilla.Ghost_type_4) && eliminados.contains(Casilla.Ghost_type_5)) {
				return CodigosDescuento.EXTRA25;
			}else if(eliminados.contains(Casilla.Ghost_type_1)
					&& eliminados.contains(Casilla.Ghost_type_2) && eliminados.contains(Casilla.Ghost_type_3) 
					&& eliminados.contains(Casilla.Ghost_type_4) && eliminados.contains(Casilla.Ghost_type_5)) {
				return CodigosDescuento.EXTRA10;
			}else {
				return null;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return board.toString();
	}

	public Casilla[][] getCopyOfBoard() {
		return board.getCopyBoard();
	}

	public int getType(int x, int y) {
		if (board != null) {
			return board.getType(x, y);
		} else {
			return Casilla.null_area; 
		}
	}


}
