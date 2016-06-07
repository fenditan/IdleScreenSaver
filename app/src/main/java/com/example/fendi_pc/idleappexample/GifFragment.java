package com.example.fendi_pc.idleappexample;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Fendi-PC on 6/6/2016.
 */
public class GifFragment extends Fragment {

    private GifImageView gifView;
    private byte[] bitmapData;
    private File gif;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Layout
        View layout = inflater.inflate(R.layout.gif_layout, container, false);
        gifView = (GifImageView) layout.findViewById(R.id.gifImageView);

        //Reading input from gif assetfolder
        try {
            InputStream inputStream = getActivity().getAssets().open("weather.gif");
            byte[] bytes;
            bytes = IOUtils.toByteArray(inputStream);
            gifView.setBytes(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //start animation
        gifView.startAnimation();

        return layout;
    }

    @Override
    public void onStop() {
        super.onStop();
        //stop animation when fragment is stop
        gifView.stopAnimation();
    }
}

