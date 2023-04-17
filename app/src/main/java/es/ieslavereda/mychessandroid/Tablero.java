package es.ieslavereda.mychessandroid;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TableLayout;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * The type Tablero.
 */
public class Tablero extends TableLayout {

    private IDeletePieceManager remainigPieces;
    private IDeletePieceManager deletedPieces;
    private boolean whitesUp;
    private Map<Coordenada, Celda> mapaTablero;

    /**
     * Instantiates a new Tablero.
     */
    public Tablero(Context context, AttributeSet attributeSet){
        super(context, attributeSet);

        whitesUp = false;
        this.remainigPieces = new DeletePieceManagerOwn();
        this.deletedPieces = new DeletePieceManagerOwn();
        mapaTablero = new LinkedHashMap<>();
        Coordenada coordenada;


        for(int row = 0; row <=7; row++){
            for(int col=0; col<=7; col++){
                coordenada = new Coordenada((char)('A'+col),row+1);
                mapaTablero.put(coordenada, new Celda(context, coordenada,this));
            }
        }

    }

    /**
     * Is whites up boolean.
     *
     * @return the boolean
     */
    public boolean isWhitesUp() {
        return whitesUp;
    }

    /**
     * Get celda celda.
     *
     * @param coordenada the coordenada
     * @return the celda
     */
    public Celda getCelda(Coordenada coordenada){
        if(coordenada.getFila()<1 || coordenada.getFila()>8)
            return null;
        if(coordenada.getCol()<'A' || coordenada.getCol()>'H')
            return null;
        return mapaTablero.get(coordenada);
    }

    /**
     * Gets remainig pieces.
     *
     * @return the remainig pieces
     */
    public IDeletePieceManager getRemainigPieces() {
        return remainigPieces;
    }

    /**
     * Gets deleted pieces.
     *
     * @return the deleted pieces
     */
    public IDeletePieceManager getDeletedPieces() {
        return deletedPieces;
    }

    /**
     * Gets mapa tablero.
     *
     * @return the mapa tablero
     */
    public Map<Coordenada, Celda> getMapaTablero() {
        return mapaTablero;
    }

    /**
     * Sets mapa tablero.
     *
     * @param mapaTablero the mapa tablero
     */
    public void setMapaTablero(Map<Coordenada, Celda> mapaTablero) {
        this.mapaTablero = mapaTablero;
    }

    /**
     * Highlight king.
     *
     * @param coordenada the coordenada
     */
    public void highlightKing(Coordenada coordenada){
        getCelda(coordenada).highlight();
    }

    /**
     * Highlight.
     *
     * @param coordenadas the coordenadas
     */
    public void highlight(Set<Coordenada> coordenadas){

        for (Coordenada c : coordenadas)
            getCelda(c).highlight();
    }

    /**
     * Reset colors.
     */
    public void resetColors(){
        Collection<Celda> celdas = mapaTablero.values();

        for (Celda celda : celdas) {
            celda.resetColor();
        }
    }

//    @Override
//    public String toString(){
//        String output = "   A  B  C  D  E  F  G  H\n";
//        for(int row = 0; row <=7; row++){
//            output += (row+1)+" ";
//            for(int col=0; col<=7; col++){
//                output += mapaTablero.get(new Coordenada((char)('A'+col), row+1));
//            }
//            output +=" "+(row+1)+"\n";
//        }
//        output += "   A  B  C  D  E  F  G  H\n";
//
//        output += "\t\t REMAINING PIECES";
//        output += "\n" + remainigPieces.toString() + "\n";
//
//        output += "\t\t DELETED PIECES";
//        output += "\n" + deletedPieces.toString();
//
//        return output;
//    }

}
