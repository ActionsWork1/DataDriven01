package auto.play01.base;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:framework.properties"})
public interface FrameworkConfig extends Config {
    @Config.Key("base.url")
    String baseUrl();

    @Key("browser")
    @DefaultValue("chromium")
    String browser();
}
