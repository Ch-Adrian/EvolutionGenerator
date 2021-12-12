package agh.ics.oop.proj1.controller;

import agh.ics.oop.proj1.view.menu.MenuView;
import javafx.scene.layout.VBox;

import java.awt.*;

public class MenuController {

    private MenuView menuView;

    public static MenuController createController(){
        return new MenuController();
    }

    public MenuController(){
        menuView = new MenuView(this);
    }

    public VBox getMenuView() {
        return menuView.getvBox();
    }

    public void buttonOneSimulation(){
        App.setSettings(false);
    }

    public void buttonTwoSimulations(){
        App.setSettings(true);
    }
}
