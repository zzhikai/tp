package seedu.linkedout.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.linkedout.logic.commands.EditCommand.EditApplicantDescriptor;
import seedu.linkedout.model.applicant.Applicant;
import seedu.linkedout.model.applicant.Email;
import seedu.linkedout.model.applicant.Job;
import seedu.linkedout.model.applicant.Name;
import seedu.linkedout.model.applicant.Phone;
import seedu.linkedout.model.applicant.Round;
import seedu.linkedout.model.skill.Skill;

/**
 * A utility class to help with building EditApplicantDescriptor objects.
 */
public class EditApplicantDescriptorBuilder {

    private EditApplicantDescriptor descriptor;

    public EditApplicantDescriptorBuilder() {
        descriptor = new EditApplicantDescriptor();
    }

    public EditApplicantDescriptorBuilder(EditApplicantDescriptor descriptor) {
        this.descriptor = new EditApplicantDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditApplicantDescriptor} with fields containing {@code applicant}'s details
     */
    public EditApplicantDescriptorBuilder(Applicant applicant) {
        descriptor = new EditApplicantDescriptor();
        descriptor.setName(applicant.getName());
        descriptor.setPhone(applicant.getPhone());
        descriptor.setEmail(applicant.getEmail());
        descriptor.setJob(applicant.getJob());
        descriptor.setRound(applicant.getRound());
        descriptor.setSkills(applicant.getSkills());
    }

    /**
     * Sets the {@code Name} of the {@code EditApplicantDescriptor} that we are building.
     */
    public EditApplicantDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditApplicantDescriptor} that we are building.
     */
    public EditApplicantDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditApplicantDescriptor} that we are building.
     */
    public EditApplicantDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Job} of the {@code EditApplicantDescriptor} that we are building.
     */
    public EditApplicantDescriptorBuilder withJob(String job) {
        descriptor.setJob(new Job(job));
        return this;
    }

    /**
     * Sets the {@code Round} of the {@code EditApplicantDescriptor} that we are building.
     */
    public EditApplicantDescriptorBuilder withRound(String round) {
        descriptor.setRound(new Round(round));
        return this;
    }

    /**
     * Parses the {@code skills} into a {@code Set<Skill>} and set it to the {@code EditApplicantDescriptor}
     * that we are building.
     */
    public EditApplicantDescriptorBuilder withSkills(String... skills) {
        Set<Skill> skillSet = Stream.of(skills).map(Skill::new).collect(Collectors.toSet());
        descriptor.setSkills(skillSet);
        return this;
    }

    public EditApplicantDescriptor build() {
        return descriptor;
    }
}
