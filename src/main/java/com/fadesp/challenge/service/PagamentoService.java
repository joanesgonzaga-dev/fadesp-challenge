package com.fadesp.challenge.service;

import org.springframework.http.ResponseEntity;

import com.fadesp.challenge.model.Pagamento;
import com.fadesp.challenge.model.enums.StatusDoPagamento;

public interface PagamentoService {

	ResponseEntity atualizaStatus(Integer id, String statusPagamento);
	
	ResponseEntity retornaTodosPagamentos();
	
	ResponseEntity criaPagamento(Pagamento pagamento);
	
	ResponseEntity retornaPagamentoPorCodigo(Integer codigo);
	
	ResponseEntity retornaPagamentosPorStatusPagamento(StatusDoPagamento status);
	
	ResponseEntity RetornaPagamentoPorCpfCnpj(String cpfCnpj);

}
