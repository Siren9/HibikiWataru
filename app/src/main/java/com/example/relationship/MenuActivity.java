package com.example.relationship;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.relationship.adapter.LoopViewAdapter;
import com.example.relationship.nebula.StaticRvAdapter;
import com.example.relationship.nebula.StaticRvModel;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    //轮播图模块
    private ViewPager viewPager;
    private int[] mImg;
    private int[] mImg_id;
    private String[] mDec;
    private ArrayList<ImageView> mImgList;
    private LinearLayout ll_dots_container;
    private TextView loop_dec;
    private int previousSelectedPosition = 0;
    boolean isRunning = false;

    //recycleView
    private RecyclerView recyclerView1,recyclerView2;
    private StaticRvAdapter staticRvAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        initLoopView();//实现轮播

        ArrayList<StaticRvModel> item = new ArrayList<>();
        item.add(new StaticRvModel(R.drawable.clock,"Clock"));
        item.add(new StaticRvModel(R.drawable.note,"Note"));
        item.add(new StaticRvModel(R.drawable.picture,"Picture"));
        item.add(new StaticRvModel(R.drawable.music,"Music"));
        item.add(new StaticRvModel(R.drawable.dictionary,"Dictionary"));

        recyclerView1 = findViewById(R.id.menu_rv_1);
        staticRvAdapter = new StaticRvAdapter(item);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView1.setAdapter(staticRvAdapter);


    }

    private void initLoopView() {
        viewPager = findViewById(R.id.menu_ViewPage_Detail);
        ll_dots_container = findViewById(R.id.menu_ll_dots_loop);
        loop_dec = findViewById(R.id.menu_loop_dec);

        // 图片资源id数组
        mImg = new int[]{
                R.drawable.list1,
                R.drawable.list2,
                R.drawable.list3,
                R.drawable.list4,
                R.drawable.list5
        };

        // 文本描述
        mDec = new String[]{
                "鹰状星云",
                "N55星云",
                "礁湖星云",
                "三裂星云",
                "马头星云"
        };

        mImg_id = new int[]{
                R.id.pager_img1,
                R.id.pager_img2,
                R.id.pager_img3,
                R.id.pager_img4,
                R.id.pager_img5
        };

        // 初始化要展示的5个ImageView
        mImgList = new ArrayList<ImageView>();
        ImageView imageView;
        View dotView;
        LinearLayout.LayoutParams layoutParams;
        for(int i=0;i<mImg.length;i++){
            //初始化要显示的图片对象
            imageView = new ImageView(this);
            imageView.setBackgroundResource(mImg[i]);
            imageView.setId(mImg_id[i]);
            imageView.setOnClickListener(new pagerOnClickListener(getApplicationContext()));
            mImgList.add(imageView);
            //加引导点
            dotView = new View(this);
            dotView.setBackgroundResource(R.drawable.dot);
            layoutParams = new LinearLayout.LayoutParams(10,10);
            if(i!=0){
                layoutParams.leftMargin=10;
            }
            //设置默认所有都不可用
            dotView.setEnabled(false);
            ll_dots_container.addView(dotView,layoutParams);
        }

        ll_dots_container.getChildAt(0).setEnabled(true);
        loop_dec.setText(mDec[0]);
        previousSelectedPosition=0;
        //设置适配器
        viewPager.setAdapter(new LoopViewAdapter(mImgList));
        // 把ViewPager设置为默认选中Integer.MAX_VALUE / t2，从十几亿次开始轮播图片，达到无限循环目的;
        int m = (Integer.MAX_VALUE / 2) %mImgList.size();
        int currentPosition = Integer.MAX_VALUE / 2 - m;
        viewPager.setCurrentItem(currentPosition);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                int newPosition = i % mImgList.size();
                loop_dec.setText(mDec[newPosition]);
                ll_dots_container.getChildAt(previousSelectedPosition).setEnabled(false);
                ll_dots_container.getChildAt(newPosition).setEnabled(true);
                previousSelectedPosition = newPosition;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        // 开启轮询
        new Thread(){
            public void run(){
                isRunning = true;
                while(isRunning){
                    try{
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //下一条
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                        }
                    });
                }
            }
        }.start();

    }

//    private void initFragment(){
//        GlaxyFragment mGalaxyFragment = new GlaxyFragment();
//        DailyFragment mDailyFragment = new DailyFragment();
//
//        fragments = new Fragment[]{mGalaxyFragment,mDailyFragment};
//        mFragmentManager = getSupportFragmentManager();
//        //默认显示GlaxyFragment
////        mFragmentManager.beginTransaction()
////                .replace(R.id.menu_page_contriller,mGalaxyFragment)
////                .show(mGalaxyFragment)
////                .commit();
//    }

//    private void initListener(){
//        mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.Galaxy:
//                        if (lastFragment !=0){
//                            MenuActivity.this.switchFragment(lastFragment,0);
//                            lastFragment = 0;
//                        }
//                        return true;
//                    case R.id.Daily:
//                        if (lastFragment !=1){
//                            MenuActivity.this.switchFragment(lastFragment,1);
//                            lastFragment = 1;
//                        }
//                        return true;
//                }
//                return false;
//            }
//        });
//    }

//    private void switchFragment(int lastFragment,int index){
//        FragmentTransaction transaction = mFragmentManager.beginTransaction();
//        transaction.hide(fragments[lastFragment]);
//        if (!fragments[index].isAdded()){
//            transaction.add(R.id.menu_page_contriller,fragments[index]);
//        }
//        transaction.show(fragments[index]).commitAllowingStateLoss();
//    }
}