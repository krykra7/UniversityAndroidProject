package com.kkrawczyk.podstawyandroidaprojekt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.kkrawczyk.podstawyandroidaprojekt.model.Shape;
import com.kkrawczyk.podstawyandroidaprojekt.utilities.NumberFormatUtils;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kkrawczyk on 10/4/2018.
 */
public class StatisticsActivity extends AppCompatActivity {

    @BindView(R.id.tv_statistics_square_area)
    TextView squareAreaTv;
    @BindView(R.id.tv_statistics_triangle_area)
    TextView triangleAreaTv;
    @BindView(R.id.tv_statistics_circle_area)
    TextView circleAreaTv;
    @BindView(R.id.tv_statistics_square_count)
    TextView squareCountTv;
    @BindView(R.id.tv_statistics_triangle_count)
    TextView triangleCountTv;
    @BindView(R.id.tv_statistics_circle_count)
    TextView circleCountTv;
    @BindView(R.id.tv_statistics_square_feature)
    TextView squareFeatureTv;
    @BindView(R.id.tv_statistics_triangle_feature)
    TextView triangleFeatureTv;
    @BindView(R.id.tv_statistics_circle_feature)
    TextView circleFeatureTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        ButterKnife.bind(this);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            ArrayList<Shape> shapeArrayList = (ArrayList<Shape>) extras.getSerializable(MainActivity.SHAPES_LIST_KEY);
            if (shapeArrayList != null) {
                prepareStatistics(shapeArrayList);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void prepareStatistics(ArrayList<Shape> shapes) {
        int triangles = 0;
        int squares = 0;
        int circles = 0;
        double trianglesAreaSum = 0;
        double squaresAreaSum = 0;
        double circlesAreaSum = 0;
        double trianglesFeatureSum = 0;
        double squaresFeatureSum = 0;
        double circlesFeatureSum = 0;

        for (Shape shape : shapes) {
            switch (shape.toString()) {
                case Shape.SQUARE:
                    squares++;
                    squaresAreaSum += shape.getArea();
                    squaresFeatureSum += shape.getFeature();
                    break;
                case Shape.TRIANGLE:
                    triangles++;
                    trianglesAreaSum += shape.getArea();
                    trianglesFeatureSum += shape.getFeature();
                    break;
                case Shape.CIRCLE:
                    circles++;
                    circlesAreaSum += shape.getArea();
                    circlesFeatureSum += shape.getFeature();
                    break;
            }
        }

        triangleCountTv.setText(String.valueOf(triangles));
        squareCountTv.setText(String.valueOf(squares));
        circleCountTv.setText(String.valueOf(circles));
        triangleAreaTv.setText(NumberFormatUtils.getFormattedDoubleAsString(trianglesAreaSum));
        squareAreaTv.setText(NumberFormatUtils.getFormattedDoubleAsString(squaresAreaSum));
        circleAreaTv.setText(NumberFormatUtils.getFormattedDoubleAsString(circlesAreaSum));
        triangleFeatureTv.setText(NumberFormatUtils.getFormattedDoubleAsString(trianglesFeatureSum));
        squareFeatureTv.setText(NumberFormatUtils.getFormattedDoubleAsString(squaresFeatureSum));
        circleFeatureTv.setText(NumberFormatUtils.getFormattedDoubleAsString(circlesFeatureSum));
    }
}
