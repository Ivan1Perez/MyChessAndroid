package es.ieslavereda.mychessandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class InitialMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_menu);


        Button buttonNewGame = findViewById(R.id.buttonNewGame);
        buttonNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(InitialMenu.this);
                builder.setTitle("Choose the color");
                builder.setPositiveButton("Whites", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(InitialMenu.this, MainActivity.class);
                        intent.putExtra("color", "whites");
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Blacks", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(InitialMenu.this, MainActivity.class);
                        intent.putExtra("color", "blacks");
                        startActivity(intent);
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                        // Ajustar los márgenes para centrar los botones
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);
                        lp.setMargins(0,0,130,0);
                        lp.gravity = Gravity.CENTER; // Ajustar los márgenes para centrar los botones
                        Button positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                        positiveButton.setLayoutParams(lp);
                        Button negativeButton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                        negativeButton.setLayoutParams(lp);
                    }
                });
                dialog.show();
            }
        });
    }
}