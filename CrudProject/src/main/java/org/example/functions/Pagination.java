package org.example.functions;

import javafx.scene.Scene;
import org.example.DataBase.DBUtils;
import org.example.NotesController;

public class Pagination extends NotesController {
    //Берем количество всех элементов в бд
    //Берем текущую пагинацию ( по умолчанию 0 )
    // Вычисляем С какого элемента по какой мы должны отрисовать

    public static void updatePagination(javafx.scene.control.Pagination pagination){
        int pageCount = (int) Math.ceil(DBUtils.getRecordCount() / 10.0);
        //System.out.println(pageCount);
        pagination.setPageCount(pageCount);

    }

    //getCurrentPagination();

}
