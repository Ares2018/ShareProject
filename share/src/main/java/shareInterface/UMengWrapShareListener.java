package shareInterface;

import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * Date: 2019/1/24 5:04 PM
 * Email: sisq@8531.cn
 * Author: sishuqun
 * Description: UMShareListener包装
 */
public class UMengWrapShareListener implements UMShareListener {

    private UmengShareCallBack mUmengShareCallBack;

    public UMengWrapShareListener(UmengShareCallBack callBack) {
        this.mUmengShareCallBack = callBack;
    }

    @Override
    public void onStart(SHARE_MEDIA share_media) {
        if (mUmengShareCallBack != null) {
            mUmengShareCallBack.onStart(share_media);
        }
    }

    @Override
    public void onResult(SHARE_MEDIA share_media) {
        if (mUmengShareCallBack != null) {
            mUmengShareCallBack.onResult(share_media);
        }
    }

    @Override
    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
        if (mUmengShareCallBack != null) {
            mUmengShareCallBack.onError(share_media, throwable);
        }
    }

    @Override
    public void onCancel(SHARE_MEDIA share_media) {
        if (mUmengShareCallBack != null) {
            mUmengShareCallBack.onCancel(share_media);
        }
    }
}
