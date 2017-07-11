package com.example.fenglei.pullrefreshandloadmore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.fenglei.pullrefreshandloadmore.adapter.MyAdapter;
import com.example.fenglei.pullrefreshandloadmore.pullrefresh.PullRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PullRefreshLayout mPullRefreshLayout;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManger;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPullRefreshLayout = (PullRefreshLayout) findViewById(R.id.pull_refresh_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        initRecyclerView();
    }

    private void initRecyclerView() {
        mLayoutManger = new LinearLayoutManager(this);
        mAdapter = new MyAdapter(initDataList());
        mRecyclerView.setLayoutManager(mLayoutManger);
        mRecyclerView.setAdapter(mAdapter);
    }

    private List<String> initDataList() {
        List<String> dataList = new ArrayList<>();
        for(int i = 0; i < 50; i++) {
            dataList.add(String.valueOf(i));
        }
        return dataList;
    }

}
