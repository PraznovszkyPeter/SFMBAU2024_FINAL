package hu.unideb.inf.sfm.bau_backend.mapper;

import hu.unideb.inf.sfm.bau_backend.data.FormDto;
import hu.unideb.inf.sfm.bau_backend.entity.FormEntity;

import java.time.LocalDate;

public class FormMapper {

    public static FormEntity FormDtoToFormEntity(FormDto formDto) {
        FormEntity formEntity = FormEntity.builder()
                .faj(formDto.faj())
                .panasz(formDto.panasz())
                .datum(LocalDate.parse(formDto.datum()))
                .idopont(formDto.idopont()).build();

        return formEntity;
    }

}
