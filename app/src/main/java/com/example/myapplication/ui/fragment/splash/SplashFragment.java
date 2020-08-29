package com.example.myapplication.ui.fragment.splash;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.myapplication.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashFragment extends Fragment {
    private static final String TAG = "SplashFragment";
    private View rootView;
    Timer timer = new Timer();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setTimerSplash();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_splash, container, false);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    private void setTimerSplash() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Navigation.findNavController(rootView).navigate(R.id.action_splashFragment_to_mainToDosFragment);

            }
        }, 1000, 2500);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
        timer.purge();
        timer.cancel();
        timer = null;
    }
}