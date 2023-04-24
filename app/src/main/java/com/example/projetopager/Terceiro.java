package com.example.projetopager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Terceiro#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Terceiro extends Fragment {
    EditText HI, MI, HF, MF;
    TextView ResultadoH, ResultadoM;
    Button somar, sub;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Terceiro() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Terceiro.
     */
    // TODO: Rename and change types and number of parameters
    public static Terceiro newInstance(String param1, String param2) {
        Terceiro fragment = new Terceiro();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_terceiro, container, false);
        ResultadoH = v.findViewById(R.id.rh);
        ResultadoM = v.findViewById(R.id.rm);
        HI = v.findViewById(R.id.h1);
        MI = v.findViewById(R.id.m1);
        HF = v.findViewById(R.id.h2);
        MF = v.findViewById(R.id.m2);
        somar = v.findViewById(R.id.soma);
        sub = v.findViewById(R.id.sub);
        somar.setOnClickListener(click -> {
            Soma();
        });
        sub.setOnClickListener(click -> {
            Subtração();
        });

        return v;
    }
    public void Soma() {
        int HoraI = Integer.parseInt(HI.getText().toString()); // transforma string em int
        int MinutoI = Integer.parseInt(MI.getText().toString());// transforma string em int
        int HoraF = Integer.parseInt(HF.getText().toString());// transforma string em int
        int MinutoF = Integer.parseInt(MF.getText().toString());// transforma string em int

        int RH = HoraI + HoraF; // somando
        int RM = MinutoI + MinutoF; // somando

        while (RM > 59) { //enquanto o resultado do minuto for maior que 60
            RM -= 60; //diminuir 60
            RH++; //+hora
        }
        ResultadoH.setText(RH+"");//aparecer resultado no campo
        ResultadoM.setText(RM+"");//aparecer resultado no campo
    }

    public void Subtração() {
        int HoraI = Integer.parseInt(HI.getText().toString()); // transforma string em int
        int MinutoI = Integer.parseInt(MI.getText().toString());// transforma string em int
        int HoraF = Integer.parseInt(HF.getText().toString());// transforma string em int
        int MinutoF = Integer.parseInt(MF.getText().toString());// transforma string em int

        int RH = HoraI - HoraF; //subtraindo
        int RM = MinutoI - MinutoF;//subtraindo

        if (HoraI > HoraF) { //se a hora inicial foi maior que a final
            RH = HoraI - HoraF; //o resultado final vai ser a inicial menos a final
        } else { // se não
            RH = HoraF - HoraI; //o resultado será final menos inicial
        }

        if (MinutoI > MinutoF) { //se o minuto inicial for maior que o final
            RM = MinutoI - MinutoF; //o resultado do minuto será o inicial menos o final
        } else { //se não
            RM = MinutoF - MinutoI; //o resultado do minuto será o final menos o inicial
        }

        while (RM < 0) { //enquanto o resultado do minuto for menor que 0
            RM += 60; //aumentar 60
            RH--; //diminuir hora
        }
        while (RM > 59) { //enquanto o resultado do minuto for maior que 60
            RM -= 60; //diminuir 60
            RH++; //+hora
        }
        ResultadoH.setText(RH+"");//aparecer resultado no campo
        ResultadoM.setText(RM+"");//aparecer resultado no campo
    }
}