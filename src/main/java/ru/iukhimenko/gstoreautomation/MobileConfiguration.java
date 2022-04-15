package ru.iukhimenko.gstoreautomation;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

@Config.Sources("classpath:" + MobileConfiguration.PROPERTY_PATH)
public interface MobileConfiguration extends Config {
    String PROPERTY_PATH = "mobile.properties";
    MobileConfiguration CONFIG = ConfigFactory.create(MobileConfiguration.class);

    @Key("mobile.hub.url")
    String mobileHubUrl();

    @Key("mobile.device.name")
    String deviceName();

    @Key("mobile.automation.name")
    String automationName();

    @Key("app.name")
    String appName();
}
