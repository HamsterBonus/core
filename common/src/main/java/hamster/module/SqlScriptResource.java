package hamster.module;

import org.springframework.core.io.ClassPathResource;

public class SqlScriptResource extends ClassPathResource implements SqlScript{

    private final int order;

    public SqlScriptResource(int order, String path) {
        super(path);
        this.order = order;
    }

    @Override
    public int getOrder() {
        return order;
    }

    @Override
    public int compareTo(SqlScript o) {
        return order - o.getOrder();
    }
}
