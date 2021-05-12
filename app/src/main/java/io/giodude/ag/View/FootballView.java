package io.giodude.ag.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import dagger.hilt.android.AndroidEntryPoint;
import io.giodude.ag.R;
import io.giodude.ag.ViewModel.ViewModels;

@AndroidEntryPoint
public class FootballView extends Fragment implements View.OnClickListener {
    TextView titles,descs,rules,score,object,player;

    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_football_view, container, false);
        rules = view.findViewById(R.id.rulesbtn);
        rules.setOnClickListener(this);
        score = view.findViewById(R.id.scoringbtn);
        score.setOnClickListener(this);
        object = view.findViewById(R.id.objectbtn);
        object.setOnClickListener(this);
        player = view.findViewById(R.id.playerbtn);
        player.setOnClickListener(this);
        return view;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.rulesbtn:
                final Dialog myDialog;
                myDialog = new Dialog(getActivity());
                myDialog.setContentView(R.layout.footbetails);
                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                titles = myDialog.findViewById(R.id.title);
                descs = myDialog.findViewById(R.id.desc);
                titles.setText(R.string.rules);
                descs.setText(R.string.rulesdesc);
                myDialog.show();
                break;
            case R.id.scoringbtn:
                final Dialog myDialog1;
                myDialog1 = new Dialog(getActivity());
                myDialog1.setContentView(R.layout.footbetails);
                myDialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                titles = myDialog1.findViewById(R.id.title);
                descs = myDialog1.findViewById(R.id.desc);
                titles.setText(R.string.scoring);
                descs.setText(R.string.scoringdesc);
                myDialog1.show();
                break;
            case R.id.objectbtn:
                final Dialog myDialog2;
                myDialog2 = new Dialog(getActivity());
                myDialog2.setContentView(R.layout.footbetails);
                myDialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                titles = myDialog2.findViewById(R.id.title);
                descs = myDialog2.findViewById(R.id.desc);
                titles.setText(R.string.objective);
                descs.setText(R.string.objectivedesc);
                myDialog2.show();
                break;
            case R.id.playerbtn:
                final Dialog myDialog3;
                myDialog3 = new Dialog(getActivity());
                myDialog3.setContentView(R.layout.footbetails);
                myDialog3.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                titles = myDialog3.findViewById(R.id.title);
                descs = myDialog3.findViewById(R.id.desc);
                titles.setText(R.string.playersequip);
                descs.setText(R.string.playersequipdesc);
                myDialog3.show();
                break;
        }
    }
}