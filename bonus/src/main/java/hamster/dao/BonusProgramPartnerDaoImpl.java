package hamster.dao;

import com.nurkiewicz.jdbcrepository.JdbcRepository;
import com.nurkiewicz.jdbcrepository.RowUnmapper;
import com.nurkiewicz.jdbcrepository.TableDescription;
import hamster.model.BonusProgramPartner;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

@Repository
public class BonusProgramPartnerDaoImpl extends JdbcRepository<BonusProgramPartner, String> implements BonusProgramPartnerDao {

    public static RowMapper<BonusProgramPartner> rowMapper = new RowMapper<BonusProgramPartner>(){

        @Override
        public BonusProgramPartner mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new BonusProgramPartner(
                    rs.getString("id"),
                    rs.getString("program"),
                    rs.getString("partner"),
                    rs.getBoolean("by_default")
            );
        }
    };

    public static RowUnmapper<BonusProgramPartner> rowUnmapper = new RowUnmapper<BonusProgramPartner>(){
        @Override
        public Map<String, Object> mapColumns(BonusProgramPartner bpm) {
            return Utils.ColumnsBuilder.create()
                    .add("program", bpm.getProgram())
                    .add("partner", bpm.getPartner())
                    .add("by_default", bpm.isByDefault())
                    .build();
        }
    };

    public BonusProgramPartnerDaoImpl() {
        super(rowMapper, rowUnmapper, new TableDescription("bonus_program_partner", null, "id"));
    }

    public Collection<BonusProgramPartner> findByPartner(String partner){
        return getJdbcOperations().query("select bpm.* from bonus_program_partner bpm where bpm.partner = ? ", new Object[]{partner }, rowMapper);
    }
}
