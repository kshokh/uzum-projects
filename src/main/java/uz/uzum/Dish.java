package uz.uzum;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dish {
    private String name;
    private double price;
    private boolean shared;
}
