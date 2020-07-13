package sg.edu.rp.c346.id19036849.demomyprofile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etGPA;
    RadioGroup rgGender;
    Button btnSave;

    @Override
    protected void onResume() {
        super.onResume();

        // Step 2a: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        // Step 2b: Retrieve the saved data with the specified key from the SharedPreferences object
        String strName = prefs.getString("name", "");
        float fltGPA = prefs.getFloat("GPA", 0);
        int intID = prefs.getInt("genderID", R.id.radioButtonGenderMale);

        // Step 3a: Update the UI element with the value
        etName.setText(strName);
        etGPA.setText(Float.toString(fltGPA));
        rgGender.check(intID);

    }

    @Override
    protected void onPause() {
        super.onPause();

        save();

    }

    protected void save() {

        // Step 0: Get user input
        String strName = etName.getText().toString();
        float fltGPA = Float.parseFloat(etGPA.getText().toString());
        int intID = rgGender.getCheckedRadioButtonId();

        // Step 1a: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        // Step 1b: Obtain an instance of the SharedPreferences Editor for update later
        SharedPreferences.Editor prefEdit = prefs.edit();

        // Step 1c: Add the key-value pair
        prefEdit.putString("name", strName);
        prefEdit.putFloat("GPA", fltGPA);
        prefEdit.putInt("genderID", intID);

        // Step 1d: Call commit() to save the changes into SharedPreferences
        prefEdit.commit();

        Toast toast = Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_LONG);
        toast.show();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);
        rgGender = findViewById(R.id.radioGroupGender);
        btnSave = findViewById(R.id.buttonSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

    }


}
