package com.musalasoft.practicaltask.GatewayManager.db.dao;

import com.musalasoft.practicaltask.GatewayManager.db.entity.Gateway;

import java.util.List;

public interface IGatewayDAO {
    List<Gateway> getGateways();
    Gateway getGateway(int id);
    Gateway createGateway(Gateway gateway);
    Gateway updateGateway(int id,Gateway gateway);
    boolean deleteGateway(int id);
}
