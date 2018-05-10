package springapp.service;

import org.omg.CORBA.Object;
import org.springframework.stereotype.Service;
import springapp.entities.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class PointService {
    private Map<String, Map<String, Queue<Pair<Object, Object>>>> queue = new HashMap<>();

    public void addDevice(String id) {
        queue.computeIfAbsent(id, k -> new HashMap<>());
    }

    public void addModule(String id, String type) {
        if (queue.get(id) != null && queue.get(id).get(type) == null) {
            queue.get(id).put(type, new ConcurrentLinkedQueue<>());
        }
    }

    public void add(String id, String type, Pair pair) {
        queue.get(id).get(type).offer(pair);
    }

    public Pair poll(String id, String type) {
        Pair p = queue.get(id).get(type).poll();
        return p == null ? Pair.of("is", null) : p;
    }
}
