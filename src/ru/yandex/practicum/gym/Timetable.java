package ru.yandex.practicum.gym;

import java.util.*;

public class Timetable {

    private final Map<DayOfWeek, Map<TimeOfDay, ArrayList<TrainingSession>>> timetable = new HashMap<>();


    public void addNewTrainingSession(TrainingSession trainingSession) {
        //сохраняем занятие в расписании
        DayOfWeek day = trainingSession.getDayOfWeek();
        TimeOfDay time = trainingSession.getTimeOfDay();
        TreeMap<TimeOfDay, ArrayList<TrainingSession>> daySessions =
                (TreeMap<TimeOfDay, ArrayList<TrainingSession>>) timetable.get(day);
        ArrayList<TrainingSession> timeSessions = daySessions.getOrDefault(time, new ArrayList<>());
        timeSessions.add(trainingSession);
        daySessions.put(time, timeSessions);
        timetable.compute(day, (k, v) -> daySessions);
        System.out.println(timetable);
    }

    public ArrayList<TrainingSession> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        //как реализовать, тоже непонятно, но сложность должна быть О(1)
        ArrayList<TrainingSession> trainingList = new ArrayList<>();
        for (TimeOfDay time : timetable.get(dayOfWeek).keySet()) {
            trainingList.addAll(timetable.get(dayOfWeek).get(time));
        }
        return trainingList;
    }

    public ArrayList<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        //как реализовать, тоже непонятно, но сложность должна быть О(1)
        return timetable.get(dayOfWeek).get(timeOfDay);
        // вроде бы тут возвращается список тренироваок в конкретное время
    }

    public List<CounterOfTrainingsForCoach> getCountByCoaches() {
        List<CounterOfTrainingsForCoach> counterOfTrainings = new ArrayList<>();
        Map<Coach, Integer> countMap = new LinkedHashMap<>();

        for (Map.Entry<DayOfWeek, Map<TimeOfDay, ArrayList<TrainingSession>>> dayEntry : timetable.entrySet()) {

            TreeMap<TimeOfDay, ArrayList<TrainingSession>> daySchedule =
                    (TreeMap<TimeOfDay, ArrayList<TrainingSession>>) dayEntry.getValue();

            for (Map.Entry<TimeOfDay, ArrayList<TrainingSession>> timeEntry : daySchedule.entrySet()) {

                ArrayList<TrainingSession> sessions = timeEntry.getValue();

                for (TrainingSession session : sessions) {
                    Coach coach = session.getCoach();
                    countMap.put(coach, countMap.getOrDefault(coach, 0) + 1);
                }
            }
        }

        for (Map.Entry<Coach, Integer> entry : countMap.entrySet()) {
            counterOfTrainings.add(new CounterOfTrainingsForCoach(entry.getKey(), entry.getValue()));
        }

        return counterOfTrainings;
    }

    public Timetable() {
        for (DayOfWeek day : DayOfWeek.values()) {
            timetable.put(day, new TreeMap<>());
        }
    }
}
