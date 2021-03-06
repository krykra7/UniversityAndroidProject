package com.kkrawczyk.podstawyandroidaprojekt.list.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kkrawczyk.podstawyandroidaprojekt.R;
import com.kkrawczyk.podstawyandroidaprojekt.model.Shape;
import com.kkrawczyk.podstawyandroidaprojekt.utilities.ApplicationConstants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kkrawczyk on 10/4/2018.
 */
public class ShapeListAdapter extends RecyclerView.Adapter<ShapeListAdapter.ShapeListViewHolder> {

    private Context mContext;
    private ArrayList<Shape> mShapes;
    private int sortType = ApplicationConstants.UNSORTED;
    private int position = ApplicationConstants.EMPTY_POSITION;

    public ShapeListAdapter(Context context) {
        this.mContext = context;
    }

    public void swapDataSource(ArrayList<Shape> shapes) {
        if (shapes != null) {
            this.mShapes = shapes;
            this.notifyDataSetChanged();
        }
    }

    public void addShapeToDataSource(Shape shape, int index) {
        if (shape == null) {
            return;
        }

        if (index != ApplicationConstants.EMPTY_POSITION) {
            this.mShapes.add(index, shape);
            this.notifyItemInserted(index + 1);
        } else {
            this.mShapes.add(shape);
            this.notifyItemInserted(this.mShapes.size() - 1);
        }
    }



    public ArrayList<Shape> getShapes() {
        return mShapes;
    }

    @NonNull
    @Override
    public ShapeListViewHolder onCreateViewHolder(@NonNull ViewGroup root, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.shape_list_item, root, false);

        return new ShapeListViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ShapeListViewHolder shapeListViewHolder, int position) {
        Shape currentShape = mShapes.get(position);

        shapeListViewHolder.shapeIconIv.setImageResource(currentShape.getImageRes());
        shapeListViewHolder.shapeAreaTv.setText(String.valueOf(currentShape.getCalculatedArea()));
        shapeListViewHolder.shapeFeatureTv.setText(currentShape.getFeatureName()
                + System.getProperty("line.separator")
                + currentShape.getCalculatedFeature());
    }

    @Override
    public int getItemCount() {

        if (mShapes == null) {
            return 0;
        }

        return mShapes.size();
    }

    public int getSortType() {
        return sortType;
    }

    public void setSortType(int sortType) {
        this.sortType = sortType;
    }

    public int getPosition() {
        return position;
    }

    class ShapeListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_list_item_shape_icon)
        ImageView shapeIconIv;
        @BindView(R.id.tv_list_item_area_value)
        TextView shapeAreaTv;
        @BindView(R.id.tv_list_item_feature_value)
        TextView shapeFeatureTv;

        ShapeListViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnCreateContextMenuListener((AppCompatActivity) mContext);
            itemView.setOnLongClickListener(view -> {
                position = this.getAdapterPosition();
                return false;
            });
        }
    }
}
