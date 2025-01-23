package bhooking.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
	private Casilla[][] board;
	public final static int ROW_SIZE=5; 
	public final static int COL_SIZE=7; 

	private List<Integer> tmp_store= new ArrayList<Integer>();

	public Board() {
		inicializar();
		fill();
	}

	private void inicializar() {
		this.board=new Casilla[ROW_SIZE][COL_SIZE];
	}

	private void fill() {
		this.board[0][3]=new Casilla(Casilla.Ghost_king);
		for(int col=0;col<COL_SIZE; col++) {
			this.board[4][col]=new Casilla(Casilla.Ghostbusters);
		}
		fillNormalGhost();
	}


	private void fillNormalGhost() {
		this.tmp_store = loadStore();
		int size=tmp_store.size();
		while(size>0) {
			for(int i=1; i<ROW_SIZE; i++) {
				for(int j=0; j<COL_SIZE; j++) {
					if(!isNullArea(i,j)) {
						if(!tmp_store.isEmpty()) {
							board[i][j]=new Casilla(pickOne());
							size--;
						}
					}
				}
			}
		}
	}

	private int pickOne() {
		int val=tmp_store.get(0);
		tmp_store.get(0);
		tmp_store.remove(0);
		return val;
	}


	private boolean isNullArea(int x,int y) {
		//Restricción fila 2
		if(x==2) {if (y==0 || y==6) {return true;}
		//Restricción fila 1
		}if(x==1) { 
			if (y==0 || y==1 || y==5 || y==6) {return true;}
		//Restricción fila 0
		}if(x==0) {
			if(!(y==3)) {return true;
			}
		}
		return false;
	}

	private List<Integer> loadStore() {
		List<Integer> almacen = new ArrayList<Integer>();
		for(int i=Casilla.Ghost_type_1;i<=Casilla.Ghost_type_5;i++) {
			for(int j=0; j<3; j++) {
				almacen.add(i);
			}
		}
		Collections.shuffle(almacen);
		return almacen;
	}
	
	public int move(int src_x,int src_y,int dst_x,int dst_y,int dice) {
		if((src_y==dst_y&& src_x-dst_x==dice) && !isNullArea(dst_x, dst_y)) {
			int aux=board[dst_x][dst_y].getType();
			board[dst_x][dst_y]=new Casilla(Casilla.Ghostbusters);
			board[src_x][src_y]=null;
			return aux;
		}
		return -1;
	} 
	
	protected boolean isGhostbusters(int x,int y) {
		return this.board[x][y].getType()==Casilla.Ghostbusters;
	}
	
	protected int getType(int x, int y) {
        if (board!=null) {
            return board[x][y].getType();
        } else {
            return Casilla.null_area;
        }
    }
	
	protected Casilla[][] getCopyBoard() {
		Casilla[][] aux = new Casilla[ROW_SIZE][COL_SIZE];
		for(int i=0; i<ROW_SIZE; i++) {
			for(int j=0;j<COL_SIZE;j++) {
				aux[i][j]=board[i][j];
			}
		}
		return aux;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<ROW_SIZE; i++) {
			for(int j=0;j<COL_SIZE;j++) {
				sb.append(board[i][j]!=null? board[i][j].toString():"*");
				sb.append(" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

}
