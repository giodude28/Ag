package io.giodude.ag.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.giodude.ag.Model.Team;
import io.giodude.ag.R;
import io.giodude.ag.View.AdapterOnClick;
import io.giodude.ag.View.TeamClassView;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {
    Context context;
    private List<Team> data;
    private AdapterOnClick adapterOnClick;

    public TeamAdapter(Context context, List<Team> data, AdapterOnClick adapterOnClick) {
        this.context = context;
        this.data = data;
        this.adapterOnClick = adapterOnClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View item = layoutInflater.inflate(R.layout.teamitem, parent, false);
        ViewHolder viewHolder = new ViewHolder(item, adapterOnClick);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(data.get(position).getStrTeam());

        if (data.get(position).getStrTeamJersey() == null){
            Picasso.get().load(R.drawable.convert).into(holder.jer);
        }else {
            Picasso.get().load(data.get(position).getStrTeamJersey()).into(holder.jer, new Callback() {
                @Override
                public void onSuccess() {
                    holder.progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onError(Exception e) {
                    holder.jer.setImageResource(R.drawable.ic_launcher_background);
                    holder.progressBar.setVisibility(View.GONE);
                }
            });
        }
        Picasso.get().load(data.get(position).getStrTeamBadge()).into(holder.image, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar1.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {
                holder.image.setImageResource(R.drawable.ic_launcher_background);
                holder.progressBar1.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image;
        ImageView jer;
        TextView name;
        ProgressBar progressBar,progressBar1;
        AdapterOnClick adapterOnClick;
        public ViewHolder(@NonNull View itemView, AdapterOnClick adapterOnClick) {
            super(itemView);

            jer = itemView.findViewById(R.id.teamjersey);
            image = itemView.findViewById(R.id.teamimg);
            name = itemView.findViewById(R.id.teamname);
            progressBar = itemView.findViewById(R.id.progress_load_photo);
            progressBar1 = itemView.findViewById(R.id.progress_load_team);
            this.adapterOnClick = adapterOnClick;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            adapterOnClick.onAdapterClick(getAdapterPosition());
        }
    }

    public void updateList(List<Team> updatedList) {
        data = updatedList;
        notifyDataSetChanged();
    }
}
