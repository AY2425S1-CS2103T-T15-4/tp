package seedu.address.testutil;

import seedu.address.logic.commands.AddProjectCommand;
import seedu.address.model.project.Project;

import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT_NAME;

/**
 * A utility class for Person.
 */
public class ProjectUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddProjectCommand(Project project) {
        return AddProjectCommand.COMMAND_WORD + " " + getProjectDetails(project);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getProjectDetails(Project project) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_PROJECT_ID + project.getId().fullId + " ");
        sb.append(PREFIX_PROJECT_NAME + project.getName().fullName + " ");
        return sb.toString();
    }
}
