package net.ttcxy.tang.properties;

/**
 * SpringSecurity 配置文件
 * @author huanlgei
 */
public class SpringSecurityProperties {




    /**
     * 记住登陆
     */
    private boolean rememberMe = false;

    /**
     * 需要登陆的URL
     */
    private String privateUrl;

    /**
     * 开发OPEN URL
     */
    private String openUrl;


    /**
     * 不需要csrf保护的页面给
     */
    private String ignoringUrl;

    /**
     * 登陆页面
     */
    private String loginPagePath = "/";

    /**
     * 登陆请求
     */
    private String formLoginApi = "/doLogin";

    // 默认登录请求
    private String verifyUri = formLoginApi;


    /**
     * 获取token登陆
     */
    private String tokenLoginApi;


    /**
     * token有效时间
     */
    private int tokenTime = 2592000;

    public String getVerifyUri() {
        return verifyUri;
    }

    public void setVerifyUri(String verifyUri) {
        this.verifyUri = verifyUri;
    }

    public String getPrivateUrl() {
        return privateUrl;
    }

    public void setPrivateUrl(String privateUrl) {
        this.privateUrl = privateUrl;
    }

    public int getTokenTime() {
        return tokenTime;
    }

    public void setTokenTime(int tokenTime) {
        this.tokenTime = tokenTime;
    }

    public String getOpenUrl() {
        return openUrl;
    }

    public void setOpenUrl(String openUrl) {
        this.openUrl = openUrl;
    }

    public String getLoginPagePath() {
        return loginPagePath;
    }

    public void setLoginPagePath(String loginPagePath) {
        this.loginPagePath = loginPagePath;
    }

    public String getFormLoginApi() {
        return formLoginApi;
    }

    public void setFormLoginApi(String formLoginApi) {
        this.formLoginApi = formLoginApi;
    }

    public String getTokenLoginApi() {
        return tokenLoginApi;
    }

    public void setTokenLoginApi(String tokenLoginApi) {
        this.tokenLoginApi = tokenLoginApi;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getIgnoringUrl() {
        return ignoringUrl;
    }

    public void setIgnoringUrl(String ignoringUrl) {
        this.ignoringUrl = ignoringUrl;
    }

    @Override
    public String toString() {
        return "SpringSecurityProperties{" +
                "rememberMe=" + rememberMe +
                ", privateUrl='" + privateUrl + '\'' +
                ", openUrl='" + openUrl + '\'' +
                ", ignoringUrl='" + ignoringUrl + '\'' +
                ", loginPagePath='" + loginPagePath + '\'' +
                ", formLoginApi='" + formLoginApi + '\'' +
                ", tokenLoginApi='" + tokenLoginApi + '\'' +
                ", tokenTime=" + tokenTime +
                '}';
    }
}
