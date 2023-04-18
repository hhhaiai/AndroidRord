# AndroidRord

> android receiver word: 安卓自定义广播插件


## 介绍

* 用于发送自定义广播收发场景，初始化传入自定义action、回调函数，当收到对应广播这会调用传入的回调函数
* action及回调支持如下几种模式:
    - 一个action 对应一个回调
    - 多个action 对应一个回调函数
* 初始化方式支持:
    - 初始化一对一(action:callback)
    - 初始化多对一(actions:callback)
    - 初始化支持多对多([action0:callback0,action1:callback1])

### 调用方法

* 初始化

``` java
//一对一
AndRord.init(Context context, String action, IRordCallback callback);
//多对一
AndRord.init(Context context, List<String> actions, IRordCallback callback);
//多对
AndRord.init(Context context, Map<String, IRordCallback> actionAndCallback)
```

* 调用方式:

  - 代码广播，如下:
  
  ``` java
   // 发送自定义action1的广播，附加key、value参数
   Intent i = new Intent("action1");
   i.setPackage(getPackageName());
   i.putExtra("msg", "this is data from  " + action);
   i.setAction(action);
   i.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
   sendBroadcast(i);
  ```
  
  - shell指令,这种方式应用内无法发送，如有能搞定的方案可以Issues联系。如下:
  
  ``` shell
   # 发送自定义action1的广播，附加key、value参数
   adb shell am broadcast -a action1 --es msg1 'hello action1'
  ```

