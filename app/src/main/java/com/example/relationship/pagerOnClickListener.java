package com.example.relationship;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class pagerOnClickListener implements View.OnClickListener {

    Context mContext;

    public pagerOnClickListener(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.pager_img1:
                Toast.makeText(mContext, "鹰状星云", Toast.LENGTH_SHORT).show();

                break;
            case R.id.pager_img2:
                Toast.makeText(mContext, "N55星云", Toast.LENGTH_SHORT).show();
                break;
            case R.id.pager_img3:
                Toast.makeText(mContext, "礁湖星云", Toast.LENGTH_SHORT).show();
                break;
            case R.id.pager_img4:
                Toast.makeText(mContext, "三裂星云", Toast.LENGTH_SHORT).show();
                break;
            case R.id.pager_img5:
                Toast.makeText(mContext, "马头星云", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
