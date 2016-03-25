package tn.mdevtunisia.sample.prisonapp.utils;

import java.util.ArrayList;

import tn.mdevtunisia.sample.prisonapp.R;
import tn.mdevtunisia.sample.prisonapp.models.Prisoner;

/**
 * Created by adnenhamdouni on 23/03/2016.
 */
public class PrisonerContent {

    public static String[] names = {"Name 1", "Name 2", "Name 3", "Name 4", "Name 5"};
    public static String[] matricules = {"911", "912", "913", "914", "915"};
    public static Integer[] pictures = {R.drawable.profil, R.drawable.profil, R.drawable.profil, R.drawable.profil, R.drawable.profil};
    public static String[] durations = {"50 years - 1980", "45 years ", "Name 3", "Name 4", "Name 5"};

    public static ArrayList<Prisoner> getPrisoners(){
        ArrayList<Prisoner> prisoners = new ArrayList<>();
        for (int i = 0; i<5; i++){
            prisoners.add(new Prisoner(names[i], matricules[i], durations[i], pictures[i]));
        }

        return prisoners;
    }


}
