package com.example.eaters.Dialogs;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.eaters.R;


public class Dialog_TrocoPagamento extends AppCompatDialogFragment {
    private EditText editValor;
    private ExampleDialogListener listener;

    @Override
    //public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //LayoutInflater inflater = ((MainActivity)getContext()).getLayoutInflater();
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.dialog_trocopagamento, null);

        //AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        editValor = view.findViewById(R.id.dialog_edit_valor);


        builder.setView(view)
                .setTitle("Troco")
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //getDialog().dismiss();
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String diag_valor = editValor.getText().toString();
                        listener.applyTexts(diag_valor);
                        //getDialog().dismiss();
                    }
                });

        //return view;
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            //listener = (ExampleDialogListener) context;
            listener = (ExampleDialogListener) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement ExampleDialogListener");
        }
    }

    public interface ExampleDialogListener{
        void applyTexts(String diag_valor);
    }
}
