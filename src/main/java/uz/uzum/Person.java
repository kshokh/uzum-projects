
package uz.uzum;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String name;
    private List<Dish> dishes;
    private double totalCost;
}
