package hamster.dao;

import com.google.common.base.Enums;
import com.nurkiewicz.jdbcrepository.JdbcRepository;
import com.nurkiewicz.jdbcrepository.RowUnmapper;
import com.nurkiewicz.jdbcrepository.TableDescription;
import hamster.model.Merchant;
import hamster.model.MerchantState;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@Repository
public class MerchantDao  extends JdbcRepository<Merchant, String> {

    public static RowMapper<Merchant> rowMapper = new RowMapper<Merchant>(){

        @Override
        public Merchant mapRow(ResultSet rs, int rowNum) throws SQLException {
            //todo: get balances
            return new Merchant(
                    rs.getString("id"),
                    Enums.getIfPresent(MerchantState.class, rs.getString("state_id")).orNull()
            );
        }
    };

    public static RowUnmapper<Merchant> rowUnmapper = new RowUnmapper<Merchant>(){
        @Override
        public Map<String, Object> mapColumns(Merchant merchant) {
            //todo: balances
            return Utils.ColumnsBuilder.create()
                    .add("state", merchant.getState().getId())
                    .build();
        }
    };

    public MerchantDao() {
        super(rowMapper, rowUnmapper, new TableDescription("merchant", null, "id"));
    }
}
