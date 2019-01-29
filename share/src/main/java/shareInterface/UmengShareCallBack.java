package shareInterface;

import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * Date: 2019/1/24 4:44 PM
 * Email: sisq@8531.cn
 * Author: sishuqun
 * Description: 回调接口封装
 */
public interface UmengShareCallBack {

    /**
     * 分享开始
     *
     * @param share_media
     */
    void onStart(SHARE_MEDIA share_media);

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

    /**
     * 未安装相关应用
     * @param platform
     */
    void onInstallOut(SHARE_MEDIA platform);

    /**
     * 权限被拒绝
     */
    void onPermissonDeny(); // 权限被拒绝

    /**
     * 分享文本为空
     */
    void onTextEmpty(SHARE_MEDIA share_media, String text);

    /**
     * 分享图片为空
     */
    void onImageEmpty(SHARE_MEDIA share_media);

    /**
     * 分享链接为空
     */
    void onWebUrlEmpty(SHARE_MEDIA share_media);

    /**
     * 分享内容
     */
    void onShareEmpty(SHARE_MEDIA share_media);
}
