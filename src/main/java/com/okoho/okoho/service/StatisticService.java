package com.okoho.okoho.service;

import com.okoho.okoho.service.dto.MessageDTO;
import com.okoho.okoho.service.dto.StatisticResponse;

import java.util.Optional;

public interface StatisticService {
    public Optional<StatisticResponse> getStatistics();
    public void sendCantactus(MessageDTO messageDTO);
    public Optional<StatisticResponse> getDashbord(String id);
    public Optional<StatisticResponse> getDashbordEntreprise(String id);
}
