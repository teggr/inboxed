package news.inboxed.app.data;

import java.util.List;

import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.stereotype.Component;

import news.inboxed.app.webshares.URLToStringConverter;

@Component
public class CustomJdbcConfiguration extends AbstractJdbcConfiguration {

    @Override
    protected List<?> userConverters() {
        return List.of(
            new URLToStringConverter()
        );
    }

}
