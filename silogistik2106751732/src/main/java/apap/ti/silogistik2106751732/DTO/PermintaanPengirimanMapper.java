package apap.ti.silogistik2106751732.DTO;

import apap.ti.silogistik2106751732.DTO.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106751732.DTO.response.ReadListItemPermintaanPengirimanResponseDTO;
import apap.ti.silogistik2106751732.model.PermintaanPengiriman;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermintaanPengirimanMapper {
    ReadListItemPermintaanPengirimanResponseDTO permintaanPengirimanToReadListItemPermintaanPengirimanResponseDTO(PermintaanPengiriman permintaanPengiriman);
    PermintaanPengiriman createPermintaanPengirimanRequestDTOToPermintaanPengiriman(CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO);
}
