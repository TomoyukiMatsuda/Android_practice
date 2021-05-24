package com.android.recyclertablayoutsample;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.nshmura.recyclertablayout.RecyclerTabLayout;

import java.util.ArrayList;

public class StoryFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private static final int NUMBER_OF_LOOPS = 10000;
    private ArrayList<StoryFragmentPagerAdapter.PageItem> mPageItems;

    public StoryFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        mPageItems = new ArrayList<>();
    }

    public void setContentTypes(ArrayList<StoryFragmentPagerAdapter.PageItem> contentTypes) {
        mPageItems.clear();
        mPageItems.addAll(contentTypes);
    }

    @Override
    public Fragment getItem(int position) {
        StoryFragmentPagerAdapter.PageItem item = getValueAt(position);
        if (item.contentType == StoryFragmentPagerAdapter.PageItem.ContentType.PICK_UP) {
            return new PickUpFragment();
        } else if (item.contentType == StoryFragmentPagerAdapter.PageItem.ContentType.CLEANEST_LINE) {
            return new CleanestLineFragment();
        } else if (item.contentType == StoryFragmentPagerAdapter.PageItem.ContentType.MOVIE) {
            return new MovieFragment();
        } else if (item.contentType == PageItem.ContentType.NOTICE) {
            return new NoticeFragment();
        } else if (item.contentType == PageItem.ContentType.BOOKMARK_LIST) {
            return new BookmarkListFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return mPageItems.size() * NUMBER_OF_LOOPS;
    }



    // ライブラリrecyclerTabLayoutの中でこのような記述がありここでセットしているよう、
    /*
    @Override
    public void onBindViewHolder(RecyclerTabLayout.DefaultAdapter.ViewHolder holder, int position) {
        CharSequence title = getViewPager().getAdapter().getPageTitle(position);
        holder.title.setText(title);
        holder.title.setSelected(getCurrentIndicatorPosition() == position);
    }
     */
    // TODO: このメソッドの有無でタイトル表示有無が切り替わる
    @Override
    public String getPageTitle(int position) {
        return getValueAt(position).title;
    }

    public StoryFragmentPagerAdapter.PageItem getValueAt(int position) {
        if (mPageItems.size() == 0) {
            return null;
        }
        return mPageItems.get(position % mPageItems.size());
    }

    public int getCenterPosition(int position) {
        return mPageItems.size() * NUMBER_OF_LOOPS / 2 + position;
    }

    public static class PageItem {
        public enum ContentType {
            PICK_UP,
            CLEANEST_LINE,
            MOVIE,
            NOTICE,
            BOOKMARK_LIST
        }

        public ContentType contentType;
        public String title;

        // TODO: viewID は何に使っている？
        //public int viewId;
        public int position;
    }
}
