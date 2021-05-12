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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import io.giodude.ag.Adapter.TeamAdapter;
import io.giodude.ag.Model.Team;
import io.giodude.ag.R;
import io.giodude.ag.ViewModel.ViewModels;

@AndroidEntryPoint
public class TeamClassView extends Fragment implements AdapterOnClick {
    TextView name, taon, laro, tdesc, stadium, stadiumdesc;
    ImageView badge, stadiumimg;
    private ViewModels viewModel;
    private TeamAdapter teamAdapter;
    private List<Team> teamList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager rvLayout;
    private ProgressBar progressBar;
    View view;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_team_class_view, container, false);
        progressBar = view.findViewById(R.id.progress);
        observeData();
        return view;
    }

    private void observeData() {
        viewModel = new ViewModelProvider(this).get(ViewModels.class);
        viewModel.getTeams();
        viewModel.getTeamList().observe(getViewLifecycleOwner(), pokemons -> {
            if (pokemons.size() == 0) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.INVISIBLE);
                teamList.addAll(pokemons);
                initRecyclerView(pokemons);
                teamAdapter.updateList(pokemons);
            }

        });
    }

    private void initRecyclerView(List<Team> team) {
        recyclerView = view.findViewById(R.id.teamRV);
        rvLayout = new LinearLayoutManager((getActivity()));
        recyclerView.setLayoutManager(rvLayout);
        teamAdapter = new TeamAdapter(getActivity(), team, this);
        recyclerView.setAdapter(teamAdapter);
    }

    @Override
    public void onAdapterClick(int position) {
        final Dialog myDialog;
        myDialog = new Dialog(getActivity());
        myDialog.setContentView(R.layout.teamdetails);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        name = myDialog.findViewById(R.id.name);
        taon = myDialog.findViewById(R.id.year);
        laro = myDialog.findViewById(R.id.sport);
        stadiumdesc = myDialog.findViewById(R.id.stadiumdesc);
        tdesc = myDialog.findViewById(R.id.teamdesc);
        stadiumimg = myDialog.findViewById(R.id.Stadiumimg);
        badge = myDialog.findViewById(R.id.jerseyimg);
        stadium = myDialog.findViewById(R.id.stadiumtitle);
        name.setText(teamList.get(position).getStrTeam());
        taon.setText(teamList.get(position).getIntFormedYear());
        tdesc.setText(teamList.get(position).getStrDescriptionEN());
        laro.setText(teamList.get(position).getStrSport());
        stadiumdesc.setText(teamList.get(position).getStrStadiumDescription());
        stadium.setText(teamList.get(position).getStrStadium());
        Picasso.get().load(teamList.get(position).getStrTeamBadge()).into(badge);
        Picasso.get().load(teamList.get(position).getStrStadiumThumb()).into(stadiumimg);
        myDialog.show();

    }
}