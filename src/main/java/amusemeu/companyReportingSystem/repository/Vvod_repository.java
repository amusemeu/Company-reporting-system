package amusemeu.companyReportingSystem.repository;

import amusemeu.companyReportingSystem.model.Vvod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface Vvod_repository extends JpaRepository<Vvod, Long> {

    List<Vvod> findAll();

}
