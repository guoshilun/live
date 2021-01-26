package com.jk.jkproject.ui.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jk.jkproject.R;
import com.jk.jkproject.base.BFragment;
import com.jk.jkproject.base.BaseFragmentPageAdapter;
import com.jk.jkproject.ui.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class LiveHomeNearParentFragment extends BFragment implements ViewPager.OnPageChangeListener {
    private static String tab_type = "tab_type";
    @BindView(R.id.tv_title_1)
    TextView tvTitle1;
    @BindView(R.id.iv_tab_line)
    ImageView ivTabLine;
    @BindView(R.id.tv_title_2)
    TextView tvTitle2;
    @BindView(R.id.iv_tab_line1)
    ImageView ivTabLine1;
    @BindView(R.id.ll_header)
    LinearLayout llHeader;
    @BindView(R.id.live_vp_pager)
    NoScrollViewPager vp;

    private List<Fragment> fragList = new ArrayList<Fragment>();

    private FragmentManager fragmentManager;


    private BaseFragmentPageAdapter mAdapter;

    private String tabName = "";
    private LiveHomeNearFragment fragment1;
    private LiveHomeNearFragment2 fragment2;

    private void initVp() {
//        this.vp.setNoScroll(true);
        fragment1 = LiveHomeNearFragment.newInstance(1);
        fragment2 = LiveHomeNearFragment2.newInstance(2);
        fragList.add(fragment1);
        fragList.add(fragment2);
        fragmentManager = getChildFragmentManager();
        mAdapter = new BaseFragmentPageAdapter(this.fragmentManager, this.fragList);
        vp.setAdapter((PagerAdapter) this.mAdapter);
        vp.setOffscreenPageLimit(this.fragList.size());
        vp.setCurrentItem(0);
        vp.addOnPageChangeListener(this);
    }

    public static LiveHomeNearParentFragment newInstance(int paramInt) {
        LiveHomeNearParentFragment liveHomeNearParentFragment = new LiveHomeNearParentFragment();
        Bundle bundle = new Bundle();
//        bundle.putInt(tab_type, paramInt);
        liveHomeNearParentFragment.setArguments(bundle);
        return liveHomeNearParentFragment;
    }

    protected void initData() {
        super.initData();
        initVp();
    }

    protected void initView(View paramView) {
        super.initView(paramView);
    }

    protected boolean isBindEventBusHere() {
        return false;
    }

    protected int setLayoutId() {
        return R.layout.fragment_home_near_parent;
    }

    @OnClick({R.id.tv_title_1, R.id.tv_title_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_title_1:
                tvTitle1.setTextColor(getResources().getColor(R.color.color_D83FDD));
                tvTitle2.setTextColor(getResources().getColor(R.color.color_333));
                ivTabLine1.setVisibility(View.INVISIBLE);
                ivTabLine.setVisibility(View.VISIBLE);
                vp.setCurrentItem(0);
                break;
            case R.id.tv_title_2:
                tvTitle2.setTextColor(getResources().getColor(R.color.color_D83FDD));
                tvTitle1.setTextColor(getResources().getColor(R.color.color_333));
                ivTabLine1.setVisibility(View.VISIBLE);
                ivTabLine.setVisibility(View.INVISIBLE);
                vp.setCurrentItem(1);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                this.tvTitle1.setTextColor(getResources().getColor(R.color.color_D83FDD));
                this.tvTitle2.setTextColor(getResources().getColor(R.color.color_333));
                this.ivTabLine1.setVisibility(View.INVISIBLE);
                this.ivTabLine.setVisibility(View.VISIBLE);
                break;
            case 1:
                this.tvTitle2.setTextColor(getResources().getColor(R.color.color_D83FDD));
                this.tvTitle1.setTextColor(getResources().getColor(R.color.color_333));
                this.ivTabLine1.setVisibility(View.VISIBLE);
                this.ivTabLine.setVisibility(View.INVISIBLE);
                break;
        }
        this.vp.setCurrentItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}