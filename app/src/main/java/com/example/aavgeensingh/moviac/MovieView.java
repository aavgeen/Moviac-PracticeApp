package com.example.aavgeensingh.moviac;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class MovieView extends AppCompatActivity {
    TextView originalTitle;
    TextView originalLan;
    TextView isAdult;
    TextView overview;
    TextView genres;
    TextView votes;
    TextView ratings;
    ImageView poster;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_view);
        Intent i=getIntent();
        movies m= (movies) i.getSerializableExtra("MovieDetail");
        originalTitle=(TextView)findViewById(R.id.originalTitle);
        originalLan=(TextView)findViewById(R.id.originalLan);
        isAdult=(TextView)findViewById(R.id.isAdult);
        genres=(TextView)findViewById(R.id.genres);
        votes=(TextView)findViewById(R.id.votes);
        ratings=(TextView)findViewById(R.id.ratings);
        poster=(ImageView)findViewById(R.id.poster);
        overview=(TextView)findViewById(R.id.overview);
        originalTitle.setText(m.getOriginalTitle());
        originalLan.setText(m.getOriginalLanguage());
        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();  // deprecated
        int height = display.getHeight();
//        Picasso.with(this)
//                .load("http://image.tmdb.org/t/p/w342"+m.getPosterPath())
//                .placeholder(R.drawable.loadbar)
//                .resize(width+120, height+130)
//                .error(R.drawable.ic_date_range_black_24dp)
//                .into(poster);
        Picasso.with(this)
                .load("http://image.tmdb.org/t/p/w185"+m.getPosterPath())
                .placeholder(R.drawable.loadbar)
                .resize(width+120, height+130)
                .error(R.drawable.ic_date_range_black_24dp)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded (final Bitmap bitmap, Picasso.LoadedFrom from){
                /* Save the bitmap or do something with it here */
                        Bitmap blurredBitmap = BlurBuilder.blur(getApplicationContext(), bitmap );
                        //Set it in the ImageView
                        System.out.println("Going to set image.");
                        poster.setImageBitmap(blurredBitmap);
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {}
                });
        System.out.println("HSVhdhjshbvjhbvjrbvbsh  "+width+" X "+height);
        isAdult.setText(m.isAdult()?"Adult":"");
        overview.setText(m.getOverview());
        //genres.setText(m.getGe);
        votes.setText(m.getVoteCount()+" votes");
        ratings.setText(m.getVoteAverage()+"");
    }
}
