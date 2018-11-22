package cn.daily.share;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.aliya.permission.Permission;
import com.aliya.permission.PermissionCallback;
import com.aliya.permission.PermissionManager;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.media.UMusic;

import java.util.List;

import cn.zgy.utils.utils.CompatibleUtils.EMUIUtils;
import cn.zgy.utils.utils.ImageUtils;
import cn.zgy.utils.utils.UIUtils;
import shareInterface.ShareHelpCallBack;
import shareInterface.ShareResultCB;

/**
 * 友盟分享工具类
 * Created by wanglinjie on 16/11/17.
 */

final public class UmengShareUtils {

    private UMShareAPI mShareAPI;
    private UmengShareBean mBean;
    private ShareResultCB shareResultCB;
    private ShareHelpCallBack shareHelpCallBack;
    private static volatile UmengShareUtils instance = null;


    private UmengShareUtils() {
        mShareAPI = UMShareAPI.get(UIUtils.getActivity());
        shareResultCB = (ShareResultCB) UIUtils.getActivity();
        shareHelpCallBack = (ShareHelpCallBack) UIUtils.getActivity();
    }

    /**
     * 分享单例
     *
     * @return
     */
    public static UmengShareUtils getInstance() {
        if (instance == null) {
            synchronized (UmengShareUtils.class) {
                if (instance == null) {
                    instance = new UmengShareUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 分享回调监听
     */
    private UMShareListener umShareListener = new UMShareListener() {

        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {
            if (shareResultCB != null) {
                shareResultCB.onResult(share_media);
            }

        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
            if (shareResultCB != null) {
                shareResultCB.onError(share_media, throwable);
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
            if (shareResultCB != null) {
                shareResultCB.onCancel(share_media);
            }
        }
    };

    /**
     * 校验是否需要权限  qq相关需要校验权限
     *
     * @param platform
     * @return true为权限校验通过或不需要权限
     */
    private boolean checkPermission(final SHARE_MEDIA platform) {
        final boolean[] result = {false};
        if (EMUIUtils.isEMUI() || platform == SHARE_MEDIA.QZONE || platform == SHARE_MEDIA.QQ) {
            PermissionManager.request(UIUtils.getActivity(), new PermissionCallback() {

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
                 * @param deniedPermissions   被拒绝权限集合(包括不再询问)
                 * @param neverAskPermissions 被拒绝不再询问权限集合
                 */
                @Override
                public void onDenied(@NonNull List<String> deniedPermissions, @Nullable List<String> neverAskPermissions) {
                    if (shareHelpCallBack != null) {
                        shareHelpCallBack.onPermissonDeny();
                    }
                    result[0] = false;
                }
            }, Permission.STORAGE_READE, Permission.STORAGE_WRITE);
        } else {
            result[0] = true;
        }
        return result[0];
    }

    /**
     * 单个稿件分享
     *
     * @param bean
     */
    public void startSingleShare(UmengShareBean bean) {
        if (bean == null) {
            return;
        }
        if (!checkInstall(bean.getPlatform())) {
            return;
        }
        if (checkPermission(bean.getPlatform())) {
            UMWeb web = setShareUWeb(bean);
            new ShareAction(UIUtils.getActivity())
                    .setPlatform(bean.getPlatform())
                    .withMedia(web)
                    .setCallback(umShareListener)
                    .share();
        }
    }


    /**
     * 单个图片分享
     *
     * @param bean
     */
    public void startSinglePicShare(final UmengShareBean bean) {
        if (bean == null) {
            return;
        }
        if (!checkInstall(bean.getPlatform())) {
            return;
        }
        if (checkPermission(bean.getPlatform())) {
            UMImage image = setUMImage(bean);
            new ShareAction(UIUtils.getActivity())
                    .setPlatform(bean.getPlatform())
                    .withMedia(image)
                    .setCallback(umShareListener)
                    .share();
        }
    }

    /**
     * 视频分享 说明:视频只能使用网络视频
     */
    public void shareVideo(final UmengShareBean bean) {
        if (bean == null) {
            return;
        }
        if (!checkInstall(bean.getPlatform())) {
            return;
        }
        if (checkPermission(bean.getPlatform())) {
            if (!TextUtils.isEmpty(bean.getVideourl())) {
                UMVideo video = new UMVideo(bean.getVideourl());
                video.setTitle(bean.getTitle());
                video.setDescription(bean.getTextContent());
                new ShareAction(UIUtils.getActivity())
                        .withMedia(video)
                        .setPlatform(bean.getPlatform())
                        .setCallback(umShareListener)
                        .share();
            }
        }
    }

    /**
     * 音乐分享 说明:播放链接是指在微信qq分享音乐，是可以在当前聊天界面播放的，要求这个musicurl（播放链接）必须要以.mp3等音乐格式结尾，跳转链接是指点击linkcard之后进行跳转的链接。
     */
    public void shareMusic(final UmengShareBean bean) {
        if (bean == null) {
            return;
        }
        if (!checkInstall(bean.getPlatform())) {
            return;
        }
        if (checkPermission(bean.getPlatform())) {
            if (!TextUtils.isEmpty(bean.getMusicurl())) {
                UMusic music = new UMusic(bean.getMusicurl());
                music.setTitle(bean.getTitle());
                music.setDescription(bean.getTextContent());
                music.setmTargetUrl(bean.getTargetUrl()); // 跳转链接
                new ShareAction(UIUtils.getActivity())
                        .withMedia(music)
                        .setPlatform(bean.getPlatform())
                        .setCallback(umShareListener)
                        .share();
            }
        }
    }


    /**
     * 设置UWeb
     *
     * @param bean
     * @return
     */
    private UMWeb setShareUWeb(UmengShareBean bean) {
        if (bean != null && !TextUtils.isEmpty(bean.getTargetUrl())) {
            UMWeb web = new UMWeb(bean.getTargetUrl());
            if (!TextUtils.isEmpty(bean.getTitle())) {
                web.setTitle(bean.getTitle());//标题
            }
            //支持网络图片、本地图片、资源文件、bitmap、字节流
            if (!TextUtils.isEmpty(bean.getImgUri())) {
                web.setThumb(new UMImage(UIUtils.getContext(), bean.getImgUri()));  //缩略图
            } else {
                web.setThumb(new UMImage(UIUtils.getContext(), bean.getDefaultIcon()));
            }
            if (!TextUtils.isEmpty(bean.getTextContent())) {
                web.setDescription(bean.getTextContent());//描述
            }
            return web;
        }
        return null;

    }

    /**
     * 设置UMImage
     *
     * @param bean
     * @return
     */
    private UMImage setUMImage(UmengShareBean bean) {
        if (bean != null) {
            UMImage umImage = null;
            if (bean.getBimtap() != null) { // bitmap图片分享
                umImage = new UMImage(UIUtils.getContext(), bean.getBimtap());
                umImage.compressStyle = UMImage.CompressStyle.SCALE;
                return umImage;
            } else if (!TextUtils.isEmpty(bean.getImgUri())) { // 网络图片分享
                umImage = new UMImage(UIUtils.getContext(), bean.getImgUri());
                umImage.compressStyle = UMImage.CompressStyle.SCALE;
                return umImage;
            } else if (bean.getPicId() != 0) { // 资源文件图片分享
                umImage = new UMImage(UIUtils.getContext(), ImageUtils.getBitmapById(bean.getPicId()));
                umImage.compressStyle = UMImage.CompressStyle.SCALE;
                return umImage;
            }
            return umImage;
        }
        return null;
    }


    /**
     * 检测是否安装微信等
     */
    private boolean checkInstall(SHARE_MEDIA platform) {
        if (platform == SHARE_MEDIA.WEIXIN || platform == SHARE_MEDIA.WEIXIN_CIRCLE) {
            if (mShareAPI != null && !mShareAPI.isInstall(UIUtils.getActivity(), SHARE_MEDIA.WEIXIN)) {
                if (shareHelpCallBack != null) {
                    shareHelpCallBack.onInstallOut(platform);
                }
                return false;
            }
        } else if (platform == SHARE_MEDIA.QQ || platform == SHARE_MEDIA.QZONE) {
            if (mShareAPI != null && !mShareAPI.isInstall(UIUtils.getActivity(), SHARE_MEDIA.QQ)) {
                if (shareHelpCallBack != null) {
                    shareHelpCallBack.onInstallOut(platform);
                }
                return false;
            }
        } else if (platform == SHARE_MEDIA.DINGTALK) {
            if (mShareAPI != null && !mShareAPI.isInstall(UIUtils.getActivity(), SHARE_MEDIA.DINGTALK)) {
                if (shareHelpCallBack != null) {
                    shareHelpCallBack.onInstallOut(platform);
                }
                return false;
            }
        }
        return true;
    }

}
