package tn.mdevtunisia.sample.prisonapp.fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import tn.mdevtunisia.sample.prisonapp.R;
import tn.mdevtunisia.sample.prisonapp.adapters.PrisonerAdapter;
import tn.mdevtunisia.sample.prisonapp.helper.MyHelper;
import tn.mdevtunisia.sample.prisonapp.listeners.RecyclerItemClickListener;
import tn.mdevtunisia.sample.prisonapp.models.Prisoner;
import tn.mdevtunisia.sample.prisonapp.models.response.MyResponse;

public class PrisonerListFragment extends Fragment implements PrisonerAdapter.OnItemClickListener{


    private RecyclerView mRecyclerView;
    private PrisonerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<Prisoner> prisoners;
    private Prisoner prisoner;

    boolean mResult = false;

    private MyResponse.Prisoner prisonerResonse;

    public final static String TAG = "MainActivity";

    private ProgressDialog pDialog;

    public PrisonerListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_prisoner_list, container, false);

        initRecyclerView(view);


        new listUsersAsyncTask().execute();


        return view;
    }


    private void initRecyclerView(View view) {

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_list);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext().getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                        Log.e("adnen", "onItemClick position ="+position);

                        PrisonerDetailFragment prisonerDetailFragment = PrisonerDetailFragment.newInstance(prisoners.get(position));
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager
                                .beginTransaction();
                        fragmentTransaction.replace(R.id.container, prisonerDetailFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();

                    }
                })
        );







    }

    @Override
    public void onItemClick(View view, int position) {


    }


    private class listUsersAsyncTask extends
            AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {

            super.onPreExecute();

            prisoners = new ArrayList<>();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setTitle("In Progress");
            pDialog.setMessage("En cours du traitement");
            pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... params) {

            String data = MyHelper
                    .PrisonersData(null, "users");


            prisoners.clear();

            Log.v(TAG, "data = "+data);

            Gson gson = new GsonBuilder().create();
            prisonerResonse = gson.fromJson(data,
                    MyResponse.Prisoner.class);

            if (prisonerResonse.getSuccess() == 1) {
                Log.v(TAG, "chargement réussite");

                mResult = true;

                for (MyResponse.PrisonerResponse pr : prisonerResonse
                        .getPrisoners()) {

                    prisoner = new Prisoner();

                    prisoner.setId(pr.getmId());
                    prisoner.setName(pr.getName());
                    prisoner.setMatricule(pr.getMatricule());
                    prisoner.setDuration(pr.getDuration());
                    prisoner.setImageRes(pr.getImagePath());

                    prisoners.add(prisoner);

                    Log.v(TAG, prisoner.toString());

                }
            }

            else {
                mResult = false;
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            Log.v(TAG, "success = "
                    + prisonerResonse.getSuccess());

            if (mResult == true) {
                Log.v(TAG, "chargement réussite");

                mAdapter = new PrisonerAdapter(getContext().getApplicationContext(), PrisonerListFragment.this, prisoners);

                mRecyclerView.setAdapter(mAdapter);

            } else {
                Toast.makeText(getActivity().getApplicationContext(),
                        "probleme de chargement", Toast.LENGTH_SHORT)
                        .show();

                Log.v(TAG, "probleme de chargement");
            }

            pDialog.dismiss();

        }


    }

}
