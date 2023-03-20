package es.ieslavereda.mychessandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TableLayout board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TableRow row;
        TextView textView;

        board = findViewById(R.id.board);

        row = new TableRow(this);

        row.addView(getTextView(""));

        for(int i = 0; i < 8 ; i++){

            for(int j = 0; j <8; j++)
                row.addView(getTextView(String.valueOf((char)('A'+ j))));
        }

        row.addView(getTextView(""));

        board.addView(row);

    }

    private TextView getTextView(String text) {
        DisplayMetrics displayMetrics = new DisplayMetrics();


        ((Activity) this).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int widh = displayMetrics.widthPixels;

        TextView txtView = new TextView(this);

        txtView.setText(text);
        txtView.setTextColor(getResources().getColor(R.color.white,getApplicationContext().getTheme()));
        txtView.setBackgroundColor(getResources().getColor(R.color.black,getApplicationContext().getTheme()));
        txtView.setWidth(widh/10);
        txtView.setHeight(widh/10);
        txtView.setGravity(Gravity.CENTER);

        return txtView;
    }
}