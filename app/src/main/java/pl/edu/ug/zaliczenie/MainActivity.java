package pl.edu.ug.zaliczenie;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    MediaPlayer mediaPlayer1;
    MediaPlayer mediaPlayer2;
    Integer count = -1;
    ImageView zdj;
    ImageView zdj2;
    ImageView zdj3;
    Button start;
    Button stop;
    Handler handler;
    Runnable runnable;
    Integer timer = 0;
    TextView counter;
    Button next;
    Button prev;

    public void play(View view){
        mediaPlayer = MediaPlayer.create(this,R.raw.muzyka);
        if(count==-1){
            zdj.setVisibility(View.VISIBLE);
            mediaPlayer.start();
            count=0;
            timer = 0;
            start.setVisibility(View.INVISIBLE);
            stop.setVisibility(View.VISIBLE);
            prev.setVisibility(View.VISIBLE);
            next.setVisibility(View.VISIBLE);
            handler = new Handler();
            runnable = new Runnable(){
            @Override
            public void run(){
                timer++;
                Log.i("Uwaga","Minęła sekunda");
                counter = findViewById(R.id.timerTextView);
                counter.setVisibility(View.VISIBLE);
                counter.setText(timer.toString() + "s");
                handler.postDelayed(this,1000);
            }
        };
        handler.post(runnable);
        }
    }

    public void stop(View view){
        mediaPlayer.stop();
        mediaPlayer1.stop();
        mediaPlayer2.stop();
        stop.setVisibility(View.INVISIBLE);
        start.setVisibility(View.VISIBLE);
        prev.setVisibility(View.INVISIBLE);
        next.setVisibility(View.INVISIBLE);
        count = -1;
        zdj.setVisibility(View.INVISIBLE);
        zdj2.setVisibility(View.INVISIBLE);
        zdj3.setVisibility(View.INVISIBLE);
        handler.removeCallbacks(runnable);
        timer = 0;
        counter.setText(timer.toString() + "s");
    }
    public void next(View view){


        if(count == 0){
            mediaPlayer.stop();
            mediaPlayer1 = MediaPlayer.create(this,R.raw.muzyka1);
            mediaPlayer1.start();
            count = 1;
            zdj.setVisibility(View.INVISIBLE);
            zdj2.setVisibility(View.VISIBLE);
            timer=0;
            counter.setText(timer.toString() + "s");
        }else if(count==1){
            mediaPlayer1.stop();
            mediaPlayer2 = MediaPlayer.create(this,R.raw.muzyka2);
            mediaPlayer2.start();
            count = 2;
            zdj2.setVisibility(View.INVISIBLE);
            zdj3.setVisibility(View.VISIBLE);
            timer=0;
            counter.setText(timer.toString() + "s");
        }else if(count==2){
            mediaPlayer2.stop();
            mediaPlayer = MediaPlayer.create(this,R.raw.muzyka);
            mediaPlayer.start();
            count = 0;
            zdj3.setVisibility(View.INVISIBLE);
            zdj.setVisibility(View.VISIBLE);
            timer=0;
            counter.setText(timer.toString() + "s");
        }
    }

    public void previous(View view){


        if(count == 0){
            mediaPlayer.stop();
            mediaPlayer2 = MediaPlayer.create(this,R.raw.muzyka2);
            mediaPlayer2.start();
            count = 2;
            zdj.setVisibility(View.INVISIBLE);
            zdj3.setVisibility(View.VISIBLE);
            timer=0;
            counter.setText(timer.toString() + "s");
        }else if(count==1){
            mediaPlayer1.stop();
            mediaPlayer = MediaPlayer.create(this,R.raw.muzyka);
            mediaPlayer.start();
            count = 0;
            zdj2.setVisibility(View.INVISIBLE);
            zdj.setVisibility(View.VISIBLE);
            timer=0;
            counter.setText(timer.toString() + "s");
        }else if(count==2){
            mediaPlayer2.stop();
            mediaPlayer1 = MediaPlayer.create(this,R.raw.muzyka1);
            mediaPlayer1.start();
            count = 1;
            zdj3.setVisibility(View.INVISIBLE);
            zdj2.setVisibility(View.VISIBLE);
            timer=0;
            counter.setText(timer.toString() + "s");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        zdj = findViewById(R.id.imageView);
        zdj2 = findViewById(R.id.imageView2);
        zdj3 = findViewById(R.id.imageView3);

        mediaPlayer = MediaPlayer.create(this,R.raw.muzyka);
        mediaPlayer1 = MediaPlayer.create(this,R.raw.muzyka1);
        mediaPlayer2 = MediaPlayer.create(this,R.raw.muzyka2);


        start = findViewById(R.id.startButton);
        stop = findViewById(R.id.stopButton);
        next = findViewById(R.id.nextButton);
        prev = findViewById(R.id.previousButton);
    }
}