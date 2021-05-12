package io.giodude.ag.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TeamModel {

    @SerializedName("teams")
    @Expose
    private List<Team> teams;


    public List<Team> getTeams(){
        return teams;
    }
}
