package com.mycompany.app;

import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void shouldReturnAnimalObject() {
        User user = User.builder().name("bruno").build();
        assertThat("bruno", equalTo(user.getName()));
    }
    @Test
    public void shouldAnswerWithTrue(){
        int message = this.answer()
                .map(Integer::parseInt)
                .or(this::defaultAnswer)
                .get();
        assertThat(message, is(42));
    }

    public Optional<String> answer() {
        return Optional.ofNullable(null);
    }

    public Optional<Integer> defaultAnswer() {
        return Optional.of(42);
    }
}
