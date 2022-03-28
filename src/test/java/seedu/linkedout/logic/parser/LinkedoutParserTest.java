package seedu.linkedout.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.linkedout.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.linkedout.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.linkedout.logic.parser.CliSyntax.PREFIX_FIELD;
import static seedu.linkedout.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.linkedout.logic.parser.CliSyntax.PREFIX_ORDER;
import static seedu.linkedout.testutil.Assert.assertThrows;
import static seedu.linkedout.testutil.TypicalIndexes.INDEX_FIRST_APPLICANT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.linkedout.logic.commands.AddCommand;
import seedu.linkedout.logic.commands.ClearCommand;
import seedu.linkedout.logic.commands.DeleteCommand;
import seedu.linkedout.logic.commands.EditCommand;
import seedu.linkedout.logic.commands.EditCommand.EditApplicantDescriptor;
import seedu.linkedout.logic.commands.ExitCommand;
import seedu.linkedout.logic.commands.HelpCommand;
import seedu.linkedout.logic.commands.ListCommand;
import seedu.linkedout.logic.commands.SearchCommand;
import seedu.linkedout.logic.commands.SortCommand;
import seedu.linkedout.logic.commands.ViewCommand;
import seedu.linkedout.logic.parser.exceptions.ParseException;
import seedu.linkedout.model.applicant.Applicant;
import seedu.linkedout.model.applicant.Field;
import seedu.linkedout.model.applicant.KeywordsPredicate;
import seedu.linkedout.model.applicant.NameContainsAllKeywordsPredicate;
import seedu.linkedout.model.applicant.NameContainsKeywordsPredicate;
import seedu.linkedout.model.applicant.Order;
import seedu.linkedout.model.applicant.SortComparator;
import seedu.linkedout.testutil.ApplicantBuilder;
import seedu.linkedout.testutil.ApplicantUtil;
import seedu.linkedout.testutil.EditApplicantDescriptorBuilder;

public class LinkedoutParserTest {

    private final LinkedoutParser parser = new LinkedoutParser();

    @Test
    public void parseCommand_add() throws Exception {
        Applicant applicant = new ApplicantBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(ApplicantUtil.getAddCommand(applicant));
        assertEquals(new AddCommand(applicant), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_APPLICANT.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_APPLICANT), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Applicant applicant = new ApplicantBuilder().build();
        EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder(applicant).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_APPLICANT.getOneBased() + " "
                + ApplicantUtil.getEditApplicantDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_APPLICANT, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_view() throws Exception {
        String keywords = "foo bar baz".toLowerCase();
        ViewCommand command = (ViewCommand) parser.parseCommand(
                ViewCommand.COMMAND_WORD + " " + keywords);
        assertEquals(new ViewCommand(new NameContainsAllKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_search() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        List<KeywordsPredicate> keywordsPredicateList = new ArrayList<>();
        SearchCommand command = (SearchCommand) parser.parseCommand(
                SearchCommand.COMMAND_WORD + " "
                        + PREFIX_NAME.getPrefix() + keywords.stream().collect(Collectors.joining(" ")));
        Collections.addAll(keywordsPredicateList, new NameContainsKeywordsPredicate(keywords));
        assertEquals(new SearchCommand(keywordsPredicateList), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_sort() throws Exception {
        Field field = new Field("NAME");
        Order order = new Order("ASC");
        SortCommand command = (SortCommand) parser.parseCommand(SortCommand.COMMAND_WORD + " "
                        + PREFIX_FIELD + field + " " + PREFIX_ORDER + order.toString());
        assertEquals(new SortCommand(new SortComparator(field, order)), command);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
