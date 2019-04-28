package cn.daily.share;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.media.UMusic;

import java.io.File;

import shareInterface.UMengWrapShareListener;
import shareInterface.UmengShareCallBack;

/**
 * Date: 2019/1/26 2:59 PM
 * Email: sisq@8531.cn
 * Author: sishuqun
 * Description: 友盟分享封装,builder模式,支持文本,图片,链接分享
 */
public class UmengShare {

    public UmengShare() {
    }

    /**
     * 文本分享
     */
    public static class TextBuilder extends ShareBuilder {

        public TextBuilder(@NonNull Context context) {
            super(context);
        }

        @Override
        public TextBuilder text(String text) {
            super.text(text);
            return this;
        }

        @Override
        public TextBuilder platform(@NonNull SHARE_MEDIA platformType) {
            super.platform(platformType);
            return this;
        }

        @Override
        public TextBuilder callback(@Nullable UmengShareCallBack shareListener) {
            super.callback(shareListener);
            return this;
        }

        @Override
        public void share() {
            // 文本为空处理
            if (TextUtils.isEmpty(mText)) {
                mUmengShareCallBack.onTextEmpty(mShareMedia, mText);
            } else {
                mShareAction.withText(mText);
                super.share();
            }
        }
    }


    /**
     * 图片分享(包括图文)
     */
    public static class ImageBuilder extends ShareBuilder {

        protected UMImage mUMImage;
        protected UMImage mThumb; // 缩略图
        protected UMImage.CompressStyle mCompressStyle = UMImage.CompressStyle.SCALE; // 默认压缩方式,默认为大小压缩，适合普通很大的图
        // TODO: 2019/1/29 图片格式


        @Override
        public ImageBuilder text(String text) {
            super.text(text);
            return this;
        }

        @Override
        public ImageBuilder platform(@NonNull SHARE_MEDIA platformType) {
            super.platform(platformType);
            return this;
        }

        @Override
        public ImageBuilder callback(@Nullable UmengShareCallBack shareListener) {
            super.callback(shareListener);
            return this;
        }

        public ImageBuilder(@NonNull Context context) {
            super(context);
        }

        /**
         * 网络图片
         * @param imageUrl
         * @return
         */
        public ImageBuilder image(@NonNull String imageUrl) {
            mUMImage = TextUtils.isEmpty(imageUrl) ? null : new UMImage(mContext, imageUrl);
            return this;
        }

        /**
         * 资源文件
         * @param imageRes
         * @return
         */
        public ImageBuilder image(int imageRes) {
            mUMImage = imageRes == 0 ? null : new UMImage(mContext, imageRes);
            return this;
        }


        /**
         * 本地图片
         * @param file
         * @return
         */
        public ImageBuilder image(@NonNull File file) {
            mUMImage = (file == null || file.length() == 0) ? null : new UMImage(mContext, file);
            return this;
        }

        /**
         * bitmap
         * @param bitmap
         * @return
         */
        public ImageBuilder image(@NonNull Bitmap bitmap) {
            mUMImage = bitmap == null ? null : new UMImage(mContext, bitmap);
            return this;
        }

        /**
         * 字节流
         * @param imgBytes
         * @return
         */
        public ImageBuilder image(@NonNull byte[] imgBytes) {
            mUMImage = (imgBytes == null || imgBytes.length == 0) ? null : new UMImage(mContext, imgBytes);
            return this;
        }

        /**
         * 直接设置umImage
         * @param umImage
         * @return
         */
        public ImageBuilder image(UMImage umImage) {
            mUMImage = umImage;
            if (mUMImage != null) {
                mCompressStyle = mUMImage.compressStyle;
            }
            return this;
        }

        /**
         * 缩略图 网络图片
         * @param imageUrl
         * @return
         */
        public ImageBuilder thumb(String imageUrl) {
            mThumb = TextUtils.isEmpty(imageUrl) ? null : new UMImage(mContext, imageUrl);
            return this;
        }

        public ImageBuilder thumb(int imageRes) {
            mThumb = imageRes == 0 ? null : new UMImage(mContext, imageRes);
            return this;
        }

        public ImageBuilder thumb(@NonNull File file) {
            mThumb = (file == null || file.length() == 0) ? null : new UMImage(mContext, file);
            return this;
        }

        public ImageBuilder thumb(@NonNull Bitmap bitmap) {
            mThumb = bitmap == null ? null : new UMImage(mContext, bitmap);
            return this;
        }

        public ImageBuilder thumb(@NonNull byte[] imgBytes) {
            mThumb = (imgBytes == null || imgBytes.length == 0) ? null : new UMImage(mContext, imgBytes);
            return this;
        }

        public ImageBuilder thumb(UMImage umImage) {
            mThumb = umImage;
            return this;
        }

        /**
         * 压缩方式
         * @param style //大小压缩，默认为大小压缩，适合普通很大的图 大小压缩，默认为大小压缩，适合普通很大的图
         *              UMImage.CompressStyle.QUALITY;//质量压缩，适合长图的分享
         * @return
         */
        public ImageBuilder compressStyle(UMImage.CompressStyle style) {
            mCompressStyle = style;
            return this;
        }

        @Override
        public void share() {
            if (mUMImage == null) {
                if (mUmengShareCallBack != null) {
                    mUmengShareCallBack.onImageEmpty(mShareMedia);
                }
            } else {
                if (mCompressStyle != null) {
                    mUMImage.compressStyle = mCompressStyle;
                }
                // 缩略图设置
                if (mThumb != null) {
                    mUMImage.setThumb(mThumb);
                } else {
                    mUMImage.setThumb(mUMImage);
                }
                mShareAction.withMedia(mUMImage);
                if (!TextUtils.isEmpty(mText)) { // 图文分享
                    mShareAction.withText(mText);
                }
                super.share();
            }

        }
    }

    /**
     * 分享链接
     */
    public static class LinkBuilder extends ImageBuilder{
        private String mUrl;
        private String mDescription;
        private String mTitle;
        private UMWeb mUMWeb; // 外部直接传递UMWeb的方式来设置

        public LinkBuilder(@NonNull Context context) {
            super(context);
        }

        @Override
        public LinkBuilder text(String text) {
            super.text(text);
            return this;
        }

        @Override
        public LinkBuilder platform(@NonNull SHARE_MEDIA platformType) {
            super.platform(platformType);
            return this;
        }

        @Override
        public LinkBuilder callback(@Nullable UmengShareCallBack shareListener) {
            super.callback(shareListener);
            return this;
        }

        /**
         * 分享链接
         * @param url
         * @return
         */
        public LinkBuilder url(@NonNull String url) {
            if (TextUtils.isEmpty(url) || TextUtils.isEmpty(url.trim())) {
                throw new IllegalArgumentException("Url can not be null or empty");
            }
            mUrl = url;
            return this;
        }

        /**
         * 分享标题
         * @param title
         * @return
         */
        public LinkBuilder title(@NonNull String title) {
            mTitle = title;
            return this;
        }

        /**
         * 分享描述
         * @param description
         * @return
         */
        public LinkBuilder description(@NonNull String description) {
            mDescription = description;
            return this;
        }

        /**
         * 网络图片
         * @param imageUrl
         * @return
         */
        public LinkBuilder image(@NonNull String imageUrl) {
            mUMImage = TextUtils.isEmpty(imageUrl) ? null : new UMImage(mContext, imageUrl);
            return this;
        }

        /**
         * 资源文件
         * @param imageRes
         * @return
         */
        public LinkBuilder image(int imageRes) {
            mUMImage = imageRes == 0 ? null : new UMImage(mContext, imageRes);
            return this;
        }


        /**
         * 本地图片
         * @param file
         * @return
         */
        public LinkBuilder image(@NonNull File file) {
            mUMImage = (file == null || file.length() == 0) ? null : new UMImage(mContext, file);
            return this;
        }

        /**
         * bitmap
         * @param bitmap
         * @return
         */
        public LinkBuilder image(@NonNull Bitmap bitmap) {
            mUMImage = bitmap == null ? null : new UMImage(mContext, bitmap);
            return this;
        }

        /**
         * 字节流
         * @param imgBytes
         * @return
         */
        public LinkBuilder image(@NonNull byte[] imgBytes) {
            mUMImage = (imgBytes == null || imgBytes.length == 0) ? null : new UMImage(mContext, imgBytes);
            return this;
        }

        /**
         * 直接设置umImage
         * @param umImage
         * @return
         */
        public LinkBuilder image(UMImage umImage) {
            mUMImage = umImage;
            if (mUMImage != null) {
                mCompressStyle = mUMImage.compressStyle;
            }
            return this;
        }

        /**
         * 缩略图 网络图片
         * @param imageUrl
         * @return
         */
        public LinkBuilder thumb(String imageUrl) {
            mThumb = TextUtils.isEmpty(imageUrl) ? null : new UMImage(mContext, imageUrl);
            return this;
        }

        public LinkBuilder thumb(int imageRes) {
            mThumb = imageRes == 0 ? null : new UMImage(mContext, imageRes);
            return this;
        }

        public LinkBuilder thumb(@NonNull File file) {
            mThumb = (file == null || file.length() == 0) ? null : new UMImage(mContext, file);
            return this;
        }

        public LinkBuilder thumb(@NonNull Bitmap bitmap) {
            mThumb = bitmap == null ? null : new UMImage(mContext, bitmap);
            return this;
        }

        public LinkBuilder thumb(@NonNull byte[] imgBytes) {
            mThumb = (imgBytes == null || imgBytes.length == 0) ? null : new UMImage(mContext, imgBytes);
            return this;
        }

        public LinkBuilder thumb(UMImage umImage) {
            mThumb = umImage;
            return this;
        }

        public LinkBuilder web(UMWeb umWeb) {
            mUMWeb = umWeb;
            if (mUMWeb != null) {
                mTitle = mUMWeb.getTitle();
                mDescription = mUMWeb.getDescription();
                mThumb = mUMWeb.getThumbImage();
            }
            return this;
        }

        /**
         * 压缩方式
         * @param style //大小压缩，默认为大小压缩，适合普通很大的图 大小压缩，默认为大小压缩，适合普通很大的图
         *              UMImage.CompressStyle.QUALITY;//质量压缩，适合长图的分享
         * @return
         */
        public LinkBuilder compressStyle(UMImage.CompressStyle style) {
            mCompressStyle = style;
            return this;
        }


        @Override
        public void share() {
            if (mUMWeb == null) {
                if (TextUtils.isEmpty(mUrl) || TextUtils.isEmpty(mUrl.trim())) {
                    if (mUmengShareCallBack != null) {
                        mUmengShareCallBack.onWebUrlEmpty(mShareMedia);
                    }
                    return;
                }
                mUMWeb = new UMWeb(mUrl);
            }
            // 外部直接传UMWeb及自己构造UMWeb都要走下面代码
            mUMWeb.setTitle(mTitle);
            mUMWeb.setDescription(mDescription);
            // 缩略图设置
            if (mThumb != null) {
                mUMWeb.setThumb(mThumb);
            } else if (mUMImage != null) {
                mUMWeb.setThumb(mUMImage);
            }
            mShareAction.withMedia(mUMWeb);
            mShareAction.setPlatform(mShareMedia)
                    .setCallback(new UMengWrapShareListener(mUmengShareCallBack))
                    .share();
        }
    }


    /**
     * 视频分享,只能使用网络视频
     */
    public static class VideoBuilder extends ShareBuilder {

        protected UMImage mThumb; // 缩略图
        private String mDescription;
        private String mTitle;
        private String mUrl;

        public VideoBuilder(@NonNull Context context) {
            super(context);
        }

        @Override
        public VideoBuilder text(String text) {
            super.text(text);
            return this;
        }

        @Override
        public VideoBuilder platform(@NonNull SHARE_MEDIA platformType) {
            super.platform(platformType);
            return this;
        }

        @Override
        public VideoBuilder callback(@Nullable UmengShareCallBack shareListener) {
            super.callback(shareListener);
            return this;
        }

        /**
         * 视频链接
         * @param url
         * @return
         */
        public VideoBuilder url(@NonNull String url) {
            if (TextUtils.isEmpty(url) || TextUtils.isEmpty(url.trim())) {
                throw new IllegalArgumentException("VIDEO Url can not be null or empty");
            }
            mUrl = url;
            return this;
        }

        /**
         * 视频标题
         * @param title
         * @return
         */
        public VideoBuilder title(@NonNull String title) {
            mTitle = title;
            return this;
        }

        /**
         * 视频描述
         * @param description
         * @return
         */
        public VideoBuilder description(@NonNull String description) {
            mDescription = description;
            return this;
        }

        /**
         * 缩略图 网络图片
         * @param imageUrl
         * @return
         */
        public VideoBuilder thumb(@NonNull String imageUrl) {
            mThumb = TextUtils.isEmpty(imageUrl) ? null : new UMImage(mContext, imageUrl);
            return this;
        }

        public VideoBuilder thumb(int imageRes) {
            mThumb = imageRes == 0 ? null : new UMImage(mContext, imageRes);
            return this;
        }

        public VideoBuilder thumb(@NonNull File file) {
            mThumb = (file == null || file.length() == 0) ? null : new UMImage(mContext, file);
            return this;
        }

        public VideoBuilder thumb(@NonNull Bitmap bitmap) {
            mThumb = bitmap == null ? null : new UMImage(mContext, bitmap);
            return this;
        }

        public VideoBuilder thumb(@NonNull byte[] imgBytes) {
            mThumb = (imgBytes == null || imgBytes.length == 0) ? null : new UMImage(mContext, imgBytes);
            return this;
        }

        @Override
        public void share() {
            if (TextUtils.isEmpty(mUrl) || TextUtils.isEmpty(mUrl.trim())) {
                if (mUmengShareCallBack != null) {
                    mUmengShareCallBack.onShareEmpty(mShareMedia);
                }
            } else {
                UMVideo video = new UMVideo(mUrl);
                video.setTitle(mTitle);//视频的标题
                if (mThumb != null) {
                    video.setThumb(mThumb);//视频的缩略图
                }
                video.setDescription(mDescription);//视频的描述
                mShareAction.withMedia(video);
                super.share();
            }

        }
    }

    /**
     * 音乐分享,只能使用网络音乐
     */
    public static class MusicBuilder extends ShareBuilder {

        protected UMImage mThumb; // 缩略图
        private String mDescription;
        private String mTitle;
        private String mUrl; // 音乐的播放链接
        private String mTargetUrl; // 音乐的跳转链接

        public MusicBuilder(@NonNull Context context) {
            super(context);
        }

        @Override
        public MusicBuilder text(String text) {
            super.text(text);
            return this;
        }

        @Override
        public MusicBuilder platform(@NonNull SHARE_MEDIA platformType) {
            super.platform(platformType);
            return this;
        }

        @Override
        public MusicBuilder callback(@Nullable UmengShareCallBack shareListener) {
            super.callback(shareListener);
            return this;
        }

        /**
         * 音乐链接
         * @param url
         * @return
         */
        public MusicBuilder url(@NonNull String url) {
            if (TextUtils.isEmpty(url) || TextUtils.isEmpty(url.trim())) {
                throw new IllegalArgumentException("Music Url can not be null or empty");
            }
            mUrl = url;
            return this;
        }

        /**
         * 音乐的跳转链接
         * @param targetUrl
         * @return
         */
        public MusicBuilder targetUrl(@NonNull String targetUrl) {
            if (TextUtils.isEmpty(targetUrl) || TextUtils.isEmpty(targetUrl.trim())) {
                throw new IllegalArgumentException("Music TargetUrl can not be null or empty");
            }
            mTargetUrl = targetUrl;
            return this;
        }

        /**
         * 音乐标题
         * @param title
         * @return
         */
        public MusicBuilder title(@NonNull String title) {
            mTitle = title;
            return this;
        }

        /**
         * 音乐描述
         * @param description
         * @return
         */
        public MusicBuilder description(@NonNull String description) {
            mDescription = description;
            return this;
        }

        /**
         * 缩略图 网络图片
         * @param imageUrl
         * @return
         */
        public MusicBuilder thumb(@NonNull String imageUrl) {
            mThumb = TextUtils.isEmpty(imageUrl) ? null : new UMImage(mContext, imageUrl);
            return this;
        }

        public MusicBuilder thumb(int imageRes) {
            mThumb = imageRes == 0 ? null : new UMImage(mContext, imageRes);
            return this;
        }

        public MusicBuilder thumb(@NonNull File file) {
            mThumb = (file == null || file.length() == 0) ? null : new UMImage(mContext, file);
            return this;
        }

        public MusicBuilder thumb(@NonNull Bitmap bitmap) {
            mThumb = bitmap == null ? null : new UMImage(mContext, bitmap);
            return this;
        }

        public MusicBuilder thumb(@NonNull byte[] imgBytes) {
            mThumb = (imgBytes == null || imgBytes.length == 0) ? null : new UMImage(mContext, imgBytes);
            return this;
        }

        @Override
        public void share() {
            if (TextUtils.isEmpty(mUrl) || TextUtils.isEmpty(mUrl.trim())) {
                if (mUmengShareCallBack != null) {
                    mUmengShareCallBack.onShareEmpty(mShareMedia);
                }
            } else {
                UMusic music = new UMusic(mUrl);//音乐的播放链接
                music.setTitle(mTitle);//音乐的标题
                if (mThumb != null) {
                    music.setThumb(mThumb);//音乐的缩略图
                }
                music.setDescription(mDescription);//音乐的描述
                music.setmTargetUrl(mTargetUrl);//音乐的跳转链接
                mShareAction.withMedia(music);
                super.share();
            }

        }
    }


}
