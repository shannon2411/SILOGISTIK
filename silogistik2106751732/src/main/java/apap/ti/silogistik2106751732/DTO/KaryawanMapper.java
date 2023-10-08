package apap.ti.silogistik2106751732.DTO;
import apap.ti.silogistik2106751732.DTO.request.CreateKaryawanRequestDTO;
import apap.ti.silogistik2106751732.model.Karyawan;
import org.mapstruct.Mapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface KaryawanMapper {
    Karyawan createKaryawanRequestDTOToKaryawan(CreateKaryawanRequestDTO createKaryawanRequestDTO);
}
