package hamster.repository;

import com.google.common.collect.ImmutableMap;
import com.nurkiewicz.jdbcrepository.JdbcRepository;
import com.nurkiewicz.jdbcrepository.RowUnmapper;
import com.nurkiewicz.jdbcrepository.TableDescription;
import hamster.balance.AmountBuilder;
import hamster.model.Payment;
import hamster.payment.PaymentBuilder;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@Repository
public class PaymentRepository extends JdbcRepository<Payment, String>{

    public static RowMapper<Payment> rowMapper = new RowMapper<Payment>(){

        @Override
        public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Payment(rs.getString("id"), "1", null, AmountBuilder.create().build());
        }
    };

    public static RowUnmapper<Payment> rowUnmapper = new RowUnmapper<Payment>(){
        @Override
        public Map<String, Object> mapColumns(Payment payment) {
            return ImmutableMap.<String, Object>builder().
                    put("merchant", payment.getMerchant())
                    .build();
        }
    };

    public PaymentRepository() {
        super(rowMapper, rowUnmapper, new TableDescription("payment", null, "id"));
    }

    @Override
    protected Payment postCreate(Payment entity, Number generatedId) {
        return PaymentBuilder.create(entity)
                    .id(generatedId.toString()).build();
    }
}
