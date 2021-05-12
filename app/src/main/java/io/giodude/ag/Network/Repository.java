package io.giodude.ag.Network;

import javax.inject.Inject;

import io.giodude.ag.Model.MatchesModel;
import io.giodude.ag.Model.TeamModel;
import io.reactivex.rxjava3.core.Observable;

public class Repository {

    private TeamApiService apiService;
    private MatchesApiService matchesApiService;

    @Inject
    public Repository(TeamApiService apiService,MatchesApiService matchesApiService){
        this.matchesApiService = matchesApiService;
        this.apiService = apiService;
//        this.tableApiService = tableApiService;
//        this.liveApiService = liveApiService;
    }

    public Observable<TeamModel> getTeam(){
        return apiService.getTeam();
    }

    public Observable<MatchesModel> getPast(){
        return matchesApiService.getPast();
    }
}
