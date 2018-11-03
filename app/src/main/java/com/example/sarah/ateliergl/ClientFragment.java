package com.example.sarah.ateliergl;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class ClientFragment extends Fragment {
    EditText nom, prenom, phone;
    EditText email;
    private boolean validate (EditText email){

        if (email.toString().isEmpty()){
            email.setError("champs vide");
            return false;

        } else {
            if ( Patterns.EMAIL_ADDRESS.matcher(email.toString()).matches() || Patterns.PHONE.matcher(email.toString()).matches()) {
                email.setError(null);
                return true;
            }
            else {

                email.setError("entrer un vrai mail ou numero");
                return false;
            }


        }
    }
    private boolean validate1 (EditText nom){

        if (nom.toString().isEmpty()){
            nom.setError("champs vide");
            return false;

        } else {
            if ( Patterns.EMAIL_ADDRESS.matcher(nom.toString()).matches()) {
                nom.setError(null);
                return true;
            }
            else {
                nom.setError("seulement les lettres sont autorisés");
                return false;
            }


        }
    }
    private boolean validate2 (EditText phone){

        if (phone.toString().isEmpty()){
            phone.setError("champs vide");
            return false;

        } else {
            if ( Patterns.PHONE.matcher(phone.toString()).matches()) {
                phone.setError(null);
                return true;
            }
            else {
                phone.setError("seulement les lettres sont autorisés");
                return false;
            }


        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_client, container, false);
        nom =  view.findViewById(R.id.inscription_nom_cl);
        prenom =  view.findViewById(R.id.inscription_prenom_cl);
        phone =  view.findViewById(R.id.inscription_tel_cl);
        Button button = (Button) view.findViewById(R.id.sign_in_cl);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = getView().findViewById(R.id.inscription_mail_cl);
                validate(email);
                validate1(nom);
                validate1(prenom);
                validate2(phone);
            }
        });
        return view;

    }

}










/* // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ClientFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClientFragment.

    // TODO: Rename and change types and number of parameters
    public static ClientFragment newInstance(String param1, String param2) {
        ClientFragment fragment = new ClientFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
*/




//--------------------------------

/*
 // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.

public interface OnFragmentInteractionListener {
    // TODO: Update argument type and name
    void onFragmentInteraction(Uri uri);
}


 */