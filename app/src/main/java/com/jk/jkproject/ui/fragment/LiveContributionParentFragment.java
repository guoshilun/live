package com.jk.jkproject.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jk.jkproject.R;
import com.jk.jkproject.base.BFragment;
import com.jk.jkproject.base.BaseFragmentPageAdapter;
import com.jk.jkproject.net.AppApis;
import com.jk.jkproject.net.Urls;
import com.jk.jkproject.ui.activity.MyHomePageActivity;
import com.jk.jkproject.ui.entity.LiveRankInfoMyBean;
import com.jk.jkproject.ui.model.PostResult;
import com.jk.jkproject.ui.widget.NoScrollViewPager;
import com.jk.jkproject.utils.Constants;
import com.jk.jkproject.utils.OnClickUtils;
import com.jk.jkproject.utils.SPUtils;
import com.jk.jkproject.utils.UserLevelSetUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Zick
 * @params
 * @date 2020/8/28 9:42 AM
 * @desc 榜单Fragment
 */
public class LiveContributionParentFragment extends BFragment implements ViewPager.OnPageChangeListener {
    private static String tab_type = "tab_type";
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.tv_3)
    TextView tv3;
    @BindView(R.id.rl_1)
    RelativeLayout rl1;
    @BindView(R.id.live_vp_pager)
    NoScrollViewPager vp;
    @BindView(R.id.iv_small)
    ImageView ivSmall;
    @BindView(R.id.iv_emply)
    ImageView ivEmply;
    @BindView(R.id.tv_rank_number)
    TextView tvRankNumber;
    @BindView(R.id.sdv_header)
    SimpleDraweeView sdvHeader;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_user_level)
    TextView tvUserLevel;
    @BindView(R.id.tv_hot_count)
    TextView tvHotCount;
    @BindView(R.id.rl_2)
    RelativeLayout rl2;
    @BindView(R.id.rl_item)
    RelativeLayout rlItem;

    Unbinder unbinder;
    private List<Fragment> fragList = new ArrayList<Fragment>();

    private int tabName; //1.用户榜 2.主播榜
    private int type = 1;//1.日榜 2.周榜 3.月榜
    private FragmentManager fragmentManager;
    private BaseFragmentPageAdapter mAdapter;
    private LiveContributionListFragment fragment;
    private LiveContributionListFragment fragment2;
    private LiveContributionListFragment fragment1;

    public static LiveContributionParentFragment newInstance(int position) {
        LiveContributionParentFragment liveHomeNearFragment = new LiveContributionParentFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(tab_type, position);
        liveHomeNearFragment.setArguments(bundle);
        return liveHomeNearFragment;
    }


    protected void initData() {
        super.initData();
        if (getArguments() != null) {
            tabName = getArguments().getInt(tab_type);
        }
        setMyRankData();
        ivSmall.setImageResource(R.mipmap.icon_use_contribution_head_bg);
        initVp();
    }

    private void initVp() {
        vp.setNoScroll(true);
        fragment = LiveContributionListFragment.newInstance(1, 1);
        fragment1 = LiveContributionListFragment.newInstance(1, 2);
        fragment2 = LiveContributionListFragment.newInstance(1, 3);
        fragList.add(fragment);
        fragList.add(fragment1);
        fragList.add(fragment2);
        fragmentManager = getChildFragmentManager();
        mAdapter = new BaseFragmentPageAdapter(fragmentManager, fragList);
        vp.setAdapter(mAdapter);
        vp.setOffscreenPageLimit(fragList.size());
        vp.setCurrentItem(0);
        vp.addOnPageChangeListener(this);
    }

    @Override
    public void onSuccess(String url, Object obj) {
        super.onSuccess(url, obj);
        if (url.equals(Urls.GET_USER_RANK_MY)) {
            if (obj != null && obj instanceof LiveRankInfoMyBean) {
                LiveRankInfoMyBean infoMyBean = (LiveRankInfoMyBean) obj;
                if (infoMyBean.getCode() == 200) {
                    rlItem.setVisibility(View.VISIBLE);
                    setDataView(infoMyBean.getData());
                } else {
                    rlItem.setVisibility(View.GONE);
                }
            }
        }
    }

    private void setDataView(LiveRankInfoMyBean.DataBean data) {
        if (data.getId() >= 99) {
            tvRankNumber.setText("99+");
        } else if (data.getId() >= 9) {
            tvRankNumber.setText(data.getId() + "");
        } else if (data.getId() > 0) {
            tvRankNumber.setText("0" + data.getId());
        } else if (data.getId() == -2) {
            tvRankNumber.setText("未上榜");
        }
        sdvHeader.setImageURI(data.getPicture());
        tvUserName.setText(data.getNickname());
        tvUserLevel.setText(data.getUserGrade());
        UserLevelSetUtils.setUserLevel(tvUserLevel, data.getUserGrade());
        tvHotCount.setText(data.getSum_amount() + "钻石");
    }

    protected void initView(View paramView) {
        super.initView(paramView);
    }

    protected boolean isBindEventBusHere() {
        return true;
    }

    @Override
    public void onEvent(PostResult postResult) {
        super.onEvent(postResult);
        if (postResult.getTag().equals(Constants.EVENTBUS.EVENTBUS_CONTRIBUTION_USER_EMPLY)) {
            switch ((int) postResult.getResult()) {
                case 1:
                    ivEmply.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    ivEmply.setVisibility(View.GONE);
                    break;
            }
        }
    }

    protected int setLayoutId() {
        return R.layout.fragment_contribution_parent;
    }

    @OnClick({R.id.tv_1, R.id.tv_2, R.id.tv_3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_1:
                setInitBg(0);
                vp.setCurrentItem(0);
                type = 1;
                setMyRankData();
                if (fragment != null) {
                    fragment.getData(0);
                }
                break;
            case R.id.tv_2:
                setInitBg(1);
                vp.setCurrentItem(1);
                type = 2;
                setMyRankData();
                if (fragment1 != null) {
                    fragment1.getData(0);
                }
                break;
            case R.id.tv_3:
                setInitBg(2);
                vp.setCurrentItem(2);
                type = 3;
                setMyRankData();
                if (fragment2 != null) {
                    fragment2.getData(0);
                }
                break;
        }
    }

    private void setMyRankData() {
        AppApis.getMyUseRank(type, this);
    }

    private void setInitBg(int position) {
        tv1.setBackgroundColor(getResources().getColor(R.color.transparent));
        tv2.setBackgroundColor(getResources().getColor(R.color.transparent));
        tv3.setBackgroundColor(getResources().getColor(R.color.transparent));
        switch (position) {
            case 0:
                tv1.setBackgroundResource(R.drawable.bg_day_btn);
//                if (fragment != null) {
//                    fragment.getData(0);
//                }
                break;
            case 1:
                tv2.setBackgroundResource(R.drawable.bg_day_btn);
//                if (fragment1 != null) {
//                    fragment1.getData(1);
//                }
                break;
            case 2:
                tv3.setBackgroundResource(R.drawable.bg_day_btn);
//                if (fragment2 != null) {
//                    fragment2.getData(2);
//                }
                break;
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setInitBg(position);
        vp.setCurrentItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @OnClick(R.id.rl_item)
    public void onViewClicked() {
        if (OnClickUtils.isFastClick()) {
            Intent intent = new Intent(getContext(), MyHomePageActivity.class);
            intent.putExtra("u_id", SPUtils.getUserId());
            getActivity().startActivity(intent);
        }
    }
}