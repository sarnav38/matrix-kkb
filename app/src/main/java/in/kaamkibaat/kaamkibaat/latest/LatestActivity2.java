package in.kaamkibaat.kaamkibaat.latest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import in.kaamkibaat.kaamkibaat.MainActivity;
import in.kaamkibaat.kaamkibaat.R;
import in.kaamkibaat.kaamkibaat.bio.BioActivity;
import in.kaamkibaat.kaamkibaat.bio.BioActivity2;

public class LatestActivity2 extends AppCompatActivity {

    TextView mtitle;
    ImageView mimage;
    TextView mcontent;
    TextView mcat;
    TextView mtitleTag;
    private String image;
    AdView ad1,ad2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest2);

        getSupportActionBar().setTitle("Kaam Ki Baat");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mtitle = findViewById(R.id.textView_L_A2_1);
        mimage = findViewById(R.id.imageView_L_A2_1);
        mcontent = findViewById(R.id.textView_L_A2_2);
        mcat = findViewById(R.id.textView_L_A2_3);
        mtitleTag = findViewById(R.id.textView_L_A2_4);

        Intent intent = getIntent();
        String title = intent.getExtras().getString("title");
        String content = intent.getExtras().getString("content");
        String cat = intent.getExtras().getString("cat");
        String titleTag = intent.getExtras().getString("titleTag");
        image =intent.getExtras().getString("image");

        mtitle.setText(Html.fromHtml(title));
        mcontent.setText(Html.fromHtml(content));
        mcat.setText(cat);
        mtitleTag.setText(titleTag);
        Picasso.get().load(image).networkPolicy(NetworkPolicy.OFFLINE).into(mimage, new Callback() {
            @Override
            public void onSuccess() {
            }
            @Override
            public void onError(Exception e) {
                Picasso.get().load(image).into(mimage);
            }
        });

        MobileAds.initialize(this, initializationStatus -> {
        });
        ad1 = findViewById(R.id.adView);
        ad2 = findViewById(R.id.adView2);

        AdRequest adRequest = new AdRequest.Builder().build();
        ad1.loadAd(adRequest);
        ad2.loadAd(adRequest);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(LatestActivity2.this, MainActivity.class);
        startActivity(intent);
    }

}