package com.durodecodar.desafioilab.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.durodecodar.desafioilab.dto.PedidoDTO;
import com.durodecodar.desafioilab.model.Pedido;

public interface IPedidoServices {
	
//	public Pedido adicionarPedido(Pedido pedido);
//	public boolean removerPedido(Integer id);
//	public ResponseEntity<?> buscarPedidoPorId(@PathVariable Integer idPedido);
	public List<PedidoDTO> listarTodosPedidos();
	public PedidoDTO buscarPedidoPorId(Integer idPedido);
	public List<PedidoDTO> listaPedidosEmAberto();
	
}
