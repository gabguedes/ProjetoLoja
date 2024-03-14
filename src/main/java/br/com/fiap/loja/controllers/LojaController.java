package br.com.fiap.loja.controllers;

import br.com.fiap.loja.model.Loja;
import br.com.fiap.loja.repository.LojaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/lojas")
public class LojaController {

    @Autowired
    private LojaRepository repository;

    @GetMapping("/novo")
    public String adicionarLoja(Model model){
        model.addAttribute("loja", new Loja());
        return "/views/novo-loja";
    }

    @PostMapping("/salvar")
    @Transactional
    public String insertLoja(@Valid Loja loja,
                             BindingResult br,
                             RedirectAttributes attributes){
        if(br.hasErrors()){
            return "/views/novo-loja";
        }

        repository.save(loja);
        attributes.addFlashAttribute("mensagem", "Loja cadastrada com sucesso!");
        return "redirect:/lojas/novo";

    }

    @GetMapping("/listar")
    @Transactional(readOnly = true)
    public String listarLojas(Model model) {
        model.addAttribute("lojas", repository.findAll());
        return "/views/listar-lojas";
    }

    @GetMapping("/editar/{id}")
    @Transactional(readOnly = true)
    public String editarLoja(@PathVariable ("id") int id, Model model ){
        Loja loja = repository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Loja inválida - id: " + id)
        );
        model.addAttribute("loja", loja);
        return "/views/editar-loja";
    }

    @PostMapping("/editar/{id}")
    @Transactional
    public String editarLoja(@PathVariable ("id") int id, @Valid Loja loja, BindingResult br){
        if(br.hasErrors()){
            loja.setId(id);
            return "/produto/editar-produto";
        }

        repository.save(loja);
        return "redirect:/lojas/listar";
    }

    @GetMapping("/deletar/{id}")
    @Transactional
    public String deletarLojas(@PathVariable ("id") int id, Model model ){
        if(!repository.existsById(id)){
            throw new IllegalArgumentException("Produto inválido - id: " + id);
        }
        try{
            repository.deleteById(id);
        }catch (Exception e){
            throw new IllegalArgumentException("Produto inválido - id: " + id);
        }
        return "redirect:/lojas/listar";
    }





}
