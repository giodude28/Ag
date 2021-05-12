package io.giodude.ag.Adapter;

import android.content.Context;
import android.graphics.drawable.Icon;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.giodude.ag.Model.Team;
import io.giodude.ag.R;

public class IconAdapter extends RecyclerView.Adapter<IconAdapter.ViewHolder> {
    Context context;
    private List<Team> data;

    public IconAdapter(Context context, List<Team> data) {
    this.context = context;
    this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View item = layoutInflater.inflate(R.layout.iconitem, parent, false);
        ViewHolder viewHolder = new ViewHolder(item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(data.get(position).getStrTeamBadge()).into(holder.img, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);
            }
            @Override
            public void onError(Exception e) {
                holder.img.setImageResource(R.drawable.ic_launcher_background);
                holder.progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        ProgressBar progressBar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.teamicon);
            progressBar = itemView.findViewById(R.id.progress_load_photo);
        }
    }
    public void updateList(List<Team> updatedList) {
        data = updatedList;
        notifyDataSetChanged();
    }
}
