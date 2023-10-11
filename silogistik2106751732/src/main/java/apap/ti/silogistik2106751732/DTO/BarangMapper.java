package apap.ti.silogistik2106751732.DTO;

import apap.ti.silogistik2106751732.DTO.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106751732.DTO.response.ReadBarangGudangResponseDTO;
import apap.ti.silogistik2106751732.DTO.response.ReadBarangResponseDTO;
import apap.ti.silogistik2106751732.model.Barang;
import apap.ti.silogistik2106751732.model.GudangBarang;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface BarangMapper {
    Barang createBarangRequestDTOToBarang(CreateBarangRequestDTO createBarangRequestDTO);


    @Mapping(target = "listGudangMemuatBarang", ignore = true)
    @Mapping(target = "totalStok", ignore = true)
    ReadBarangResponseDTO barangToReadBarangResponseDTO(Barang barang);

    @AfterMapping
    default void fillListGudangMemuatBarangAndSetTotalStok(@MappingTarget ReadBarangResponseDTO responseDTO, Barang barang) {
        List<ReadBarangGudangResponseDTO> listBarangGudangDto = new ArrayList<>();
        int totalStok = 0;
        for (GudangBarang barangGudang: barang.getListGudangMemuatBarang()) {
            ReadBarangGudangResponseDTO barangGudangDto = new ReadBarangGudangResponseDTO();
            barangGudangDto.setIdGudang(barangGudang.getId());
            barangGudangDto.setNamaGudang(barangGudang.getIdGudang().getNamaGudang());
            barangGudangDto.setAlamatGudang(barangGudang.getIdGudang().getAlamatGudang());
            barangGudangDto.setStok(barangGudang.getStok());
            listBarangGudangDto.add(barangGudangDto);
            totalStok = totalStok + barangGudang.getStok();
        }
        responseDTO.setListGudangMemuatBarang(listBarangGudangDto);
        responseDTO.setTotalStok(totalStok);
    }
}
