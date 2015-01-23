package hamster.dao;

import com.google.common.base.Enums;
import com.google.common.collect.Maps;
import hamster.balance.AmountBuilder;
import hamster.model.Amount;
import hamster.state.State;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Utils {

    private Utils(){
    }

    public static Amount createAmount(ResultSet rs) throws SQLException {
        return AmountBuilder.create()
                    .value(rs.getString("amount_value"))
                    .currency(rs.getString("amount_currency"))
        .build();
    }

    public static <T extends Enum<T>> T createState(ResultSet rs, Class<T> _class) throws SQLException {
        return createState(rs, _class, "state_id");
    }

    public static <T extends Enum<T>> T createState(ResultSet rs, Class<T> _class, String fieldName) throws SQLException {
        return Enums.getIfPresent(_class, rs.getString(fieldName)).orNull();
    }

    public static <T> T choose(List<T> entityOrEmpty){
        return entityOrEmpty.isEmpty() ? null : entityOrEmpty.get(0);
    }

    //ImmutableMap does not support null values
    public static class ColumnsBuilder {

        public static ColumnsBuilder create(){
            return new ColumnsBuilder();
        }

        private Map<String, Object> data;

        private ColumnsBuilder(){
            data = Maps.<String, Object>newHashMap();
        }

        public ColumnsBuilder add(String key, Object value){
            data.put(key, value);
            return this;
        }

        public ColumnsBuilder add(String key, boolean value){
            data.put(key, value ? "1" : "0");
            return this;
        }

        public ColumnsBuilder add(Amount amount){
            data.put("amount_value", amount.getDoubleValue());
            data.put("amount_currency", amount.getCurrencyCode());
            return this;
        }

        public ColumnsBuilder add(State state){
            data.put("state_id", state.getId());
            return this;
        }

        public Map<String, Object> build(){
            return data;
        }
    }
}
