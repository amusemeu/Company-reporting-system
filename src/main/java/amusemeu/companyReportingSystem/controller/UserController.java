package amusemeu.companyReportingSystem.controller;

import amusemeu.companyReportingSystem.model.User;
import amusemeu.companyReportingSystem.model.Vvod;
import amusemeu.companyReportingSystem.service.UserService;
import amusemeu.companyReportingSystem.service.VvodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    private VvodService vvodService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String viewHomePage(Model model) {
        return findPaginated(1, "userName", "asc", model);
    }


    @GetMapping("/pageUsers/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model){
        int pageSize = 5;
        Page<User> page = userService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<User> listUser = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("listUser",listUser);
        return "admin";
    }



    //не доделан вывод диаграммы
    @GetMapping("/displayBarGraph")
    public String barGraph(Model model) {
       //Map<String, Integer> surveyMap = new LinkedHashMap<>();
       /*for (int i = 0; i <vvodService.getAllVvod().size(); i ++) {
            surveyMap.put(vvodService.getAllVvod().get(i).getNte(), Integer.valueOf(vvodService.getAllVvod().get(i).getFactVvoda()));
        }*/
        Set<Vvod> surveyMap = (Set<Vvod>) vvodService.getAllVvod();
        model.addAttribute("surveyMap", surveyMap);
        System.out.println(surveyMap);
        return "bar";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("deleteUserById/{id}")
    public String deleteUserById(@PathVariable(value = "id") long id){
        this.userService.deleteUserbyId(id);
        return "redirect:/";
    }
}
