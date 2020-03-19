package com.example.eaters.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.eaters.Fragments.PedidoConcluido_Fragment;
import com.example.eaters.R;

public class ProgressBarActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private int progressStatus = 0;
    private TextView textView;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progressbar);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        textView = (TextView) findViewById(R.id.progress_counter);
        // Start long running operation in a background thread
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            textView.setText(progressStatus+"/"+progressBar.getMax());
                        }
                    });
                    try {
                        // Sleep for 100 milliseconds.
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (progressStatus == 100){
                    telafinal();
                }
            }
        }).start();
    }

    public void telafinal() {
        Intent mySuperIntent = new Intent(this, MainActivity.class);
        startActivity(mySuperIntent);
        PedidoConcluido_Fragment newFragment = new PedidoConcluido_Fragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, newFragment).commit();

        //FragmentManager manager = ((MainActivity)getContext()).getSupportFragmentManager();
        /*FragmentManager manager = (getActivity().getSupportFragmentManager());

        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
        startActivity(mySuperIntent);*/
    }
}

