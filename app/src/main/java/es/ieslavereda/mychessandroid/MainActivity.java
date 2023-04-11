package es.ieslavereda.mychessandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
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
        TableRow row1 = (TableRow) board.getChildAt(1), row2 = (TableRow) board.getChildAt(2);
        TableRow row7 = (TableRow) board.getChildAt(7), row8 = (TableRow) board.getChildAt(8);

        if(row1.getChildAt(1) instanceof Celda){
            Celda celda = (Celda) row1.getChildAt(1);
            celda.setImageResource(R.mipmap.ic_b_knight_foreground);
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