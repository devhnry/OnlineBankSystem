package org.henry.onlinebankingsystemp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.henry.onlinebankingsystemp.dto.DefaultResponse;
import org.henry.onlinebankingsystemp.entity.Customer;
import org.henry.onlinebankingsystemp.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;

    public DefaultResponse suspendUser(Long id){
        DefaultResponse res = new DefaultResponse();
        Customer customer = userRepository.findById(id).orElseThrow(
                () -> {
                    log.error("Customer does not exist");
                    return new UsernameNotFoundException(String.format("Customer with id %l does not exist", id));
                }
        );

        customer.setIsSuspended(true);
        authenticationService.revokeAllUserTokens(customer);
        res.setStatusCode(200);
        res.setMessage("Successfully suspended users");
        log.info("Customer has been saved successfully");
        userRepository.save(customer);
        return res;
    }
}
