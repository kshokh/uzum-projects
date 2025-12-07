package uz.uzum.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.uzum.model.Person;
import uz.uzum.service.BillSplitterService;

import java.util.List;

@RestController
@RequestMapping("/api/bill")
@RequiredArgsConstructor
public class BillSplitterController {

    private final BillSplitterService billSplitterService;

    @PostMapping("/split")
    public List<Person> splitBill( @RequestParam double commission,
                                   @RequestBody List<Person> people ) {
        billSplitterService.calculateBill( people, commission );
        return people;
    }
}
