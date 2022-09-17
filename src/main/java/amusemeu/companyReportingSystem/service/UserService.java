package amusemeu.companyReportingSystem.service;


import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;
import amusemeu.companyReportingSystem.model.User;
import amusemeu.companyReportingSystem.web.dto.UserRegistrationDto;

import java.util.List;


public interface UserService extends UserDetailsService {

    User save(UserRegistrationDto registrationDto);
    List<User> getAllUsers();
    Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
    void deleteUserbyId(long id);
}
