package com.jk.jkproject.ui.activity;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jk.jkproject.R;
import com.jk.jkproject.base.BActivity;
import com.jk.jkproject.base.BaseFragmentPageAdapter;
import com.jk.jkproject.ui.fragment.LiveContributionParent2Fragment;
import com.jk.jkproject.ui.fragment.LiveContributionParentFragment;
import com.jk.jkproject.ui.fragment.LiveContributionParentFragment3;
import com.jk.jkproject.ui.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Zick
 * @params
 * @date 2020/8/28 9:38 AM
 * @desc 榜单
 */
public class ContributionListActivity extends BActivity implements ViewPager.OnPageChangeListener {


    @BindView(R.id.iv_title)
    ImageView ivTitle;
    @BindView(R.id.tv_title_1)
    TextView tvTitle1;
    @BindView(R.id.tv_title_2)
    TextView tvTitle2;
    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.live_vp_pager)
    NoScrollViewPager vp;
    @BindView(R.id.iv_big)
    ImageView ivBig;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.tv_3)
    TextView tv3;
    @BindView(R.id.rl_1)
    RelativeLayout rl1;
    @BindView(R.id.iv_small)
    ImageView ivSmall;
    private Unbinder bind;
    private int mType = 0; //
    private FragmentManager fragmentManager;
    private List<Fragment> fragList = new ArrayList<Fragment>();
    private BaseFragmentPageAdapter mAdapter;
    private String targetId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contribution_list);
        bind = ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void initData() {
        initVp();
    }

    private void initVp() {
        vp.setNoScroll(true);

        if (mType == 3) {
            fragList.add(LiveContributionParentFragment3.newInstance(targetId));
            tvTitle1.setText("粉丝贡献榜");
            tvTitle1.setTextSize(17);
            tvTitle1.setTextColor(getResources().getColor(R.color.white));
            tvTitle2.setVisibility(View.GONE);
            tvTitle1.setEnabled(false);
        } else {
            fragList.add(LiveContributionParentFragment.newInstance(1));
            fragList.add(LiveContributionParent2Fragment.newInstance(2));
        }
        fragmentManager = getSupportFragmentManager();
        mAdapter = new BaseFragmentPageAdapter(fragmentManager, fragList);
        vp.setAdapter(mAdapter);
        vp.setOffscreenPageLimit(fragList.size());
        vp.setCurrentItem(0);
        vp.addOnPageChangeListener(this);
    }

    private void initView() {
        if (getIntent() != null) {
            mType = getIntent().getIntExtra("type", -1);
            if (mType == 3) {
                targetId = getIntent().getStringExtra("targetId");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    @OnClick({R.id.iv_title, R.id.tv_title_1, R.id.tv_title_2, R.id.tv_1, R.id.tv_2, R.id.tv_3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title:
                finish();
                break;
            case R.id.tv_title_1:
                tvTitle1.setTextSize(17);
                tvTitle2.setTextSize(11);
                vp.setCurrentItem(0);
                break;
            case R.id.tv_title_2:
                tvTitle1.setTextSize(11);
                tvTitle2.setTextSize(17);
                vp.setCurrentItem(1);
                break;
            case R.id.tv_1:
                setInitBg(0);
                vp.setCurrentItem(0);
                break;
            case R.id.tv_2:
                setInitBg(1);
                vp.setCurrentItem(1);
                break;
            case R.id.tv_3:
                setInitBg(2);
                vp.setCurrentItem(2);
                break;
        }
    }

    private void setInitBg(int position) {
        tv1.setBackgroundColor(getResources().getColor(R.color.transparent));
        tv2.setBackgroundColor(getResources().getColor(R.color.transparent));
        tv3.setBackgroundColor(getResources().getColor(R.color.transparent));
        switch (position) {
            case 0:
                tv1.setBackgroundResource(R.drawable.bg_day_btn);
                break;
            case 1:
                tv2.setBackgroundResource(R.drawable.bg_day_btn);
                break;
            case 2:
                tv3.setBackgroundResource(R.drawable.bg_day_btn);
                break;
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
//        switch (position) {
//            case 0:
//                tvTitle1.setTextSize(17);
//                tvTitle2.setTextSize(11);
//                break;
//            case 1:
//                tvTitle1.setTextSize(11);
//                tvTitle2.setTextSize(17);
//                break;
//        }
//        this.vp.setCurrentItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
