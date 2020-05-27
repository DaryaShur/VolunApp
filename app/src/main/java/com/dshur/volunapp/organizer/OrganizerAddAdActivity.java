package com.dshur.volunapp.organizer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dshur.volunapp.R;
import com.dshur.volunapp.model.Ad;
import com.dshur.volunapp.viewmodel.AdViewModel;


public class OrganizerAddAdActivity extends AppCompatActivity {
    private EditText titleEditText;
    private EditText descriptionEditText;
    Button saveAdButton;
    private AdViewModel mAdViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizer_add_ad);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add New Ad");

        final String TAG = "OrganizerAddAdActivity";

        titleEditText = findViewById(R.id.titleEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        saveAdButton = findViewById(R.id.saveAdButton);

        mAdViewModel = ViewModelProviders.of(this).get(AdViewModel.class);
        mAdViewModel.init();

        saveAdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString();
                String description = descriptionEditText.getText().toString();

                if (title.trim().isEmpty()){
                    Toast.makeText(OrganizerAddAdActivity.this, "Please insert a title", Toast.LENGTH_SHORT).show();
                    return;
                }

                Ad ad = new Ad(title, description);
                mAdViewModel.addNewAd(ad);
                finish();
            }
        });

    }



   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.new_ad_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.saveAd:
                saveAd();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/


}
