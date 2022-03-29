package seedu.linkedout.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.linkedout.model.applicant.Applicant;
import seedu.linkedout.model.applicant.Email;
import seedu.linkedout.model.applicant.Flag;
import seedu.linkedout.model.applicant.Job;
import seedu.linkedout.model.applicant.Name;
import seedu.linkedout.model.applicant.Phone;
import seedu.linkedout.model.applicant.Round;
import seedu.linkedout.model.skill.Skill;
import seedu.linkedout.model.util.SampleDataUtil;

/**
 * A utility class to help with building Applicant objects.
 */
public class ApplicantBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_JOB = "Software Engineer";
    public static final String DEFAULT_ROUND = "Resume Sent";
    public static final Boolean DEFAULT_FLAG = false;

    private Name name;
    private Phone phone;
    private Email email;
    private Job job;
    private Round round;
    private Flag flag;
    private Set<Skill> skills;

    /**
     * Creates a {@code ApplicantBuilder} with the default details.
     */
    public ApplicantBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        job = new Job(DEFAULT_JOB);
        round = new Round(DEFAULT_ROUND);
        flag = new Flag(DEFAULT_FLAG);
        skills = new HashSet<>();
    }

    /**
     * Initializes the ApplicantBuilder with the data of {@code applicantToCopy}.
     */
    public ApplicantBuilder(Applicant applicantToCopy) {
        name = applicantToCopy.getName();
        phone = applicantToCopy.getPhone();
        email = applicantToCopy.getEmail();
        job = applicantToCopy.getJob();
        round = applicantToCopy.getRound();
        flag = applicantToCopy.getFlag();
        skills = new HashSet<>(applicantToCopy.getSkills());
    }

    /**
     * Sets the {@code Name} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code skills} into a {@code Set<Skill>} and set it to the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withSkills(String ... skills) {
        this.skills = SampleDataUtil.getSkillSet(skills);
        return this;
    }

    /**
     * Sets the {@code Job} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withJob(String job) {
        this.job = new Job(job);
        return this;
    }

    /**
     * Sets the {@code Round} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withRound(String round) {
        this.round = new Round(round);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Flag} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withFlag(Boolean flag) {
        this.flag = new Flag(flag);
        return this;
    }

    public Applicant build() {
        return new Applicant(name, phone, email, job, round, skills, flag);
    }

}
