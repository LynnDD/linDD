[TOC]

---------

# 小管家——linDDv1.0

![这里写图片描述](http://img.blog.csdn.net/20170925084636650?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvamlsaW5ncWlhbmc2ODE0/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

发布版本:1.0

日期:2017.09

Copyright 2017 @Dingqiang Lin



**版本记录**

| 日期      | 版本   | 作者   | 版本说明  |
| ------- | ---- | ---- | ----- |
| 2017.09 | v1.0 | 林鼎强  | 备忘录提醒 |



**对象用户**

希望自己定制简单桌面程序的办公族或频繁使用代码人群

的



**环境配置**

64位系统，32位不确定是否能运行



## 安装方案一

- 将jre/文件夹放在固定目录下，建议放在C:\Program Files\下

  ![这里写图片描述](http://img.blog.csdn.net/20170925084323907?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvamlsaW5ncWlhbmc2ODE0/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

- 打开环境变量：右键''计算机" -> 属性 -> 高级系统设置 ->  环境变量

- 在系统变量中新建一条变量：变量名：EXE4J_JAVA_HOME     变量值：C:\Program Files\jre

  ![这里写图片描述](http://img.blog.csdn.net/20170925084348709?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvamlsaW5ncWlhbmc2ODE0/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

**!!!!!!   ( 变量值要根据你把jre/文件夹放的位置来确定)**

- 最后将整个ldqRob_v1.0\文件夹放在自己喜欢的目录下,点击linDD.exe，即可使用

## 安装方案二

- 将整个ldqRob_v1.0\文件夹放在自己喜欢的目录下
- 直接运行run.bat

![这里写图片描述](http://img.blog.csdn.net/20170925084403522?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvamlsaW5ncWlhbmc2ODE0/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

# 主要功能

## 进入主界面前预览

![这里写图片描述](http://img.blog.csdn.net/20170925084417237?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvamlsaW5ncWlhbmc2ODE0/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

## 主界面

主界面会永远在最顶层

![这里写图片描述](http://img.blog.csdn.net/20170925084432222?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvamlsaW5ncWlhbmc2ODE0/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

## 右键菜单

![这里写图片描述](http://img.blog.csdn.net/20170925084508282?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvamlsaW5ncWlhbmc2ODE0/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 备忘录

主显示框：显示当天所有备忘。

![这里写图片描述](http://img.blog.csdn.net/20170925084522201?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvamlsaW5ncWlhbmc2ODE0/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

备注修改：进入tasks.txt所在的文件夹目录

![这里写图片描述](http://img.blog.csdn.net/20170925084537061?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvamlsaW5ncWlhbmc2ODE0/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

直接在txt文件夹中进行修改，在end符号前添加事件（时间（时分）+  内容），例如

**1300**

**午睡**

修改完，ctrl + s后，再在界面中点击“备忘生效”

### 退出

退出小管家



## 左键单击 

关闭所有弹出，只留下小管家



## 左键双击



# 源码
[git 源码和安装包](https://github.com/LynnDD/linDD.git)
[个人git主页](https://github.com/LynnDD)