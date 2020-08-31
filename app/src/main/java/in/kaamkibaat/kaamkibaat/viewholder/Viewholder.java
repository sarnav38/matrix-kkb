package in.kaamkibaat.kaamkibaat.viewholder;

import android.app.Application;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import in.kaamkibaat.kaamkibaat.R;

public class Viewholder extends RecyclerView.ViewHolder {
    View view;

    public Viewholder(@NonNull View itemView) {
        super(itemView);
        view =itemView;

        itemView.setOnClickListener(view -> mClicklistener.onItemclick(view,getAdapterPosition()));
        itemView.setOnLongClickListener(view -> {
            mClicklistener.onItemLongclick(view,getAdapterPosition());
            return false;
        });
    }
// class for news
    public void Nsetdetails(Application application, String title, String content, String image_url, String cat, String titleTag) {
        TextView mtitle = view.findViewById(R.id.textView_N_2);
        TextView mcontent = view.findViewById(R.id.textView_N_3);
        TextView mcat = view.findViewById(R.id.textView_N_4);
        TextView mtitleTag = view.findViewById(R.id.textView_N_5);
        ImageView mimage = view.findViewById(R.id.imageView_N);

        mtitle.setText(Html.fromHtml(title));
        mcontent.setText(content);
        mcat.setText(cat);
        mtitleTag.setText(titleTag);
        Picasso.get().load(image_url).networkPolicy(NetworkPolicy.OFFLINE).into(mimage, new Callback() {
            @Override
            public void onSuccess() {
//
            }
            //
            @Override
            public void onError(Exception e) {
                Picasso.get().load(image_url).into(mimage);
            }
        });
    }
// class for entertainment
    public void Esetdetails(Application application, String title, String content, String image_url, String cat, String titleTag) {
        TextView mtitle = view.findViewById(R.id.textView_E_2);
        TextView mcontent = view.findViewById(R.id.textView_E_3);
        TextView mcat = view.findViewById(R.id.textView_E_4);
        TextView mtitleTag = view.findViewById(R.id.textView_E_5);
        ImageView mimage = view.findViewById(R.id.imageView_E);

        mtitle.setText(Html.fromHtml(title));
        mcontent.setText(content);
        mcat.setText(cat);
        mtitleTag.setText(titleTag);
        Picasso.get().load(image_url).networkPolicy(NetworkPolicy.OFFLINE).into(mimage, new Callback() {
            @Override
            public void onSuccess() {
//
            }
            //
            @Override
            public void onError(Exception e) {
                Picasso.get().load(image_url).into(mimage);
            }
        });
    }
    // class for Biography
    public void Bsetdetails(Application application, String title, String content, String image_url, String cat, String titleTag) {
        TextView mtitle = view.findViewById(R.id.textView_B_2);
        TextView mcontent = view.findViewById(R.id.textView_B_3);
        TextView mcat = view.findViewById(R.id.textView_B_4);
        TextView mtitleTag = view.findViewById(R.id.textView_B_5);
        ImageView mimage = view.findViewById(R.id.imageView_B);

        mtitle.setText(Html.fromHtml(title));
        mcontent.setText(content);
        mcat.setText(cat);
        mtitleTag.setText(titleTag);
        Picasso.get().load(image_url).networkPolicy(NetworkPolicy.OFFLINE).into(mimage, new Callback() {
            @Override
            public void onSuccess() {
//
            }
            //
            @Override
            public void onError(Exception e) {
                Picasso.get().load(image_url).into(mimage);
            }
        });
    }
    public void Psetdetails(Application application, String title, String content, String image_url, String cat, String titleTag) {
        TextView mtitle = view.findViewById(R.id.textView_P_2);
        TextView mcontent = view.findViewById(R.id.textView_P_3);
        TextView mcat = view.findViewById(R.id.textView_P_4);
        TextView mtitleTag = view.findViewById(R.id.textView_P_5);
        ImageView mimage = view.findViewById(R.id.imageView_P);

        mtitle.setText(Html.fromHtml(title));
        mcontent.setText(content);
        mcat.setText(cat);
        mtitleTag.setText(titleTag);
        Picasso.get().load(image_url).networkPolicy(NetworkPolicy.OFFLINE).into(mimage, new Callback() {
            @Override
            public void onSuccess() {
//
            }
            //
            @Override
            public void onError(Exception e) {
                Picasso.get().load(image_url).into(mimage);
            }
        });
    }
    public void Ssetdetails(Application application, String title, String content, String image_url, String cat, String titleTag) {
        TextView mtitle = view.findViewById(R.id.textView_S_2);
        TextView mcontent = view.findViewById(R.id.textView_S_3);
        TextView mcat = view.findViewById(R.id.textView_S_4);
        TextView mtitleTag = view.findViewById(R.id.textView_S_5);
        ImageView mimage = view.findViewById(R.id.imageView_S);

        mtitle.setText(Html.fromHtml(title));
        mcontent.setText(content);
        mcat.setText(cat);
        mtitleTag.setText(titleTag);
        Picasso.get().load(image_url).networkPolicy(NetworkPolicy.OFFLINE).into(mimage, new Callback() {
            @Override
            public void onSuccess() {
//
            }
            //
            @Override
            public void onError(Exception e) {
                Picasso.get().load(image_url).into(mimage);
            }
        });
    }
    public void Lsetdetails(Application application, String title, String content, String image_url, String cat, String titleTag) {
        TextView mtitle = view.findViewById(R.id.textView_L_2);
        TextView mcontent = view.findViewById(R.id.textView_L_3);
        TextView mcat = view.findViewById(R.id.textView_L_4);
        TextView mtitleTag = view.findViewById(R.id.textView_L_5);
        ImageView mimage = view.findViewById(R.id.imageView_L);

        mtitle.setText(Html.fromHtml(title));
        mcontent.setText(content);
        mcat.setText(cat);
        mtitleTag.setText(titleTag);
        Picasso.get().load(image_url).networkPolicy(NetworkPolicy.OFFLINE).into(mimage, new Callback() {
            @Override
            public void onSuccess() {
//
            }
            //
            @Override
            public void onError(Exception e) {
                Picasso.get().load(image_url).into(mimage);
            }
        });
    }

    private Viewholder.ClickListener mClicklistener;
    public interface  ClickListener{
        void onItemclick(View view, int position);
        void onItemLongclick(View view, int position);

    }
    public  void setOnClicklistener(Viewholder.ClickListener clicklistener){
        mClicklistener = clicklistener;
    }
}
