package seedu.linkedout.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.linkedout.model.Linkedout;
import seedu.linkedout.model.ReadOnlyLinkedout;
import seedu.linkedout.model.applicant.Applicant;
import seedu.linkedout.model.applicant.Email;
import seedu.linkedout.model.applicant.Job;
import seedu.linkedout.model.applicant.Name;
import seedu.linkedout.model.applicant.Phone;
import seedu.linkedout.model.applicant.Round;
import seedu.linkedout.model.skill.Skill;

/**
 * Contains utility methods for populating {@code Linkedout} with sample data.
 */
public class SampleDataUtil {
    public static Applicant[] getSampleApplicants() {
        return new Applicant[] {
            new Applicant(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Job("Software Engineer"), new Round("Technical Interview"),
                getSkillSet("Java", "Python")),
            new Applicant(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Job("Social Media Marketer"),
                    new Round("Instagram Check"),
                getSkillSet("Video Editing", "Social Media Marketing")),
            new Applicant(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Job("Middle Manager"), new Round("Behavioural Interview"),
                getSkillSet("Business Development")),
            new Applicant(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Job("Risk Assessment Associate"),
                    new Round("Stock Pitch Assessment"),
                getSkillSet("Accounting", "Equities", "Cryptocurrency")),
            new Applicant(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Job("Editor"), new Round("Test Article Submitted"),
                getSkillSet("Writing", "Editing", "Publishing")),
            new Applicant(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Job("Van Driver"), new Round("Driving Safety Test"),
                getSkillSet("Logistics"))
        };
    }

    public static ReadOnlyLinkedout getSampleLinkedout() {
        Linkedout sampleLo = new Linkedout();
        for (Applicant sampleApplicant : getSampleApplicants()) {
            sampleLo.addApplicant(sampleApplicant);
        }
        return sampleLo;
    }

    /**
     * Returns a skill set containing the list of strings given.
     */
    public static Set<Skill> getSkillSet(String... strings) {
        return Arrays.stream(strings)
                .map(Skill::new)
                .collect(Collectors.toSet());
    }

}
