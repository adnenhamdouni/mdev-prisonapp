package tn.mdevtunisia.sample.prisonapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import tn.mdevtunisia.sample.prisonapp.models.Prisoner;

public class PrisonerDetailActivity extends AppCompatActivity {

    private ImageView mIvPrisoner;
    private TextView mTvName, mTvMatricule, mTvDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prisoner_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mTvName = (TextView) findViewById(R.id.tv_prisoner_detail_name);
        mTvMatricule = (TextView) findViewById(R.id.tv_prisoner_detail_matricule);
        mTvDuration = (TextView) findViewById(R.id.tv_prisoner_detail_duration);
        mIvPrisoner = (ImageView) findViewById(R.id.img_prisoner_big);

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            Prisoner prisoner = bundle.getParcelable(MainActivity.PRISONER_OBJECT_KEY);
            mTvName.setText(prisoner.getName());
            mTvMatricule.setText(prisoner.getMatricule());
            mTvDuration.setText(prisoner.getDuration());
            mIvPrisoner.setBackgroundResource(prisoner.getImageRes());

        }


    }

}
