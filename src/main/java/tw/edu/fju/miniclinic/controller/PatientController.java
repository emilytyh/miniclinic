package tw.edu.fju.miniclinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import tw.edu.fju.miniclinic.model.Patient;
import tw.edu.fju.miniclinic.model.PatientRepository;

import java.util.List;

@Controller   // 網頁 Controller（回傳 HTML）
public class PatientController {

    @Autowired
    private PatientRepository patientRepo;   // 注入資料來源

    // 頁面：顯示病患清單
    @GetMapping("/patients")
    public String listPatients(Model model) {

        // 取得所有病患資料
        model.addAttribute("patients", patientRepo.findAll());

        return "patients";   // 對應 templates/patients.html
    }

    // API：回傳 JSON
    @GetMapping("/api/patients")
    @ResponseBody   // 直接回傳 JSON（不是頁面）
    public List<Patient> getPatients() {
        return patientRepo.findAll();
    }

    @GetMapping("/api/patients/{chartNo}")
    @ResponseBody
    public ResponseEntity<Patient> getPatient(@PathVariable String chartNo) {
        return patientRepo.findById(chartNo)
                .map(p -> ResponseEntity.ok(p))
                .orElse(ResponseEntity.notFound().build());
    }
}