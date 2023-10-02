package com.fadesp.challenge.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fadesp.challenge.model.Pagamento;
import com.fadesp.challenge.model.enums.StatusDoPagamento;
import com.fadesp.challenge.repository.PagamentoRepository;
import com.fadesp.challenge.service.PagamentoService;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController  {

	private final PagamentoService pagamentoService;
	
	public PagamentoController(PagamentoService pagamentoService){
		super();
		this.pagamentoService = pagamentoService;
	}

	@GetMapping
	public ResponseEntity Listar()	{	
		return pagamentoService.retornaTodosPagamentos();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity CriaPagamento(@RequestBody Pagamento pagamento){
		return this.pagamentoService.criaPagamento(pagamento);	
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity AtualizaStatusPagamento(@PathVariable Integer id, @RequestBody String statusPagamento)	{	
		return  this.pagamentoService.atualizaStatus(id, statusPagamento);	
	}
	
	@GetMapping("{codigo}")
	public ResponseEntity RetornaPagamentoPorId(@PathVariable Integer codigo)	{
		
		return this.pagamentoService.retornaPagamentoPorCodigo(codigo);
	}
	
	@GetMapping("/status/{status}")
	public ResponseEntity RetornaPagamentoPorStatusPagamento(@PathVariable StatusDoPagamento status){
		return this.pagamentoService.retornaPagamentosPorStatusPagamento(status);
	}
	
	@GetMapping("/cpfcnpj/{cpfCnpj}")
	public ResponseEntity RetornaPagamentoPorCpfCnpj(@PathVariable String cpfCnpj){
		return this.pagamentoService.RetornaPagamentoPorCpfCnpj(cpfCnpj);
	}

	
	
	
	
	
	
}
