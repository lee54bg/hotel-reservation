package com.frankmoley.lil.learningspring.business.service;

import com.frankmoley.lil.learningspring.business.domain.RoomReservation;
import com.frankmoley.lil.learningspring.data.entity.Guest;
import com.frankmoley.lil.learningspring.data.entity.Reservation;
import com.frankmoley.lil.learningspring.data.entity.Room;
import com.frankmoley.lil.learningspring.data.repository.GuestRepository;
import com.frankmoley.lil.learningspring.data.repository.ReservationRepository;
import com.frankmoley.lil.learningspring.data.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservationService {
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<RoomReservation> getRoomReservationsForDate(Date date) {
        Iterable<Room> rooms = this.roomRepository.findAll();
        HashMap<Long, RoomReservation> roomReservationMap = new HashMap();
        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getRoomId());
            roomReservation.setRoomName(room.getRoomName());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservationMap.put(room.getRoomId(), roomReservation);
        });

        Iterable<Reservation> reservations = this.reservationRepository.findReservationByReservationDate(new java.sql.Date(date.getTime()));
        reservations.forEach(reservation -> {
            RoomReservation roomReservation = roomReservationMap.get(reservation.getReservationRoomId());
            roomReservation.setDate(date);
            Guest guest = this.guestRepository.findById(reservation.getReservationGuestId()).get();
            roomReservation.setFirstName(guest.getGuestFirstName());
            roomReservation.setLastName(guest.getGuestLastName());
            roomReservation.setGuestId(guest.getGuestId());
        });

        List<RoomReservation> roomReservations = new ArrayList<>();
        for(Long id: roomReservationMap.keySet()) {
            roomReservations.add(roomReservationMap.get(id));
        }
        return roomReservations;
    }
}
