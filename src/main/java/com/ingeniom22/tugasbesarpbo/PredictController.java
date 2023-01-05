package com.ingeniom22.tugasbesarpbo;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PredictController {
    @GetMapping("/predict")
    public String predictForm(Model model) {
        model.addAttribute("request", new DiabetesPredictionRequest());
        return "predict2";
    }

    @PostMapping("/predict")
    public String predictSubmit(@ModelAttribute DiabetesPredictionRequest request, Model model)
            throws FileNotFoundException, IOException {
        model.addAttribute("request", request);

        DiabetesPredictionResult result = new DiabetesPredictionResult();
        result.setPrediction(request);

        model.addAttribute("result", result);
        return "result2";
    }

}