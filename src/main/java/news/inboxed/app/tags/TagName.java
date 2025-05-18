package news.inboxed.app.tags;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("TAG_NAMES")
public record TagName(@Id Long id, String name) {
    
}
