package agh.ics.oop.proj1.view.settings;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class SettingsComponent {

    private VBox vBox;
    private HBox[] hBox;
    private Label[] label;
    private TextField[] textFields;
    private CheckBox checkBox1;
    private CheckBox checkBox2;

    SettingsComponent(ArrayList<String> defaultValues){

        checkBox2 = new CheckBox();
        checkBox2.setPrefWidth(180);
        checkBox1 = new CheckBox();
        checkBox1.setPrefWidth(180);
        vBox = new VBox();
        vBox.setSpacing(5);
        vBox.setPadding(new Insets(15,15,15,15));
        hBox = new HBox[12];
        for(int i = 0; i<12; i++){
            hBox[i] = new HBox();
        }
        vBox.setPrefWidth(330);
        label = new Label[12];
        for(int i= 0; i<12; i++){
            label[i] = new Label();
        }
        label[0].setText("Settings:");
        label[0].setFont(new Font(25));
        label[1].setText("Map parameters: ");
        label[1].setFont(new Font(20));
        label[2].setText("Map width: ");
        label[3].setText("Map height: ");
        label[4].setText("Animals at start: ");
        label[5].setText("Jungleratio: ");
        label[6].setText("Magic evolution: ");
        label[7].setText("Wall: ");
        label[8].setText("Energy properties: ");
        label[8].setFont(new Font(20));
        label[9].setText("Animal energy at start: ");
        label[10].setText("Energy lost each day: ");
        label[11].setText("Plant energy: ");
        for(int i =0 ;i<12; i++) {
            label[i].setPrefWidth(180);
            hBox[i].getChildren().add(label[i]);
        }

        textFields = new TextField[7];
        for(int i =0; i<7; i++){
            textFields[i] = new TextField();
            textFields[i].setPrefWidth(150);
            textFields[i].setPrefHeight(25);
        }
        textFields[0].setText(defaultValues.get(0));
        hBox[2].getChildren().add(textFields[0]);
        textFields[1].setText(defaultValues.get(1));
        hBox[3].getChildren().add(textFields[1]);
        textFields[2].setText(defaultValues.get(2));
        hBox[4].getChildren().add(textFields[2]);
        textFields[3].setText(defaultValues.get(3));
        hBox[5].getChildren().add(textFields[3]);
        checkBox1.setSelected(Boolean.getBoolean(defaultValues.get(4)));
        hBox[6].getChildren().add(checkBox1);
        checkBox2.setSelected(Boolean.getBoolean(defaultValues.get(5)));
        hBox[7].getChildren().add(checkBox2);
        textFields[4].setText(defaultValues.get(6));
        hBox[9].getChildren().add(textFields[4]);
        textFields[5].setText(defaultValues.get(7));
        hBox[10].getChildren().add(textFields[5]);
        textFields[6].setText(defaultValues.get(8));
        hBox[11].getChildren().add(textFields[6]);


        for(int i =0 ;i<12; i++){
            hBox[i].setAlignment(Pos.CENTER);
            vBox.getChildren().add(hBox[i]);
        }

    }

    public ArrayList<String> getSettings(){
        ArrayList<String> arrayList = new ArrayList<>();
        for(int i = 0;i <4; i++){
            arrayList.add(textFields[i].getText());
        }
        arrayList.add(checkBox1.getText());
        arrayList.add(checkBox2.getText());
        for(int i =4; i<7; i++){
            arrayList.add(textFields[i].getText());
        }

        return arrayList;
    }

    public VBox getvBox() {
        return vBox;
    }

    public void disableCheckBox(){
        checkBox2.setDisable(true);
    }

    public void setCheckBox2(boolean val){
        checkBox2.setSelected(val);
    }

    public void setTitle(String title){
        this.label[0].setText(title);
    }


}
