package io.anshily.admin.service;

import io.anshily.model.SweetTieba;

import java.util.List;

public interface SweetTiebaService {

    Integer addSweetWall(SweetTieba sweetTieba);
    List<SweetTieba> sweetList(Integer page);

    Long getTotals();

    void startCrowler();

    SweetTieba detail(String tid);
}
