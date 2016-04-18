package tn.mdevtunisia.sample.prisonapp.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tn.mdevtunisia.sample.prisonapp.MainActivity;
import tn.mdevtunisia.sample.prisonapp.R;
import tn.mdevtunisia.sample.prisonapp.models.Prisoner;

/**
 * Created by adnenhamdouni on 23/03/2016.
 */
public class PrisonerAdapter extends RecyclerView.Adapter<PrisonerAdapter.ViewHolder> {


    private ArrayList<Prisoner> mPrisonersList;
    private Context mContext;

    private OnItemClickListener mListener;


    public PrisonerAdapter(Context context, OnItemClickListener listener, ArrayList<Prisoner> itemsList) {
        this.mContext = context;
        this.mPrisonersList = itemsList;
        this.mListener = listener;


    }

    public PrisonerAdapter(ArrayList<Prisoner> itemsList) {

        mPrisonersList = itemsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_prisoner, parent, false);

        ViewHolder viewholder = new ViewHolder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Prisoner prisoner = mPrisonersList.get(position);

        holder.mPrisonerName.setText(prisoner.getName());
        holder.mPrisonerMatricule.setText(prisoner.getMatricule());
        holder.mPrisonerDuration.setText(prisoner.getDuration());

        Picasso.with(mContext).load(prisoner.getImageRes())
        .resize(150, 150)
                .centerCrop()
                .error(R.drawable.default_avatar)
                .placeholder(holder.mPrisonerPhoto.getDrawable()).into(holder.mPrisonerPhoto);
    }

    @Override
    public int getItemCount() {
        return mPrisonersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView mPrisonerName;
        public TextView mPrisonerMatricule;
        public TextView mPrisonerDuration;
        public ImageView mPrisonerPhoto;

        public ViewHolder(View view) {
            super(view);
            mPrisonerName = (TextView) view.findViewById(R.id.tv_prisoner_name);
            mPrisonerMatricule = (TextView) view.findViewById(R.id.tv_prisoner_matricule);
            mPrisonerDuration = (TextView) view.findViewById(R.id.tv_prisoner_duration);
            mPrisonerPhoto = (ImageView) view.findViewById(R.id.img_prisoner);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            //mListener.onItemClick(v, position);
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }


}
