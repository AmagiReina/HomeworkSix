package com.example.homeworksix.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.homeworksix.R;
import com.example.homeworksix.entity.Album;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder> {

    private List<Album> albums;
    private Context context;
    private static int k = 0;

    public AlbumsAdapter(List<Album> albums, Context context) {
        this.albums = albums;
        this.context = context;
    }

    @NonNull
    @Override
    public AlbumsAdapter.AlbumsViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                             int viewType) {
        View view = LayoutInflater.from(
                parent.getContext())
                .inflate(R.layout.item_albums, parent, false);

        return new AlbumsViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull AlbumsAdapter.AlbumsViewHolder holder,
                                 int position) {
        holder.tvSinger.setText(getAlbum(position).getLeadSinger());
        holder.tvDate.setText(getAlbum(position).getDate().toString());
        holder.tvTitle.setText(getAlbum(position).getTitle());

        if (getAlbum(position).getPathImage() != null) {
            holder.ivImg.setImageURI(Uri.parse(getAlbum(position).getPathImage()));
        }
        AssetManager manager = this.context.getAssets();
        List<String> list = setStreamsList(manager);
        InputStream[] inputStreams = setInputStream(manager, list);

        holder.cardView.setBackground(
                new BitmapDrawable(BitmapFactory.
                        decodeStream(inputStreams[k++%list.size()]))
        );
    }

    private Album getAlbum(int position) {
        return albums.get(position);
    }

    @Override
    public int getItemCount() {
        if (albums == null) return 0;
        else return albums.size();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<String> setStreamsList(AssetManager manager) {
        String[] stringList = null;
        try {
            stringList = manager.list("");
        } catch (IOException e) {
            Log.e("AlbumsAdapterStringList", "exception", e);
        }
        List<String> streamList = new ArrayList<>();
        Arrays.stream(stringList).filter(s -> s.contains(".png")
                || s.contains(".jpg") || s.contains(".jpeg"))
                .forEach(s -> streamList.add(s));

        return streamList;
    }

    private InputStream[] setInputStream(AssetManager manager,
                                    List<String> streamList) {
        InputStream[] streams = new InputStream[streamList.size()];
        for (int i = 0; i < streamList.size(); i++) {
            try {
                streams[i] = manager.open(streamList.get(i));
            } catch (IOException e) {
                Log.e("AlbumsAdapterManager", "exception",
                        e);
            }
        }
        return streams;
    }

    public class AlbumsViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView ivImg;
        TextView tvTitle;
        TextView tvSinger;
        TextView tvDate;

        private AlbumsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvSinger = itemView.findViewById(R.id.tvSinger);
            tvDate = itemView.findViewById(R.id.tvDate);
            ivImg = itemView.findViewById(R.id.ivImg);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}
