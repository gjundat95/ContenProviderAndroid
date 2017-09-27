package com.jundat95.contactandroid.ui.music;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jundat95.contactandroid.R;
import com.jundat95.contactandroid.repository.local.model.MusicModel;
import com.jundat95.contactandroid.shared.util.TimeUtil;

import java.util.List;

/**
 * Created by tinhngo on 27/09/2017.
 */

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicHolder> {

    private Context context;
    private List<MusicModel> musicModels;

    public MusicAdapter(Context context, List<MusicModel> musicModels) {
        this.context = context;
        this.musicModels = musicModels;
    }

    @Override
    public MusicHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_music, parent, false);
        return new MusicHolder(view);
    }

    @Override
    public void onBindViewHolder(MusicHolder holder, int position) {
        MusicModel musicModel = musicModels.get(position);
        holder.tvTitle.setText(musicModel.getTitle());
        holder.tvArtist.setText(musicModel.getArtist());
        holder.tvTime.setText(new TimeUtil(musicModel.getDuration()).getTimeFromDuration());
    }

    @Override
    public int getItemCount() {
        return musicModels.size();
    }

    public static class MusicHolder extends RecyclerView.ViewHolder {

        private ImageView imgPhoto;
        private TextView tvTitle, tvArtist, tvTime;

        public MusicHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.imgPhoto);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvArtist = itemView.findViewById(R.id.tvArtist);
            tvTime = itemView.findViewById(R.id.tvTime);
        }
    }
}
