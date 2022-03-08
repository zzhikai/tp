package seedu.linkedout.model;

import java.nio.file.Path;

import seedu.linkedout.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getLinkedoutFilePath();

}
