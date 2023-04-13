package es.ieslavereda.mychessandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class InitialMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_menu);


        Button buttonNewGame = findViewById(R.id.buttonNewGame);
        buttonNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Game g = new Game();

                enterNames();
            }
        });
    }

    private void enterNames() {
        AlertDialog.Builder builderEnterNames = new AlertDialog.Builder(InitialMenu.this);
        LayoutInflater inflater = LayoutInflater.from(InitialMenu.this);
        View view = inflater.inflate(R.layout.activity_dialog_enter_names, null);
        final EditText editTextPlayer1 = view.findViewById(R.id.editTextPlayer1);
        final EditText editTextPlayer2 = view.findViewById(R.id.editTextPlayer2);
        builderEnterNames.setView(view);
        builderEnterNames.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                String player1Name = editTextPlayer1.getText().toString().trim();
                String player2Name = editTextPlayer2.getText().toString().trim();
                if (!player1Name.isEmpty() && !player2Name.isEmpty()) {
                    Intent intent = new Intent(InitialMenu.this, MainActivity.class);
                    intent.putExtra("player1Name", player1Name);
                    intent.putExtra("player2Name", player2Name);
                    selectColor(intent);
                } else {
                    // Si alguno de los nombres está vacío, se muestra un mensaje de error.
                    Toast.makeText(InitialMenu.this, "Please enter names for both players", Toast.LENGTH_SHORT).show();
                }
            }
        });
        AlertDialog dialogEnterNames = builderEnterNames.create();
        dialogEnterNames.show();

    }

    private void selectColor(Intent intent) {
        AlertDialog.Builder builderSelectColor = new AlertDialog.Builder(InitialMenu.this);
        builderSelectColor.setTitle("Player 1 (" + intent.getStringExtra("player1Name") + ") choose color:");
        builderSelectColor.setPositiveButton("Whites", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                intent.putExtra("color", "whites");
                startActivity(intent);
            }
        });
        builderSelectColor.setNegativeButton("Blacks", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                intent.putExtra("color", "blacks");
                startActivity(intent);
            }
        });
        AlertDialog dialogSelectColor = builderSelectColor.create();
        dialogSelectColor.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                // Ajustar los márgenes para centrar los botones
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(0,0,130,0);
                lp.gravity = Gravity.CENTER; // Ajustar los márgenes para centrar los botones
                Button positiveButton = dialogSelectColor.getButton(DialogInterface.BUTTON_POSITIVE);
                positiveButton.setLayoutParams(lp);
                Button negativeButton = dialogSelectColor.getButton(DialogInterface.BUTTON_NEGATIVE);
                negativeButton.setLayoutParams(lp);
            }
        });
        dialogSelectColor.show();
//                g.start();
    }
}