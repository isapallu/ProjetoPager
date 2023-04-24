package com.example.projetopager;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Primeiro#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Primeiro extends Fragment {

    Button botao, botao2;
    ImageView foto;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Primeiro() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Primeiro.
     */
    // TODO: Rename and change types and number of parameters
    public static Primeiro newInstance(String param1, String param2) {
        Primeiro fragment = new Primeiro();
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
        View v = inflater.inflate(R.layout.fragment_primeiro, container, false);
        botao = v.findViewById(R.id.btn1);
        botao2 = v.findViewById(R.id.button);
        foto = v.findViewById(R.id.imageView);
        botao.setOnClickListener(click -> {
           tiraFoto();
        });
        botao2.setOnClickListener(click -> {
            pegaFoto();
        });
        return v;
    }
    public void tiraFoto(){
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //startActivityForResult(i, 1);
        abrirCamera.launch(i);// código de requisição para tirar foto
        //quando tiver o resultado de tirar foto volta para a tela
    }
    public void pegaFoto(){
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        abrirGaleria.launch(i);
    }

    ActivityResultLauncher<Intent> abrirCamera = registerForActivityResult
            (new ActivityResultContracts.StartActivityForResult(), result -> {
                if(result.getResultCode() == Activity.RESULT_OK){
                    Intent data = result.getData();
                    Bundle dado = data.getExtras();//Bundle: classe para converter dados, fará com que o dado seja compatível com o format view
                    Bitmap imagem = (Bitmap) dado.get("data");//formato de imagem
                    foto.setImageBitmap(imagem);// mudar imagem para bitmap e colocar no image view
                }
            });
    ActivityResultLauncher<Intent> abrirGaleria = registerForActivityResult
            (new ActivityResultContracts.StartActivityForResult(), result ->{
                if(result.getResultCode() == Activity.RESULT_OK){
                    Intent data = result.getData();
                    Uri imagemSelecionada = data.getData();
                    String[] caminho = {MediaStore.Images.Media.DATA};
                    Cursor c = getActivity().getContentResolver().query
                            (imagemSelecionada, caminho, null, null, null);
                    c.moveToFirst();
                    int coluna = c.getColumnIndex(caminho[0]);
                    String caminhoFisico = c.getString(coluna);
                    c.close();
                    Bitmap imagem = (BitmapFactory.decodeFile(caminhoFisico));
                    foto.setImageBitmap(imagem); //mudar imagem para bitmap e colocar no image view

                }
            });
}
