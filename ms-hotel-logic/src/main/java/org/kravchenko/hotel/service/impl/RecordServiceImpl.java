package org.kravchenko.hotel.service.impl;

import lombok.AllArgsConstructor;
import org.kravchenko.hotel.exception.EntityNotFoundException;
import org.kravchenko.hotel.exception.NoAvailablePlacesException;
import org.kravchenko.hotel.exception.RoomIsInServiceException;
import org.kravchenko.hotel.model.Client;
import org.kravchenko.hotel.model.Employee;
import org.kravchenko.hotel.model.Record;
import org.kravchenko.hotel.model.Room;
import org.kravchenko.hotel.repository.ClientRepository;
import org.kravchenko.hotel.repository.EmployeeRepository;
import org.kravchenko.hotel.repository.RecordRepository;
import org.kravchenko.hotel.repository.RoomRepository;
import org.kravchenko.hotel.repository.ServiceRepository;
import org.kravchenko.hotel.service.RecordService;
import org.kravchenko.hotel.service.converter.RecordClientDateConverter;
import org.kravchenko.hotel.service.converter.RecordClientWithRoomConverter;
import org.kravchenko.hotel.service.converter.RoomNoClientsConverter;
import org.kravchenko.hotel.service.dto.RecordClientDateDto;
import org.kravchenko.hotel.service.dto.RecordClientWithRoomDto;
import org.kravchenko.hotel.service.dto.RoomNoClientsDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@Transactional
@AllArgsConstructor
public class RecordServiceImpl implements RecordService {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final ClientRepository clientRepository;

    private final EmployeeRepository employeeRepository;

    private final RoomRepository roomRepository;

    private final ServiceRepository serviceRepository;

    private final RecordRepository recordRepository;

    private final RecordClientWithRoomConverter recordClientWithRoomConverter;

    private final RecordClientDateConverter recordClientDateConverter;

    private final RoomNoClientsConverter roomNoClientsConverter;

    @Override
    public void checkIn(Long clientId, Long employeeId, Long roomId, String checkOutDate, List<Long> services) {
        LocalDate date = LocalDate.parse(checkOutDate, DATE_TIME_FORMATTER);
        Client client = getClient(clientId);
        client.setServices(getServicesForClient(services));
        clientRepository.save(client);
        Employee employee = getEmployee(employeeId);
        Room room = getRoom(roomId);
        if (!room.isInService()) {
            List<Client> clients = room.getClients();
            if (clients.size() < room.getCapacity()) {
                clients.add(client);
            } else {
                throw new NoAvailablePlacesException(roomId);
            }
            room.setOccupied(true);
            room.setClients(clients);
        } else {
            throw new RoomIsInServiceException(roomId);
        }
        roomRepository.save(room);
        Record checkInRecord = mapRecord(client, room, employee, date);
        recordRepository.save(checkInRecord);
    }

    @Override
    public void checkOut(String name, String surname) {
        Client client = clientRepository.findClientByNameAndSurname(name, surname);
        List<Record> checkOutRecords = recordRepository.findByClientId(client.getId());
        Record checkOutRecord = checkOutRecords
                .stream()
                .filter(rec -> !rec.isCheckedOut())
                .findFirst()
                .orElseThrow(
                        () -> new EntityNotFoundException(client.getId())
                );
        checkOutRecord.setCheckedOut(true);
        checkOutRecord.setCheckOutDate(LocalDate.now());
        Room room = checkOutRecord.getRoom();
        List<Client> clients = room.getClients();
        clients.remove(client);
        room.setClients(clients);
        if (clients.isEmpty()) {
            room.setOccupied(false);
            room.setInService(true);
        }
        roomRepository.save(room);
        recordRepository.save(checkOutRecord);
    }

    @Override
    public int clientsCount() {
        return (int) recordRepository.findAll().stream().filter(rec -> !rec.isCheckedOut()).count();
    }

    @Override
    public List<RecordClientWithRoomDto> getClientsWithRooms() {
        return recordRepository.findAll()
                .stream()
                .filter(rec -> !rec.isCheckedOut())
                .map(recordClientWithRoomConverter::toDto)
                .distinct()
                .toList();
    }

    @Override
    public List<RecordClientWithRoomDto> getClientsWithRoomsByCheckOut() {
        return recordRepository.findAll()
                .stream()
                .filter(rec -> !rec.isCheckedOut())
                .sorted(Comparator.comparing(Record::getCheckOutDate))
                .map(recordClientWithRoomConverter::toDto)
                .distinct()
                .toList();
    }

    @Override
    public int getTotalForRoom(Long id) {
        Room room = getRoom(id);
        List<Client> clients = room.getClients();
        List<org.kravchenko.hotel.model.Service> serviceList = new ArrayList<>();
        for (Client client : clients) {
            serviceList.addAll(client.getServices());
        }
        int serviceTotal = serviceList
                .stream()
                .mapToInt(org.kravchenko.hotel.model.Service::getPrice)
                .sum();
        Record record = recordRepository.findFirstByRoomIdAndIsCheckedOutIsFalse(id);
        if (record != null) {
            int clientTotal = room.getPrice()
                    * (int) DAYS.between(record.getCheckInDate(), record.getCheckOutDate());
            return serviceTotal + clientTotal;
        }
        return 0;
    }

    @Override
    public List<RecordClientDateDto> getLastRoomClients(Long roomId) {
        return recordRepository.findByRoomId(roomId).stream()
                .map(recordClientDateConverter::toDto)
                .sorted(Comparator.comparing(RecordClientDateDto::getCheckOutDate).reversed())
                .limit(3L)
                .toList();
    }

    @Override
    public List<RoomNoClientsDto> getAllFreeRoomsByDate(String dateLine) {
        LocalDate date = LocalDate.parse(dateLine, DATE_TIME_FORMATTER);
        List<Room> free = roomRepository.findAllByIsOccupiedIsFalse();
        List<Room> willFree = recordRepository.findByIsCheckedOutFalse().stream()
                .filter(rec -> rec.getCheckOutDate().isBefore(date))
                .map(Record::getRoom)
                .distinct()
                .toList();
        free.addAll(willFree);
        return free.stream()
                .map(roomNoClientsConverter::toDto)
                .sorted(Comparator.comparing(RoomNoClientsDto::getId)).toList();
    }

    private List<org.kravchenko.hotel.model.Service> getServicesForClient(List<Long> services) {
        List<org.kravchenko.hotel.model.Service> serviceList = new ArrayList<>();
        for (Long id : services) {
            serviceList.add(serviceRepository.findById(id).orElseThrow(
                    () -> new EntityNotFoundException(id)
            ));
        }
        return serviceList;
    }

    private Room getRoom(Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(
                () -> new EntityNotFoundException(roomId)
        );
        return room;
    }

    private Employee getEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new EntityNotFoundException(employeeId)
        );
        return employee;
    }

    private Client getClient(Long clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(
                () -> new EntityNotFoundException(clientId)
        );
        return client;
    }

    private static Record mapRecord(Client client, Room room, Employee employee, LocalDate date) {
        Record checkInRecord = new Record();
        checkInRecord.setClient(client);
        checkInRecord.setRoom(room);
        checkInRecord.setEmployee(employee);
        checkInRecord.setCheckInDate(LocalDate.now());
        checkInRecord.setCheckedOut(false);
        checkInRecord.setCheckOutDate(date);
        return checkInRecord;
    }

}
