package io.giodude.ag.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import io.giodude.ag.Adapter.MatchesAdapter;
import io.giodude.ag.Model.Event;
import io.giodude.ag.R;
import io.giodude.ag.ViewModel.ViewModels;
@AndroidEntryPoint
public class MatchesClassView extends Fragment implements AdapterOnClick{

    private List<Event> eventModel = new ArrayList<>();
    private static RecyclerView recyclerView;
    private RecyclerView.LayoutManager rvLayout;
    private MatchesAdapter matchesAdapter;
    private ViewModels viewModel;
    private ProgressBar progressBar;
    private TextView h,a,hs,as,d,s,w,ve;

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_matches_class_view, container, false);
        progressBar = view.findViewById(R.id.progress);
        observeData();
        return view;
    }

    private void observeData() {
        viewModel = new ViewModelProvider(this).get(ViewModels.class);
        viewModel.getMatches();
        viewModel.getEventList().observe(getViewLifecycleOwner(), pokemons -> {
            if (pokemons.size() == 0) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.INVISIBLE);
                eventModel.addAll(pokemons);
                initRecyclerView(pokemons);
                matchesAdapter.updateList(pokemons);
            }

        });
    }

    private void initRecyclerView (List < Event > team) {
        recyclerView = view.findViewById(R.id.matchesRV);
        rvLayout = new LinearLayoutManager((getActivity()));
        recyclerView.setLayoutManager(rvLayout);
        matchesAdapter = new MatchesAdapter(getActivity(), team,this);
        recyclerView.setAdapter(matchesAdapter);
    }

    @Override
    public void onAdapterClick(int position) {
        final Dialog myDialog;
        myDialog = new Dialog(getActivity());
        myDialog.setContentView(R.layout.matchesdetails);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        h = myDialog.findViewById(R.id.home);
        a = myDialog.findViewById(R.id.away);
        hs = myDialog.findViewById(R.id.homescore);
        as = myDialog.findViewById(R.id.awayscore);
        d = myDialog.findViewById(R.id.date);
        s = myDialog.findViewById(R.id.status);
        ve = myDialog.findViewById(R.id.venue);
        h.setText(eventModel.get(position).getStrHomeTeam());
        a.setText(eventModel.get(position).getStrAwayTeam());
        hs.setText(eventModel.get(position).getIntHomeScore());
        as.setText(eventModel.get(position).getIntAwayScore());
        d.setText(eventModel.get(position).getDateEvent());
        s.setText(eventModel.get(position).getStrStatus());
        ve.setText(eventModel.get(position).getStrVenue());
        myDialog.show();
    }
}