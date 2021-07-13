package Controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class MenuHandler
{
    @FXML
    void menusHandler(MouseEvent event)
    {
        Node source = (Node)event.getSource() ;
        Integer colIndex = GridPane.getColumnIndex(source);

        if (colIndex == null)
            colIndex = 0;

        switch (colIndex)
        {
            case 0 -> System.out.println(0);
            case 1 -> System.out.println("one");
            case 2 -> System.out.println("two");
            case 3 -> System.out.println("three");
            case 4 -> System.out.println("four");

        }

    }

}
