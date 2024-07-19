package com.example.bee.service;

import com.example.bee.entity.MauSac;
import org.springframework.data.domain.Page;

public interface MauSacService {
    public Page<MauSac> getAllMauSac(Integer totalPage, Integer size);
    public MauSac getOneMauSac(String id);
    public MauSac deleteMauSac(String id);
    public MauSac updateMauSac(String id);

}
