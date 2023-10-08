package apap.ti.silogistik2106751732.DTO;

import apap.ti.silogistik2106751732.DTO.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106751732.DTO.response.ReadGudangBarangResponseDTO;
import apap.ti.silogistik2106751732.DTO.response.ReadGudangResponseDTO;
import apap.ti.silogistik2106751732.model.Gudang;
import apap.ti.silogistik2106751732.model.GudangBarang;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface GudangMapper {
    Gudang createGudangRequestDTOToGudang(CreateGudangRequestDTO createGudangRequestDTO);

    @Mapping(target = "listBarangDimuatGudang", ignore = true)
    ReadGudangResponseDTO gudangToReadGudangResponseDTO(Gudang gudang);

    @AfterMapping
    default void fillListBarangDimuatGudang(@MappingTarget ReadGudangResponseDTO responseDTO, Gudang gudang) {
        List<ReadGudangBarangResponseDTO> listGudangBarangDto = new ArrayList<>();
        for (GudangBarang gudangBarang: gudang.getListBarangDimuatGudang()) {
            ReadGudangBarangResponseDTO gudangBarangDto = new ReadGudangBarangResponseDTO();
            gudangBarangDto.setId(gudangBarang.getId());
            gudangBarangDto.setStok(gudangBarang.getStok());
            gudangBarangDto.setHargaBarang(gudangBarang.getSkuBarang().getHargaBarang());
            gudangBarangDto.setMerk(gudangBarang.getSkuBarang().getMerk());
            listGudangBarangDto.add(gudangBarangDto);
        }
        responseDTO.setListBarangDimuatGudang(listGudangBarangDto);
    }
}
