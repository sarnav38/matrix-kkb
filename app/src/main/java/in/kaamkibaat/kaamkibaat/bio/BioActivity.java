package in.kaamkibaat.kaamkibaat.bio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import in.kaamkibaat.kaamkibaat.MainActivity;
import in.kaamkibaat.kaamkibaat.R;
import in.kaamkibaat.kaamkibaat.RecyclerDecoration;
import in.kaamkibaat.kaamkibaat.entertainment.EntertainmentActivity;
import in.kaamkibaat.kaamkibaat.member.Member;
import in.kaamkibaat.kaamkibaat.news.NewsActivity;
import in.kaamkibaat.kaamkibaat.news.NewsActivity2;
import in.kaamkibaat.kaamkibaat.politics.PoliticsActivity;
import in.kaamkibaat.kaamkibaat.viewholder.Viewholder;

public class BioActivity extends AppCompatActivity {

    private ActionBarDrawerToggle actionBarDrawerToggle;
    ProgressDialog pd;
    NavigationView navigationView;
    RecyclerView mRecyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    String mtitle,mcontent,mimage,mcat,mtitleTag;
    AdView ad1,ad2;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio);

        // ToolBar set
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Kaam Ki Baat - Biography");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
// Progress dialog set
        pd = new ProgressDialog(BioActivity.this);
        //Nav coding here
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(BioActivity.this, drawerLayout,R.string.drw_op,R.string.drw_cl);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        navigationView = findViewById(R.id.navigation_view);

        // firebase code
        mRecyclerView = findViewById(R.id.RV);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("KKB");
        reference.keepSynced(true);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });
        ad1 = findViewById(R.id.adView);
        ad2 = findViewById(R.id.adView2);

        AdRequest adRequest = new AdRequest.Builder().build();
        ad1.loadAd(adRequest);
        ad2.loadAd(adRequest);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(adRequest);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void UserMenuSelected(MenuItem item){
        if (item.getItemId() == R.id.nav_home){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.nav_enter){
            pd.show();
            pd.setContentView(R.layout.pd_lo);
            pd.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            Intent intent = new Intent(this, EntertainmentActivity.class);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.nav_politics){
            pd.show();
            pd.setContentView(R.layout.pd_lo);
            pd.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            Intent intent =new Intent(this, PoliticsActivity.class);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.nav_news){
            pd.show();
            pd.setContentView(R.layout.pd_lo);
            pd.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            Intent intent = new Intent(this,NewsActivity.class);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.nav_bio){
            pd.show();
            pd.setContentView(R.layout.pd_lo);
            pd.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            Intent intent = new Intent(this,BioActivity.class);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.nav_vid){
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/channel/UC3dzH7JgfIzm2na8QhIIscg"));
            try {
                BioActivity.this.startActivity(webIntent);
            } catch (ActivityNotFoundException ignored) {
            }
        }
        else if (item.getItemId() == R.id.nav_pod){
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/channel/UCXO9Pm-55cV34EShFbpsVQA"));
            try {
                BioActivity.this.startActivity(webIntent);
            } catch (ActivityNotFoundException ignored) {
            }
        }
        else if (item.getItemId() == R.id.nav_share){
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT,"open this app to study beutyfull post http:/play.google.com");
            intent.setType("text/plain");
            startActivity(Intent.createChooser(intent,"share via"));
            startActivity(intent);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(item -> {
            UserMenuSelected(item);
            return false;
        });

        String p = "bio";
        Query firebaseQuery = firebaseDatabase.getReference("KKB").orderByChild("cat").equalTo(p);
        FirebaseRecyclerOptions<Member> options =
                new FirebaseRecyclerOptions.Builder<Member>()
                        .setQuery(firebaseQuery, Member.class)
                        .build();


        FirebaseRecyclerAdapter<Member, Viewholder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Member, Viewholder>(options) {


                    @Override
                    protected void onBindViewHolder(@NonNull Viewholder viewHandler, int position, @NonNull Member member) {
                        viewHandler.Bsetdetails(getApplication(), member.getTitle(), member.getContent(), member.getImage_url(),member.getCat(),member.getTitleTag());

                        viewHandler.setOnClicklistener(new Viewholder.ClickListener() {

                            @Override
                            public void onItemclick(View view, int position) {
                                mtitle = getItem(position).getTitle();
                                mcontent = getItem(position).getContent();
                                mcat = getItem(position).getCat();
                                mtitleTag = getItem(position).getTitleTag();
                                mimage = getItem(position).getImage_url();

                                Intent intent = new Intent(BioActivity.this, BioActivity2.class);
                                intent.putExtra("title", mtitle);
                                intent.putExtra("content", mcontent);
                                intent.putExtra("cat", mcat);
                                intent.putExtra("titleTag", mtitleTag);
                                intent.putExtra("image", mimage);
                                startActivity(intent);
                            }
                            @Override
                            public void onItemLongclick(View view, int position) {
                            }
                        });
                    }
                    @NonNull
                    @Override
                    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.bioitem, parent, false);
                        return new Viewholder(view);
                    }
                };
        firebaseRecyclerAdapter.startListening();
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
        int sidePadding = getResources().getDimensionPixelSize(R.dimen.sidePadding);
        int topPadding = getResources().getDimensionPixelSize(R.dimen.topPadding);
        mRecyclerView.addItemDecoration(new RecyclerDecoration(sidePadding,topPadding));
    }

}