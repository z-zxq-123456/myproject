package com.dcits.ensemble.om.pf.module.parameter;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by admin on 2016/03/18 18:02:59.
 */
public class FmChannel extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is FM_CHANNEL.CHANNEL 娓犻亾 鐩墠绯荤粺浣跨敤鍒版笭閬撶被鍨嬫湁锛�MT-鏌滈潰涓氬姟 MC-鍗℃煖闈笟鍔�AC-鏈鑷姪 DP-澶ч鏀粯 MP-灏忛鏀粯 SF-SWIFT CB-缃戜笂閾惰 PB-鐢佃瘽閾惰 CC-CALL CENTRE MB-鐭俊閾惰 UC-閾惰仈浜ゆ槗 PC-鍗℃敮浠樹笟鍔�BC-鏌滈潰鍙戣捣鍗′腑闂翠笟鍔�ND-绁ㄦ嵁绯荤粺鍙戣捣鐨勪笟鍔�AB-鑷姪缁堢绯荤粺鍙戣捣鐨勪腑闂翠笟鍔�BH-涓棿涓氬姟 UT-閾惰仈鏈唬浠栦笟鍔�BI-鎵归噺鎺ュ彛涓氬姟 IF-涓€鑸枃浠舵帴鍙ｄ笟鍔�NI-鍐呴儴涓氬姟澶勭悊 IG-鍐呴儴娓呯畻 CS-璐㈢◣搴撹涓氬姟 SR-(Security)瀹夊叏鍔犲瘑娓犻亾 GL-鎬诲笎绯荤粺涓氬姟
     */
    @TablePk(index = 1)
    private String channel;

    /**
     * This field is FM_CHANNEL.CHANNEL_DESC 娓犻亾鎻忚堪
     */
    private String channelDesc;

    /**
     * This field is FM_CHANNEL.CHANNEL_SHORT 
     */
    private String channelShort;

    /**
     * This field is FM_CHANNEL.COMPANY 
     */
    private String company;

    /**
     * @return the value of  FM_CHANNEL.CHANNEL 娓犻亾 鐩墠绯荤粺浣跨敤鍒版笭閬撶被鍨嬫湁锛�MT-鏌滈潰涓氬姟 MC-鍗℃煖闈笟鍔�AC-鏈鑷姪 DP-澶ч鏀粯 MP-灏忛鏀粯 SF-SWIFT CB-缃戜笂閾惰 PB-鐢佃瘽閾惰 CC-CALL CENTRE MB-鐭俊閾惰 UC-閾惰仈浜ゆ槗 PC-鍗℃敮浠樹笟鍔�BC-鏌滈潰鍙戣捣鍗′腑闂翠笟鍔�ND-绁ㄦ嵁绯荤粺鍙戣捣鐨勪笟鍔�AB-鑷姪缁堢绯荤粺鍙戣捣鐨勪腑闂翠笟鍔�BH-涓棿涓氬姟 UT-閾惰仈鏈唬浠栦笟鍔�BI-鎵归噺鎺ュ彛涓氬姟 IF-涓€鑸枃浠舵帴鍙ｄ笟鍔�NI-鍐呴儴涓氬姟澶勭悊 IG-鍐呴儴娓呯畻 CS-璐㈢◣搴撹涓氬姟 SR-(Security)瀹夊叏鍔犲瘑娓犻亾 GL-鎬诲笎绯荤粺涓氬姟
     */
    public String getChannel() {
        return channel;
    }

    /**
     * @param channel the value for FM_CHANNEL.CHANNEL 娓犻亾 鐩墠绯荤粺浣跨敤鍒版笭閬撶被鍨嬫湁锛�MT-鏌滈潰涓氬姟 MC-鍗℃煖闈笟鍔�AC-鏈鑷姪 DP-澶ч鏀粯 MP-灏忛鏀粯 SF-SWIFT CB-缃戜笂閾惰 PB-鐢佃瘽閾惰 CC-CALL CENTRE MB-鐭俊閾惰 UC-閾惰仈浜ゆ槗 PC-鍗℃敮浠樹笟鍔�BC-鏌滈潰鍙戣捣鍗′腑闂翠笟鍔�ND-绁ㄦ嵁绯荤粺鍙戣捣鐨勪笟鍔�AB-鑷姪缁堢绯荤粺鍙戣捣鐨勪腑闂翠笟鍔�BH-涓棿涓氬姟 UT-閾惰仈鏈唬浠栦笟鍔�BI-鎵归噺鎺ュ彛涓氬姟 IF-涓€鑸枃浠舵帴鍙ｄ笟鍔�NI-鍐呴儴涓氬姟澶勭悊 IG-鍐呴儴娓呯畻 CS-璐㈢◣搴撹涓氬姟 SR-(Security)瀹夊叏鍔犲瘑娓犻亾 GL-鎬诲笎绯荤粺涓氬姟
     */
    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }

    /**
     * @return the value of  FM_CHANNEL.CHANNEL_DESC 娓犻亾鎻忚堪
     */
    public String getChannelDesc() {
        return channelDesc;
    }

    /**
     * @param channelDesc the value for FM_CHANNEL.CHANNEL_DESC 娓犻亾鎻忚堪
     */
    public void setChannelDesc(String channelDesc) {
        this.channelDesc = channelDesc == null ? null : channelDesc.trim();
    }

    /**
     * @return the value of  FM_CHANNEL.CHANNEL_SHORT 
     */
    public String getChannelShort() {
        return channelShort;
    }

    /**
     * @param channelShort the value for FM_CHANNEL.CHANNEL_SHORT 
     */
    public void setChannelShort(String channelShort) {
        this.channelShort = channelShort == null ? null : channelShort.trim();
    }

    /**
     * @return the value of  FM_CHANNEL.COMPANY 
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for FM_CHANNEL.COMPANY 
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}