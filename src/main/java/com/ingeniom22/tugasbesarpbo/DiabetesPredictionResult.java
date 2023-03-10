package com.ingeniom22.tugasbesarpbo;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.tribuo.Example;
import org.tribuo.Model;
import org.tribuo.Prediction;
import org.tribuo.classification.Label;
import org.tribuo.classification.LabelFactory;
import org.tribuo.impl.ArrayExample;

public class DiabetesPredictionResult {
    private static Model<Label> loadedModel;
    private String label;
    private double proba;

    public void setPrediction(DiabetesPredictionRequest request) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream("diabetes-rf-model.se")))) {
            loadedModel = (Model<Label>) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Label outputPlaceHolder = LabelFactory.UNKNOWN_LABEL;

        Example<Label> example = new ArrayExample<>(
                outputPlaceHolder,
                DiabetesPredictionRequest.featureNames,
                request.getFeatureValues());

        Prediction<Label> prediction = loadedModel.predict(example);
        Label result = prediction.getOutput();

        if (result.getLabel().equals("1")) {
            this.label = "Positive";
        } else {
            this.label = "Negative";
        }
        ;
        this.proba = Math.round(result.getScore() * 100);

    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getProba() {
        return proba;
    }

    public void setProba(double proba) {
        this.proba = proba;
    }

}
