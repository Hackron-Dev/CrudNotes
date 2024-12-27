package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.DataBase.*;

import java.util.logging.Logger;

public class NotesController {
    private static final Logger logger = Logger.getLogger(NotesController.class.getName());

    public Button searchButton;
    public TextField searchField;
    public TextField addText;
    public Pagination pagination;
    @FXML
    public TextField addName;

    @FXML
    public ListView<String> listView;

    @FXML
    public ChoiceBox<String> filterBy;

    @FXML
    public TextField getDeleteID;

    @FXML
    public TextField getEditIDField;

    @FXML
    public TextField editNameField;

    @FXML
    public TextField editTextField;

    @FXML
    private Button filterButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button addCommit;

    @FXML
    private Button EditCommitButton;

    // Методы для обработки событий
    @FXML
    public void filterClicked() {
        logger.info("Фильтр применён: " + filterBy.getValue() + " успешно✅");
        updateShowDB();
    }


    @FXML
    private void filterOpenClicked() {
    }

    public String getFilterBy() {
        return filterBy.getValue();
    }

    @FXML
    public void deleteClicked() {
        try {
            DBdelete.deleteEntityById(Integer.parseInt(getDeleteID.getText()));
            updateShowDB();
            logger.info("Удаление ID " + getDeleteID.getText() + " успешно✅");
        } catch (NumberFormatException e) {
            logger.warning("Ошибка при удалении❌");
        }
    }

    @FXML
    public void addClicked() {
        if (addName.getText().length() >= 2 && addName.getText().length() <= 50 && addText.getText().length() <= 255) {
            DBadd.addEntity(addName.getText(), addText.getText());
            logger.info("Добавление заметки: " + addName.getText() + " - " + addText.getText() + " успешно✅");
            updateShowDB();
        } else {
            logger.warning("Условия добавления не соблюдены❌");
        }
    }

    @FXML
    private void editClicked() {
        try {
            if (editNameField.getText().length() >= 3 && editNameField.getText().length() <= 50 && editTextField.getText().length() <= 255) {
                DBupdate.updateEntityById(Integer.parseInt(getEditIDField.getText()), editNameField.getText(), editTextField.getText());
                updateShowDB();
                logger.info("Изменение записи ID " + getEditIDField.getText() + " успешно✅");
            }else {
                logger.warning("Условия добавления не соблюдены❌");
            }


        } catch (NumberFormatException e) {
            logger.warning("Запись не найдена❌");
        }
    }

    @FXML
    private void searchClicked() {
        logger.info("Поиск: " + searchField.getText());
        updateShowDB();
    }

    // Метод инициализации
    @FXML
    public void initialize() {
        logger.info("Контроллер инициализирован.");
        filterBy.getItems().addAll("ID", "TextLength", "FilterByUpdateTime");
        filterBy.setValue("ID");
        updateShowDB();

        pagination.currentPageIndexProperty().addListener((observable, oldValue, newValue) -> {
            paginationClicked();
        });
    }

    @FXML
    public void paginationClicked() {
        logger.info("Pagination: " + pagination.getCurrentPageIndex());
        logger.info("Всего записей: " + DBUtils.getRecordCount());
        updateShowDB();
    }

    public void updateShowDB() {
        org.example.functions.Pagination.updatePagination(pagination);
        DBshow.DBshow(pagination.getCurrentPageIndex(), getFilterBy(), searchField.getText(), listView);
    }
}
