package seedu.linkedout.model.skill;

import static seedu.linkedout.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SkillTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Skill(null));
    }

    @Test
    public void constructor_invalidSkillName_throwsIllegalArgumentException() {
        String invalidSkillName = "";
        assertThrows(IllegalArgumentException.class, () -> new Skill(invalidSkillName));
    }

    @Test
    public void constructor_allSymbolsSkillName_throwsIllegalArgumentException() {
        String invalidSkillName = "!#$";
        assertThrows(IllegalArgumentException.class, () -> new Skill(invalidSkillName));
    }

    @Test
    public void isValidSkillName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Skill.isValidSkillName(null));
    }

}
