package seedu.linkedout.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.linkedout.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.linkedout.commons.core.Messages.MESSAGE_INVALID_PREFIX;
import static seedu.linkedout.logic.parser.CliSyntax.PREFIX_SKILL;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.linkedout.commons.core.index.Index;
import seedu.linkedout.logic.commands.AddSkillCommand;
import seedu.linkedout.logic.parser.exceptions.ParseException;
import seedu.linkedout.model.skill.Skill;

public class AddSkillCommandParser implements Parser<AddSkillCommand> {

    private static final int NO_ARGUMENTS_PRESENT = 0;

    /**
     * Parses the given {@code String} of arguments in the context of the AddSkillCommand
     * and returns an AddSkillCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddSkillCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SKILL);

        Index index;

        boolean hasInvalidPrefix = ArgumentTokenizer.hasInvalidPrefix(args, argMultimap);
        if (hasInvalidPrefix) {
            throw new ParseException(String.format(MESSAGE_INVALID_PREFIX, AddSkillCommand.MESSAGE_USAGE));
        }
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSkillCommand.MESSAGE_USAGE), pe);
        }

        Set<Skill> skillsToAdd = parseSkillsForEdit(argMultimap.getAllValues(PREFIX_SKILL)).orElse(null);
        if (skillsToAdd == null || skillsToAdd.size() == NO_ARGUMENTS_PRESENT) {
            throw new ParseException(AddSkillCommand.MESSAGE_NOT_EDITED);
        }

        return new AddSkillCommand(index, skillsToAdd);
    }

    /**
     * Parses {@code Collection<String> skills} into a {@code Set<Skill>} if {@code skills} is non-empty.
     * If {@code skills} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Skill>} containing zero skills.
     */
    private Optional<Set<Skill>> parseSkillsForEdit(Collection<String> skills) throws ParseException {
        assert skills != null;

        if (skills.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> skillSet = skills.size() == 1 && skills.contains("") ? Collections.emptySet() : skills;
        return Optional.of(ParserUtil.parseSkills(skillSet));
    }
}
