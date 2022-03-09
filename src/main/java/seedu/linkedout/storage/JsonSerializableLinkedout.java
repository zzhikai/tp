package seedu.linkedout.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.linkedout.commons.exceptions.IllegalValueException;
import seedu.linkedout.model.Linkedout;
import seedu.linkedout.model.ReadOnlyLinkedout;
import seedu.linkedout.model.applicant.Applicant;

/**
 * An Immutable Linkedout app that is serializable to JSON format.
 */
@JsonRootName(value = "linkedout")
class JsonSerializableLinkedout {

    public static final String MESSAGE_DUPLICATE_APPLICANT = "Applicant list contains duplicate applicant(s).";

    private final List<JsonAdaptedApplicant> applicants = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableLinkedout} with the given applicants.
     */
    @JsonCreator
    public JsonSerializableLinkedout(@JsonProperty("applicants") List<JsonAdaptedApplicant> applicants) {
        this.applicants.addAll(applicants);
    }

    /**
     * Converts a given {@code ReadOnlyLinkedout} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableLinkedout}.
     */
    public JsonSerializableLinkedout(ReadOnlyLinkedout source) {
        applicants.addAll(source.getApplicantList().stream()
                .map(JsonAdaptedApplicant::new).collect(Collectors.toList()));
    }

    /**
     * Converts this linkedout app into the model's {@code Linkedout} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Linkedout toModelType() throws IllegalValueException {
        Linkedout linkedout = new Linkedout();
        for (JsonAdaptedApplicant jsonAdaptedApplicant : applicants) {
            Applicant applicant = jsonAdaptedApplicant.toModelType();
            if (linkedout.hasApplicant(applicant)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_APPLICANT);
            }
            linkedout.addApplicant(applicant);
        }
        return linkedout;
    }

}
