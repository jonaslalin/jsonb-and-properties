package com.jonaslalin.jsonb_and_properties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LombokUser {
    private String firstName;
    @Builder.Default
    private String middleInitial = "DEFAULT";
    private String lastName;
}
