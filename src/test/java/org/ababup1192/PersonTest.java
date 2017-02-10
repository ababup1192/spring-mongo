package org.ababup1192;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PersonTest {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonService personService;

    private static final String P_NAME1 = "Mike";
    private static final String P_NAME2 = "Alice";
    private static final String P_NAME3 = "John";

    private static final String I_NAME1 = "Ice";
    private static final String I_NAME2 = "Chocolate";
    private static final String I_NAME3 = "Potato";

    private static final Date DATE1 = Date.from(LocalDate.of(2016, 4, 1).atStartOfDay(ZoneId.systemDefault()).toInstant());
    private static final Date DATE2 = Date.from(LocalDate.of(2016, 6, 1).atStartOfDay(ZoneId.systemDefault()).toInstant());
    private static final Date DATE3 = Date.from(LocalDate.of(2016, 10, 1).atStartOfDay(ZoneId.systemDefault()).toInstant());

    private static final LocalDate SEARCH_FROM_DATE = LocalDate.of(2016, 5, 1);
    private static final LocalDate SEARCH_TO_DATE = LocalDate.of(2016, 11, 1);

    private static final Item ITEM1 = new Item(I_NAME1, DATE1);
    private static final Item ITEM2 = new Item(I_NAME2, DATE2);
    private static final Item ITEM3 = new Item(I_NAME3, DATE3);

    private static final Person PERSON1 = new Person(P_NAME1, Arrays.asList(ITEM1, ITEM3));
    private static final Person PERSON2 = new Person(P_NAME2, Collections.singletonList(ITEM2));
    private static final Person PERSON3 = new Person(P_NAME3, Arrays.asList(ITEM1, ITEM2, ITEM3));

    private static final List<Person> PEOPLE = Arrays.asList(
            PERSON1, PERSON2, PERSON3
    );

    // Initial data
    @Before
    public void SetUp() {
        personRepository.deleteAll();
        personRepository.save(PEOPLE);
    }

    // Comment out this annotation if you check migrateTest only!!
    // @After
    public void tearDown() {
    }

    @Test
    public void searchItemNameTest() {
        List<Person> lovedIcePeople = personService.findByItemName("Ice");

        assertThat(lovedIcePeople, is(hasItems(
                PERSON1,
                PERSON3
        )));
    }

    @Test
    public void searchItemDateTest() {
        List<Person> people = personRepository.findAll();

        people.forEach(person -> {
            List<Item> items = person.getLoves();
            List<Item> targetItem = items.stream().filter(item ->
                    betweenDate(item.getCreateTime(), SEARCH_FROM_DATE, SEARCH_TO_DATE)).collect(Collectors.toList());

            System.out.println(targetItem);
        });
        assertThat(true, is(true));
    }

    private boolean betweenDate(Date date, LocalDate from, LocalDate to){
        LocalDate target = toLocalDate(date);
        return target.isAfter(from) && target.isBefore(to);
    }

    private LocalDate toLocalDate(Date date){
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

}
