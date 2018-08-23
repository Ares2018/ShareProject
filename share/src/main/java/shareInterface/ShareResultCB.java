package shareInterface;

import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * 外部调用分享结果接口，支持扩展继承
 * Created by wanglinjie.
 * create time:2018/8/10  上午10:04
 */
public interface ShareResultCB {
    /**
     * 分享结果
     *
     * @param share_media
     */
    void onResult(SHARE_MEDIA share_media);

    /**
     * 分享返回错误
     *
     * @param share_media
     * @param throwable
     */
    void onError(SHARE_MEDIA share_media, Throwable throwable);

    /**
     * 取消分享
     *
     * @param share_media
     */
    void onCancel(SHARE_MEDIA share_media);
}
