package tn.mdevtunisia.sample.prisonapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import tn.mdevtunisia.sample.prisonapp.MainActivity;
import tn.mdevtunisia.sample.prisonapp.PrisonerDetailActivity;
import tn.mdevtunisia.sample.prisonapp.R;
import tn.mdevtunisia.sample.prisonapp.adapters.PrisonerAdapter;
import tn.mdevtunisia.sample.prisonapp.utils.PrisonerContent;

public class PrisonerListFragment extends Fragment {


    private ListView mLvPrisoners;
    private PrisonerAdapter mAdapter;

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

        mLvPrisoners = (ListView) view.findViewById(R.id.lv_prisoners);
        mAdapter = new PrisonerAdapter(getActivity(), R.layout.item_prisoner, PrisonerContent.getPrisoners());
        mLvPrisoners.setAdapter(mAdapter);

        mLvPrisoners.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(getActivity(), PrisonerDetailActivity.class);
//                intent.putExtra(MainActivity.PRISONER_OBJECT_KEY, PrisonerContent.getPrisoners().get(position));
//                startActivity(intent);

                PrisonerDetailFragment prisonerDetailFragment = PrisonerDetailFragment.newInstance(PrisonerContent.getPrisoners().get(position));
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager
                        .beginTransaction();
                fragmentTransaction.replace(R.id.container, prisonerDetailFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }

}
