package seedu.linkedout.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.linkedout.commons.core.GuiSettings;
import seedu.linkedout.commons.core.LogsCenter;
import seedu.linkedout.logic.commands.Command;
import seedu.linkedout.logic.commands.CommandResult;
import seedu.linkedout.logic.commands.exceptions.CommandException;
import seedu.linkedout.logic.parser.LinkedoutParser;
import seedu.linkedout.logic.parser.exceptions.ParseException;
import seedu.linkedout.model.Model;
import seedu.linkedout.model.ReadOnlyLinkedout;
import seedu.linkedout.model.applicant.Applicant;
import seedu.linkedout.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final LinkedoutParser linkedoutParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        linkedoutParser = new LinkedoutParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = linkedoutParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveLinkedout(model.getLinkedout());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyLinkedout getLinkedout() {
        return model.getLinkedout();
    }

    @Override
    public ObservableList<Applicant> getFilteredApplicantList() {
        return model.getFilteredApplicantList();
    }

    @Override
    public ObservableList<Applicant> getSortedApplicantList() {
        return model.getSortedApplicantList();
    }

    @Override
    public Path getLinkedoutFilePath() {
        return model.getLinkedoutFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
