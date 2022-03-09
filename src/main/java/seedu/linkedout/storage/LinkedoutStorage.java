package seedu.linkedout.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.linkedout.commons.exceptions.DataConversionException;
import seedu.linkedout.model.ReadOnlyLinkedout;

/**
 * Represents a storage for {@link seedu.linkedout.model.Linkedout}.
 */
public interface LinkedoutStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getLinkedoutFilePath();

    /**
     * Returns Linkedout app data as a {@link ReadOnlyLinkedout}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyLinkedout> readLinkedout() throws DataConversionException, IOException;

    /**
     * @see #getLinkedoutFilePath()
     */
    Optional<ReadOnlyLinkedout> readLinkedout(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyLinkedout} to the storage.
     * @param linkedout cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveLinkedout(ReadOnlyLinkedout linkedout) throws IOException;

    /**
     * @see #saveLinkedout(ReadOnlyLinkedout)
     */
    void saveLinkedout(ReadOnlyLinkedout linkedout, Path filePath) throws IOException;

}
