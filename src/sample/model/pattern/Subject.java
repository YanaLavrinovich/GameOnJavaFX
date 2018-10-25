package sample.model.pattern;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<Observer> observerList = new ArrayList<>();
    private boolean changed;

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public void addObserver(Observer observer) {
        observerList.add(observer);
    }

    public void notifyAllObservers() {
        for(Observer observer : observerList) {
            observer.update();
        }
        setChanged(false);
    }
}
