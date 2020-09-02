package in.kaamkibaat.kaamkibaat.entertainment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.google.android.gms.ads.InterstitialAd;

import in.kaamkibaat.kaamkibaat.R;

public class EntertainmentActivity2 extends AppCompatActivity {

    TextView mtitle;
    ImageView mimage;
    TextView mcontent;
    TextView mcat;
    TextView mtitleTag;
    private String image;
    private InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entertainment2);

        getSupportActionBar().setTitle("Kaam Ki Baat - News");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mtitle = findViewById(R.id.textView_E_A2_1);
        mimage = findViewById(R.id.imageView_E_A2_1);
        mcontent = findViewById(R.id.textView_E_A2_2);
        mcat = findViewById(R.id.textView_E_A2_3);
        mtitleTag = findViewById(R.id.textView_E_A2_4);

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
        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(adRequest);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();

            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    finish();
                }
            });
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }
        super.onBackPressed();
    }
}

