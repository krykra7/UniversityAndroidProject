package com.kkrawczyk.podstawyandroidaprojekt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.kkrawczyk.podstawyandroidaprojekt.list.adapter.ShapeListAdapter;
import com.kkrawczyk.podstawyandroidaprojekt.model.Shape;
import com.kkrawczyk.podstawyandroidaprojekt.utilities.ApplicationConstants;
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

    @BindView(R.id.rv_shape_list)
    RecyclerView shapeListRv;
    @BindView(R.id.iv_shape_sort_indicator)
    ImageView shapeSortIndicatorIv;
    @BindView(R.id.iv_area_sort_indicator)
    ImageView areaSortIndicatorIv;
    @BindView(R.id.iv_feature_sort_indicator)
    ImageView featureSortIndicatorIv;

    private ShapeGenerator shapeGenerator;
    private ShapeListAdapter shapeListAdapter;
    private ArrayList<Shape> unsortedShapes;

    @SuppressWarnings("ComparatorCombinators")
    @OnClick(R.id.tv_shape_indicator)
    public void sortByShape() {
        sortShapeList(((o1, o2) -> o1.toString().compareTo(o2.toString())), ApplicationConstants.SORTED_BY_SHAPE);
    }

    @SuppressWarnings({"LambdaBodyCanBeCodeBlock", "ComparatorCombinators"})
    @OnClick(R.id.tv_area_indicator)
    public void sortByArea() {
        sortShapeList(((o1, o2) -> Double.compare(o1.getArea(), o2.getArea())), ApplicationConstants.SORTED_BY_AREA);
    }

    @SuppressWarnings("ComparatorCombinators")
    @OnClick(R.id.tv_feature_indicator)
    public void sortByFeature() {
        sortShapeList(((o1, o2) -> Double.compare(o1.getFeature(), o2.getFeature())),
                ApplicationConstants.SORTED_BY_FEATURE);
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

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(ApplicationConstants.SHAPES_SAVED_INSTANCE_KEY)) {
                //noinspection unchecked
                shapeListAdapter.swapDataSource(
                        (ArrayList<Shape>) savedInstanceState
                                .getSerializable(ApplicationConstants.SHAPES_SAVED_INSTANCE_KEY));
            }
        } else {
            populateListWithShapes();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(ApplicationConstants.SHAPES_SAVED_INSTANCE_KEY, shapeListAdapter.getShapes());
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
                openStatisticsActivity();
                break;
            case R.id.action_refresh:
                populateListWithShapes();
                break;
            case R.id.action_add_random:
                addRandomShapeToList();
                break;
            case R.id.action_delete_all:
                shapeListAdapter.swapDataSource(new ArrayList<>());
                break;
            default:
                throw new IllegalArgumentException("Such menu item does not exist");
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.shape_item_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_context_delete:
                deleteShape();
                return true;
            case R.id.action_context_duplicate:
                duplicateShape();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void addRandomShapeToList() {
        shapeListAdapter.addShapeToDataSource(shapeGenerator.getRandomShape(), ApplicationConstants.EMPTY_POSITION);
        shapeListRv.smoothScrollToPosition(shapeListAdapter.getShapes().size());
    }

    private void openStatisticsActivity() {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ApplicationConstants.SHAPES_LIST_KEY, shapeListAdapter.getShapes());
        Intent startStatisticsIntent = new Intent(this, StatisticsActivity.class);
        startStatisticsIntent.putExtras(bundle);
        startActivity(startStatisticsIntent);
    }

    private void deleteShape() {
        ArrayList<Shape> shapes = shapeListAdapter.getShapes();
        shapes.remove(shapeListAdapter.getPosition());
        shapeListAdapter.swapDataSource(shapes);
    }

    private void duplicateShape() {
        int shapePosition = shapeListAdapter.getPosition();
        Shape shapeToDuplicate = shapeListAdapter.getShapes().get(shapePosition);

        shapeListAdapter.addShapeToDataSource(shapeToDuplicate, shapePosition);
    }

    private void sortShapeList(Comparator<Shape> comparator, int requestedSortType) {
        if (shouldSort(requestedSortType)) {
            ArrayList<Shape> shapeArrayList = shapeListAdapter.getShapes();
            unsortedShapes = new ArrayList<>(shapeArrayList);

            Collections.sort(shapeArrayList, comparator);

            shapeListAdapter.setSortType(requestedSortType);
            shapeListAdapter.swapDataSource(shapeArrayList);
        } else {
            setIndicatorsUnsorted();
            shapeListAdapter.setSortType(ApplicationConstants.UNSORTED);
            shapeListAdapter.swapDataSource(unsortedShapes);
        }
    }

    private boolean shouldSort(int requestedSortType) {
        int currentSortType = shapeListAdapter.getSortType();
        boolean shouldSort;

        switch (currentSortType) {
            case ApplicationConstants.UNSORTED:
                return true;
            case ApplicationConstants.SORTED_BY_SHAPE:
                shouldSort = currentSortType != requestedSortType;

                if (shouldSort) {
                    shapeSortIndicatorIv.setVisibility(View.VISIBLE);
                } else {
                    shapeSortIndicatorIv.setVisibility(View.GONE);
                }

                return shouldSort;
            case ApplicationConstants.SORTED_BY_AREA:
                shouldSort = currentSortType != requestedSortType;

                if (shouldSort) {
                    areaSortIndicatorIv.setVisibility(View.VISIBLE);
                } else {
                    areaSortIndicatorIv.setVisibility(View.GONE);
                }

                return shouldSort;
            case ApplicationConstants.SORTED_BY_FEATURE:
                shouldSort = currentSortType != requestedSortType;

                if (shouldSort) {
                    featureSortIndicatorIv.setVisibility(View.VISIBLE);
                } else {
                    featureSortIndicatorIv.setVisibility(View.GONE);
                }

                return shouldSort;
            default:
                return false;
        }
    }

    private void setIndicatorsUnsorted() {
        areaSortIndicatorIv.setVisibility(View.GONE);
        featureSortIndicatorIv.setVisibility(View.GONE);
        shapeSortIndicatorIv.setVisibility(View.GONE);
    }

    private void populateListWithShapes() {
        int shapeCount = ShapePreferencesManager.getShapesCountAsInt(this);
        shapeListAdapter.swapDataSource(shapeGenerator.getShapeList(shapeCount));
    }
}
