package com.acrosure;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.jupiter.api.Test;

public class AcrosureTest {

    @Test
    public void Application_ByDefault_ReturnsApplicationManager() {
        Acrosure acrosure = new Acrosure("some_token");

        ApplicationManager manager = acrosure.application();

        assertThat(manager, instanceOf(ApplicationManager.class));
    }
}