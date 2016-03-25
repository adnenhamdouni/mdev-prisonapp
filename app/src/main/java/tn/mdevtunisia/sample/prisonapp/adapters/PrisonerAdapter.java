package tn.mdevtunisia.sample.prisonapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import tn.mdevtunisia.sample.prisonapp.R;
import tn.mdevtunisia.sample.prisonapp.models.Prisoner;

/**
 * Created by adnenhamdouni on 23/03/2016.
 */
public class PrisonerAdapter extends ArrayAdapter<Prisoner>{

    private Context mContext;
    private int mResource;


    private String mName, mDuration, mMatricule;
    private int mImageRes;

    public PrisonerAdapter(Context context, int resource, List<Prisoner> prisoners) {
        super(context, resource, prisoners);

        this.mContext = context;
        this.mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        PrisonerHolder holder = new PrisonerHolder();

        if (view==null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(mResource, parent, false);

            holder.mTvName = (TextView) view.findViewById(R.id.tv_prisoner_name);
            holder.mTvMatricule = (TextView) view.findViewById(R.id.tv_prisoner_matricule);
            holder.mTvDuration = (TextView) view.findViewById(R.id.tv_prisoner_duration);
            holder.mIvProsoner = (ImageView) view.findViewById(R.id.img_prisoner);


           view.setTag(holder);


        } else {

            holder = (PrisonerHolder) view.getTag();
        }


        mName = getItem(position).getName();
        mMatricule = getItem(position).getMatricule();
        mDuration = getItem(position).getDuration();
        mImageRes = getItem(position).getImageRes();

        holder.mTvName.setText(mName);
        holder.mTvMatricule.setText(mMatricule);
        holder.mTvDuration.setText(mDuration);
        holder.mIvProsoner.setBackgroundResource(mImageRes);

        return view;

    }

    class PrisonerHolder {

        TextView mTvName;
        TextView mTvMatricule;
        TextView mTvDuration;
        ImageView mIvProsoner;

    }
}
