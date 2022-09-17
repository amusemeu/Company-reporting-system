package amusemeu.companyReportingSystem.controller;

import amusemeu.companyReportingSystem.dataUnload.VvodExcelExporter;
import amusemeu.companyReportingSystem.dataUnload.VvodPDFExporter;
import amusemeu.companyReportingSystem.model.Vvod;
import amusemeu.companyReportingSystem.service.VvodService;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class VvodController {
    @Autowired
   private VvodService vvod_service;

    @GetMapping("/")
    public String viewHomePage(Model model) {
       return findPaginated(1, "nte", "asc", model);
    }

    @GetMapping("/showNewVvodForm")
    public String showNewVvodForm(Model model){
        Vvod vvod = new Vvod();
        model.addAttribute("vvod", vvod);
        return "new_vvod";
    }
    @PostMapping("/saveVvod")
    public String saveVvod(@ModelAttribute("vvod") Vvod vvod){
    vvod_service.saveVvod(vvod);
    return "redirect:/";
    }
    @GetMapping("showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model){
        Vvod vvod = vvod_service.getVvodById(id);
    model.addAttribute("vvod", vvod);
    return "update_vvod";
}
    @GetMapping("deleteVvod/{id}")
    public String deleteVvod(@PathVariable(value = "id") long id){
    this.vvod_service.deleteVvodById(id);
    return "redirect:/";
    }
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model){
    int pageSize = 5;
        Page <Vvod> page = vvod_service.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Vvod> listVvod = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("listVvod",listVvod);
        return "index2";
    }


    @GetMapping("/allNte")
    public String getAllNte(Model model){
        model.addAttribute("listVvod", vvod_service.getAllVvodByNte());
        return "allNte";
    }




    @GetMapping("/vvod/exportPDF")
    public void exportPdf(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        List<Vvod> listVvod = vvod_service.getAllVvod();
        VvodPDFExporter exporter = new VvodPDFExporter(listVvod);
        exporter.export(response);
    }
    @GetMapping("/vvod/exportExcel")
    public void exportExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";

        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());


        String fileName = "vvod_" + currentDateTime + ".xlsx";
        String headerValue = "attachment; filename=" + fileName;
        response.setHeader(headerKey, headerValue);


        List<Vvod> listVvod = vvod_service.getAllVvod();
        VvodExcelExporter exporter = new VvodExcelExporter(listVvod);
        exporter.export(response);
    }

}
