package io.giodude.ag.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import io.giodude.ag.Adapter.IconAdapter;
import io.giodude.ag.Adapter.TeamAdapter;
import io.giodude.ag.Model.Team;
import io.giodude.ag.R;
import io.giodude.ag.ViewModel.ViewModels;

@AndroidEntryPoint
public class HomeClassView extends Fragment {
    private List<Team> teams = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager rvLayout;
    private IconAdapter iconAdapter;
    private ViewModels viewModels;
    public ProgressBar progressBar;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_home_class_view, container, false);
        progressBar = view.findViewById(R.id.progress);
        observeData();
        return view;
    }


    private void observeData() {
        viewModels = new ViewModelProvider(this).get(ViewModels.class);
        viewModels.getTeams();
        viewModels.getTeamList().observe(getViewLifecycleOwner(), pokemons -> {
            if (pokemons.size() == 0) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.INVISIBLE);
                initRecyclerView(pokemons);
                iconAdapter.updateList(pokemons);
            }

        });
    }

    private void initRecyclerView(List<Team> team) {
        recyclerView = view.findViewById(R.id.iconRecyclerview);
        rvLayout = new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false);
        recyclerView.setLayoutManager(rvLayout);
        iconAdapter = new IconAdapter(getActivity(), team);
        recyclerView.setAdapter(iconAdapter);
    }
}