package seedu.linkedout.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.linkedout.model.applicant.Applicant;

/**
 * An UI component that displays information of a {@code Applicant}.
 */
public class ApplicantCard extends UiPart<Region> {

    private static final String FXML = "ApplicantListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Applicant applicant;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label round;
    @FXML
    private Label job;
    @FXML
    private Label email;
    @FXML
    private Label phoneTitle;
    @FXML
    private Label jobTitle;
    @FXML
    private Label skillsTitle;
    @FXML
    private Label emailTitle;
    @FXML
    private Label roundTitle;
    @FXML
    private FlowPane skills;
    @FXML
    private ImageView flagIcon;

    /**
     * Creates a {@code ApplicantCode} with the given {@code Applicant} and index to display.
     */
    public ApplicantCard(Applicant applicant, int displayedIndex) {
        super(FXML);
        this.applicant = applicant;
        id.setText(displayedIndex + ". ");
        name.setText(applicant.getName().fullName);
        phoneTitle.setText("Phone: ");
        phone.setText(applicant.getPhone().value);
        jobTitle.setText("Job Applied: ");
        job.setText(applicant.getJob().value);
        round.setText(applicant.getRound().value);
        email.setText(applicant.getEmail().value);
        skillsTitle.setText("Skills: ");
        roundTitle.setText("Round: ");
        emailTitle.setText("Email: ");

        if (applicant.getFlag().value) {
            flagIcon.setVisible(true);
        } else {
            flagIcon.setVisible(false);
        }
        applicant.getSkills().stream()
                .sorted(Comparator.comparing(skill -> skill.skillName))
                .forEach(skill -> skills.getChildren().add(new Label(skill.skillName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ApplicantCard)) {
            return false;
        }

        // state check
        ApplicantCard card = (ApplicantCard) other;
        return id.getText().equals(card.id.getText())
                && applicant.equals(card.applicant);
    }
}
