package hamster.dao;

import com.nurkiewicz.jdbcrepository.JdbcRepository;
import com.nurkiewicz.jdbcrepository.RowUnmapper;
import com.nurkiewicz.jdbcrepository.TableDescription;
import hamster.balance.AmountBuilder;
import hamster.model.Balance;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@Repository
public class BalanceDaoImpl extends JdbcRepository<Balance, String> implements BalanceDao {

    public static RowMapper<Balance> rowMapper = new RowMapper<Balance>(){

        @Override
        public Balance mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Balance(
                    rs.getString("id"),
                    AmountBuilder.create().value(rs.getString("amount_value")).currency(rs.getString("amount_currency")).build(),
                    AmountBuilder.create().value(rs.getString("hold_amount_value")).currency(rs.getString("amount_currency")).build()
            );
        }
    };

    public static RowUnmapper<Balance> rowUnmapper = new RowUnmapper<Balance>(){
        @Override
        public Map<String, Object> mapColumns(Balance balance) {
            return Utils.ColumnsBuilder.create()
                    .add("amount_value", balance.getActiveValue().getValue())
                    .add("amount_currency", balance.getActiveValue().getCurrencyCode())
                    .add("hold_amount_value", balance.getHoldValue().getValue())
                    .build();
        }
    };

    public BalanceDaoImpl() {
        super(rowMapper, rowUnmapper, new TableDescription("balance", null, "id"));
    }

}