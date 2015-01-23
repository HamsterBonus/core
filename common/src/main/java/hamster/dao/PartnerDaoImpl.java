package hamster.dao;

import com.nurkiewicz.jdbcrepository.JdbcRepository;
import com.nurkiewicz.jdbcrepository.RowUnmapper;
import com.nurkiewicz.jdbcrepository.TableDescription;
import hamster.model.Partner;
import hamster.model.PartnerState;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@Repository
public class PartnerDaoImpl extends JdbcRepository<Partner, String> implements PartnerDao {

    public static RowMapper<Partner> rowMapper = new RowMapper<Partner>(){

        @Override
        public Partner mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Partner(
                    rs.getString("id"),
                    Utils.createState(rs, PartnerState.class)
            );
        }
    };

    public static RowUnmapper<Partner> rowUnmapper = new RowUnmapper<Partner>(){
        @Override
        public Map<String, Object> mapColumns(Partner partner) {
            return Utils.ColumnsBuilder.create()
                    .add(partner.getState())
                    .build();
        }
    };

    public PartnerDaoImpl() {
        super(rowMapper, rowUnmapper, new TableDescription("partner", null, "id"));
    }

}
