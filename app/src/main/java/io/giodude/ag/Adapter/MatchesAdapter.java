package io.giodude.ag.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.giodude.ag.Model.Event;
import io.giodude.ag.R;
import io.giodude.ag.View.AdapterOnClick;
import io.giodude.ag.View.MatchesClassView;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.ViewHolder> {
    Context context;
    private List<Event> data;
    private AdapterOnClick adapterOnClick;

    public MatchesAdapter(Context context, List<Event> data,AdapterOnClick adapterOnClick) {
        this.context = context;
        this.data = data;
        this.adapterOnClick = adapterOnClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.matchesitem, parent, false);
        MatchesAdapter.ViewHolder viewHolder = new MatchesAdapter.ViewHolder(v,adapterOnClick);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.home.setText(data.get(position).getStrHomeTeam());
        holder.away.setText(data.get(position).getStrAwayTeam());
        holder.homescore.setText(data.get(position).getIntHomeScore().toString());
        holder.awayscore.setText(data.get(position).getIntAwayScore().toString());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView home;
        public TextView away;
        public TextView homescore;
        public TextView awayscore;
        AdapterOnClick adapterOnClick;
        public ViewHolder(@NonNull View itemView, AdapterOnClick adapterOnClick) {
            super(itemView);
            home = (TextView) itemView.findViewById(R.id.home);
            awayscore = (TextView) itemView.findViewById(R.id.awayscore);
            homescore = (TextView) itemView.findViewById(R.id.homescore);
            away = (TextView) itemView.findViewById(R.id.away);

            this.adapterOnClick = adapterOnClick;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            adapterOnClick.onAdapterClick(getAdapterPosition());
        }
    }

    public void updateList(List<Event> updatedList) {
        data = updatedList;
        notifyDataSetChanged();
    }
}
