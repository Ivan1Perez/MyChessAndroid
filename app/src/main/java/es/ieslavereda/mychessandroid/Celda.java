package es.ieslavereda.mychessandroid;

import com.diogonunes.jcolor.Attribute;
//
import static com.diogonunes.jcolor.Ansi.colorize;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;

/**
 * The type Celda.
 */
public class Celda extends androidx.appcompat.widget.AppCompatImageView {

    private Tablero tablero;
    private int color;
    private Coordenada coordenada;
    private Piece piece;
    private int original;

    /**
     * Instantiates a new Celda.
     *
     * @param coordenada the coordenada
     * @param tablero    the tablero
     */

    public Celda(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
    }

    public Celda(Context context, Coordenada coordenada, Tablero tablero) {
        super(context);
        this.coordenada = coordenada;
        this.tablero = tablero;
        this.piece = null;
        this.original = (
                (coordenada.getFila()-1 + coordenada.getCol()-'A')%2==0)
                ?
                R.color.cell_white
                :
                R.color.cell_black;
        this.color = original;

        DisplayMetrics displayMetrics = new DisplayMetrics();

        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;

        setMaxWidth(width /10);
        setMinimumWidth(width /10);
        setMaxHeight(width /10);
        setMinimumHeight(width /10);

        setPadding(0,0,0,0);
        setScaleType(ScaleType.FIT_CENTER);
        setImageResource(R.mipmap.ic_b_knight_foreground);
        setAdjustViewBounds(true);

        updateCellView();
    }

    private void updateCellView() {
        setBackgroundColor(getResources().getColor(color,getContext().getTheme()));
    }

    /**
     * Gets tablero.
     *
     * @return the tablero
     */
    public Tablero getTablero() {
        return tablero;
    }

    /**
     * Is empty boolean.
     *
     * @return the boolean
     */
    public boolean isEmpty(){
        return piece == null;
    }

    /**
     * Gets coordenada.
     *
     * @return the coordenada
     */
    public Coordenada getCoordenada() {
        return coordenada;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public int getColor() {
        return color;
    }

    /**
     * Sets piece.
     *
     * @param piece the piece
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     * Get piece piece.
     *
     * @return the piece
     */
    public Piece getPiece(){
        return piece;
    }

    /**
     * Highlight the color of the cells selected depending on the color of the piece it contains and if is on target
     */
    public void highlight(){
        if (isEmpty()) {

            if (original == R.color.cell_white)
                color = R.color.HIGHLIGHT_WHITE;
            else
                color = R.color.HIGHLIGHT_BLACK;

        } else {
            color = (original == R.color.cell_white)
                    ?
                    R.color.HIGHLIGHT_KILL_WHITE
                    :
                    R.color.HIGHLIGHT_KILL_BLACK;
        }

        updateCellView();
    }

    @Override
    public int hashCode() {
        return getPiece().hashCode();
    }

    /**
     * The enum Color celda.
     */
    enum ColorCelda {
        /**
         * White cell color celda.
         */
        WHITE_CELL(Attribute.BACK_COLOR(180, 180, 180)),
        /**
         * Black cell color celda.
         */
        BLACK_CELL(Attribute.BACK_COLOR(100, 100, 100)),
        /**
         * Highlight white color celda.
         */
        HIGHLIGHT_WHITE(Attribute.BACK_COLOR(180, 180, 0)),
        /**
         * Highlight black color celda.
         */
        HIGHLIGHT_BLACK(Attribute.BACK_COLOR(130, 180, 0)),
        /**
         * Highlight kill white color celda.
         */
        HIGHLIGHT_KILL_WHITE(Attribute.BACK_COLOR(180, 0, 0)),
        /**
         * Highlight kill black color celda.
         */
        HIGHLIGHT_KILL_BLACK(Attribute.BACK_COLOR(130, 0, 0));

        private Attribute attribute;

        ColorCelda(Attribute attribute) {
            this.attribute = attribute;
        }

        /**
         * Gets attribute.
         *
         * @return the attribute
         */
        public Attribute getAttribute() {
            return attribute;
        }

    }

    /**
     * Reset color.
     */
    public void resetColor() {
        color = original;
        updateCellView();
    }

//    @Override
//    public String toString(){
//        if(piece==null)
//            return colorize("   ",color.getAttribute());
//        return colorize(" ",color.getAttribute()) +
//                piece +
//                colorize(" ",color.getAttribute());
//    }

}
