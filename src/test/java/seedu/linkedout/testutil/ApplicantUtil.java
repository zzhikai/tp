package seedu.linkedout.testutil;

import static seedu.linkedout.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.linkedout.logic.parser.CliSyntax.PREFIX_JOB;
import static seedu.linkedout.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.linkedout.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.linkedout.logic.parser.CliSyntax.PREFIX_ROUND;
import static seedu.linkedout.logic.parser.CliSyntax.PREFIX_SKILL;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import seedu.linkedout.logic.commands.AddCommand;
import seedu.linkedout.logic.commands.EditCommand.EditApplicantDescriptor;
import seedu.linkedout.model.applicant.Applicant;
import seedu.linkedout.model.skill.Skill;


/**
 * A utility class for Applicant.
 */
public class ApplicantUtil {

    /**
     * Returns an add command string for adding the {@code applicant}.
     */
    public static String getAddCommand(Applicant applicant) {
        return AddCommand.COMMAND_WORD + " " + getApplicantDetails(applicant);
    }

    /**
     * Returns the part of command string for the given {@code applicant}'s details.
     */
    public static String getApplicantDetails(Applicant applicant) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + applicant.getName().fullName + " ");
        sb.append(PREFIX_PHONE + applicant.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + applicant.getEmail().value + " ");
        sb.append(PREFIX_JOB + applicant.getJob().value + " ");
        sb.append(PREFIX_ROUND + applicant.getRound().value + " ");
        applicant.getSkills().stream().forEach(
            s -> sb.append(PREFIX_SKILL + s.skillName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditApplicantDescriptor}'s details.
     */
    public static String getEditApplicantDescriptorDetails(EditApplicantDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getJob().ifPresent(job -> sb.append(PREFIX_JOB).append(job.value).append(" "));
        descriptor.getRound().ifPresent(round -> sb.append(PREFIX_ROUND).append(round.value).append(" "));
        if (descriptor.getSkills().isPresent()) {
            Set<Skill> skills = descriptor.getSkills().get();
            if (skills.isEmpty()) {
                sb.append(PREFIX_SKILL);
            } else {
                skills.forEach(s -> sb.append(PREFIX_SKILL).append(s.skillName).append(" "));
            }
        }
        return sb.toString();
    }

    /**
     * Returns a new set of skills {@code keywords} to be added for a given applicant.
     */
    public static Set<Skill> getApplicantNewSkills(String[] keywords) {
        Skill[] skillArray = new Skill[keywords.length];
        for (int i = 0; i < keywords.length; i++) {
            skillArray[i] = new Skill(keywords[i]);
        }
        return new HashSet<Skill>(Arrays.asList(skillArray));
    }
}
