package hamster.bonus.module;

import org.springframework.core.io.ClassPathResource;

public class SqlScriptResource extends ClassPathResource implements SqlScript{

    public SqlScriptResource(String path) {
        super(path);
    }
}
