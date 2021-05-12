package io.giodude.ag.ViewModel;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.giodude.ag.Model.Event;
import io.giodude.ag.Model.MatchesModel;
import io.giodude.ag.Model.Team;
import io.giodude.ag.Model.TeamModel;
import io.giodude.ag.Network.Repository;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ViewModels extends ViewModel {


    private static final String TAG = "ViewModel";
    private MutableLiveData<List<Team>> teamList = new MutableLiveData<>();
    private MutableLiveData<List<Event>> eventList = new MutableLiveData<>();


    private final CompositeDisposable disposables = new CompositeDisposable();
    private Repository repository;

    @ViewModelInject
    public ViewModels(Repository repository) {
        this.repository = repository;
    }

    public LiveData<List<Team>> getTeamList() {
        return teamList;
    }


    public LiveData<List<Event>> getEventList() {
        return eventList;
    }


    public void getTeams() {
        disposables.add(repository.getTeam()
                .subscribeOn(Schedulers.newThread())
                .map(new Function<TeamModel, List<Team>>() {
                    public List<Team> apply(TeamModel teamResponse) throws Throwable {
                        List<Team> list = teamResponse.getTeams();
                        return list;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> teamList.setValue(result),
                        error -> Log.e(TAG, "getPokemons: " + error.getMessage())));

    }

    public void getMatches() {
        disposables.add(repository.getPast()
                .subscribeOn(Schedulers.newThread())
                .map(new Function<MatchesModel, List<Event>>() {
                    public List<Event> apply(MatchesModel teamResponse) throws Throwable {
                        List<Event> list = teamResponse.getEvents();
                        return list;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> eventList.setValue(result),
                        error -> System.out.println(TAG + "getTable: " + error.getMessage())));
    }

}
