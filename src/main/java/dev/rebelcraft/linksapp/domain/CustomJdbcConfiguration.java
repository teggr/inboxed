package dev.rebelcraft.linksapp.domain;

import java.util.List;

import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.stereotype.Component;

@Component
public class CustomJdbcConfiguration extends AbstractJdbcConfiguration {

    @Override
    protected List<?> userConverters() {
        return List.of(
            new URLToStringConverter()
        );
    }

}
