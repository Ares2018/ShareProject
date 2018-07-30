package cn.daily.zhejiangdaily;

import android.app.Application;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //设置LOG开关，默认为false
        UMConfigure.setLogEnabled(true);
        //初始化组件化基础库, 统计SDK/推送SDK/分享SDK都必须调用此初始化接口
        UMConfigure.init(this, "535879d256240b8965030920", "Umeng", UMConfigure.DEVICE_TYPE_PHONE,
            "");
    }

    {
        PlatformConfig.setWeixin("wxc8bcb96e972bd147", "6bde68292c1295c7cf81d47a3a520030");
        PlatformConfig.setSinaWeibo("287017146", "5113d5e528ae8335f230f025bcbd6fa1", "http://www.zjol.com.cn");
        PlatformConfig.setQQZone("101096646", "da6306af99c94ba949029b563a69a9a4");
        PlatformConfig.setDing("dingoahuzuxhqyb2jtypgm");

    }





}
