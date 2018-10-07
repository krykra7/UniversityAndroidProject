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
import com.kkrawczyk.podstawyandroidaprojekt.utilities.ShapeGenerator;
import com.kkrawczyk.podstawyandroidaprojekt.utilities.ShapePreferencesManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kkrawczyk on 10/4/2018.
 */
public class MainActivity extends AppCompatActivity {

    static final String SHAPES_LIST_KEY = "shapesList";

    @BindView(R.id.rv_shape_list)
    RecyclerView shapeListRv;

    private ShapeGenerator shapeGenerator;
    private ShapeListAdapter shapeListAdapter;

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
        inflater.inflate(R.menu.shapes, menu);
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
        }

        return super.onOptionsItemSelected(item);
    }

    private void populateListWithShapes() {
        int shapeCount = ShapePreferencesManager.getShapesCountAsInt(this);
        shapeListAdapter.swapShapesSource(shapeGenerator.getShapeList(shapeCount));
    }
}
