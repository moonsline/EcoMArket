package com.example.EcoMarket.Repository;

import com.example.EcoMarket.Model.Model_Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Model_Pedido, Integer> {
}
