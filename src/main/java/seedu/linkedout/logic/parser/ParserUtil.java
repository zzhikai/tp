package seedu.linkedout.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.linkedout.commons.core.index.Index;
import seedu.linkedout.commons.util.StringUtil;
import seedu.linkedout.logic.parser.exceptions.ParseException;
import seedu.linkedout.model.applicant.Email;
import seedu.linkedout.model.applicant.Job;
import seedu.linkedout.model.applicant.Name;
import seedu.linkedout.model.applicant.Order;
import seedu.linkedout.model.applicant.Phone;
import seedu.linkedout.model.applicant.Round;
import seedu.linkedout.model.skill.Skill;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String job} into a {@code Job}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code job} is invalid.
     */
    public static Job parseJob(String job) throws ParseException {
        requireNonNull(job);
        String trimmedJob = job.trim();
        if (!Job.isValidJob(trimmedJob)) {
            throw new ParseException(Job.MESSAGE_CONSTRAINTS);
        }
        return new Job(trimmedJob);
    }

    /**
     * Parses a {@code String round} into a {@code Round}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code round} is invalid.
     */
    public static Round parseRound(String round) throws ParseException {
        requireNonNull(round);
        String trimmedRound = round.trim();
        if (!Round.isValidRound(trimmedRound)) {
            throw new ParseException(Round.MESSAGE_CONSTRAINTS);
        }
        return new Round(trimmedRound);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String skill} into a {@code Skill}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code skill} is invalid.
     */
    public static Skill parseSkill(String skill) throws ParseException {
        requireNonNull(skill);
        String trimmedSkill = skill.trim();
        if (!Skill.isValidSkillName(trimmedSkill)) {
            throw new ParseException(Skill.MESSAGE_CONSTRAINTS);
        }
        return new Skill(trimmedSkill);
    }

    /**
     * Parses {@code Collection<String> skills} into a {@code Set<Skill>}.
     */
    public static Set<Skill> parseSkills(Collection<String> skills) throws ParseException {
        requireNonNull(skills);
        final Set<Skill> skillSet = new HashSet<>();
        for (String skillName : skills) {
            skillSet.add(parseSkill(skillName));
        }
        return skillSet;
    }

    /**
     * Parses a {@code String order} into an {@code Order}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Order parseOrder(String order) throws ParseException {
        requireNonNull(order);
        String trimmedOrder = order.trim();
        if (!Order.isValidOrder(trimmedOrder)) {
            throw new ParseException(Order.MESSAGE_CONSTRAINTS);
        }
        return new Order(trimmedOrder);
    }
}
