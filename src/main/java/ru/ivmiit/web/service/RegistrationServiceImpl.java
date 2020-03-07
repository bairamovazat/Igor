package ru.ivmiit.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivmiit.web.forms.UserRegistrationForm;
import ru.ivmiit.web.model.Role;
import ru.ivmiit.web.model.User;
import ru.ivmiit.web.repository.RoleRepository;
import ru.ivmiit.web.repository.UserRepository;
import ru.ivmiit.web.security.details.State;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private EmailService emailService;
    @Value("${site.url}")
    private String siteUrl;

    private String emailMessage = "Здравствуйте! Перейдите по ссылке, чтобы подтвердить аккаунт %s/confirm/%s";

    private ExecutorService executorService = Executors.newFixedThreadPool(4);

    @Override
    @Transactional
    public void register(UserRegistrationForm userForm) {
        UUID uuid = UUID.randomUUID();
        User newUser = User.builder()
                .login(userForm.getLogin())
                .hashPassword(passwordEncoder.encode(userForm.getPassword()))
                .state(State.CREATED)
                .email(userForm.getEmail())
                .name(userForm.getName())
                .roles(new ArrayList<>())
                .uuid(uuid)
                .build();
        Role role = roleRepository.findFirstByRole(ru.ivmiit.web.security.details.Role.USER);
        role.getUsers().add(newUser);
        newUser.getRoles().add(role);
        executorService.execute(() -> {
            emailService.sendMail(String.format(emailMessage, siteUrl, uuid), "Подтверждение аккаунта", userForm.getEmail());
        });
        userRepository.save(newUser);
    }
}
