package hamster.dao;

import com.nurkiewicz.jdbcrepository.JdbcRepository;
import com.nurkiewicz.jdbcrepository.RowUnmapper;
import com.nurkiewicz.jdbcrepository.TableDescription;
import hamster.model.PartnerBalance;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

@Repository
public class PartnerBalanceDaoImpl extends JdbcRepository<PartnerBalance, String> implements PartnerBalanceDao {

    public static RowMapper<PartnerBalance> rowMapper = new RowMapper<PartnerBalance>(){

        @Override
        public PartnerBalance mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new PartnerBalance(
                    rs.getString("id"),
                    rs.getString("partner"),
                    rs.getString("balance")
            );
        }
    };

    public static RowUnmapper<PartnerBalance> rowUnmapper = new RowUnmapper<PartnerBalance>(){
        @Override
        public Map<String, Object> mapColumns(PartnerBalance pb) {
            return Utils.ColumnsBuilder.create()
                    .add("partner", pb.getPartner())
                    .add("balance", pb.getBalance())
                    .build();
        }
    };

    public PartnerBalanceDaoImpl() {
        super(rowMapper, rowUnmapper, new TableDescription("partner_balance", null, "id"));
    }

    @Override
    public Collection<PartnerBalance> findByPartner(String partner) {
        return getJdbcOperations().query(
                "select pb.* from partner_balance pb where pb.partner = ?",
                new Object[]{partner},
                rowMapper
        );

    }
}