package pyxis.uzuki.live.hintablespinnerdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

import pyxis.uzuki.live.hintablespinner.HintableSpinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final HintableSpinner spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new HintableSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(boolean isNothingSelected, @org.jetbrains.annotations.Nullable View view,
                                       int position, @org.jetbrains.annotations.Nullable String item) {
                if (isNothingSelected) return;

                Toast.makeText(MainActivity.this, String.format("selected %s -> %s", position, item), Toast.LENGTH_SHORT).show();
            }
        });
        spinner.addDropdownList("A", "B", "C", "D", "E");

        Button btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinner.clear();
            }
        });

        Button btnRandomSelect = findViewById(R.id.btnRandomSelect);
        btnRandomSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();
                int index = random.nextInt(spinner.getDropdownList().size());
                spinner.setItemSelected(index);
            }
        });
    }
}