package id.sch.smktelkom_mlg.privateassignment.xirpl323.cinemovie;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import id.sch.smktelkom_mlg.privateassignment.xirpl323.cinemovie.R;
import id.sch.smktelkom_mlg.privateassignment.xirpl323.cinemovie.adapter.filmAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl323.cinemovie.model.Result;
import id.sch.smktelkom_mlg.privateassignment.xirpl323.cinemovie.model.ResultResponse;
import id.sch.smktelkom_mlg.privateassignment.xirpl323.cinemovie.service.GsonGetRequest;
import id.sch.smktelkom_mlg.privateassignment.xirpl323.cinemovie.service.VolleySingleton;

/**
 * A simple {@link Fragment} subclass.
 */
public class NowPlayingFragment extends Fragment {
    ArrayList<Result> mlist = new ArrayList<>();
    filmAdapter filmAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_now_playing, container, false);

        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv_recycler_view);
        rv.setHasFixedSize(true);
        filmAdapter = new filmAdapter(this, mlist, getContext());
        rv.setAdapter(filmAdapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        downloadDataResource();

        return rootView;
    }

    private void downloadDataResource() {
        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=83e9bd45d01bdec860110180bf6d664b&language=en-US&page=1";

        GsonGetRequest<ResultResponse> myRequest = new GsonGetRequest<ResultResponse>
                (url, ResultResponse.class, null, new Response.Listener<ResultResponse>() {
                    @Override
                    public void onResponse(ResultResponse response) {
                        Log.d("FLOW", "onResponse: " + (new Gson().toJson(response)));
                        mlist.addAll(response.results);
                        filmAdapter.notifyDataSetChanged();
                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("FLOW", "onErrorResponse: ", error);
                    }
                });
        VolleySingleton.getInstance(this).addToRequestQueue(myRequest);
    }


}
