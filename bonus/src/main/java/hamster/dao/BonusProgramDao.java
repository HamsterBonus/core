package hamster.dao;

import com.nurkiewicz.jdbcrepository.JdbcRepository;
import com.nurkiewicz.jdbcrepository.RowUnmapper;
import com.nurkiewicz.jdbcrepository.TableDescription;
import hamster.model.BonusProgram;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@Repository
public class BonusProgramDao extends JdbcRepository<BonusProgram, String> {

    public static RowMapper<BonusProgram> rowMapper = new RowMapper<BonusProgram>(){

        @Override
        public BonusProgram mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new BonusProgram(
                    rs.getString("id"),
                    rs.getString("description")
            );
        }
    };

    public static RowUnmapper<BonusProgram> rowUnmapper = new RowUnmapper<BonusProgram>(){
        @Override
        public Map<String, Object> mapColumns(BonusProgram bp) {
            return Utils.ColumnsBuilder.create()
                    .add("description", bp.getDescription())
                    .build();
        }
    };

    public BonusProgramDao() {
        super(rowMapper, rowUnmapper, new TableDescription("bonus_program", null, "id"));
    }

}
