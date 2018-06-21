package com.acrosure;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class AcrosureTest {

    @Test
    public void applications_byDefault_returnsApplicationManager() {
        Acrosure acrosure = new Acrosure("some_token");

        ApplicationManager manager = acrosure.applications();

        assertThat(manager, instanceOf(ApplicationManager.class));
    }
}