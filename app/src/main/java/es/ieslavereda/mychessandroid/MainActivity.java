package es.ieslavereda.mychessandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.LinkedHashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TableLayout board;
    private TextView textView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TableRow row;
        board = findViewById(R.id.board);
        textView = findViewById(R.id.textView2);
        String color;


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
        color = getIntent().getStringExtra("color");
        if (color.equals("blancas")) {
            fillBoard(true);
        } else {
            fillBoard(false);
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

    private void fillBoard(boolean whitesUp){
        TableRow row1 = (TableRow) board.getChildAt(1), row2 = (TableRow) board.getChildAt(2);
        TableRow row7 = (TableRow) board.getChildAt(7), row8 = (TableRow) board.getChildAt(8);
        Celda celda;

        int[] imgPiezasBlancas = {R.mipmap.ic_b_torre_foreground, R.mipmap.ic_b_knight_foreground,
                R.mipmap.ic_b_alfil_foreground, R.mipmap.ic_b_rey_foreground,
                R.mipmap.ic_b_reina_foreground, R.mipmap.ic_b_alfil_foreground,
                R.mipmap.ic_b_knight_foreground, R.mipmap.ic_b_torre_foreground};

        int[] imgPiezasNegras = {R.mipmap.ic_n_torre_foreground, R.mipmap.ic_n_caballo_foreground,
                R.mipmap.ic_n_alfil_foreground, R.mipmap.ic_n_rey_foreground,
                R.mipmap.ic_n_reina_foreground, R.mipmap.ic_n_alfil_foreground,
                R.mipmap.ic_n_caballo_foreground, R.mipmap.ic_n_torre_foreground};


        //Agregamos la primera fila por arriba
        if (whitesUp) {
            for(int i = 1; i < row1.getChildCount()-1; i++){
                celda = (Celda) row1.getChildAt(i);
                celda.setImageResource(imgPiezasBlancas[i-1]);
            }
        } else {
            for(int i = 1; i < row1.getChildCount()-1; i++){
                celda = (Celda) row1.getChildAt(i);
                celda.setImageResource(imgPiezasNegras[i-1]);
            }
        }

        //Agregamos los peones
        for(int i = 1; i < row2.getChildCount()-1; i++){
            celda = (Celda) row2.getChildAt(i);
            celda.setImageResource(R.mipmap.ic_b_peon_foreground);
            celda = (Celda) row7.getChildAt(i);
            celda.setImageResource(R.mipmap.ic_n_peon_foreground);
        }

        //Agregamos la primera fila por arriba
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
        if(view instanceof Celda)
            textView.setText(((Celda)view).getCoordenada().toString());
        else
            textView.setText(((TextView)view).getText());
    }
}