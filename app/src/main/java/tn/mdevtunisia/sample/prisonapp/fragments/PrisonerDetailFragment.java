package tn.mdevtunisia.sample.prisonapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import tn.mdevtunisia.sample.prisonapp.MainActivity;
import tn.mdevtunisia.sample.prisonapp.R;
import tn.mdevtunisia.sample.prisonapp.models.Prisoner;

public class PrisonerDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private Prisoner mParam1;

    private ImageView mIvPrisoner;
    private TextView mTvName, mTvMatricule, mTvDuration;

    public PrisonerDetailFragment() {
        // Required empty public constructor
    }

    public static PrisonerDetailFragment newInstance(Prisoner param1) {
        PrisonerDetailFragment fragment = new PrisonerDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (Prisoner) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_prisoner_detail, container, false);

        mTvName = (TextView) view.findViewById(R.id.tv_prisoner_detail_name);
        mTvMatricule = (TextView) view.findViewById(R.id.tv_prisoner_detail_matricule);
        mTvDuration = (TextView) view.findViewById(R.id.tv_prisoner_detail_duration);
        mIvPrisoner = (ImageView) view.findViewById(R.id.img_prisoner_big);

        mTvName.setText(mParam1.getName());
        mTvMatricule.setText(mParam1.getMatricule());
        mTvDuration.setText(mParam1.getDuration());

        Picasso.with(getContext().getApplicationContext()).load(mParam1.getImageRes())
                .into(mIvPrisoner);

        //mIvPrisoner.setBackgroundResource(mParam1.getImageRes());


        return view;
    }

}
