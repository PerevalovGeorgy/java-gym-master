package ru.yandex.practicum.gym;

import java.util.*;

public class CounterOfTrainings {

    Map<Coach, Integer> counterOfTrainings = new HashMap<>();

    public Map<Coach, Integer> listOfCoach(Timetable timetable) {
        HashMap<DayOfWeek, TreeMap<TimeOfDay, ArrayList<TrainingSession>>> schedule = timetable.getTimetable();

        for (Map.Entry<DayOfWeek, TreeMap<TimeOfDay, ArrayList<TrainingSession>>> dayEntry : schedule.entrySet()) {

            TreeMap<TimeOfDay, ArrayList<TrainingSession>> daySchedule = dayEntry.getValue();

            for (Map.Entry<TimeOfDay, ArrayList<TrainingSession>> timeEntry : daySchedule.entrySet()) {

                ArrayList<TrainingSession> sessions = timeEntry.getValue();

                for (TrainingSession session : sessions) {
                    Coach coach = session.getCoach();
                    counterOfTrainings.put(coach, counterOfTrainings.getOrDefault(coach, 0) + 1);
                }
            }
        }

        List<Map.Entry<Coach, Integer>> entries = new ArrayList<>(counterOfTrainings.entrySet());

        Collections.sort(entries, comparator);
        Map<Coach, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<Coach, Integer> entry : entries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    Comparator<Map.Entry<Coach, Integer>> comparator = new Comparator<Map.Entry<Coach, Integer>>() {
        @Override
        public int compare(Map.Entry<Coach, Integer> e1, Map.Entry<Coach, Integer> e2) {
            return e2.getValue().compareTo(e1.getValue());
        }
    };

}
