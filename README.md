# 分享组件
## 说明
友盟分享的常用功能进行封装,功能包括分享链接(包括标题,摘要,缩略图),分享纯文本,分享纯本地图片,分享网络图片等,目前使用的是友盟分享最新的版本V6.9.2,其中微博,qq,微信分享都是使用的V6.9.2精简版的jar包

因为各个客户端分享的UI都不尽相同,这里只是对分享常用的功能进行封装,具体的UI还需要各个客户端自己进行实现

#### 分享的使用方法(使用示例见ShareDetailActivity,具体方法见ShareUtils):
1.纯文本分享

    ShareUtils.shareText(this, Defaultcontent.text, share_media, shareListener);

2.本地图片分享

    ShareUtils.shareImageLocal(this, R.drawable.logo, null, share_media, shareListener);

3.网络图片分享

    String url = "http://stcbeta.8531.cn/assets/20180509/1525829171241_5af24e339949d8745a229fee.jpeg";
    ShareUtils.shareImageNet(this, url, null, share_media, shareListener);

4.图片分享(Bitmap类型)

    ShareUtils.shareImageBitmap(this, bitmap, null, share_media, shareListener);

5.分享链接(标题,摘要,缩略图及跳转链接信息需要自己构造UMWeb)


    UMWeb web = new UMWeb(Defaultcontent.url);
    web.setTitle("This is web title");
    web.setThumb(new UMImage(this, R.drawable.thumb));
    web.setDescription("my description");
    ShareUtils.shareUrl(this, web, share_media, shareListener);


6.分享链接(传入url的形式)

    ShareUtils.shareUrl(this,url, title, desc, thumb, share_media, shareListener);
