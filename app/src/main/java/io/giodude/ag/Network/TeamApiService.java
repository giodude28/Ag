package io.giodude.ag.Network;

import io.giodude.ag.Model.TeamModel;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TeamApiService {

    @GET("search_all_teams.php?l=Chinese Super League")
    Observable<TeamModel> getTeam();
}
