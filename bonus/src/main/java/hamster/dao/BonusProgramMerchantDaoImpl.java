package hamster.dao;

import com.nurkiewicz.jdbcrepository.JdbcRepository;
import com.nurkiewicz.jdbcrepository.RowUnmapper;
import com.nurkiewicz.jdbcrepository.TableDescription;
import hamster.model.BonusProgramMerchant;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

@Repository
public class BonusProgramMerchantDaoImpl extends JdbcRepository<BonusProgramMerchant, String> implements BonusProgramMerchantDao {

    public static RowMapper<BonusProgramMerchant> rowMapper = new RowMapper<BonusProgramMerchant>(){

        @Override
        public BonusProgramMerchant mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new BonusProgramMerchant(
                    rs.getString("id"),
                    rs.getString("program"),
                    rs.getString("merchant"),
                    rs.getBoolean("by_default")
            );
        }
    };

    public static RowUnmapper<BonusProgramMerchant> rowUnmapper = new RowUnmapper<BonusProgramMerchant>(){
        @Override
        public Map<String, Object> mapColumns(BonusProgramMerchant bpm) {
            return Utils.ColumnsBuilder.create()
                    .add("program", bpm.getProgram())
                    .add("merchant", bpm.getMerchant())
                    .add("by_default", bpm.isByDefault())
                    .build();
        }
    };

    public BonusProgramMerchantDaoImpl() {
        super(rowMapper, rowUnmapper, new TableDescription("bonus_program_merchant", null, "id"));
    }

    public Collection<BonusProgramMerchant> findByMerchant(String merchant){
        return getJdbcOperations().query("select bpm.* from bonus_program_merchant bpm where bpm.merchant = ? ", new Object[]{merchant }, rowMapper);
    }
}
