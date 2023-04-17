package es.ieslavereda.mychessandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TableLayout board;
    private TextView textView;
    private Set<Coordenada> coordenadas;
    private Coordenada coordenadaSeleccionada;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TableRow row;
        board = findViewById(R.id.board);
        textView = findViewById(R.id.textView2);

        // Fila superior
        addTextViews();


        // Parte media
        for (int r = 1; r <= 8; r++) {
            row = new TableRow(this);
            row.addView(getTextView(""+r));

            for (int c = 'A'; c <= 'H'; c++) {
                Celda celda = new Celda(this, new Coordenada((char) c, r), null);
                celda.setOnClickListener(this);
//                celda.setImageResource(R.mipmap.ic_b_knight_foreground);
                row.addView(celda);
            }

            row.addView(getTextView(""+r));
            board.addView(row);
        }


        // Fila inferior
        addTextViews();

        // Rellenamos el tablero
        fillBoard();

        //Almacenamos los nombres de los nuevos jugadores y de paso saludamos
        showNames();

        //Gestionamos los turnos de los jugadores
        turn();
    }

    private void turn() {

        //Almacenamos la celda seleccionada por el jugador
        Celda celdaSeleccionada = selectCell();

//        if(coordenadas.size() != 0) {
//            t.highlight(coordenadas);
//            MatchScreen.printBoard(t);
//            //A continuación el usuario selecciona el movimiento o cancela el mover esa pieza
//            selectMovement(celda);
//            t.resetColors();
//            if (movementDone) {
//                isKingOnTarget(false, t);
//                if (kingOnTarget)
//                    if (colorKingOnTarget != color)
//                        isCheckmate();
//            }
//            MatchScreen.printBoard(t);
//        }


    }

    private Celda selectCell() {
        Celda celda;

        if(coordenadaSeleccionada!=null){
            celda = board.getCelda
            coordenadas = new HashSet<>(celda.getPiece().getNextMoves());
        }

        return celda;

    }

    @SuppressLint("SetTextI18n")
    private void showNames() {
        Intent intent = getIntent();
        TextView tVPlayer1Name = findViewById(R.id.tVPlayer1Name), tVPlayer2Name = findViewById(R.id.tVPlayer2Name);

        // Verificar si el intent tiene los extras "player1Name" y "player2Name"
        if (intent.hasExtra("player1Name") && intent.hasExtra("player2Name")) {
            String player1Name = intent.getStringExtra("player1Name");
            String player2Name = intent.getStringExtra("player2Name");

            // Mostrar el Toast en MainActivity
            Toast.makeText(MainActivity.this, "Welcome " + player1Name + " and " + player2Name + "!", Toast.LENGTH_SHORT).show();

            if(getIntent().getStringExtra("color").equals("blacks")){
                ConstraintLayout.LayoutParams layoutParams1 = (ConstraintLayout.LayoutParams) tVPlayer1Name.getLayoutParams();
                        layoutParams1.topToBottom = board.getId();
                        layoutParams1.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
                        layoutParams1.bottomToTop = ConstraintLayout.LayoutParams.UNSET;

                tVPlayer1Name.setLayoutParams(layoutParams1);

                ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) tVPlayer2Name.getLayoutParams();
                        layoutParams2.bottomToTop = board.getId();
                        layoutParams2.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
                        layoutParams2.topToBottom = ConstraintLayout.LayoutParams.UNSET;

                tVPlayer2Name.setLayoutParams(layoutParams2);
            }

            tVPlayer1Name.setText("Player 1: " + player1Name);
            tVPlayer2Name.setText("Player 2: " + player2Name);
        }
    }

    private void addTextViews() {
        TableRow row;

        row = new TableRow(this);
        row.addView(getTextView(""));

        for (int i = 0; i < 8; i++)
            row.addView(getTextView(String.valueOf((char) ('A' + i))));

        row.addView(getTextView(""));

        board.addView(row);

    }

    private void fillBoard(){
        String color = getIntent().getStringExtra("color");
        TableRow row1, row2, row7, row8;
        Celda celda;
        int[] imgPiezasBlancas, imgPiezasNegras;

        if (color.equals("blacks")) {
            row1 = (TableRow) board.getChildAt(1);
            row2 = (TableRow) board.getChildAt(2);
            row7 = (TableRow) board.getChildAt(7);
            row8 = (TableRow) board.getChildAt(8);
            imgPiezasBlancas = new int[]{R.mipmap.ic_b_torre_foreground, R.mipmap.ic_b_knight_foreground,
                    R.mipmap.ic_b_alfil_foreground, R.mipmap.ic_b_rey_foreground,
                    R.mipmap.ic_b_reina_foreground, R.mipmap.ic_b_alfil_foreground,
                    R.mipmap.ic_b_knight_foreground, R.mipmap.ic_b_torre_foreground};
            imgPiezasNegras = new int[]{R.mipmap.ic_n_torre_foreground, R.mipmap.ic_n_caballo_foreground,
                    R.mipmap.ic_n_alfil_foreground, R.mipmap.ic_n_rey_foreground,
                    R.mipmap.ic_n_reina_foreground, R.mipmap.ic_n_alfil_foreground,
                    R.mipmap.ic_n_caballo_foreground, R.mipmap.ic_n_torre_foreground};
        } else {
            row1 = (TableRow) board.getChildAt(8);
            row2 = (TableRow) board.getChildAt(7);
            row7 = (TableRow) board.getChildAt(2);
            row8 = (TableRow) board.getChildAt(1);
            imgPiezasBlancas = new int[]{R.mipmap.ic_b_torre_foreground, R.mipmap.ic_b_knight_foreground,
                    R.mipmap.ic_b_alfil_foreground, R.mipmap.ic_b_reina_foreground,
                    R.mipmap.ic_b_rey_foreground, R.mipmap.ic_b_alfil_foreground,
                    R.mipmap.ic_b_knight_foreground, R.mipmap.ic_b_torre_foreground};
            imgPiezasNegras = new int[]{R.mipmap.ic_n_torre_foreground, R.mipmap.ic_n_caballo_foreground,
                    R.mipmap.ic_n_alfil_foreground, R.mipmap.ic_n_reina_foreground,
                    R.mipmap.ic_n_rey_foreground, R.mipmap.ic_n_alfil_foreground,
                    R.mipmap.ic_n_caballo_foreground, R.mipmap.ic_n_torre_foreground};
        }


        //Agregamos la primera fila por arriba
        for(int i = 1; i < row1.getChildCount()-1; i++){
            celda = (Celda) row1.getChildAt(i);
            celda.setImageResource(imgPiezasBlancas[i-1]);
        }

        //Agregamos los peones
        for(int i = 1; i < row2.getChildCount()-1; i++){
            celda = (Celda) row2.getChildAt(i);
            celda.setImageResource(R.mipmap.ic_b_peon_foreground);
            celda = (Celda) row7.getChildAt(i);
            celda.setImageResource(R.mipmap.ic_n_peon_foreground);
        }

        //Agregamos la primera fila por abajo
        for(int i = 1; i < row8.getChildCount()-1; i++){
            celda = (Celda) row8.getChildAt(i);
            celda.setImageResource(imgPiezasNegras[i-1]);
        }

    }

    private TextView getTextView(String text) {
        DisplayMetrics displayMetrics = new DisplayMetrics();

        ((Activity) this).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;

        TextView textView = new TextView(this);

        textView.setText(text);
        textView.setTextColor(getResources().getColor(R.color.white,getApplicationContext().getTheme()));
        textView.setBackgroundColor(getResources().getColor(R.color.black,getApplicationContext().getTheme()));
        textView.setWidth(width /10);
        textView.setHeight(width /10);
        textView.setGravity(Gravity.CENTER);
        textView.setOnClickListener(this);

        return textView;
    }


    @Override
    public void onClick(View view) {
        if(view instanceof Celda){
            textView.setText(((Celda)view).getCoordenada().toString());
            coordenadaSeleccionada = ((Celda) view).getCoordenada();
        }
        else{
            textView.setText(((TextView)view).getText());
            coordenadaSeleccionada = null;
        }
    }
}