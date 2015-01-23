package hamster.dao;

import com.nurkiewicz.jdbcrepository.JdbcRepository;
import com.nurkiewicz.jdbcrepository.RowUnmapper;
import com.nurkiewicz.jdbcrepository.TableDescription;
import hamster.model.PartnerMerchant;
import hamster.model.PartnerMerchantState;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@Repository
public class PartnerMerchantDaoImpl extends JdbcRepository<PartnerMerchant, String> implements PartnerMerchantDao {

    public static RowMapper<PartnerMerchant> rowMapper = new RowMapper<PartnerMerchant>(){

        @Override
        public PartnerMerchant mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new PartnerMerchant(
                    rs.getString("id"),
                    rs.getString("parent"),
                    rs.getString("merchant"),
                    rs.getString("partner"),
                    rs.getBoolean("use_merchant_balance"),
                    Utils.createState(rs, PartnerMerchantState.class)
            );
        }
    };

    public static RowUnmapper<PartnerMerchant> rowUnmapper = new RowUnmapper<PartnerMerchant>(){
        @Override
        public Map<String, Object> mapColumns(PartnerMerchant partner) {
            return Utils.ColumnsBuilder.create()
                    .add("parent", partner.getParent())
                    .add("merchant", partner.getMerchant())
                    .add("partner", partner.getPartner())
                    .add("use_merchant_balance", partner.isUseMerchantBalance())
                    .add(partner.getState())
                    .build();
        }
    };

    public PartnerMerchantDaoImpl() {
        super(rowMapper, rowUnmapper, new TableDescription("partner_merchant", null, "id"));
    }

    @Override
    public PartnerMerchant findByParentAndMerchant(String parent, String merchant) {
        return Utils.choose(
                getJdbcOperations().query(
                        "select pm.* from partner_merchant pm where pm.parent = ? and pm.merchant = ? ",
                        new Object[]{parent, merchant},
                        rowMapper
                )
        );
    }
}