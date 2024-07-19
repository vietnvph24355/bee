package com.example.bee.service.impl;

import com.example.bee.entity.MauSac;
import com.example.bee.repository.MauSacRepository;
import com.example.bee.service.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class MauSacServiceImpl implements MauSacService {
    @Autowired
    private MauSacRepository REPO;

    @Override
    public Page<MauSac> getAllMauSac(Integer totalPage, Integer size) {
        return null;
    }

    @Override
    public MauSac getOneMauSac(String id) {
        return null;
    }

    @Override
    public MauSac deleteMauSac(String id) {
        return null;
    }

    @Override
    public MauSac updateMauSac(String id) {
        return null;
    }
}
