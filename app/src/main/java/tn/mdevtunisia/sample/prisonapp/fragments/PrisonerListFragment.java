package tn.mdevtunisia.sample.prisonapp.fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import tn.mdevtunisia.sample.prisonapp.MainActivity;
import tn.mdevtunisia.sample.prisonapp.R;
import tn.mdevtunisia.sample.prisonapp.adapters.PrisonerAdapter;
import tn.mdevtunisia.sample.prisonapp.helper.MyHelper;
import tn.mdevtunisia.sample.prisonapp.models.Prisoner;
import tn.mdevtunisia.sample.prisonapp.models.response.MyResponse;
import tn.mdevtunisia.sample.prisonapp.utils.PrisonerContent;

public class PrisonerListFragment extends Fragment {


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

        initData();

        initRecyclerView(view);


        new listUsersAsyncTask().execute();

        //mLvPrisoners = (RecyclerView) view.findViewById(R.id.recycler_list);
//        mAdapter = new PrisonerAdapter(getActivity(), R.layout.item_prisoner, PrisonerContent.getPrisoners());
//        mLvPrisoners.setAdapter(mAdapter);



//        mLvPrisoners.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                Intent intent = new Intent(getActivity(), PrisonerDetailActivity.class);
////                intent.putExtra(MainActivity.PRISONER_OBJECT_KEY, PrisonerContent.getPrisoners().get(position));
////                startActivity(intent);
//
//                PrisonerDetailFragment prisonerDetailFragment = PrisonerDetailFragment.newInstance(prisoners.get(position));
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager
//                        .beginTransaction();
//                fragmentTransaction.replace(R.id.container, prisonerDetailFragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//            }
//        });

        return view;
    }

    private void initData() {
        //prisoners = PrisonerContent.getPrisoners();


    }

    private void initRecyclerView(View view) {


        prisoners = new ArrayList<Prisoner>();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_list);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        //mAdapter = new UserAdapter(receiveUsers);

        mAdapter = new PrisonerAdapter(getContext(), prisoners);

        mRecyclerView.setAdapter(mAdapter);

    }


    private class listUsersAsyncTask extends
            AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity().getApplicationContext());
            pDialog.setMessage("Connexion...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
            // progress_status = 0;

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

                    Log.v(TAG, "name prisoner ="
                            + prisoner.getName());

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

            pDialog.dismiss();


                    Log.v(TAG, "success = "
                            + prisonerResonse.getSuccess());

                    if (mResult == true) {
                        Log.v(TAG, "chargement réussite");

                        //showUsersList();
                        mAdapter.notifyDataSetChanged();


                    } else {
                        Toast.makeText(getActivity().getApplicationContext(),
                                "probleme de chargement", Toast.LENGTH_SHORT)
                                .show();

                        Log.v(TAG, "probleme de chargement");
                    }

                }
    }

}
