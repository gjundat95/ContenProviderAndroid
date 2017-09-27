package com.jundat95.contactandroid.ui.music;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jundat95.contactandroid.R;
import com.jundat95.contactandroid.repository.local.model.MusicModel;
import com.jundat95.contactandroid.shared.util.TimeUtil;

import java.io.IOException;
import java.util.List;

/**
 * Created by tinhngo on 27/09/2017.
 */

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicHolder> {

    private Context context;
    private List<MusicModel> musicModels;
    private MediaPlayer mediaPlayer;
    private boolean isPlay = false;

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
        final MusicModel musicModel = musicModels.get(position);
        holder.tvTitle.setText(musicModel.getTitle());
        holder.tvArtist.setText(musicModel.getArtist());
        holder.tvTime.setText(new TimeUtil(musicModel.getDuration()).getTimeFromDuration());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Playing "+musicModel.getTitle(), Toast.LENGTH_SHORT).show();
                try {
                    stopPlaying();
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(musicModel.getPath());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void stopPlaying() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public int getItemCount() {
        return musicModels.size();
    }

    public static class MusicHolder extends RecyclerView.ViewHolder {

        private ImageView imgPhoto;
        private TextView tvTitle, tvArtist, tvTime;
        private ConstraintLayout layout;

        public MusicHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.imgPhoto);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvArtist = itemView.findViewById(R.id.tvArtist);
            tvTime = itemView.findViewById(R.id.tvTime);
            layout = itemView.findViewById(R.id.item_music);

        }
    }
}
