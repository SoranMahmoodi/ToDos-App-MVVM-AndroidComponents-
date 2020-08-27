package com.example.myapplication.ui.fragment.splash;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.myapplication.R;

public class SplashFragment extends Fragment {

    private Runnable runnable;
    private Handler handler = new Handler();
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_splash, container, false);
            setTimerSplash();
        }
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void fullScreenActivity() {
        getActivity().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                , WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void setTimerSplash() {
        runnable = () -> {
            Navigation.findNavController(rootView).navigate(R.id.action_splashFragment_to_mainToDosFragment);
        };
        handler.postDelayed(runnable, 2500);
    }
}