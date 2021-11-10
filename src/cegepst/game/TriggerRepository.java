package cegepst.game;

import cegepst.engine.entities.StaticEntity;
import cegepst.game.entities.Trigger;

import java.util.ArrayList;
import java.util.HashMap;

public class TriggerRepository {

    private HashMap<Trigger, Triggerable> hashMap;

    public TriggerRepository() {
        hashMap = new HashMap<>();
    }

    public boolean isValueTriggeredByEntity(Triggerable triggerable, StaticEntity entity) {
        for (HashMap.Entry<Trigger, Triggerable> entry : hashMap.entrySet()) {
            if (entry.getValue().equals(triggerable) && entry.getKey().intersectWith(entity)) {
                return true;
            }
        }
        return false;
    }

    public void triggerValuesIfCollindingWithEntity(StaticEntity entity) {
        for (HashMap.Entry<Trigger, Triggerable> entry : hashMap.entrySet()) {
            if (entry.getKey().isTriggered(entity)) {
                entry.getValue().trigger();
            } else {
                entry.getValue().untrigger();
            }
        }
    }

    public Trigger getTrigger(Triggerable value) {
        for (HashMap.Entry<Trigger, Triggerable> entry : hashMap.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public Triggerable getTriggerable(Trigger key) {
        return hashMap.get(key);
    }

    public ArrayList<Trigger> getKeys() {
        ArrayList<Trigger> keys = new ArrayList<>();
        for (HashMap.Entry<Trigger, Triggerable> entry : hashMap.entrySet()) {
            keys.add(entry.getKey());
        }
        return keys;
    }

    public ArrayList<Triggerable> getValues() {
        ArrayList<Triggerable> values = new ArrayList<>();
        for (HashMap.Entry<Trigger, Triggerable> entry : hashMap.entrySet()) {
            values.add(entry.getValue());
        }
        return values;
    }

    public void addEntry(Trigger trigger, Triggerable triggerable) {
        hashMap.put(trigger, triggerable);
    }

    public void addEntries(HashMap<Trigger, Triggerable> entries) {
        hashMap.putAll(entries);
    }

    public void removeEntry(Trigger key) {
        hashMap.remove(key);
    }

    public void clear() {
        hashMap.clear();
    }

    public boolean contains(Trigger key) {
        return hashMap.containsKey(key);
    }

    public HashMap<Trigger, Triggerable> getHashMap() {
        return hashMap;
    }

    public void setHashMap(HashMap<Trigger, Triggerable> hashMap) {
        this.hashMap = hashMap;
    }
}
