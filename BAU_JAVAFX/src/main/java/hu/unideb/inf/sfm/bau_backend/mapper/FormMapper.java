package hu.unideb.inf.sfm.bau_backend.mapper;

import hu.unideb.inf.sfm.bau_backend.data.FormDto;
import hu.unideb.inf.sfm.bau_backend.entity.FormEntity;

import java.time.LocalDate;

public class FormMapper {

    public static FormEntity FormDtoToFormEntity(FormDto formDto) {
        FormEntity formEntity = FormEntity.builder()
                .panasz(formDto.panasz())
                .faj(formDto.faj())
                .gazdi_name(formDto.gazdi_name())
                .email(formDto.email())
                .phone_num(formDto.phone_num())
                .datum(formDto.datum())
                .idopont(formDto.idopont()).build();

        return formEntity;
    }

}
