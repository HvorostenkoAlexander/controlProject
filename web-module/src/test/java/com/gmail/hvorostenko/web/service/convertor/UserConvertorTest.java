package com.gmail.hvorostenko.web.service.convertor;

import com.gmail.hvorostenko.repository.model.User;
import com.gmail.hvorostenko.service.converter.UserConvertor;
import com.gmail.hvorostenko.service.model.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserConvertorTest {
    private final UserConvertor userConvertor = new UserConvertor();

    @Test
    void shouldConvertUserDTOtoUserAndReturnNotNullObject() {
        UserDTO userDTO = new UserDTO();
        User user = userConvertor.convert(userDTO);
        Assertions.assertNotNull(user);
    }

    @Test
    void shouldConvertUserDTOtoUserAndReturnRightFirstName() {
        UserDTO userDTO = new UserDTO();
        String  name = "name";
        userDTO.setName( name);
        User user = userConvertor.convert(userDTO);
        Assertions.assertEquals( name, user.getName());
    }
}
