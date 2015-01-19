package hamster.dao;

import com.nurkiewicz.jdbcrepository.JdbcRepository;
import com.nurkiewicz.jdbcrepository.RowUnmapper;
import com.nurkiewicz.jdbcrepository.TableDescription;
import hamster.model.PaymentBonus;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@Repository
public class PaymentBonusDaoImpl extends JdbcRepository<PaymentBonus, String> implements PaymentBonusDao {

    public static RowMapper<PaymentBonus> rowMapper = new RowMapper<PaymentBonus>(){

        @Override
        public PaymentBonus mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new PaymentBonus(
                    rs.getString("id"),
                    rs.getString("payment"),
                    rs.getString("partner"),
                    rs.getString("transaction"),
                    Utils.createAmount(rs)
            );
        }
    };

    public static RowUnmapper<PaymentBonus> rowUnmapper = new RowUnmapper<PaymentBonus>(){
        @Override
        public Map<String, Object> mapColumns(PaymentBonus bp) {
            return Utils.ColumnsBuilder.create()
                    .add("payment", bp.getPayment())
                    .add("partner", bp.getPartner())
                    .add("transaction", bp.getTransaction())
                    .add(bp.getAmount())
                    .build();
        }
    };

    public PaymentBonusDaoImpl() {
        super(rowMapper, rowUnmapper, new TableDescription("payment_bonus", null, "id"));
    }
    @Override
    protected PaymentBonus postCreate(PaymentBonus entity, Number generatedId) {
        return new PaymentBonus(generatedId.toString(), entity.getPayment(), entity.getPartner(), entity.getTransaction(), entity.getAmount());
    }

}
