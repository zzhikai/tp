package seedu.linkedout.testutil;

import static seedu.linkedout.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.linkedout.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.linkedout.logic.commands.CommandTestUtil.VALID_JOB_AMY;
import static seedu.linkedout.logic.commands.CommandTestUtil.VALID_JOB_BOB;
import static seedu.linkedout.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.linkedout.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.linkedout.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.linkedout.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.linkedout.logic.commands.CommandTestUtil.VALID_ROUND_AMY;
import static seedu.linkedout.logic.commands.CommandTestUtil.VALID_ROUND_BOB;
import static seedu.linkedout.logic.commands.CommandTestUtil.VALID_SKILL_MARKETING;
import static seedu.linkedout.logic.commands.CommandTestUtil.VALID_SKILL_PYTHON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.linkedout.model.Linkedout;
import seedu.linkedout.model.applicant.Applicant;

/**
 * A utility class containing a list of {@code Applicant} objects to be used in tests.
 */
public class TypicalApplicants {

    public static final Applicant ALICE = new ApplicantBuilder().withName("Alice Pauline")
            .withEmail("alice@example.com").withPhone("94351253")
            .withJob("Frontend Software Engineer").withRound("Resume Sent").withFlag(false)
            .withSkills("Java", "Python").build();
    public static final Applicant BENSON = new ApplicantBuilder().withName("Benson Meier")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withJob("Video Editor").withRound("Practical Interview").withFlag(false)
            .withSkills("Photography", "Videography").build();
    public static final Applicant CARL = new ApplicantBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withJob("Developer").withRound("Interviewed").build();
    public static final Applicant DANIEL = new ApplicantBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withJob("UI and UX Developer")
            .withRound("Recruiting").withFlag(false)
            .withSkills("Marksmanship").build();
    public static final Applicant ELLE = new ApplicantBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withJob("Engineer").withRound("Classical Mechanics Test")
            .withFlag(false).build();
    public static final Applicant FIONA = new ApplicantBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withJob("Developer").withRound("Job Offer Rejected")
            .withFlag(false).build();
    public static final Applicant GEORGE = new ApplicantBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withJob("Software Developer").withRound("Resume Reviewed")
            .withFlag(false).build();

    // Manually added
    public static final Applicant HOON = new ApplicantBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withJob("Backend Software Engineer")
            .withRound("Job Offer Accepted")
            .withFlag(false).build();
    public static final Applicant IDA = new ApplicantBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withJob("Blockchain Engineer").withRound("Finalising")
            .withFlag(false).build();

    // Manually added - Applicant's details found in {@code CommandTestUtil}
    public static final Applicant AMY = new ApplicantBuilder().withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY)
            .withJob(VALID_JOB_AMY).withRound(VALID_ROUND_AMY).withSkills(VALID_SKILL_PYTHON)
            .withFlag(false).build();
    public static final Applicant BOB = new ApplicantBuilder().withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withJob(VALID_JOB_BOB)
            .withRound(VALID_ROUND_BOB).withSkills(VALID_SKILL_PYTHON, VALID_SKILL_MARKETING)
            .withFlag(false).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalApplicants() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical applicants.
     */
    public static Linkedout getTypicalLinkedout() {
        Linkedout l = new Linkedout();
        for (Applicant applicant : getTypicalApplicants()) {
            l.addApplicant(applicant);
        }
        return l;
    }

    public static List<Applicant> getTypicalApplicants() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
