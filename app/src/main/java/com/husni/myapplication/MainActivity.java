package com.husni.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.husni.myapplication.adapter.FoodAdapterRV;
import com.husni.myapplication.model.Food;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.ed_food)
    EditText edFood;
    @BindView(R.id.ed_desc)
    EditText edDesc;
    @BindView(R.id.btnAdd)
    Button btnAdd;
    @BindView(R.id.list)
    RecyclerView list;

    private FoodDatabase database;
    private FoodAdapterRV adapterRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        database = FoodDatabase.getDatabase(this);

        getFood();
    }

    @OnClick(R.id.btnAdd)
    public void onViewClicked() {

        //ambil input
        String name = edFood.getText().toString();
        String desc = edDesc.getText().toString();

        Food food = new Food();
        food.setFood_name(name);
        food.setFood_desc(desc);

        edFood.setText("");
        edDesc.setText("");


        database.foodDao().add(food);

        //Get the food from local database
        getFood();
    }

    private void getFood() {

        List<Food> data = database.foodDao().getFood();

        showData(data);

        if(data.size() == 0){
            Toast.makeText(this, "No Data ... ", Toast.LENGTH_SHORT).show();
        }
    }

    private void showData(List<Food> data) {

        adapterRV = new FoodAdapterRV(data, new FoodAdapterRV.OnItemClickImg() {
            @Override
            public void Item(final Food food) {

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("information");
                alert.setMessage("Are you sure to delete it ?");
                alert.setPositiveButton("sure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        actionDelete(food);
                    }
                });
                alert.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alert.show();


            }
        });
        adapterRV.notifyDataSetChanged();
        list.setAdapter(adapterRV);

    }

    public void actionDelete(Food food) {
        database.foodDao().delete(food);
        adapterRV.notifyDataSetChanged();
        getFood();
    }
}
