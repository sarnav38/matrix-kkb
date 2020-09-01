package in.kaamkibaat.kaamkibaat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;

import in.kaamkibaat.kaamkibaat.bio.BioActivity;
import in.kaamkibaat.kaamkibaat.entertainment.EntertainmentActivity;
import in.kaamkibaat.kaamkibaat.latest.LatestActivity2;
import in.kaamkibaat.kaamkibaat.member.Member;
import in.kaamkibaat.kaamkibaat.news.NewsActivity;
import in.kaamkibaat.kaamkibaat.politics.PoliticsActivity;
import in.kaamkibaat.kaamkibaat.search.SearchActivity;
import in.kaamkibaat.kaamkibaat.viewholder.Viewholder;

public class MainActivity extends AppCompatActivity {
    private ActionBarDrawerToggle actionBarDrawerToggle;
    CardView btn_P,btn_N,btn_B,btn_V,btn_A,btn_E;
    ProgressDialog pd;
    NavigationView navigationView;
    ImageView searchBtn;
    RecyclerView.LayoutManager manager;
    RecyclerView mRecyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    String mtitle,mcontent,mimage,mcat,mtitleTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// ToolBar set
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
// Progress dialog set
        pd = new ProgressDialog(MainActivity.this);
        //Nav coding here
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout,R.string.drw_op,R.string.drw_cl);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        navigationView = findViewById(R.id.navigation_view);
// Linking with layout
        btn_P =findViewById(R.id.btn_P);
        btn_N =findViewById(R.id.btn_N);
        btn_B =findViewById(R.id.btn_B);
        btn_V =findViewById(R.id.btn_V);
        btn_A =findViewById(R.id.btn_A);
        btn_E =findViewById(R.id.btn_E);
        // Search button
        searchBtn = findViewById(R.id.searchBtn);

        mRecyclerView = findViewById(R.id.RV);
        mRecyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,true);
        mRecyclerView.setLayoutManager(manager);
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("KKB");
        reference.keepSynced(true);

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .setNotificationOpenedHandler(new NotificationOpenedHandler(getApplicationContext()))
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
    }
    public class NotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {

        private final Context context;

        public NotificationOpenedHandler(Context context) {
            this.context = context;
        }
        @Override
        public void notificationOpened(OSNotificationOpenResult result) {
            JSONObject data = result.notification.payload.additionalData;
            String message;
            if (data != null) {
                message = data.optString("message", null);
                if (message != null && message.equals("pol")) {
                    Log.i("OneSignal", "customkey set with value: " + message);
                    Intent intent = new Intent(context, PoliticsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                } else if (message != null && message.equals("news")) {
                    Intent intent = new Intent(context, NewsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                } else if (message != null && message.equals("bio")) {
                    Intent intent = new Intent(context, BioActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
                else if (message != null && message.equals("ent")) {
                    Intent intent = new Intent(context, EntertainmentActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
                else if (message != null && message.equals("video")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW,  Uri.parse("https://www.youtube.com/channel/UC3dzH7JgfIzm2na8QhIIscg"));
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
                else if (message != null && message.equals("pod")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW,  Uri.parse("https://www.youtube.com/channel/UCXO9Pm-55cV34EShFbpsVQA"));
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }

            }
        }
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
            Intent intent = new Intent(this,MainActivity.class);
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
            Intent intent = new Intent(this, NewsActivity.class);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.nav_bio){
            pd.show();
            pd.setContentView(R.layout.pd_lo);
            pd.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            Intent intent = new Intent(this, BioActivity.class);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.nav_vid){
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/channel/UC3dzH7JgfIzm2na8QhIIscg"));
            try {
                MainActivity.this.startActivity(webIntent);
            } catch (ActivityNotFoundException ignored) {
            }
        }
        else if (item.getItemId() == R.id.nav_pod){
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/channel/UCXO9Pm-55cV34EShFbpsVQA"));
            try {
                MainActivity.this.startActivity(webIntent);
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

        else if (item.getItemId() == R.id.exit){
//            System.exit(1);
            this.finishAffinity();
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
        btn_V.setOnClickListener(view -> {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/channel/UC3dzH7JgfIzm2na8QhIIscg"));
            try {
                MainActivity.this.startActivity(webIntent);
            } catch (ActivityNotFoundException ignored) {
            }
        });
        btn_A.setOnClickListener(view -> {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/channel/UCXO9Pm-55cV34EShFbpsVQA"));
            try {
                MainActivity.this.startActivity(webIntent);
            } catch (ActivityNotFoundException ignored) {
            }
        });
        // render to politics pages.
        btn_P.setOnClickListener(view -> {
            pd.show();
            pd.setContentView(R.layout.pd_lo);
            pd.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            Intent Politics_page = new Intent(MainActivity.this, PoliticsActivity.class);
            startActivity(Politics_page);
        });
        // render to News pages.
        btn_N.setOnClickListener(view -> {
            pd.show();
            pd.setContentView(R.layout.pd_lo);
            pd.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            Intent News_page = new Intent(MainActivity.this, NewsActivity.class);
            startActivity(News_page);

        });
        // render to Biography pages.
        btn_B.setOnClickListener(view -> {
            pd.show();
            pd.setContentView(R.layout.pd_lo);
            pd.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            Intent Bio_page = new Intent(MainActivity.this, BioActivity.class);
            startActivity(Bio_page);
        });
        btn_E.setOnClickListener(view -> {
            pd.show();
            pd.setContentView(R.layout.pd_lo);
            pd.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            Intent Ent_page = new Intent(MainActivity.this, EntertainmentActivity.class);
            startActivity(Ent_page);
        });

        searchBtn.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(i);
        });

        Query firebaseQuery = firebaseDatabase.getReference("KKB").limitToFirst(2);
        FirebaseRecyclerOptions<Member> options =
                new FirebaseRecyclerOptions.Builder<Member>()
                        .setQuery(firebaseQuery, Member.class)
                        .build();


        FirebaseRecyclerAdapter<Member, Viewholder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Member, Viewholder>(options) {


                    @Override
                    protected void onBindViewHolder(@NonNull Viewholder viewHandler, int position, @NonNull Member member) {
                        viewHandler.Lsetdetails(getApplication(), member.getTitle(), member.getContent(), member.getImage_url(),member.getCat(),member.getTitleTag());

                        viewHandler.setOnClicklistener(new Viewholder.ClickListener() {
                            @Override
                            public void onItemclick(View view, int position) {
                                mtitle = getItem(position).getTitle();
                                mcontent = getItem(position).getContent();
                                mcat = getItem(position).getCat();
                                mtitleTag = getItem(position).getTitleTag();
                                mimage = getItem(position).getImage_url();

                                Intent intent = new Intent(MainActivity.this, LatestActivity2.class);
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
                                .inflate(R.layout.latestitem, parent, false);
                        return new Viewholder(view);
                    }
                };
        firebaseRecyclerAdapter.startListening();
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        pd.dismiss();
    }
}