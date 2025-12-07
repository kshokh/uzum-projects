package uz.uzum.service;

import org.springframework.stereotype.Service;
import uz.uzum.model.Dish;
import uz.uzum.model.Person;

import java.util.List;

@Service
public class BillSplitterService {

    public void calculateBill( List<Person> people, double commissionPercentage ) {

        double sharedTotal = people.stream()
                .flatMap( p -> p.getDishes().stream() )
                .filter( Dish::isShared )
                .mapToDouble( Dish::getPrice )
                .sum();

        double sharedPerPerson = sharedTotal / people.size();

        for ( Person person : people ) {
            double total = person.getDishes().stream()
                    .filter( d -> !d.isShared() )
                    .mapToDouble( Dish::getPrice )
                    .sum();

            total += sharedPerPerson;
            total += total * ( commissionPercentage / 100 );

            person.setTotalCost( total );
        }
    }
}
