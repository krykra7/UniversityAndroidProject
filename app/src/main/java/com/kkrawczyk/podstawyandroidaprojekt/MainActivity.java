package com.kkrawczyk.podstawyandroidaprojekt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.kkrawczyk.podstawyandroidaprojekt.list.adapter.ShapeListAdapter;
import com.kkrawczyk.podstawyandroidaprojekt.model.Shape;
import com.kkrawczyk.podstawyandroidaprojekt.utilities.ShapeGenerator;
import com.kkrawczyk.podstawyandroidaprojekt.utilities.ShapePreferencesManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kkrawczyk on 10/4/2018.
 */
public class MainActivity extends AppCompatActivity {

    static final String SHAPES_LIST_KEY = "shapesList";

    @BindView(R.id.rv_shape_list)
    RecyclerView shapeListRv;

    private ShapeGenerator shapeGenerator;
    private ShapeListAdapter shapeListAdapter;
    private ArrayList<Shape> unsortedShapes;

    @SuppressWarnings("ComparatorCombinators")
    @OnClick(R.id.tv_shape_indicator)
    public void sortByShape() {
        sortShapeList(((o1, o2) -> o1.toString().compareTo(o2.toString())), ShapeListAdapter.SORTED_BY_SHAPE);
    }

    @SuppressWarnings("LambdaBodyCanBeCodeBlock")
    @OnClick(R.id.tv_area_indicator)
    public void sortByArea() {
        sortShapeList(((o1, o2) -> Double.compare(o1.getArea(), o2.getArea())), ShapeListAdapter.SORTED_BY_AREA);
    }

    @OnClick(R.id.tv_feature_indicator)
    public void sortByFeature() {
        sortShapeList(((o1, o2) -> Double.compare(o1.getFeature(), o2.getFeature())),
                ShapeListAdapter.SORTED_BY_FEATURE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        shapeGenerator = new ShapeGenerator(this);

        shapeListRv.setLayoutManager(new LinearLayoutManager(this));
        shapeListAdapter = new ShapeListAdapter(this);
        shapeListRv.setAdapter(shapeListAdapter);

        populateListWithShapes();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.shapes_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId) {
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
            case R.id.action_statistics:
                Bundle bundle = new Bundle();
                bundle.putSerializable(SHAPES_LIST_KEY, shapeListAdapter.getShapes());
                Intent startStatisticsIntent = new Intent(this, StatisticsActivity.class);
                startStatisticsIntent.putExtras(bundle);
                startActivity(startStatisticsIntent);
                break;
            case R.id.action_refresh:
                populateListWithShapes();
                break;
            default:
                throw new IllegalArgumentException("Such menu item does not exist");
        }

        return super.onOptionsItemSelected(item);
    }

    private void sortShapeList(Comparator<Shape> comparator, int requestedSortType) {
        if (shouldSort(requestedSortType)) {
            ArrayList<Shape> shapeArrayList = shapeListAdapter.getShapes();
            unsortedShapes = new ArrayList<>(shapeArrayList);

            Collections.sort(shapeArrayList, comparator);

            shapeListAdapter.setSortType(requestedSortType);
            shapeListAdapter.swapShapesSource(shapeArrayList);
        } else {
            shapeListAdapter.setSortType(ShapeListAdapter.UNSORTED);
            shapeListAdapter.swapShapesSource(unsortedShapes);
        }
    }

    private boolean shouldSort(int requestedSortType) {
        int currentSortType = shapeListAdapter.getSortType();

        switch (currentSortType) {
            case ShapeListAdapter.UNSORTED:
                return true;
            case ShapeListAdapter.SORTED_BY_SHAPE:
                return currentSortType != requestedSortType;
            case ShapeListAdapter.SORTED_BY_AREA:
                return currentSortType != requestedSortType;
            case ShapeListAdapter.SORTED_BY_FEATURE:
                return currentSortType != requestedSortType;
            default:
                return false;
        }
    }

    private void populateListWithShapes() {
        int shapeCount = ShapePreferencesManager.getShapesCountAsInt(this);
        shapeListAdapter.swapShapesSource(shapeGenerator.getShapeList(shapeCount));
    }
}
