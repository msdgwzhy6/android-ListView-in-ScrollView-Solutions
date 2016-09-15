package com.cxmscb.cxm.scrolllistview;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView comentListView;
    private MyListView myListView;
    private ScollListView scrollListView;


    private ScrollView mScrollView;
    private String[] adapterData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        comentListView = (ListView) findViewById(R.id.commentlist);
        myListView = (MyListView) findViewById(R.id.mylist);
        scrollListView = (ScollListView) findViewById(R.id.scrolllist);

        mScrollView = (ScrollView) findViewById(R.id.scrollView);
        getData();
        comentListView.setFocusable(false);
        myListView.setFocusable(false);

        comentListView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,adapterData));
        myListView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,adapterData));
        scrollListView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,adapterData));
       /* scrollListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent ev) {
                switch (ev.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        scrollListView.getParent().requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        scrollListView.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                return false;
            }
        });*/
        setListViewHeightBasedOnChildren(comentListView);

       /* mScrollView.post(new Runnable() {
            @Override
            public void run() {
                mScrollView.scrollTo(0,0);
            }
        });*/


    }

    private void getData() {
        adapterData = new String[] { "Afghanistan", "Albania", "Algeria",
                "American Samoa", "Andorra", "Angola", "Anguilla" };
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1))+listView.getPaddingTop()+listView.getPaddingBottom();
        listView.setLayoutParams(params);
    }
}
