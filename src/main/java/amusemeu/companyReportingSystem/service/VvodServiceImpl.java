package amusemeu.companyReportingSystem.service;

import amusemeu.companyReportingSystem.model.Vvod;
import amusemeu.companyReportingSystem.model.VvodWithSum;
import amusemeu.companyReportingSystem.repository.Vvod_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VvodServiceImpl implements VvodService {
    @Autowired
    private Vvod_repository vvod_repository;
    private VvodWithSum withSum;


    @Override
    public List<Vvod> getAllVvod() {
        return vvod_repository.findAll();
    }

    @Override
    public void saveVvod(Vvod vvod) {
        this.vvod_repository.save(vvod);
    }

    @Override
    public Vvod getVvodById(long id) {
        Optional<Vvod> optional = vvod_repository.findById(id);
        Vvod vvod = null;
        if(optional.isPresent()){
            vvod = optional.get();
        }else {
            throw new RuntimeException("Позиция " + id + " не найдена");
        }
        return vvod;
    }

    @Override
    public void deleteVvodById(long id) {
        this.vvod_repository.deleteById(id);
    }

    @Override
    public Page<Vvod> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.vvod_repository.findAll(pageable);
    }

    @Override
    public List<VvodWithSum> getAllVvodByNte() {

        List<Vvod> vvodByNte= vvod_repository.findAll();
        double oktSum = 0;
        double gorkSum = 0;
        double mskSum = 0;
        double sevSum = 0;
        double skvSum = 0;
        double uvostSum = 0;
        double privvSum = 0;
        double svrdSum = 0;
        double kbshSum = 0;
        double uurSum = 0;
        double zsivSum = 0;
        double krasSum = 0;
        double vsibSum = 0;
        double zabSum = 0;
        double dvostSum = 0;

        for (int i = 0; i < vvodByNte.size(); i++) {
            if (vvodByNte.get(i).getNte().equals("ОКТ")){
                oktSum +=Double.valueOf(vvodByNte.get(i).getFactVvoda());
            }
            else if (vvodByNte.get(i).getNte().equals("ГОРЬК")){
                    gorkSum +=Double.valueOf(vvodByNte.get(i).getFactVvoda());
                }
            else if (vvodByNte.get(i).getNte().equals("МОСК")){
                mskSum +=Double.valueOf(vvodByNte.get(i).getFactVvoda());
            }
            else if (vvodByNte.get(i).getNte().equals("СЕВ")){
                sevSum +=Double.valueOf(vvodByNte.get(i).getFactVvoda());
            }
            else if (vvodByNte.get(i).getNte().equals("С-КАВ")){
                skvSum +=Double.valueOf(vvodByNte.get(i).getFactVvoda());
            }
            else if (vvodByNte.get(i).getNte().equals("Ю-ВОСТ")){
                uvostSum +=Double.valueOf(vvodByNte.get(i).getFactVvoda());
            }
            else if (vvodByNte.get(i).getNte().equals("ПРИВ")){
                privvSum +=Double.valueOf(vvodByNte.get(i).getFactVvoda());
            }
            else if (vvodByNte.get(i).getNte().equals("СВЕРД")){
                svrdSum +=Double.valueOf(vvodByNte.get(i).getFactVvoda());
            }
            else if (vvodByNte.get(i).getNte().equals("КБШ")){
                kbshSum +=Double.valueOf(vvodByNte.get(i).getFactVvoda());
            }
            else if (vvodByNte.get(i).getNte().equals("Ю-УР")){
                uurSum +=Double.valueOf(vvodByNte.get(i).getFactVvoda());
            }
            else if (vvodByNte.get(i).getNte().equals("З-СИБ")){
                zsivSum +=Double.valueOf(vvodByNte.get(i).getFactVvoda());
            }
            else if (vvodByNte.get(i).getNte().equals("КРАС")){
                krasSum +=Double.valueOf(vvodByNte.get(i).getFactVvoda());
            }
            else if (vvodByNte.get(i).getNte().equals("В-СИБ")){
                vsibSum +=Double.valueOf(vvodByNte.get(i).getFactVvoda());
            }
            else if (vvodByNte.get(i).getNte().equals("ЗАБ")){
                zabSum +=Double.valueOf(vvodByNte.get(i).getFactVvoda());
            }
            else if (vvodByNte.get(i).getNte().equals("ДВОСТ")){
                dvostSum +=Double.valueOf(vvodByNte.get(i).getFactVvoda());
            }

        }
        double oktPlan = 12300;

        List<VvodWithSum> vvodW = new ArrayList<>();
        vvodW.add(new VvodWithSum("ОКТ", oktPlan,oktSum,getPercent(oktPlan,oktSum)));
        vvodW.add(new VvodWithSum("ГОРЬК", 0,gorkSum,0));
        vvodW.add(new VvodWithSum("МОСК", 0,mskSum,0));
        vvodW.add(new VvodWithSum("СЕВ", 0,sevSum,0));
        vvodW.add(new VvodWithSum("С-КАВ", 0,skvSum,0));
        vvodW.add(new VvodWithSum("Ю-ВОСТ", 0,uvostSum,0));
        vvodW.add(new VvodWithSum("ПРИВ", 0,privvSum,0));
        vvodW.add(new VvodWithSum("СВЕРД", 0,svrdSum,0));
        vvodW.add(new VvodWithSum("КБШ", 0,kbshSum,0));
        vvodW.add(new VvodWithSum("Ю-УР", 0,uurSum,0));
        vvodW.add(new VvodWithSum("З-СИБ", 0,zsivSum,0));
        vvodW.add(new VvodWithSum("КРАС", 0,krasSum,0));
        vvodW.add(new VvodWithSum("В-СИБ", 0,vsibSum,0));
        vvodW.add(new VvodWithSum("ЗАБ", 0,zabSum,0));
        vvodW.add(new VvodWithSum("ДВОСТ", 0,dvostSum,0));
        return vvodW;
    }

    public double getPercent(double planVvoda, double factVvoda){

        return (factVvoda * 100)/planVvoda;

    }

}
