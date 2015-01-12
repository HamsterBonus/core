package hamster.dao;

import com.nurkiewicz.jdbcrepository.JdbcRepository;
import com.nurkiewicz.jdbcrepository.RowUnmapper;
import com.nurkiewicz.jdbcrepository.TableDescription;
import hamster.model.Payment;
import hamster.payment.PaymentBuilder;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import static hamster.dao.Utils.*;

@Repository
public class PaymentDaoImpl extends JdbcRepository<Payment, String> implements PaymentDao{

    public static RowMapper<Payment> rowMapper = new RowMapper<Payment>(){

        @Override
        public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Payment(
                    rs.getString("id"),
                    rs.getString("merchant"),
                    rs.getString("transaction"),
                    createAmount(rs)
            );
        }
    };

    public static RowUnmapper<Payment> rowUnmapper = new RowUnmapper<Payment>(){
        @Override
        public Map<String, Object> mapColumns(Payment payment) {
            return ColumnsBuilder.create()
                    .add("merchant", payment.getMerchant())
                    .add("transaction", payment.getTransaction())
                    .add(payment.getAmount())
                    .build();
        }
    };

    public PaymentDaoImpl() {
        super(rowMapper, rowUnmapper, new TableDescription("payment", null, "id"));
    }

    @Override
    protected Payment postCreate(Payment entity, Number generatedId) {
        return PaymentBuilder.create(entity)
                .id(generatedId.toString()).build();
    }
}
