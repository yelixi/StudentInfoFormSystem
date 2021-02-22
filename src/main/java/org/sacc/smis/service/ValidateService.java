package org.sacc.smis.service;

import org.sacc.smis.entity.User;
import org.sacc.smis.entity.UserValidate;
import org.springframework.mail.SimpleMailMessage;
import org.thymeleaf.util.Validate;

import java.util.List;

public interface ValidateService {

    void sendPasswordResetEmail(SimpleMailMessage email);

    UserValidate insertNewResetRecord(UserValidate validate, User users, String token);

    List<UserValidate> findUserByResetToken(String resetToken);

    boolean validateLimitation(String email, long requestPerDay, long interval, String token);

    long sendValidateLimitation(String email, long requestPerDay, int interval);


}
