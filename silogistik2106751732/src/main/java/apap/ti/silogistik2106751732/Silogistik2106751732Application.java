package apap.ti.silogistik2106751732;

import apap.ti.silogistik2106751732.DTO.GudangMapper;
import apap.ti.silogistik2106751732.DTO.KaryawanMapper;
import apap.ti.silogistik2106751732.DTO.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106751732.DTO.request.CreateKaryawanRequestDTO;
import apap.ti.silogistik2106751732.service.GudangService;
import apap.ti.silogistik2106751732.service.KaryawanService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Silogistik2106751732Application {

	public static void main(String[] args) {
		SpringApplication.run(Silogistik2106751732Application.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner run(GudangService gudangService, KaryawanService karyawanService, GudangMapper gudangMapper, KaryawanMapper karyawanMapper){
		return args -> {
			var faker = new Faker(new Locale("in-ID"));
			for(int i=0; i<3; i++){
				var gudangDTO = new CreateGudangRequestDTO();
				var karyawanDTO = new CreateKaryawanRequestDTO();
				gudangDTO.setAlamatGudang(faker.address().fullAddress());
				gudangDTO.setNamaGudang(faker.company().name());
				karyawanDTO.setNama(faker.name().fullName());
				karyawanDTO.setJenisKelamin(faker.number().numberBetween(1,3));
				karyawanDTO.setTanggalLahir(faker.date().birthday());

				var gudang= gudangMapper.createGudangRequestDTOToGudang(gudangDTO);
				var karyawan = karyawanMapper.createKaryawanRequestDTOToKaryawan(karyawanDTO);

				gudangService.saveGudang(gudang);
				karyawanService.saveKaryawan(karyawan);

			}
		};
	}

}
