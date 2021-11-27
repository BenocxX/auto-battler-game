package cegepst.engine.helpers;

public class LoopingIndex {

    private int currentIndex;
    private int minIndex;
    private int maxIndex;

    public LoopingIndex() {
        currentIndex = 0;
        minIndex = 0;
        maxIndex = 0;
    }

    public void decrement() {
        if (currentIndex > minIndex) {
            currentIndex--;
        } else {
            currentIndex = maxIndex;
        }
    }

    public void increment() {
        if (currentIndex < maxIndex) {
            currentIndex++;
        } else {
            currentIndex = minIndex;
        }
    }

    public int getIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public void setMinIndex(int minIndex) {
        this.minIndex = minIndex;
    }

    public void setMaxIndex(int maxIndex) {
        this.maxIndex = maxIndex;
    }
}
