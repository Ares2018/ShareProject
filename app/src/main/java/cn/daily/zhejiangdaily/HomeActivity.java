package cn.daily.zhejiangdaily;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.common.ResContainer;
import com.umeng.socialize.shareboard.SnsPlatform;

import java.util.ArrayList;

import cn.daily.zhejiangdaily.views.Item;

public class HomeActivity extends AppCompatActivity {
    public ArrayList<SnsPlatform> platforms = new ArrayList<SnsPlatform>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ushareplatform);
        setTitle(getResources().getString(R.string.app_name));
        initViews();

        }


    private void initViews(){
        LinearLayout container = (LinearLayout)findViewById(R.id.platform_container);
        initPlatforms();
        for (final SnsPlatform platform:platforms){
            Item item = new Item(this);
            item.setIcon(ResContainer.getResourceId(this,"drawable",platform.mIcon));
            item.setName(platform.mShowWord);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,getResources().getDimensionPixelOffset(R.dimen.item_height));
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println();
                    Intent intent = new Intent(HomeActivity.this,ShareDetailActivity.class);
                    intent.putExtra("platform",platform.mPlatform);
                    intent.putExtra("name",platform.mShowWord);
                    HomeActivity.this.startActivity(intent);
                }
            });
            container.addView(item,lp);
        }
    }

    private void initPlatforms(){
        platforms.clear();
        platforms.add(SHARE_MEDIA.WEIXIN.toSnsPlatform());
        platforms.add(SHARE_MEDIA.WEIXIN_CIRCLE.toSnsPlatform());
        platforms.add(SHARE_MEDIA.SINA.toSnsPlatform());
        platforms.add(SHARE_MEDIA.QQ.toSnsPlatform());
        platforms.add(SHARE_MEDIA.QZONE.toSnsPlatform());
        platforms.add(SHARE_MEDIA.DINGTALK.toSnsPlatform());
//        platforms.add(SHARE_MEDIA.RENREN.toSnsPlatform());
//        platforms.add(SHARE_MEDIA.DOUBAN.toSnsPlatform());
//        platforms.add(SHARE_MEDIA.SMS.toSnsPlatform());
//        platforms.add(SHARE_MEDIA.EMAIL.toSnsPlatform());
//        platforms.add(SHARE_MEDIA.YNOTE.toSnsPlatform());
//        platforms.add(SHARE_MEDIA.EVERNOTE.toSnsPlatform());
//        platforms.add(SHARE_MEDIA.LAIWANG.toSnsPlatform());
//        platforms.add(SHARE_MEDIA.LAIWANG_DYNAMIC.toSnsPlatform());
//        platforms.add(SHARE_MEDIA.LINKEDIN.toSnsPlatform());
//        platforms.add(SHARE_MEDIA.YIXIN.toSnsPlatform());
//        platforms.add(SHARE_MEDIA.YIXIN_CIRCLE.toSnsPlatform());
//        platforms.add(SHARE_MEDIA.TENCENT.toSnsPlatform());
//        platforms.add(SHARE_MEDIA.FACEBOOK.toSnsPlatform());
//        platforms.add(SHARE_MEDIA.FACEBOOK_MESSAGER.toSnsPlatform());
//        platforms.add(SHARE_MEDIA.VKONTAKTE.toSnsPlatform());
//        platforms.add(SHARE_MEDIA.TWITTER.toSnsPlatform());
//        platforms.add(SHARE_MEDIA.WHATSAPP.toSnsPlatform());
//        platforms.add(SHARE_MEDIA.GOOGLEPLUS.toSnsPlatform());
//        platforms.add(SHARE_MEDIA.LINE.toSnsPlatform());
//        platforms.add(SHARE_MEDIA.INSTAGRAM.toSnsPlatform());
//        platforms.add(SHARE_MEDIA.KAKAO.toSnsPlatform());
//        platforms.add(SHARE_MEDIA.PINTEREST.toSnsPlatform());
//        platforms.add(SHARE_MEDIA.POCKET.toSnsPlatform());
//        platforms.add(SHARE_MEDIA.TUMBLR.toSnsPlatform());
//        platforms.add(SHARE_MEDIA.FLICKR.toSnsPlatform());
//        platforms.add(SHARE_MEDIA.FOURSQUARE.toSnsPlatform());
//        platforms.add(SHARE_MEDIA.DROPBOX.toSnsPlatform());
//        platforms.add(SHARE_MEDIA.MORE.toSnsPlatform());

    }

}
