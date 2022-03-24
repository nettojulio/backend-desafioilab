package com.durodecodar.desafioilab.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.durodecodar.desafioilab.dao.PedidoDAO;
import com.durodecodar.desafioilab.model.Pedido;
import com.durodecodar.desafioilab.util.Mensagem;

@Service
public class PedidoServiceImpl implements IPedidoServices {

	@Autowired
	private PedidoDAO pedidoDao;

	@Override
	public List<Pedido> listaPedidosEmAberto() {
		return (List<Pedido>) pedidoDao.findByStatusOrderByDataCriacao("em_aberto");
	}


	@Override
	public ResponseEntity<List<Pedido>> listarTodosPedidos() {
		List<Pedido> pedidos = (List<Pedido>) pedidoDao.findAll();
		return new ResponseEntity<List<Pedido>>(pedidos, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> buscarPedidoPorId(Integer idPedido) {
		Pedido pedido = pedidoDao.findById(idPedido).orElse(null);
		if (pedido != null) {
			return ResponseEntity.ok(pedido);
		}
		return ResponseEntity.status(400).body(new Mensagem(400, "Pedido nao encontrado"));
	}

	@Override
	public Pedido atualizarStatusPedido(Pedido pedido) {
		Optional<Pedido> pedidoExiste = pedidoDao.findById(pedido.getId());

		if (pedidoExiste.isEmpty()) {
			return null;
		}
		
		pedido.setStatus("em_andamento");
		pedido.getEntregador().setEmEntrega(true);
		
		pedidoDao.save(pedido);
		return pedido;

	}

}
