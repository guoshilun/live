package com.jk.lib_egg

import android.content.Context
import android.graphics.Color
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import android.text.Html
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.TextView
import com.fondesa.recyclerviewdivider.RecyclerViewDivider
import com.jk.lib_egg.pojo.EggBean
import com.opensource.svgaplayer.SVGACallback
import com.opensource.svgaplayer.SVGAImageView
import com.opensource.svgaplayer.SVGAParser
import com.opensource.svgaplayer.SVGAVideoEntity
import razerdp.basepopup.BasePopupWindow
import razerdp.util.animation.AnimationHelper
import razerdp.util.animation.TranslationConfig

/**
 *@author  fansan
 *@version 2020/10/28
 */

class EggPopup(c: Context) : BasePopupWindow(c) {

    init {
        popupGravity = Gravity.BOTTOM
        setOutSideDismiss(true)
        isOutSideTouchable = false
    }

    var hammerType:Int = context.getSharedPreferences("eggType",Context.MODE_PRIVATE)
        .let {
            it.getInt("hammerType",2)
        }
    var isTenCombo = false
    var showResult: (() -> Unit)? = null
    var diamondClick: (() -> Unit)? = null
    var pocketClick: (() -> Unit)? = null
    var ruleClick: (() -> Unit)? = null
    private lateinit var hammerImg: SVGAImageView
    private lateinit var eggImg: SVGAImageView
    private lateinit var copperLabel: View
    private lateinit var goldenLabel: View
    private lateinit var knockScope: View
    private lateinit var copper: CardView
    private lateinit var golden: CardView
    private lateinit var combo: ImageView
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mBroadcastView: TextView
    private lateinit var mBroadcastLayout: View
    private lateinit var copperPrice: TextView
    private lateinit var goldenPrice: TextView
    private lateinit var mDiamond: TextView
    private lateinit var mBroadcastDiamond: TextView
    private lateinit var mLoading: View
    private lateinit var mPocket: View
    private lateinit var mAdapter: GiftAdapter
    private var copperGiftList: List<EggBean.Data.GoldenMalletAward> = mutableListOf()
    private var goldenGiftList: List<EggBean.Data.GoldenMalletAward> = mutableListOf()
    private var request = false

    override fun onCreateContentView(): View {
        val contentView = createPopupById(R.layout.popup_egg_main)
        golden = contentView.findViewById(R.id.goldenHammer)
        copper = contentView.findViewById(R.id.copperHammer)
        hammerImg = contentView.findViewById(R.id.hammerImg)
        mLoading = contentView.findViewById(R.id.progress)
        eggImg = contentView.findViewById(R.id.eggImg)
        knockScope = contentView.findViewById(R.id.knockScope)
        mBroadcastLayout = contentView.findViewById(R.id.broadcastLayout)
        mBroadcastView = contentView.findViewById(R.id.broadcast)
        mBroadcastDiamond = contentView.findViewById(R.id.broadcastDiamond)
        combo = contentView.findViewById(R.id.combo)
        mPocket = contentView.findViewById(R.id.pocket)
        copperLabel = contentView.findViewById(R.id.copperLabel)
        mDiamond = contentView.findViewById<TextView>(R.id.diamond)
        mDiamond.click {
            diamondClick?.invoke()
        }

        contentView.findViewById<View>(R.id.rule).click {
            ruleClick?.invoke()
        }


        mRecyclerView = contentView.findViewById(R.id.recyclerView)
        goldenLabel = contentView.findViewById(R.id.goldenLabel)
        copperPrice = contentView.findViewById<TextView>(R.id.copperPrice)
        goldenPrice = contentView.findViewById<TextView>(R.id.goldenPrice)

        golden.click {
            if (hammerType != 1) {
                hammerType = 1
                setHammer()
            }
        }
        mAdapter = GiftAdapter()
        mRecyclerView.adapter = mAdapter
        RecyclerViewDivider.with(context).asSpace().size(6).build().addTo(mRecyclerView)

        combo.setOnClickListener {
            isTenCombo = !isTenCombo
            combo.setImageResource(if (isTenCombo) R.drawable.combo_10_selected else R.drawable.combo_10_normal)
        }

        copper.setOnClickListener {
            if (hammerType != 2) {
                hammerType = 2
                setHammer()
            }
        }

        knockScope.setOnClickListener {
            hammerKnock()
            setEgg(1)
            knockScope.isEnabled = false
        }

        mPocket.setOnClickListener {
            pocketClick?.invoke()
        }
        return contentView
    }

    private fun setEgg(type: Int) {
        val parser = SVGAParser(context)
        val assets = if (type == 0) {
            "egg_normal.svga"
        } else {
            "egg_crash.svga"
        }
        parser.decodeFromAssets(assets, object : SVGAParser.ParseCompletion {
            override fun onComplete(videoItem: SVGAVideoEntity) {
                eggImg.loops = type
                eggImg.setVideoItem(videoItem)
                eggImg.stepToFrame(0, true)
                if (type == 1) {
                    combo.isEnabled = false
                }
                if (type == 1) {
                    eggImg.callback = object : SVGACallback {
                        override fun onFinished() {
                            eggImg.callback = null
                            setEgg(0)
                            knockScope.isEnabled = true
                            combo.isEnabled = true
                            request = false
                            Log.d("fansan", "egg onFinished")
                        }

                        override fun onPause() {

                        }

                        override fun onRepeat() {

                        }

                        override fun onStep(frame: Int, percentage: Double) {
                            if (!request && percentage > 0.7){
                                request = true
                                showResult?.invoke()
                            }
                        }

                    }
                }
            }

            override fun onError() {
                Log.d("fansan", "svga error")
            }
        })
    }

    private fun hammerKnock() {
        val parser = SVGAParser(context)
        val assets = if (hammerType == 2) {
            "copper_hammer_knock.svga"
        } else {
            "golden_hammer_knock.svga"
        }

        parser.decodeFromAssets(assets, object : SVGAParser.ParseCompletion {
            override fun onComplete(videoItem: SVGAVideoEntity) {
                hammerImg.loops = 1
                hammerImg.setVideoItem(videoItem)
                hammerImg.stepToFrame(0, true)
                hammerImg.callback = object : SVGACallback {
                    override fun onFinished() {
                        hammerImg.callback = null
                        setHammer()
                    }

                    override fun onPause() {

                    }

                    override fun onRepeat() {

                    }

                    override fun onStep(frame: Int, percentage: Double) {

                    }
                }
            }

            override fun onError() {
                Log.d("fansan", "svga error")
            }
        })
    }

    private fun setHammer() {
        val parser = SVGAParser(context)
        val assets = if (hammerType == 2) {
            copperLabel.setBackgroundColor(Color.parseColor("#FF8D31"))
            copper.setCardBackgroundColor(Color.parseColor("#ffffff"))
            goldenLabel.setBackgroundColor(Color.parseColor("#625166"))
            golden.setCardBackgroundColor(Color.parseColor("#80cccccc"))
            mAdapter.setNewData(copperGiftList)
            "copper_hammer_normal.svga"
        } else {
            goldenLabel.setBackgroundColor(Color.parseColor("#FF8D31"))
            golden.setCardBackgroundColor(Color.parseColor("#ffffff"))
            copperLabel.setBackgroundColor(Color.parseColor("#625166"))
            copper.setCardBackgroundColor(Color.parseColor("#80cccccc"))
            mAdapter.setNewData(goldenGiftList)
            "golden_hammer_normal.svga"
        }

        parser.decodeFromAssets(assets, object : SVGAParser.ParseCompletion {
            override fun onComplete(videoItem: SVGAVideoEntity) {
                hammerImg.loops = 0
                hammerImg.setVideoItem(videoItem)
                hammerImg.stepToFrame(0, true)
            }

            override fun onError() {
                Log.d("fansan", "svga error")
            }
        })
    }

    fun setBroadcast(username: String?, giftName: String?, price: String?) {
        mBroadcastLayout.visible()
        mBroadcastView.text = Html.fromHtml(
            context.resources.getString(
                R.string.egg_broadcast, username, giftName
            )
        )
        mBroadcastDiamond.text = Html.fromHtml(
            context.resources.getString(
                R.string.egg_broadcast_diamond, price
            )
        )
    }

    fun setData(eggBean: EggBean.Data) {
        copperPrice.text =
            Html.fromHtml(context.resources.getString(R.string.diamond_price, eggBean.hammerMallet))
        goldenPrice.text =
            Html.fromHtml(context.resources.getString(R.string.diamond_price, eggBean.goldenMallet))
        goldenGiftList = eggBean.goldenMalletAwardList
        copperGiftList = eggBean.hammerMalletAwardList
        mAdapter.setNewData(copperGiftList)
        mLoading.gone()
    }

    fun setDiamond(diamond: Int) {
        mDiamond.text = diamond.toString()
    }

    override fun onCreateShowAnimation(): Animation {
        return AnimationHelper.asAnimation().withTranslation(TranslationConfig.FROM_BOTTOM)
            .toShow()
    }



    override fun onCreateDismissAnimation(): Animation {
        return AnimationHelper.asAnimation().withTranslation(TranslationConfig.TO_BOTTOM)
            .toShow()
    }

    override fun onDismiss() {
        context.getSharedPreferences("eggType",Context.MODE_PRIVATE)
            .apply {
                edit().putInt("hammerType",hammerType)
                    .apply()
            }
        super.onDismiss()
    }

    override fun showPopupWindow() {
        super.showPopupWindow()
        setHammer()
        setEgg(0)
    }
}
