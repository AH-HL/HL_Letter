package com.aahl.hl_letter.config;

/**
 * Created by Mr.Hao on 2018/5/22
 */

public class Standard {

    /**
     * 一 ,常量命名规范
     * 1,全部为大写字母；
     * 2,中间以"-"连接；
     * 3,望文知意原则；
     * ( 例如：static final int MIN_SCREEN_WIDTH = 4 )
     */

    /**
     * 二 ,变量命名规范
     * 1,首字母小写；
     * 2,java驼峰命名；
     * 3,望文知意原则；
     * 4,推荐引用类型变量添加前缀"m"；
     * 5,如果是View组件变量,则组件名称为xml文件中定义的ID名称去掉下划线，下划线后一位大写；
     * ( 例如：static final int mMinScreenWidth = 4 )
     */

    /**
     * 三 ,方法命名规范
     * 1,首字母小写；
     * 2, java驼峰命名；
     * 3,简单明了原则；
     * 4, 初始化View方法init*（每个init做一件事）
     * ( 例如：public static int getScreenWidth() )
     */

    /**
     * 四 ,类命名规范
     * 1,首字母大写；
     * 2,java驼峰命名；
     * 3,望文知意原则；
     * 4,能够说明类的功能和主要作用（注释的作用）；
     * 5,Acitivity类以Acitivity结尾；
     * 6,Fragment类以Fragment结尾；
     * 7,Service类以Service结尾；
     * 8,BroadcastReceiver类以Receiver结尾；
     * 9,ContentProvider类以Provider结尾；
     * 10,Application类以Application结尾；
     * 11,自定义View类以Custom**View结尾；
     * 12,自定义Adapter类以Adapter结尾；
     * ( 例如：public class ScreenUtils )
     */

    /**
     * 五 ,接口命名规范
     * 1,首字母大写（第二个字母也是大写）；
     * 2,java驼峰命名；
     * 3,望文知意原则；
     * 4,驼峰表示；；
     * ( 例如：public interface IFunctionListener )
     */

    /**
     * 六 ,布局文件名称规范
     * 1,全部为小写字母；
     * 2,中间以”_”连接；
     * 3,望文知意原则；
     * 4,布局文件的开头问类名；
     * 5,列表项的xml布局文件名称：类名_item.xml；
     * 6,activity类的xml文件名称：类名_activity.xml；
     * 7,fragment类的xml文件名称：类名_fragment.xml；
     * 8,自定义View的xml文件的名称：类名_父类名.xml；
     */

    /**
     * 七 ,资源ID命名规范
     * 1,TextView的组件:tv_pay_money
     * 2,Button的组件：btn_pay_money
     * 3,EditText的组件：et_user_name
     * 4,LinerLayout组件：ll_container
     */

    /**
     * AndFix阿里热修复
     * a.无法添加类和字段
     * b.不能修改xml布局文件
     *
     * 要用正式的签名,签名和apk最好都放到生成patch的目录下,方便操作
     *
     *
     * 命令行进入到生成patch的目录
     * 1,C:\Users\HLL\Desktop\2018AllDownloadCode\AndFix
     * 生成patch并修改output里面对应的.apatch 的名字
     * 2,apkpatch -f app-release1.apk -t app-release.apk -o output -k android_vpclub.keystore -p
     * zte1234$ -a 9225dffc29c07f9f0f04ef1418200038 -e zte1234$
     * 模拟数据到本地进行测试
     * 3,adb push C:\Users\HLL\Desktop\2018AllDownloadCode\AndFix\output\out.apatch /sdcard/
     */


    /**
     * Git
     * 打开本地电脑的git bash，git clone url，
     * 克隆代码到本地，url就是上面Clone or download按钮点开可以看到和复制的地址
     *
     * 本地新建一个文件，用来演示代码提交的过程
     *
     * git add . 添加文件
     * git status -s 列出当前目录所有还没有被git管理的文件和被git管理且被修改但还未提交
     * git commit -m "注释" 提交代码到本地仓库
     * git push 提交代码到远程git仓库
     * push完成之后，登录github上可以查看到本地提交到仓库的代码和记录
     * 输入git pull命令，从远程仓库拉取代码，从提示信息上可以看大在github上面创建的文件，已经被更新到本地代码目录了
     * ls查看目录
     *
     */

}
