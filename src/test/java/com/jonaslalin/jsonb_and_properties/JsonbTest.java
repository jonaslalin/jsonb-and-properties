package com.jonaslalin.jsonb_and_properties;

import static org.junit.Assert.assertEquals;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.config.PropertyNamingStrategy;

import org.junit.Test;

public class JsonbTest {
    private final JsonbConfig jsonbConfig = new JsonbConfig()
            .withPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE_WITH_UNDERSCORES);
    private final Jsonb jsonb = JsonbBuilder.create(jsonbConfig);

    @Test
    public void shouldDeserializeWithAbsentProperties() {
        final String json = "{\"first_name\":\"Donald\",\"last_name\":\"Duck\"}";
        final User user = jsonb.fromJson(json, User.class);

        assertEquals("Donald", user.getFirstName());
        assertEquals("DEFAULT", user.getMiddleInitial());
        assertEquals("Duck", user.getLastName());
    }

    @Test
    public void shouldDeserializeWithPresentProperties() {
        final String json = "{\"first_name\":\"Donald\",\"middle_initial\":\"F\",\"last_name\":\"Duck\"}";
        final User user = jsonb.fromJson(json, User.class);

        assertEquals("Donald", user.getFirstName());
        assertEquals("F", user.getMiddleInitial());
        assertEquals("Duck", user.getLastName());
    }

    @Test
    public void shouldDeserializeWithExcessiveProperties() {
        final String json = "{\"first_name\":\"Donald\",\"middle_initial\":null,\"last_name\":\"Duck\",\"gender\":\"Male\"}";
        final User user = jsonb.fromJson(json, User.class);

        assertEquals("Donald", user.getFirstName());
        assertEquals(null, user.getMiddleInitial());
        assertEquals("Duck", user.getLastName());
    }
}
