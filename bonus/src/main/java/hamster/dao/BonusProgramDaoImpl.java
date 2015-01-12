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
public class BonusProgramDaoImpl extends JdbcRepository<BonusProgram, String> implements BonusProgramDao {

    public static RowMapper<BonusProgram> rowMapper = new RowMapper<BonusProgram>(){

        @Override
        public BonusProgram mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new BonusProgram(
                    rs.getString("id"),
                    rs.getString("description"),
                    rs.getBigDecimal("percent"),
                    rs.getBoolean("can_be_changed")
            );
        }
    };

    public static RowUnmapper<BonusProgram> rowUnmapper = new RowUnmapper<BonusProgram>(){
        @Override
        public Map<String, Object> mapColumns(BonusProgram bp) {
            return Utils.ColumnsBuilder.create()
                    .add("description", bp.getDescription())
                    .add("percent", bp.getPercent())
                    .add("can_be_changed", bp.isCanBeChanged())
                    .build();
        }
    };

    public BonusProgramDaoImpl() {
        super(rowMapper, rowUnmapper, new TableDescription("bonus_program", null, "id"));
    }

}
