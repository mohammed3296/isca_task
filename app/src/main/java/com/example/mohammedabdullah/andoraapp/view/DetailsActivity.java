package com.example.mohammedabdullah.andoraapp.view;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.mohammedabdullah.andoraapp.R;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    TextView offer;
    TextView name;
    TextView price;
    TextView details;
    ImageView image;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();
        intent.getExtras().getString("CAT");

        offer = findViewById(R.id.offer);
        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        details = findViewById(R.id.details);
        image = findViewById(R.id.image);

      //  offer.setText(intent.getExtras().getString("OFFER"));

        name.setText(intent.getExtras().getString("NAME"));

        price.setText(intent.getExtras().getString("PRICE"));

        details.setText(intent.getExtras().getString("DETAILS"));

        if (intent.getExtras().getString("IMAGE") != "") {
            Log.e(">>>>>" , intent.getExtras().getString("IMAGE")) ;
            Uri builtUri = Uri.parse(intent.getExtras().getString("IMAGE")).buildUpon().build();
            Picasso.get().load(builtUri).into(image);
        }

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

    }
}
