package ui.model;

import javax.swing.*;

public class ResultModel extends AbstractListModel<String> {
    String[] result = {""};

    @Override
    public int getSize() {
        return result.length;
    }

    @Override
    public String getElementAt(int i) {
        return result[i];
    }

    public void updateModel(String[] input) {
        result = input;
    }
}
