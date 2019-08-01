package com.jonaslalin.jsonb_and_properties;

import static org.junit.Assert.assertEquals;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.config.PropertyNamingStrategy;
import org.junit.Test;

public class JsonbDeserializationTest {
    private final JsonbConfig jsonbConfig = new JsonbConfig()
            .withPropertyNamingStrategy(
                    PropertyNamingStrategy.LOWER_CASE_WITH_UNDERSCORES);
    private final Jsonb jsonb = JsonbBuilder.create(jsonbConfig);

    @Test
    public void shouldDeserializeWithAbsentProperties() {
        final String json = "{" +
                "\"first_name\":\"Donald\"," +
                "\"last_name\":\"Duck\"" +
                "}";

        final PojoUser pojoUser = jsonb.fromJson(json, PojoUser.class);
        assertEquals("Donald", pojoUser.getFirstName());
        assertEquals("DEFAULT", pojoUser.getMiddleInitial());
        assertEquals("Duck", pojoUser.getLastName());

        final LombokUser lombokUser = jsonb.fromJson(json, LombokUser.class);
        assertEquals("Donald", lombokUser.getFirstName());
        assertEquals("DEFAULT", lombokUser.getMiddleInitial());
        assertEquals("Duck", lombokUser.getLastName());
    }

    @Test
    public void shouldDeserializeWithPresentProperties() {
        final String json = "{" +
                "\"first_name\":\"Donald\"," +
                "\"middle_initial\":\"F\"," +
                "\"last_name\":\"Duck\"" +
                "}";

        final PojoUser pojoUser = jsonb.fromJson(json, PojoUser.class);
        assertEquals("Donald", pojoUser.getFirstName());
        assertEquals("F", pojoUser.getMiddleInitial());
        assertEquals("Duck", pojoUser.getLastName());

        final LombokUser lombokUser = jsonb.fromJson(json, LombokUser.class);
        assertEquals("Donald", lombokUser.getFirstName());
        assertEquals("F", lombokUser.getMiddleInitial());
        assertEquals("Duck", lombokUser.getLastName());
    }

    @Test
    public void shouldDeserializeWithExcessiveProperties() {
        final String json = "{" +
                "\"first_name\":\"Donald\"," +
                "\"middle_initial\":null," +
                "\"last_name\":\"Duck\"," +
                "\"gender\":\"Male\"" +
                "}";

        final PojoUser pojoUser = jsonb.fromJson(json, PojoUser.class);
        assertEquals("Donald", pojoUser.getFirstName());
        assertEquals(null, pojoUser.getMiddleInitial());
        assertEquals("Duck", pojoUser.getLastName());

        final LombokUser lombokUser = jsonb.fromJson(json, LombokUser.class);
        assertEquals("Donald", lombokUser.getFirstName());
        assertEquals(null, lombokUser.getMiddleInitial());
        assertEquals("Duck", lombokUser.getLastName());
    }
}
