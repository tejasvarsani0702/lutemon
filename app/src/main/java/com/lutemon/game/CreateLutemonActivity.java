package com.lutemon.game;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.lutemon.game.model.Lutemon;
import com.lutemon.game.model.Storage;

public class CreateLutemonActivity extends AppCompatActivity {
    private EditText edtName;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_lutemon);

        edtName = findViewById(R.id.edtLutemonName);
        radioGroup = findViewById(R.id.radioGroupColors);
    }

    public void createLutemon(View view) {
        String name = edtName.getText().toString().trim();
        int selectedId = radioGroup.getCheckedRadioButtonId();

        if (name.isEmpty() || selectedId == -1) {
            Toast.makeText(this, "Enter name & select color", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selectedButton = findViewById(selectedId);
        String color = selectedButton.getText().toString();

        Lutemon newLutemon = getLutemon(color, name);

        Storage.getInstance(this).addLutemon(newLutemon);
        Toast.makeText(this, "Lutemon Created!", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();
    }

    private static Lutemon getLutemon(String color, String name) {
        Lutemon newLutemon;
        if (color.contains("White")) {
            newLutemon = new Lutemon(name, "White", 5, 4, 20, R.drawable.l_white);
        } else if (color.contains("Green")) {
            newLutemon = new Lutemon(name, "Green", 6, 3, 19, R.drawable.l_green);
        } else if (color.contains("Pink")) {
            newLutemon = new Lutemon(name, "Pink", 7, 2, 18, R.drawable.l_pink);
        } else if (color.contains("Orange")) {
            newLutemon = new Lutemon(name, "Orange", 8, 1, 17, R.drawable.l_orange);
        } else {
            newLutemon = new Lutemon(name, "Black", 9, 0, 16, R.drawable.l_black);
        }
        return newLutemon;
    }

}
