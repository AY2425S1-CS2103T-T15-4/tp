package seedu.address.model.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAssignments.ALICE_ALPHA;
import static seedu.address.testutil.TypicalAssignments.BENSON_BETA;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalProjects.BETA;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.assignment.exceptions.AssignmentNotFoundException;
import seedu.address.model.assignment.exceptions.DuplicateAssignmentException;
import seedu.address.testutil.AssignmentBuilder;

public class UniqueAssignmentListTest {

    private final UniqueAssignmentList uniqueAssignmentList = new UniqueAssignmentList();

    @Test
    public void contains_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssignmentList.contains(null));
    }

    @Test
    public void contains_assignmentNotInList_returnsFalse() {
        assertFalse(uniqueAssignmentList.contains(ALICE_ALPHA));
    }

    @Test
    public void contains_assignmentInList_returnsTrue() {
        uniqueAssignmentList.add(ALICE_ALPHA);
        assertTrue(uniqueAssignmentList.contains(ALICE_ALPHA));
    }

    @Test
    public void contains_assignmentWithDifferentProjectFieldInList_returnsFalse() {
        uniqueAssignmentList.add(ALICE_ALPHA);
        Assignment editedAssignment = new AssignmentBuilder(ALICE_ALPHA).withProject(BETA)
                .build();
        assertFalse(uniqueAssignmentList.contains(editedAssignment));
    }

    @Test
    public void contains_assignmentWithDifferentPersonFieldInList_returnsFalse() {
        uniqueAssignmentList.add(ALICE_ALPHA);
        Assignment editedAssignment = new AssignmentBuilder(ALICE_ALPHA)
                .withPerson(BENSON)
                .build();
        assertFalse(uniqueAssignmentList.contains(editedAssignment));
    }

    @Test
    public void add_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssignmentList.add(null));
    }

    @Test
    public void add_duplicateAssignment_throwsDuplicatePersonException() {
        uniqueAssignmentList.add(ALICE_ALPHA);
        assertThrows(DuplicateAssignmentException.class, () -> uniqueAssignmentList.add(ALICE_ALPHA));
    }

    @Test
    public void setAssignment_targetAssignmentNotInList_throwsAssignmentNotFoundException() {
        assertThrows(AssignmentNotFoundException.class, () -> uniqueAssignmentList.setAssignment(
                ALICE_ALPHA, ALICE_ALPHA));
    }

    @Test
    public void setAssignment_editedAssignmentIsSameAssignment_success() {
        uniqueAssignmentList.add(ALICE_ALPHA);
        uniqueAssignmentList.setAssignment(ALICE_ALPHA, ALICE_ALPHA);
        UniqueAssignmentList expectedUniqueAssignmentList = new UniqueAssignmentList();
        expectedUniqueAssignmentList.add(ALICE_ALPHA);
        assertEquals(expectedUniqueAssignmentList, uniqueAssignmentList);
    }

    @Test
    public void setAssignment_editedAssignmentHasDifferentIdentity_success() {
        uniqueAssignmentList.add(ALICE_ALPHA);
        uniqueAssignmentList.setAssignment(ALICE_ALPHA, BENSON_BETA);
        UniqueAssignmentList expectedUniqueAssignmentList = new UniqueAssignmentList();
        expectedUniqueAssignmentList.add(BENSON_BETA);
        assertEquals(expectedUniqueAssignmentList, uniqueAssignmentList);
    }

    @Test
    public void setAssignment_editedAssignmentHasNonUniqueIdentity_throwsDuplicateAssignmentException() {
        uniqueAssignmentList.add(ALICE_ALPHA);
        uniqueAssignmentList.add(BENSON_BETA);
        assertThrows(DuplicateAssignmentException.class, () -> uniqueAssignmentList.setAssignment(
                ALICE_ALPHA, BENSON_BETA));
    }

    @Test
    public void remove_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssignmentList.remove(null));
    }

    @Test
    public void remove_assignmentDoesNotExist_throwsAssignmentNotFoundException() {
        assertThrows(AssignmentNotFoundException.class, () -> uniqueAssignmentList.remove(ALICE_ALPHA));
    }

    @Test
    public void remove_existingAssignment_removesAssignment() {
        uniqueAssignmentList.add(ALICE_ALPHA);
        uniqueAssignmentList.remove(ALICE_ALPHA);
        UniqueAssignmentList expectedUniqueAssignmentList = new UniqueAssignmentList();
        assertEquals(expectedUniqueAssignmentList, uniqueAssignmentList);
    }

    @Test
    public void setAssignments_nullUniqueAssignmentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssignmentList.setAssignments(
                (UniqueAssignmentList) null));
    }

    @Test
    public void setAssignments_uniqueAssignmentList_replacesOwnListWithProvidedUniqueAssignmentList() {
        uniqueAssignmentList.add(ALICE_ALPHA);
        UniqueAssignmentList expectedUniqueAssignmentList = new UniqueAssignmentList();
        expectedUniqueAssignmentList.add(BENSON_BETA);
        uniqueAssignmentList.setAssignments(expectedUniqueAssignmentList);
        assertEquals(expectedUniqueAssignmentList, uniqueAssignmentList);
    }

    @Test
    public void setAssignments_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssignmentList.setAssignments((List<Assignment>) null));
    }

    @Test
    public void setAssignments_list_replacesOwnListWithProvidedList() {
        uniqueAssignmentList.add(ALICE_ALPHA);
        List<Assignment> assignmentList = Collections.singletonList(BENSON_BETA);
        uniqueAssignmentList.setAssignments(assignmentList);
        UniqueAssignmentList expectedUniqueAssignmentList = new UniqueAssignmentList();
        expectedUniqueAssignmentList.add(BENSON_BETA);
        assertEquals(expectedUniqueAssignmentList, uniqueAssignmentList);
    }

    @Test
    public void setAssignments_listWithDuplicateAssignments_throwsDuplicatePersonException() {
        List<Assignment> listWithDuplicatePersons = Arrays.asList(ALICE_ALPHA, ALICE_ALPHA);
        assertThrows(DuplicateAssignmentException.class, () -> uniqueAssignmentList.setAssignments(
                listWithDuplicatePersons));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> uniqueAssignmentList
                .asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueAssignmentList.asUnmodifiableObservableList().toString(), uniqueAssignmentList.toString());
    }
}
