package com.api.parkingcontrol.services;

import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.repositories.ParkingSpotRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParkingSpotService {
  // Cria ponto de injeção - criação de bean
  final ParkingSpotRepository parkingSpotRepository;

  public ParkingSpotService(ParkingSpotRepository parkingSpotRepository) {
    this.parkingSpotRepository = parkingSpotRepository;
  }

  @Transactional
  public ParkingSpotModel save(ParkingSpotModel parkingSpotModel) {
    return parkingSpotRepository.save(parkingSpotModel);
  }

  public boolean existsByLicensePlateCar(String licensePlateCar) {
    return parkingSpotRepository.existsByLicensePlateCar(licensePlateCar);
  }

  public boolean existsByParkingSpotNumber(String parkingSpotNumber) {
    return parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber);
  }

  public boolean existsByApartamentAndBlock(String apartament, String block) {
    return parkingSpotRepository.existsByApartamentAndBlock(apartament, block);
  }

  public Page<ParkingSpotModel> findAll(Pageable pageable) {
    return parkingSpotRepository.findAll(pageable);

  }

  public Optional<ParkingSpotModel> findById(UUID id) {
    return parkingSpotRepository.findById(id);

  }

  // metodo destrutivo - deleções em cascata muitos para muitos - para garantir o
  // rollback
  @Transactional
  public void delete(ParkingSpotModel parkingSpotModel) {
    parkingSpotRepository.delete(parkingSpotModel);
  }

}
