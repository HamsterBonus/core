package hamster.dao;

import com.google.common.base.Enums;
import com.nurkiewicz.jdbcrepository.JdbcRepository;
import com.nurkiewicz.jdbcrepository.RowUnmapper;
import com.nurkiewicz.jdbcrepository.TableDescription;
import hamster.model.ProgramPartner;
import hamster.model.ProgramPartnerState;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

@Repository
public class ProgramPartnerDaoImpl extends JdbcRepository<ProgramPartner, String> implements ProgramPartnerDao {

    public static RowMapper<ProgramPartner> rowMapper = new RowMapper<ProgramPartner>(){

        @Override
        public ProgramPartner mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new ProgramPartner(
                    rs.getString("id"),
                    rs.getString("program"),
                    rs.getString("partner"),
                    rs.getBoolean("by_default"),
                    Enums.getIfPresent(ProgramPartnerState.class, rs.getString("state_id")).orNull()
            );
        }
    };

    public static RowUnmapper<ProgramPartner> rowUnmapper = new RowUnmapper<ProgramPartner>(){
        @Override
        public Map<String, Object> mapColumns(ProgramPartner bpm) {
            return Utils.ColumnsBuilder.create()
                    .add("program", bpm.getProgram())
                    .add("partner", bpm.getPartner())
                    .add("by_default", bpm.isByDefault())
                    .add("state_id", bpm.getState().getId())
                    .build();
        }
    };

    public ProgramPartnerDaoImpl() {
        super(rowMapper, rowUnmapper, new TableDescription("program_partner", null, "id"));
    }

    public Collection<ProgramPartner> findByPartner(String partner){
        return getJdbcOperations().query("select bpm.* from program_partner bpm where bpm.partner = ? ", new Object[]{partner }, rowMapper);
    }
}
