package cn.daily.share;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;

import shareInterface.UMengWrapShareListener;
import shareInterface.UmengShareCallBack;

/**
 * Date: 2019/1/24 4:28 PM
 * Email: sisq@8531.cn
 * Author: sishuqun
 * Description: 分享构建基类
 */
public class ShareBuilder {
    protected Context mContext;
    protected ShareAction mShareAction;
    protected SHARE_MEDIA mShareMedia; // 平台类型
    protected UmengShareCallBack mUmengShareCallBack;
    public String mText;

    public ShareBuilder(@NonNull Context context) {
        if (!(context instanceof Activity)) {
            throw new IllegalArgumentException("Context must be an Activity");
        }
        mContext = context;
        mShareAction = new ShareAction((Activity) mContext);
    }

    public ShareBuilder platform(@NonNull SHARE_MEDIA media) {
        mShareMedia = media;
        return this;
    }

    public ShareBuilder callback(@NonNull UmengShareCallBack callBack) {
        this.mUmengShareCallBack = callBack;
        return this;
    }

    /**
     * 安装包检测
     */
    public void share() {
        // 安装包检测
        if (!ShareUtils.checkInstall(mContext, mShareMedia, mUmengShareCallBack)) {
            return;
        }
        mShareAction.setPlatform(mShareMedia)
                .setCallback(new UMengWrapShareListener(mUmengShareCallBack))
                .share();
    }


    public ShareBuilder text(String text) {
        this.mText = text;
        return this;
    }


}
