package com.aglhz.s1.common;

import com.aglhz.s1.BuildConfig;

import cn.itsite.abase.common.BaseConstants;

/**
 * Created by Administrator on 2017/5/2 0002.
 */
public class Constants extends BaseConstants {
    public static final String TAG = Constants.class.getSimpleName();
    public static final int RESPONSE_CODE_SUCCESS = 200;

    //不允许new
    private Constants() {
        super();
        throw new Error("Do not need instantiate!");
    }

    //--------------------以下是区分debug版和非debug版的baseurl-----------

    public static String BASE_USER = "";

    static {
        if (BuildConfig.DEBUG) {
            //调试可以改这里的地址。
            BASE_USER = "http://www.aglhz.com:8076/memberSYS-m";           //用户
        } else {
            //这里的是正式版的基础地址，永远不要动。
            BASE_USER = "http://www.aglhz.com:8076/memberSYS-m";           //用户
        }
    }
    //-------------------以上是区分debug版和非debug版的baseurl-----------------

    public static final String PRESS_AGAIN = "再按一次退出";

    public static final String IS_FIRST_ENTER = "is_first_enter";

    //配网模块
    public static final String WIFI_NAME = "wifi_name";
    public static final String WIFI_PASSWORD = "WIFI_PASSWORD";

    //安全模块
    public static final String GATEWAY_STATE_CANCLE = "cancel";
    public static final String GATEWAY_STATE_HOME = "home";
    public static final String GATEWAY_STATE_FARAWAY = "faraway";

    //key
    public static final String KEY_HOST_NUMBER = "host_number";
    public static final String KEY_HOST = "key_host";
//    public static final String KEY_ADD_TYPE = "KEY_ADD_TYPE";//0是添加，1是编辑。


//    public static final int TYPE_ADD_HOST = 0;//0是添加。
//    public static final int TYPE_EDIT_HOST = 1;//1是编辑。


    //额外推送类型。
    public static final String SENSOR_LEARN = "sensor_learn";//传感器学习
    public static final String GW_ALARM_GAS = "gw_alarm_gas";//燃气报警
    public static final String GW_NOTIFIY_DEFENSE_ST = "gw_defense_satus";//主机布防状态变更

    public static final int PAGE_SIZE = 20;

    //设备分类：sensor/device_ctrl 控制类或者探测器类。默认控制类
    public static final String DEVICE_CTRL = "device_ctrl";
    public static final String SENSOR = "sensor";
    public static final String TIME = "time";
    public static final String SCENE = "scene";
    public static final String DEVICE = "device";

    public static final String KEY_SELECTOR = "key_SELECTOR";


    /**
     * 主机配置类型
     */
    public static final String VOLUME = "vol";//音量
    public static final String PHONE = "phones";//手机号
    public static final String PUSH = "pushs";//推送

    /**
     * 主机设置子类型
     */
    public static final String V_GSM = "v_gsm";//留言音量
    public static final String V_SPEECH = "v_speech";//通话音量大小
    public static final String V_ALARM = "v_alarm";//报警音量大小
    public static final String V_TONE = "v_tone";//按键音量+语音提示音量

    public static final String P_MAIN = "p_main";//主卡电话(网关sim卡)
    public static final String P_ALARM = "p_alarm";//报警电话
    public static final String P_PUSH = "p_push";//推送电话

    public static final String PS_DEFENSE_CHG = "p_defense_chg";///** 网关开启布防撤防的推送 */
    public static final String PS_DOOR_CLOSE = "p_door_close";///** 网关开启门关闭的推送 */
    public static final String PS_DOOR_OPEN = "p_door_open";///** 网关开启门打开的推送 */
    public static final String PS_HOST_POWER_LOW = "p_host_power_low";///** 网关开启主机电量低的推送 */
    public static final String PS_POWER_FAIL = "p_power_fail";///** 网关开启外界电源掉电的推送 */
    public static final String PS_POWER_RECOVER = "p_power_recover";///** 网关开启外界电源恢复的推送 */
    public static final String PS_SENSOR_POWER_LOW = "p_sensor_power_low";///** 网关开启传感器电量低的推送 */
    public static final String PS_SMS_TOPHONE = "p_sms_tophone";///** 网关开启指定手机短信的推送 */
    public static final String PS_WIFI_CONNECT = "p_wifi_connect";///** 网关开启WiFi连接的推送 */
    public static final String PS_WIFI_DISCONNECT = "p_wifi_disconnect";///** 网关开启WiFi断开的推送 */
}
