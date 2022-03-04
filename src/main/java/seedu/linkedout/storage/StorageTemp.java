package seedu.linkedout.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.linkedout.commons.exceptions.DataConversionException;
import seedu.linkedout.model.ReadOnlyLinkedout;
import seedu.linkedout.model.ReadOnlyUserPrefs;
import seedu.linkedout.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface StorageTemp extends LinkedoutStorage, UserPrefsStorage {
    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getLinkedoutFilePath();

    @Override
    Optional<ReadOnlyLinkedout> readLinkedout() throws DataConversionException, IOException;

    @Override
    void saveLinkedout(ReadOnlyLinkedout linkedout) throws IOException;
}
