package io.giodude.ag.Network;

import io.giodude.ag.Model.MatchesModel;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MatchesApiService {
    @GET("eventspastleague.php?id=4359")
    Observable<MatchesModel> getPast();
}
