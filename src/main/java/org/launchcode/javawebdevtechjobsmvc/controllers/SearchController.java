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
    @PostMapping("search/results")
 public String displaySearchResults(Model model, @RequestParam String search, @RequestParam String results, @ModelAttribute Job job){


        if (search.toLowerCase().equals("all")) {
            jobs = JobData.findAll();
            jobs.add(job);
            model.addAttribute("jobs", jobs);
        } else if (search.equals("")){
            jobs = JobData.findAll();
            jobs.add(job);
            model.addAttribute("jobs", jobs);
        } else {
            model.addAttribute("columns", ListController.columnChoices);
            //jobs = JobData.findByColumnAndValue(column, value);
        }
//        if (searchTerm.equals("all")) {
//            searchTerm.toUpperCase();
//            results.toUpperCase();
//            jobs.add(job);
//            JobData.findAll();
//        }
//
//        if (searchTerm.equals("")){
//            searchTerm.toUpperCase();
//            results.toUpperCase();
//            jobs.add(job);
//            JobData.findAll();
//        }

//        model.addAttribute("jobs", jobs);
//        model.addAttribute("columns", ListController.columnChoices);
        return "search/results";


    }
}
