# 分享组件
## 说明
友盟分享的常用功能进行封装,功能包括分享链接(包括标题,摘要,缩略图),分享纯文本,分享纯本地图片,分享网络图片等,目前使用的是友盟分享最新的版本V6.9.2,其中微博,qq,微信分享都是使用的V6.9.2精简版的jar包,其中demo是使用的浙江新闻相关的配置
因为各个客户端分享的UI都不尽相同,这里只是对分享常用的功能进行封装,具体的UI还需要各个客户端自己进行实现

## 使用方法

### 添加依赖
1. 主工程build.gradle添加仓库地址:

	```
	allprojects {
	    repositories {
	        google()
	        jcenter()
	        maven { url "http://10.100.62.98:8086/nexus/content/groups/public" }
	    }
	}
	```
2. 项目工程build.gradle 添加依赖,最新版本请查看 [最新版本](http://10.100.62.98:8086/nexus/#nexus-search;gav~cn.daily.android~share~~~)

	```
	compile 'cn.daily.android:share:0.0.0.1-SNAPSHOT'
	```


## 注意点:
因为友盟分享中钉钉,QQ,微信等的回调Activity的配置都是与应用包名相关的(新浪微博的相关配置因为与包名无关,故已经写到Lib的清单下),故各个客户端还需要在自己的客户端中做相应的处理,处理方式参考[友盟分享集成文档](https://developer.umeng.com/docs/66632/detail/66639)

### 添加回调Activity(注:回调activity的添加大多要求在当前应用包名下,demo以浙江新闻新闻为例,添加到com.zhejiangdaily下,其他应用需添加到自己应用的包名下)
#### 微信:
在包名目录下创建wxapi文件夹，新建一个名为WXEntryActivity的activity继承WXCallbackActivity
以浙江新闻为例,需要在com.zhejiangdaily下创建wxapi文件夹,然后在该文件夹下创建WXEntryActivity,其他应用请在该应用的包名下创建:
```java
public class WXEntryActivity extends WXCallbackActivity {

}
```

#### 钉钉:
与微信相似，钉钉需要在包名目录下创建ddshare文件夹，然后建立一个DDShareActivity的类继承DingCallBack
以浙江新闻为例,需要在com.zhejiangdaily下创建ddshare文件夹,然后在该文件夹下创建DDShareActivity,其他应用请在该应用的包名下创建:
```java
public class DDShareActivity extends DingCallBack {

}
```


#### QQ与新浪:
QQ与新浪不需要添加Activity，但需要在使用QQ分享或者授权的Activity中，添加：

```java
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
}
```
注意onActivityResult不可在fragment中实现，如果在fragment中调用登录或分享，需要在fragment依赖的Activity中实现

### 配置Android Manifest XML
#### 新浪
新浪相关配置因为与包名无关,已在lib的清单下配置

#### 微信:
```java
 <activity
            android:name=".wxapi.WXEntryActivity"
            android:configChanges="keyboardHidden|orientation|screenSize"
            android:exported="true"
            android:theme="@android:style/Theme.Translucent.NoTitleBar" />
```

钉钉：
```java
    <activity
        android:name=".ddshare.DingCallBack"
        android:configChanges="keyboardHidden|orientation|screenSize"
        android:exported="true"
        android:screenOrientation="portrait"
        android:theme="@android:style/Theme.Translucent.NoTitleBar" />
```

#### qq（请注意将data schema中的appkey替换成您自己应用的qq appkey）：
```java
     <activity
        android:name="com.tencent.tauth.AuthActivity"
        android:launchMode="singleTask"
        android:noHistory="true" >
        <intent-filter>
        <action android:name="android.intent.action.VIEW" />

        <category android:name="android.intent.category.DEFAULT" />
        <category android:name="android.intent.category.BROWSABLE" />

        <data android:scheme="替换为自己应用的appKey" />
        </intent-filter>
        </activity>
        <activity
        android:name="com.tencent.connect.common.AssistActivity"
        android:theme="@android:style/Theme.Translucent.NoTitleBar"
        android:configChanges="orientation|keyboardHidden|screenSize"/>
```


#### 分享的使用方法(使用示例见ShareDetailActivity,具体方法见ShareUtils):
1.纯文本分享
```java
    ShareUtils.shareText(this, Defaultcontent.text, share_media, shareListener);
```

2.本地图片分享
```java
    ShareUtils.shareImageLocal(this, R.drawable.logo, null, share_media, shareListener);
```

3.网络图片分享
```java
    String url = "http://stcbeta.8531.cn/assets/20180509/1525829171241_5af24e339949d8745a229fee.jpeg";
    ShareUtils.shareImageNet(this, url, null, share_media, shareListener);
```

4.图片分享(Bitmap类型)
```java
    ShareUtils.shareImageBitmap(this, bitmap, null, share_media, shareListener);
```

5.分享链接(标题,摘要,缩略图及跳转链接信息需要自己构造UMWeb)
```java
    UMWeb web = new UMWeb(Defaultcontent.url);
    web.setTitle("This is web title");
    web.setThumb(new UMImage(this, R.drawable.thumb));
    web.setDescription("my description");
    ShareUtils.shareUrl(this, web, share_media, shareListener);
```

6.分享链接(传入url的形式)
```java
    ShareUtils.shareUrl(this,url, title, desc, thumb, share_media, shareListener);
```

#### 友盟的sdk里面提供了判断应用是否安装的方法,以微信为例:
```java
UMShareAPI umShareAPI = UMShareAPI.get(getApplicationContext());
System.out.println("微信是否安装:" + umShareAPI.isInstall(this, SHARE_MEDIA.WEIXIN));
```