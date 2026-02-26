package com.ramires.gestaousuarios.controller;

import com.ramires.gestaousuarios.model.Usuario;
import com.ramires.gestaousuarios.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", service.listarTodos());
        return "lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "formulario"; // busca o arquivo formulario.html em templates/
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Usuario usuario) {
        service.criarUsuario(usuario.getNome(), usuario.getEmail());
        return "redirect:/usuarios"; // volta para a lista após salvar
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("usuario", service.buscarPorId(id));
        return "formulario";
    }

    @PostMapping("/atualizar")
    public String atualizar(@ModelAttribute Usuario usuario) {
        service.atualizarUsuario(usuario.getId(), usuario.getNome(), usuario.getEmail());
        return "redirect:/usuarios";
    }

    @GetMapping("/remover/{id}")
    public String remover(@PathVariable Long id) {
        service.removerUsuario(id);
        return "redirect:/usuarios";
    }

    @GetMapping("/buscar")
    public String buscar(@RequestParam(required = false) Long id, Model model) {
        if (id != null) {
            Usuario usuario = service.buscarPorId(id);
            if (usuario != null) {
                model.addAttribute("usuario", usuario);
            } else {
                model.addAttribute("erro", "Usuário com ID " + id + " não encontrado.");
            }
        }
        return "buscar";
    }
}