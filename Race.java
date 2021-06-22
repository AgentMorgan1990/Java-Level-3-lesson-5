package Lesson3_5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

public class Race {
    private CopyOnWriteArrayList<Stage> stages;

    public CopyOnWriteArrayList<Stage> getStages() {
        return stages;
    }

    public Race(Stage... stages) {
        this.stages = new CopyOnWriteArrayList<>(Arrays.asList(stages));
    }
}
