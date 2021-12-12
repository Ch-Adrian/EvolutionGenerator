package agh.ics.oop.proj1.model.settings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

public class SettingsModel {

    private final ArrayList<String> defaultParam;
    private ArrayList<String> userParamMap1;
    private ArrayList<String> userParamMap2;

    public SettingsModel() {
        defaultParam = new ArrayList<>();

        defaultParam.add("50");
        defaultParam.add("50");
        defaultParam.add("10");
        defaultParam.add("0.5");
        defaultParam.add("false");
        defaultParam.add("false");
        defaultParam.add("20");
        defaultParam.add("1");
        defaultParam.add("5");
    }

    public ArrayList<String> getDefaultParam() {
        return defaultParam;
    }

    public void setUserParamMap1(ArrayList<String> userParam) {
        this.userParamMap1 = userParam;
    }

    public void setUserParamMap2(ArrayList<String> userParam) {
        this.userParamMap2 = userParam;
    }


}
