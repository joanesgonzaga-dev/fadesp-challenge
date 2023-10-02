package com.fadesp.challenge.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fadesp.challenge.model.Pagamento;
import com.fadesp.challenge.model.enums.MetodoDePagamento;
import com.fadesp.challenge.model.enums.StatusDoPagamento;
import com.fadesp.challenge.repository.PagamentoRepository;
import com.fadesp.challenge.service.PagamentoService;

@Service
public class PagamentoServiceImpl implements PagamentoService{
	
	private final PagamentoRepository pagamentoRepository;
	
	public PagamentoServiceImpl(PagamentoRepository pagamentoRepository){
		super();
		this.pagamentoRepository = pagamentoRepository;
	}

	@Override
	@Transactional
	public ResponseEntity atualizaStatus(Integer id, String statusPagamento) {		
		Optional<Pagamento> pag = pagamentoRepository.findById(id);	
		
		if(pag.isPresent()) {
			Pagamento pagamento = pag.get();
			if(pagamento.getStatusPagamento() == StatusDoPagamento.pendente) {
				pagamento.setStatusPagamento(StatusDoPagamento.processado);
				this.pagamentoRepository.save(pagamento);
				return ResponseEntity.ok(pagamento);
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Status do pagamento não é 'pendente'.");
	        }
		}else {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pagamento não encontrado com o Código fornecido.");
	    }
	}

	public ResponseEntity retornaTodosPagamentos() {
		
		List<Pagamento> pagamentos = this.pagamentoRepository.findAll();
		
		if(!pagamentos.isEmpty()) {
			return ResponseEntity.ok(pagamentos);
		}
		
		else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Não existe Pagamento cadastrado"); 
		}
	}
	
	public ResponseEntity criaPagamento(Pagamento pagamento) {

		
		if( (pagamento.getMetodoDePagamento() != MetodoDePagamento.cartao_credito && 
				pagamento.getMetodoDePagamento() != MetodoDePagamento.cartao_debito)
				&& pagamento.getNumeroCartao() != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Número de cartão só deve ser informado para pagamentos em 'cartao_credito' ou 'cartao_debito' ");
		}
		
		else {
			this.pagamentoRepository.save(pagamento);
			
			return ResponseEntity.ok(pagamento);	
		}
		
		
	}

	public ResponseEntity retornaPagamentoPorCodigo(Integer codigo) {
	
		Optional<Pagamento> pagamento = pagamentoRepository.findById(codigo);
		if(pagamento.isPresent()) {
			return ResponseEntity.ok(pagamento);
		}else {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pagamento não encontrado com o Código fornecido.");
	    }
	}

	@Override
	public ResponseEntity retornaPagamentosPorStatusPagamento(StatusDoPagamento status) {
		
		List<Pagamento> pagamentos = this.pagamentoRepository.findByStatusPagamento(status);
		if(!pagamentos.isEmpty()) {
				return ResponseEntity.ok(pagamentos);
		}else {
	        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Nenhum pagamento encontrado com o Status fornecido");
	    }
	}

	@Override
	public ResponseEntity RetornaPagamentoPorCpfCnpj(String cpfCnpj) {
		
		Pagamento pagamento = this.pagamentoRepository.findOneByCpfCnpj(cpfCnpj);
		if(pagamento != null) {
				return ResponseEntity.ok(pagamento);
		}else {
	        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Nenhum pagamento encontrado com o Cpf/Cnpj fornecido");
	    }
	}

	
}