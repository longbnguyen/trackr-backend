package de.techdev.trackr.domain.employee.expenses.support;

import de.techdev.trackr.domain.employee.expenses.TravelExpenseType;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * @author Moritz Schulze
 */
@Controller
@RequestMapping("/travelExpenses")
public class TravelExpenseTypeController {

    @ResponseBody
    @RequestMapping(value = "types", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TravelExpenseType> types() {
        return asList(TravelExpenseType.values());
    }
}
