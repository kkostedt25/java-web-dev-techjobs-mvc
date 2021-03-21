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
        if (searchTerm.toLowerCase().equals("all")) {
            jobs = JobData.findAll();
            model.addAttribute("jobs", JobData.findAll() );

        } else if (searchTerm.equals("")){
            jobs = JobData.findAll();
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("jobs", JobData.findAll());
            model.addAttribute("columns", ListController.columnChoices);
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("columns", ListController.columnChoices);
        }
        if (searchType.equals("all")){
           jobs = JobData.findAll();
           jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("jobs", jobs);
            model.addAttribute("columns", ListController.columnChoices);

        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("jobs", jobs);
        }

//           model.addAttribute("jobs", jobs);
//           model.addAttribute("columns", ListController.columnChoices);
            return "search";


    }
}
