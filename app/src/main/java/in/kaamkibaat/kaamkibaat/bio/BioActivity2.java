package in.kaamkibaat.kaamkibaat.bio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import in.kaamkibaat.kaamkibaat.R;

public class BioActivity2 extends AppCompatActivity {

    TextView mtitle;
    ImageView mimage;
    TextView mcontent;
    TextView mcat;
    TextView mtitleTag;
    private String image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio2);

        getSupportActionBar().setTitle("Kaam Ki Baat - Biography");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mtitle = findViewById(R.id.textView_B_A2_1);
        mimage = findViewById(R.id.imageView_B_A2_1);
        mcontent = findViewById(R.id.textView_B_A2_2);
        mcat = findViewById(R.id.textView_B_A2_3);
        mtitleTag = findViewById(R.id.textView_B_A2_4);

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
    }
}