package com.fadesp.challenge.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fadesp.challenge.model.Pagamento;
import com.fadesp.challenge.model.enums.StatusDoPagamento;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
	
	Pagamento findOneByCpfCnpj(String cpfCnpj);
	Optional<Pagamento> findById(Integer id);	
	List<Pagamento> findByStatusPagamento(StatusDoPagamento status);
}
