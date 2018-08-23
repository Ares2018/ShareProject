package cn.daily.share;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.umeng.socialize.bean.SHARE_MEDIA;

import java.io.Serializable;

/**
 * 支持扩展继承
 * Created by wanglinjie on 16/11/18.
 * 支持链式调用
 */

public class UmengShareBean implements Serializable {

    /**
     * @param ctx
     * @param title       分享的标题
     * @param textContent 内容
     * @param imgUri      图片uri,图片分享也使用该参数
     * @param targetUrl   分享的链接
     * @param platform    分享类型(QQ,微信等)
     * @param isSingle    是否为单个分享(非弹窗)
     * @param articleId   稿件ID
     * @param isNeedScored  是否需要加积分  true:需要加积分 false:不需要加积分
     * @param isPicShare 是否是图片分享 false:非图片分享 true:图片分享
     * @param picId 图片分享,分享的图片资源id
     * @param bitmap 图片分享,要分享的bitmap  注:图片分享bitmap优先级 > url图片分享 > 资源id图片分享
     */
    private String title = "";
    private String textContent = "";
    private String imgUri = "";
    private String targetUrl = "";
    private String shareType = "";
    private String shareContentID = "";
    private String eventName = "";
    private String articleId = "";
    /**
     * 视频url
     */
    private String videourl = "";
    private int defaultIcon;

    /**
     * 音频分享链接
     */
    private String musicurl = "";

    /**
     * 是否是来自红船号详情页的分享
     */
    private boolean isRedBoat = false;
    /**
     * 非必传(当单个分享时，必传)
     */
    private SHARE_MEDIA platform = null;
    /**
     * 必传
     */
    private boolean isSingle = false;

    /**
     * 是否是图片分享,默认为false,图片分享设置为true
     */
    private boolean isPicShare = false;

    /**
     * 图片分享的资源id
     */
    private int picId;

    /**
     * 要分享的bitmap
     */
    private Bitmap bimtap;

    private UmengShareBean() {

    }

    static public UmengShareBean getInstance() {
        return new UmengShareBean();
    }


    public String getArticleId() {
        return articleId;
    }

    public String getTitle() {
        return title;
    }

    public String getTextContent() {
        return textContent;
    }

    public String getImgUri() {
        return imgUri;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public SHARE_MEDIA getPlatform() {
        return platform;
    }

    public String getShareType() {
        return shareType;
    }

    public String getShareContentID() {
        return shareContentID;
    }

    public String getEventName() {
        return eventName;
    }

    public String getMusicurl() {
        return musicurl;
    }

    public String getVideourl() {
        return videourl;
    }

    public int getDefaultIcon() {
        return defaultIcon;
    }

    public UmengShareBean setVideourl(String videourl) {
        this.videourl = videourl;
        return this;
    }

    public UmengShareBean setDefaultIcon(int defaultIcon) {
        this.defaultIcon = defaultIcon;
        return this;
    }


    public UmengShareBean setMusicurl(String musicurl) {
        this.musicurl = musicurl;
        return this;
    }


    public UmengShareBean setEventName(String eventName) {
        this.eventName = eventName;
        return this;
    }


    public UmengShareBean setShareContentID(String shareContentID) {
        this.shareContentID = shareContentID;
        return this;
    }


    public UmengShareBean setShareType(String shareType) {
        this.shareType = shareType;
        return this;
    }

    public UmengShareBean setArticleId(String articleId) {
        if (articleId != null) {
            this.articleId = articleId;
        }
        return this;
    }

    public UmengShareBean setTextContent(@NonNull String textContent) {
        if (textContent != null) {
            this.textContent = textContent;
        }
        return this;
    }

    public UmengShareBean setImgUri(@NonNull String imgUri) {
        if (imgUri != null) {
            this.imgUri = imgUri;
        }
        return this;
    }

    public UmengShareBean setTargetUrl(@NonNull String targetUrl) {
        if (targetUrl != null) {
            this.targetUrl = targetUrl;
        }
        return this;
    }

    public UmengShareBean setPlatform(SHARE_MEDIA platform) {
        this.platform = platform;
        return this;
    }


    public UmengShareBean setTitle(@NonNull String title) {
        if (title != null) {
            this.title = title;
        }
        return this;
    }

    public boolean isSingle() {
        return isSingle;
    }

    public UmengShareBean setSingle(boolean single) {
        isSingle = single;
        return this;
    }

    public boolean isPicShare() {
        return isPicShare;
    }

    public UmengShareBean setPicShare(boolean picShare) {
        this.isPicShare = picShare;
        return this;
    }

    public int getPicId() {
        return picId;
    }

    public UmengShareBean setPicId(int picId) {
        this.picId = picId;
        return this;
    }

    public boolean isRedBoat() {
        return isRedBoat;
    }

    public UmengShareBean setRedBoat(boolean redBoat) {
        isRedBoat = redBoat;
        return this;
    }

    public Bitmap getBimtap() {
        return bimtap;
    }

    public UmengShareBean setBimtap(Bitmap bimtap) {
        this.bimtap = bimtap;
        return this;
    }
}
