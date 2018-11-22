package shareInterface;

import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * 友盟分享帮助接口
 * Created by wanglinjie.
 * create time:2018/8/10  下午3:35
 */
public interface ShareHelpCallBack {

    void onInstallOut(SHARE_MEDIA platform); // 手机无安装微信、QQ、微博弹出Toast内容

    void onPermissonDeny(); // 权限被拒绝

}
