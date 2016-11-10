package com.example.androidexp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

import com.example.androidexp.R;
import com.example.androidexp.Views.DragGridView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class DragActivity extends Activity {

    private List<HashMap<String, Object>> dataSourceList = new ArrayList<HashMap<String, Object>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag);

        DragGridView mDragGridView = (DragGridView) findViewById(R.id.dragGridView);
        for (int i = 0; i < 30; i++) {
            HashMap<String, Object> itemHashMap = new HashMap<String, Object>();
            itemHashMap.put("item_image",R.mipmap.ic_launcher);
            itemHashMap.put("item_text", "拖拽 " + Integer.toString(i));
            dataSourceList.add(itemHashMap);
        }


        final SimpleAdapter mSimpleAdapter = new SimpleAdapter(this, dataSourceList,
                R.layout.item, new String[] { "item_image", "item_text" },
                new int[] { R.id.item_image, R.id.item_text });

        mDragGridView.setAdapter(mSimpleAdapter);

        mDragGridView.setOnChangeListener(new DragGridView.OnChanageListener() {

            @Override
            public void onChange(int from, int to) {
                HashMap<String, Object> temp = dataSourceList.get(from);
                //直接交互item
//              dataSourceList.set(from, dataSourceList.get(to));
//              dataSourceList.set(to, temp);
//              dataSourceList.set(to, temp);


                //这里的处理需要注意下
                if (from < to) {
                    for (int i = from; i < to; i++) {
                        Collections.swap(dataSourceList, i, i + 1);
                    }
                } else if (from > to) {
                    for (int i = from; i > to; i--) {
                        Collections.swap(dataSourceList, i, i - 1);
                    }
                }

                dataSourceList.set(to, temp);

                mSimpleAdapter.notifyDataSetChanged();
            }
        });
    }
}
