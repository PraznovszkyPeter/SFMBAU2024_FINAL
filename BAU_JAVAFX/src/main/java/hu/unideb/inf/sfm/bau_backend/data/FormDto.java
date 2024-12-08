package hu.unideb.inf.sfm.bau_backend.data;

import java.time.LocalDate;

public record FormDto(String panasz, String faj, String gazdi_name , String email, String phone_num,  String datum, String idopont) {
}
