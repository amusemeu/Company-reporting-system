package amusemeu.companyReportingSystem.service;

import amusemeu.companyReportingSystem.model.Vvod;
import amusemeu.companyReportingSystem.model.VvodWithSum;
import org.springframework.data.domain.Page;

import java.util.List;


public interface VvodService {
    List<Vvod> getAllVvod();

    List<VvodWithSum> getAllVvodByNte();

    void saveVvod (Vvod vvod);

    Vvod getVvodById(long id);

    void deleteVvodById(long id);

    Page<Vvod> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}
