package cn.daily.zhejiangdaily;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;

import cn.zgy.utils.utils.AppManager;


public abstract class BaseActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.titlebg));

        }
        setContentView(getLayout());
        AppManager.get().addActivity(this);
    }
    public abstract int getLayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.get().removeActivity(this);
    }
}
