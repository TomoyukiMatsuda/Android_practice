package com.android.recyclertablayoutsample;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.android.recyclertablayoutsample.databinding.FragmentStoryBinding;

import java.util.ArrayList;

public class StoryFragment extends Fragment implements ViewPager.OnPageChangeListener {

    private ArrayList<StoryFragmentPagerAdapter.PageItem> mContentTypes = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentStoryBinding mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_story, container, false);
        StoryFragmentPagerAdapter mAdapter = new StoryFragmentPagerAdapter(getChildFragmentManager());
        createTabItem(StoryFragmentPagerAdapter.PageItem.ContentType.PICK_UP,  "ストーリー", 1);
        createTabItem(StoryFragmentPagerAdapter.PageItem.ContentType.CLEANEST_LINE, "クリーネストラインn",  2);
        createTabItem(StoryFragmentPagerAdapter.PageItem.ContentType.MOVIE, "フィルム", 3);
        createTabItem(StoryFragmentPagerAdapter.PageItem.ContentType.NOTICE, "お知らせ", 4);
        createTabItem(StoryFragmentPagerAdapter.PageItem.ContentType.BOOKMARK_LIST, "ブックマーク", 5);
        mAdapter.setContentTypes(mContentTypes);

        mBinding.viewPager.setAdapter(mAdapter);
        mBinding.viewPager.setCurrentItem(mAdapter.getCenterPosition(0));
        mBinding.viewPager.addOnPageChangeListener(this);
        mBinding.recyclerTabLayout.setUpWithViewPager(mBinding.viewPager);


        mBinding.viewPager.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (mBinding.viewPager.getViewTreeObserver().isAlive()) {
                    mBinding.viewPager.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                mBinding.recyclerTabLayout.setCurrentItem(mAdapter.getCenterPosition(0), false);
            }
        });

        return mBinding.getRoot();
    }


    /**
     * タブItemを作成する。
     *
     * @param contentType PageItem.ContentType
     * @param title       タイトル文字列
     * // @param viewId      タブItemのViewId
     * @param position    タブのインデックス
     */
    private void createTabItem(StoryFragmentPagerAdapter.PageItem.ContentType contentType, String title, int position) {
        StoryFragmentPagerAdapter.PageItem item = new StoryFragmentPagerAdapter.PageItem();
        item.contentType = contentType;
        item.title = title;
        //item.viewId = viewId;
        item.position = position;
        mContentTypes.add(item);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
