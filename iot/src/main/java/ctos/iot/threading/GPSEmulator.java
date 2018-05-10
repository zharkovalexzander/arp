package ctos.iot.threading;

import ctos.iot.Pair;
import ctos.iot.structure.GPSModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class GPSEmulator implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(GPSEmulator.class);
    private static final Pair[] TEST_COORDS = new Pair[]{
            Pair.of(-96.80692775125806, 32.77579189151535),
            Pair.of(-96.80700287662532, 32.77604925359501),
            Pair.of(-96.80705653760195, 32.77622082790125),
            Pair.of(-96.80710483248089, 32.7763743414738),
            Pair.of(-96.80714239516453, 32.77650979440611),
            Pair.of(-96.80717889337909, 32.776642796665286),
            Pair.of(-96.80723792045337, 32.77683694521854),
            Pair.of(-96.80727863631492, 32.77698302264041),
            Pair.of(-96.80733229729151, 32.77712750477259),
            Pair.of(-96.80739442043621, 32.777337404609504),
            Pair.of(-96.80745127137895, 32.77753268130673),
            Pair.of(-96.80749956625787, 32.77768619261613),
            Pair.of(-96.8075435427938, 32.777852066296575),
            Pair.of(-96.80759183767276, 32.77802363712677),
            Pair.of(-96.80763690899299, 32.778204892106274),
            Pair.of(-96.8077066682626, 32.778417097243214),
            Pair.of(-96.80776032923922, 32.77859318197262),
            Pair.of(-96.80782146509604, 32.77882771393801),
            Pair.of(-96.80788797376908, 32.779027060856215),
            Pair.of(-96.80798560792233, 32.7793428988451),
            Pair.of(-96.80805319458335, 32.77955041523823),
            Pair.of(-96.80795671904042, 32.77957352601689),
            Pair.of(-96.80789232586848, 32.77959610070827),
            Pair.of(-96.80780110220823, 32.77961867539393),
            Pair.of(-96.80765621757138, 32.77964576500914),
            Pair.of(-96.80750175599024, 32.779683047382925),
            Pair.of(-96.80729574893357, 32.77972813967456),
            Pair.of(-96.80715623039433, 32.77975522925644),
            Pair.of(-96.80692127062976, 32.77980570519247),
            Pair.of(-96.80666810698519, 32.779865652557),
            Pair.of(-96.80642663259039, 32.779910801785796),
            Pair.of(-96.806115398926, 32.77998304050422),
            Pair.of(-96.80571541310263, 32.78007315829479),
            Pair.of(-96.80542027773122, 32.78012282232938),
            Pair.of(-96.80538271504757, 32.7801453968814),
            Pair.of(-96.80525929480135, 32.78015894160986),
            Pair.of(-96.80513923264816, 32.780173212512544),
            Pair.of(-96.80498361581594, 32.78021384667905),
            Pair.of(-96.80478507020246, 32.78025899573117),
            Pair.of(-96.80461335507728, 32.780299629858455),
            Pair.of(-96.80449530092869, 32.78032671926638),
            Pair.of(-96.80415604776857, 32.78040226206041),
            Pair.of(-96.80369456336963, 32.780492559950474),
            Pair.of(-96.8031901501894, 32.78060994707057),
            Pair.of(-96.80313648921276, 32.7803842024714),
            Pair.of(-96.80305063165017, 32.780122338018764),
            Pair.of(-96.8029755062829, 32.779815323541065),
            Pair.of(-96.8029647740876, 32.779607636676026),
            Pair.of(-96.80293256406453, 32.77937288815117),
            Pair.of(-96.80291109967392, 32.77918326008983),
            Pair.of(-96.80287890308792, 32.77900266156048),
            Pair.of(-96.8028252421113, 32.77877691288325),
            Pair.of(-96.80276743075488, 32.77846957242165),
            Pair.of(-96.80267084099694, 32.77821673234988),
            Pair.of(-96.80261718002032, 32.77795486151814),
            Pair.of(-96.80252707801849, 32.77775490006392),
            Pair.of(-96.80247778241464, 32.777589510898785),
            Pair.of(-96.80238119265674, 32.77729151779135),
            Pair.of(-96.80230606728945, 32.7770206141004),
            Pair.of(-96.80225240631282, 32.776876131794666),
            Pair.of(-96.80222020972687, 32.776776800073385),
            Pair.of(-96.80223094192218, 32.77671358892031),
            Pair.of(-96.80251374306502, 32.77671490927459),
            Pair.of(-96.80280351233874, 32.776669758424134),
            Pair.of(-96.80310401380783, 32.77664266790285),
            Pair.of(-96.8033615864956, 32.776606547195),
            Pair.of(-96.80362989137872, 32.77655236610571),
            Pair.of(-96.8039733216291, 32.776480124602045),
            Pair.of(-96.8043382162701, 32.776398852840295),
            Pair.of(-96.80471384310644, 32.77631758100433),
            Pair.of(-96.8051753275054, 32.77622727887731),
            Pair.of(-96.80550802556046, 32.776155037109675),
            Pair.of(-96.8059265811781, 32.776046674348336),
            Pair.of(-96.80629147581912, 32.77598346267661),
            Pair.of(-96.80665637046015, 32.775911220711066),
            Pair.of(-96.80692775125806, 32.77579189151535)
    };

    @Autowired
    private GPSModule gpsModule;

    private Queue<Pair> coordinates;
    private Pair<Double, Double> previous;
    private boolean isStop;

    public boolean isStop() {
        return isStop;
    }

    public void setUp() {
        this.coordinates = new LinkedList<>(Arrays.asList(TEST_COORDS));
        previous = this.coordinates.poll();
        isStop = false;
    }

    @Override
    public void run() {
        AtomicLong atomicLong = new AtomicLong(1);
        while (true) {
            try {
                Pair<Double, Double> current = this.coordinates.poll();
                if (current != null) {
                    gpsModule.putCoord(current);
                    gpsModule.sendData();
                    if ((atomicLong.get() % 10) == 0) {
                        isStop = true;
                        atomicLong.set(1);
                        Thread.sleep(30000);
                        isStop = false;
                    } else {
                        atomicLong.incrementAndGet();
                        Thread.sleep(1000);
                    }
                    previous = current;
                    this.coordinates.offer(current);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private long calculateTimeout(Pair<Double, Double> a, Pair<Double, Double> b) {
        return TimeUnit.HOURS.toMillis(Math.round(distance(a.getValue(), a.getKey(), b.getValue(), b.getKey(), "K") * 20));
    }

    private double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit.equals("K")) {
            dist = dist * 1.609344;
        } else if (unit.equals("N")) {
            dist = dist * 0.8684;
        }

        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
