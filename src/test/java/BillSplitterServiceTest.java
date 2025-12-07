
import org.junit.jupiter.api.Test;
import uz.uzum.model.Dish;
import uz.uzum.model.Person;
import uz.uzum.service.BillSplitterService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BillSplitterServiceTest {

    private final BillSplitterService service = new BillSplitterService();

    @Test
    void testSinglePersonNoShared() {
        Person p = new Person();
        p.setName( "Ali" );
        p.setDishes( List.of( new Dish() {{
            setName( "Burger" );
            setPrice( 10 );
            setShared( false );
        }} ) );

        service.calculateBill( List.of( p ), 0 );

        assertEquals( 10, p.getTotalCost() );
    }

    @Test
    void testMultiplePeopleWithShared() {
        Person p1 = new Person();
        p1.setName( "Ali" );
        p1.setDishes( List.of(
                new Dish() {{
                    setName( "Burger" );
                    setPrice( 10 );
                    setShared( false );
                }},
                new Dish() {{
                    setName( "Fries" );
                    setPrice( 4 );
                    setShared( true );
                }}
        ) );

        Person p2 = new Person();
        p2.setName( "Vali" );
        p2.setDishes( List.of(
                new Dish() {{
                    setName( "Pizza" );
                    setPrice( 15 );
                    setShared( false );
                }},
                new Dish() {{
                    setName( "Fries" );
                    setPrice( 4 );
                    setShared( true );
                }}
        ) );

        List<Person> people = Arrays.asList( p1, p2 );
        service.calculateBill( people, 0 );

        // Shared Fries: total 8 / 2 = 4 each
        assertEquals( 14, p1.getTotalCost() ); // 10 + 4
        assertEquals( 19, p2.getTotalCost() ); // 15 + 4
    }

    @Test
    void testCommissionApplied() {
        Person p = new Person();
        p.setName( "Ali" );
        p.setDishes( List.of( new Dish() {{
            setName( "Burger" );
            setPrice( 20 );
            setShared( false );
        }} ) );

        service.calculateBill( List.of( p ), 10 ); // 10% commission

        assertEquals( 22, p.getTotalCost() ); // 20 + 10% = 22
    }
}
