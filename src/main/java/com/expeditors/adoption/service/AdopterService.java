package com.expeditors.adoption.service;

import com.expeditors.adoption.domain.entities.Adopter;

public interface AdopterService {
    Adopter getAdopterById(int adopterId);
}
