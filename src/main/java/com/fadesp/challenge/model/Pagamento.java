package com.fadesp.challenge.model;

import java.math.BigDecimal;
import java.util.Optional;

import com.fadesp.challenge.model.enums.MetodoDePagamento;
import com.fadesp.challenge.model.enums.StatusDoPagamento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Pagamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;
	
	@Column(nullable = false)
	private String cpfCnpj;
	
	@Column(nullable = true)
	private String numeroCartao;
	
	@Column(nullable = false)
	private BigDecimal valorPagamento;
	
	@Column(nullable = false)
	private MetodoDePagamento metodoDePagamento;

	@Column(nullable = false)
	private StatusDoPagamento statusPagamento;
	
	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getCpfCnpj() {
		return this.cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getNumeroCartao() {
		return this.numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public BigDecimal getValorPagamento() {
		return this.valorPagamento;
	}

	public void setValorPagamento(BigDecimal valorPagamento) {
		this.valorPagamento = valorPagamento;
	}
	
	public MetodoDePagamento getMetodoDePagamento() {
		return this.metodoDePagamento;
	}
	
	public void setMetodoDePagamento(MetodoDePagamento metodoDePagamento) {
		this.metodoDePagamento = metodoDePagamento;
	}

	public StatusDoPagamento getStatusPagamento() {
		return statusPagamento;
	}

	public void setStatusPagamento(StatusDoPagamento statusPagamento) {
		this.statusPagamento = statusPagamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.codigo == null) ? 0 : this.codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pagamento other = (Pagamento) obj;
		if (this.codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!this.codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
	
}
