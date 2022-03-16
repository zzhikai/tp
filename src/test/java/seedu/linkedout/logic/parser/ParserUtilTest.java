package seedu.linkedout.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.linkedout.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.linkedout.testutil.Assert.assertThrows;
import static seedu.linkedout.testutil.TypicalIndexes.INDEX_FIRST_APPLICANT;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.linkedout.logic.parser.exceptions.ParseException;
import seedu.linkedout.model.applicant.Email;
import seedu.linkedout.model.applicant.Job;
import seedu.linkedout.model.applicant.Name;
import seedu.linkedout.model.applicant.Phone;
import seedu.linkedout.model.applicant.Round;
import seedu.linkedout.model.skill.Skill;


public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_JOB = " ";
    private static final String INVALID_ROUND = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_SKILL = "Skill With Too Many Words Inside";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_JOB = "Software Engineer";
    private static final String VALID_ROUND = "Technical Interview";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_SKILL_1 = "Python";
    private static final String VALID_SKILL_2 = "Java";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_APPLICANT, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_APPLICANT, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseRound_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseRound((String) null));
    }

    @Test
    public void parseRound_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRound(INVALID_ROUND));
    }

    @Test
    public void parseRound_validValueWithoutWhitespace_returnsRound() throws Exception {
        Round expectedRound = new Round(VALID_ROUND);
        assertEquals(expectedRound, ParserUtil.parseRound(VALID_ROUND));
    }

    @Test
    public void parseRound_validValueWithWhitespace_returnsTrimmedRound() throws Exception {
        String roundWithWhitespace = WHITESPACE + VALID_ROUND + WHITESPACE;
        Round expectedRound = new Round(VALID_ROUND);
        assertEquals(expectedRound, ParserUtil.parseRound(roundWithWhitespace));
    }
    @Test
    public void parseJob_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseJob((String) null));
    }

    @Test
    public void parseJob_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseJob(INVALID_JOB));
    }

    @Test
    public void parseJob_validValueWithoutWhitespace_returnsJob() throws Exception {
        Job expectedJob = new Job(VALID_JOB);
        assertEquals(expectedJob, ParserUtil.parseJob(VALID_JOB));
    }

    @Test
    public void parseJob_validValueWithWhitespace_returnsTrimmedJob() throws Exception {
        String jobWithWhitespace = WHITESPACE + VALID_JOB + WHITESPACE;
        Job expectedJob = new Job(VALID_JOB);
        assertEquals(expectedJob, ParserUtil.parseJob(jobWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseSkill_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSkill(null));
    }

    @Test
    public void parseSkill_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSkill(INVALID_SKILL));
    }

    @Test
    public void parseSkill_validValueWithoutWhitespace_returnsSkill() throws Exception {
        Skill expectedSkill = new Skill(VALID_SKILL_1);
        assertEquals(expectedSkill, ParserUtil.parseSkill(VALID_SKILL_1));
    }

    @Test
    public void parseSkill_validValueWithWhitespace_returnsTrimmedSkill() throws Exception {
        String skillWithWhitespace = WHITESPACE + VALID_SKILL_1 + WHITESPACE;
        Skill expectedSkill = new Skill(VALID_SKILL_1);
        assertEquals(expectedSkill, ParserUtil.parseSkill(skillWithWhitespace));
    }

    @Test
    public void parseSkills_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSkills(null));
    }

    @Test
    public void parseSkills_collectionWithInvalidSkills_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSkills(Arrays.asList(VALID_SKILL_1, INVALID_SKILL)));
    }

    @Test
    public void parseSkills_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseSkills(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseSkills_collectionWithValidSkills_returnsSkillSet() throws Exception {
        Set<Skill> actualSkillSet = ParserUtil.parseSkills(Arrays.asList(VALID_SKILL_1, VALID_SKILL_2));
        Set<Skill> expectedSkillSet = new HashSet<Skill>(Arrays.asList(new Skill(VALID_SKILL_1),
                new Skill(VALID_SKILL_2)));

        assertEquals(expectedSkillSet, actualSkillSet);
    }
}
