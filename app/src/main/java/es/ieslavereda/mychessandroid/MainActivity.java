package es.ieslavereda.mychessandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.LinkedHashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TableLayout board;
    private Map<Coordenada, TextView> mapaTablero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TableRow[] rowArray = new TableRow[8];
        TableRow lettersRow = new TableRow(this);
        TextView textView;
        mapaTablero = new LinkedHashMap<>();
        Coordenada coordenada;

        board = findViewById(R.id.board);

        lettersRow.addView(getTextView(""));
        for(int j = 0; j <8; j++){
            lettersRow.addView(getTextView(String.valueOf((char)('A'+ j))));
        }
        lettersRow.addView(getTextView(""));
        board.addView(lettersRow);


        lettersRow = new TableRow(this);

        lettersRow.addView(getTextView(""));
        for(int j = 0; j <8; j++){
            lettersRow.addView(getTextView(String.valueOf((char)('A'+ j))));
        }
        lettersRow.addView(getTextView(""));
        board.addView(lettersRow);





//        for(int row = 0; row <=7; row++){
//            for(int col=0; col<=7; col++){
//                coordenada = new Coordenada((char)('A'+col),row+1);
//                mapaTablero.put(coordenada, new TextView(rowArray[1].addView(getTextView(""))));
//            }
//        }

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

        return textView;
    }
}