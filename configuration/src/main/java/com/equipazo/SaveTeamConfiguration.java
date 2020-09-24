package com.equipazo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "team")
public class SaveTeamConfiguration {
    private String filesURI = "";
}
