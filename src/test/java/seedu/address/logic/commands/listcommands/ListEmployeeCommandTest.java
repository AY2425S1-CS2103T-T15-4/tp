package seedu.address.logic.commands.listcommands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalEmployees.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.ui.DisplayType;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListPersonsCommand.
 */
public class ListEmployeeCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        CommandResult expectedCommandResult = new CommandResult(ListMembersCommand.MESSAGE_SUCCESS,
                DisplayType.PERSON_LIST, false, false);
        assertCommandSuccess(new ListMembersCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        CommandResult expectedCommandResult = new CommandResult(ListMembersCommand.MESSAGE_SUCCESS,
                DisplayType.PERSON_LIST, false, false);
        assertCommandSuccess(new ListMembersCommand(), model, expectedCommandResult, expectedModel);
    }
}
