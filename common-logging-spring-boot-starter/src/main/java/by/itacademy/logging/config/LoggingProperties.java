package by.itacademy.logging.config;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Slf4j
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = "app.service.logging")
public class LoggingProperties {
    /**
     * enable service layer aop logging
     */
    private boolean enabled;

    @PostConstruct
    public void init() {
        log.info("Logging properties initialized {}", this);
    }
}
