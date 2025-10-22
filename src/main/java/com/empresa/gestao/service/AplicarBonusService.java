package com.empresa.gestao.service;

import com.empresa.gestao.business.AplicarBonus;
import com.empresa.gestao.domain.Vendedor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AplicarBonusService {

    public void aplicarBonusVendedores(Set<Vendedor> vendedores) {
        AplicarBonus aplicarBonus = new AplicarBonus();

        vendedores.forEach(vendedor -> {
            double bonus = aplicarBonus.aplicarBonusDestaque(vendedor);
            if (bonus > 0) {
                System.out.println("Bônus aplicado no vendedor: " + vendedor.getNome());
            }
        });
    }
}
