package es.ieslavereda.mychessandroid;

import java.util.List;

/**
 * The type Peon negro.
 */
public final class PeonNegro extends Peon{
    /**
     * Instantiates a new Peon negro.
     *
     * @param celda the celda
     */
    public PeonNegro(Celda celda){
        super(PieceType.BLACK_PEON, celda);
    }

    @Override
    public void transform() {
//        ListaDE<Piece> nonFilteredDeletedPieces = this.getCelda().getTablero().getDeletedPieces().getAll();
//        List<Piece> filteredDeletedPieces = Tools.getDeletedPiecesByColor(nonFilteredDeletedPieces, this);
//        ListaDE<Piece> remainingPieces = this.getCelda().getTablero().getRemainigPieces().getAll();
//
//        Piece piece = MatchScreen.selectPieceToTransformMessage(filteredDeletedPieces);
//
//        if(piece!=null) {
//
//            switch (piece.getShape()) {
//                case BLACK_REINA:
//                    Piece reinaNegra = new ReinaNegra(getCelda());
//                    Tools.addAndRemoveProcess(reinaNegra, this, nonFilteredDeletedPieces, remainingPieces);
//                    celda=null;
//                    break;
//                case BLACK_TORRE:
//                    Piece torreNegra = new TorreNegra(getCelda());
//                    Tools.addAndRemoveProcess(torreNegra, this, nonFilteredDeletedPieces, remainingPieces);
//                    celda=null;
//                    break;
//                case BLACK_CABALLO:
//                    Piece caballoNegro = new CaballoNegro(getCelda());
//                    Tools.addAndRemoveProcess(caballoNegro, this, nonFilteredDeletedPieces, remainingPieces);
//                    celda=null;
//                    break;
//                case BLACK_ALFIL:
//                    Piece alfilNegro = new AlfilNegro(getCelda());
//                    Tools.addAndRemoveProcess(alfilNegro, this, nonFilteredDeletedPieces, remainingPieces);
//                    celda=null;
//                    break;
//                default:
//                    break;
//            }
//        }
    }
}
