package edu.eci.cvds.reservas.repository.reservation;

import edu.eci.cvds.reservas.model.Reservation;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepositoryMongo extends ReservationRepository, MongoRepository<Reservation, String>  {


    @Override
    public default Reservation saveReservation(Reservation reservation){
        reservation.setLaboratory(null);
        reservation.setDateHour(null);
        reservation.setCreationDate(LocalDateTime.now());
        if(reservation.getCreationDate() == null){
            reservation.setCreationDate(java.time.LocalDateTime.now());
        }
        save(reservation);
        return reservation;
    }


    @Override
    public default List<Reservation> findAllReservations(){
        return findAll();
    }


    @Override
    public default void deleteReservation(Reservation reservation){
        if(!existsById(reservation.getId())){
            throw new RuntimeException("Reservation not found");
        }
        delete(reservation);
    }


    @Override
    public default Reservation findReservationById(String id){
        return findById(id).orElse(null);
    }


    @Override
    public default Reservation updateReservation(Reservation reservation){
        if(!existsById(reservation.getId())){
            throw new RuntimeException("Task not found");
        }
        save(reservation);
        return reservation;
    }



    @Override
    public default boolean existsById(String id){
        Reservation reservation = findById(id).orElse(null);
        return reservation != null;
}}

