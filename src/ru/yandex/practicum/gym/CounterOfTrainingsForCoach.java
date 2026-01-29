package ru.yandex.practicum.gym;

import java.util.Objects;

public class CounterOfTrainingsForCoach implements Comparable<CounterOfTrainingsForCoach> {
    private Coach coach;
    private Integer count;

    public CounterOfTrainingsForCoach(Coach coach, Integer count) {
        this.coach = coach;
        this.count = count;
    }

    public Coach getCoach() {
        return coach;
    }

    public Integer getCount() {
        return count;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CounterOfTrainingsForCoach that = (CounterOfTrainingsForCoach) o;
        return Objects.equals(coach, that.coach) && Objects.equals(count, that.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coach, count);
    }

    @Override
    public int compareTo(CounterOfTrainingsForCoach o) {
        return count - o.count;
    }
}
