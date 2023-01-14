package br.com.isidrocorp.Projeto.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import br.com.isidrocorp.Projeto.dao.ContaDao;
import br.com.isidrocorp.Projeto.model.Conta;

@RestController
public class ContaController {
	@Autowired
	private ContaDao dao;

	@GetMapping("/contas")
	public ArrayList<Conta> RecuperarTudo() {
		ArrayList<Conta> lista;
		lista = (ArrayList<Conta>) dao.findAll();
		return lista;
	}

	@GetMapping("/contas/{numero}")
	public Conta RecuperarConta(@PathVariable int numero) {
		return dao.findById(numero).orElse(null);
	}
}