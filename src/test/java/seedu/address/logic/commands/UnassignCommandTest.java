package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAssignments.ALICE_ALPHA;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalProjects.ALPHA;
import static seedu.address.testutil.TypicalProjects.BETA;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.AssignmentId;
import seedu.address.model.person.EmployeeId;
import seedu.address.model.person.Person;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectId;
import seedu.address.testutil.AssignmentBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.ProjectBuilder;

public class UnassignCommandTest {

    @Test
    public void constructor_nullAssignmentId_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new UnassignCommand(null));
    }

    @Test
    public void constructor_nullProjectIdAndEmployeeId_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new UnassignCommand(null, null));
    }

    @Test
    public void execute_unassignAssignmentWithAssignmentIdByModel_addSuccessful() throws Exception {
        UnassignCommandTest.ModelStubAlwaysUnassign modelStub = new UnassignCommandTest.ModelStubAlwaysUnassign();
        Assignment validAssignment = new AssignmentBuilder().build();

        CommandResult commandResult = new UnassignCommand(validAssignment.getAssignmentId()).execute(modelStub);

        assertEquals(String.format(UnassignCommand.MESSAGE_SUCCESS_ASSIGNMENT_ID, validAssignment.getAssignmentId()),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_unassignAssignmentWithProjectIdAndEmployeeIdByModel_addSuccessful() throws Exception {
        UnassignCommandTest.ModelStubAlwaysUnassign modelStub = new UnassignCommandTest.ModelStubAlwaysUnassign();
        Project validProject = new ProjectBuilder().build();
        Person validPerson = new PersonBuilder().build();

        CommandResult commandResult = new UnassignCommand(validProject.getId(),
                validPerson.getEmployeeId()).execute(modelStub);

        assertEquals(String.format(UnassignCommand.MESSAGE_SUCCESS, validPerson.getEmployeeId(), validProject.getId()),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_duplicateUnassignment_throwsCommandException() {
        Assignment validAssignment = new AssignmentBuilder().build();
        UnassignCommand unassignCommand = new UnassignCommand(validAssignment.getAssignmentId());
        UnassignCommandTest.ModelStub modelStub = new UnassignCommandTest.ModelStubWithAssignment(validAssignment);
        assertThrows(CommandException.class,
                UnassignCommand.MESSAGE_ASSIGNMENT_NOT_FOUND, () -> {
                    unassignCommand.execute(modelStub);
                    unassignCommand.execute(modelStub);
                });
    }

    @Test
    public void equals() {
        Assignment alphaAlice = new AssignmentBuilder().withAssignmentId("1").withProject(ALPHA)
                .withPerson(ALICE).build();
        Assignment betaBenson = new AssignmentBuilder().withAssignmentId("2").withProject(BETA)
                .withPerson(BENSON).build();
        UnassignCommand unassignAlphaAliceCommand = new UnassignCommand(alphaAlice.getAssignmentId());
        UnassignCommand unassignBetaBensonCommand = new UnassignCommand(betaBenson.getAssignmentId());

        // same object -> returns true
        assertTrue(unassignAlphaAliceCommand.equals(unassignAlphaAliceCommand));

        // same values -> returns true
        UnassignCommand unassignAlphaAliceCommandCopy = new UnassignCommand(alphaAlice.getAssignmentId());
        assertTrue(unassignAlphaAliceCommand.equals(unassignAlphaAliceCommandCopy));

        // different types -> returns false
        assertFalse(unassignAlphaAliceCommand.equals(1));

        // null -> returns false
        assertFalse(unassignAlphaAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(unassignAlphaAliceCommand.equals(unassignBetaBensonCommand));
    }

    @Test
    public void toStringMethod() {
        UnassignCommand unassignCommand = new UnassignCommand(ALICE_ALPHA.getAssignmentId());
        String expected = UnassignCommand.class.getCanonicalName() + "{assignmentId="
                + ALICE_ALPHA.getAssignmentId().toString() + ", projectId=null"
                + ", employeeId=null}";

        assertEquals(expected, unassignCommand.toString());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addProject(Project project) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookPerson(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookProject(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEmployeeId(EmployeeId employeeId) {
            return true;
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasProject(Project project) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasProjectId(ProjectId projectId) {
            return true;
        }

        @Override
        public void deleteProject(Project target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setProject(Project target, Project editedproject) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAssignment(Assignment assignment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAssignment(Assignment assignment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAssignment(AssignmentId assignmentId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAssignment(ProjectId projectId, EmployeeId employeeId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAssignment(ProjectId projectId, EmployeeId employeeId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAssignment(AssignmentId target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAssignment(Assignment target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Project> getProjectList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Project> getFilteredProjectList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Assignment> getFilteredAssignmentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredProjectList(Predicate<Project> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredAssignmentList(Predicate<Assignment> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single assignment.
     */
    private class ModelStubWithAssignment extends UnassignCommandTest.ModelStub {
        private Assignment assignment;

        ModelStubWithAssignment(Assignment assignment) {
            requireNonNull(assignment);
            this.assignment = assignment;
        }

        @Override
        public boolean hasAssignment(AssignmentId assignmentId) {
            requireNonNull(assignmentId);
            return this.assignment != null && assignment.getAssignmentId().equals(assignmentId);
        }

        @Override
        public boolean hasAssignment(ProjectId projectId, EmployeeId employeeId) {
            requireNonNull(projectId);
            requireNonNull(employeeId);
            return this.assignment != null
                    && assignment.getProject().getId().equals(projectId)
                    && assignment.getPerson().getEmployeeId().equals(employeeId);
        }

        @Override
        public void deleteAssignment(AssignmentId assignmentId) {
            requireNonNull(assignmentId);
            this.assignment = null;
        }

        @Override
        public void deleteAssignment(ProjectId projectId, EmployeeId employeeId) {
            requireNonNull(projectId);
            requireNonNull(employeeId);
            this.assignment = null;
        }
    }

    /**
     * A Model stub that always unassign assignment successfully
     */
    private class ModelStubAlwaysUnassign extends UnassignCommandTest.ModelStub {
        final ArrayList<Assignment> assignmentsAdded = new ArrayList<>();

        @Override
        public boolean hasAssignment(AssignmentId assignmentId) {
            requireNonNull(assignmentId);
            return true;
        }

        @Override
        public boolean hasAssignment(ProjectId projectId, EmployeeId employeeId) {
            requireNonNull(projectId);
            requireNonNull(employeeId);
            return true;
        }

        @Override
        public void deleteAssignment(AssignmentId assignmentId) {
            requireNonNull(assignmentId);
        }

        @Override
        public void deleteAssignment(ProjectId projectId, EmployeeId employeeId) {
            requireNonNull(projectId);
            requireNonNull(employeeId);
        }
    }
}
