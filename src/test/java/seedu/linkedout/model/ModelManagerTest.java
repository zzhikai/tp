package seedu.linkedout.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.linkedout.model.Model.PREDICATE_SHOW_ALL_APPLICANTS;
import static seedu.linkedout.testutil.Assert.assertThrows;
import static seedu.linkedout.testutil.TypicalApplicants.ALICE;
import static seedu.linkedout.testutil.TypicalApplicants.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.linkedout.commons.core.GuiSettings;
import seedu.linkedout.model.applicant.NameContainsKeywordsPredicate;
import seedu.linkedout.testutil.LinkedoutBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new Linkedout(), new Linkedout(modelManager.getLinkedout()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setLinkedoutFilePath(Paths.get("linkedout/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setLinkedoutFilePath(Paths.get("new/linkedout/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setLinkedoutFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setLinkedoutFilePath(null));
    }

    @Test
    public void setLinkedoutFilePath_validPath_setsLinkedoutFilePath() {
        Path path = Paths.get("linkedout/book/file/path");
        modelManager.setLinkedoutFilePath(path);
        assertEquals(path, modelManager.getLinkedoutFilePath());
    }

    @Test
    public void hasApplicant_nullApplicant_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasApplicant(null));
    }

    @Test
    public void hasApplicant_applicantNotInLinkedout_returnsFalse() {
        assertFalse(modelManager.hasApplicant(ALICE));
    }

    @Test
    public void hasApplicant_applicantInLinkedout_returnsTrue() {
        modelManager.addApplicant(ALICE);
        assertTrue(modelManager.hasApplicant(ALICE));
    }

    @Test
    public void getFilteredApplicantList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredApplicantList().remove(0));
    }

    @Test
    public void equals() {
        Linkedout linkedout = new LinkedoutBuilder().withApplicant(ALICE).withApplicant(BENSON).build();
        Linkedout differentLinkedout = new Linkedout();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(linkedout, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(linkedout, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different linkedout -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentLinkedout, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredApplicantList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(linkedout, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setLinkedoutFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(linkedout, differentUserPrefs)));
    }
}
