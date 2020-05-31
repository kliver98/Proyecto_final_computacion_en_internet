package co.edu.icesi.fi.tics.tssc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.fi.tics.tssc.model.TsscTimecontrol;
import co.edu.icesi.fi.tics.tssc.service.TsscTimeControlService;
import co.edu.icesi.fi.tics.tssc.service.TsscTimeControlServiceImpl;

@Controller
public class TsscTimeControlController {
	
	@Autowired
	private TsscTimeControlService tsscTimeControlService;
	
	
	@GetMapping("/timeControl/")
	public String index(Model model) {
		model.addAttribute("timeControls", tsscTimeControlService.findAll());
		return "timeControl/index";
		
	}
	
	@GetMapping("timeControl/add")
	public String add(Model model) {
		model.addAttribute("timeControl", new TsscTimecontrol());
		return "timeControl/save";
	}
	
	@PostMapping("timeControl/add")
	public String addTimeControl(@RequestParam(value = "action", required = true) String action, TsscTimecontrol tsscTimeControl, BindingResult bindingResult , Model model) {
		if(tsscTimeControl == null) {
			throw new IllegalArgumentException("Invalid TimeControl");
		}
		
		if(!action.equals("Cancelar")) {
			if(bindingResult.hasErrors()) {
				return "timeControl/add";
			} else {
				tsscTimeControlService.save(tsscTimeControl);
			}
			
		}
		return "redirect:/timeControl/";
		
	}
	
	@GetMapping("timeControl/edit/{id}")
	public String edit(@PathVariable("id") long id, Model model) {
		TsscTimecontrol  tsscTimecontrol= tsscTimeControlService.findById(id);
			if(tsscTimecontrol == null) {
				throw new IllegalArgumentException("Invalid timeControl Id:" + id);
			}
			model.addAttribute("timeControl", tsscTimecontrol);
			return "timeControl/edit";
			
	}
	@PostMapping("timeControl/edit/{id}")
	public String editTimeControl(@Validated @PathVariable("id") long id, @RequestParam(value="action", required = true) String action, TsscTimecontrol tsscTimecontrol, BindingResult bindingResult) {
	
			if(action != null && !action.equals("Cancelar")) {
				if(bindingResult.hasErrors()) {
					return "/timeControl/edit";
				}else {
					tsscTimeControlService.update(tsscTimecontrol);
				}
			}
			return "redirect:/timeControl/";
			
	}
	
	@GetMapping("/timeControl/del/{id}")
	public String delete(@PathVariable("id") long id) {
		TsscTimecontrol tsscTimecontrol = tsscTimeControlService.findById(id);
		if(tsscTimecontrol == null) {
			throw new IllegalArgumentException("Invalid timeControl Id:" + id);
		}
		tsscTimeControlService.delete(tsscTimecontrol);
		return "redirect:/timeControl/";
	}
	
	
}



