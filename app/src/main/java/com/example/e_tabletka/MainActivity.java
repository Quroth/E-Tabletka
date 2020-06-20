package com.example.e_tabletka;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    private CardView cardview_dodaj_lek, cardview_dodaj_przypomnienie, cardview_wyswietl_przypomnienia,
            cardview_statystyki, cardview_o_mnie, cardview_wyjscie;
    DatabaseHelper myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        cardview_dodaj_lek = (CardView) findViewById(R.id.cardview_dodaj_lek);
        cardview_dodaj_przypomnienie = (CardView) findViewById(R.id.cardview_dodaj_przypomnienie);
        cardview_wyswietl_przypomnienia = (CardView) findViewById(R.id.cardview_moje_przypomnienia);
        cardview_statystyki = (CardView) findViewById(R.id.cardview_statystyki);
        cardview_o_mnie = (CardView) findViewById(R.id.cardview_o_mnie);
        cardview_wyjscie = (CardView) findViewById(R.id.cardview_wyjscie);

        cardview_dodaj_lek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog dialogBuilder = new AlertDialog.Builder(MainActivity.this).create();
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.alertdialog_custom_view, null);

                final EditText editName = (EditText) dialogView.findViewById(R.id.et_nazwa_leku);
                Button positiveButton = (Button) dialogView.findViewById(R.id.dialog_positive_btn);
                Button negativeButton = (Button) dialogView.findViewById(R.id.dialog_negative_btn);

                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                      boolean isInserted = myDb.insertData(editName.getText().toString());
                      if (isInserted = true) {
                          Toast.makeText(MainActivity.this, "Lek został dodany pomyślnie!", Toast.LENGTH_SHORT).show();
                          dialogBuilder.dismiss();
                      }
                      else
                          Toast.makeText(MainActivity.this, "Błąd podczas dodawania leku!", Toast.LENGTH_SHORT).show();
                    }
                });
                negativeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogBuilder.dismiss();
                    }
                });

                dialogBuilder.setView(dialogView);
                dialogBuilder.show();

            }

            });
        cardview_dodaj_przypomnienie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Dodaj_przypomnienie.class);
                startActivity(intent);
            }
        });

        cardview_o_mnie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), O_mnie.class);
                startActivity(intent);

            }
        });

        cardview_wyjscie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

        }
    }