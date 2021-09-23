package ua.testing.registration_form.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Note {
    private Long id;
    private String language;
    private String names;
    private String lastname;
    private String login;
    private String email;
}
