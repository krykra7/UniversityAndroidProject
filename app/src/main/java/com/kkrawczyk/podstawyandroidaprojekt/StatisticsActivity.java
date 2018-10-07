package com.kkrawczyk.podstawyandroidaprojekt;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.kkrawczyk.podstawyandroidaprojekt.model.Shape;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kkrawczyk on 10/4/2018.
 */
public class StatisticsActivity extends Activity {

    @BindView(R.id.tv_text)
    TextView testTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            ArrayList<Shape> shapeArrayList = (ArrayList<Shape>) extras.getSerializable(MainActivity.SHAPES_LIST_KEY);
            if (shapeArrayList != null) {
                for (Shape shape : shapeArrayList) {
                    testTv.append(shape.toString());
                }
            }
        }
    }
}
