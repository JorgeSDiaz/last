package com.ieti.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    private List<Movie> movies;

    public MoviesAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_element, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.title.setText(movie.getTitle());
        holder.year.setText(movie.getYear());

        Picasso.get().load(movie.getPoster()).into(holder.poster);
        // Use Glide or another library if preferred
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }



    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView year;
        public ImageView poster;

        public MovieViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            year = itemView.findViewById(R.id.year);
            poster = itemView.findViewById(R.id.movie_poster);
        }
    }
}
