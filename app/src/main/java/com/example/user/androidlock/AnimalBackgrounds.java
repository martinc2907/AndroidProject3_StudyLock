package com.example.user.androidlock;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by user on 2018-01-08.
 */

public class AnimalBackgrounds extends Activity {
    Button set,balance;
    FloatingActionButton Cute,Pokemon,Animals,Cool3D;
    ImageView money;
    int money_now;
    ArrayList<Background> list = new ArrayList<Background>();
    SharedPreferences mSettings;
    SharedPreferences.Editor editor;
    private AlertDialog.Builder builder;
    private AlertDialog popup;
    ViewPager viewPager;

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(AnimalBackgrounds.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        finish();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.available_background);
        //버튼 및 뷰페이저 초기화
        set =(Button) findViewById(R.id.set);
        balance = findViewById(R.id.balance);
        money = findViewById(R.id.money2);
        Cute = findViewById(R.id.float1);
        Pokemon = findViewById(R.id.float2);
        Animals = findViewById(R.id.float3);
        Cool3D = findViewById(R.id.float4);
        //Shared Preferences 초기화
        mSettings = this.getSharedPreferences("settings",Context.MODE_PRIVATE);
        editor = mSettings.edit();
        /*
        Background b1 = new Background(R.drawable.animal1,300,false,"cute") ;
        Background b2 = new Background(R.drawable.animal2,300,false,"cute");
        Background b3 = new Background(R.drawable.animal3,700,true,"cute");
        Background b4 = new Background(R.drawable.animal4,800,false,"cute");
        Background b5 = new Background(R.drawable.animal5,1500,false,"cute");
        Background b6 = new Background(R.drawable.animal6,300,false,"cute") ;
        Background b7 = new Background(R.drawable.animal7,300,false,"cute");
        Background b8 = new Background(R.drawable.animal8,700,true,"cute");
        Background b9 = new Background(R.drawable.animal9,800,false,"cute");
        Background b10 = new Background(R.drawable.animal10,1500,false,"cute");

        Gson gson = new Gson();
        String json,json2,json3,json4,json5,json6,json7,json8,json9,json10;
        json = gson.toJson(b1);
        editor.putString("p0",json);
        json2 = gson.toJson(b2);
        editor.putString("p1",json2);
        json3 = gson.toJson(b3);
        editor.putString("p2",json3);
        json4 = gson.toJson(b4);
        editor.putString("p3",json4);
        json5 = gson.toJson(b5);
        editor.putString("p4",json5);
        json6 = gson.toJson(b6);
        editor.putString("p5",json6);
        json7 = gson.toJson(b7);
        editor.putString("p6",json7);
        json8 = gson.toJson(b8);
        editor.putString("p7",json8);
        json9 = gson.toJson(b9);
        editor.putString("p8",json9);
        json10 = gson.toJson(b10);
        editor.putString("p9",json10);


        editor.putInt("money",1000);
        editor.putInt("main_img",R.drawable.test1);
        editor.commit();
        */
        //Put Money and selcted

        Gson gson = new Gson();
        list.add(gson.fromJson(mSettings.getString("a0","fail"),Background.class));
        list.add(gson.fromJson(mSettings.getString("a1","fail"),Background.class));
        list.add(gson.fromJson(mSettings.getString("a2","fail"),Background.class));
        list.add(gson.fromJson(mSettings.getString("a3","fail"),Background.class));
        list.add(gson.fromJson(mSettings.getString("a4","fail"),Background.class));
        list.add(gson.fromJson(mSettings.getString("a5","fail"),Background.class));
        list.add(gson.fromJson(mSettings.getString("a6","fail"),Background.class));
        list.add(gson.fromJson(mSettings.getString("a7","fail"),Background.class));
        list.add(gson.fromJson(mSettings.getString("a8","fail"),Background.class));
        list.add(gson.fromJson(mSettings.getString("a9","fail"),Background.class));

        money_now = mSettings.getInt("money",0);
        balance.setText(""+money_now);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        ImagePagerAdapter adapter = new ImagePagerAdapter();
        viewPager.setAdapter(adapter);
        //initialize first value

        Background first_value = list.get(0);
        if(first_value.getAvailable()==false){
            money.setVisibility(View.VISIBLE);
            set.setText("  "+ first_value.getPrice());
        }
        else{
            money.setVisibility(View.GONE);
            set.setText("Set");
        }

        Pokemon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent = new Intent(AnimalBackgrounds.this, PokemonBackgrounds.class);
                startActivity(intent);
                finish();
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {}
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            public void onPageSelected(int position) {

                int index = viewPager.getCurrentItem();
                Gson gson = new Gson();
                Background selected_now = gson.fromJson(mSettings.getString("a"+index,"fail"),Background.class);
                if(selected_now.getAvailable()==false){
                    money.setVisibility(View.VISIBLE);
                    set.setText("  "+selected_now.getPrice());
                }
                else{
                    money.setVisibility(View.GONE);
                    set.setText("Set");
                }
            }
        });

        set.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int index = viewPager.getCurrentItem();
                Gson gson = new Gson();
                Background selected_now = gson.fromJson(mSettings.getString("a"+index,"fail"),Background.class);
                if(selected_now.getAvailable() ==true) {
                    Background selected = list.get(index);
                    //Save selected
                    editor.putInt("main_img",selected.getImage());
                    Toast.makeText(AnimalBackgrounds.this, "Selected as Background",
                            Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AnimalBackgrounds.this,MainActivity.class);
                    startActivity(intent);
                }
                else{
                    if(money_now<selected_now.getPrice()){
                        Toast.makeText(AnimalBackgrounds.this, "Save up some more!",
                                Toast.LENGTH_LONG).show();

                    }
                    else{
                        builder = new AlertDialog.Builder(AnimalBackgrounds.this);
                        View mView = getLayoutInflater().inflate(R.layout.pay_alert,null);
                        Button yesButton = mView.findViewById(R.id.yes);
                        Button noButton = mView.findViewById(R.id.no);
                        builder.setView(mView);
                        popup = builder.create();
                        popup.show();

                        yesButton.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View view) {
                                int index = viewPager.getCurrentItem();
                                Gson gson = new Gson();
                                Background selected_now = gson.fromJson(mSettings.getString("a"+index,"fail"),Background.class);
                                selected_now.setAvailable();
                                money_now = money_now - selected_now.getPrice();
                                balance.setText(""+money_now);
                                String json = gson.toJson(selected_now);
                                editor.putString("a"+index,json);
                                editor.putInt("money", money_now);
                                editor.commit();
                                set.setText("Set");
                                money.setVisibility(View.GONE);
                                Toast.makeText(AnimalBackgrounds.this, money_now + " Coins Left!",
                                        Toast.LENGTH_LONG).show();
                                popup.cancel();

                            }
                        });

                        noButton.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View view){
                                popup.cancel();
                            }
                        });


                    }

                }
            }

        });

    }

    private class ImagePagerAdapter extends PagerAdapter {


        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((ImageView) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Context context = AnimalBackgrounds.this;
            //추가



            ImageView imageView = new ImageView(context);
            /*
            imageView.setPadding(8, 8, 8, 8);
            */
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(list.get(position).getImage());
            ((ViewPager) container).addView(imageView, 0);
            return imageView;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((ImageView) object);
        }
    }

}