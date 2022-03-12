package seedu.linkedout.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.linkedout.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.linkedout.commons.exceptions.IllegalValueException;
import seedu.linkedout.commons.util.JsonUtil;
import seedu.linkedout.model.Linkedout;
import seedu.linkedout.testutil.TypicalApplicants;

public class JsonSerializableLinkedoutTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableLinkedoutTest");
    private static final Path TYPICAL_APPLICANTS_FILE = TEST_DATA_FOLDER.resolve("typicalApplicantsLinkedout.json");
    private static final Path INVALID_APPLICANT_FILE = TEST_DATA_FOLDER.resolve("invalidApplicantLinkedout.json");
    private static final Path DUPLICATE_APPLICANT_FILE = TEST_DATA_FOLDER.resolve("duplicateApplicantLinkedout.json");

    @Test
    public void toModelType_typicalApplicantsFile_success() throws Exception {
        JsonSerializableLinkedout dataFromFile = JsonUtil.readJsonFile(TYPICAL_APPLICANTS_FILE,
                JsonSerializableLinkedout.class).get();
        Linkedout linkedoutFromFile = dataFromFile.toModelType();
        Linkedout typicalApplicantsLinkedout = TypicalApplicants.getTypicalLinkedout();
        assertEquals(linkedoutFromFile, typicalApplicantsLinkedout);
    }

    @Test
    public void toModelType_invalidApplicantFile_throwsIllegalValueException() throws Exception {
        JsonSerializableLinkedout dataFromFile = JsonUtil.readJsonFile(INVALID_APPLICANT_FILE,
                JsonSerializableLinkedout.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateApplicants_throwsIllegalValueException() throws Exception {
        JsonSerializableLinkedout dataFromFile = JsonUtil.readJsonFile(DUPLICATE_APPLICANT_FILE,
                JsonSerializableLinkedout.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableLinkedout.MESSAGE_DUPLICATE_APPLICANT,
                dataFromFile::toModelType);
    }

}
