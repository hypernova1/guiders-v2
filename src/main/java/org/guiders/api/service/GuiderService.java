package org.guiders.api.service;

import lombok.RequiredArgsConstructor;
import org.guiders.api.domain.Guider;
import org.guiders.api.exception.AccountNotFoundException;
import org.guiders.api.payload.GuiderDto;
import org.guiders.api.repository.GuiderRepository;
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
public class GuiderService {

    private final ModelMapper modelMapper;
    private final GuiderRepository guiderRepository;

    public GuiderDto.DetailResponse getDetail(Long id) {

        Guider guider = guiderRepository.findById(id)
                .orElseThrow(AccountNotFoundException::new);

        return modelMapper.map(guider, GuiderDto.DetailResponse.class);

    }

    public List<GuiderDto.DetailResponse> getList(int page, int size) {

        PageRequest pageable = PageRequest.of(page - 1, size, Sort.by("id").descending());

        Page<Guider> guiders = guiderRepository.findAll(pageable);

        return guiders.getContent().stream()
                .map(guider -> modelMapper.map(guider, GuiderDto.DetailResponse.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public void update(Long id, GuiderDto.DetailRequest request) {

        Guider guider = guiderRepository.findById(id)
                .orElseThrow(AccountNotFoundException::new);

        guider.update(request);

    }

    public void delete(Long id) {
        guiderRepository.deleteById(id);
    }
}
