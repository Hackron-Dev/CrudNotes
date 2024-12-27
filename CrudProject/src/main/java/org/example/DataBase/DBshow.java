package org.example.DataBase;

import javafx.scene.control.ListView;
import org.example.NotesController;

import java.util.Arrays;

import static org.example.functions.Filters.*;

public class DBshow extends NotesController{
    //Берем значение из фильтра

    public static void DBshow(int CurPagination, String getFilter, String search, ListView<String> listView){
        listView.getItems().clear();
        if (getFilter.equals("ID")) {
            //Filters.ID(Search);
            String[] DBList = FilterByID(search);
            //System.out.println(Arrays.toString(DBList));
            //System.out.println("El count: " + DBList.length);

            for (int i = CurPagination * 10; i <= CurPagination * 10 + 9 && i < DBList.length; i++){
                listView.getItems().add(DBList[i]);
            }



        } else if (getFilter.equals("TextLength")) {
            String[] DBList = FilterByTextLength(search);
            //System.out.println(Arrays.toString(DBList));
            //System.out.println("El count: " + DBList.length);

            for (int i = CurPagination * 10; i <= CurPagination * 10 + 9 && i < DBList.length; i++){
                listView.getItems().add(DBList[i]);
            }
        } else if (getFilter.equals("FilterByUpdateTime")) {
            String[] DBList = FilterByUpdateTime(search);
            //System.out.println(Arrays.toString(DBList));
            //System.out.println("El count: " + DBList.length);

            for (int i = CurPagination * 10; i <= CurPagination * 10 + 9 && i < DBList.length; i++){
                listView.getItems().add(DBList[i]);
            }
        }
    }
}
