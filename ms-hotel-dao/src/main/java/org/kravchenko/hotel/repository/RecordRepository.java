package org.kravchenko.hotel.repository;

import org.kravchenko.hotel.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {

    List<Record> findByClientId(Long clientId);

    List<Record> findByRoomId(Long roomId);

    Record findFirstByRoomIdAndIsCheckedOutIsFalse(Long roomId);

    List<Record> findByIsCheckedOutFalse();

}
