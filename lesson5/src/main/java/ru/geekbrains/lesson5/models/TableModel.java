package ru.geekbrains.lesson5.models;

import ru.geekbrains.lesson5.presenters.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

public class TableModel implements Model {


    private Collection<Table> tables;

    /**
     * Получить все столки
     *
     * @return
     */
    @Override
    public Collection<Table> loadTables() {
        if (tables == null) {
            tables = new ArrayList<>();

            tables.add(new Table());
            tables.add(new Table());
            tables.add(new Table());
            tables.add(new Table());
            tables.add(new Table());
        }

        return tables;
    }

    /**
     * Бронирование столика
     *
     * @param reservationDate Дата бронирования
     * @param tableNo         номер столика
     * @param name            Имя
     */
    @Override
    public int reservationTable(Date reservationDate, int tableNo, String name) {
        for (Table table : loadTables()) {
            if (table.getNo() == tableNo) {
                Reservation reservation = new Reservation(reservationDate, name);
                table.getReservations().add(reservation);
                return reservation.getId();
            }
        }
        throw new RuntimeException("Некорректный номер столика.");
    }
    /**
     * TODO: Разработать самостоятельно
     * Изменить бронь столика
     * @param oldReservation номер старого резерва (для снятия)
     * @param reservationDate дата резерва столика
     * @param tableNo номер столика
     * @param name Имя
     */
    public int changeReservationTable(int oldReservation, Date reservationDate, int tableNo, String name){
        for (Table table : loadTables()) {
            Iterator<Reservation> iterator = table.getReservations().iterator();

            while(iterator.hasNext()) {
                Reservation reservation = iterator.next();
                if (reservation.getId() == oldReservation) {
                    iterator.remove();

                    if (table.getNo() == tableNo) {
                        Reservation newReservation = new Reservation(reservationDate, name);
                        table.getReservations().add(newReservation);
                        return newReservation.getId();
                    }
                }
            }
        }

        throw new RuntimeException("Бронь не найдена.");
    }

}
