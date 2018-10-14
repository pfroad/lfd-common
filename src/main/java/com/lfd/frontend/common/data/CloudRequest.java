package com.lfd.frontend.common.data;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.text.ParseException;
import java.util.*;

/**
 * Created by ryan on 12/20/16.
 */
public class CloudRequest implements Serializable {
    private String version;
    private String appVersion;
    private Double userLng;
    private Double userLat;
    private String token;
    @Required(true)
    private String sign;
    private Long timestamp;
    private String deviceModel;
    private String screen;
    private String deviceId;
    private String deviceIP;
    @Required(true)
    private String appId;
    private String mobileSystem;
    private Long channelId;
    private ClientType clientType;
    private NetworkType networkType;
    private Map<String, Object> parameters;

    public CloudRequest() {
    }

    public CloudRequest(HttpServletRequest request) throws ServletRequestBindingException {
    	parameters = new HashMap<>();
        Map<String, String[]> map = request.getParameterMap();
		Iterator<String> it = map.keySet().iterator();

        String missingParams = missingParams(map);
        if (!StringUtils.isEmpty(missingParams)) {
            throw new ServiceException("Miss required parameters: " + missingParams.substring(0, missingParams.length() - 1), ResponseCode.MISS_REQUIRED);
        }

		while (it.hasNext()) {
			String key = it.next();
			String[] value = map.get(key);
			
			if (value != null) {
				parameters.put(key, value.length > 1 ? value : value[0]);
			}
		}

		if (request.getAttribute("channelId") != null) {
            this.setChannelId((Long) request.getAttribute("channelId"));
        }

        this.setAppId(ServletRequestUtils.getStringParameter(request, "appId"));
        this.setSign(ServletRequestUtils.getStringParameter(request, "sign"));
    	this.setVersion(ServletRequestUtils.getStringParameter(request, "version", null));
        this.setAppVersion(ServletRequestUtils.getStringParameter(request, "appVersion", null));
        this.setUserLat(this.getDouble("userLat"));
        this.setUserLng(this.getDouble("userLng"));
        this.setToken(ServletRequestUtils.getStringParameter(request, "token", null));
        this.setTimestamp(ServletRequestUtils.getLongParameter(request, "timestamp"));
        this.setDeviceModel(ServletRequestUtils.getStringParameter(request, "deviceModel", null));
        this.setScreen(ServletRequestUtils.getStringParameter(request, "screen", null));
        this.setDeviceId(ServletRequestUtils.getStringParameter(request, "deviceId", null));
        String deviceIP = ServletRequestUtils.getStringParameter(request, "screen", null);
        if (deviceIP == null)
        	deviceIP = (String) request.getAttribute("deviceIP");
        this.setDeviceIP(deviceIP);
        this.setMobileSystem(ServletRequestUtils.getStringParameter(request, "mobileSystem", null));
        this.setClientType(ClientType.getClientType(ServletRequestUtils.getIntParameter(request, "clientType", 0)));
        this.setNetworkType(NetworkType.getNetworkTypes(ServletRequestUtils.getIntParameter(request,"networkType", 0)));
	}

	private String missingParams(final Map<String, String[]> requestParameterMap) {
        return Arrays.stream(this.getClass().getDeclaredFields()).filter(it -> {
            return it.isAnnotationPresent(Required.class) &&
                    it.getAnnotation(Required.class).value() &&
                    (!requestParameterMap.containsKey(it.getName()) || requestParameterMap.get(it.getName()) == null);
        }).map(it -> it.getName() + ",").reduce("", String::concat);
    }

	public enum ClientType {
        UNKNOWN(0, "unknown"),
        IOS(1, "IOS"),
        ANDROID(2, "ANDROID"),
        WP(3, "wp"),
        H5(4, "H5"),
        WEIXIN(5, "weixin"),
        ALIPAY(6, "alipay");
        private int value;
        private String desc;

        ClientType(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }
        public boolean isWeb() {
            return this.value == WEIXIN.getValue()|| this.value == ALIPAY.getValue();
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }

        public static ClientType getClientType(int value) {
            ClientType[] clientTypes = ClientType.values();
            for (ClientType type : clientTypes) {
                if (type.getValue() == value)
                    return type;
            }

            return null;
        }
    }

    public enum NetworkType {
        UNKNOWN(0, "unknown"),
        WIFI(1, "wifi"),
        GSM(2, "2g"),
        CDMA(3, "3g"),
        LTE(4, "4g");
        private int value;
        private String desc;

        NetworkType(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }

        public static NetworkType getNetworkTypes(int value) {
            NetworkType[] networkTypes = NetworkType.values();
            for (NetworkType type : networkTypes) {
                if (type.getValue() == value)
                    return type;
            }

            return null;
        }
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public Double getUserLng() {
        return userLng;
    }

    public void setUserLng(Double userLng) {
        this.userLng = userLng;
    }

    public Double getUserLat() {
        return userLat;
    }

    public void setUserLat(Double userLat) {
        this.userLat = userLat;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceIP() {
        return deviceIP;
    }

    public void setDeviceIP(String deviceIP) {
        this.deviceIP = deviceIP;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMobileSystem() {
        return mobileSystem;
    }

    public void setMobileSystem(String mobileSystem) {
        this.mobileSystem = mobileSystem;
    }

    public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public NetworkType getNetworkType() {
        return networkType;
    }

    public void setNetworkType(NetworkType networkType) {
        this.networkType = networkType;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public Integer getInt(String key) throws NumberFormatException {
        return parameters.containsKey(key) ? isEmpty(parameters.get(key)) ? null : Integer.parseInt(parameters.get(key).toString()) : null;
    }

    public Long getLong(String key) throws NumberFormatException {
        return parameters.containsKey(key) ? isEmpty(parameters.get(key)) ? null : Long.parseLong(parameters.get(key).toString()) : null;
    }

    public Double getDouble(String key) throws NumberFormatException {
        return parameters.containsKey(key) ? isEmpty(parameters.get(key)) ? null : Double.parseDouble(parameters.get(key).toString()) : null;
    }

    public String getString(String key) {
        return parameters.containsKey(key) ? isEmpty(parameters.get(key)) ? null : parameters.get(key).toString() : null;
    }
    
    public String[] getStringArray(String key) {
        return parameters.containsKey(key) ? isEmpty(parameters.get(key)) ? null : (String[]) parameters.get(key) : null;
    }

    public Date getDate(String key) throws ParseException {
        return parameters.containsKey(key) ? isEmpty(parameters.get(key)) ? null : DateUtils.parseDate(parameters.get(key).toString(), Constants.DATETIME_FORMAT) : null;
    }

    public boolean containKey(String key) {
        return parameters.containsKey(key);
    }
    
    public Object get(String key) {
    	return parameters.get(key);
    }
    
    private boolean isEmpty(Object obj) {
    	return obj == null || StringUtils.isEmpty(obj.toString());
    }
}
