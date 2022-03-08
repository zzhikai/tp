package seedu.linkedout.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.linkedout.testutil.TypicalApplicants.getTypicalLinkedout;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.linkedout.commons.core.GuiSettings;
import seedu.linkedout.model.Linkedout;
import seedu.linkedout.model.ReadOnlyLinkedout;
import seedu.linkedout.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonLinkedoutStorage linkedoutStorage = new JsonLinkedoutStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(linkedoutStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void linkedoutReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonLinkedoutStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonLinkedoutStorageTest} class.
         */
        Linkedout original = getTypicalLinkedout();
        storageManager.saveLinkedout(original);
        ReadOnlyLinkedout retrieved = storageManager.readLinkedout().get();
        assertEquals(original, new Linkedout(retrieved));
    }

    @Test
    public void getLinkedoutFilePath() {
        assertNotNull(storageManager.getLinkedoutFilePath());
    }

}
