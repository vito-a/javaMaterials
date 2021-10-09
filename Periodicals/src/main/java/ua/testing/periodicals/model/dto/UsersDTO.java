package ua.testing.periodicals.model.dto;

import lombok.*;
import ua.testing.periodicals.model.entity.User;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UsersDTO {
    private List<User> users;
}
