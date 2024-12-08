package hu.unideb.inf.sfm.bau_backend.service;

import hu.unideb.inf.sfm.bau_backend.data.FormDto;
import hu.unideb.inf.sfm.bau_backend.entity.FormEntity;
import hu.unideb.inf.sfm.bau_backend.mapper.FormMapper;
import hu.unideb.inf.sfm.bau_backend.repository.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormService {

    @Autowired
    private FormRepository formRepository;

    public FormEntity addForm(FormDto formDto) {
        FormEntity formEntity = FormMapper.FormDtoToFormEntity(formDto);
        formRepository.save(formEntity);
        return formEntity;
    }
}
