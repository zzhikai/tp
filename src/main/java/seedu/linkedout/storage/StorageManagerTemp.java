package seedu.linkedout.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.linkedout.commons.core.LogsCenter;
import seedu.linkedout.commons.exceptions.DataConversionException;
import seedu.linkedout.model.ReadOnlyLinkedout;
import seedu.linkedout.model.ReadOnlyUserPrefs;
import seedu.linkedout.model.UserPrefs;

/**
 * Manages storage of Linkedout book data in local storage.
 */
public class StorageManagerTemp implements StorageTemp {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private LinkedoutStorage linkedoutStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code LinkedoutStorage} and {@code UserPrefStorage}.
     */
    public StorageManagerTemp(LinkedoutStorage linkedoutStorage, UserPrefsStorage userPrefsStorage) {
        this.linkedoutStorage = linkedoutStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ Linkedout methods ==============================

    @Override
    public Path getLinkedoutFilePath() {
        return linkedoutStorage.getLinkedoutFilePath();
    }

    @Override
    public Optional<ReadOnlyLinkedout> readLinkedout() throws DataConversionException, IOException {
        return readLinkedout(linkedoutStorage.getLinkedoutFilePath());
    }

    @Override
    public Optional<ReadOnlyLinkedout> readLinkedout(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return linkedoutStorage.readLinkedout(filePath);
    }

    @Override
    public void saveLinkedout(ReadOnlyLinkedout linkedout) throws IOException {
        saveLinkedout(linkedout, linkedoutStorage.getLinkedoutFilePath());
    }

    @Override
    public void saveLinkedout(ReadOnlyLinkedout linkedout, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        linkedoutStorage.saveLinkedout(linkedout, filePath);
    }

}
