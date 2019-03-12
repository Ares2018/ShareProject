package cn.daily.share;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.media.UMusic;
import com.zjrb.core.permission.AbsPermCallBack;
import com.zjrb.core.permission.IPermissionOperate;
import com.zjrb.core.permission.Permission;
import com.zjrb.core.permission.PermissionManager;

import java.util.List;

import cn.daily.CompatibleUtils.EMUIUtils;
import shareInterface.UmengShareCallBack;


/**
 * Date: 2018/6/15 上午10:07
 * Email: sisq@8531.cn
 * Author: sishuqun
 * Description: 分享工具类相关方法
 */
public class ShareUtils {

    /**
     * 分享链接 标题,摘要,缩略图及跳转链接等信息通过自己构造UMWeb
     *
     * @param activity
     * @param umWeb         可通过构造umWeb设置标题,摘要,缩略图
     * @param share_media
     * @param shareListener
     */
    public static void shareUrl(Activity activity, UMWeb umWeb, SHARE_MEDIA share_media, UMShareListener shareListener) {
        if (activity == null) {
            return;
        }
        new ShareAction(activity).withMedia(umWeb)
                .setPlatform(share_media)
                .setCallback(shareListener).share();
    }


    /**
     * 分享链接  有标题,摘要
     *
     * @param activity
     * @param url           分享链接
     * @param title         标题
     * @param desc          摘要
     * @param thumb         缩略图  不需要传null
     * @param share_media
     * @param shareListener
     */
    public static void shareUrl(Activity activity, String url, String title, String desc, UMImage thumb, SHARE_MEDIA share_media, UMShareListener shareListener) {
        if (activity == null) {
            return;
        }
        UMWeb web = new UMWeb(url);
        web.setTitle(title);
        web.setDescription(desc);
        if (thumb != null) {
            web.setThumb(thumb);
        }
        new ShareAction(activity).withMedia(web)
                .setPlatform(share_media)
                .setCallback(shareListener).share();
    }

    /**
     * 分享图片
     *
     * @param activity
     * @param umImage
     * @param thumb         缩略图  不需要传null
     * @param share_media
     * @param shareListener
     */
    public static void shareImage(Activity activity, UMImage umImage, UMImage thumb, SHARE_MEDIA share_media, UMShareListener shareListener) {
        if (activity == null || umImage == null) {
            return;
        }
        if (thumb != null) {
            umImage.setThumb(thumb);
        }
        new ShareAction(activity).withMedia(umImage)
                .setPlatform(share_media)
                .setCallback(shareListener).share();
    }


    /**
     * 纯本地图片分享
     *
     * @param activity
     * @param resId
     * @param thumb         缩略图  不需要传null
     * @param share_media
     * @param shareListener
     */
    public static void shareImageLocal(Activity activity, int resId, UMImage thumb, SHARE_MEDIA share_media, UMShareListener shareListener) {
        if (activity == null) {
            return;
        }
        UMImage imagelocal = new UMImage(activity, resId);
        shareImage(activity, imagelocal, thumb, share_media, shareListener);
    }

    /**
     * 分享纯网络图片
     *
     * @param activity
     * @param url
     * @param thumb         缩略图  不需要传null
     * @param share_media
     * @param shareListener
     */
    public static void shareImageNet(Activity activity, String url, UMImage thumb, SHARE_MEDIA share_media, UMShareListener shareListener) {
        if (activity == null) {
            return;
        }
        UMImage imageurl = new UMImage(activity, url);
        shareImage(activity, imageurl, thumb, share_media, shareListener);

    }

    /**
     * 图片分享,bitmap
     *
     * @param activity
     * @param bitmap
     * @param thumb         缩略图  不需要传null
     * @param share_media
     * @param shareListener
     */
    public void shareImageBitmap(Activity activity, Bitmap bitmap, UMImage thumb, SHARE_MEDIA share_media, UMShareListener shareListener) {

        if (activity == null || bitmap == null) {
            return;
        }
        UMImage imageBitmap = new UMImage(activity, bitmap);
        shareImage(activity, imageBitmap, thumb, share_media, shareListener);
    }

    /**
     * 纯文本分享
     *
     * @param activity
     * @param text
     * @param share_media
     * @param shareListener
     */
    public static void shareText(Activity activity, String text, SHARE_MEDIA share_media, UMShareListener shareListener) {
        if (activity == null) {
            return;
        }
        new ShareAction(activity).withText(text)
                .setPlatform(share_media)
                .setCallback(shareListener).share();
    }


    /**
     * 音乐分享 说明:播放链接是指在微信qq分享音乐，是可以在当前聊天界面播放的，要求这个musicurl（播放链接）必须要以.mp3等音乐格式结尾，跳转链接是指点击linkcard之后进行跳转的链接。
     *
     * @param activity
     * @param musicurl      播放链接
     * @param url           跳转链接
     * @param share_media
     * @param shareListener
     */
    public static void shareMusic(Activity activity, String musicurl, String url, String title, String desc, SHARE_MEDIA share_media, UMShareListener shareListener) {
        if (activity == null) {
            return;
        }
        UMusic music = new UMusic(musicurl);
        music.setTitle(title);
        music.setDescription(desc);
        music.setmTargetUrl(url); // 跳转链接
        new ShareAction(activity).withMedia(music)
                .setPlatform(share_media)
                .setCallback(shareListener).share();
    }

    /**
     * 视频分享 说明:视频只能使用网络视频
     *
     * @param activity
     * @param videourl
     * @param title
     * @param desc
     * @param share_media
     * @param shareListener
     */
    public static void shareVideo(Activity activity, String videourl, String title, String desc, SHARE_MEDIA share_media, UMShareListener shareListener) {
        if (activity == null) {
            return;
        }
        UMVideo video = new UMVideo(videourl);
        video.setTitle(title);
        video.setDescription(desc);
        new ShareAction(activity).withMedia(video)
                .setPlatform(share_media)
                .setCallback(shareListener).share();
    }

    /**
     * 本地图文分享
     *
     * @param activity
     * @param text
     * @param resId
     * @param thumb         缩略图  不需要传null
     * @param share_media
     * @param shareListener
     */
    public static void shareTextAndImage(Activity activity, String text, int resId, UMImage thumb, SHARE_MEDIA share_media, UMShareListener shareListener) {
        if (activity == null) {
            return;
        }
        UMImage imagelocal = new UMImage(activity, resId);
        if (thumb != null) {
            imagelocal.setThumb(thumb);
        }
        new ShareAction(activity).withText(text)
                .withMedia(imagelocal)
                .setPlatform(share_media)
                .setCallback(shareListener).share();
    }

    /**
     * 检测应用是否安装,如果是新浪则不检测,因为新浪未安装会打开web页面
     */
    public static boolean checkInstall(@NonNull Context context, @NonNull SHARE_MEDIA platform, @NonNull UmengShareCallBack callBack) {
        if (context == null || !(context instanceof Activity)) {
            return false;
        }
        if (platform != SHARE_MEDIA.SINA) {
            if (!UMShareAPI.get(context).isInstall((Activity) context, platform)) {
                if (callBack != null) {
                    callBack.onInstallOut(platform);
                }
                return false;
            }
        }
        return true;
    }

    /**
     * qq平台相关及华为手机检测权限
     *
     * @param context
     * @param media
     * @return
     */
    public static boolean checkPerssion(@NonNull Context context, @NonNull SHARE_MEDIA media) {
        if (context == null) {
            return false;
        }
        if (EMUIUtils.isEMUI() || media == SHARE_MEDIA.QZONE || media == SHARE_MEDIA.QQ) {
            final boolean[] result = {false};
            PermissionManager.get().request((IPermissionOperate) context, new AbsPermCallBack() {
                /**
                 * 全部授予
                 * @param isAlready 申请之前已全部默认授权
                 */
                @Override
                public void onGranted(boolean isAlready) {
                    result[0] = true;
                }

                /**
                 * 拒绝(至少一个权限拒绝)
                 *
                 * @param neverAskPerms 被拒绝不再询问权限集合
                 */
                @Override
                public void onDenied(List<String> neverAskPerms) {
                    result[0] = false;
                }
            }, Permission.STORAGE_READE, Permission.STORAGE_WRITE);
            return result[0];
        } else {
            return true;
        }

    }

}
