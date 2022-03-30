
package seedu.linkedout.ui;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import seedu.linkedout.logic.commands.ClearCommand;


public class ClearAlertBox {

    /**
     * Displays an alert dialog to notify the user linkedout app is cleared
     * @param response
     */
    public void showInformationAlert(String response) {
        final Alert alert = new Alert(Alert.AlertType.INFORMATION, response);
        alert.getDialogPane().getStylesheets().add("view/DarkTheme.css");
        alert.setTitle("Notification");
        alert.setHeaderText("Notification");
        alert.setContentText(ClearCommand.MESSAGE_SUCCESS);
        alert.showAndWait();
    }

    /**
     * Displays an alert dialog to get confirmation from user on clearing the linkedout app
     * @param content
     * @return True if user press yes
     */
    public boolean showConfirmationAlert(String content) {
        final Alert alert = new Alert(Alert.AlertType.CONFIRMATION, content, ButtonType.YES, ButtonType.NO);
        alert.getDialogPane().getStylesheets().add("view/DarkTheme.css");
        alert.setTitle("Clear");
        alert.setHeaderText("Confirmation");
        alert.setContentText(ClearCommand.MESSAGE_CONFIRMATION);
        alert.showAndWait();
        return alert.getResult() == ButtonType.YES;
    }
}
