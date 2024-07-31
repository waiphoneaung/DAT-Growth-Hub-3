package com.g3.elis.util;

import java.util.List;

public class SheetData {
    private String sheetName;
    private List<List<String>> data;

    public SheetData(String sheetName, List<List<String>> data) {
        this.sheetName = sheetName;
        this.data = data;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public List<List<String>> getData() {
        return data;
    }

    public void setData(List<List<String>> data) {
        this.data = data;
    }
}
