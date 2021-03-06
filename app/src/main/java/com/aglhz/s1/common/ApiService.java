package com.aglhz.s1.common;

import com.aglhz.s1.entity.bean.AuthorizationBean;
import com.aglhz.s1.entity.bean.BaseBean;
import com.aglhz.s1.entity.bean.CheckTokenBean;
import com.aglhz.s1.entity.bean.DeviceListBean;
import com.aglhz.s1.entity.bean.DeviceLogBean;
import com.aglhz.s1.entity.bean.DevicesBean;
import com.aglhz.s1.entity.bean.GatewaysBean;
import com.aglhz.s1.entity.bean.HostSettingsBean;
import com.aglhz.s1.entity.bean.LinkageBean;
import com.aglhz.s1.entity.bean.RoomTypesBean;
import com.aglhz.s1.entity.bean.RoomsBean;
import com.aglhz.s1.entity.bean.SceneBean;
import com.aglhz.s1.entity.bean.SecurityBean;
import com.aglhz.s1.entity.bean.SubDeviceDetBean;
import com.aglhz.s1.entity.bean.UserBean;

import java.io.File;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by leguang on 2017/7/4 0022.
 * Email：langmanleguang@qq.com
 */

public interface ApiService {

    String BASE_USER = Constants.BASE_USER;           //用户

    String BASE_URL = Constants.BASE_URL;

    //*************以上基础路径*******************

    //-----------------以下为推送相关---------------------
    //友盟用户登记接口
    String requestRegisterUMeng = BASE_USER + "/client/logUMengParams.do";

    @POST
    Observable<BaseBean> requestRegisterUMeng(@Url String url,
                                              @Query("token") String token,
                                              @Query("deviceToken") String deviceToken,
                                              @Query("alias") String alias,
                                              @Query("aliasType") String aliasType);
    //-----------------以上为推送相关---------------------


    //----------以下为Launch模块--------------
    //登录
    String requestLogin = BASE_USER + "/client/login.do";

    @POST
    Observable<UserBean> requestLogin(@Url String url,
                                      @Query("sc") String sc,
                                      @Query("user") String user,
                                      @Query("pwd") String pwd);

    //注册
    String requestRegister = BASE_USER + "/client/register.do";

    @POST
    Observable<BaseBean> requestRegister(@Url String url,
                                         @Query("sc") String sc,
                                         @Query("account") String account,
                                         @Query("code") String code,
                                         @Query("Password1") String password1,
                                         @Query("Password2") String password2);

    //获取验证码
    String requestVerifyCode = BASE_USER + "/client/validCode.do";

    @POST
    Observable<BaseBean> requestVerifyCode(@Url String url,
                                           @Query("sc") String sc,
                                           @Query("phone") String phone,
                                           @Query("type") String type);

    //重置密码
    String requestResetPassword = BASE_USER + "/client/renewMemberPwd.do";

    @POST
    Observable<BaseBean> requestResetPassword(@Url String url,
                                              @Query("sc") String sc,
                                              @Query("account") String account,
                                              @Query("code") String code,
                                              @Query("pwd1") String password1,
                                              @Query("pwd2") String password2);

    //登录验证
    String requestCheckToken = BASE_USER + "/client/checkIfTokenInvalid.do";

    @POST
    Observable<CheckTokenBean> requestCheckToken(@Url String url,
                                                 @Query("token") String token);

    //登出
    String requestLogout = BASE_USER + "/client/logout.do";

    @POST
    Observable<BaseBean> requestLogout(@Url String url,
                                       @Query("token") String token);

    //----------以上为Launch模块--------------


    //----------------------------- 以下为主机操作相关 ---------------------------------
    //获取主机列表
    String requestGateways = BASE_URL + "/client/info/gatewayList";

    @FormUrlEncoded
    @POST
    Observable<GatewaysBean> requestGateways(@Url String url
            , @Field("token") String token
            , @Field("page") int page
            , @Field("pageSize") int pageSize
    );

    //-----------------------------探测器 相关 ---------------------------------
    //获取探测器列表
    String requestSensorTypeList = BASE_URL + "/client/info/sensorTypeList";

    @FormUrlEncoded
    @POST
    Observable<DevicesBean> requestSensorTypeList(@Url String url
            , @Field("token") String token
            , @Field("page") int page
            , @Field("pageSize") int pageSize
    );

    //添加探测器
    String reqeuestNewsensor = BASE_URL + "/client/newsensor";


    @POST
    Observable<BaseBean> reqeuestNewsensor(@Url String url
            , @Body MultipartBody file
    );

    //取消传感器的学习。
    String reqeuestCancellationOfSensorLearning = BASE_URL + "/client/sensorLearnCancle";

    @POST
    Observable<BaseBean> reqeuestCancellationOfSensorLearning(@Url String url, @Query("token") String token);

    // 修改探测器
    String requestModsensor = BASE_URL + "/client/modsensor";

    @FormUrlEncoded
    @POST
    Observable<BaseBean> requestModsensor(@Url String url
            , @Field("token") String token
            , @Field("file") File file
            , @Field("index") int index
            , @Field("name") String name
            , @Field("defenseLevel") String defenseLevel
            , @Field("alarmDelay") int alarmDelay);

    @POST
    Observable<BaseBean> requestModsensor(@Url String url
            , @Query("token") String token
            , @Body MultipartBody file
    );

    // 删除探测器
    String requestDelsensor = BASE_URL + "/client/delsensor";

    @FormUrlEncoded
    @POST
    Observable<BaseBean> requestDelsensor(@Url String url,
                                          @Field("token") String token,
                                          @Field("index") int index);

    //获取探测器/设备详情
    String requestSubDeviceDet = BASE_URL + "/client/info/subDeviceDet";

    @FormUrlEncoded
    @POST
    Observable<SubDeviceDetBean> requestSubDeviceDet(@Url String url,
                                                     @Field("token") String token,
                                                     @Field("category") String category,
                                                     @Field("index") int index);

    //----------------------------- 设备相关 ---------------------------------
    //添加设备
    String requestNewDevice = BASE_URL + "/client/newdevice";

    @FormUrlEncoded
    @POST
    Observable<BaseBean> requestNewDevice(@Url String url,
                                          @Field("token") String token,
                                          @Field("deviceType") String deviceType,
                                          @Field("name") String name,
                                          @Field("roomFid") String roomFid);


    //修改设备
    String requestModDevice = BASE_URL + "/client/moddevice";

    @POST
    Observable<BaseBean> requestModDevice(@Url String url
            , @Query("token") String token
            , @Body MultipartBody file);

    //删除设备
    String requestDelDevice = BASE_URL + "/client/deldevice";

    @FormUrlEncoded
    @POST
    Observable<BaseBean> requestDelDevice(@Url String url
            , @Field("token") String token
            , @Field("index") int index);

    String requestSubDeviceList = BASE_URL + "/client/info/subDeviceList";

    //设备列表
    @FormUrlEncoded
    @POST
    Observable<DeviceListBean> requestSubDeviceList(@Url String url,
                                                    @Field("token") String token,
                                                    @Field("page") int page,
                                                    @Field("pageSize") int pageSize,
                                                    @Field("roomId") int roomId,
                                                    @Field("category") String category);

    //设备列表
    @FormUrlEncoded
    @POST
    Observable<DeviceListBean> requestSubDeviceList(@Url String url
            , @Field("token") String token
            , @Field("page") int page
            , @Field("pageSize") int pageSize
            , @Field("category") String category);

    //设备控制
    String requestDevicectrl = BASE_URL + "/client/devicectrl";

    @FormUrlEncoded
    @POST
    Observable<BaseBean> requestDevicectrl(@Url String url
            , @Field("token") String token
            , @Field("index") int index
            , @Field("nodeId") String nodeId
            , @Field("status") int status);

    //设备消息记录
    String requestDeviceLogs = BASE_URL + "/client/info/deviceLogs";

    @FormUrlEncoded
    @POST
    Observable<DeviceLogBean> requestDeviceLogs(@Url String url
            , @Field("token") String token
            , @Field("page") int page
            , @Field("pageSize") int pageSize
    );

    //设备类型
    String requestCtrlSDeviceTypeList = BASE_URL + "/client/info/ctrlSDeviceTypeList";

    @POST
    Observable<DevicesBean> requestCtrlSDeviceTypeList(@Url String url);

    String requestNewDeviceConfirm = BASE_URL + "/client/newDeviceConfirm";

    @FormUrlEncoded
    @POST
    Observable<BaseBean> requestNewDeviceConfirm(@Url String url
            , @Field("token") String token
            , @Field("status") int status);


    //----------------------------- 房间相关 ---------------------------------
    //获取房间列表
    String requestRoomList = BASE_URL + "/client/info/roomList";

    @FormUrlEncoded
    @POST
    Observable<RoomsBean> requestRoomList(@Url String url
            , @Field("token") String token
            , @Field("page") int page
            , @Field("pageSize") int pageSize
    );

    //添加房间
    String requestNewroom = BASE_URL + "/client/newroom";

    @FormUrlEncoded
    @POST
    Observable<BaseBean> requestNewroom(@Url String url
            , @Field("token") String token
            , @Field("name") String roomName
            , @Field("roomTypeFid") String roomTypeFid
    );

    //房间类型列表
    String requestRoomTypeList = BASE_URL + "/client/info/roomTypeList";

    @POST
    Observable<RoomTypesBean> requestRoomTypeList(@Url String url);

    //删除房间
    String requestDelroom = BASE_URL + "/client/delroom";

    @FormUrlEncoded
    @POST
    Observable<BaseBean> requestDelroom(@Url String url
            , @Field("token") String token
            , @Field("fid") String fid
    );


    //----------------------------- 联动相关 ---------------------------------
    //添加联动
    String requestNewlinkage = BASE_URL + "/client/newlinkage";

    @FormUrlEncoded
    @POST
    Observable<BaseBean> requestNewlinkage(@Url String url
            , @Field("token") String token
            , @Field("name") String roomName
            , @Field("triggerType") String triggerType
            , @Field("cdt_sensorId") int cdt_sensorId
            , @Field("cdt_sensorAct") String cdt_sensorAct
            , @Field("cdt_day") String cdt_day
            , @Field("cdt_time") String cdt_time
            , @Field("targetType") String targetType
            , @Field("targetId") String targetId
            , @Field("nodeId") String nodeId
            , @Field("act1") String act1
            , @Field("delay") String delay
            , @Field("act2") String act2
    );

    //修改联动
    String requestModLinkage = BASE_URL + "/client/modlinkage";

    @FormUrlEncoded
    @POST
    Observable<BaseBean> requestModLinkage(@Url String url
            , @Field("token") String token
            , @Field("index") int index
            , @Field("name") String name
            , @Field("triggerType") String triggerType
            , @Field("cdt_sensorId") int cdt_sensorId
            , @Field("cdt_sensorAct") String cdt_sensorAct
            , @Field("cdt_day") String cdt_day
            , @Field("cdt_time") String cdt_time
            , @Field("targetType") String targetType
            , @Field("targetId") String targetId
            , @Field("nodeId") String nodeId
            , @Field("act1") String act1
            , @Field("delay") String delay
            , @Field("act2") String act2
            , @Field("status") int status);


    //联动列表
    String requestLinkageList = BASE_URL + "/client/info/linkageList";

    @FormUrlEncoded
    @POST
    Observable<LinkageBean> requestLinkageList(@Url String url
            , @Field("token") String token
            , @Field("page") int page
            , @Field("pageSize") int pageSize
    );

    //删除联动
    String requestDellinkage = BASE_URL + "/client/dellinkage";

    @FormUrlEncoded
    @POST
    Observable<BaseBean> requestDellinkage(@Url String url
            , @Field("token") String token
            , @Field("index") int index
    );
    //----------------------------- 授权相关 ---------------------------------

    //会员主机授权列表
    String requestGatewayAuthList = BASE_URL + "/client/info/gatewayAuthList";

    @FormUrlEncoded
    @POST
    Observable<AuthorizationBean> requestGatewayAuthList(@Url String url,
                                                         @Field("token") String token,
                                                         @Field("gateway") String gateway,
                                                         @Field("page") int page,
                                                         @Field("pageSize") int pageSize);

    //解除主机授权
    String requestGatewayUnAuth = BASE_URL + "/client/gatewayUnAuth";

    @FormUrlEncoded
    @POST
    Observable<BaseBean> requestGatewayUnAuth(@Url String url,
                                              @Field("token") String token,
                                              @Field("gateway") String gateway,
                                              @Field("fid") String fid);

    //选择一个主机，授权给用户
    String requestGatewayAuth = BASE_URL + "/client/gatewayAuth";

    @FormUrlEncoded
    @POST
    Observable<BaseBean> requestGatewayAuth(@Url String url
            , @Field("token") String token
            , @Field("gateway") String gateway
            , @Field("mobile") String mobile);

    //解绑当前主机
    String requestUnbindHost = BASE_URL + "/client/delgateway";

    @FormUrlEncoded
    @POST
    Observable<BaseBean> requestUnbindHost(@Url String url
            , @Field("token") String token
            , @Field("gateway") String gateway);

    //----------------------------- 关于 ---------------------------------
    String requestFeedback = BASE_URL + "/client/feedback";

    @FormUrlEncoded
    @POST
    Observable<BaseBean> requestFeedback(@Url String url,
                                         @Field("token") String token,
                                         @Field("des") String des,
                                         @Field("contact") String contact
    );

    //-----------------以下为主机操作相关---------------------
    //切换主机
    String requestSwichGateway = BASE_URL + "/client/gatewaySW";

    @POST
    Observable<BaseBean> requestSwichGateway(@Url String url,
                                             @Query("token") String token,
                                             @Query("gateway") String gateway);

    //切换主机状态
    String requestSwichState = BASE_URL + "/client/gatewayDSS";

    @POST
    Observable<BaseBean> requestSwichState(@Url String url,
                                           @Query("token") String token,
                                           @Query("dstatus") String dstatus);

    //主页显示内容
    String requestSecurity = BASE_URL + "/client/info/homePage ";

    @POST
    Observable<SecurityBean> requestSecurity(@Url String url,
                                             @Query("token") String token);


    //添加主机
    String requestAddHost = BASE_URL + "/client/newgateway3";

    @FormUrlEncoded
    @POST
    Observable<BaseBean> requestAddHost(@Url String url,
                                        @Field("token") String token,
                                        @Field("no") String no,
                                        @Field("name") String name,
                                        @Field("addr") String addr,
                                        @Field("lng") String lng,
                                        @Field("lat") String lat);

    //修改主机定位
    String requestEditHostLocation = BASE_URL + "/client/gatewayAddrSet";

    @FormUrlEncoded
    @POST
    Observable<BaseBean> requestEditHostLocation(@Url String url,
                                                 @Field("token") String token,
                                                 @Field("addr") String addr,
                                                 @Field("lng") String lng,
                                                 @Field("lat") String lat,
                                                 @Field("gateway") String gateway);

    //主机配置都归为这一个借口。如：音量，短信，推送等。
    String requestHostConfig = BASE_URL + "/client/gatewayConfig";

    @FormUrlEncoded
    @POST
    Observable<BaseBean> requestHostConfig(@Url String url,
                                           @Field("gateway") String gateway,
                                           @Field("token") String token,
                                           @Field("type") String type,
                                           @Field("subType") String subType,
                                           @Field("val") String val);


    //获取主机设置信息。
    String requestHostSettings = BASE_URL + "/client/info/gatewayConfigInfo";

    @FormUrlEncoded
    @POST
    Observable<HostSettingsBean> requestHostSettings(@Url String url,
                                                     @Field("gateway") String gateway,
                                                     @Field("token") String token,
                                                     @Field("type") String type);

    //主机配置都归为这一个借口。如：音量，短信，推送等。
    String requestUpdateHostName = BASE_URL + "/client/modgateway";

    @FormUrlEncoded
    @POST
    Observable<BaseBean> requestUpdateHostName(@Url String url,
                                               @Field("token") String token,
                                               @Field("gateway") String gateway,
                                               @Field("name") String name);

    //主机留言。
    String requestLeaveMassge = BASE_URL + "/client/gatewayMsgLeave";

    @POST
    Observable<BaseBean> requestLeaveMassge(@Url String url,
                                            @Query("token") String token,
                                            @Body MultipartBody file);

    //----------------------------- 以上为主机操作相关 ---------------------------------

    //----------------------------- 以下为场景 ----------------------------------

    //场景列表
    String requestSceneList = BASE_URL + "/client/info/sceneList";

    @FormUrlEncoded
    @POST
    Observable<SceneBean> requestSceneList(@Url String url,
                                           @Field("pageSize") int pageSize,
                                           @Field("page") int page,
                                           @Field("token") String token);

    //启动场景
    String requestStartScene = BASE_URL + "/client/scenectrl";

    @POST
    Observable<BaseBean> requestStartScene(@Url String url,
                                           @Query("token") String token,
                                           @Query("index") int index);

    //删除场景
    String requestDeleteScene = BASE_URL + "/client/delscene";

    @POST
    Observable<BaseBean> requestDeleteScene(@Url String url,
                                            @Query("token") String token,
                                            @Query("index") int index);


    // 添加场景
    String requestAddScene = BASE_URL + "/client/newscene";

    @FormUrlEncoded
    @POST
    Observable<BaseBean> requestAddScene(@Url String url,
                                         @Field("token") String token,
                                         @Field("name") String name,
                                         @Field("paramJson") String paramJson);

    //----------------------------- 以上为场景 ----------------------------------


    //********************以下为更新App接口*******************************
    String requestAppUpdatae = BASE_URL + "/client/info/checkVersion";

    //********************以上为更新App接口*******************************
}
