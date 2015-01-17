package hamster.bonus;

import hamster.model.BonusProgram;

public interface ProgramChooser {

    BonusProgram get(BonusData data, String partner);

}
