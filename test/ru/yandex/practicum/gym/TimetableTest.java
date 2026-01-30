package ru.yandex.practicum.gym;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TimetableTest {

    @Test
    void testGetTrainingSessionsForDaySingleSession() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(singleTrainingSession);
        //Проверить, что за понедельник вернулось одно занятие
        assertEquals(1, timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY).size());
        //Проверить, что за вторник не вернулось занятий
        assertTrue(timetable.getTrainingSessionsForDay(DayOfWeek.TUESDAY).isEmpty());
    }

    @Test
    void testGetTrainingSessionsForDayMultipleSessions() {
        Timetable timetable = new Timetable();

        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");

        Group groupAdult = new Group("Акробатика для взрослых", Age.ADULT, 90);
        TrainingSession thursdayAdultTrainingSession = new TrainingSession(groupAdult, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(20, 0));

        timetable.addNewTrainingSession(thursdayAdultTrainingSession);

        Group groupChild = new Group("Акробатика для детей", Age.CHILD, 60);
        TrainingSession mondayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession thursdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(13, 0));
        TrainingSession saturdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.SATURDAY, new TimeOfDay(10, 0));

        timetable.addNewTrainingSession(mondayChildTrainingSession);
        timetable.addNewTrainingSession(thursdayChildTrainingSession);
        timetable.addNewTrainingSession(saturdayChildTrainingSession);

        // Проверить, что за понедельник вернулось одно занятие
        assertEquals(1, timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY).size());
        // Проверить, что за четверг вернулось два занятия в правильном порядке: сначала в 13:00, потом в 20:00
        assertEquals(2, timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY).size());
        // Проверить, что за вторник не вернулось занятий
        assertTrue(timetable.getTrainingSessionsForDay(DayOfWeek.TUESDAY).isEmpty());
    }

    @Test
    void testGetTrainingSessionsForDayAndTime() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(singleTrainingSession);

        //Проверить, что за понедельник в 13:00 вернулось одно занятие
        assertEquals(1, timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY, new TimeOfDay(13, 0)).size());
        //Проверить, что за понедельник в 14:00 не вернулось занятий
        List<TrainingSession> emptiList = new ArrayList<>();
        assertEquals(emptiList,timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY, new TimeOfDay(14, 0)));
    }


    @Test
    void tesCountSessionsFirstCouchIsFour() {
        Timetable timetable = new Timetable();

        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");

        Group groupAdult = new Group("Акробатика для взрослых", Age.ADULT, 90);
        Group groupChild = new Group("Акробатика для детей", Age.CHILD, 60);


        TrainingSession thursdayAdultTrainingSession = new TrainingSession(groupAdult, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(20, 0));
        TrainingSession mondayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession thursdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(13, 0));
        TrainingSession saturdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.SATURDAY, new TimeOfDay(10, 0));


        timetable.addNewTrainingSession(thursdayAdultTrainingSession);
        timetable.addNewTrainingSession(mondayChildTrainingSession);
        timetable.addNewTrainingSession(thursdayChildTrainingSession);
        timetable.addNewTrainingSession(saturdayChildTrainingSession);

        assertEquals(4, timetable.getCountByCoaches().get(0).getCount());
    }

    @Test
    void tesGetPositionSecondCouchIsTwo() {
        Timetable timetable = new Timetable();

        Coach coach1 = new Coach("Васильев", "Николай", "Сергеевич");
        Coach coach2 = new Coach("Иванов", "Иван", "Иванович");

        Group groupAdult = new Group("Акробатика для взрослых", Age.ADULT, 90);
        Group groupChild = new Group("Акробатика для детей", Age.CHILD, 60);


        TrainingSession thursdayAdultTrainingSession = new TrainingSession(groupAdult, coach1,
                DayOfWeek.THURSDAY, new TimeOfDay(20, 0));
        TrainingSession mondayChildTrainingSession = new TrainingSession(groupChild, coach1,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession thursdayChildTrainingSession = new TrainingSession(groupChild, coach1,
                DayOfWeek.THURSDAY, new TimeOfDay(13, 0));
        TrainingSession saturdayChildTrainingSession = new TrainingSession(groupChild, coach2,
                DayOfWeek.SATURDAY, new TimeOfDay(10, 0));

        timetable.addNewTrainingSession(thursdayAdultTrainingSession);
        timetable.addNewTrainingSession(mondayChildTrainingSession);
        timetable.addNewTrainingSession(thursdayChildTrainingSession);
        timetable.addNewTrainingSession(saturdayChildTrainingSession);


        assertEquals(coach2, timetable.getCountByCoaches().get(1).getCoach());
    }

    @Test
    void tesCoachDoNotHaveSessions() {
        Timetable timetable = new Timetable();

        Coach coach1 = new Coach("Васильев", "Николай", "Сергеевич");
        Coach coach2 = new Coach("Иванов", "Иван", "Иванович");

        Group groupAdult = new Group("Акробатика для взрослых", Age.ADULT, 90);
        Group groupChild = new Group("Акробатика для детей", Age.CHILD, 60);


        TrainingSession thursdayAdultTrainingSession = new TrainingSession(groupAdult, coach1,
                DayOfWeek.THURSDAY, new TimeOfDay(20, 0));
        TrainingSession mondayChildTrainingSession = new TrainingSession(groupChild, coach1,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession thursdayChildTrainingSession = new TrainingSession(groupChild, coach1,
                DayOfWeek.THURSDAY, new TimeOfDay(13, 0));
        TrainingSession saturdayChildTrainingSession = new TrainingSession(groupChild, coach1,
                DayOfWeek.SATURDAY, new TimeOfDay(10, 0));

        timetable.addNewTrainingSession(thursdayAdultTrainingSession);
        timetable.addNewTrainingSession(mondayChildTrainingSession);
        timetable.addNewTrainingSession(thursdayChildTrainingSession);
        timetable.addNewTrainingSession(saturdayChildTrainingSession);

        List<Coach> list = new ArrayList<>();
        for (CounterOfTrainingsForCoach coachCounter : timetable.getCountByCoaches()) {
            list.add(coachCounter.getCoach());
        }
        assertFalse(list.contains(coach2));

    }


    @Test
    void tesCouchHaveTwoSessions() {
        Timetable timetable = new Timetable();

        Coach coach1 = new Coach("Васильев", "Николай", "Сергеевич");

        Group groupAdult = new Group("Акробатика для взрослых", Age.ADULT, 45);
        Group groupChild = new Group("Акробатика для детей", Age.CHILD, 40);

        TrainingSession mondayAdultTrainingSession = new TrainingSession(groupAdult, coach1,
                DayOfWeek.MONDAY, new TimeOfDay(12, 0));

        TrainingSession mondayChildTrainingSession = new TrainingSession(groupChild, coach1,
                DayOfWeek.MONDAY, new TimeOfDay(12, 0));


        timetable.addNewTrainingSession(mondayAdultTrainingSession);
        timetable.addNewTrainingSession(mondayChildTrainingSession);

        assertEquals(2, timetable.getCountByCoaches().get(0).getCount());

    }
}
