package com.frankmoley.lil.learningspring.data.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="RESERVATION")
public class Reservation {
    @Id
    @Column(name="RESERVATION_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long reservationId;
    @Column(name="ROOM_ID")
    private long reservationRoomId;
    @Column(name="GUEST_ID")
    private long reservationGuestId;
    @Column(name="RES_DATE")
    private Date reservationDate;

    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }

    public long getReservationRoomId() {
        return reservationRoomId;
    }

    public void setReservationRoomId(long reservationRoomId) {
        this.reservationRoomId = reservationRoomId;
    }

    public long getReservationGuestId() {
        return reservationGuestId;
    }

    public void setReservationGuestId(long reservationGuestId) {
        this.reservationGuestId = reservationGuestId;
    }

    public Date getReservationResDate() {
        return reservationDate;
    }

    public void setReservationResDate(Date reservationResDate) {
        this.reservationDate = reservationResDate;
    }
}
