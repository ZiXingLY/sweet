package io.anshily.admin.service;

import io.anshily.model.SweetWall;

import java.util.List;

public interface SweetWallService {
    int addSweetWall(SweetWall sweetWall);
    List<SweetWall> sweetList(Integer page);

    Long getTotals();

    void startCrowler();

    SweetWall detail(String tid);

}
