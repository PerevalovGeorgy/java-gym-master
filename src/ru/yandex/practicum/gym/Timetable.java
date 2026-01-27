package ru.yandex.practicum.gym;

import java.util.*;

public class Timetable {

    private final HashMap<DayOfWeek, TreeMap<TimeOfDay, ArrayList<TrainingSession>>> timetable = new HashMap<>();

    Comparator<TimeOfDay> comparator = new Comparator<>() {
        @Override
        public int compare(TimeOfDay o1, TimeOfDay o2) {
            return o1.compareTo(o2);
        }
    };


    public void addNewTrainingSession(TrainingSession trainingSession) {
        //сохраняем занятие в расписании
        DayOfWeek day = trainingSession.getDayOfWeek();
        TimeOfDay time = trainingSession.getTimeOfDay();
        TreeMap<TimeOfDay, ArrayList<TrainingSession>> daySessions = timetable.getOrDefault(day,
                new TreeMap<>(comparator));
        ArrayList<TrainingSession> timeSessions = daySessions.getOrDefault(time, new ArrayList<>());
        timeSessions.add(trainingSession);
        daySessions.put(time, timeSessions);
        timetable.put(day, daySessions);
        System.out.println(timetable);
    }

    public TreeMap<TimeOfDay, ArrayList<TrainingSession>> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        //как реализовать, тоже непонятно, но сложность должна быть О(1)
        return timetable.get(dayOfWeek);
    }

    public ArrayList<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        //как реализовать, тоже непонятно, но сложность должна быть О(1)
        return timetable.get(dayOfWeek).get(timeOfDay);
    }

    public Map<Coach, Integer> getCountByCoaches(Timetable timetable) {
        CounterOfTrainings counter = new CounterOfTrainings();
        return counter.listOfCoach(timetable);
    }

    public HashMap<DayOfWeek, TreeMap<TimeOfDay, ArrayList<TrainingSession>>> getTimetable() {
        return timetable;
    }
}
