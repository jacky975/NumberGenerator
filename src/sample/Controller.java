package sample;

import javafx.beans.binding.Bindings;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.ScrollEvent;

import javax.management.ListenerNotFoundException;
import javax.swing.plaf.basic.BasicSliderUI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

public class Controller implements Initializable{

    public TextArea ranOut;
    public Label output;
    public Slider outputno;
    public Label digit_current;
    public CheckBox repeatable;
    public TextField min, max;

    private int OUTPUT_NUMBER = 1, RANGE = 1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ranOut.setText("Random number appears here!");


        outputno.setMax(100);
        outputno.setMin(1);

        outputno.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                output.textProperty().setValue("Output number: "+String.valueOf(newValue.intValue()));
                OUTPUT_NUMBER = newValue.intValue();
            }
        });

    }

        int minIn = 0, maxIn = 1;
    public void buttonClicked(){

        boolean ERROR_CHECKED;

        ranOut.clear();

        try {
            minIn = Integer.parseInt(this.min.getText());
            maxIn = Integer.parseInt(this.max.getText());

            ERROR_CHECKED = true;
        }catch(Exception e){
            AlertBox alert = new AlertBox();
            alert.display("Please, make sure you are giving a number to me! Or haven't you type the range?", "Input error");
        }

        if (minIn >= maxIn){
            AlertBox alert = new AlertBox();
            alert.display("Hey! The minimum number shouldn't be bigger than the max number! They can't even get equal!", "Input error");
            ERROR_CHECKED = false;
        }else{
         ERROR_CHECKED = true;
        }

        if (ERROR_CHECKED = true){
            if (repeatable.isSelected()) {
                generateNumber();
            }else {
                generateNoRepeat();
            }
        }else{

        }
    }

    public void generateNumber(){
        for(int i = 0; i < OUTPUT_NUMBER; i++){
            ranOut.appendText(String.valueOf(ThreadLocalRandom.current().nextInt(minIn, maxIn + 1))+"\n");
        }
    }
    public void generateNoRepeat(){
        ArrayList<String> generated = new ArrayList<>();
        for (int i = minIn; i<maxIn; i++){
            generated.add(String.valueOf(i));
        }

        Collections.shuffle(generated);
        for(int i = 0; i < OUTPUT_NUMBER; i++){
            ranOut.appendText(generated.get(i)+"\n");
        }
    }

}
