package pyxis.uzuki.live.hintablespinnerdemo;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import pyxis.uzuki.live.hintablespinner.HintableSpinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final HintableSpinner spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new HintableSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(@NotNull View view, int position, @NotNull String item) {
                Toast.makeText(MainActivity.this, String.format("selected %s -> %s", position, item), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

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
    }
}