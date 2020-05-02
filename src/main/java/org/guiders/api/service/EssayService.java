package org.guiders.api.service;

import lombok.RequiredArgsConstructor;
import org.guiders.api.domain.Essay;
import org.guiders.api.exception.PostNotFoundException;
import org.guiders.api.payload.EssayDto;
import org.guiders.api.payload.GuiderDto;
import org.guiders.api.repository.EssayRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public EssayDto.ResponseDetail getDetail(Long id) {

        Essay essay = essayRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);

        EssayDto.ResponseDetail essayDto = modelMapper.map(essay, EssayDto.ResponseDetail.class);

        GuiderDto.DetailResponse guiderDto = modelMapper.map(essay.getGuider(), GuiderDto.DetailResponse.class);

        essayDto.setWriter(guiderDto);

        return essayDto;
    }

    public List<EssayDto.Response> getList(int page, int size) {

        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by("id").descending());

        Page<Essay> essays = essayRepository.findAll(pageRequest);

        return essays.getContent().stream().map(essay -> {
            GuiderDto.Response guiderDto = modelMapper.map(essay.getGuider(), GuiderDto.Response.class);
            EssayDto.Response essayDto = modelMapper.map(essay, EssayDto.Response.class);
            essayDto.setWriter(guiderDto);
            return essayDto;
        }).collect(Collectors.toList());
    }

    @Transactional
    public void update(Long id, EssayDto.UpdateRequest essayDto) {
        Essay essay = essayRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);

        essay.update(essayDto);
    }
}
