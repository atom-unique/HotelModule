package org.kravchenko.hotel.service.impl;

import lombok.AllArgsConstructor;
import org.kravchenko.hotel.exception.EntityNotFoundException;
import org.kravchenko.hotel.model.Room;
import org.kravchenko.hotel.repository.RoomRepository;
import org.kravchenko.hotel.service.RoomService;
import org.kravchenko.hotel.service.converter.RoomConverter;
import org.kravchenko.hotel.service.converter.RoomPriceConverter;
import org.kravchenko.hotel.service.dto.RoomDto;
import org.kravchenko.hotel.service.dto.RoomPriceDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    private final RoomConverter roomConverter;

    private final RoomPriceConverter roomPriceConverter;

    @Override
    public void addRoom(RoomDto roomDto) {
        roomRepository.save(roomConverter.toModel(roomDto));
    }

    @Override
    public RoomDto findRoom(Long id) {
        return roomConverter.toDto(roomRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(id)
        ));
    }

    @Override
    public List<RoomDto> findAllRoomsSortedByPrice() {
        return roomRepository.findAll()
                .stream()
                .map(roomConverter::toDto)
                .sorted(Comparator.comparingInt(RoomDto::getPrice))
                .toList();
    }

    @Override
    public List<RoomDto> findAllRoomsSortedByCapacity() {
        return roomRepository.findAll()
                .stream()
                .map(roomConverter::toDto)
                .sorted(Comparator.comparingInt(RoomDto::getCapacity))
                .toList();
    }

    @Override
    public List<RoomDto> findAllRoomsSortedByRating() {
        return roomRepository.findAll()
                .stream()
                .map(roomConverter::toDto)
                .sorted(Comparator.comparingInt(RoomDto::getRating))
                .toList();
    }

    @Override
    public List<RoomDto> findAllFreeRoomsSortedByPrice() {
        return roomRepository.findAllByIsOccupiedIsFalse()
                .stream()
                .map(roomConverter::toDto)
                .sorted(Comparator.comparingInt(RoomDto::getPrice))
                .toList();
    }

    @Override
    public List<RoomDto> findAllFreeRoomsSortedByCapacity() {
        return roomRepository.findAllByIsOccupiedIsFalse()
                .stream()
                .map(roomConverter::toDto)
                .sorted(Comparator.comparingInt(RoomDto::getCapacity))
                .toList();
    }

    @Override
    public List<RoomDto> findAllFreeRoomsSortedByRating() {
        return roomRepository.findAllByIsOccupiedIsFalse()
                .stream()
                .map(roomConverter::toDto)
                .sorted(Comparator.comparingInt(RoomDto::getRating))
                .toList();
    }

    @Override
    public void roomPriceUpdate(Long id, int price) {
        Room room = getRoom(id);
        room.setPrice(price);
        roomRepository.save(room);
    }

    @Override
    public void roomStatusUpdate(Long id, boolean inService) {
        Room room = getRoom(id);
        room.setInService(inService);
        roomRepository.save(room);
    }

    @Override
    public int getAllFreeRoomsCount() {
        return roomRepository.findAllByIsOccupiedIsFalse().size();
    }

    @Override
    public List<RoomPriceDto> getAllRoomsWithPrice() {
        return roomRepository.findAll()
                .stream()
                .map(roomPriceConverter::toDto)
                .sorted(Comparator.comparingInt(RoomPriceDto::getPrice))
                .toList();
    }

    private Room getRoom(Long id) {
        return roomRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(id)
        );
    }

}
