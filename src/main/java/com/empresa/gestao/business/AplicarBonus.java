package com.empresa.gestao.business;

import com.empresa.gestao.domain.Funcionario;
import com.empresa.gestao.domain.Gerente;

public class AplicarBonus {

    public static final double VALOR_BONUS_DESTAQUE = 2000;
    public static final double VALOR_SALARIO_HAPTO = 15000;

    public double aplicarBonusDestaque(Funcionario funcionario) {

        if (funcionario instanceof Gerente){
            return 0.0;
        }

        if (funcionario.getSalario() <= VALOR_SALARIO_HAPTO){
            funcionario.setBonus(VALOR_BONUS_DESTAQUE);
            return VALOR_BONUS_DESTAQUE;
        }else {
            return 0.0;
        }
    }
}
