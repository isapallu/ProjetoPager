package com.example.projetopager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Segundo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Segundo extends Fragment {

    EditText entrada;
    TextView saida;
    Button gerar, ok;
    int numeroGerado = 0;
    int tentativas = 0;
    Random gerador = new Random();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Segundo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Segundo.
     */
    // TODO: Rename and change types and number of parameters
    public static Segundo newInstance(String param1, String param2) {
        Segundo fragment = new Segundo();
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
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_segundo, container, false);
        gerar = v.findViewById(R.id.gerar);
        ok = v.findViewById(R.id.ok);
        saida = v.findViewById(R.id.textView);
        entrada = v.findViewById(R.id.edtnumber);
        gerar.setOnClickListener(click -> {
            gerar();
        });
        ok.setOnClickListener(click -> {
            confirmar();
        });
        return v;
    }
    public void gerar(){
        tentativas = 0;
        numeroGerado = gerador.nextInt(100)+1;
    }
    public void confirmar(){
        tentativas ++;
        String mensagem = " ";
        int digito = Integer.parseInt(entrada.getText().toString());
        if(tentativas > 4){
            mensagem = "Suas tentativas se esgotaram!\nO número era "+numeroGerado+".";
        }else{if(digito>numeroGerado){
            mensagem = "O número gerado é menor!\nVocê ainda tem "+(5-tentativas)+" tentativas.";
        } else if(digito<numeroGerado){
            mensagem = "O número gerado é maior!\nVocê ainda tem "+(5-tentativas)+" tentativas.";
        }else{
            mensagem = "Parabéns, você acertou!\nForam gastas "+(5-tentativas)+" tentativas.";
        }}
        saida.setText(mensagem);

    }
}