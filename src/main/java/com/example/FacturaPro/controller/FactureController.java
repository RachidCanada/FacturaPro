package com.example.FacturaPro.controller;

import com.example.FacturaPro.model.Facture;
import com.example.FacturaPro.service.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/factures")
public class FactureController {

    @Autowired
    private FactureService factureService;

    @GetMapping
    public String afficherFactures(Model model) {
        List<Facture> factures = factureService.getAllFactures();
        model.addAttribute("factures", factures);
        model.addAttribute("facture", new Facture());
        return "factures";
    }

    @PostMapping("/ajouter")
    public String ajouterFacture(Facture facture) {
        factureService.saveFacture(facture);
        return "redirect:/factures";
    }

    @GetMapping("/supprimer/{id}")
    public String supprimerFacture(@PathVariable Long id) {
        if (factureService.existsById(id)) {
            factureService.deleteFacture(id);
        }
        return "redirect:/factures";
    }

    @GetMapping("/modifier/{id}")
    public String modifierFacture(@PathVariable Long id, Model model) {
        Facture facture = factureService.findById(id);
        model.addAttribute("facture", facture);
        return "modifier-facture";
    }

    @PostMapping("/modifier")
    public String enregistrerModification(@ModelAttribute Facture facture) {
        factureService.saveFacture(facture);
        return "redirect:/factures";
    }

    @GetMapping("/api")
    @ResponseBody
    public ResponseEntity<List<Facture>> getAllFactures() {
        List<Facture> factures = factureService.getAllFactures();
        return ResponseEntity.ok(factures);
    }

   
    @PostMapping("/api")
    @ResponseBody
    public ResponseEntity<Facture> createFacture(@RequestBody Facture facture) {
        Facture savedFacture = factureService.saveFacture(facture);
        return ResponseEntity.ok(savedFacture);
    }

    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteFacture(@PathVariable Long id) {
        if (factureService.existsById(id)) {
            factureService.deleteFacture(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
