package org.guiders.api.service;

import lombok.RequiredArgsConstructor;
import org.guiders.api.domain.Essay;
import org.guiders.api.payload.EssayDto;
import org.guiders.api.repository.EssayRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EssayService {

    private final ModelMapper modelMapper;
    private final EssayRepository essayRepository;

    public Long register(EssayDto.ResisterRequest request) {

        Essay essay = modelMapper.map(request, Essay.class);

        Essay savedEssay = essayRepository.save(essay);

        return savedEssay.getId();
    }
}
