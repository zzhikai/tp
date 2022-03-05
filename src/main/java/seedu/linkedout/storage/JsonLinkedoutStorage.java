package seedu.linkedout.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.linkedout.commons.core.LogsCenter;
import seedu.linkedout.commons.exceptions.DataConversionException;
import seedu.linkedout.commons.exceptions.IllegalValueException;
import seedu.linkedout.commons.util.FileUtil;
import seedu.linkedout.commons.util.JsonUtil;
import seedu.linkedout.model.ReadOnlyLinkedout;

/**
 * A class to access Linkedout book data stored as a json file on the hard disk.
 */
public class JsonLinkedoutStorage implements LinkedoutStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonLinkedoutStorage.class);

    private Path filePath;

    public JsonLinkedoutStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getLinkedoutFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyLinkedout> readLinkedout() throws DataConversionException {
        return readLinkedout(filePath);
    }

    /**
     * Similar to {@link #readLinkedout()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyLinkedout> readLinkedout(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableLinkedout> jsonLinkedout = JsonUtil.readJsonFile(
                filePath, JsonSerializableLinkedout.class);
        if (!jsonLinkedout.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonLinkedout.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveLinkedout(ReadOnlyLinkedout linkedout) throws IOException {
        saveLinkedout(linkedout, filePath);
    }

    /**
     * Similar to {@link #saveLinkedout(ReadOnlyLinkedout)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveLinkedout(ReadOnlyLinkedout linkedout, Path filePath) throws IOException {
        requireNonNull(linkedout);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableLinkedout(linkedout), filePath);
    }

}
