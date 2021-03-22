package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.hibernate.validator.constraints.time.DurationMax;
import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {
    private static ArrayList <Job> jobs = new ArrayList<>();
    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    @PostMapping("results")
 public String displaySearchResults(Model model, @RequestParam String searchTerm, @RequestParam String searchType){

        ArrayList<Job> jobs = new ArrayList<>();
        model.addAttribute("columns", ListController.columnChoices);
        if (searchTerm.toLowerCase().equals("all") || searchTerm.equals("")){
            jobs = JobData.findAll();
            model.addAttribute("jobs", jobs);

        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("jobs", jobs);

        }
            return "search";


    }
}
