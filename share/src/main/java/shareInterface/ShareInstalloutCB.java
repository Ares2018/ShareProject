package shareInterface;

import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * 手机无安装微信、QQ、微博弹出Toast内容
 * Created by wanglinjie.
 * create time:2018/8/10  下午3:35
 */
public interface ShareInstalloutCB {
    void showInstallOut(SHARE_MEDIA platform);
}
