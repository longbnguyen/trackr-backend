package de.techdev.trackr.domain.employee.expenses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Moritz Schulze
 */
@Controller
@RequestMapping("/travelExpenseReports")
public class TravelExpenseReportController {

    @Autowired
    private TravelExpenseReportService travelExpenseReportService;

    @RequestMapping(value = "/{id}/submit", method = RequestMethod.PUT)
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void submit(@PathVariable("id") TravelExpenseReport travelExpenseReport) {
        travelExpenseReportService.submit(travelExpenseReport);
    }

    @RequestMapping(value = "/{id}/approve", method = RequestMethod.PUT)
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void approve(@PathVariable("id") TravelExpenseReport travelExpenseReport) {
        travelExpenseReportService.accept(travelExpenseReport);
    }

    @RequestMapping(value = "/{id}/reject", method = RequestMethod.PUT)
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void reject(@PathVariable("id") TravelExpenseReport travelExpenseReport) {
        travelExpenseReportService.reject(travelExpenseReport);
    }

}
