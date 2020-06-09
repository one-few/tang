package net.ttcxy.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author huanglei
 */
@Component
@ConfigurationProperties(prefix = "tangtang")
@Getter
@Setter
@ToString
public class TangTangProperties {

    /**
     * 安全相关配置
     */
    SpringSecurityProperties security = new SpringSecurityProperties();

    /**
     * API相关配置
     */
    CloudProperties cloud = new CloudProperties();

    /**
     * 系统配置
     */
    SysConfigProperties sys = new SysConfigProperties();

}
