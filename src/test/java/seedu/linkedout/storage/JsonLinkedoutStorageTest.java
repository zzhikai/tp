package seedu.linkedout.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.linkedout.testutil.Assert.assertThrows;
import static seedu.linkedout.testutil.TypicalApplicants.ALICE;
import static seedu.linkedout.testutil.TypicalApplicants.HOON;
import static seedu.linkedout.testutil.TypicalApplicants.IDA;
import static seedu.linkedout.testutil.TypicalApplicants.getTypicalLinkedout;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.linkedout.commons.exceptions.DataConversionException;
import seedu.linkedout.model.Linkedout;
import seedu.linkedout.model.ReadOnlyLinkedout;

public class JsonLinkedoutStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonLinkedoutStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readLinkedout_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readLinkedout(null));
    }

    private java.util.Optional<ReadOnlyLinkedout> readLinkedout(String filePath) throws Exception {
        return new JsonLinkedoutStorage(Paths.get(filePath)).readLinkedout(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readLinkedout("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readLinkedout("notJsonFormatLinkedout.json"));
    }

    @Test
    public void readLinkedout_invalidApplicantLinkedout_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readLinkedout("invalidApplicantLinkedout.json"));
    }

    @Test
    public void readLinkedout_invalidAndValidApplicantLinkedout_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readLinkedout("invalidAndValidApplicantLinkedout.json"));
    }

    @Test
    public void readAndSaveLinkedout_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempLinkedout.json");
        Linkedout original = getTypicalLinkedout();
        JsonLinkedoutStorage jsonLinkedoutStorage = new JsonLinkedoutStorage(filePath);

        // Save in new file and read back
        jsonLinkedoutStorage.saveLinkedout(original, filePath);
        ReadOnlyLinkedout readBack = jsonLinkedoutStorage.readLinkedout(filePath).get();
        assertEquals(original, new Linkedout(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addApplicant(HOON);
        original.removeApplicant(ALICE);
        jsonLinkedoutStorage.saveLinkedout(original, filePath);
        readBack = jsonLinkedoutStorage.readLinkedout(filePath).get();
        assertEquals(original, new Linkedout(readBack));

        // Save and read without specifying file path
        original.addApplicant(IDA);
        jsonLinkedoutStorage.saveLinkedout(original); // file path not specified
        readBack = jsonLinkedoutStorage.readLinkedout().get(); // file path not specified
        assertEquals(original, new Linkedout(readBack));

    }

    @Test
    public void saveLinkedout_nullLinkedout_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveLinkedout(null, "SomeFile.json"));
    }

    /**
     * Saves {@code linkedout} at the specified {@code filePath}.
     */
    private void saveLinkedout(ReadOnlyLinkedout linkedout, String filePath) {
        try {
            new JsonLinkedoutStorage(Paths.get(filePath))
                    .saveLinkedout(linkedout, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveLinkedout_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveLinkedout(new Linkedout(), null));
    }
}
