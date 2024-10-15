package seedu.address.model.assignment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.person.Person;
import seedu.address.model.project.Project;

/**
 * Represents a (Project) Assignment in the address book.
 * Guarantees: details are present and not null, field values are validated,
 * immutable.
 */
public class Assignment {
    private final AssignmentId assignmentId;
    private final Project project;
    private final Person person;

    /**
     * Every field must be present and not null.
     */
    public Assignment(AssignmentId assignmentId, Project project, Person person) {
        requireAllNonNull(project, person);
        this.assignmentId = assignmentId;
        this.project = project;
        this.person = person;
    }

    public AssignmentId getAssignmentId() {
        return assignmentId;
    }

    public Project getProject() {
        return project;
    }

    public Person getPerson() {
        return person;
    }

    /**
     * Returns true if both {@code Project#equals(Project)} and
     * {@code Person#equals(Person)} returns true.
     * This defines a weaker notion of equality between two assignments.
     */
    public boolean isSameAssignment(Assignment otherAssignment) {
        if (otherAssignment == this) {
            return true;
        }

        Person otherPerson = otherAssignment.getPerson();
        boolean isSamePerson = otherPerson != null && person.equals(otherPerson);

        Project otherProject = otherAssignment.getProject();
        boolean isSameProject = otherProject != null && project.equals(otherProject);

        return isSamePerson && isSameProject;
    }

    /**
     * Returns true if both assignments have the same identity and data fields.
     * This defines a stronger notion of equality between two assignments.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Assignment)) {
            return false;
        }

        Assignment otherAssignment = (Assignment) other;
        return assignmentId.equals(otherAssignment.assignmentId)
                && project.equals(otherAssignment.project)
                && person.equals(otherAssignment.person);
    }
}
