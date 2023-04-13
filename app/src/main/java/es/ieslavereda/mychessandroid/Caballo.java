package es.ieslavereda.mychessandroid;

//import es.ieslavereda.TAD.ListaSE;
//import es.ieslavereda.Tool;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a knight chess piece.
 */
public abstract class Caballo extends Piece{

    private Set<Coordenada> coordenadas;

    /**
     * Instantiates a new Caballo (knight).
     *
     * @param pieceType the piece type
     * @param celda     the celda
     */
    public Caballo(PieceType pieceType, Celda celda){
        super(pieceType, celda);
    }

    @Override
    public Set<Coordenada> getNextMoves() {
        coordenadas = new HashSet<>();
        Coordenada position = getCelda().getCoordenada();
        Coordenada c;

        //UpLeft
        c = position.up().up().left();
        check(c);
        //UpRight
        c = position.up().upRight();
        check(c);
        //LeftUp
        c = position.left().left().up();
        check(c);
        //RightUp
        c = position.right().right().up();
        check(c);
        //LeftDown
        c = position.left().left().down();
        check(c);
        //RightDown
        c = position.right().right().down();
        check(c);
        //DownLeft
        c = position.down().down().left();
        check(c);
        //DownRight
        c = position.down().down().right();
        check(c);

        return coordenadas;
    }

    private void check(Coordenada c){
        Tablero tablero = getCelda().getTablero();

        if(tablero.getCelda(c)!=null){
            if(tablero.getCelda(c).isEmpty())
                coordenadas.add(c);
            else if (tablero.getCelda(c).getPiece().getColor()!=getColor())
                coordenadas.add(c);
        }
    }

}
