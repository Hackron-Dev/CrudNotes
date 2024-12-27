import javafx.scene.control.*;
import org.example.NotesController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;


import static org.junit.jupiter.api.Assertions.*;


public class NotesControllerTest extends ApplicationTest {

    private NotesController controller;

    @BeforeEach
    void setUp() {
        controller = new NotesController();
        controller.filterBy = new ChoiceBox<>();
        controller.listView = new ListView<>();
        controller.searchField = new TextField();
        controller.addName = new TextField();
        controller.addText = new TextField();
        controller.pagination = new Pagination();
        controller.getDeleteID = new TextField();
        controller.getEditIDField = new TextField();
        controller.editNameField = new TextField();
        controller.editTextField = new TextField();

    }

    @Test
    void testInitialize() {
        controller.initialize();
        assertEquals("ID", controller.getFilterBy());
        assertFalse(controller.filterBy.getItems().isEmpty());
    }

    @Test
    void testAddClicked_ValidInput() {
        controller.initialize();
        controller.addName.setText("Valid Name");
        controller.addText.setText("Valid Text");
        controller.addClicked();
        // Проверить, вызывается ли обновление данных или логика добавления.
    }

    @Test
    void testAddClicked_InvalidInput() {
        controller.initialize();
        controller.addName.setText("");
        controller.addText.setText("Text");
        controller.addClicked();
        // Проверить, что данные не добавляются при нарушении условий.
    }

    @Test
    void testDeleteClicked_ValidInput() {
        controller.initialize();
        controller.getDeleteID.setText("1");
        controller.deleteClicked();
        // Можете использовать mock-объект для проверки вызова DBdelete.deleteEntityById
    }

    @Test
    void testDeleteClicked_InvalidInput() {
        controller.initialize();
        controller.getDeleteID.setText("invalid");
        controller.deleteClicked();
        // Проверить, что исключение обработано корректно.
    }

    @Test
    void testPaginationClicked() {
        controller.initialize();
        controller.pagination.setCurrentPageIndex(2);
        controller.paginationClicked();
        // Убедитесь, что updateShowDB вызывается корректно.
    }

    @Test
    void testFilterClicked() {
        controller.initialize();
        controller.filterBy.getItems().add("ID");
        controller.filterBy.setValue("ID");
        controller.filterClicked();
        // Проверить, что updateShowDB вызывается и filterBy возвращает корректное значение.
    }
}
