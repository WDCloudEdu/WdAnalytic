# 伟东云统计SDK Android集成文档和说明
## 目录：
## 1.导入SDK
## 2.统计服务注册
## 3.权限
## 4.初始化
## 5.统计用户信息
## 6.页面统计
## 7.自定义事件统计
## 8.使用时长统计
## 9.奔溃统计
## 10.Sdk中使用的jar 包和三方库
## 11.历史版本
## 12.常见问题
 [![PRs Welcome](https://img.shields.io/badge/PRs-Welcome-brightgreen.svg)](http://192.168.6.133:8888/nexus/content/repositories/WdMobile/com/wdcloud/analytic/analytic-release/)
 [![](https://jitpack.io/v/sweet-guy/WdAnalytics.svg)](https://jitpack.io/#WDCloudEdu/WdAnalytic)
## 开始集成：
### 1.	导入统计SDK
`方法1：`
### 项目根目录build.gradle中添加
### maven { url "https://jitpack.io" }
### 导入依赖
` implementation 'com.github.WDCloudEdu:WdAnalytic:1.0.1' （以最新为主）

`方法2：`
#### 在根项目build.gradle allprojects中配置Maven库

`maven{url 'http://192.168.6.133:8888/nexus/content/repositories/WdMobile/'}`

#### 在App build.gradle中导入统计sdk远程依赖
`implementation ('com.wdcloud.analytic:analytic-release:1.0.9') （最新版本为1.0.9）`

### 2.	统计服务注册
#### 注：在当前app清单文件注册
    <service android:name="com.wdcloud.wdanalytics.service.AnalyticsService"
    android:exported="true"
    android:enabled="true">
</service>
### 3.权限
    <uses-permission android:name="android.permission.INTERNET"></uses-permission>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"></uses-permission>
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"></uses-permission>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"></uses-permission>
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"></uses-permission>
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"></uses-permission>
    <uses-permission android:name="android.permission.READ_PHONE_STATE"></uses-permission>
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"></uses-permission>

### 4.统计初始化

    WdAnalytics.init(Context this, String "人人通", Boolen true, int 0);
    
    参数说明：
    当前上下文，项目名称，是否开启日志，上传时间间隔（分钟）默认为5分钟
    
### 5.统计用户信息
    登录时调用
    WdAnalytics.setUserinfo（String distinctId，String locationCode，String channel）
    
    参数说明：
    distinctId 用户id，locationCode 地区代码，channel 渠道号
### 6.页面统计

    第一次进入页面调用    onPageStart()
    退出或者跳转页面调用  onPageEnd(String currentPageName,String pageId)
    
    参数说明：
    PageName 当前页面名字  pageId 当前页面id
    
### 7.自定义事件统计

    setAnalyticEvent(Context context, String eventName, String eventId, Boolean isReal，HashMap<String,Object> object)
    
    参数说明：
    Context上下文  eventName事件名称  eventId事件id  isReal是否是实时统计事件
    HashMap中String 为统计名称Object 为自定义统计参数（如：key：统计名称，value：统计参数）
    不使用自定义统计参数则HashMap参数填null即可

### 8.使用时长统计

    在App退出前调用setExitApp（）

### 9.奔溃统计

    在项目application中初始化 （必须）
    CrashHandler.getInstance().init(this);
    
    捕获异常统计（默认会捕获异常不需要调用 setCrashAnalytics）
    本地项目自定义异常可以手动调用上传
    
    WdAnalytics.setCrashAnalytics(Throwable result, String localIpAddress);  
    
    参数说明：
    Result为获取的Throwable异常信息
    localIpAddress 为获取网络的ip 
    
### 10.Sdk中使用的jar 包和三方库

    解析工具：Gson 2.6.2
    数据库：  GreenDao 3.3
    网络请求：Okhttp 3.3.0
    
   注：
    `只打包GreenDao三方数据库，其他jar和三方库不打入sdk中，Gson解析丶okhttp网络请求默认使用主项目中库`

### 11.历史版本
    1.0.6  页面统计丶事件统计丶奔溃统计丶自定义参数统计完善
    
    1.0.7  手动捕获异常上传异常改为全局捕获异常上传
    
    1.0.8  暴露统计用户信息接口，debug模式触发统计打印详细信息
    
### 12.常见问题（GreenDao数据库3.0.0版本冲突）
    删除本地 DaoMaster丶DaoSession丶xxDao文件，重新编译生成。
    （导入报错检查根目录build.gradle是否添加）classpath 'com.github.dcendents:android-maven-gradle-plugin:2.0'
